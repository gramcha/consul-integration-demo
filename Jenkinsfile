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

node {
	def app
	def mavendocker
    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }
    stage('Prerequisite') {
    		mavendocker =docker.image('maven:3.3.3')
    		sh 'echo "maven ready"'
    }
    stage('Build') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */
         mavendocker.inside {
            sh 'mvn install -DskipTests'
        }
        app = docker.build("consul-integration-demo")
    }
    stage('Test image') {
        /* Ideally, we would run a test framework against our image.
         * For this example, we're using a Volkswagen-type approach ;-) */
			sh 'consul agent -dev'
			steps{
				mavendocker.inside {
					sh 'mvn test'
				}
			}
			post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
	        app.inside {
	            sh 'echo "Tests passed"'
	        }
    }
}
