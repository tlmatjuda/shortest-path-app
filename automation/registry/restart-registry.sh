#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  SPA_RESTART_REGISTRY_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  SPA_RESTART_REGISTRY_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
PARENT_SPA_RUN_REGISTRY_SCRIPT_PATH=$(dirname "${SPA_RESTART_REGISTRY_SCRIPT_PATH}")
source "${PARENT_SPA_RUN_REGISTRY_SCRIPT_PATH}/automation.sh"



# FUNCTIONS AND ACTUAL WORK HERE
# ================================================================================================================
info "restart-registry.sh" "Restarting Docker Image Registry : ${SPA_DOCKER_CONTAINER_NAME_REGISTRY}"

${SPA_RESTART_REGISTRY_SCRIPT_PATH}/stop-registry.sh && \
${SPA_RESTART_REGISTRY_SCRIPT_PATH}/start-registry.sh

info "restart-registry.sh" "${SPA_DOCKER_CONTAINER_NAME_REGISTRY} : container restarted!"
