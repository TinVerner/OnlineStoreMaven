pipeline {
    agent any

    tools {
        maven 'Maven-3.9.16'
    }

    stages {
        stage('Check Structure') {
            steps {
                bat 'if not exist pom.xml exit /b 1'
                bat 'echo pom.xml FOUND'

                bat 'if not exist Jenkinsfile exit /b 1'
                bat 'echo Jenkinsfile FOUND'

                bat 'if not exist src exit /b 1'
                bat 'echo src FOUND'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }
}