#!/bin/bash

basepath=$(cd `dirname $0`; pwd -P);

if [[ ! -d "$basepath/apache-tomcat-8.5.37/" ]] || [[ ! -f "$basepath/p.sql" ]] ;then
  echo "没有 apache-tomcat-8.5.37 目录！！！！！！";
  echo "正在尝试解压......";
  tar -zxf $basepath/apache-tomcat-8.5.37.tar.gz
  echo "解压完成！！！！！！";
else
  echo "存在 apache-tomcat-8.5.37 目录！！！！！！";
  echo "正在尝试重新解压覆盖......";
  sudo rm -rf $basepath/apache-tomcat-8.5.37/
  tar -zxf $basepath/apache-tomcat-8.5.37.tar.gz
  echo "解压覆盖完成！！！！！！";
fi

echo -e "使用哪种数据库 [p]ostgreSQL/[M]ySQL，默认 MySQL    \c";
read wsql;

if [[ $wsql == 'P' ]] || [[ $wsql == 'p' ]];then
  if [[ ! -f "$basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war" ]];then
  cp -i $basepath/JavaWebBooks_postgresql.war $basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war
  else
    echo "项目文件已放到apache-tomcat-8.5.37/webapps/下！！！！！！";
  fi
  echo "使用 PostgreSQL 数据库！！！！！！";
  echo "请输入 psql postgres 密码！！！！！！";
  psql -h localhost -d postgres -U postgres -f $basepath/p.sql
else
  if [[ ! -f "$basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war" ]];then
  cp -i $basepath/JavaWebBooks_mysql.war $basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war
  else
    echo "项目文件已放到apache-tomcat-8.5.37/webapps/下！！！！！！";
  fi
  echo "使用 MySQL 数据库！！！！！！";
  echo "请输入 mysql root 密码！！！！！！";
  mysql -uroot -p mysql -e "source $basepath/m.sql"
fi
# 检测8080端口和8005端口
pid_8080=$(lsof -i:8080 | awk '{if($2~/[0-9]+/) print $2}')
pid_8080=$(echo $pid_8080 | awk '{if($1~/[0-9]+/) print $1}')
pid_8005=$(lsof -i:8005 | awk '{if($2~/[0-9]+/) print $2}')
pid_8005=$(echo $pid_8005 | awk '{if($1~/[0-9]+/) print $1}')
if [[ $pid_8080 != "" ]] && [[ $pid_8005 != "" ]];then
  kill -9 $pid_8080
  kill -9 $pid_8005
fi
echo "----------------------------------------------";
$basepath/apache-tomcat-8.5.37/bin/startup.sh
echo "----------------------------------------------";
echo "项目初始化完成！！！！！！";
echo "请点击按住Ctrl点击下面链接查看效果！！！！！！";
echo "----------------------------------------------";
echo "http://localhost:8080/JavaWebBooks/";
echo "----------------------------------------------";
echo "如果要停止项目，请执行  ./stop.sh";
echo "----------------------------------------------";
exit 1
