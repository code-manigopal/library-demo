//checkStatus - used to check the current status of various stages
String checkStatus(String stageName){
	index = 0
	status = "success"
	if(stageName.equals("upload")){
		index = 1
	}
	if(stageName.equals("deploy")){
		index = 2
	}
	while(!status.equals("success") || !status.equals("skipped") || !status.equals("failed")){
	    response= sh(script: 'curl --globoff --header "PRIVATE-TOKEN: imG-N5uTqD2BEmc_5hvz" https://cloudlab.us.oracle.com/api/v4/projects/8270/pipelines/'+ PIPELINE_ID + '/jobs',returnStdout: true ).trim()
		result = jsonParser(response)
		ids=result.id
		statuses=result.status
		stages=result.stage
		if(statuses.get(index).equals(status)){
		    println("Current " + stageName +" Status: " + statuses.get(index))
			break;
		}
		if(statuses.get(index).equals("skipped")){
			status = "skipped"
			println("Current " + stageName +" Status: " + statuses.get(index))
			currentBuild.result = 'FAILURE'
			break;
		}
		if(statuses.get(index).equals("failed")){
			status = "failed"
			println("Current " + stageName +" Status: " + statuses.get(index))
			currentBuild.result = 'FAILURE'
			break;
		}
		println("Current " + stageName +" Status: " + statuses.get(index))
		println("!!!!!! Waiting For " + stageName +" Stage to Complete !!!!!!!!")
		sh(script: 'sleep 30')
	}
	return response
}