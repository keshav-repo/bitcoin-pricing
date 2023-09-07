## Installation 

docker build -t backend-bitcoin:1.0.0 .

docker run -p 8080:8080 backend-bitcoin:1.0.0


docker ps -a | grep 'backend-bitcoin' | awk '{print $1}' | xargs docker rm

mvn clean install -P production

