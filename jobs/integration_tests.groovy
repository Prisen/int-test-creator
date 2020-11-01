folder('integration-tests') {
    displayName('Integration tests')
    descriptions('Integration test jobs configured through JobDSL')
}

pipelineJob('integration-tests/_create-integration-tests') {
    description('Run this job to create integration test jobs')
    definition {
        cps {
            sandbox()
            scripts(readFileFromWorkspace('scripts/create_int_test_jobs.groovy'))
        }
    }
}

