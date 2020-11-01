@Library('jenkins-pipeline-shared-lib-sample') _

def createJob(project) {
    return """
    pipelineJob('integration-tests/${project}') {
        definition {
            cps {
                sandbox()
                script(\"\"\"
                    @Library('jenkins-pipeline-shared-lib-sample') _
                             
                    stage('Print Build Info') {
                        printBuildinfo {
                            name = "Sample Name"
                        }
                    }
                    stage('Disable balancer') {
                        disableBalancerUtils()
                    }

                    node {
                        stage('Run tests') {
                            git(branch: 'master',
                            credentials: 'pontusgithub',
                            url: 'https://github.com/Prisen/${project}.git')
                            sh "./mvnw clean test"
                        }
                    }
                    stage('Enable balancer') {
                        enableBalancerUtils()
                    }
                    stage('Check Status') {
                        checkStatus()
                    }
                \"\"\")    
            }
        }
    }            
    """
}

node {
    def jobDslText = ""
    jobDslText += createJob('drools-jenkins')
    jobDsl(removedJobAction: 'DELETE', scriptText: jobDslText)
}