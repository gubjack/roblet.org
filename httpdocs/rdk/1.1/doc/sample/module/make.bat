@echo off

set  jarRoblet=..\..\..\org.roblet.jar
set  jarModules=..\..\..\genRob.genControl.modules.jar
set  jarUnit=..\unit\myunit.jar
set  classpath=-classpath %jarModules%;%jarRoblet%;%jarUnit%;.
set  encoding=-encoding UTF-8

javac  %classpath%  %encoding%  mymodule\*.java
if  ERRORLEVEL 1  goto END

jar  -cf mymodule.jar  mymodule\*.class

:END