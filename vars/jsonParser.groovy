import groovy.json.JsonSlurperClassic
//jsonParser - used to parse the JSON and turn it to map
Object jsonParser(String response){
    def slurper = new groovy.json.JsonSlurperClassic()
    result = slurper.parseText(response)
    return result
}
