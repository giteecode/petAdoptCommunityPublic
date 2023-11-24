<template>
	<view class="content" :key="keyIndex" >
		<view  class="story-title">想领养的宠物</view>
		<view v-if="adoptInfo" class="content-view" @click="toPetDetails(adoptInfo)">
			<view style="float: left;">
				<image :src="adoptInfo.pictures[0]" class="content-img" mode="aspectFill"></image>
			</view>
			<view class="is-finish" v-if="adoptInfo.isFinish == '0'">
				已被领养 
			</view>
			<view class="content-content">
				<view class="logo" v-if="adoptInfo.isFinish == '1'">{{"等待领养"}}</view>
				<text style="margin-top: -30rpx;">{{adoptInfo.nickname}}，{{adoptInfo.age}}</text>
				<text class="content-text"
					style="margin-top: 26upx; font-size: 24rpx; color: #778288;height: 70rpx;">{{adoptInfo.content}}</text>
				<view class="note">
					<text v-if="adoptInfo.physicalCondition[0]"
						style="background-color: #368BE3;">{{adoptInfo.physicalCondition[0]}}</text>
					<text v-if="adoptInfo.physicalCondition[1]"
						style="background-color: #70AF48;">{{adoptInfo.physicalCondition[1]}}</text>
					<text v-if="adoptInfo.physicalCondition[2]"
						style="background-color: #F08080;">{{adoptInfo.physicalCondition[2]}}</text>
				</view>
			</view>
		</view>
		<view class="story-title">申请人</view>
		<view class="user-view"    v-show="user">
			<view class="user-text">
				<image :src="user.avatarUrl" @click="toUser(user.openId)"></image>
				<view class="user-info">
					<view class="nickname">{{user.nickname}}</view>
					<view class="is-real-name">{{user.authenticated ? "已实名" : "未实名"}}</view>
<!-- 					<view class="num-view">
						<text style="color: #666666;margin-right: 8rpx;">正在送养 </text><text
							class="doing-num">{{user.doingNum?user.doingNum:""}}</text>
						<text style="color: #666666;margin-left: 20rpx;margin-right: 8rpx;">已送养 </text><text
							class="done-num">{{user.doingNum?user.doingNum:"0"}}</text>
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
				<image src="../../static/phone.png" @tap="copy(user.phone)"></image>
				<image src="../../static/wx.png" @tap="copy(user.wx)"></image>
			</view>
		</view>
		<view class="story-title">申请资料</view>
		<view style="font-size: 28rpx;margin-top: 30rpx;">性别</view>
		<view class="sexView">
			<view v-for="item in sexList" class="radio" :key="item.value" :class="item.checked?'radioCheck':''">
				{{item.name}}
			</view>
		</view>
		 
		<view style="font-size: 28rpx;margin-top: 30rpx;clear: both;">年龄</view>
		<view class="titleView"><input type="number" v-model="age" placeholder="请输入年龄" /></view>
		 
		<view style="font-size: 28rpx;margin-top: 30rpx;">养宠经验</view>
		<view class="sexView">
			<view v-for="item in jingyanList" class="radio" :key="item.value" :class="item.checked?'radioCheck':''">
				{{item.name}}
			</view>
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">目前宠物</view>
		<view class="sexView">
			<view v-for="item in petNowList" class="radio" :key="item.value" :class="item.checked?'radioCheck':''">
				{{item.name}}
			</view>
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">住房情况</view>
		<view class="sexView">
			<view v-for="item in houseList" class="radio" :key="item.value" :class="item.checked?'radioCheck':''">
				{{item.name}}
			</view>
		</view> 
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">婚姻情况</view>
		<view class="sexView">
			<view v-for="item in hyList" class="radio" :key="item.value" :class="item.checked?'radioCheck':''">
				{{item.name}}
			</view>
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">月收入</view>
		<view class="sexView">
			<view v-for="item in incomeList" class="radio" :key="item.value" :class="item.checked?'radioCheck':''">
				{{item.name}}
			</view>
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;clear: both;">职业</view>
		<view class="titleView"><input type="text" v-model="work" placeholder="请输入职业" /></view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">城市</view>
		<!-- 选择城市 -->
		<view class="city-view" @click="showCity = true">
			<view class="city">{{city}}</view>
		</view>
		<chose-city @selectCity="selectCity" v-if="showCity" @closeModal="closeModal"></chose-city>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">联系电话</view>
		<view class="phoneView"><input type="number" maxlength="11" v-model="phone" placeholder="请输入手机号码" /></view>

		<view style="font-size: 28rpx;margin-top: 30rpx;clear: both;">微信号</view>
		<view class="titleView"><input type="text" v-model="wechat" placeholder="请输入职业" /></view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">表达领养诚意</view>
		<view class="contentView">
			<textarea maxlength="200" v-model="content" placeholder="请输请用你的诚意打动他们...(最多200字)"></textarea>
		</view>

		<view class="bottom-view">
			<button v-show="status==='0'" class="btn" @click="apply(0)">拒绝</button>
			<button v-show="status==='0'" class="confirm-btn"  @click="apply(1)">同意</button>
			<view class="status" v-if="status==='1'"  >已同意</view>
			<view class="status" v-if="status==='2'"  >已拒绝</view>
		</view> 

	</view>
