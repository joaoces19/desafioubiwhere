FROM ubuntu:14.04

RUN apt-get update && apt-get -y install mysql-server

COPY ./schema.sql .

RUN /etc/init.d/mysql start && mysqladmin -u root password '1234' && mysql -u root --password=1234 < schema.sql; 

RUN /etc/init.d/mysql start && mysql -u root --password=1234 -e "GRANT ALL PRIVILEGES ON mydb.* TO 'root'@'%' IDENTIFIED BY '1234';" && mysql -u root --password=1234 -e "FLUSH PRIVILEGES"

COPY ./my.cnf /etc/mysql/my.cnf

ENTRYPOINT /usr/bin/mysqld_safe
