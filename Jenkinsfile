//declarative pipeline
pipeline {
    agent any 

    stages {
        stage('\u26F9 Unit Test'){
            steps{
                sh "echo  I am stage: Unit Test"
                sh "./gradlew clean build"
            }
        }

        // stage('\u2623 Sonar-Scan'){
        //     steps{
        //         sh '''
        //         ./gradlew sonarqube \
        //         -Dsonar.projectKey=xxx \
        //         -Dsonar.host.url=http://<Your_IP>:9000 \
        //         -Dsonar.login=admin  \
        //         -Dsonar.password=admin
        //         '''
        //     }
        // }

        stage('\u270A Build jar'){
            steps{
                sh "./gradlew -x jar build"
            }
        }

        stage('\u270D Build image') {
            steps{
                sh 'echo "I am stage: Build image"'
                sh "docker build -t api-demo:v${env.BUILD_NUMBER} ."
            }
        } 

        stage('\u26F1 Deploy') {
            options {
              timeout(time: 300, unit: 'SECONDS') 
            }
            input {
              message "Should we continue?"
            }
            steps{
                sh 'echo "-----Deploy stage-----"'
                sh './check.sh'
                sh "docker run -d --name api-container -p 8888:8080 api-demo:v${env.BUILD_NUMBER}"
            }
        } 
        
        stage('\u261D Api Test'){
            steps{
                sh 'echo "I am stage: Api Test"'
            }
        }
    }   
}
