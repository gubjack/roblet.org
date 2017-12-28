@echo off

set  jarServer=..\..\..\genRob.genControl.jar
set  jarModule=mymodule.jar
set  jarUnit=..\unit\myunit.jar
set  classpath=%jarServer%;%jarModule%;%jarUnit%

set  security=-DgenRob.genControl.security=true
set  port=-DgenRob.genControl.port=2001
set  log=-DgenRob.genControl.log=terminal

set  module=mymodule.ModuleImpl
set  main=genRob.genControl.Main

java  %security%  %port%  %log%  -classpath %classpath%  %main%  %module%
