#!/usr/bin/env bash

set -e

gradle_file="build.gradle"

currentVersionCode=$(grep 'versionCode' $gradle_file | sed -r 's/(.*) (.*)$/\2/')
nextReleaseVersionCode=$((currentVersionCode+1))

if [ -z "$currentVersionCode" ]
then
    echo "Code ($currentVersionCode) is empty! Don't bump anything."
else
    CURRENT_VERSION_NAME="0.${currentVersionCode}"
    NEXT_RELEASE_VERSION_NAME="0.${nextReleaseVersionCode}"
    echo "Version code is $currentVersionCode and name $CURRENT_VERSION_NAME"

    git tag "RELEASE_INTERNAL_${CURRENT_VERSION_NAME}"

    echo "Replace versionCode for the next release"
    sed --in-place -r "s/versionCode (.*)/versionCode ${nextReleaseVersionCode}/" $gradle_file
    echo "Replace versionName for the next release"
    sed --in-place -r "s/versionName \"(.*)\"/versionName \"${NEXT_RELEASE_VERSION_NAME}\"/" $gradle_file

    # commit bump & push tag
    git config --global user.name warisdgk
    git config --global user.email warisdgk@gmail.com
    git fetch origin master
    git add .
    git commit -m "[skip ci] Version bump ${nextReleaseVersionCode}"
    git push origin HEAD
    git push --tags
fi
