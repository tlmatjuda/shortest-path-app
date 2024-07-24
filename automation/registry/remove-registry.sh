#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  SPA_REMOVE_REGISTRY_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  SPA_REMOVE_REGISTRY_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
PARENT_SPA_RUN_DATABASE_SCRIPT_PATH=$(dirname "${SPA_REMOVE_REGISTRY_SCRIPT_PATH}")
source "${PARENT_SPA_RUN_DATABASE_SCRIPT_PATH}/automation.sh"



# FUNCTIONS AND ACTUAL WORK HERE
# ================================================================================================================

info "remove-registry.sh" "Removing container : ${SPA_DOCKER_CONTAINER_NAME_REGISTRY}"
info "remove-registry.sh" "Using compose file : ${SPA_DOCKER_COMPOSE_REGISTRY_FILE_PATH}"

removeRegistryContainer

info "remove-registry.sh" "${SPA_DOCKER_CONTAINER_NAME_REGISTRY} : container removed"
