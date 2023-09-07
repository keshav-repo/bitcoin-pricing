# Bitcoin-pricing

### Prerequisite 
- Docker 
- maven 
- node 

### To Start the app, use below command 
```shell
# clone repo

# Build frontend
cd frontend/
npm run build-prod

# build backend
cd backend/
mvn clean install -P prod

# Run the apps on docker
docker-compose up
```


