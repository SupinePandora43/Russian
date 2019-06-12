console.log("Deploying...")
var child_process = require("child_process")
child_process.exec(`bash -c \'curl https://${process.env.APPETIZE_TOKEN}@api.appetize.io/v1/apps` +
	` -F "file=@./app/build/outputs/apk/debug/app-debug.apk"` +
	` -F "platform=android"\'`,
	function (error, stdout, _stderr) {
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
		}
	})
