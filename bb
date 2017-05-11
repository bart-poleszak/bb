#!/bin/bash

if [ -z $1 ]
then
	echo "bb - open a commit on Bitbucket, Bitbucket Server or GitHub"
	echo "Usage: $0 <commit-hash>"
	exit
fi

GIT_REMOTE_V=(`git remote -v | head -n 1`)

REMOTE_NAME=${GIT_REMOTE_V[0]}

TMP=${GIT_REMOTE_V[1]%.git}
TMP=${TMP#*@}
TMP=(${TMP//"/"/ })

if [ ${#TMP[*]} = 3 ] 	# that should be the case for HTTP addresses
then
	ADDRESS_BASE=${TMP[0]}
	USERNAME=${TMP[1]}
	REPO_NAME=${TMP[2]}
else					# SSH address
	ADDRESS_BASE=${TMP[0]%:*}
	USERNAME=${TMP[0]#*:}
	REPO_NAME=${TMP[1]}
fi

if [ $ADDRESS_BASE = "github.com" ]
then
	COMMIT_URL="commit/"
else
	COMMIT_URL="commits/"
fi

SLASH=\/
ADDRESS_TO_RUN="http://"$ADDRESS_BASE$SLASH$USERNAME$SLASH$REPO_NAME$SLASH$COMMIT_URL$1

if [ $OSTYPE = "cygwin" ] || [ $OSTYPE = "msys" ] #We're on Windows
then
	start $ADDRESS_TO_RUN
else
	xdg-open $ADDRESS_TO_RUN
fi