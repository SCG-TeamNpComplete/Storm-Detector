#!/bin/bash

sudo yum install -y docker-io
sudo service docker start

dangling_images="$(sudo docker images -f "dangling=true" -q)"


if [ "$dangling_images" == "" ] ; then
        echo "no images"
else
		sudo docker rmi -f $(sudo docker images -f "dangling=true" -q)
    echo "images present"
fi

install_dir="/usr/local"
dir="/usr/local/zookeeper-3.4.8"
if [ ! -d "$dir" ] ; then
	cd "$install_dir"
	wget http://www-us.apache.org/dist/zookeeper/zookeeper-3.4.8/zookeeper-3.4.8.tar.gz
	tar xzf zookeeper-3.4.8.tar.gz
	rm zookeeper-3.4.8.tar.gz
	cd "$dir/conf"
	touch zoo.cfg
	echo "tickTime=2000" > zoo.cfg
	echo "initLimit=10" >> zoo.cfg
	echo "syncLimit=5" >> zoo.cfg
	echo "dataDir=/var/lib/zookeeper" >> zoo.cfg
	echo "clientPort=2181" >> zoo.cfg
	echo "server.1=ec2-35-161-48-143.us-west-2.compute.amazonaws.com:2888:3888" >> zoo.cfg
	echo "server.2=ec2-35-160-137-157.us-west-2.compute.amazonaws.com:2888:3888" >> zoo.cfg
	echo "server.3=ec2-52-15-90-97.us-east-2.compute.amazonaws.com:2888:3888" >> zoo.cfg

	cd "$dir/bin"
	./zkServer.sh stop
	./zkServer.sh start
fi

