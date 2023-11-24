<template>
	<view class="content">
		<view class="record-view" v-for="(item, index) in records" :key="index" @click="toDetail(item)">
			<view class="left-view">
				<view class="title">{{item.subject}}</view>
				<view class="time">{{item.time}}</view>
			</view>
			<view class="right-view" :style="{'background-color': item.type == '1' ? '#18BC37' : 'red'}">{{item.type == '1' ? '答对' : '答错'}}</view>
		</view>
		<view class="cue-words" v-if="records.length == 0">您还未答过题哦</view>
	</view>
</template>

<script>
	import http from '@/api/backend.js'
	export default {
		data() {
			return {
				userId: '',
				records: []
			};
		},
		onLoad(option) {
			this.userId = option.userId
			this.queryAnswerRecords(this.userId, 1, 10)
		},
		onPullDownRefresh() {
			this.queryAnswerRecords(this.userId, 1, 10)
			uni.stopPullDownRefresh()
		},
		methods: {
			// 查询答题记录
			queryAnswerRecords(userId, pageNum, pageSize) {
				http.queryAnswerRecords({userId: this.userId, pageNum: pageNum, pageSize: pageSize}).then(res => {
					this.records = res.data
				})
			},
			toDetail(item) {
				uni.navigateTo({
					url: `/pages/other/answer?answerId=${item.answerId}&btnType=${item.type}`
				});
			}
		}
	}
</script>

<style lang="scss">
	.content {
		padding: 20rpx;
	}
	.record-view {
		display: flex;
		align-items: center;
		padding-bottom: 20rpx;
		border-bottom: 2rpx solid #ececec;
		margin-top: 30rpx;
		
		.left-view {
			float: left;
			
			.title {
				font-size: 30rpx;
				color: #212121;
				width: 86%;
				// 文字超出...
				text-overflow: -o-ellipsis-lastline;
				overflow: hidden;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-line-clamp: 1;
				line-clamp: 1;
				-webkit-box-orient: vertical;
			}
			
			.time {
				margin-top: 16rpx;
				font-size: 28rpx;
				color: #acacac;
			}
		}
		.right-view {
			float: right;
			font-size: 26rpx;
			width: 12%;
			padding: 10rpx;
			padding-left: 20rpx;
			padding-right: 20rpx;
			text-align: center;
			color: #FFFFFF;
			border-radius: 50rpx;
		}
	}
	
	.cue-words {
		text-align: center;
		font-size: 28rpx;
		color: #778288;
		margin-top: 12rpx;
	}
</style>
