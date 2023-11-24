<template>
	<view  class="content">
		<view class="content-view" v-for="(item, index) in dataList" :key="index" @click="toDetail(item)">
			<view  class="up"  >
				<view  class="sex"  >
					<view  class="label"  >性别： </view>
					{{item.sex}}
				</view>
				<view  class="income"  >
					<view  class="label"  >月收入： </view>
					{{item.monthlyIncome}}
				</view>
				<view  class="petnow"  >
					<view  class="label"  >现有宠物： </view>
					{{item.petNow}}
				</view>
				<view  class="house"  > 
					<view  class="label"  >住房状况： </view>
					{{item.house}}
				</view>
			</view>
			<view class="down" >
				<view class="label">请求内容： </view>
				<view class="details">{{item.content}}</view>
			</view>
			<view class="mystatus status-green" v-show="item.status === '1'">已同意</view>
			<view class="mystatus status-red" v-show="item.status === '2'">已拒绝</view>
		</view>
		<view class="cue-words" v-if="dataList.length == 0">没有更多数据</view>
	</view>
</template>

<script>
	import http from '@/api/backend.js';
	import {mapMutations, mapState} from 'vuex';
	var _self;
	export default {
		data() {
			return {
				openId:"",
				dataList:[]
			}
		},
		onPullDownRefresh() {
			
		},
		onShow() {
			this.getDatas()
		},
		onLoad(e) {
			this.openId = e.openId
			this.dataList = []
		},
		methods: {
			getDatas(){
				http.queryApplyList({
					openId:this.openId,
					pageNum:1,
					pageSize:999
				}).then(res=>{
					console.log(res)
					if(res.code === 0){
						this.dataList = res.data
					}
				})
			},
			toDetail(item){
				uni.navigateTo({
					url: `/pages/myapprove/detail?id=${item.id}&adoptId=${item.adoptId}&duifangOpenId=${item.openId}&openId=${this.openId}`
				});
			}
		}
	}
</script>

<style  lang="scss" >
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
	.content-view {
		width: 90%;
		height: 300upx;
		box-shadow: 0 0 9px 3px #cee1eb;
		margin: 0 auto;
		border-radius: 20upx;
		margin-top: 30rpx;
		position: relative;
		padding: 30upx;
		.up{
			display: flex;
			flex-direction: column;
			justify-content: space-around;
			align-items: flex-start;
			font-size: 30upx;
			flex-wrap: wrap;
			& > view{
				width: 100%;
			}
			.label{
				width: 30%;
				display: inline-block;
				color: #F58418;
			}
		}
		.down{
			display: flex;
			.label{
				width: 30%;
				display: inline-block;
				color: #F58418;
			}
			.detail{
				width: 69%;
				font-size: 35upx;
				line-height: 40upx;
				overflow: hidden;
				max-height: 210upx;
				word-break: break-all;
			}
		}
	}
	.cue-words {
		text-align: center;
		font-size: 28rpx;
		color: #778288;
		margin-top: 12rpx;
	}
	.mystatus{
		position: absolute;
		right: 30upx;
		top: 30upx;
		width: 150upx;
		height: 150upx;
		border-radius: 80upx;
		font-weight: 600;
		transform: rotate(-45deg);
		display: flex;
		justify-content: center;
		align-items: center;
	} 
	.status-green{
		border: 12upx solid green;
		color: green;
	}
	.status-red{
		border: 12upx solid red;
		color: red;
	}
</style>
