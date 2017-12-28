#!/bin/bash

java=java
key=book

filePID=$key.pid
fileOut=$key.out

function prepare
{
    port="-DgenRob.genControl.port=2001"

    logServer="-DgenRob.genControl.log=terminal,long,slots,security"
    logModules="-Dbook.module.log=module,page,unit"
    log="$logServer  $logModules"

    cpServer=org.roblet.jar
    cpModules=book.module.jar
    cp="-cp $cpServer:$cpModules"

    classMain="org.roblet.Main server"
    classModules="book.module.ModuleImpl"
    class="$classMain  $classModules"

#    library="-Djava.library.path=."

#    securityD="-Djava.security.debug=access,failure"
    securityM="-Djava.security.manager"
    securityP="-Djava.security.policy==policy"
    securityS="-DgenRob.genControl.security=true"
    security="$securityD $securityM $securityP $securityS"

    server="$java  $library  $security  $port  $log  $cp  $class"
}
function start
{
    echo $0:  Starting server
    prepare
    echo $0:  +++++++++ Start at `date +%F+%T`  +++++++++  >> $fileOut
    echo $0:  $server >> $fileOut
    nohup nice $server < /dev/null >> $fileOut 2>&1  &  echo $! > $filePID
}
function run
{
    echo $0:  Running server
    prepare 
    echo $0:  $server 
    $server 
}
function stop
{
    echo $0:  Stopping server
    echo $0:  +++++++++ Stop at `date +%F+%T`  +++++++++  >> $fileOut
    kill `cat $filePID`
    rm -f $filePID
}
function backup
{
    if [ "$1" == "" ]; then host=$HOSTNAME; else host=$1; fi
    if [ "$2" == "" ]; then timestamp=$(date -u +%Y%m%d+%H%M); else timestamp=$2; fi
    dir=$host+$key-$timestamp

    echo $0:  Backing to $dir.tgz
    mkdir  $dir
    if [ $? -ne 0 ]; then N=10; echo $0: Error $N; exit $N; fi
    mv  $fileOut  $dir
    if [ $? -ne 0 ]; then N=11; echo $0: Error $N; exit $N; fi
    tar  czf  $dir.tgz  $dir
    if [ $? -ne 0 ]; then N=12; echo $0: Error $N; exit $N; fi
    rm  -rf  $dir
    if [ $? -ne 0 ]; then N=13; echo $0: Error $N; exit $N; fi
}


case "$1" in
  start)    start;;
  run)      run;;
  stop)     stop;;
  check)    ps axl | grep $key;;
  backup)   backup $2 $3;;
  *)        echo "Usage: $0 {start|run|stop|check|backup}";  exit 1;;
esac
