#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  SPA_STOP_DATABASE_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  SPA_STOP_DATABASE_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
PARENT_SPA_RUN_DATABASE_SCRIPT_PATH=$(dirname "${SPA_STOP_DATABASE_SCRIPT_PATH}")
source "${PARENT_SPA_RUN_DATABASE_SCRIPT_PATH}/automation.sh"

COMPOSE_POSTGRES_FILE_PATH="${PROJECT_SOURCE_DOCKER_PATH}/compose-postgres.yml"



# FUNCTIONS AND ACTUAL WORK HERE
# ================================================================================================================
info "stop-database.sh" "Stopping container : ${SPA_DOCKER_CONTAINER_NAME_POSTGRES}"

stopPostgresContainer

info "stop-database.sh" "${SPA_DOCKER_CONTAINER_NAME} : container stopped"
