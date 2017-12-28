#!/bin/sh

jarRoblet="../../../org.roblet.jar"
classpath="-classpath $jarRoblet:."
encoding="-encoding UTF-8"

javac  $classpath  $encoding  myunit/*.java
if [ $? -ne 0 ]; then exit 1; fi

jar  -cf myunit.jar  myunit/*.class
if [ $? -ne 0 ]; then exit 1; fi

javadoc  $classpath  $encoding  -quiet  -d javadoc  myunit
