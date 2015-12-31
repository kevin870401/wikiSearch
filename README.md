# wikiSearch
research project for solr

## Getting solr ready

### start solr
bin/ $ ./solr start

### Create core 'wiki':
solr create -c wiki
./solr create -c wikimaster -d sample_techproducts_configs -p 8983 -rf 2


### download wikipedia dump file:
https://dumps.wikimedia.org/enwiki/latest/

smaller file for German:
this contains all Recombine articles, templates, media/file descriptions, and primary meta-pages.
https://dumps.wikimedia.org/dewiki/20151226/dewiki-20151226-pages-articles.xml.bz2

no event or log including here since they are too large.

### put input file into solr/server/solr/wiki/input

### change solr config
* managed-schema

rename it to schema.xml
replace fields with below

    ```<field name="_version_" type="long" indexed="true" stored="true"/>
    <field name="id" type="string" indexed="true" stored="true" required="true" />
    <field name="title" type="string" indexed="true" stored="true"/>
    <field name="revision" type="int" indexed="true" stored="false"/>
    <field name="user" type="string" indexed="true" stored="false"/>
    <field name="userId" type="int" indexed="true" stored="false" />
    <field name="text" type="text_en" indexed="true" stored="false" /> ```
   
* solrconfig.xml

include dataimporthandler lib

add: ```<lib dir="${solr.install.dir:../../../..}/dist/" regrex="solr-dataimporthandler-.*\.jar"/>``` at line 86

replace ManagedIndexSchemaFactory with classicIndex schemaFactory
    <schemaFactory class="ClassicIndexSchemaFactory"/>
    <!--
    <schemaFactory class="ManagedIndexSchemaFactory">
      <bool name="mutable">true</bool>
      <str name="managedSchemaResourceName">managed-schema</str>
    </schemaFactory>
  -->

 add dih update for http request handler

    <requestHandler name="/dihupdate" class="org.apache.solr.handler.dataimport.DataImportHandler" 
                  startup="lazy">
    <lst name="defaults">
      <str name="config">data-config.xml</str>
    </lst></requestHandler>


* create data-config.xml file

```<dataConfig>
    <dataSource type="FileDataSource" encoding="UTF-8"/>
    <document>
        <entity name="page"
                processor="XPathEntityProcessor"
                stream="true"
                forEach="/mediawiki/page/"
                url="C:\kevin\solr-5.4.0\server\solr\wiki\input\dewiki-20151226-pages-articles.xml"
                transformer="RegexTransformer,DateFormatTransformer"
                >
            <field column="id" xpath="/mediawiki/page/id"/>
            <field column="title" xpath="/mediawiki/page/id"/>
            <field column="revision" xpath="/mediawiki/page/id"/>
            <field column="user" xpath="/mediawiki/page/id"/>
            <field column="userId" xpath="/mediawiki/page/id"/>
            <field column="text" xpath="/mediawiki/page/id"/>
            <field column="timestamp" xpath="/mediawiki/page/revision/timestamp"
                   dateTimeFormat="yyy-MM-dd'T'hh:mm:ss'X'"/>
            <field column="$skipDoc" regrex="^#REDIRECT .*" replaceWith="true" sourceColName="text"/>
        </entity>
    </document>
</dataConfig>

