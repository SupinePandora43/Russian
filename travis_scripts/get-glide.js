var child_process = require("child_process")
var fs = require("fs")
var dirPath = "./app/libs/"
var fileNames = [
	"compiler-4.7.0.jar",
	"glide-full-4.7.0.jar",
	"glide-gifencoder-integration-4.7.0.jar",
	"annotation-4.7.0.jar"
]
fileNames.forEach(file => {
	fs.access(dirPath + file, fs.constants.F_OK, (err) => {
		if (err) {
			child_process.exec(`bash -c 'wget -P ${dirPath} "https://github.com/bumptech/glide/releases/download/v4.7.0/${file}"'`,
				(error, _stdout, stderr) => {
					if (error) {
						console.error("GLIDE: ERROR while downloading " + file)
						console.error(error)
						console.error(stderr)
					} else {
						console.log("GLIDE: Downloaded " + file)
					}
				}
			)
		} else {
			console.log("GLIDE: Exists " + file)
		}
	})
});
