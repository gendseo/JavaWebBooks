#!/bin/bash

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
