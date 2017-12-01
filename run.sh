#!/bin/bash
adb shell am force-stop com.malin.test && \
gradle installDebug -x lint --build-cache --daemon --parallel --offline --configure-on-demand --continue && \
adb shell am start com.malin.test/.MainActivity

