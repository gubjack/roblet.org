@echo off

rem  JDK 6

set  jarRDK=..\..\..\org.roblet.jar
set  jarUnit=..\unit\myunit.jar
set  classpath=-classpath %jarRDK%;%jarUnit%;.
set  encoding=-encoding UTF-8

javac  %classpath%  %encoding%  *.java
