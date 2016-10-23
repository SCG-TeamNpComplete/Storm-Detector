echo 'starting installation process'
cd '/home/ec2-user/docker'
sudo docker login -e="kedar.gn20@gmail.com" -u="kedargn" -p="npcomplete"   #TODO : hide password
sudo docker pull kedargn/stormdetectortest
sudo docker run -d -p 9999:8080 --name stormdetectortest $(sudo docker images | grep kedargn/stormdetectortest | awk '{print $3}') >> ./log.txt
