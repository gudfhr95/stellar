#!/bin/sh
./gradlew clean jibDockerBuild
docker-compose up -d
