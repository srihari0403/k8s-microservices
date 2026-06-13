pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/srihari0403/netflix-app.git'
            }
        }

        stage('Deploy Microservices') {
            parallel {
                stage('movie-service') {
                    steps {
                        build job: 'movie-service', wait: true
                    }
                }
                stage('auth-service') {
                    steps {
                        build job: 'auth-service', wait: true
                    }
                }
                stage('user-service') {
                    steps {
                        build job: 'user-service', wait: true
                    }
                }
            }
        }
    }
}
