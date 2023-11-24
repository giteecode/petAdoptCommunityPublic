<template>
	<view class="content">
		<view class="typeView">
			<axb-check-box :list="typeList" @change="chooseType"></axb-check-box>
		</view>
		<view class="title-view">
			<view>Man's only chance to choose his kin!</view>
			<view>领养宠物，也许是人类唯一可以选择亲人的机会！</view>
		</view>
		<view class="path-view">
			<view class="path" v-for="(item, index) in dataList" :key="index">
				<view class="dot-view"></view>
				<view class="content-view">
					<view style="font-size: 26rpx;">{{item.title}}</view>
					<view style="font-size: 22rpx;color: #778288;margin-top: 6rpx;">{{item.desc}}</view>
				</view>
			</view>
		</view>
		<view style="font-size: 30rpx;margin-top: 50rpx;">注意事项</view>
		<view class="care-view">
			<view v-for="(item, index) in careList" :key="index">* {{item}}</view>
		</view>
	</view>
</template>

<script>
	import axbCheckBox from '../../components/axb-checkbox/axb-checkbox.vue'
	import http from '@/api/backend.js'
	export default {
		components: {
			axbCheckBox
		},
		data() {
			return {
				typeList: [{
						name: "领养人",
						value: "1",
						checked: 1
					},
					{
						name: "送养人",
						value: "2",
						checked: 0
					}
				],
				dataList: [],
				lyrList: [
					{
						title: "首页筛选",
						desc: "筛选心仪的宠物"
					},
					{
						title: "点击申请领养",
						desc: "提交简单信息，了解大概条件"
					},
					{
						title: "等待送养人审核",
						desc: "等待送养人了解大概信息"
					},
					{
						title: "双方沟通，达成一致",
						desc: "为了宠物相关信息，以及养宠经验等"
					},
					{
						title: "线下交接",
						desc: "双方线下约定交接领养宠物"
					},
					{
						title: "领养成功",
						desc: "成功领养宠物后，也请领养人在生活中多陪伴它"
					}
				],
				syrList: [
					{
						title: "发布送养",
						desc: "首页发布宠物信息"
					},
					{
						title: "平台审核",
						desc: "审核发布送养信息是否真实"
					},
					{
						title: "领养人申请",
						desc: "等待有缘的送养人提交申请"
					},
					{
						title: "等待送养人审核",
						desc: "等待送养人了解大致信息"
					},
					{
						title: "双方沟通，达成一致",
						desc: "为了宠物相关信息，以及养宠经验等"
					},
					{
						title: "线下交接",
						desc: "双方线下约定交接领养宠物"
					},
					{
						title: "领养成功",
						desc: "成功领养宠物后，也请领养人在生活中多陪伴它"
					}
				],
				careList: [
					"不要发布二维码",
					"不要进行任何形式的宠物买卖",
					"不要支付任何费用",
					"不要草率领养，对生命负责"
				]
			}
		},
		onLoad() {
			this.chooseType("1")
		},
		methods: {
			chooseType(e) {
				if(e == "1") {
					this.dataList = this.lyrList
				}else {
					this.dataList = this.syrList
				}
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
	
	.typeView {
		margin-left: -20upx;
		margin-top: 20rpx;
	}
	.title-view {
		margin-top: 40rpx;
		font-size: 24rpx;
	}
	
	.path-view {
		margin-top: 40rpx;
		
		.path {
			display: flex;
			align-items: center;
			margin-top: 40rpx;
			
			.dot-view {
				width: 15rpx;
				height: 15rpx;
				background-color: #ffaa00;
				border-radius: 100rpx;
				float: left;
			}
			
			.content-view {
				float: left;
				margin-left: 40rpx;
			}
		}
	}
	
	.care-view {
		margin-top: 30rpx;
		
		view {
			font-size: 24rpx;
			margin-top: 10rpx;
			color: #dd0000;
		}
	}
</style>
