pipeline {
    agent any

    tools {
        maven 'M3'
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage("Deploy to QA") {
            steps {
                echo("Deploying to QA...")
            }
        }
stage('Regression Automation Test') {
    steps {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            dir('validaide-qa') {
                git branch: 'main', url: 'https://github.com/codebysahil/validaide-qa.git'
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/testng_sanity.xml'
            }
        }
    }
}

        stage('Publish Allure Reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'validaide-qa/allure-results']]
                    ])
                }
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'validaide-qa/reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Extent Report',
                    reportTitles: ''
                ])
            }
        }

        stage("Deploy to Stage") {
            steps {
                echo("Deploying to Stage...")
            }
        }

        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    dir('validaide-qa') {
                         git branch: 'main', git url: 'https://github.com/codebysahil/validaide-qa.git'
                        sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/testng_sanity.xml"
                    }
                }
            }
        }

        stage('Publish Sanity Extent Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'validaide-qa/reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Sanity Extent Report',
                    reportTitles: ''
                ])
            }
        }
    }
}
