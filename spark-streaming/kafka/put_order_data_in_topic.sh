#!/bin/bash

export PATH=$PATH:/usr/hdp/current/kafka-broker/bin
FILES=$1/*.csv
for f in $FILES
do
    echo "\npushing $f file\n"
    cat $f | kafka-console-producer.sh --broker-list $2  --topic $3
    sleep 60
done
