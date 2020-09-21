#!/bin/bash
PROFILE="$1"

./gradlew clean build
docker build -t "$PROFILE:Dockerfile" .
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=$PROFILE" $PROFILE:Dockerfile
