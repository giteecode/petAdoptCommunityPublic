<template>
	<view class="">
		<view class="content">
			<view class="title">{{detailObj.title}}</view>

			<view class="time-view">
				<text>更新：{{detailObj.updateTime}}</text>
				<text style="margin-left: 40rpx;">浏览量：{{detailObj.view}}</text>
				<text style="margin-left: 30rpx;color: #F58418;width:50rpx" @click="toReport">举报</text>
			</view>

			<view class="desc-view">
				<view>
					<text style="color: #888;">姓名：</text>{{detailObj.nickname}}
					<text style="color: #888;margin-left: 100rpx;">性别：</text>{{detailObj.sex == "1" ? "男孩" : "女孩"}}
					<text style="color: #888;margin-left: 100rpx;">年龄：</text>{{detailObj.age}}
				</view>
				<view>
					<text style="color: #888;">类型：</text>{{detailObj.type == "1" ? "狗狗" : "猫咪"}}
					<text style="color: #888;margin-left: 100rpx;">品种：</text>{{detailObj.breed}}
				</view>
			</view>

			<view class="story-title">走失区域</view>

			<view class="story">{{detailObj.city}} {{detailObj.address}}</view>

			<view class="user-title">发布者信息</view>

			<view class="user-view">
				<view class="user-text">
					<image :src="user.avatarUrl" @click="toUser(user.openId)"></image>
					<view class="user-info">
						<view class="nickname">{{user.nickname}}</view>
						<view class="is-real-name">{{user.authenticated ? "已实名" : "未实名"}}</view>
					</view>
					<view class="collect-view" v-if="isFollowUser" @click="unFollow">
						<image class="collect-img" src="../../static/focus.png" v-if="openId != user.openId"></image>
						<text v-if="openId != user.openId">已关注</text>
					</view>
					<view class="collect-view" v-if="!isFollowUser" @click="follow">
						<image class="collect-img" src="../../static/un_focus.png" v-if="openId != user.openId"></image>
						<text v-if="openId != user.openId">关注</text>
					</view>
				</view>

				<view class="user-btn">
					<button @click="chat()">与我联系</button>
					<image src="../../static/phone.png" @tap="copy(detailObj.phone)"></image>
					<image src="../../static/wx.png" @tap="copy(user.wx)"></image>
				</view>
			</view>

			<view class="content-title">详情</view>

			<view class="content-content">
				<view>{{detailObj.content}}</view>
				<image mode="aspectFill" v-for="(item, index) in detailObj.pictures" :key="index" :src="item"
					@click="previewImage(item)"></image>
			</view>

		</view>

		<view style="font-size: 28rpx;color: #444444;margin-top: 20rpx;padding: 20rpx;clear: both;">评论 {{commentCount}}</view>
		<!-- 父评论 -->
		<view class="comment-view" v-for="(item, index) in comments" :key="index" v-if="item.parentId == null">
			<image mode="aspectFill" :src="item.avatarUrl" @click="toUser(item.ownerId)"></image>
			<view class="comment-text">
				<view style="color: #666666;">
					<text style="color: #FFFFFF;" class="owner-text" v-if="petCircle.userId == item.ownerId">作者</text>
					{{item.nickname}}
				</view>
				<view @click="reply(item, null)">{{item.content}} <text>{{item.createTime}}</text></view>
			</view>
			<!-- 子评论 -->
			<view class="comment-child" v-for="(i, ind) in commentsChild" :key="ind" v-if="item.id == i.parentId">
				<image mode="aspectFill" :src="i.avatarUrl" @click="toUser(i.ownerId)"></image>
				<view style="float: left;">
					<view style="color: #666666;">
						<text style="color: #FFFFFF;" class="owner-text" v-if="petCircle.userId == i.ownerId">作者</text>
						{{i.nickname}}
					</view>
					<view @click="reply(i, item)">{{i.content}} <text>{{i.createTime}}</text></view>
				</view>
			</view>
			<!-- 三级评论 -->
			<view class="comment-child" v-for="(i, ind) in commentsChild" :key="ind" v-if="item.id == i.grandId">
				<image mode="aspectFill" :src="i.avatarUrl" @click="toUser(i.ownerId)"></image>
				<view style="float: left;">
					<view style="color: #666666;">
						<text style="color: #FFFFFF;" class="owner-text" v-if="petCircle.userId == i.ownerId">作者</text>
						{{item.nickname}}
					</view>
					<view @click="reply(i, item)">@{{i.parentName}} {{i.content}} <text>{{i.createTime}}</text></view>
				</view>
			</view>
		</view>
		<view class="cue-words" v-if="comments.length == 0">快来抢沙发呀!</view>

		<!-- 撑高度 -->
		<view style="height: 240rpx;"></view>

		<view class="bottom-view">
			<view class="input-view">
				<view style="float: left;width: 570rpx;">
					<input v-model="commentContent"
						style="background-color: #e5e5e5;padding: 10rpx;font-size: 28rpx;border-radius: 10rpx;padding-left: 20rpx;"
						type="text" placeholder="留下你的线索！" />
				</view>
				<view style="float: right;height: 40rpx;margin-left: 20rpx;">
					<button
						style="background-color: #F58418;height: 60rpx;line-height: 60rpx;font-size: 28rpx;color: #FFFFFF;" @click="submit(null, null)">发送</button>
				</view>

			</view>
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
				type: 'FIND',
				comments: [],
				commentsChild: [],
				commentContent: "",
				commentCount: 0
			}
		},
		onLoad(option) {
			const userInfo = uni.getStorageSync('userInfo')
			this.openId = userInfo.openId
			this.getPetFind(option.id)
			this.queryComments(option.id, this.type)
			this.queryCommentCount(option.id, this.type)
			setTimeout(() => {
				this.isFollow()
			}, 500)
		},
		methods: {
			toReport(){
				uni.navigateTo({url: `/pages/report/report?openId=${this.openId}`});
			},
			toHome() {
				uni.switchTab({
					url: '/pages/home/index'
				});
			},
			getPetFind(id) {
				let obj = {
					adoptId: id
				}
				http.getPetFind(obj).then(res => {
					this.detailObj = res.data.petFindVO
					this.user = res.data.wxUserVO
				})
			},
			// 复制文本
			copy(item) {
				if (item == null || item == undefined) {
					uni.showToast({
						title: '发布者未绑定信息',
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
					urls: this.detailObj.pictures,
					// loop: true   // 是否可循环预览
				});
			},
			toUser(e) {
				console.log(e)
				uni.navigateTo({
					url: `/pages/user/user?openId=${e}`
				});
			},
			chat() {
				if (this.openId == '' || this.openId == undefined) {
					uni.showToast({
						title: '请先去登录哦',
						icon: 'none'
					})
					return
				}
				let userId = this.user.openId
				if (this.openId == userId) {
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
				if (this.openId == '' || this.openId == undefined) {
					return
				}
				http.isFollow({
					userId: this.openId,
					followId: this.user.openId
				}).then(res => {
					this.isFollowUser = res.data
				})
			},
			// 关注
			follow() {
				if (this.openId == '' || this.openId == undefined) {
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
					if (res.data) {
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
					if (res.data) {
						this.isFollowUser = false
					}
				})
			},
			// 查询评论
			queryComments(fromId, type) {
				http.queryComments({
					fromId: fromId,
					type: type
				}).then(res => {
					if (res.data != null) {
						this.commentsChild = []
						this.comments = res.data
						this.comments.map(item => {
							if (item.parentId != null) {
								this.commentsChild.push(item)
							}
						})
					}
				})
			},
			// 查询数量
			queryCommentCount(fromId, type) {
				http.queryCommentCount({
					fromId: fromId,
					type: type
				}).then(res => {
					this.commentCount = res.data
				})
			},
			// 发表评论
			submit(parentId, grandId) {
				const userInfo = uni.getStorageSync('userInfo')
				let openId = userInfo.openId
				if (openId === undefined) {
					uni.showToast({
						title: '您未登录，请前往登录',
						icon: 'none',
						duration: 1500
					});
					setTimeout(function() {
						uni.switchTab({
							url: '/pages/user/my',
						});
					}, 1500)
					return;
				}
				if (this.commentContent == "") {
					uni.showToast({
						title: '请输入评论内容',
						icon: 'none'
					})
					return
				}
				const params = {
					parentId: parentId,
					grandId: grandId,
					ownerId: openId,
					formId: this.detailObj.id,
					content: this.commentContent,
					type: this.type
				}
				http.addComment(params).then(res => {
					if (res.data == 1) {
						uni.showToast({
							title: "发布成功",
							icon: "success"
						})
						this.queryComments(this.detailObj.id, this.type)
						this.queryCommentCount(this.detailObj.id, this.type)
						this.commentContent = ""
						return
					}
					uni.showToast({
						title: "接口请求异常",
						icon: error
					})
				})
			},
			// 回复评论
			reply(item, parent) {
				let _self = this
				const userInfo = uni.getStorageSync('userInfo')
				// 本人评论(可删除)
				if (item.ownerId == userInfo.openId) {
					uni.showModal({
						title: '确定要删除吗',
						success: function(res) {
							if (res.confirm) {
								http.deleteComment({
									id: item.id
								}).then(re => {
									if (re.data == 1) {
										uni.showToast({
											title: '删除成功',
											icon: 'success'
										})
										_self.queryComments(_self.detailObj.id, _self.type)
										_self.queryCommentCount(_self.detailObj.id, _self.type)
										return
									}
									uni.showToast({
										title: "接口请求异常",
										icon: error
									})
								})
							}
						}
					})
				} else {
					// 回复评论
					uni.showModal({
						title: '评论',
						confirmText: '回复',
						editable: true,
						placeholderText: '回复@' + item.nickname,
						success: function(res) {
							if (res.confirm) {
								_self.commentContent = res.content
								_self.submit(item.id, parent != null ? parent.id : null)
							}
						}
					});
				}
			}
		}

	}
</script>

<style lang="scss">
	.content {
		padding: 20rpx;
		// padding-bottom: 60rpx;
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

		text {
			font-size: 22rpx;
			color: #778288;
		}
	}

	.desc-view {
		width: 95%;
		height: 100rpx;
		border: 4rpx solid #ffaa7f;
		margin: 0 auto;
		padding: 15rpx;
		margin-top: 30rpx;

		view {
			margin: 10rpx;
			font-size: 24rpx;

			text {
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
	}

	.story {
		margin-top: 20rpx;
		font-size: 24rpx;

	}

	.content-title {
		margin-top: 30rpx;
		height: 40rpx;
		border-left: 6rpx solid #F58418;
		padding-left: 20rpx;
		color: #F58418;
	}

	.content-content {
		margin-top: 20rpx;
		font-size: 24rpx;


		view {
			font-size: 26rpx;
			margin-bottom: 16rpx;
			letter-spacing: 1px;
		}

		image {
			width: 100%;
		}
	}

	uni-image {
		width: 100%;
	}

	.bottom-view {
		width: 100%;
		height: 80rpx;
		display: flex;
		align-items: center;
		// 固定在底部
		position: fixed;
		bottom: 0;
		background: #FFFFFF;
		border-top: 1px solid #e5e5e5;
		padding-top: 20rpx;
		padding-bottom: 20rpx;

		.input-view {
			margin: 0 auto;
		}

	}

	.user-view {
		width: 85%;
		height: 220rpx;
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

	.comment-view {
		margin-top: 10rpx;
		padding: 20rpx;

		.owner-text {
			margin-right: 16rpx;
			color: #FFFFFF;
			font-size: 24rpx;
			background-color: #ffaa00;
			padding-left: 6rpx;
			padding-right: 6rpx;
			border-radius: 10rpx;
		}

		image {
			width: 60rpx;
			height: 60rpx;
			border-radius: 100rpx;
			float: left;
			margin-right: 20rpx;
		}

		.comment-text {


			view {
				font-size: 26rpx;
			}

			text {
				font-size: 22rpx;
				color: #7a7a7a;
				margin-left: 10rpx;
			}
		}

		.comment-child {
			display: flex;
			margin-left: 80rpx;
			margin-top: 20rpx;

			view {
				font-size: 26rpx;
			}

			text {
				font-size: 22rpx;
				color: #7a7a7a;
				margin-left: 10rpx;
			}
		}
	}
	.cue-words {
		text-align: center;
		font-size: 28rpx;
		color: #778288;
		margin-top: 12rpx;
	}
</style>
