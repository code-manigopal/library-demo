//triggerPipeline - used to trigger the Gitlab pipeline using Curl command.
void triggerPipeline(String encoded_k8s_command){
    response = sh(script: 'curl -v -k --noproxy "*" -X POST -F token=${TOKEN} -F ref=${ref} -F "variables[OST_ENVIRONMENT]=${OST_ENVIRONMENT}" -F "variables[CD_UTIL_OPERATION]=test" -F "variables[ADHOC_CMD]='+ encoded_k8s_command +'" https://cloudlab.us.oracle.com/api/v4/projects/8270/trigger/pipeline', returnStdout: true).trim()
    println("Response: \n---------\n" + response)
    result = jsonParser(response)
    PIPELINE_ID=result.id
    println("PIPELINE ID: " + PIPELINE_ID)
}