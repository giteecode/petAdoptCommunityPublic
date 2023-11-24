<template>
	<view class="">
		<view class="swiper-view">
			<swiper class="swiper" indicator-dots autoplay interval="2000" duration="800">
				<swiper-item class="swiper-item" v-for="(item, index) in detailObj.pictures" :key="index">
					<view class="image-wrapper" style="height: 800rpx;">
						<image :src="item" mode="aspectFill" @click="previewImage(item)"></image>
					</view>
				</swiper-item>
			</swiper>
		</view>

		<view class="content">

			<view class="title">{{detailObj.title}}</view>
			<view class="report" @click="toReport">
				<text>举报</text>
			</view>
			<view class="time-view">
				<text @click="getToLocation" style="color: #007AFF;">📍{{detailObj.city}}</text>
				<text>浏览量：{{detailObj.view}}</text>
				<text>更新：{{detailObj.updateTime}}</text>
			</view>

			<view class="desc-view">
				<view>
					<text>姓名：{{detailObj.nickname}}</text>
					<text>性别：{{detailObj.sex == "1" ? "男孩" : "女孩"}}</text>
					<text>年龄：{{detailObj.age}}</text>
				</view>
				<view>
					<text v-if="detailObj.physicalCondition[0]"
						style="background-color: #368BE3;color: #FFFFFF;">{{detailObj.physicalCondition[0]}}</text>
					<text v-if="detailObj.physicalCondition[1]"
						style="background-color: #70AF48;color: #FFFFFF;">{{detailObj.physicalCondition[1]}}</text>
					<text v-if="detailObj.physicalCondition[2]"
						style="background-color: #F08080;color: #FFFFFF;">{{detailObj.physicalCondition[2]}}</text>
				</view>
			</view>
			<view class="story-title">特别提醒</view>
			
			<view class="story">
				1.未见到宠物却以任何名义（包括运费）要求打款的，请提高警惕（切勿异地领养）<br/>
				2.涉及有偿领养，一律下架<br/>
				3.送养需谨慎<br/>
				4.坚持异地送养/领养的，请确认对方是否实名<br/>
			</view>
			
			<view class="story-title">领养说明</view>
			
			<view class="story">
				您可能很想领养一直宠物，但是实际可能和您想象的不一样，所以请您务必做好充足准备
				<!-- <view class="lysm-title">请认真阅读一下内容:</view> -->
				<view class="lysm-content">
					1.如果不是你本人有意愿养宠物，以后宠物不是由你来负责饲养，请不要申请领养。<br>
					2.你是否愿意留出资金用于它们的生活，和可能得病需要的治疗费。<br>
				</view>
			</view>

			<view class="story-title">TA的故事</view>

			<view class="story">{{detailObj.content}}</view>

			<view class="condition-title">领养条件</view>

			<view class="condition">
				<view v-for="(item, index) in detailObj.conditions" :key="index">· {{item}}</view>
			</view>

		</view>

		<view class="user-title">送养人信息</view>

		<view class="user-view">

			<view class="user-text">
				<image :src="user.avatarUrl" @click="toUser(user.openId)"></image>
				<view class="user-info">
					<view class="nickname">{{user.nickname}}</view>
					<view class="is-real-name">{{user.authenticated ? "已实名" : "未实名"}}</view>
