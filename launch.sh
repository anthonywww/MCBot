#!/bin/bash

# Initial memory heap
INIT_MEM="1024m"

# Maximum memory heap
MAX_MEM="1024m"

# Garbage collection threads
GC_THREADS="8"

# Jar file location
JAR="target/MCBot.jar"

# Extra arguments
ARGS=""

#### End of Configuration ####

# Set the directory to this script's current directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

if [ ! -f "${DIR}/${JAR}" ]; then
	echo -e "$(tput sgr0)$(tput bold)$(tput setaf 1)The jar file ${JAR} does not exist!$(tput sgr0)"
	exit 1
fi

echo -e "$(tput sgr0)$(tput bold)$(tput setaf 2)Launching ...$(tput sgr0)"

java -server -XX:+UseConcMarkSweepGC -XX:+UseFastAccessorMethods -XX:ParallelGCThreads=${GC_THREADS} -XX:+UseAdaptiveGCBoundary -XX:MaxGCPauseMillis=50 -Xms${INIT_MEM} -Xmx${MAX_MEM} -jar ${DIR}/${JAR} ${ARGS} $@

echo -e "$(tput sgr0)"
