#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  KUBE_DEPLOY_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  KUBE_DEPLOY_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
KUBE_AUTOMATION_PATH=$(dirname "${KUBE_DEPLOY_SCRIPT_PATH}")
source "${KUBE_AUTOMATION_PATH}/automation.sh"
PROJECT_SOURCE_KUBE_PATH=${PROJECT_SOURCE_KUBE_PATH}




# FUNCTIONS, ALIASES AND ACTUAL WORK HERE
# ================================================================================================================
info "kube-deploy.sh" "Deploying HELM Chart with Application and Database"

helm install -f "${PROJECT_SOURCE_KUBE_PATH}/local-values.yaml" shortest-path-application "${PROJECT_SOURCE_KUBE_PATH}"

info "kube-deploy.sh" "Hopefully Deployment is done!"