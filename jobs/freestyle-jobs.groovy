/**
 * Example Freestyle Job created via Job DSL.
 */
job('example-freestyle-job') {
    description('Freestyle job created via Job DSL.')

    parameters {
        stringParam('BRANCH_NAME', 'main', 'Git branch to build')
        choiceParam('ENVIRONMENT', ['dev', 'qa', 'prod'], 'Deployment environment')
    }

    scm {
        git {
            remote {
                url('https://your-git-server/your-org/sample-app.git')
                // credentials('git-credentials') // uncomment if needed
            }
            branch('$BRANCH_NAME')
        }
    }

    triggers {
        // Poll SCM every 5 minutes
        scm('H/5 * * * *')
    }

    steps {
        shell('echo "Building branch $BRANCH_NAME for $ENVIRONMENT"')
        shell('echo "Run your build commands here"')
    }

    publishers {
        archiveArtifacts {
            pattern('**/target/*.jar')
            onlyIfSuccessful(true)
        }
    }
}
