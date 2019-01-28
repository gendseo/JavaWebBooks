#!/bin/bash

pid_8080=$(lsof -i:8080 | awk '{if($2~/[0-9]+/) print $2}')
pid_8080=$(echo $pid_8080 | awk '{if($1~/[0-9]+/) print $1}')
pid_8005=$(lsof -i:8005 | awk '{if($2~/[0-9]+/) print $2}')
pid_8005=$(echo $pid_8005 | awk '{if($1~/[0-9]+/) print $1}')
if [[ $pid_8080 != "" ]] && [[ $pid_8005 != "" ]];then
  if [[ $pid_8080 == $pid_8005 ]]; then
    kill -9 $pid_8080
    echo "Tomcat 进程结束了！！！"
  else
    kill -9 $pid_8005
    echo "Tomcat 进程结束了！！！"
  fi
else
  echo "没有检测到 Tomcat 进程！！！"
fi
