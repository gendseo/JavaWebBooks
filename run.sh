#!/bin/bash

basepath=$(cd `dirname $0`; pwd -P);

if [ ! -d "$basepath/apache-tomcat-8.5.37/" ] || [ ! -f "$basepath/p.sql" ] ;then
  echo "没有 apache-tomcat-8.5.37 目录！！！";
  exit 1
fi

if [ ! -f "$basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war" ];then
#   mv $basepath/JavaWebBooks.war $basepath/webapps/
  cp -i $basepath/JavaWebBooks.war $basepath/apache-tomcat-8.5.37/webapps/
else
  echo "项目文件已放到webapps/下！！！";
fi

echo -e "使用哪种数据库 [P]ostgreSQL/[m]ySQL  \c";
read wsql;

case "$wsql" in
"")
  wsql="P"
  ;;
esac

if [ $wsql == 'P' ] || [ $wsql == 'p' ];then
  echo "使用 PostgreSQL 数据库！！！";
  sudo psql -h localhost -d postgres -U postgres -f $basepath/p.sql
fi

if [ $wsql == 'M' ] || [ $wsql == 'm' ];then 
  echo "使用 MySQL 数据库！！！";
fi

$basepath/apache-tomcat-8.5.37/bin/startup.sh
