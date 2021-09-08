#!/usr/bin/env bash
ABSPATH = $(readlink -f $0)
ABSDIR = $(dirname $ABSPATH)
source ${ABSDIR}/profile.sh


REPOSITORY = /home/ec2-user/app/step3
PROJECT_NAME=Junit-JPA-AWS-Learn/book

echo "> COPY BUILD FILE... "
echo "cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> DEPLOY NEW APPLICAITON..."
JAR_NAME =$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME : $JAR_NAME"

echo "> SET PRIVILEGE"

chmod +x $JAR_NAME

echo "> RUN $JAR_NAME "

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile= $IDLE_PROFILE 로 실행합니다."
nohup java -jar \
	-Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
	-Dspring.profiles.active=$IDLE_PROFILE \
	$JAR_NAME > $REPOSITORY/nohub.out 2>&1 &