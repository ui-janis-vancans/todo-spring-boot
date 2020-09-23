#!/bin/bash
PROFILE="$1"
PORT="${2:-8080}"

./gradlew clean build
docker build -t "$PROFILE:Dockerfile" .
docker run -p "${PORT}:8080" -e "SPRING_PROFILES_ACTIVE=$PROFILE" $PROFILE:Dockerfile
