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
LOCAL_REGISRY_REPO="localhost:5001"



# FUNCTIONS, ALIASES AND ACTUAL WORK HERE
# ================================================================================================================
info "build-app-image-kube.sh" "Pushing Applicaiton Image to Local Registry at : ${LOCAL_REGISRY_REPO}"

docker push ${LOCAL_REGISRY_REPO}/shortest-path-app:latest

info "build-app-image-kube.sh" "Done"