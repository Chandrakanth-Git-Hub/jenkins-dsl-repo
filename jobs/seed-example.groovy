/**
 * Simple example to verify seed job works.
 */
job('dummy-seed-test-job') {
    description('Job created by Job DSL seed job for testing.')
    steps {
        shell('echo "Hello from Job DSL!"')
    }
}
