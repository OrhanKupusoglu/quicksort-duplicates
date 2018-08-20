#!/bin/bash

MAIN_CLASS=
MAVEN_PLUGIN=kupusoglu.orhan:bazelize-maven-plugin

mvn ${MAVEN_PLUGIN}:module
mvn ${MAVEN_PLUGIN}:meta
mvn ${MAVEN_PLUGIN}:build
mvn ${MAVEN_PLUGIN}:workspace
mvn ${MAVEN_PLUGIN}:clean
mvn ${MAVEN_PLUGIN}:test

if [[ ! -z $MAIN_CLASS ]]
then
    mvn ${MAVEN_PLUGIN}:binary -DmainClass=${MAIN_CLASS}
fi
