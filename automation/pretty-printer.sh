#!/bin/bash

# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2022-07-20
#
# ================================================================================================================


# CONFIGS & GLOBAL VARIABLES SECTION
# ================================================================================================================


# These are some String Tags that we will use for our custom bash LOGGER!! Nothing major, just a pretty printer
ERROR_TAG="[ERROR]"
INFO_TAG="[INFO]"
LINE_SPACE_TAG="[LINE]"



# FUNCTIONS SECTION
# ================================================================================================================

# This is a bash function that gets the current in a certain format.
# The main use for this will for our logger. That's all.
function getCurrentDateTime() {
    echo $(date +"%Y-%m-%d %T")
}

# This mimics a INFO logger for us.
# It takes 2 parameters
#     scriptName : This is the name of the script that's currently logging.
#     messageToLog : The actual message you are trying to log
function info() {
    scriptName=$1
    messageToLog=$2
    echo "$INFO_TAG $(getCurrentDateTime) [$scriptName] : $messageToLog"
}

# This mimics a ERROR logger for us.
# It takes 2 parameters
#     scriptName : This is the name of the script that's currently logging.
#     messageToLog : The actual message you are trying to log
function error() {
    scriptName=$1
    messageToLog=$2
    echo "$ERROR_TAG $(getCurrentDateTime) [$scriptName] : $messageToLog"
}

# This mimics a BLANK_LINE_SPACE logger for us.
# It takes 2 parameters
#     scriptName : This is the name of the script that's currently logging.
function whitespace() {
    scriptName=$1
    echo "$LINE_SPACE_TAG $(getCurrentDateTime) [$scriptName]"
}
