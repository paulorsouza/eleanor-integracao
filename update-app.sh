echo "Atualizando eleanor integracao"
echo "----------------------------------------------------------------"
sudo systemctl stop apache2
git pull
cd ui
npm install
npm run build
cd ..
cd api
export PATH=$PATH:/opt/gradle/gradle-4.7/bin
gradle build
sudo docker ps -a | awk '{ print $1,$2 }' | grep eleanor | awk '{print $1 }' | xargs -I {} sudo docker rm -f {}
cd ..
sudo docker build --rm -t eleanor . 
sudo docker run -p 80:8080 eleanor &
