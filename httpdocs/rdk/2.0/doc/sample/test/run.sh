#!/bin/sh

# JDK 6

jarRDK="../../../org.roblet.jar"
jarUnit="../unit/myunit.jar"
classpath="-classpath $jarRDK:$jarUnit:."

java  $classpath  HelloUnitTest