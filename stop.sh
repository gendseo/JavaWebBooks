#!/bin/bash

basepath=$(cd `dirname $0`; pwd -P);

if [ ! -d "$basepath/apache-tomcat-8.5.37/" ] || [ ! -f "$basepath/p.sql" ] ;then
  echo "没有 apache-tomcat-8.5.37 目录！！！";
  exit 1
fi

# $basepath/apache-tomcat-8.5.37/bin/shutdown.sh

pid=$(sudo netstat -lnp | grep 8080)
pid=${pid#*LISTEN      }
pid=${pid%/*}

case "$pid" in
"")
  echo "Tomcat 进程结束了！！！";
  exit 1
  ;;
esac

sudo kill -9 $pid