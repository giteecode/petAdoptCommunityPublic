import baseUrl from './config.js'

export function getToken() {
	try {
		const token = uni.getStorageSync('Token');
		if (token) {
			return token
		} else {
			return ''
		}
	} catch (e) {
		console.log(e)
	}
}

module.exports = {
	config: function(name) {
		var info = null;
		if (name) {
			var name2 = name.split("."); //字符分割
			if (name2.length > 1) {
				info = configdata[name2[0]][name2[1]] || null;
			} else {
				info = configdata[name] || null;
			}
			if (info == null) {
				let web_config = cache.get("web_config");
				if (web_config) {
					if (name2.length > 1) {
						info = web_config[name2[0]][name2[1]] || null;
					} else {
						info = web_config[name] || null;
					}
				}
			}
		}
		return info;
	},
	post: function(url, data, xAuthToken = true) {
		url = baseUrl + url;
		let header = {
			"content-type": "application/json"
		}
		if (xAuthToken) {
			header = {
				"content-type": "application/json",
				"X-Auth-Token": getToken()
			}
		}
		return new Promise((succ, error) => {
			uni.request({
				url: url,
				data: data,
				method: "POST",
				header: header,
				success: function(result) {
					succ.call(self, result.data)
				},
				fail: function(e) {
					error.call(self, e)
				}
			})
		})
	},
	put: function(url, data, xAuthToken = true) {
		url = baseUrl + url;
		let header = {
			"content-type": "application/json"
		}
		if (xAuthToken) {
			header = {
				"content-type": "application/json",
				"X-Auth-Token": getToken()
			}
		}
		return new Promise((succ, error) => {
			uni.request({
				url: url,
				data: data,
				method: "PUT",
				header: header,
				success: function(result) {
					succ.call(self, result.data)
				},
				fail: function(e) {
					error.call(self, e)
				}
			})
		})
	},
	get: function(url, data, xAuthToken = true) {
		url = baseUrl + url;
		let header = {
			"content-type": "application/x-www-form-urlencoded"
		}
		if (xAuthToken) {
			header = {
				"content-type": "application/x-www-form-urlencoded",
				"X-Auth-Token": getToken()
			}
		}
		return new Promise((succ, error) => {
			uni.request({
				url: url,
				data: data,
				method: "GET",
				header: header,
				success: function(result) {
					succ.call(self, result.data)
				},
				fail: function(e) {
					error.call(self, e)
				}
			})
		})
	},
	// 小程序不支持多张图片上传
	uploadFile: function(url, data) {
		url = baseUrl + url;
		let n = 1;
		return new Promise((succ, error) => {
			let imgArr = []
			data.map((item) => {
				uni.uploadFile({
					url: url,
					filePath: item,
					name: 'file',
					success: function(result) {
						console.log('上传第' + (n++) + '张图片...')
						imgArr.push(JSON.parse(result.data).data)
						succ.call(self, imgArr)
					},
					fail: function(e) {
						error.call(self, e)
					}
				})
			})
		})
	}
	
}
