<template>
	<view class="appGetFile" id="appGetFile"></view>
</template>

<script>
import { BASE_URL } from './config.js'
import { aesEncrypt } from './tool.js'

var wv //计划创建的webview
let inter
export default {
	props: {
		appid: {
			type: String
		},
		currentWebview: {
			type: Object
		},
		btnStyle: {
			type: String,
			default: 'font-size: 14px;background-color: aqua;'
		},
		content: {
			type: String,
			default: '<button>按钮</button>'
		},
		isShow: {
			type: Boolean,
			default: true
		}
	},
	watch: {
		appid: {
			handler() {
				this.appidKey = aesEncrypt(this.appid)
			},
			immediate: true
		},
		isShow: {
			handler(newVal) {
				wv.setVisible(newVal)
			}
		}
	},
	mounted() {
		// #ifdef APP-PLUS
		const params = {
			btnStyle: this.btnStyle,
			content: this.content
		}

		this.$nextTick(() => {
			wv = plus.webview.create('', 'custom-webview', {
				cachemode: 'noCache'
			})
			plus.globalEvent.addEventListener('plusMessage', e => {
				if (wv && wv['__uuid__'] === e.originId) {
					this.childEvent(e.data.args.data.arg)
				}
			})

			wv.loadURL(this.BASE_URL + this.API + '?params=' + encodeURIComponent(JSON.stringify(params)))
			this.currentWebview.append(wv)
			this.setPosition()

			setTimeout(() => {
				wv.evalJS("getAppidKey({type:'event',appidKey:'" + this.appidKey + "'})")
			}, 500)
		})
		// #endif
	},

	data() {
		return {
			BASE_URL: BASE_URL,
			API: '/appWebViewUploadFile',
			appidKey: '',
			top: 100
		}
	},
	methods: {
		// appGetFileClick() {
		//   wv.evalJS("onGetFile({type:'event',appidKey:'" + this.appidKey + "'})")
		// },

		childEvent(data) {
			if (data.type && data.type === 'webOffice') {
				switch (data.event) {
					case 'error':
						//this.showErrorMsg(data.msg)
						this.$emit('error', data.msg)
						break
					case 'success':
						this.$emit('success', data.msg)
						break
					case 'readFile':
						this.$emit('readFile', data.msg)
						break
					case 'complete':
						this.$emit('complete', data.msg)
						break
				}
			}
		},
		showErrorMsg(msg) {
			uni.showModal({
				title: '系统异常！',
				content: msg,
				showCancel: false,
				success: () => {
					//this.closePre()
				}
			})
		},
		setPosition() {
			inter && clearInterval(inter)
			const h = uni.getSystemInfoSync().screenHeight - uni.getSystemInfoSync().windowHeight
			inter = setInterval(() => {
				const query = uni.createSelectorQuery()
				query.select('#appGetFile').boundingClientRect()
				query.selectViewport().scrollOffset()
				query.exec(res => {
					const dome = res[0]
					wv.setStyle({
						top: h + dome.top,
						left: dome.left,
						height: dome.height,
						width: dome.width
					})
				})
			}, 15)
		}
	}
}
</script>

<style>
.appGetFile {
	width: 200px;
	height: 80px;
}
</style>
