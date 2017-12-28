@echo off

rem  JDK 6

set  jarRDK=..\..\..\org.roblet.jar
set  jarModule=mymodule.jar
set  jarUnit=..\unit\myunit.jar
set  classpath=-classpath %jarRDK%;%jarModule%;%jarUnit%

set  log=-DgenRob.genControl.log=terminal

set  module=mymodule.Module2Impl

java  %log%  %classpath%  org.roblet.Main server  %module%
