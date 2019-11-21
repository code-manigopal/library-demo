//getLogs - used to get Logs from the GitLab Pipeline jobs
String getLogs(String stageName, String response){
	result = jsonParser(response)
	ids=result.id
	statuses=result.status
	stages=result.stage
	index = 0
	if(stageName.equals("upload")){
		index = 1
	}
	if(stageName.equals("deploy")){
		index = 2
	}
	logs = sh(script: 'curl --globoff --header "PRIVATE-TOKEN: imG-N5uTqD2BEmc_5hvz" https://cloudlab.us.oracle.com/api/v4/projects/8270/jobs/'+ ids.get(index) + '/trace',returnStdout: true ).trim()
	return logs
}
