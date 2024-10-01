FROM jenkins/jenkins
USER root

RUN apt-get update
RUN apt-get install -y nano
RUN mkdir -p /usr/local/empik
COPY ../../.. /usr/local/empik
RUN ls
RUN apt update
RUN apt install wget
RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz
RUN tar -xvf apache-maven-3.9.9-bin.tar.gz
RUN mv apache-maven-3.9.9 /opt/
RUN M2_HOME='/opt/apache-maven-3.9.9'
RUN PATH="$M2_HOME/bin:$PATH"
RUN export PATH