pipeline {
	agent any
	
	environment {
	JAVA_HOME= '/usr/lib/jvm/java'
	}
	
	stages {
		stage('Build') {
			steps{
				sh 'mvn compile'
			}
		}
		stage('Test') {
			steps {
				wrap([$class: 'Xvfb', debug: true, displayName : 99, displayNameOffset: 0, timeout: 15]) {
				sh 'mvn test'
				}
			}
	
		}	
		
		stage('Publish') {
			steps {
				
				step([$class: 'CucumberTestReportPublisher', fileIncludePattern: '**/*.json', reportsDirectory: 'target- ' , copyHTMLInWorkspace: true, ignoreUndefinedSteps: true])
			}
	
		}
		
		stage('Post') {
			steps {
				echo 'Posting....'
			}
	
		}
	
	}
	
}
