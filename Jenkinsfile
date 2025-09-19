pipeline {
    //agent= any agent

    environment {
        MAVEN_HOME = 'C:\\apache-maven-3.9.11' // Update if Maven is in a different path
        PATH = "${env.MAVEN_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checking out source code..."
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                echo "Compiling the Spring Boot application..."

                bat """cd matrimony-backend
                mvn clean compile"""
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo "Running unit tests..."
                bat 'mvn test'
            }
        }



    }

    post {
        success {
            echo 'Build and test completed successfully.'
        }
        failure {
            echo 'Build or tests failed.'
        }
    }
}