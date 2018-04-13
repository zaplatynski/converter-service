# Converter Service [![Build Status](https://travis-ci.org/zaplatynski/converter-service.svg?branch=master)](https://travis-ci.org/zaplatynski/converter-service)

This is an example or a bit more complex 'hello world' try-out to package a fully-executable Spring Boot application as Debian package so that it could be installed at any Debian derived Linux distribution such as Ubuntu.
It will be installed as a systemd service and can be controlled using `service converter start` or `service converter stop`. Status information are available with `service converter status`.

It uses the Jackson library as defined by Spring Boot to do the conversion between JSON and YAML.

The following endpoints are available:
* http://localhost:8080/actuator reveals some runtime information such as health check, thread dump or some basic metrics.
* http://localhost:8080/json2yaml accepts POST and converts the body from a JSON to YAML
* http://localhost:8080/yaml2json accepts POST and converts the body from a YAML to JSON


## Build
To build the project run Maven:

```bash
mvn package
```

## Run stand-alone
After build change into the `target` directory and just execute the `jar` file:

```bash
cd target
./convert-<VERSION>.jar
```
Requires an installed JVM such as OpenJDK 8 with the environement variable JAVA_HOME set accordingly.

## Installation of deb

To install the Debain package:
```bash
sudo dpkg -i converter-<VERSION>.deb
```

The deb-package requires an installed Java distribution such as OpenJDK 8 which will be resolved by the package manager.

The following directories are used:
* `/var/log/converter` contains the log files
* `/var/run/converter` conatins runtime data such as the pid file and the Apache Tomcat's work directory
* `/etc/converter` contains the configuration
* `/usr/lib/converter` contains the fully-executable Spring Boot fat `jar` file

A technical user `converter` will be created to run the systemd service.

## Deinstallation of deb
```bash
 sudo apt remove converter-<VERSION>
```

## See also
* Spring Boot tutorial: https://spring.io/guides/gs/spring-boot/
* JDeb: https://github.com/tcurdt/jdeb