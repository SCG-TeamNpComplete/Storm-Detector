echo 'starting installation process'
cd '/home/ec2-user/docker'
sudo docker login -e="kedar.gn20@gmail.com" -u="kedargn" -p="npcomplete"   #TODO : hide password
sudo docker pull kedargn/stormdetector
sudo docker run -d -p 9999:8080 --name stormdetector $(sudo docker images | grep kedargn/stormdetector | awk '{print $3}') >> ./log.txt
