<template>
	<view>
		<view class="grace-idcard-main">
			<view class="grace-idcard-desc">
				拍摄时，注意光线和焦距，务必保持照片清晰
				<view class="pink-font">仅用于资料审核，不会对外显示</view>
			</view>
			<view class="grace-idcard-text">
				身份证照片 ( 正面 )
			</view>
			<view class="grace-idcard-items">
				<view class="grace-idcard-uper-btn" @tap="selectImg1">
					<view class="img"><image src="../../static/imgs/realname/camera.png" mode="widthFix" /></view>
					<view class="text">拍摄或选择照片</view>
				</view>
				<view class="grace-idcard-preview">
					<image :src="idCard1"  @tap="previewImg1" mode="widthFix"></image>
				</view>
			</view>
			<view class="grace-idcard-text">
				身份证照片 ( 背面 )
			</view>
			<view class="grace-idcard-items">
				<view class="grace-idcard-uper-btn" @tap="selectImg2">
					<view class="img"><image src="../../static/imgs/realname/camera.png" mode="widthFix" /></view>
					<view class="text">拍摄或选择照片</view>
				</view>
				<view class="grace-idcard-preview">
					<image :src="idCard2" @tap="previewImg2" mode="widthFix" />
				</view>
			</view>
			<view>
				<view  class="font-weight">为什么要实名认证：</view>
				1.实名认证后将获得认证标志<br/>
				2.实名认证有利于用户建立信任联系<br/>
				3.实名认证将更好的规范双方行为，发布的信息更具有参考性
			</view>
			<view style="margin-top:38upx;">
				<button type="primary" @tap="uploadCards">提交认证</button>
			</view>
		</view>
	</view>
</template>
<script>
import http from '@/api/backend.js'
var _self;
export default {
	data() {
		return {
			openId:"",
			idCard1 : '../../static/imgs/realname/idcard1.png',
			idCard2 : '../../static/imgs/realname/idcard2.png'
		};
	},
	onLoad:function(e){
		_self = this;
		this.openId = e.openId;
		const userInfo = uni.getStorageSync('userInfo');
	},
	methods: {
		selectImg1 : function() {			
			var serverUrl = this.serverUrl;
			uni.chooseImage({
				count: 1,
				sizeType: ['original', 'compressed'],
				success: function(res) {
					let img = res.tempFiles[0]
					if(img.size > 10458760) {
						uni.showToast({
							title: "图片过大",
							icon: 'error'
						});
						return
					}
					uni.showLoading({title: "上传中"});
					http.uploadFile(res.tempFilePaths).then(picRes => {
						_self.idCard1 = picRes[0]
						uni.hideLoading()
						uni.showToast({
							title: "上传成功",
							icon: 'success'
						});
					})
				}
			});
		},
		selectImg2 : function() {
			var serverUrl = this.serverUrl;
			uni.chooseImage({
				count: 1,
				sizeType: ['original', 'compressed'],
				success: function(res) {
					let img = res.tempFiles[0]
					if(img.size > 10458760) {
						uni.showToast({
							title: "图片过大",
							icon: 'error'
						});
						return
					}
					uni.showLoading({title: "上传中"});
					http.uploadFile(res.tempFilePaths).then(picRes => {
						_self.idCard2 = picRes[0]
						uni.hideLoading()
						uni.showToast({
							title: "上传成功",
							icon: 'success'
						});
					})
				}
			});
		},
		previewImg1 : function(){
			uni.previewImage({
				urls:[_self.idCard1]
			});
		},
		previewImg2 : function(){
			uni.previewImage({
				urls:[_self.idCard2]
			});
		},
		uploadCards : function(){
			if(this.idCard1 == '../../static/imgs/realname/idcard1.png' || this.idCard2 == '../../static/imgs/realname/idcard2.png'){
				uni.showToast({title:"请选择身份证照片", icon:"none"});
				return;
			}
			// 因底层限制一次上传一个
			uni.showLoading({title:"提交认证..."});
			// 上传正面
			let params = {
				"openId": this.openId,
				"idcardFrontPic": this.idCard1,
				"idcardBackPic": this.idCard2
			}
			http.realname(params).then(res => {
				uni.hideLoading();
				if(res.success) {
					uni.showToast({
						title: '认证成功',
						icon: 'success',
						duration: 2000
					})
					uni.navigateBack({
						delta:1,
					})
					return;
				}
				uni.showToast({
					title: '提交不成功',
					icon: 'error',
					duration: 2000
				})
			})
			
		}
	},
}
</script>
<style>
view{font-size:28upx;}
.grace-idcard-main{margin:20upx 30upx;}
.grace-idcard-desc{line-height:2em; background:#FFFFFF; padding:[20upx,20upx,5upx,20upx]; border-radius:10upx;}
.grace-idcard-text{line-height:2em; margin-top:30upx;}
.grace-idcard-items{background:#FFFFFF; padding:30upx 0; display:flex; margin:30upx 0; border-radius:10upx; align-items: flex-start;}
.grace-idcard-uper-btn{width:276upx; margin:0 60upx; background:#F1F1F1; padding-bottom:10upx; border-radius:10upx; text-align:center;}
.grace-idcard-uper-btn .img{width:100upx; height:100upx; margin:0 auto; margin-top:30upx;}
.grace-idcard-uper-btn .img image{width:100upx; height:100upx;}
.grace-idcard-uper-btn .text{width:100%; margin-top:10upx; text-align:center; line-height:2em;}
.grace-idcard-preview{width:50%; margin:0 30upx;}
.grace-idcard-preview image{width:100%;}
.font-weight{
	font-weight: 600;
	font-size: 18px;
}
.pink-font{
	color: pink;
}
</style>