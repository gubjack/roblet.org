#!/bin/sh

jarClient="../../../org.roblet.client.jar"
jarClientOld="../../../genRob.genControl.client.jar"
jarRoblet="../../../org.roblet.jar"
jarUnit="../unit/myunit.jar"
classpath="-classpath $jarClient:$jarClientOld:$jarRoblet:$jarUnit:."
encoding="-encoding UTF-8"

javac  $classpath  $encoding  *.java
