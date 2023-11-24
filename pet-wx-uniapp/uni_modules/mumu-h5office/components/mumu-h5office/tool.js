import FileMd5 from './index.umd.js'
import {
	BASE_URL,
	AES_KEY as aa,
	PUBLIC_KEY
} from './config.js'
import JSEncrypt from './jsencrypt.min.js'
import {
	hex_md5
} from './md5.js'



/** 浏览器获取文件 md5 */
export const browserFileToMd5 = (file) => {
	return new Promise((resove, reject) => {
		const bmf = new FileMd5()
		bmf.md5(file, (err, md5) => {
			if (err) reject(err)
			resove(md5)
		})
	})
}

/** 浏览器上传文件 */
export const browserUploadFile = (url, file, addData = {}) => {
	return new Promise((resove, reject) => {
		uni.uploadFile({
			url: BASE_URL + url,
			file,
			name: 'file',
			formData: addData,
			timeout: 1000 * 60 * 100,
			success({
				data
			}) {
				data = JSON.parse(data)
				if (data.code !== 200) reject(data)
				resove(data)
			},
			fail(err) {
				reject(err)
			}
		})
	})
}

/* 网络请求 */
export const request = (options = {
	type: 'GET',
	url: '',
	data: {}
}) => {
	return new Promise((resove, reject) => {
		uni.request({
			url: BASE_URL + options.url,
			method: options.type,
			data: options.data,
			success({
				data
			}) {
				if (data.code !== 200) reject(data)
				resove(data)
			},
			fail(err) {
				reject(err)
			}
		})
	})
}
//
/* 获取app文件信息 */
export const plus_io_getFileInfo = (filePath) => {
	return new Promise((resove, reject) => {
		plus.io.getFileInfo({
			filePath,
			digestAlgorithm: 'md5',
			success: res => {
				resove(res)
			},
			fail: (err) => {
				reject(err)
			}
		})
	})
}

/* app 文件上传 */
export const plus_uploader = (url, filePath, addData = {}) => {
	return new Promise((resove, reject) => {
		const task = plus.uploader.createUpload(BASE_URL + url, {
				method: "POST",
				priority: 0
			},
			function(res, status) {
				if (status !== 200) reject(res)
				res = JSON.parse(res.responseText)
				if (res.code !== 200) reject(res)
				resove(res)
			}
		);
		task.addFile(filePath, {
			key: "file"
		});
		for (let key in addData) {
			task.addData(key, addData[key]);
		}
		task.start();
	})
}

export const aesEncrypt = (value) => {
	const e = new JSEncrypt()
	e.setPublicKey(PUBLIC_KEY);
	const s = e.encrypt(value)
	return s
}

// rgb 转 16
export function colorRGBtoHex(color) {
	var rgb = color.split(',');
	var r = parseInt(rgb[0].split('(')[1]);
	var g = parseInt(rgb[1]);
	var b = parseInt(rgb[2].split(')')[0]);
	var hex = "#" + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
	return hex;
}
