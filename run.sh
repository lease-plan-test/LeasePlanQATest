#!/usr/bin/env bash

run_in_docker() {
  if [[ "$(docker images -q maven:3.8.1-openjdk-16-slim 2> /dev/null)" == "" ]]; then
    docker pull maven:3.8.1-openjdk-16-slim
  fi
  docker run --rm -it -v "$PWD":/usr/src -w /usr/src maven:3.8.1-openjdk-16-slim mvn clean test;
}

# Check if this script is being executed from base project dir
if [[ "$(pwd)" != *"/LeasePlanQATest" ]]; then
    echo "$(pwd)"
    echo "Please execute this file from inside of base project directory only..."
    exit 1
fi

echo "How do you prefer to run these tests:"
echo "1. Locally"
echo "3. Docker"
echo "Your selection: "; read n

case $n in
  1) mvn clean test; ;;
  2) run_in_docker;;
  *) echo "invalid option";;
esac


