#!/bin/bash

# Originally written by Ralf Kistner <ralf@embarkmobile.com>, but placed in the public domain
# ORIGINAL: https://github.com/mmcc007/test_emulators/blob/master/scripts/android-wait-for-emulator.sh

set +e

bootanim=""
failcounter=0
# emulator must start in 10 min
# ref https://travis-ci.com/SupinePandora43/Russian/builds/116552693
timeout_in_sec=600

until [[ "$bootanim" =~ "stopped" ]]; do
  bootanim=`adb -e shell getprop init.svc.bootanim 2>&1 &`
#echo bootanim=\`$bootanim\`
  if [[ "$bootanim" =~ "device not found" || "$bootanim" =~ "device offline"
    || "$bootanim" =~ "running" || "$bootanim" =~  "error: no emulators found" ]]; then
    let "failcounter += 1"
    echo "Waiting for emulator to start"
    if [[ $failcounter -gt timeout_in_sec ]]; then
      echo "Timeout ($timeout_in_sec seconds) reached; failed to start emulator"
      exit 1
    fi
  fi
  sleep 1
done

echo "Emulator is ready"
