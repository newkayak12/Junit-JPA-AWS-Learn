#!/bin/bash
REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=Junit-JPA-AWS-Learn/book
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo ">stop running application"

CURRENT_PID = $(pgrep -f book |pgrep -f java |awk '{print $1}')
# TEST_PID = $(pgrep -f book.*.jar  )

echo ">now Running pid : $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
	echo ">_"
else 
	echo "> kill -15 $CURRENT_PID"
	kill -15 $CURRENT_PID
	sleep 5
fi

echo "> deploy new One"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar |tail -n 1)

echo ">JAR_NAME : $JAR_NAME"

echo "privilege "

chmod +x $JAR_NAME

echo "run $JAR_NAME"
nohup java -jar \
         -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
   	 -Dspring.profiles.active=real \
   $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &