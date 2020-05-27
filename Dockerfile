# The Docker file to build and run Maven "pdf-analyzer" project.

## Build ##
### sudo docker build --no-cache -t "pdf-analyzer:PdfAnalyzer" .
### sudo docker images -a | grep pdf-analyzer

## Check content ##
### sudo docker image save pdf-analyzer > pdf-analyzer.tar

## Remove failed images with "none" tag if needed
### Remove all images with "none" tag or repository
### sudo docker images -a | grep none
### sudo docker rmi --force $(sudo docker images | grep "^<none" | awk '{print $3}')

## Run ##
### check what java version is in container: sudo docker run pdf-analyzer:PdfAnalyzer java -version
### run help: sudo docker run pdf-analyzer:PdfAnalyzer java -jar pdf-analyzer-0.0.0.Dev-SNAPSHOT.jar -help
### run: sudo docker run -v /home/Share/DockerInAction.pdf:/home/Share/DockerInAction.pdf:ro pdf-analyzer:PdfAnalyzer java -jar pdf-analyzer-0.0.0.Dev-SNAPSHOT.jar -source /home/Share/DockerInAction.pdf -type FONT -report CONSOLE


FROM maven:3.6.0-jdk-8-alpine

MAINTAINER Stepan stepan.melnik@gmail.com

LABEL maintainer="stepan.melnik@gmail.com"
LABEL version="0.1"
LABEL description="The Docker image to build Maven pdf-analyzer project"

ENV HOME=/home/usr/app
RUN echo "Create '$HOME' folder"
RUN mkdir -p $HOME 

COPY src $HOME
COPY pom.xml $HOME
RUN cat $HOME/pom.xml

COPY settings.xml $HOME/settings.xml
RUN cat $HOME/settings.xml

WORKDIR $HOME

RUN mvn --version

#RUN mvn dependency:go-offline -f pom.xml clean test

RUN mvn -s settings.xml -f pom.xml clean test
RUN mvn -s settings.xml -Dmaven.test.skip=true -f pom.xml package

# Run java project
FROM openjdk:8
ADD $HOME/target/pdf-analyzer-0.0.0.Dev-SNAPSHOT.jar pdf-analyzer-0.0.0.Dev-SNAPSHOT.jar

#CMD ["java", "-version"]
CMD ["java", "-jar", "pdf-analyzer-0.0.0.Dev-SNAPSHOT.jar"]

