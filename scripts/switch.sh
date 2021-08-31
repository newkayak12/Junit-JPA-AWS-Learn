#!/usr/bin/env bash
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh


function switch_proxy(){
	IDLE_PORT=$(find_idle_port)

	echo "SWICHING PORT TO $IDLE_PORT"
	echo "NOW SWICHING!"
	echo "set \$service_url http://127.0.0.1:$(IDLE_PORT);" | sudo tee /etc/nginx/conf.d/serive-url.inc

	echo " RESTART NGINX..."
	sudo service nginx reload
}