#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  SPA_RESTART_DATABASE_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  SPA_RESTART_DATABASE_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
PARENT_SPA_RUN_DATABASE_SCRIPT_PATH=$(dirname "${SPA_RESTART_DATABASE_SCRIPT_PATH}")
source "${PARENT_SPA_RUN_DATABASE_SCRIPT_PATH}/automation.sh"



# FUNCTIONS AND ACTUAL WORK HERE
# ================================================================================================================
info "restart-database.sh" "Restarting database : ${SPA_DOCKER_CONTAINER_NAME}"

${SPA_RESTART_DATABASE_SCRIPT_PATH}/stop-database.sh && \
${SPA_RESTART_DATABASE_SCRIPT_PATH}/start-database.sh

info "restart-database.sh" "${SPA_DOCKER_CONTAINER_NAME} : container restarted!"
