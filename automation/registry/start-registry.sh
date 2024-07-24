#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  SPA_START_REGISTRY_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  SPA_START_REGISTRY_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
PARENT_SPA_RUN_REGISTRY_SCRIPT_PATH=$(dirname "${SPA_START_REGISTRY_SCRIPT_PATH}")
source "${PARENT_SPA_RUN_REGISTRY_SCRIPT_PATH}/automation.sh"



# FUNCTIONS AND ACTUAL WORK HERE
# ================================================================================================================
info "start-registry.sh" "Running starting up Docker Image Registry via Docker Compose"
info "start-registry.sh" "Using file : ${SPA_DOCKER_COMPOSE_REGISTRY_FILE_PATH}"

createAndRunRegistryContainer

info "start-registry.sh" "All done, check your Docker."
