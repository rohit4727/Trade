cd E:\etl package
E:
cmd /c start "KafkaProducer" java -jar KafkaProducer.jar
cmd /c start "KafkaConsumer" java -jar KafkaConsumer.jar
cmd /c start "BatchJobService" java -jar BatchJobService.jar
cmd /c start "JobScheduler" java -jar JobScheduler.jar
cmd /c start "SpringBatchBoot" java -jar spring-batch-boot.jar
cmd /c start "UI" java -jar MyApp.war