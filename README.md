# solrplugin
Testing

# Build jar
mvn clean assembly:assembly && jar -tf target/solrplugin-1.0-SNAPSHOT-jar-with-dependencies.jar | grep sann
