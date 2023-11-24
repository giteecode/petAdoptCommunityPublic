<template>
	<view class="content">
		<view class="sign_conent_box">
			<view class="sign_conent">
				<view class="sign_conent_title">
					你已连续签到
					<span class="sign_conent_title_span">{{credits.dayNum}}</span>
					天
				</view>
				<view class="sign_list_aligns">
					<scroll-view class="scroll-view_H" scroll-x="true">
						<view class="scroll_view_items" v-for="(item, index) in sign_list" :key="index">
							<!--  -->
							<view class="scroll_view_itemsv">
								<view class="scroll_view_item">
									<view class="scroll_view_item_img_box">
										<!-- <image src="../../static/sign_bg.png" class="scroll_view_item_img" mode="">
										</image> -->
										<view class="scroll_view_item_top">{{ item.discount }}</view>
										<view class="scroll_view_item_bottom">积分</view>
									</view>
									<view class="scroll_view_item_tips">签到{{ item.day }}天</view>
								</view>
								<view class="scroll_xian" v-show="index !== sign_list.length - 1"></view>
							</view>
						</view>
					</scroll-view>
				</view>
				<view class="sign_conent_btn" v-if="!credits.isFinish" @click="updateCredits(openId, credits.creditsNum == null ? '' : credits.creditsNum)">今日签到</view>
				<view class="sign_conent_btn" style="background:  #cecece;" v-if="credits.isFinish">今日已签到</view>
			</view>
		</view>
	</view>
</template>

<script>
	import http from '@/api/backend.js'
	export default {
		data() {
			return {
				sign_list: [{
						discount: '10',
						day: '3'
					},
					{
						discount: '30',
						day: '7'
					},
					{
						discount: '50',
						day: '10'
					},
					{
						discount: '80',
						day: '20'
					},
					{
						discount: '100',
						day: '25'
					},
					{
						discount: '150',
						day: '30'
					}
				],
				credits: {},
				openId: ""
			};
		},
		onLoad() {
			const userInfo = uni.getStorageSync('userInfo')
			this.openId = userInfo.openId
			this.getCredits(userInfo.openId)
		},
		onPullDownRefresh() {
			uni.stopPullDownRefresh()
		},
		methods: {
			getCredits(openId) {
				// 未登录
				if (openId == null || openId == undefined) {
					console.log("未登录")
					openId = "000001"
				}
				const params = {openId: openId}
				http.getCredits(params).then(res => {
					this.credits = res.data
				})
			},
			updateCredits(openId, creditsNum) {
				if(openId == undefined || openId == null) {
					uni.showToast({
					  title: '您未登录，请前往登录',
					  icon: 'none',
					  duration: 1500
					});
					setTimeout(function(){
						uni.switchTab({
							url: '/pages/user/my',
						});
					}, 1500)
					return;
				}
				// 首次签到 积分为0
				if(creditsNum == "") {
					creditsNum = 0
				}
				const params = {
					openId: openId,
					creditsNum: creditsNum
				}
				http.updateCredits(params).then(res => {
					if(res.data == 1) {
						uni.showToast({title: '打卡成功', icon: 'success'});
						this.credits = {
							...this.credits,
							dayNum:this.credits?this.credits.dayNum + 1:1,
							isFinish:true
						}
						return
					}
					uni.showToast({title: '接口请求异常', icon: 'error'});
				})
			}
		}
	}
</script>

<style lang="scss">
	.content {
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding: 30rpx;
	}

	.sign_conent_box {
		width: 100%;
		display: flex;
		justify-content: center;
		margin-top: 0;

		.sign_conent {
			width: 686upx;
			background: white;
			border-radius: 20upx;
			padding: 40upx 32upx;
			box-sizing: border-box;
			background-image: url(https://img2.baidu.com/it/u=3629823039,2494968196&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=938);

			.sign_conent_title {
				width: 100%;
				font-size: 36upx;
				font-weight: bold;
				color: #333333;

				.sign_conent_title_span {
					color: #ff6526 !important;
				}
			}

			.sign_list_aligns {
				width: 100%;
				padding: 48upx 0 64upx 0;
				box-sizing: border-box;

				.scroll-view_H {
					width: 100%;
					display: flex;
					white-space: nowrap;

					.scroll_view_items {
						display: inline-block;
					}

					.scroll_view_itemsv {
						display: flex;
					}

					.scroll_view_item {
						.scroll_view_item_img_box {
							width: 108upx;
							height: 108upx;
							position: relative;
							border: 1upx solid #bf8d46;
							border-radius: 10upx;

							.scroll_view_item_top {
								font-size: 24upx;
								color: #bf8d46;
								font-weight: bold;
								position: absolute;
								top: 32upx;
								width: 100%;
								text-align: center;
								z-index: 1;
							}

							.scroll_view_item_bottom {
								font-size: 20upx;
								color: #cbb47d;
								position: absolute;
								bottom: 18upx;
								left: 36upx;
								z-index: 2;
							}
						}

						.scroll_view_item_tips {
							width: 108upx;
							text-align: center;
							font-size: 26upx;
							padding-top: 16upx;
							color: #333333;
						}

						.scroll_view_item_img {
							width: 108upx;
							height: 108upx;
							position: absolute;
							top: 0;
							left: 0;
							z-index: 0;
						}
					}

					.scroll_xian {
						width: 64upx;
						height: 2upx;
						background: #ffe4d9;
						margin-top: 52upx;
					}
				}
			}

			.sign_conent_btn {
				width: 100%;
				border-radius: 45upx;
				background: linear-gradient(270deg, #fcae3a 0%, #f15d25 100%);
				color: #ffffff;
				text-align: center;
				padding: 22upx 0;
				font-size: 32upx;
			}
		}
	}
</style>
