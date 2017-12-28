@echo off

rem  JDK 6

set  jarRDK=..\..\..\org.roblet.jar
set  classpath=-classpath %jarRDK%;.
set  encoding=-encoding UTF-8

javac  %classpath%  %encoding%  myunit\*.java
if  ERRORLEVEL 1  goto END

jar  -cf myunit.jar  myunit\*.class
if  ERRORLEVEL 1  goto END

javadoc  %classpath%  %encoding%  -quiet  -d javadoc  myunit

:END