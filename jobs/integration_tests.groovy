folder('integration-tests') {
    displayName('Integration tests')
    description('Integration test jobs configured through JobDSL')
}

pipelineJob('integration-tests/_create-integration-tests') {
    description('Run this job to create integration test jobs')
    definition {
        cps {
            sandbox()
            script(readFileFromWorkspace('scripts/create_int_test_jobs.groovy'))
        }
    }
}

