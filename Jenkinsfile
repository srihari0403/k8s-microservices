pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/srihari0403/k8s-microservices.git'
            }
        }

        stage('Setup') {
            steps {
                sh """
                    export KUBECONFIG=/var/snap/jenkins/current/.kube/config
                    kubectl apply -f k8s/namespace.yaml
                    kubectl get ns ingress-nginx || kubectl apply -f k8s/ingress-nginx-controller.yaml
                    helm upgrade --install netflix-ingress charts/ingress --namespace production
                """
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
