#!/bin/bash
export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=5555,server=y,suspend=n"
mvn jetty:run
