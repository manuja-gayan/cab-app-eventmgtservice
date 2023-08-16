# eventmgtservice
Microservice for handling all notify events of event-driven system using Firebase Cloud Messaging(FCM) tokens

## Features
* Event publishing to relevant users based on incoming data through Kafka topic.
* Use Firebase Cloud Messaging (FCM) tokens to submit notifier data as background push messages.
* Highly scalable.

## Supported versions

system will support the following versions.  
Other versions might also work, but we have not tested it.

* Java 8, 11
* Spring Boot 2.7.5

## Building and running

To build and test, you can run:

```sh
$ cd cab-app-eventmgtservice
# build the application
$ mvn clean install
# run the application
$ java -jar target/eventmgtservice-0.0.1.jar
```

## Contributing

Bug reports and pull requests are welcome :)