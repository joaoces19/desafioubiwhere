FROM ubuntu:14.04

EXPOSE 80

RUN apt-get update && apt-get install -y software-properties-common python-software-properties && add-apt-repository ppa:webupd8team/java

RUN apt-get update && echo debconf shared/accepted-oracle-license-v1-1 select true | sudo debconf-set-selections && echo debconf shared/accepted-oracle-license-v1-1 seen true | sudo debconf-set-selections && apt-get install -y oracle-java8-installer \
	wget \
	unzip

RUN wget download.java.net/glassfish/4.0/release/glassfish-4.0.zip && unzip glassfish-4.0.zip -d /opt

RUN echo 'export PATH=/opt/glassfish4/bin:$PATH' >> ~/.profile

WORKDIR  /opt/glassfish4/bin/

RUN ./asadmin start-domain && ./asadmin --user=admin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource --restype javax.sql.DataSource --property portNumber=3306:serverName=db:user=root:password=1234:databaseName=mydb:connectionAttributes=\;create\\=true phonebook && ./asadmin create-jdbc-resource --user admin --connectionpoolid phonebook mydb

COPY ./mysql-connector-java-5.1.36-bin.jar /opt/glassfish4/glassfish/domains/domain1/lib/
COPY ./passwd /opt/glassfish4/bin/
COPY ./passwd2 /opt/glassfish4/bin/

COPY ./entry.sh ./
RUN chmod +x entry.sh

RUN ./asadmin start-domain && ./asadmin --user admin --passwordfile passwd change-admin-password 
RUN ./asadmin start-domain && ./asadmin --user admin --passwordfile passwd2 --host localhost --port 4848 enable-secure-admin

#RUN ./asadmin start-domain && echo y | ./asadmin --user admin --passwordfile passwd2 deploy /DesafioUbiwhere-1.0-SNAPSHOT.war

COPY ./target/DesafioUbiwhere-1.0-SNAPSHOT.war /

ENTRYPOINT sh entry.sh
