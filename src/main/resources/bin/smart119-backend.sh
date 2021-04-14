#!/bin/sh
#
# devops  Startup script for devops
#
# chkconfig: - 97 15
# processname: java
# description: start and stop devops
#
### BEGIN INIT INFO
# Provides: thrz
# Required-Start: $local_fs $remote_fs $network
# Required-Stop: $local_fs $remote_fs $network
# Short-Description: start and stop devops
### END INIT INFO
. /etc/rc.d/init.d/functions
if [ -L $0 ]; then
    initscript=`/bin/readlink -f $0`
else
    initscript=$0
fi

sysconfig=`/bin/basename $initscript`

if [ -f /etc/sysconfig/$sysconfig ]; then
    . /etc/sysconfig/$sysconfig
fi

#. /opt/smart/java/env.sh

APP_DIR="$( cd "$( dirname "$0"  )" && cd "../" && pwd  )"

progname=${sysconfig}
prog=java
dev_version=${DEV_VERSION-2.0.0}
dev_name=${DEV_NAME-smart119-backend}
APP_FILE="${dev_name}-${dev_version}.jar"
pid_file=${PID_FILE-"$APP_DIR/$dev_name.pid"}
#APP_CONF=$APP_DIR/application.properties
RETVAL=0

if [ ! -d $APP_DIR/logs ]; then
    mkdir -p $APP_DIR/logs
fi
log_file=${LOG_FILE-"$APP_DIR/logs/bms_backend.log"}

#set java home
#export JAVA_HOME=/opt/jdk1.8.0_111
echo "$1 $APP_DIR/$APP_FILE"
usage(){
    echo 'Usage:  sh ${dev_name}.sh [start|stop|restart|status]'
    exit 1
}

start(){
	export JAVA_OPTS="-server -Xms128M -Xmx128M -Xmn256M -XX:PermSize=64M -XX:MaxPermSize=64M -Djava.io.tmpdir=$APP_DIR/tmp"
	ulimit -n 65535
    if [[ $APP_CONF ]]; then
        cd $APP_DIR/bin &&
        nohup java -jar $APP_DIR/"$APP_FILE" --spring.config.location="$APP_CONF" > $APP_DIR/log/bms_backend.log 2>&1 &
		'starting...'
    else
	echo 'starting...'
	cd $APP_DIR/bin &&
	nohup java -classpath $APP_DIR/conf/:$APP_DIR/lib/* com.smart119.Smart119ApplicationBackend `pwd` > $log_file 2>&1 &
	RETVAL=$?
	if [ $RETVAL -eq "0" ]
	then
		echo $! > $pid_file
		echo 'Start Success!'
	else
	  	echo 'Start failed!'
	fi
	
    fi
     return $RETVAL
}

stop(){
    kill -9 `cat $pid_file`
    RETVAL=$?
    if [ $RETVAL -eq "0" ]
    then
	 rm -rf $pid_file
	echo 'Stop Success!'
    else 
	echo 'Stop Failed!'
    fi
   return $RETVAL
}

status(){
	ps -ef | grep `cat $pid_file` | grep -v grep
	RETVAL=$?
    if [ $RETVAL -eq "0" ]
    then
    echo 'Runing!'
    else
    echo 'Stopped!'
    fi
}

restart(){
    stop
    start
}


case "$1" in
    "start")
	start;;
    "stop")
	stop;;
    "restart")
	restart;;
	"status")
	status;;
    *)
	usage;;
esac
