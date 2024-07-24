#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  BUILD_APP_IMAGE_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  BUILD_APP_IMAGE_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
SHORTEST_PATH_PROJECT_ROOT_PATH=$(dirname "${BUILD_APP_IMAGE_SCRIPT_PATH}")
PROJECT_SOURCE_DOCKER_PATH="${SHORTEST_PATH_PROJECT_ROOT_PATH}/src/docker"
PROJECT_SOURCE_KUBE_PATH="${SHORTEST_PATH_PROJECT_ROOT_PATH}/src/kube"
source "${BUILD_APP_IMAGE_SCRIPT_PATH}/pretty-printer.sh"



# FUNCTIONS, ALIASES AND ACTUAL WORK HERE
# ================================================================================================================
info "build-app-image-kube.sh" "Pushing Applicaiton Image to Local Registry at : localhost:5002"

docker push localhost:5002/shortest-path-app:latest

info "build-app-image-kube.sh" "Done"