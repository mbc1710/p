#!/bin/sh
echo // Date init start // `date`  
echo "Setting options ndots:1 in /etc/resolv.conf"
echo "options ndots:1" >> /etc/resolv.conf

echo "Running Application ..."
java -Xms128M -Xmx1G  -jar /data/point-sale-web-api/point-sale-web-api.jar --spring.config.location=file:/data/point-sale-web-api/cfg/application.yml  -Dlogging.config=file:/data/point-sale-web-api/cfg/logback.xml

echo "Up...."
