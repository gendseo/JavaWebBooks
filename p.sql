DROP DATABASE if exists "Books";
CREATE DATABASE "Books"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'zh_CN.UTF-8'
    LC_CTYPE = 'zh_CN.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
\c Books;
DROP TABLE if exists books;
CREATE TABLE public.books
(
    "Bid" smallint NOT NULL,
    "Bname" character varying(100) NOT NULL,
    "Bnumber" smallint NOT NULL,
    PRIMARY KEY ("Bid")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE public.books OWNER to postgres;
INSERT INTO books VALUES 
(1, '机器学习', 80), (2, 'J2EE 从入门到精通', 14), (3, '数据结构与算法', 22), (4, 'Java 网络编程设计实训', 45), (5, 'Javascript 高级编程', 233),
(6, '算法导论', 32), (7, 'C++11 标准库', 182), (8, '高性能 MySQL', 12), (9, 'Linux 服务器架设与维护', 76), (10, '系统架构设计师入门', 66),
(11, 'BootstrapTable 教程', 52), (12, 'Kotlin 入门教程', 182), (13, 'Flutter 实战', 712), (14, '精通 Linux', 476), (15, '流程的 Python', 606),
(16, '设计模式 (JAVA版)', 362), (17, '深度学习', 1282), (18, 'Redis 开发与运维', 127), (19, 'Nginx_Lua 高性能实践', 376), (20, 'SpringBoot 入门', 76);
SELECT * FROM books;
