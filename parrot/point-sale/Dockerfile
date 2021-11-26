FROM openjdk:8-jdk-alpine

RUN apk add --no-cache curl
WORKDIR /data/point-sale-web-api/
RUN mkdir -p /data/point-sale-web-api/cfg/

COPY point-sale-web-api.jar /data/point-sale-web-api/point-sale-web-api.jar 
COPY application.yml /data/point-sale-web-api/cfg/application.yml
COPY logback.xml /data/point-sale-web-api/cfg/logback.xml
COPY entrypoint-point-sale-web-api.sh /data/entrypoint-point-sale-web-api.sh

RUN chmod 755 /data/entrypoint-point-sale-web-api.sh

#Container deployment
EXPOSE 8081
ENTRYPOINT ["/data/entrypoint-point-sale-web-api.sh"]
