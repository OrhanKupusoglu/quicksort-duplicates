#!/bin/bash

# customize the following declarations
# ------------------------------------------------------------------------------
export USE_BAZEL_VERSION=0.14.1
BAZEL_BIN=/home/unknown/dev/bazelisk/bin/bazelisk-linux-amd64
MAIN_CLASS=
# ------------------------------------------------------------------------------

MAVEN_PLUGIN=kupusoglu.orhan:bazelize-maven-plugin
SEPARATOR=$(printf "%0.s=" {1..80})
OPTION=$1

case $OPTION in
    -h | --help )
        printf "usage:\n"
        printf "\t$0 <option>\n"
        printf "options:\n"
        printf "\tmigrate to bazel: -m | --migrate | -g | --generate\n"
        printf "\tclean bazel:      -c | --clean\n"
        printf "\tbuild with bazel: -b | --build\n"
        printf "\ttest with bazel:  -t | --test\n"
        printf "\trun with bazel:   -r | --run\n"
        printf "requires Bazelisk for Bazel v${USE_BAZEL_VERSION}:\n"
        printf "\t$BAZEL_BIN\n"
        exit 0
        ;;

    -m | --migrate | -g | --generate )
        MSG="migrate"
        ;;

    -c | --clean )
        MSG="clean"
        ;;

    -b | --build )
        MSG="build"
        ;;

    -t | --test )
        MSG="test"
        ;;

    -r | --run )
        MSG="run"
        ;;

    * )
        echo "ERROR - unknown option: $OPTION"
        exit 1
esac

MSG="$MSG | bazel version: $USE_BAZEL_VERSION"

printf "$SEPARATOR\n"
printf "== ${MSG}\n"
printf "$SEPARATOR\n\n"

if [ ! -f $BAZEL_BIN ]
then
    echo "ERROR - missing Bazelisk"
    echo "path: $BAZEL_BIN"
    exit 2
fi

case $OPTION in
    -m | --migrate | -g | --generate )
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
        ;;

    -c | --clean )
        $BAZEL_BIN clean --expunge
        rm -f bazelize.out bazelize.out.html
        ;;

    -b | --build )
        $BAZEL_BIN build ... --strict_java_deps=off --profile=bazelize.out
        $BAZEL_BIN analyze-profile --html bazelize.out
        ;;

    -t | --test )
        $BAZEL_BIN test ... --strict_java_deps=off --test_output all
        ;;

    -r | --run )
        if [[ -z $MAIN_CLASS ]]
        then
            echo "ERROR: no main class is given"
        else
            MAIN_BAZEL=${MAIN_CLASS//./_}
            $BAZEL_BIN run $MAIN_BAZEL
        fi
        ;;
esac
