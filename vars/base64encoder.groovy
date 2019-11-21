//base64Encoder - used to convert the k8s command to base64 format
String base64Encoder(String data) {
  writeFile file: "raw.txt", text: "${data}"
  def encodedData = sh(script: "base64 -w 0 raw.txt", returnStdout: true)
  sh("rm -f raw.txt")
  return encodedData
}