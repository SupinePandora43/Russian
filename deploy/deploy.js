console.log("Deploying...")
var child_process = require("child_process")
child_process.exec(`bash -c \'curl https://${process.env.APPETIZE_TOKEN}@api.appetize.io/v1/apps` +
	` -F "file=@./app/build/outputs/apk/debug/app-debug.apk"` +
	` -F "platform=android"\'`,
	function (error, stdout, _stderr) {
		if(error == null){
			var output = JSON.parse(stdout)
			console.log(output.publicURL)
			console.log("Deployed!")
		}else{
			console.error("ERROR:")
			console.error(error)
		}
	})
