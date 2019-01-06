#!/bin/bash

basepath=$(cd `dirname $0`; pwd -P);

if [ ! -d "$basepath/apache-tomcat-8.5.37/" ] || [ ! -f "$basepath/p.sql" ] ;then
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

echo -e "使用哪种数据库 [p]ostgreSQL/[M]ySQL    \c";
read wsql;

case "$wsql" in
"")
  wsql="M"
  ;;
esac

if [ $wsql == 'P' ] || [ $wsql == 'p' ];then
  if [ ! -f "$basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war" ];then
    cp -i $basepath/JavaWebBooks_postgresql.war $basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war
  else
    echo "项目文件已放到apache-tomcat-8.5.37/webapps/下！！！！！！";
  fi
  echo "使用 PostgreSQL 数据库！！！！！！";
  echo "请输入 psql postgres 密码！！！！！！";
  psql -h localhost -d postgres -U postgres -f $basepath/p.sql
fi

if [ $wsql == 'M' ] || [ $wsql == 'm' ];then 
  if [ ! -f "$basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war" ];then
    cp -i $basepath/JavaWebBooks_mysql.war $basepath/apache-tomcat-8.5.37/webapps/JavaWebBooks.war
  else
    echo "项目文件已放到apache-tomcat-8.5.37/webapps/下！！！！！！";
  fi
  echo "使用 MySQL 数据库！！！！！！";
  echo "请输入 mysql root 密码！！！！！！";
  mysql -uroot -p mysql -N -e "source $basepath/m.sql"
fi
pid=$(sudo netstat -lnp | grep 8080)
pid=${pid#*LISTEN      }
pid=${pid%/*}
case "$pid" in
"")
  echo "没有检测到 Tomcat 进程！！！";
  ;;
esac
sudo kill -9 $pid
$basepath/apache-tomcat-8.5.37/bin/startup.sh
echo "-----------------------------------";
echo "";
echo "项目初始化完成！！！！！！";
echo "请点击按住Ctrl点击下面链接查看效果！！！！！！";
echo "";
echo "http://localhost:8080/JavaWebBooks/";
echo "";
echo "如果要停止项目，请执行  ./stop.sh";
