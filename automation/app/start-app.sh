#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  SPA_START_APP_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  SPA_START_APP_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
PARENT_SPA_RUN_APP_SCRIPT_PATH=$(dirname "${SPA_START_APP_SCRIPT_PATH}")
source "${PARENT_SPA_RUN_APP_SCRIPT_PATH}/automation.sh"



# FUNCTIONS AND ACTUAL WORK HERE
# ================================================================================================================
info "start-app.sh" "Starting up the App via Docker Compose"
info "start-app.sh" "Using file : ${SPA_DOCKER_COMPOSE_APPLICATION_FILE_PATH}"

createAndRunAppContainer

info "start-app.sh" "All done, check your Docker."
