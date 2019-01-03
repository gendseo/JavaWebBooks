# JavaWebBooks
> 一个关于 java web 前后端分离的图书实践

## 说明
- 前端(html/jsp)通过 http 请求与后端(servlet)交互;
- 前端请求后端 API 后，后端返回 JSON 数据;
- 前端拿到 JSON 数据并解析成 Javascript 对象，使用 Bootstarp Table 动态创建表格;
- 请配合每个文件的注释使用该项目

```
前端(html + bootstarp(table) + jquery(ajax));  
后端(servlet + jdbc + postgresql);  
整体流程：html(Bootstrap) --> jquery(ajax) --> servlet(API) --> Dao类(jdbc) --> postgresql  
```

## 开发环境
- Tomcat >= v8.0
- Eclipse J2EE >= vR12
- PostgreSQL >= v9.0
- Jquery >= v2.0
- Bootstarp Table >= v1.13.1

## 数据库表结构
> 如无意外，请保持数据库表结构一致  
> 如使用的数据库为 Postgres 不愿手动创建数据库及表，请执行目录中的 p.sql 文件

```
 Bid |         Bname          | Bnumber 
-----+------------------------+---------
   1 | 机器学习               |      80
   2 | J2EE 从入门到精通      |      14
   3 | 数据结构与算法          |      22
   4 | Java 网络编程设计实训   |      45
   5 | Javascript 高级编程    |     233
   6 | 算法导论               |      32
   7 | C++11 标准库           |     182
   8 | 高性能 MySQL           |      12
   9 | Linux 服务器架设与维护   |      76
  10 | 系统架构设计师入门       |      66
  11 | BootstrapTable 教程    |      52
  12 | Kotlin 入门教程        |     182
  13 | Flutter 实战           |     712
  14 | 精通 Linux             |     476
  15 | 流程的 Python          |     606
  16 | 设计模式 (JAVA版)      |     362
  17 | 深度学习               |    1282
  18 | Redis 开发与运维       |     127
  19 | Nginx_Lua 高性能实践   |     376
  20 | SpringBoot 入门       |      76
```


## 如何使用
#### 克隆仓库并进入项目
> git clone https://github.com/gendseo/JavaWebBooks.git  && cd JavaWebBooks/

#### 运行于 Tomcat
> 手动将 JavaWebBooks.war 文件放于 Tomcat 根文件夹中的 webapps 的文件夹中  
> 运行 bin/startup.sh  
> 浏览器中查看  

#### 使用一键运行脚本查看效果
> 在终端里运行 run.sh 脚本  
> ./run.sh  
> 浏览器中查看  

#### 记得停止一键运行脚本
> 在终端里运行 stop.sh 脚本  
> ./stop.sh  

#### 在 Eclipse 中添加项目
> 注意，添加的是 JavaWebBooks 中的 JavaWebBooks  
> File --> Open Projects from File System... --> do something  

#### 更换数据库
> 修改 src 中 top.gendseo.books.dao.BooksDao 中的：  
> DB_DEIVER, DB_URL, DB_NAME, DB_USER, DB_PASSWORD  

#### 如何查看前端网页
> http://localhost:8080/JavaWebBooks/

#### 后端接口地址
> http://localhost:8080/JavaWebBooks/BooksApi/*

## 进度（v1.0.0）

#### 开发进度
- [x] 查询图书数据
- [x] 增加图书数据
- [x] 删除图书数据
- [x] 更新图书数据
- [x] 一键运行Demo的 PostgreSQL 脚本
- [ ] 一键运行Demo的 MySQL 脚本

#### Issues
- [x] 查询图书数据时页面会生成重复数据
- [x] 优化 Dao 类的业务逻辑
- [x] 优化 servlet API 响应请求的逻辑
- [x] 优化后端性能，添加可配置化
- [x] 优化 JS 代码逻辑问题，并使用 ES6 语法
- [x] 优化前端请求逻辑
- [x] 优化 Jquery 操作表格的 CRUD 时对应的逻辑
