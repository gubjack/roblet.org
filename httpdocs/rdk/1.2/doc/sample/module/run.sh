#!/bin/sh

# JDK 6

jarRDK=../../../org.roblet.jar
jarModule=mymodule.jar
jarUnit=../unit/myunit.jar
classpath="-classpath $jarRDK:$jarModule:$jarUnit"

log=-DgenRob.genControl.log=terminal

module=mymodule.ModuleImpl

java  $log  $classpath  org.roblet.Main server  $module
