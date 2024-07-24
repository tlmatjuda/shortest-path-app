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
source "${BUILD_APP_IMAGE_SCRIPT_PATH}/pretty-printer.sh"



# FUNCTIONS, ALIASES AND ACTUAL WORK HERE
# ================================================================================================================
info "build-app-image-kube.sh" "Containerizing Spring Boot App"
info "build-app-image-kube.sh" "Using Pom file in : ${SHORTEST_PATH_PROJECT_ROOT_PATH}/pom.xml"

mvn -f ${SHORTEST_PATH_PROJECT_ROOT_PATH}/pom.xml spring-boot:build-image -DskipTests -Pkube

info "build-app-image-kube.sh" "Build process done"