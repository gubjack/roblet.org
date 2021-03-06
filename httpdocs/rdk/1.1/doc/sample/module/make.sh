#!/bin/sh

jarRoblet="../../../org.roblet.jar"
jarModules="../../../genRob.genControl.modules.jar"
jarUnit="../unit/myunit.jar"
classpath="-classpath $jarModules:$jarRoblet:$jarUnit:."
encoding="-encoding UTF-8"

javac  $classpath  $encoding  mymodule/*.java
if [ $? -ne 0 ]; then exit 1; fi

jar  -cf mymodule.jar  mymodule/*.class
