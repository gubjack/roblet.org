#!/bin/sh

jarServer=../../../../genRob.genControl.jar
jarModule=mymodule.jar
jarUnit=../unit/myunit.jar
classpath=$jarServer:$jarModule:$jarUnit

security=-DgenRob.genControl.security=true
port=-DgenRob.genControl.port=2001
log=-DgenRob.genControl.log=terminal

module=mymodule.ModuleImpl
main=genRob.genControl.Main

java  $security  $port  $log  -classpath $classpath  $main  $module
