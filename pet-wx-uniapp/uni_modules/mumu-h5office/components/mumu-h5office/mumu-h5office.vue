<template>
	<view class="preview" v-if="isShow">
		<template v-if="src">
			<!-- #ifdef APP -->
			<web-view :src="src" @message="webMess"></web-view>
			<!-- #endif -->
			<!-- #ifdef H5 -->
			<iframe
				id="if"
				:src="src"
				align="top"
				frameborder="0"
				name="frameEditor"
				allowfullscreen=""
				onmousewheel=""
				allow="autoplay; camera; microphone; display-capture; clipboard-write;"
				style="position: fixed; overflow: hidden;"
			></iframe>
			<!-- #endif -->
		</template>
		<!-- 		<template v-else>
			{{ tps }}
		</template> -->
	</view>
</template>
<script>
import { FILE_TYPES, BASE_URL } from './config.js'
import { hex_md5 } from './md5.js'
import {
	browserFileToMd5,
	browserUploadFile,
	request,
	plus_io_getFileInfo,
	plus_uploader,
	aesEncrypt,
	colorRGBtoHex
} from './tool.js'

export default {
	model: {
		prop: 'isShow',
		event: 'change'
	},
	props: {
		isShow: {
			type: Boolean,
			default: false
		},
		appid: {
			type: String
		},
		// #ifdef APP-PLUS
		file: {
			type: [String],
			default: ''
		},
		// #endif
		// #ifdef H5
		file: {
			type: [String, File],
			default: ''
		},
		// #endif
		fileName: {
			type: String,
			default: ''
		},
		privilege: {
			type: Array,
			default: () => ['copy', 'download', 'print']
		}
	},
	watch: {
		isShow: {
			async handler() {
				this.src = ''
				if (!this.isShow) return
				if (!this.appid) return this.showErrorMsg('请填写appid')
				this.$emit('create')

				let params = {
					fileUrl: '',
					fileName: '',
					fileExit: '',
					digest: '',
					type: '',
					privilege: this.privilege,
					appid: this.appidKey
				}

				if (typeof this.file === 'string') {
					if (this.file.indexOf('http://') === 0 || this.file.indexOf('https://') === 0) {
						// 走网络文件
						const res = this.getHttpFileInfo(this.file, this.fileName)
						if (typeof res === 'string') return this.showErrorMsg(res)
						else
							params = {
								...params,
								...res
							}
					} else if (this.file.indexOf('md5::') === 0) {
						// app 外部上传
						const res = await this.getAppFileInfoToMd5(this.file)
						if (typeof res === 'string') return this.showErrorMsg(res)
						else
							params = {
								...params,
								...res
							}
					} else {
						// 走 app  内部本地上传
						try {
							const res = await this.getAppFileInfo(this.file)
							if (typeof res === 'string') return this.showErrorMsg(res)
							else
								params = {
									...params,
									...res
								}
						} catch (e) {
							return this.showErrorMsg('本地文件异常 001:' + e.msg ?? e.message)
						}
					}
				} else {
					// 走浏览器本地上传
					try {
						if (this.file.lastModifiedDate) {
							const res = await this.getInputFileInfo(this.file)
							if (typeof res === 'string') return this.showErrorMsg(res)
							else
								params = {
									...params,
									...res
								}
						} else {
							return this.showErrorMsg('本地文件异常 002')
						}
					} catch (e) {
						return this.showErrorMsg('本地文件异常 001:' + e.msg ?? e.message)
					}
				}
				this.src = this.paseUrl(params)
			},
			immediate: true
		},
		appid: {
			handler() {
				this.appidKey = aesEncrypt(this.appid)
			},
			immediate: true
		},
		src: {
			handler(newVal) {
				if (newVal) {
					this.$emit('created')
				}
			}
		}
	},
	mounted() {
		// #ifdef H5
		this.addH5Event()
		// #endif
	},
	data() {
		return {
			src: '',
			tps: '文件读取中...',
			appidKey: ''
		}
	},
	methods: {
		closePre() {
			this.$emit('change', false)
			//this.$emit('update:openLocalFile', '')
			this.src = ''
		},
		paseUrl(param) {
			param['host'] = 'app'
			// #ifdef H5
			param['host'] = origin
			// #endif
			param = encodeURIComponent(JSON.stringify(param))
			return BASE_URL + '?param=' + param
		},
		getHttpFileInfo(src, fileName) {
			if (!fileName) {
				let path = src.split('/')
				if (!path.length) return '传入文件路径格式不正确或者文件名称后缀不正确'
				fileName = path.pop()
			}
			let exit = fileName.split('.')
			if (!exit.length) return '传入文件名称未添加后缀类型'
			exit = exit.pop()
			if (!FILE_TYPES.includes(exit)) return '传入文件格式不正确或者文件名称后缀不正确'
			return {
				fileUrl: src,
				fileName: fileName,
				fileExit: exit,
				digest: hex_md5(src),
				type: 'src'
			}
		},
		async getInputFileInfo(fileLocal) {
			try {
				const fileName = fileLocal.name
				let exit = fileName.split('.')
				if (!exit.length) return '传入文件名称未添加后缀类型'
				exit = exit.pop()
				if (!FILE_TYPES.includes(exit)) return '传入文件格式不正确或者文件名称后缀不正确'
				const fileMd5 = await browserFileToMd5(fileLocal)
				const fileInfo = {
					size: fileLocal.size,
					fileMd5: fileMd5,
					appid: this.appidKey
				}
				const checkRes = await request({
					url: '/checkFileInfo',
					type: 'POST',
					data: fileInfo
				})

				if (checkRes.msg === 0) {
					const res = await browserUploadFile('/uploadLocalFile', fileLocal, {
						fileMd5: fileMd5,
						appid: this.appidKey,
						size: fileLocal.size,
						fileExit: exit,
						fileName: fileName + '（无缓存）',
						host: origin
					})
				}
				const fileUrl = 'http://localhost/' + fileMd5 + '.' + exit
				return {
					fileMd5: fileMd5,
					fileUrl: fileUrl,
					fileName: fileName,
					fileExit: exit,
					digest: hex_md5(fileUrl),
					type: 'localhost'
				}
			} catch (e) {
				throw e
			}
		},
		async getAppFileInfo(fileLocalPath) {
			try {
				let { size, digest: fileMd5 } = await plus_io_getFileInfo(fileLocalPath)
				fileMd5 = fileMd5.toLowerCase()
				const fileName = fileLocalPath.split('/').pop()
				let exit = fileName.split('.')
				if (!exit.length) return '传入文件名称未添加后缀类型'
				exit = exit.pop()
				if (!FILE_TYPES.includes(exit)) return '传入文件格式不正确或者文件名称后缀不正确'

				const fileInfo = {
					size: size,
					fileMd5: fileMd5,
					appid: this.appidKey
				}
				const checkRes = await request({
					url: '/checkFileInfo',
					type: 'POST',
					data: fileInfo
				})
				if (checkRes.msg === 0) {
					const res = await plus_uploader('/uploadLocalFile', fileLocalPath, {
						fileMd5: fileMd5,
						appid: this.appidKey,
						size: size + '',
						fileExit: exit,
						fileName: fileName + '（无缓存）',
						host: 'app'
					})
				}

				const fileUrl = 'http://localhost/' + fileMd5 + '.' + exit
				return {
					fileMd5: fileMd5,
					fileUrl: fileUrl,
					fileName: fileName,
					fileExit: exit,
					digest: hex_md5(fileUrl),
					type: 'localhost'
				}
			} catch (e) {
				console.log(e, 'err')
				throw e
			}
		},
		getAppFileInfoToMd5(fileLocalPath) {
			const fileName = fileLocalPath.replace('md5::', '')
			let exit = fileName.split('.')
			if (!exit.length) return '传入文件名称未添加后缀类型'
			const fileMd5 = exit[0]
			exit = exit.pop()
			if (!FILE_TYPES.includes(exit)) return '传入文件格式不正确或者文件名称后缀不正确'

			const fileUrl = 'http://localhost/' + fileMd5 + '.' + exit
			return {
				fileMd5: fileMd5,
				fileUrl: fileUrl,
				fileName: fileName,
				fileExit: exit,
				digest: hex_md5(fileUrl),
				type: 'localhost'
			}
		},

		addH5Event() {
			window.addEventListener(
				'message',
				e => {
					if (e.data && typeof e.data === 'string') {
						const data = JSON.parse(e.data)
						this.childEvent(data)
					}
				},
				false
			)
		},
		webMess(e) {
			this.childEvent(e.detail.data[0])
		},
		childEvent(data) {
			if (data.type && data.type === 'webOffice') {
				switch (data.event) {
					case 'close':
						this.closePre()
						this.$emit('close')
						break
					case 'error':
						this.showErrorMsg(data.msg)
						break
					case 'download':
						this.$emit('download', data.msg)
						break
					case 'init':
						let params = JSON.parse(data.msg)
						params.backgroundColor = colorRGBtoHex(params.backgroundColor)
						this.$emit('init', params)
						break
					case 'inited':
						let paramss = JSON.parse(data.msg)
						paramss.backgroundColor = colorRGBtoHex(paramss.backgroundColor)
						this.$emit('inited', paramss)
						break
					case 'readFiled':
						this.$emit('readFiled', data.msg)
						break
				}
			}
		},
		showErrorMsg(msg) {
			this.$emit('error', msg)
			uni.showModal({
				title: '系统异常！',
				content: msg,
				showCancel: false,
				success: () => {
					this.closePre()
				}
			})
		}
	}
}
</script>
<style lang="scss" scoped>
.preview {
	position: fixed;
	top: var(--window-top);
	bottom: 0;
	left: 0;
	right: 0;
	z-index: 100;
	background-color: #fff;

	.error {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		color: #ccc;
	}

	.close {
		position: absolute;
		top: 5px;
		right: 10px;
		background-color: #ccc;
		color: #fff;
		padding: 5px 10px;
		border-radius: 5px;
		font-size: 12px;
		z-index: 102;
	}

	#if {
		position: relative;
		z-index: 101;
		width: 100%;
		height: calc(100% - var(--window-top));
	}
}
</style>
