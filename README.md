# wikiSearch
research project for solr

## Getting solr ready

### start solr instant at port 8983 (default port) and 9000:
bin/ $ ./solr start -p 8983
bin/ $ ./solr start -p 9000

### Create core 'wiki':
creates 3 cores
./solr create -c wiki
./solr create -c wikimaster -d sample_techproducts_configs -p 8983 -rf 2
./solr create -c wikislave -d sample_techproducts_configs -p 9000

please note that -d sample_techproducts_configs optional, it generate more configs from solr configset

### download wikipedia article dump file from:
https://dumps.wikimedia.org/enwiki/latest/
* enwiki-latest-stub-articles.xml.gz (full version)
* enwiki-latest-stub-articles1.xml.gz (first part of article dump)

there are lots of event, log, history files there but we are not using those

### unarchive these two files
put enwiki-latest-stub-articles1.xml file into solr/server/solr/wiki/input
put enwiki-latest-stub-articles.xml file into solr/server/solr/wikimaster/input

### change solr config

* for master core folders change it's solrconfig.xml

include dataimporthandler lib

add: ```<lib dir="${solr.install.dir:../../../..}/dist/" regrex="solr-dataimporthandler-.*\.jar"/>``` at line 86

replace ManagedIndexSchemaFactory with classicIndex schemaFactory
```
<schemaFactory class="ClassicIndexSchemaFactory"/>

<!--
    <schemaFactory class="ManagedIndexSchemaFactory">
      <bool name="mutable">true</bool>
      <str name="managedSchemaResourceName">managed-schema</str>
    </schemaFactory>
  -->
```
 add DataImportHandler
```
<requestHandler name="/dihupdate" class="org.apache.solr.handler.dataimport.DataImportHandler" startup="lazy">
    <lst name="defaults">
      <str name="config">data-config.xml</str>
    </lst>
</requestHandler>
```
add replicationHandler
    
```
<requestHandler name="/replication" class="solr.ReplicationHandler" >
          <lst name="master">
               <str name="replicateAfter">optimize</str>
               <str name="backupAfter">optimize</str>
               <str name="confFiles">solrconfig_slave.xml:solrconfig.xml,x.xml,y.xml</str>
               <str name="commitReserveDuration">00:00:10</str>
          </lst>    
          <int name="maxNumberOfBackups">2</int>
          <lst name="invariants">
               <str name="maxWriteMBPerSec">16</str>
          </lst>
     </requestHandler>
```

* for master core edit managed-schema

rename it to schema.xml
replace fields with below

```
    <field name="_version_" type="long" indexed="true" stored="true"/>
    <field name="id" type="string" indexed="true" stored="true" required="true"/>
    <field name="title" type="string" indexed="true" stored="true"/>
    <field name="revision" type="int" indexed="true" stored="true"/>
    <field name="user" type="string" indexed="true" stored="true"/>
    <field name="userId" type="int" indexed="true" stored="true"/>
    <field name="text" type="text_en" indexed="true" stored="true"/>
    <field name="timestamp" type="date" indexed="true" stored="true"/>
```
   

* for master core create data-config.xml file

```<dataConfig>
    <dataSource type="FileDataSource" name="file" encoding="UTF-8"/>
    <document>
        <entity name="page"
                processor="XPathEntityProcessor"
                stream="true"
                forEach="/mediawiki/page/"
                url="/home/kevin/solr-5.4.0/server/solr/wiki/input/enwiki-20151201-pages-articles1.xml"
                transformer="RegexTransformer,DateFormatTransformer"
                dataSource="file"
                >
            <field column="id" xpath="/mediawiki/page/id"/>
            <field column="title" xpath="/mediawiki/page/title"/>
            <field column="revision" xpath="/mediawiki/page/revision/id"/>
            <field column="user" xpath="/mediawiki/page/revision/contributor/username"/>
            <field column="userId" xpath="/mediawiki/page/revision/contributor/id"/>
            <field column="text" xpath="/mediawiki/page/revision/text"/>
            <field column="timestamp" xpath="/mediawiki/page/revision/timestamp" dateTimeFormat="yyyy-MM-dd'T'hh:mm:ss'Z'"/>
            <field column="$skipDoc" regex="^#REDIRECT .*" replaceWith="true" sourceColName="text"/>
        </entity>
    </document>
</dataConfig>
```
* for slave core folder add below to solrconfig.xml     
```
<requestHandler name="/replication" class="solr.ReplicationHandler" >
          <lst name="slave">
               <str name="masterUrl">http://localhost:8983/solr/wikimaster/replication</str>
               <str name="pollInterval">00:30:00</str>
          </lst>
     </requestHandler>
```
