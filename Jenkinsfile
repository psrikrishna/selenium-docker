pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //bat
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //bat
                sh "docker build -t srikp/selenium-docker:${BUILD_NUMBER} ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //bat
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push srikp/selenium-docker:${BUILD_NUMBER}"
			        sh "docker push srikp/selenium-docker:latest"
			    }
            }
        }
    }
}