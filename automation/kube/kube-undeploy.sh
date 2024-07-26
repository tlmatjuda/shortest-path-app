#!/bin/bash

#!/bin/bash
# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2024-07-23
#
# ================================================================================================================


if [ -z "${BASH_VERSINFO+x}" ]; then
  KUBE_UNDEPLOY_SCRIPT_PATH=${0:a:h}
  alias reload="exec zsh"
else
  KUBE_UNDEPLOY_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
  alias reload="bash -l"
fi



# CONSTANTS, CONFIGS & ENVIRONMENT VARIABLES
# ================================================================================================================
KUBE_AUTOMATION_PATH=$(dirname "${KUBE_UNDEPLOY_SCRIPT_PATH}")
source "${KUBE_AUTOMATION_PATH}/automation.sh"




# FUNCTIONS, ALIASES AND ACTUAL WORK HERE
# ================================================================================================================
info "kube-deploy.sh" "Undeploying HELM Chart with Application and Database"

helm delete shortest-path-application

info "kube-deploy.sh" "Hopefully Undeployed!"