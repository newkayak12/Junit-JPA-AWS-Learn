#!/usr/bin/env bash
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

IDLE_PORT=$(find_idle_port)

echo "HEALTH CHECK START..."
echo "IDLE_PORT : $IDLE_PORT"
echo "curl -s http://localhost:$IDLE_PORT/profile"

sleep 10

for RETRY_COUNT in {1..10}
do
	RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
	UP_COUNT=`$(echo ${RESPONSE} | grep 'real' | wc -1)`

	if [ ${UP_COUNT} -ge 1 ]
	then

		echo "COMPLETE HEALTH CHECK"
		switch_proxy
		break;
	else
		echo " CHECKING HEALTH ..."
		echo " HEALTH CHECK : ${RESPONSE}"
	fi

	if [ ${RETRY_COUNT} -eq 10 ]
	then 
		echo "HEALTH CHECK FAILED..."
		echo "SUSPEND! "
		exit 1
	fi	
		echo "> NOW HEALTH CHECKING >>>>"
		sleep 10
done