/**
 * Example Pipeline Job created via Job DSL.
 */
pipelineJob('example-pipeline-job') {
    description('Pipeline job created via Job DSL.')

    parameters {
        stringParam('BRANCH_NAME', 'main', 'Git branch for Jenkinsfile')
        booleanParam('RUN_TESTS', true, 'Run tests during the pipeline')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://your-git-server/your-org/sample-app.git')
                        // credentials('git-credentials') // uncomment if needed
                    }
                    branch('$BRANCH_NAME')
                }
            }
            scriptPath('Jenkinsfile')  // path to Jenkinsfile in repo
        }
    }

    triggers {
        // Schedule: every day at 2 AM
        cron('H 2 * * *')
    }
}
