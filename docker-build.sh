#!/usr/bin/env bash

DOCKER_IMAGE_PREFIX="ivanprokic"
APP_VERSION="1.0.0"

TICKET_PRODUCER_APP_NAME="ticket-producer"
# TICKET_CONSUMER_APP_NAME="ticket-consumer"
# TICKET_PRODUCER_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${TICKET_PRODUCER_APP_NAME}:${APP_VERSION}"
# TICKET_CONSUMER_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${TICKET_CONSUMER_APP_NAME}:${APP_VERSION}"

SPORT_TICKET_CONSUMER_APP_NAME="sport-ticket-consumer"
MOVIE_TICKET_CONSUMER_APP_NAME="movie-ticket-consumer"

TICKET_PRODUCER_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${TICKET_PRODUCER_APP_NAME}:${APP_VERSION}"
TICKET_SPORT_CONSUMER_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${SPORT_TICKET_CONSUMER_APP_NAME}:${APP_VERSION}"
TICKET_MOVIEW_CONSUMER_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${MOVIE_TICKET_CONSUMER_APP_NAME}:${APP_VERSION}"

SKIP_TESTS="true"

# OCI images with packet do not have curl to enable healtchecks in compose
./mvnw clean spring-boot:build-image --projects "$TICKET_PRODUCER_APP_NAME" -DskipTests="$SKIP_TESTS" -Dspring-boot.build-image.imageName="$TICKET_PRODUCER_DOCKER_IMAGE_NAME"
./mvnw clean spring-boot:build-image --projects "$SPORT_TICKET_CONSUMER_APP_NAME" -DskipTests="$SKIP_TESTS" -Dspring-boot.build-image.imageName="$TICKET_SPORT_CONSUMER_DOCKER_IMAGE_NAME"
./mvnw clean spring-boot:build-image --projects "$MOVIE_TICKET_CONSUMER_APP_NAME" -DskipTests="$SKIP_TESTS" -Dspring-boot.build-image.imageName="$TICKET_MOVIEW_CONSUMER_DOCKER_IMAGE_NAME"

# docker build -t --platform=linux/amd64 $TICKET_PRODUCER_DOCKER_IMAGE_NAME ./$TICKET_PRODUCER_APP_NAME
# docker build -t --platform=linux/amd64 $TICKET_CONSUMER_DOCKER_IMAGE_NAME ./$TICKET_CONSUMER_APP_NAME