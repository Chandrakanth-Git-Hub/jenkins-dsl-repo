job('example-freestyle-job') {
    description('Freestyle job created via Job DSL with parameters and triggers.')

    parameters {
        stringParam('BRANCH_NAME', 'main', 'Git branch to build')
        choiceParam('ENVIRONMENT', ['dev', 'qa', 'prod'], 'Deployment environment')
        booleanParam('SKIP_TESTS', false, 'Skip running tests')
    }

    scm {
        git {
            remote {
                url('https://github.com/Chandrakanth-Git-Hub/jenkins-dsl-repo.git')
                // credentials('git-credentials')
            }
            branch('$BRANCH_NAME')
        }
    }

    triggers {
        // Poll SCM every 10 minutes
        scm('H/10 * * * *')
    }

    steps {
        shell('echo "Building $BRANCH_NAME for $ENVIRONMENT"')
        shell('if [ "$SKIP_TESTS" = "true" ]; then echo "Skipping tests"; el
se echo "Running tests"; fi')
    }
}
