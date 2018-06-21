/* Requires the Docker Pipeline plugin */
/*
pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
*/

node('docker') {
    checkout scm
    stage('Build') {
        docker.image('maven:3.3.3').inside {
            sh 'mvn --version'
        }
    }
}