<!-- 					<view class="num-view">
						<text style="color: #666666;margin-right: 8rpx;">正在送养 </text><text
							class="doing-num">{{user.doingNum}}</text>
						<text style="color: #666666;margin-left: 20rpx;margin-right: 8rpx;">已送养 </text><text
							class="done-num">{{user.doingNum}}</text>
					</view> -->
				</view>
				<view class="collect-view" v-if="isFollowUser" @click="unFollow">
					<image class="collect-img" src="../../static/focus.png" v-if="openId != user.openId"></image>
					<text v-if="openId != user.openId">已关注</text>
				</view>
				<view class="collect-view" v-if="!isFollowUser" @click="follow">
					<image v-if="openId != user.openId" class="collect-img" src="../../static/un_focus.png"></image>
					<text v-if="openId != user.openId">关注</text>
				</view>
			</view>

			<view class="user-btn">
				<button @click="chat()">在线咨询</button>
				<image src="../../static/phone.png" @tap="copy(detailObj.phone)"></image>
				<image src="../../static/wx.png" @tap="copy(user.wx)"></image>
			</view>
		</view>

		<!-- 撑高度 -->
		<view style="height: 240rpx;"></view>

		<view class="bottom-view">
			<view class="index-view" @click="toHome()">
				<image src="../../static/tabber/home.png"></image>
				</br>
				<text>返回首页</text>
			</view>
			<view class="love-view" v-if="!isCollect" @click="collectClick">
				<image src="../../static/un_collect.png"></image>
				</br>
				<text>收藏</text>
			</view>
			<view class="love-view" v-if="isCollect" @click="removeCollectClick">
				<image src="../../static/collect.png"></image>
				</br>
				<text>已收藏</text>
			</view>
			<button class="confirm-btn" :disabled="detailObj.isFinish === '0'"  @click="applys"  >{{detailObj.isFinish !== "0"?'申请领养':'已被领养'}}</button>
		</view>

	</view>

</template> 

