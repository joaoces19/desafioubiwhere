./asadmin start-domain 
echo y | ./asadmin --user admin --passwordfile passwd2 deploy /DesafioUbiwhere-1.0-SNAPSHOT.war
./asadmin stop-domain
./asadmin start-domain --verbose