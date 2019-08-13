//declarative pipeline
pipeline {
    agent any 

    stages {
        stage('\u270A Unit Test'){
            steps{
                sh "I am stage --> Unit Test"
            }
        }
        
        stage('\u261D Api Test'){
            steps{
                sh "I am stage --> Api Test"
            }
        }

        stage('Build package'){
            steps{
                sh "I am stage --> Build package"
                sh "./gradlew -x jar build"
            }
        }

        stage('\u270D  Build image') {
            steps{
                sh "I am stage --> Build image"
                sh "docker build -t api-demo:v${env.BUILD_NUMBER} ."
            }
        } 

        stage('\u26F1  Deploy to local') {
            steps{
                sh "I am stage --> Deploy to local"
                sh "echo deploy...."
            }
        }         
    }
    
}