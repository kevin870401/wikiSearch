# wikiSearch
research project for solr

## Getting solr ready

### start solr
bin/ $ ./solr start

### Create core 'wiki':
solr create -c wiki

### download wikipedia dump file:
this contains all Recombine articles, templates, media/file descriptions, and primary meta-pages.
https://dumps.wikimedia.org/dewiki/20151226/dewiki-20151226-pages-articles.xml.bz2

no event or log including here since they are too large.

### put input file into solr/server/solr/wiki/input

### change solr config
#### managed-schema
rename it to schema.xml

#### solrconfig.xml
##### include dataimporthandler lib
add: <lib dir="${solr.install.dir:../../../..}/dist/" regrex="solr-dataimporthandler-.*\.jar"/> at line 86

##### replace ManagedIndexSchemaFactory with classicIndex schemaFactory
  <schemaFactory class="ClassicIndexSchemaFactory"/>
    <!--
    <schemaFactory class="ManagedIndexSchemaFactory">
      <bool name="mutable">true</bool>
      <str name="managedSchemaResourceName">managed-schema</str>
    </schemaFactory>
  -->

##### add dih update for http request handler
  <requestHandler name="/dihupdate" class="org.apache.solr.handler.dataimport.DataImportHandler" 
                  startup="lazy">
    <lst name="defaults">
      <str name="config">data-config.xml</str>
    </lst>
  </requestHandler>

