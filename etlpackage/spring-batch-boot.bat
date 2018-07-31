cd E:\Trade\SpringBatch_Boot
mvn clean package -DskipTests
cd E:\etlpackage
copy E:\Trade\SpringBatch_Boot\target\spring-batch-boot-0.1.0.jar *
java -jar spring-batch-boot-0.1.0.jar