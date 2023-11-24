<template>
	<view class="content">
		<view class="top-img-view"></view>
		<view class="main-view">
			<view class="my-view">
				<view class="my-answer" @click="recordClick" v-if="isShow">我的答题</view>
				<view class="record" @click="winRecord" v-if="isShow">中奖记录</view>
			</view>
			<view class="title-view"> -- {{answer.type == undefined ? '喵喵宠物圈' : answer.type}} -- </view>
			<view class="subject-view">{{answer.subject}}</view>
			<view class="option-view">
				<button v-if="!isFinish" :type="item.btnType" v-for="(item, index) in answer.options" :key="index" @click="choose(item)">{{item.option}}</button>
				<button v-if="isFinish" :type="item.btnType" v-for="(item, index) in answer.options" :key="index" @click="un_choose">{{item.option}}</button>
			</view>
			<view class="loading-view" @click="loadingClick" v-if="answer == null">题目准备中，请稍候再来......</view>
			<view class="bottom-view">喵喵宠物圈</view>
		</view>
	</view>
</template>

<script>
	import http from '@/api/backend.js'
	export default {
		data() {
			return {
				userId: '',
				answer: {},
				isFinish: false, // 今日是否答题
				isShow: true,
				answerId: '',
				btnType: ''
			};
		},
		onLoad(option) {
			this.userId = option.userId
			this.answerId = option.answerId
			this.btnType = option.btnType
			this.getAnswer(option.answerId || '', option.btnType || '')
			setTimeout(()=>{
				if(this.answer != null && option.answerId == undefined) {
					this.isFinishToday()
				}
			}, 300)
		},
		onPullDownRefresh() {
			this.getAnswer(this.answerId || '', this.btnType || '')
			setTimeout(()=>{
				if(this.answer != null && this.answerId == undefined) {
					this.isFinishToday()
				}
			}, 500)
			uni.stopPullDownRefresh()
		},
		methods: {
			// 获取题目
			getAnswer(answerId, btnType) {
				http.getAnswer({answerId: answerId}).then(res => {
					if(res.data != null) {
						this.answer = res.data
						if(answerId != '' ) {
							this.isFinish = true
							this.answer.options.map(item => {
								if(btnType == '1') {
									if(item.option == this.answer.answer) {
										item.btnType = 'primary'
									}
								}else {
									if(item.option != this.answer.answer) {
										item.btnType = 'warn'
									}
								}
							})
							this.isShow = false
						}
						return
					}
					this.answer = null
					// uni.showToast({
					// 	title: '接口请求异常',
					// 	icon: 'error'
					// })
				})
			},
			// 选择
			choose(item) {
				if(item.option == this.answer.answer) {
					// 添加答题记录
					http.addAnswerRecord({userId: this.userId, answerId: this.answer.id, type: '1'}).then(res => {
						item.btnType = 'primary'
						uni.showToast({title: '答对啦', icon: 'none'})
						this.isFinish = true
					})
				}else {
					// 添加答题记录
					http.addAnswerRecord({userId: this.userId, answerId: this.answer.id, type: '2'}).then(res => {
						item.btnType = 'warn'
						uni.showToast({title: '答错了哦', icon: 'none'})
						this.isFinish = true
					})
				}
			},
			// 今日是否答题
			isFinishToday() {
				http.isFinishToday({userId: this.userId}).then(res => {
					// 未答题
					if(res.data == '0') {
						this.isFinish = false
					}
					// 答对
					if(res.data == '1') {
						this.isFinish = true
						this.answer.options.map(item => {
							if(item.option == this.answer.answer) {
								item.btnType = 'primary'
							}
						})
					}
					// 答错
					if(res.data == '2') {
						this.isFinish = true
						this.answer.options.map(item => {
							if(item.option != this.answer.answer) {
								item.btnType = 'warn'
							}
						})
					}
				})
			},
			recordClick() {
				uni.navigateTo({
					url: `/pages/other/answer-record?userId=${this.userId}`
				});
			},
			winRecord() {
				uni.showToast({title: '暂未开放', icon: 'none'})
			},
			loadingClick() {
				uni.showToast({title: '点我也没用', icon: 'none'})
			},
			un_choose() {
				uni.showToast({title: '已答过题了哦', icon: 'none'})
			}
		}
	}
</script>

<style lang="scss">
	page {
		height: 100%;
		background-color: #ececec;
	}
	.content {
		height: 100%;
		background-color: #ececec;
		padding-bottom: 40rpx;
	}
	
	.top-img-view {
		background-image: url(https://pet-life.oss-cn-beijing.aliyuncs.com/images/other/login_background.png);
		width: 100%;
		height: 360rpx;
	}
	
	.main-view {
		width: 92%;
		height: 1000rpx;
		margin: 0 auto;
		border-radius: 10rpx;
		margin-top: -40rpx;
		box-shadow: 0 0 2rpx 2rpx #FFFFFF;
		background-color: #FFFFFF;
		position: relative;
		
		.loading-view {
			text-align: center;
			font-size: 30rpx;
			color: #ffaa00;
		}
		
		.my-view {
			padding: 40rpx;
			font-size: 30rpx;
			
			.my-answer {
				float: left;
			}
			.record {
				float: right;
			}
		}
		
		.title-view {
			text-align: center;
			font-size: 28rpx;
			color: #acacac;
			margin-top: 20rpx;
			margin-bottom: 40rpx;
		}
		
		.subject-view {
			font-size: 32rpx;
			text-align: left;
			width: 80%;
			margin: 0 auto;
			line-height: 50rpx;
		}
		
		.option-view {
			margin-top: 200rpx;
			
			button {
				width: 80%;
				border-radius: 50rpx;
				// background-color: #e8e8e8;
				font-size: 30rpx;
				margin-top: 40rpx;
			}
		}
		
		.bottom-view {
			position: absolute;
			bottom: 20rpx;
			left: 40%;
			font-size: 28rpx;
			color: #bfbfbf;
		}
	}
</style>
