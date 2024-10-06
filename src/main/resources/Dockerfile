FROM jenkins/jenkins:2.479-jdk21
USER root

RUN apt-get update
RUN apt-get install -y nano
RUN apt-get install -y sudo
RUN mkdir -p /usr/local/empik
COPY ../../.. /usr/local/empik
RUN ls
RUN apt update
RUN apt install wget