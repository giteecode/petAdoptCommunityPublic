<template>
	<view>
		<view class="background-view">
			<view class="background-text">给小宠物们温暖的家！！</view>
		</view>

		<view class="card-view">
			<view class="card" @click="toNew">
				<view class="card-title">最新消息</view>
				<view class="card-content"></view>
			</view>
			<view class="card" @click="toAdd()">
				<view class="card-title">发布寻宠</view>
				<view class="card-content">丢失或捡到宠物，您可以在这里发布。</view>
			</view> 
		</view>

		<view class="content">
 
			<view class="filter-view">
				<!-- 选择城市 -->
				<view class="city-view" @click="showCity = true">
					<image src="https://img0.baidu.com/it/u=3165573562,1012927251&fm=26&fmt=auto" mode="aspectFill"></image>
					{{city}}
				</view>
				<chose-city @selectCity="selectCity" v-if="showCity" @closeModal="closeModal"></chose-city>
				<!-- 下拉组件 -->
				<hpy-form-select title='更新时间' :dataList="timeList" text="text" name="value" v-model="time" hideBorder @change="changeTime"/>
				<hpy-form-select title='选择性别' :dataList="sexList" text="text" name="value" v-model="sex" hideBorder @change="changeSex"/>
				<hpy-form-select title='选择星球' :dataList="typeList" text="text" name="value" v-model="type" hideBorder @change="changeType"/>
			</view>

			<view style="clear: both;"></view>

			<view class="content-view" v-for="(item, index) in dataList" :key="index" @click="toDetail(item)">
				<view style="float: left;">
					<image :src="item.pictures[0]" class="content-img" mode="aspectFill"></image>
				</view>
				<view class="content-content">
					<text style="margin-top: 20upx;">{{item.nickname}}</text>
					<text class="content-text"
						style="margin-top: 10upx; font-size: 24rpx; color: #778288;height: 100rpx;">{{item.content}}</text>
					<text style="margin-top: 10upx; font-size: 24rpx;">📍{{item.city}}</text>
					<view>
						<text style="margin-top: 36upx; font-size: 24rpx; color: #778288;">{{item.updateTime}}发布</text>
						<text
							style="margin-top: 36upx; font-size: 24rpx; color: #778288;margin-left: 20rpx;">👀{{item.view}}</text>
					</view>
				</view>
			</view>
		</view>
		<view class="cue-words" v-if="dataList.length == 0">没有更多数据</view>
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
				showCity: false,
				typeList: [{
						text: '选择星球',
						name: ''
					},
					{
						text: '喵星人',
						name: '1'
					},
					{
						text: '汪星人',
						name: '2'
					}
				],
				sexList: [
					{
						text: '选择性别',
						name: ''
					},
					{
						text: '男孩',
						name: '1'
					},
					{
						text: '女孩',
						name: '2'
					}
				],
				timeList: [
					{
						text: '更新时间',
						name: ''
					},
					{
						text: '近一周',
						name: '1'
					},
					{
						text: '近一个月',
						name: '2'
					},
					{
						text: '近三个月',
						name: '3'
					}
				],
				amapPlugin: null,
				key: 'd76b183c233f54efe31c24c6185750ab', 
				dataList: [],
				city: "全国",
				type: '',
				sex: '',
				time: '',
				pageNum: 1,
				pageSize: 10
			}
		},
		onShow() {
			
		},
		onReachBottom() {
			this.pageNum++;
			this.queryList(this.pageNum, this.pageSize, this.city == '全国' ? '' : this.city, this.time, this.type, this.sex)
		},
		onLoad() {
			this.queryList(this.pageNum, this.pageSize, this.city == '全国' ? '' : this.city, this.time, this.type, this.sex)
		},
		onPullDownRefresh() {
			this.dataList = []
			this.city = '全国'
			this.queryList(1, 10, this.city == '全国' ? '' : this.city, this.time, this.type, this.sex)
			this.citySelect = "全国"
			uni.stopPullDownRefresh()
		},
		methods: {
			// 选择城市
			selectCity(item) {
				this.dataList = []
				this.city = item.name
				this.showCity = false
				this.queryList(1, 10, item.name, "")
			},
			closeModal() {
				this.showCity = false
			},
			toDetail(item) {
				uni.navigateTo({
					url: `/pages/find/detail?id=${item.id}`
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
					url: `/pages/find/add?openId=${openId}`
				});
			},
			queryList(pageNum, pageSize, city, updateTime, type, sex) {
				const obj = {
					pageNum: pageNum,
					pageSize: pageSize,
					city: city,
					updateTime: updateTime,
					type: type,
					sex: sex
				}
				http.queryPetFinds(obj).then(res => {
					this.dataList = this.dataList.concat(res.data)
				})
			},
			changeType(e){
				this.dataList = []
				this.type = e.data.name
				this.queryList(1, 10, this.city == '全国' ? '' : this.city, this.time, this.type, this.sex)
			},
			changeSex(e){
				this.dataList = []
				this.sex = e.data.name
				this.queryList(1, 10, this.city == '全国' ? '' : this.city, this.time, this.type, this.sex)
			},
			changeTime(e){
				this.dataList = []
				this.time = e.data.name
				this.queryList(1, 10, this.city == '全国' ? '' : this.city, this.time, this.type, this.sex)
			},
			toNew() {
				const userInfo = uni.getStorageSync('userInfo')
				uni.navigateTo({
					url: `/pages/chat/index?toId=${userInfo.openId}`
				});
			}
		}
	}
