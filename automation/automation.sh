#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  AUTOMATION_HOME_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  AUTOMATION_HOME_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
SHORTEST_PATH_PROJECT_ROOT_PATH=$(dirname "${AUTOMATION_HOME_SCRIPT_PATH}")
PROJECT_SOURCE_DOCKER_PATH="${SHORTEST_PATH_PROJECT_ROOT_PATH}/src/docker"
PROJECT_SOURCE_KUBE_PATH="${SHORTEST_PATH_PROJECT_ROOT_PATH}/src/kube"


# LOGGING AND PRINTING UTIL
# ================================================================================================================
# These are some String Tags that we will use for our custom bash LOGGER!! Nothing major, just a pretty printer
ERROR_TAG="[ERROR]"
INFO_TAG="[INFO]"
LINE_SPACE_TAG="[LINE]"



# FUNCTIONS SECTION
# ================================================================================================================

# This is a bash function that gets the current in a certain format.
# The main use for this will for our logger. That's all.
function getCurrentDateTime() {
    echo $(date +"%Y-%m-%d %T")
}

# This mimics a INFO logger for us.
# It takes 2 parameters
#     scriptName : This is the name of the script that's currently logging.
#     messageToLog : The actual message you are trying to log
function info() {
    scriptName=$1
    messageToLog=$2
    echo "$INFO_TAG $(getCurrentDateTime) [$scriptName] : $messageToLog"
}

# This mimics a ERROR logger for us.
# It takes 2 parameters
#     scriptName : This is the name of the script that's currently logging.
#     messageToLog : The actual message you are trying to log
function error() {
    scriptName=$1
    messageToLog=$2
    echo "$ERROR_TAG $(getCurrentDateTime) [$scriptName] : $messageToLog"
}

# This mimics a BLANK_LINE_SPACE logger for us.
# It takes 2 parameters
#     scriptName : This is the name of the script that's currently logging.
function whitespace() {
    scriptName=$1
    echo "$LINE_SPACE_TAG $(getCurrentDateTime) [$scriptName]"
}



# FUNCTIONS, ALIASES AND ACTUAL WORK HERE
# ================================================================================================================
function dockerStartByName() {
  local _containerName=$1
  docker start $_containerName
}

function dockerStopByName() {
  local _containerName=$1
  docker stop $_containerName
}



# DOCKER POSTGRES SECTION
# ================================================================================================================
# Change the username and password to what you want.
SPA_DOCKER_CONTAINER_NAME_POSTGRES="shortest-postgres-db"
SPA_DOCKER_COMPOSE_POSTGRES_FILE_PATH="${PROJECT_SOURCE_DOCKER_PATH}/compose-postgres.yml"
export DATABASE_USERNAME=${POSTGRESS_DB_USERNAME}
export DATABASE_PASSWORD=${POSTGRESS_DB_PASSWORD}
export DATABASE_PORT="5432"

function createAndRunPostgresDatabaseContainer() {
  docker-compose -f ${SPA_DOCKER_COMPOSE_POSTGRES_FILE_PATH} up -d
}

function removePostgresDatabaseContainer() {
  docker-compose -f ${SPA_DOCKER_COMPOSE_POSTGRES_FILE_PATH} down
}

function stopPostgresContainer() {
  dockerStopByName ${SPA_DOCKER_CONTAINER_NAME_POSTGRES}
}



# DOCKER REGISTRY SECTION
# ================================================================================================================
# Change the username and password to what you want.
SPA_DOCKER_CONTAINER_NAME_REGISTRY="shortest-registry"
SPA_DOCKER_COMPOSE_REGISTRY_FILE_PATH="${PROJECT_SOURCE_DOCKER_PATH}/compose-registry.yml"

function createAndRunRegistryContainer() {
  docker-compose -f ${SPA_DOCKER_COMPOSE_REGISTRY_FILE_PATH} up -d
}

function removeRegistryContainer() {
  docker-compose -f ${SPA_DOCKER_COMPOSE_REGISTRY_FILE_PATH} down
}

function stopRegistryContainer() {
  dockerStopByName ${SPA_DOCKER_CONTAINER_NAME_REGISTRY}
}



# DOCKER APPLICATION SECTION
# ================================================================================================================
# Change the username and password to what you want.
SPA_DOCKER_CONTAINER_NAME_APPLICATION="shortest-path-app"
SPA_DOCKER_COMPOSE_APPLICATION_FILE_PATH="${PROJECT_SOURCE_DOCKER_PATH}/compose-application.yml"

function createAndRunAppContainer() {
  docker-compose -f ${SPA_DOCKER_COMPOSE_APPLICATION_FILE_PATH} up -d
}

function removeAppContainer() {
  docker-compose -f ${SPA_DOCKER_COMPOSE_APPLICATION_FILE_PATH} down
}

function stopAppContainer() {
  dockerStopByName ${SPA_DOCKER_CONTAINER_NAME_APPLICATION}
}

