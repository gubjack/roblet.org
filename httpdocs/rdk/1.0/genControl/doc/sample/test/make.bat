@echo off

set  jarClient=..\..\..\org.roblet.client.jar
set  jarClientOld=..\..\..\genRob.genControl.client.jar
set  jarRoblet=..\..\..\org.roblet.jar
set  jarUnit=..\unit\myunit.jar
set  classpath=-classpath %jarClient%;%jarClientOld%;%jarRoblet%;%jarUnit%;.
set  encoding=-encoding UTF-8

javac  %classpath%  %encoding%  *.java