<script>
	import http from '@/api/backend.js'
	export default {
		data() {
			return {
				detailObj: {},
				user: {},
				openId: '',
				isFollowUser: false, // 是否关注
				isCollect: false
			}
		},
		onLoad(option) {
			const userInfo = uni.getStorageSync('userInfo')
			this.openId = userInfo.openId
			this.getAdopt(option.id)
			setTimeout(() => {
				this.isFollow()
				this.isAlreadyCollect()
			}, 500)
		},
		methods: {
			applys(){
				if(!this.openId){
					uni.showToast({
						title: '请先登录！',
						duration: 2000,
						icon: 'none'
					});
					return
				}
				uni.navigateTo({url: `/pages/userinfo/userinfo?openId=${this.openId}&adoptId=${this.detailObj.id}`});
			},
			toHome() {
				uni.switchTab({
					url: '/pages/home/index'
				});
			},
			getAdopt(id) {
				let obj = {
					adoptId: id
				}
				http.getPetAdopt(obj).then(res => {
					this.detailObj = res.data.petAdoptVO
					this.user = res.data.wxUserVO
				})
			},
			// 复制文本
			copy(item) {
				if(item == null || item == undefined) {
					uni.showToast({
						title: '送养人未绑定信息',
						icon: 'none'
					})
				}
				uni.setClipboardData({
					data: item,
					success: () => {
						uni.showToast({
							title: '复制成功'
						})
					}
				});
			},
			previewImage(item) {
				uni.previewImage({
					current: item,
					urls: this.detailObj.pictures
				});
			},
			toUser(e) {
				uni.navigateTo({url: `/pages/user/user?openId=${e}`});
			},
			chat() {
				if(this.openId == '' || this.openId == undefined) {
					uni.showToast({
						title: '请先去登录哦',
						icon: 'none'
					})
					return
				}
				let userId = this.user.openId
				if(this.openId == userId) {
					uni.showToast({
						title: '无法与自己聊天哦',
						icon: 'none'
					})
					return
				}
				uni.navigateTo({
					url: `/pages/chat/detail?toId=${userId}`
				});
			},
			// 查询是否关注
			isFollow() {
				if(this.openId == '' || this.openId == undefined) {
					return
				}
				http.isFollow({userId: this.openId,followId: this.user.openId}).then(res => {
					this.isFollowUser = res.data
				})
			},
			// 关注
			follow() {
				if(this.openId == '' || this.openId == undefined) {
					uni.showToast({
						title: '登录后才能关注哦',
						icon: 'none'
					})
					return
				}
				const params = {
					userId: this.openId,
					followId: this.user.openId
				}
				http.addUserFollow(params).then(res => {
					if(res.data) {
						this.isFollowUser = true
					}
				})
			},
			// 取消关注
			unFollow() {
				const params = {
					userId: this.openId,
					followId: this.user.openId
				}
				http.unUserFollow(params).then(res => {
					if(res.data) {
						this.isFollowUser = false
					}
				})
			},
			// 添加收藏
			collectClick() {
				if(this.openId == '' || this.openId == undefined) {
					uni.showToast({
						title: '登录后才能收藏哦',
						icon: 'none'
					})
					return
				}
				const param = {
					userId: this.openId,
					fromId: this.detailObj.id,
					type: 'ADOPT'
				}
				http.addCollect(param).then(res => {
					if(res.success) {
						this.isCollect = true
						uni.showToast({title: '收藏成功', icon: 'none'})
					}else {
						uni.showToast({title: res.message, icon: 'error'})
					}
				})
			},
			// 取消收藏
			removeCollectClick() {
				const param = {
					userId: this.openId,
					fromId: this.detailObj.id,
					type: 'ADOPT'
				}
				http.removeCollect(param).then(res => {
					if(res.success) {
						this.isCollect = false
					}
				})
			},
			// 是否收藏
			isAlreadyCollect() {
				if(this.openId == '' || this.openId == undefined) {
					return
				}
				const param = {
					userId: this.openId,
					fromId: this.detailObj.id,
					type: 'ADOPT'
				}
				http.isCollect(param).then(res => {
					if(res.success) {
						if(res.data) {
							this.isCollect = false
						}else {
							this.isCollect = true
						}
					}
				})
			},
			getToLocation() {
				this.viewMapLocation('120.210825', '30.209049', '测试地址')
			},
			// 导航
			viewMapLocation(longitude, latitude, address) {
				//  这里因为我是子组件传参过来了导致 经纬度变成了String 类型所以进行了一次转换
				latitude = Number(latitude);
				longitude = Number(longitude);
				// 获取定位信息
				uni.getLocation({
					type: 'wgs84', //返回可以用于uni.openLocation的经纬度
					// 用户允许获取定位
					success: function(res) {
						console.log(res, '经纬度')
						if (res.errMsg == "getLocation:ok") {
							console.log(latitude)
							console.log(longitude)
							uni.openLocation({
								// 传入你要去的纬度
								latitude: latitude,
								// 传入你要去的经度
								longitude: longitude,
								// 传入你要去的地址信息 不填则为空
								address: address,
								// 缩放大小
								scale: 18,
								success: function() {
									console.log('success');
								}
							});
						}
					},
					// 用户拒绝获取定位后 再次点击触发
					fail: function(res) {
						console.log(res)
						if (res.errMsg == "getLocation:fail auth deny") {
							uni.showModal({
								content: '检测到您没打开获取信息功能权限，是否去设置打开？',
								confirmText: "确认",
								cancelText: '取消',
								success: (res) => {
									if (res.confirm) {
										uni.openSetting({
											success: (res) => {
												console.log('确定');
											}
										})
									} else {
										console.log('取消');
										return false;
									}
								}
							})
						}
					}
				});
			},
			toReport(){
				uni.navigateTo({url: `/pages/report/report?openId=${this.openId}`});
			},
		}
	}
</script>

