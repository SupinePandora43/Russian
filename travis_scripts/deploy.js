console.log("Deploying...")
var child_process = require("child_process")
function deploy(){
	child_process.exec(`bash -c \'curl https://${process.env.APPETIZE_TOKEN}@api.appetize.io/v1/apps` +
	` -F "file=@./app/build/outputs/apk/debug/app-debug.apk"` +
	` -F "platform=android"\'`,
	(error, stdout, stderr) => {
		if (error == null) {
			var output = JSON.parse(stdout)
			console.log(output.publicURL)
			console.log("Deployed!")
			if (process.env.CI == "true") {
				console.log("CI")
				if (process.platform == "linux") {
					child_process.exec("export APPETIZE_URL=" + output.publicURL)
					console.log("(LINUX) APPETIZE_URL setted to " + output.publicURL)
				} else if (process.platform == "darwin") {
					child_process.exec("setenv APPETIZE_URL=" + output.publicURL)
					console.log("(OSX) APPETIZE_URL setted to " + output.publicURL)
				} else if (process.platform == "win32") {
					child_process.exec("set APPETIZE_URL=" + output.publicURL)
					console.log("(WIN32) APPETIZE_URL setted to " + output.publicURL)
				}
			}
		} else {
			console.error("ERROR:")
			console.error(error)
			console.error(stderr)
		}
	})

}
if(process.env.CI == "true"){
	var commit_name = process.env.TRAVIS_COMMIT_MESSAGE
	if(commit_name.match(/^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(-(0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(\.(0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*)?(\+[0-9a-zA-Z-]+(\.[0-9a-zA-Z-]+)*)?$/).length>=1){
		deploy()
	}
}else{
	deploy()
}
