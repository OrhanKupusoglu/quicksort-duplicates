#!/bin/bash

MAIN_CLASS=
MAVEN_PLUGIN=kupusoglu.orhan:bazelize-maven-plugin
MIGRATE=$1

bazel clean --expunge
rm -f bazelize.out bazelize.out.html

if [[ $MIGRATE == "-g" ]]
then
    mvn ${MAVEN_PLUGIN}:clean -Dexpunge
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

    msg="migration completed"
else
    msg="bazel is ready"
fi

printf "\n"
printf "%0.s=" {1..80}
printf "\n"
printf "== ${msg}\n"
printf "%0.s=" {1..80}
printf "\n\n"

bazel build ... --strict_java_deps=off --profile=bazelize.out

bazel analyze-profile --html bazelize.out

bazel test ... --strict_java_deps=off --test_output all

if [[ ! -z $MAIN_CLASS ]]
then
    MAIN_BAZEL=${MAIN_CLASS//./_}
    bazel run $MAIN_BAZEL
fi
