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
				if (process.platform == "linux"||process.platform == "darwin") {
					child_process.exec("export APPETIZE_URL=" + output.publicURL, (error,stdout,stderr)=>{
						console.log(error)
						console.log(stdout)
						console.log(stderr)
					})
					process.env.APPETIZE_URL = output.publicURL
					console.log("(LINUX/OSX) APPETIZE_URL setted to " + output.publicURL)
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
deploy()
