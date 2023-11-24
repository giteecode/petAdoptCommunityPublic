<template>
	<view class="content">
		<view class="top-view">
			<view class="swiper-view">
				<swiper class="swiper" indicator-dots autoplay interval="2000" duration="800">
					<swiper-item class="swiper-item" v-for="(item, index) in carouselList" :key="index">
						<view class="image-wrapper">
							<image :src="item" mode="aspectFill"></image>
						</view>
					</swiper-item>
				</swiper>
			</view>

			<view class="label-view">
				<view class="label" v-for="(item, index) in labelList" :key="index" @click="labelSelect(index)">
					<image mode="aspectFill" :src="item.pic"></image>
					</br>
					<text>{{item.title}}</text>
				</view>
			</view>

			<view class="cut-line"></view>

			<view @click="gotolingyangxieyi" class="notice">{{notice}}</view>

			<!-- 选择城市 -->
			<view class="city-view" @click="showCity = true">
				<image src="https://img0.baidu.com/it/u=3165573562,1012927251&fm=26&fmt=auto" mode="aspectFill"></image>
				{{city}}
			</view>
			<chose-city @selectCity="selectCity" v-if="showCity" @closeModal="closeModal"></chose-city>
		</view>

		<view class="content-view" v-for="(item, index) in dataList" :key="index" @click="toDetail(item)">
			<view style="float: left;">
				<image :src="item.pictures[0]" class="content-img" mode="aspectFill"></image>
			</view>
			<view class="is-finish" v-if="item.isFinish == '0'">
				已被领养
			</view>
			<view class="content-content">
				<view class="logo" v-if="item.isFinish == '1'">{{"等待领养"}}</view>
				<text style="margin-top: -30rpx;">{{item.nickname}}，{{item.age}}</text>
				<text class="content-text"
					style="margin-top: 26upx; font-size: 24rpx; color: #778288;height: 70rpx;">{{item.content}}</text>
				<view class="note">
					<text v-if="item.physicalCondition[0]"
						style="background-color: #368BE3;">{{item.physicalCondition[0]}}</text>
					<text v-if="item.physicalCondition[1]"
						style="background-color: #70AF48;">{{item.physicalCondition[1]}}</text>
					<text v-if="item.physicalCondition[2]"
						style="background-color: #F08080;">{{item.physicalCondition[2]}}</text>
				</view>
			</view>
		</view>
		<view class="cue-words" v-if="dataList.length == 0">没有更多数据</view>
		<view class="addIcon">
			<image src="../../static/add.png" @click="toAdd()"></image>
		</view>
		<view style="height: 80rpx;"></view>
	</view>

</template>

<script>
	// 高德地图
	import amap from '@/components/amap-wx.130.js'
	// 选择城市组件
	import choseCity from "@/components/chose-city/chose-city"
	import http from '@/api/backend.js'
	export default {
		components: {
			choseCity
		},
		data() {
			return {
				pageNum: 1,
				pageSize: 10,
				share:{
					title: '喵喵宠物圈',
					path: '/pages/home/index',
					imageUrl: '',
					desc: '',
					content: '这是宠物圈'
				},
				showCity: false,
				carouselList: [
					"https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/18/oM67KEiYr0h0a34bc1c59b78e6bda084f39bdcb791be.jpg",
					"https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/18/A09cYlkHe2XO69e414a7bf5d0c7a6dd68f2eeddc5ca6.jpg"
				],
				labelList: [{
						title: "签到",
						pic: "https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/18/1JfgFJOmVSwP431fe5edc4643560a1025920ec8bb55b.png"
					},
					{
						title: "科普",
						pic: "https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/18/CvWNIKLEKLIW1f9c74c6bdfc4cd20d67e0299849375d.png"
					},
					{
						title: "领养流程",
						pic: "https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/18/BZNVjw6os7xgb2eeab0b4741d01d476d85f9c860ce62.png"
					},
					{
						title: "答题星球",
						pic: "https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/18/NKo7E5uFfNwl89cff846ce64b86f71aa9a85a1078035.png"
					}
				], 
				notice: "建议大家签订领养协议，点此下载协议模板",
				amapPlugin: null,
				key: '1f7cda077d0ba1353fde5548a5c44b8a',
				city: "全国",
				dataList: [],
				// 微信小商店
				appId: "wx683e5e1ea8b68aac",
				urlPath: "/pages/index/index"
			}
		},
		onShow() {
			this.dataList = [] 
			this.queryList(1, 10, "", "") 
		},
		onReachBottom() {
			this.pageNum++;
			this.queryList(this.pageNum, this.pageSize, "", "")
		},
		onLoad() {
			// 控制tabber显示隐藏
			http.getFlag().then(res => {
				if(res.data) {
					uni.hideTabBar();
				}
			})
			// 自动获取位置
			this.amapPlugin = new amap.AMapWX({
				key: this.key
			});
			this.getRegeo()
		},
		onPullDownRefresh() {
			this.dataList = []
			this.queryList(1, 10, "", "")
			this.city = "全国"
			uni.stopPullDownRefresh()
		},
		methods: {
			queryList(pageNum, pageSize, city, updateTime) {
				const obj = {
					pageNum: pageNum,
					pageSize: pageSize,
					city: city,
					updateTime: updateTime
				}
				http.queryPetAdopts(obj).then(res => {
					this.dataList = this.dataList.concat(res.data)
				})
			},
			getRegeo() {
				uni.showLoading({
					title: '获取位置信息中'
				});
				this.amapPlugin.getRegeo({
					success: (data) => {
						this.addressName = data[0].name;
						this.province = data[0].regeocodeData.addressComponent.province.replace(/市$/, ''),
						this.city = data[0].regeocodeData.addressComponent.city.replace(/市$/, '')
						uni.hideLoading();	
						
						this.pageNum=1;
						this.pageSize=10;
						this.queryList(this.pageNum, this.pageSize, this.city, "")
					},
					fail: (res) => {
						console.log(res)
						uni.showToast({
							title: '获取位置信息失败，请检查是否开启权限！',
							duration: 2000
						});
						uni.hideLoading();
					}
				});
			},
			toDetail(item) {
				uni.navigateTo({
					url: `/pages/home/detail?id=${item.id}`
				});
			},
			gotolingyangxieyi(){
				uni.downloadFile({
					url: 'https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/26/Xlt653Ffr2hD5fcddebc201e87ed6a2f44082a737365.doc', 
					success: (data) => {
						if (data.statusCode === 200) {
							uni.saveFile({ //文件保存到本地
								tempFilePath: data.tempFilePath, //临时路径
								success: function(res) {
								  console.log("下载成功"+res.savedFilePath)
								  console.log(JSON.stringify(res))
								  uni.showToast({
									icon: 'none',
									mask: true,
									title: '文件已保存!', 
									duration: 3000,
								  });
								  uni.openDocument({
									fileType: 'docx',
									filePath: res.savedFilePath,
									success: function(res) {
									  console.log('打开文档成功');
									}
								  });
								}
							  });
						}
					}
				});
			},
			toAdd() {
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
				uni.navigateTo({
					url: `/pages/home/add?openId=${openId}`
				});
			},
			// 点击标签
			labelSelect(e) {
				if (e == 0) {
					uni.navigateTo({
						url: '/pages/other/card'
					});
				}
				if (e == 1) {
					uni.navigateTo({
						url: '/pages/cyclopedia/index'
					});
				}
				if (e == 2) {
					uni.navigateTo({
						url: '/pages/path/adoptPath'
					});
				}
				if (e == 3) {
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
					// 跳转答题页面
					uni.navigateTo({
						url: `/pages/other/answer?userId=${openId}`
					});
				}
			},
			// 选择城市
			selectCity(item) {
				this.dataList = []
				this.city = item.name
				this.showCity = false
				this.queryList(1, 10, item.name, "")
			},
			closeModal() {
				this.showCity = false
			}
		}
	}
