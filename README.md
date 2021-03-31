# FUEL-LOG

## Requirements

* JDK 8
* Gradle 6+ (The recommended way to execute the build is using the Gradle Wrapper: ./gradlew ...)
* Docker Compose (For local environment testing)

### Plugins

- Lombok
    - https://plugins.jetbrains.com/plugin/6317-lombok
    - Check the following box **Enable annotation processing** on *Build, Execution, Deployment > Compiler > Annotation
      Processors*

- Save Actions
    - https://plugins.jetbrains.com/plugin/7642-save-actions
    - On .idea folder there is a config file, just restart the IDE to plugin get the configuration

### Postman

https://www.getpostman.com/collections/ce5d8f8869463639f83d

### Building Project

    ./gradlew clean build

### Unit Test

    ./gradlew test

### Integration Test

    ./gradlew integrationTest

### docker-compose with Postgresql and Mongodb database

    docker-compose -f src/main/resources/docker/compose/docker-compose.yml