</template>

<script>
	import axbCheckBox from '../../components/axb-checkbox/axb-checkbox.vue'
	import choseCity from "@/components/chose-city/chose-city"
	import http from '@/api/backend.js'
	export default {
		components: {
			axbCheckBox,
			choseCity
		},
		data() {
			return {
				showCity: false,
				sexList: [{
						name: "男生",
						value: "男生",
						checked: 0
					},
					{
						name: "女生",
						value: "女生",
						checked: 0 
					}
				],
				jingyanList:[
					{
						name: "有",
						value: "有",
						checked: 0
					},
					{
						name: "无",
						value: "无",
						checked: 0
					}
				],				petNowList:[
					{
						name: "有猫",
						value: "有猫",
						checked: 0
					},
					{
						name: "有狗",
						value: "有狗",
						checked: 0
					},
					{
						name: "猫狗",
						value: "猫狗",
						checked: 0
					},
					{
						name: "无",
						value: "无",
						checked: 0
					}
				],				houseList:[
					{
						name: "自有住房",
						value: "自有住房",
						checked: 0
					},
					{
						name: "租房",
						value: "租房",
						checked: 0
					},
					{
						name: "借宿",
						value: "借宿",
						checked: 0
					}
				],				hyList:[
					{
						name: "单身",
						value: "单身",
						checked: 0
					},
					{
						name: "恋爱",
						value: "恋爱",
						checked: 0
					},
					{
						name: "已婚",
						value: "已婚",
						checked: 0
					}
				],				incomeList:[
					{
						name: "三千以下",
						value: "三千以下",
						checked: 0
					},
					{
						name: "三千到六千",
						value: "三千到六千",
						checked: 0
					},
					{
						name: "六千到一万",
						value: "六千到一万",
						checked: 0
					},
					{
						name: "一万到两万",
						value: "两千到两万",
						checked: 0
					},
					{
						name: "两万以上",
						value: "两万以上",
						checked: 0
					},
				],
				adoptInfo:"",
				user:"",
				addressShow: false,
				openId: "",
				adoptId:"",
				age:"",
				content:"",
				house:"",
				marriage:"",
				monthlyIncome:"",
				petExperience:"",
				petNow:"",
				phone:"",
				province:"",
				city:"",
				wechat:"",
				work:"",
				itemId:"",
				duifangOpenId:"",
				status:"",
				isFollowUser:false
			}
		},
		onLoad(e) {
			this.itemId = e.id
			this.duifangOpenId = e.duifangOpenId;
			this.openId =  e.openId
			this.adoptId = e.adoptId;
			const userInfo = uni.getStorageSync('userInfo');
			this.getDatas()
			this.getPetInfos()
			this.getUserDetail()
			
		},
		methods: {
			// 提交
			submit() {
				if(!this.paramCheck)return
				const params = {
					
				}
				uni.showLoading({
					title: '请求中，请稍后'
				});
				http.addApply(params).then(res=>{
					if(res.code===0){
						uni.showToast({title: '提交成功',icon: 'none'});
						setTimeout(function() {
							uni.navigateTo({url: `/pages/myapply/myapply?openId=${this.openId}`});
						}, 1500)
					}
					uni.showToast({title: res.message,icon: 'none'});
				}).catch(res=>{
					uni.showToast({title: '提交失败',icon: 'none'});
				}).finally(res=>{
					uni.hideLoading()
				})
				 
			},
			getDatas(){
				http.getApplyById(this.itemId).then(res=>{
					if(res.code===0){ 
						this.sexList = this.sexList.map(item=>{
							if(item.value == res.data.sex){
								item.checked = 1
							}
							return item
						})
						this.jingyanList = this.jingyanList.map(item=>{
							if(item.value == res.data.petExperience){
								item.checked = 1
							}
							return item
						})
						this.petNowList = this.petNowList.map(item=>{
							if(item.value == res.data.petNow){
								item.checked = 1
							}
							return item
						})
						this.houseList = this.houseList.map(item=>{
							if(item.value == res.data.house){
								item.checked = 1
							}
							return item
						})
						this.hyList = this.hyList.map(item=>{
							if(item.value == res.data.marriage){
								item.checked = 1
							}
							return item
						})
						this.incomeList = this.incomeList.map(item=>{
							if(item.value == res.data.monthlyIncome){
								item.checked = 1
							}
							return item
						})
						this.sex=res.data.petExperience;
						this.petExperience=res.data.petExperience;
						this.petNow=res.data.petNow;
						this.marriage=res.data.marriage;
						this.house=res.data.house;
						this.monthlyIncome=res.data.monthlyIncome;
						
						
						this.age=res.data.age;
						this.city=res.data.city;
						this.content=res.data.content;
						this.phone=res.data.phone;
						this.province=res.data.province;
						this.status=res.data.status;
						this.wechat=res.data.wechat;
						this.work=res.data.work;
					}
				})
			},
			getPetInfos(){
				let obj = {
					adoptId: this.adoptId
				}
				http.getPetAdopt(obj).then(res => {
					this.adoptInfo = res.data.petAdoptVO
				})
			},
			getUserDetail(){
				let obj = {
					openId: this.duifangOpenId
				}
				http.getUserInfo(obj).then(res => {
					this.user = res.data
					this.isFollow()
				})
			},
			toPetDetails(item){
				uni.navigateTo({
					url: `/pages/home/detail?id=${item.id}`
				});
			},
			apply(val){
				var status = val?"1":"2"
				var statusString = val?"同意":"拒绝"
				uni.showModal({
					title: `确定要${statusString}吗`,
					success:  (res)=> {
						if(res.confirm) {
							http.checkApply(this.itemId,{status:status,openId:this.openId}).then(res=>{
								if(res.code===0){
									this.status = status;
								}
							})
						}
					}
				})
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
		}
	}
</script>

<style lang="scss">
	.content {
		padding: 15rpx;
	}

	.imgView {
		width: 240rpx;
		height: 240rpx;
		background-color: #f1f1f1;
		display: flex;
		border-radius: 10rpx;
		margin-top: 20rpx;

		image {
			width: 80rpx;
			height: 80rpx;
			align-self: center;
			margin: 0 auto;
		}
	}
	.preImage {
		margin-left: -20rpx;
		margin-top: 20rpx;
		
		image {
			width: 160rpx;
			height: 160rpx;
			border-radius: 16rpx;
			
		}
		
		.addView {
			width: 160rpx;
			height: 160rpx;
			// background-color: #f1f1f1;
			display: flex;
			margin-left: 20rpx;
			
			image {
				width: 80rpx;
				height: 80rpx;
				align-self: center;
				margin: 0 auto;
			}
		}
	}

	.nicknameView,
	.phoneView,
	.titleView {
		margin-top: 20rpx;

		input {
			font-size: 24rpx;
		}
	}

	.typeView,
	.sexView,
	.bodyView,
	.ageView,
	.conditionView {
		margin-left: -20upx;
		margin-top: 20rpx;
	}

	.city-view {
		.city {
			display: inline-block;
			height: 50upx;
			line-height: 50upx;
			padding: 0 22upx;
			border-radius: 8upx;
			font-size: 22upx;
			box-sizing: border-box;
			margin-top: 20rpx;
			background: #f7f7f7;
		}
	}

	.contentView {

		textarea {
			margin-top: 20rpx;
			font-size: 24rpx;
			background-color: #f8f8f8;
			width: 90%;
			margin: 0 auto;
			padding: 20rpx;
			margin-top: 20rpx;
			border-radius: 10rpx;
		}
	}

	
	.radio{
		display: inline-block;
		height: 50upx;
		line-height: 50upx;
		padding: 0 22upx;
		border-radius: 8upx;
		font-size: 22upx;
		box-sizing: border-box;
		margin-left: 20rpx;
		margin-top: 10rpx;
	}
	.radioCheck{
		background: #ffaa00;
		color: #FFFFFF;
		border-color: #ffaa00;
	}
	.story-title {
		margin-top: 30rpx;
		height: 40rpx;
		border-left: 6rpx solid #F58418;
		padding-left: 20rpx;
		color: #F58418;
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
	.bottom-view {
		width: 100%;
		height: 100rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		position: fixed;
		bottom: 0;
		left: 0;
		background-color: #FFFFFF;
		.confirm-btn {
			border-radius: 40rpx;
			background-color: #F8A323;
			width: 45%;
			height: 84rpx;
			line-height: 84rpx;
			color: #FFFFFF;
			font-size: 28rpx;
		}
		.btn {
			border-radius: 40rpx;
			width: 45%;
			height: 84rpx;
			line-height: 84rpx;
			font-size: 28rpx;
		}
		.status {
			color: #ffaa00;
			font-size: 30upx;
			display: inline-block;
		}
	}
</style>
