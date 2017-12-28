#!/bin/sh

jarClient="../../../../org.roblet.client.jar"
jarUnit="../unit/myunit.jar"
classpath="-classpath $jarClient:$jarUnit:."

java  $classpath  HelloUnitTest