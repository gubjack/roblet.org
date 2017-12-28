@echo off

rem  JDK 6

set  jarRDK=..\..\..\org.roblet.jar
set  jarUnit=..\unit\myunit.jar
set  classpath=-classpath %jarRDK%;%jarUnit%;.

java  %classpath%  HelloUnitTest
