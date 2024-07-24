#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  SPA_REMOVE_APP_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  SPA_REMOVE_APP_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
PARENT_SPA_RUN_APP_SCRIPT_PATH=$(dirname "${SPA_REMOVE_APP_SCRIPT_PATH}")
source "${PARENT_SPA_RUN_APP_SCRIPT_PATH}/automation.sh"



# FUNCTIONS AND ACTUAL WORK HERE
# ================================================================================================================

info "remove-app.sh" "Removing container : ${SPA_DOCKER_CONTAINER_NAME_APPLICATION}"
info "remove-app.sh" "Using compose file : ${SPA_DOCKER_COMPOSE_APPLICATION_FILE_PATH}"

removeAppContainer

info "remove-app.sh" "${SPA_DOCKER_CONTAINER_NAME_APPLICATION} : container removed"
