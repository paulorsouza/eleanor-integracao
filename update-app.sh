echo "Atualizando eleanor integracao"
echo "----------------------------------------------------------------"
sudo systemctl stop apache2
sudo docker ps -a | awk '{ print $1,$2 }' | grep eleanor | awk '{print $1 }' | xargs -I {} sudo docker rm -f {}
git pull
cd ui
npm install
npm run build
cd ..
cd api
gradle build
cd ..
sudo docker build --rm -t eleanor . 
sudo docker run -p 8080:8080 eleanor &
