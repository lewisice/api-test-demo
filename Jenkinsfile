//declarative pipeline
pipeline {
    agent any 

    stages {
        stage('Unit Test'){
            steps{
                sh "echo  I am stage: Unit Test"
                sh "./gradlew clean build"
            }
        }

        stage('Sonar-Scan'){
            steps{
                sh '''
                BRIDGES_IP=`/sbin/ip route|awk '/default/ { print $3 }'`
                ./gradlew sonarqube \
                -Dsonar.projectKey=xxx \
                -Dsonar.host.url=http://${BRIDGES_IP}:9000 \
                -Dsonar.login=admin  \
                -Dsonar.password=admin
                '''
            }
        }

        stage('Build jar'){
            steps{
                sh "./gradlew -x jar build"
            }
        }

        stage('\u270D Build image') {
            steps{
                sh "docker build -t api-demo:v${env.BUILD_NUMBER} ."
            }
        } 

        stage('\u26F1 Deploy') {
            steps{
                sh 'docker ps -f name=api-container -q  | xargs --no-run-if-empty docker rm -f'
                sh "docker run -d --name api-container -p 8080:8080 api-demo:v${env.BUILD_NUMBER}"
            }
        } 
        
       // stage('\u261D Api Test'){
       //     steps{
       //         sh 'sleep 30'
       //         // build job: 'Jmeter-test'
       //         sh '/opt/apache-jmeter-5.1.1/bin/jmeter.sh -n -t api-test/api-test-demo.jmx'
       //     }
       // }
    }   
}