</script>

<style lang="scss">
	.content {
		padding: 15rpx;
	}

	.background-view {
		background-image: url(https://pet2023.oss-cn-beijing.aliyuncs.com/images/2023/03/18/AKDL9rC9GqbJ70adfe7718d5a4e774ba166ab8b10729.jpg);
		background-size: 100%;
		width: 100%;
		height: 400rpx;
		display: flex;
		justify-content: space-between;
		align-items: center;

		view {
			font-size: 50rpx;
			color: #FFFFFF;
			margin: 0 auto;
		}
	}

	.card-view {
		margin-left: 20rpx;
		margin-top: -90rpx;

		.card:first-child {
			background-color: #FFAFCC;
		}

		.card:nth-child(2) {
			background-color: #FF9900;
		}

		.card {
			float: left;
			width: 280rpx;
			height: 140rpx;
			margin: 16rpx;
			border-radius: 20rpx;
			padding: 22rpx;

			.card-title {
				font-size: 32rpx;
				color: #FFFFFF;
			}

			.card-content {
				margin-top: 20rpx;
				font-size: 22rpx;
				color: #FFFFFF;
			}
		}
	}

	.filter-view {

		.city-view {
			font-size: 28rpx;
			margin-top: 12rpx;
			padding-left: 10rpx;
			display: flex;
			align-items: center;
			float: left;
			height: 48rpx;

			image {
				width: 30rpx;
				height: 30rpx;
			}
		}

		.select-view {
			float: right;
			height: 70rpx;
		}
	}

	.content-view {
		width: 100%;
		height: 350upx;
		box-shadow: 0 0 9px 3px #cee1eb;
		margin: 0 auto;
		border-radius: 20upx;
		margin-top: 30rpx;
		clear: both;
	}

	.content-img {
		width: 270upx;
		height: 270upx;
		margin-left: 22upx;
		margin-top: 40upx;
		border-radius: 10upx;
	}

	.content-content {
		display: flex;
		flex-direction: column;
		height: 300upx;
		justify-content: center;
		padding-left: 30upx;
		padding: 20rpx;
	}

	.cue-words {
		text-align: center;
		font-size: 28rpx;
		color: #778288;
		margin-top: 12rpx;
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

	// 文字超出...
	.content-text {
		text-overflow: -o-ellipsis-lastline;
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 3;
		line-clamp: 3;
		-webkit-box-orient: vertical;
	}
</style>
