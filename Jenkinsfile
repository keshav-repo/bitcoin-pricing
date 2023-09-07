pipeline {
    agent any
    tools{
        maven 'Maven_3_9_4'
        nodejs 'NodeJS_20_6'
    }
    stages{
         stage('Build backend'){
             steps{
               script{
                   checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/keshav-repo/bitcoin-pricing']])
               }
               dir('backend') {
                 sh 'mvn clean install -P prod'
               }
             }  
            
         }
         stage('Build frontend'){
              steps{
                      dir('frontend'){
                          sh('npm install')
                         sh('npm run build-prod')
                     }
                  
              }
             
         }
    }
}