<style lang="scss">
	.content {
		padding: 20rpx;
		// padding-bottom: 60rpx;
	}
	.report{
		color: #F8A323;
		margin-left: auto;
		font-size: 20rpx;
		width:50rpx
	}
	.swiper-view .swiper {
		height: 700rpx;

		.image-wrapper {

			image {
				width: 100%;
				height: 100%;
			}
		}
	}

	.title {
		// padding: 20rpx;
	}

	.time-view {
		margin-top: 20rpx;
		margin-left: -20rpx;

		text {
			font-size: 22rpx;
			color: #778288;
			margin: 20rpx;
		}
	}

	.desc-view {
		width: 95%;
		height: 120rpx;
		border: 4rpx solid #ffaa7f;
		margin: 0 auto;
		padding: 15rpx;
		margin-top: 30rpx;

		view {
			margin: 10rpx;

			text {
				margin: 8rpx;
				font-size: 24rpx;
				padding-left: 8rpx;
				padding-right: 8rpx;
				border-radius: 30rpx;
			}
		}

	}

	.story-title {
		margin-top: 30rpx;
		height: 40rpx;
		border-left: 6rpx solid #F58418;
		padding-left: 20rpx;
		color: #F58418;
	}

	.user-title {
		margin-top: 30rpx;
		height: 40rpx;
		border-left: 6rpx solid #F58418;
		padding-left: 20rpx;
		color: #F58418;
		margin-left: 20rpx;
	}

	.story {
		margin-top: 20rpx;
		font-size: 24rpx;
		.lysm-title{
			margin-top: 10rpx;
			margin-left: 20rpx;
			font-size: 30rpx;
			color: #F58418;
		}
		.lysm-content{
			margin-top: 20rpx;
			font-size: 24rpx;
		}
	}

	.condition-title {
		margin-top: 30rpx;
		height: 40rpx;
		border-left: 6rpx solid #F58418;
		padding-left: 20rpx;
		color: #F58418;
	}

	.condition {
		margin-top: 20rpx;
		font-size: 24rpx;

		view {
			margin-top: 10rpx;
		}
	}

	.bottom-view {
		width: 100%;
		height: 112rpx;
		display: flex;
		// justify-content: space-between;
		align-items: center;
		padding: 20rpx;
		// 固定在底部
		position: fixed;
		bottom: 0;
		background: #FFFFFF;
		border-top: 1px solid #e5e5e5;

		.love-view,
		.index-view {
			float: left;
			text-align: center;
			font-size: 25rpx;
			margin: 30rpx;

			image {
				width: 50rpx;
				height: 50rpx;
			}
		}

		.confirm-btn {
			float: left;
			border-radius: 40rpx;
			background-color: #F8A323;
			width: 400rpx;
			height: 84rpx;
			line-height: 84rpx;
			margin: 20rpx;
			color: #FFFFFF;
			font-size: 28rpx;
		}
	}

	.user-view {
		width: 85%;
		height: 250rpx;
		margin: 0 auto;
		box-shadow: 0 0 9px 3px #eaeaea;
		border-radius: 10rpx;
		padding: 30rpx;
		margin-top: 30rpx;

		.title {
			float: left;
			font-size: 30rpx;
		}

		.collect-view {
			float: right;
			font-size: 26rpx;
			display: flex;

			.collect-img {
				width: 45rpx;
				height: 45rpx;
			}

			text {
				align-self: center;
				margin-left: 10rpx;
			}
		}

		.user-text {
			clear: both;
			height: 144rpx;

			image {
				width: 100rpx;
				height: 100rpx;
				float: left;
				border-radius: 100rpx;
			}

			.user-info {
				float: left;
				margin-left: 20rpx;

				.is-real-name {
					font-size: 20rpx;
					border: 2rpx solid green;
					padding: 2rpx;
					width: 80rpx;
					color: green;
					border-radius: 14rpx;
					text-align: center;
					margin-top: 10rpx;
				}

				.num-view {
					margin-top: 10rpx;
					font-size: 24rpx;

					.doing-num {
						color: #ff5500;
					}

					.done-num {

						color: #ff5500;
					}
				}
			}

		}

		.user-btn {
			clear: both;
			margin-top: 30rpx;

			button {
				width: 300rpx;
				height: 74rpx;
				line-height: 74rpx;
				float: left;
				font-size: 28rpx;
				border-radius: 40rpx;
				background: #FF7E93;
				color: #FFFFFF;
			}

			image {
				width: 60rpx;
				height: 60rpx;
				margin-left: 40rpx;
			}
		}
	}
</style>
