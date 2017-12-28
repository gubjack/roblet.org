@echo off

set  jarClient=..\..\..\org.roblet.client.jar
set  jarUnit=..\unit\myunit.jar
set  classpath=-classpath %jarClient%;%jarUnit%;.

java  %classpath%  HelloUnitTest
