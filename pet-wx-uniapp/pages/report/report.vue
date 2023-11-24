<template>
	<view class="content">
		
		<view style="font-size: 28rpx;margin-top: 30rpx;clear: both;">姓名</view>
		<view class="titleView"><input type="text" v-model="report.name" placeholder="请输入您的姓名（最多20个字）" /></view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">联系电话</view>
		<view class="phoneView"><input type="number" maxlength="11" v-model="report.phone" placeholder="请输入手机号码" /></view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">微信</view>
		<view class="nicknameView"><input type="text" v-model="report.wechat" maxlength="20" placeholder="请输入你的微信（最多20个字）" /></view>
	
		<view style="font-size: 28rpx;margin-top: 30rpx;">举报内容</view>
		<view class="nicknameView"><textarea type="text" v-model="report.content" maxlength="150" placeholder="请输入举报内容（最多150个字）" /></view>
	
		<view style="font-size: 28rpx;">添加图片</view>
		<view style="font-size: 24rpx;margin-top: 6rpx;color: #999999;">(最多上传6张图片，单张图片大小不能超过10MB)</view>
		<view class="imgView" @click="choseImg()" v-if="imgArr.length === 0">
			<image src="../../static/upload.png"></image>
		</view>
		<!-- 预览图片 -->
		<view class="preImage" v-if="imgArr.length !== 0">
			<view v-for="(item, index) in imgArr" :key="index" style="float: left;margin-left: 20rpx;">
				<image mode="aspectFill" :src='item' @click="previewImg(item)"></image>
				<image @click="deleteImg(item)" style="width: 30rpx;height: 30rpx;margin-bottom: 130rpx;margin-left: -6rpx;" src="../../static/x.png"></image>
			</view>
		</view>
		<view class="bottom-view">
			<button class="confirm-btn" @click="submit">提交</button>
		</view>
	</view>
</template>

<script>
	import http from '@/api/backend.js'
	var _self;
	export default {
		data() {
			return {
				report:{
					name:"",
					phone:"",
					wechat:"",
					content:""
				},
				imgArr:[],
				openId:""
			}
		},
		onLoad(e) {
			this.openId = e.openId
		},
		methods: {
			choseImg() {
				var me = this;
				var serverUrl = this.serverUrl;
				uni.chooseImage({
					count: 6,
					sizeType: ['original', 'compressed'],
					success: function(res) {
						let arr = res.tempFiles
						for(let i = 0; i < arr.length; i++) {
							// 图片大小限制10MB
							if(arr[i].size > 10458760) {
								uni.showToast({
									title: "图片过大",
									icon: 'error'
								});
								return
							}
						}
						uni.showLoading({title: "上传中"});
						http.uploadFile(res.tempFilePaths).then(picRes => {
							me.report.pictures = picRes
							me.imgArr = picRes
							uni.hideLoading()
							uni.showToast({
								title: "上传成功",
								icon: 'success'
							});
						})
					}
				});
			},
			previewImg(item) {
				uni.previewImage({
					current: item,
					urls: this.imgArr
				})
			},
			deleteImg(item) {
				http.deletePic({picture: item}).then(res => {
					if(res.success) {
						let arr = this.imgArr
						this.imgArr = arr.filter(function(i) {
						    return i != item
						});
						return;
					}
					this.error()
				})
			},
			submit() {
				this.report.openId = this.openId;
				this.report.pictures = this.imgArr;
				if(!this.paramCheck()) return
				http.report(this.report).then(res => {
					if(res.success) { 
						uni.showToast({ 
							title: '提交成功！',
							icon: 'success',
							duration: 4000
						})
						setTimeout(function(){
							uni.navigateBack({
								delta:1,
							})
						}, 1500)
						return;
					}
					this.error()
				})
			},
			paramCheck() {
				if(this.report.openId == null || this.report.openId == "") {
					uni.showToast({title: '您还未登录',icon: 'none'});
					return false
				}
				if(this.imgArr == null || this.imgArr == "") {
					uni.showToast({title: '请上传图片',icon: 'none'});
					return false
				}
				if(this.report.name == null || this.report.name == "") {
					uni.showToast({title: '请填写姓名',icon: 'none'});
					return false
				}
				if(this.report.phone == null || this.report.phone == "") {
					uni.showToast({title: '请填写手机',icon: 'none'});
					return false
				}
				if(this.report.wechat == null || this.report.wechat == "") {
					uni.showToast({title: '请填写微信',icon: 'none'});
					return false
				}
				if(this.report.content == null || this.report.content == "") {
					uni.showToast({title: '请填写内容',icon: 'none'});
					return false
				}
				return true
		},
	},
}
</script>

<style lang="scss">
	.content {
		padding: 15rpx;
	}

	.imgView {
		width: 240rpx;
		height: 240rpx;
		background-color: #f1f1f1;
		display: flex;
		border-radius: 10rpx;
		margin-top: 20rpx;

		image {
			width: 80rpx;
			height: 80rpx;
			align-self: center;
			margin: 0 auto;
		}
	}
	.preImage {
		margin-left: -20rpx;
		margin-top: 20rpx;
		
		image {
			width: 160rpx;
			height: 160rpx;
			border-radius: 16rpx;
			
		}
		
		.addView {
			width: 160rpx;
			height: 160rpx;
			// background-color: #f1f1f1;
			display: flex;
			margin-left: 20rpx;
			
			image {
				width: 80rpx;
				height: 80rpx;
				align-self: center;
				margin: 0 auto;
			}
		}
	}

	.nicknameView,
	.phoneView,
	.titleView {
		margin-top: 20rpx;

		input {
			font-size: 24rpx;
		}
	}

	.typeView,
	.sexView,
	.bodyView,
	.ageView,
	.conditionView {
		margin-left: -20upx;
		margin-top: 20rpx;
	}

	.city-view {
		.city {
			display: inline-block;
			height: 50upx;
			line-height: 50upx;
			padding: 0 22upx;
			border-radius: 8upx;
			font-size: 22upx;
			box-sizing: border-box;
			margin-top: 20rpx;
			background: #f7f7f7;
		}
	}

	.contentView {

		textarea {
			margin-top: 20rpx;
			font-size: 24rpx;
			background-color: #f8f8f8;
			width: 90%;
			margin: 0 auto;
			padding: 20rpx;
			margin-top: 20rpx;
			border-radius: 10rpx;
		}
	}

	.bottom-view {
		width: 100%;
		height: 100rpx;
		display: flex;
		align-items: center;

		.confirm-btn {
			float: left;
			border-radius: 40rpx;
			background-color: #F8A323;
			width: 80%;
			height: 84rpx;
			line-height: 84rpx;
			color: #FFFFFF;
			font-size: 28rpx;
		}
	}
</style>
