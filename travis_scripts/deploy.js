console.log("DEPLOY: Deploying...")
var fs = require("fs")
var child_process = require("child_process")
function deploy() {
	child_process.exec(`bash -c \'curl https://${process.env.APPETIZE_TOKEN}@api.appetize.io/v1/apps` +
		` -F "file=@./app/build/outputs/apk/debug/app-debug.apk"` +
		` -F "platform=android"\'`,
		(error, stdout, stderr) => {
			if (error == null) {
				var output = JSON.parse(stdout)
				console.log(output.publicURL)
				console.log("DEPLOY: Deployed!")
				if (process.env.CI == "true") {
					console.log("DEPLOY: CI")
					if (process.platform == "linux" || process.platform == "darwin") {
						fs.writeFile(__dirname + "/appetize_url.sh", "#!/bin/bash\nexport APPETIZE_URL='" + output.publicURL.toString()+"'", (err) => {
							if (err == null) {
								console.log("DEPLOY: Open appetize_url.sh");
							} else {
								console.log("DEPLOY: Error while saving appetize_url")
							}
						})
					}
				}
			} else {
				console.error("DEPLOY: ERROR:")
				console.error(error)
				console.error(stderr)
			}
		})

}
deploy()
