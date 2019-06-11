#!/bin/bash

########################
# Script to generate requests to the local service.
# 
# First, follow the instructions to run with docker, then run this.
# Hit <CTRL+C> to break the script loop and stop.
########################

function random_id {
  FLOOR=10;
  CEILING=14
  RANGE=$(($CEILING-$FLOOR));
  RESULT=$RANDOM;
  let "RESULT %= $RANGE";
  RESULT=$(($RESULT+$FLOOR));

  return $RESULT;
}

function random_interval {
  FLOOR=0;
  CEILING=2000;
  RANGE=$(($CEILING-$FLOOR));
  RESULT=$RANDOM;
  let "RESULT %= $RANGE";
  RESULT=$((1000-$RESULT+$FLOOR));

  return $RESULT;
}


printf "\n\n ****** Press <CTRL+C> to exit ****** \n\n"
sleep 3

BASE_URL="http:/localhost:8080/products"

while :
do
  INTERVAL="0.$((RANDOM % 1000))"
  sleep "$INTERVAL"

  random_id
  ID=$?
  printf "\n\n---\n$BASE_URL/$ID\n\n"
  curl -i "$BASE_URL/$ID"
done