</script>

<style lang="scss">
	.content {
		padding: 15rpx;

		&_list {
			display: flex;
			flex-direction: row;
			align-items: center;
			justify-content: space-around;

			&_lable {}

			&_content {
				flex: 1;
				width: auto;
				text-align: right;
			}
		}
	}

	.swiper {
		height: 400rpx;
	}
	
	.swiper-view {
	}

	.swiper-view .swiper .swiper-item image {
		width: 100%;
	}

	.label-view {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-top: 40rpx;

		.label {
			text-align: center;
			font-size: $uni-font-size-base;
		}

		image {
			width: 120rpx;
			height: 120rpx;
			border-radius: 20rpx;
		}
	}

	.cut-line {
		width: 100%;
		height: 6rpx;
		background-color: #efefef;
		margin-top: 20rpx;
	}

	.notice {
		width: 100%;
		height: 10rpx;
		line-height: 10rpx;
		background-color: #FFFAF0;
		margin-top: 20rpx;
		border-radius: 10rpx;
		padding: 30rpx;
		font-size: $uni-font-size-sm;
	}

	.content-view {
		width: 100%;
		height: 300upx;
		box-shadow: 0 0 9px 3px #cee1eb;
		margin: 0 auto;
		border-radius: 20upx;
		margin-top: 30rpx;
		position: relative;
		.content-img {
			width: 250upx;
			height: 240upx;
			margin-left: 22upx;
			margin-top: 40upx;
			border-radius: 10upx;
		}
		.is-finish{
			position: absolute;
			left: 50upx;
			top: 50upx;
			color: #FFFFFF;
			width: 150upx;
			height: 150upx;
			border-radius: 80upx;
			border: 12upx solid #FFFFFF;
			font-weight: 600;
			transform: rotate(-45deg);
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.content-content {
			display: flex;
			flex-direction: column;
			height: 300upx;
			justify-content: center;
			padding-left: 30upx;

			.logo {
				width: 120rpx;
				height: 50rpx;
				background-color: #fca400;
				position: relative;
				left: 300rpx;
				top: -52rpx;
				border-radius: 0 20upx 0 20upx;
				font-size: 24rpx;
				text-align: center;
				line-height: 50rpx;
				color: #FFFFFF;
			}

			// 文字超出...
			.content-text {
				text-overflow: -o-ellipsis-lastline;
				overflow: hidden;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-line-clamp: 2;
				line-clamp: 2;
				-webkit-box-orient: vertical;
			}

			.note {
				margin-top: 4rpx;

				text {
					font-size: $uni-font-size-sm;
					margin: 10rpx;
					padding-left: 8rpx;
					padding-right: 8rpx;
					border-radius: 30rpx;
					color: #ffffff;
				}
			}
		}
	}

	.city-view {
		font-size: 28rpx;
		margin-top: 12rpx;
		padding-left: 10rpx;
		display: flex;
		align-items: center;

		image {
			width: 30rpx;
			height: 30rpx;
		}
	}

	.addIcon {
		position: fixed;
		bottom: 60rpx;
		right: 40rpx;

		image {
			width: 70rpx;
			height: 70rpx;
		}
	}

	.cue-words {
		text-align: center;
		font-size: 28rpx;
		color: #778288;
		margin-top: 12rpx;
	}
</style>
