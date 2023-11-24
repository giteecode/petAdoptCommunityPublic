<template>
	<view class="content">
		<view style="font-size: 28rpx;margin-top: 30rpx;">性别</view>
		<view class="sexView">
			<axb-check-box :list="sexList" @change="sex = $event" ></axb-check-box>
		</view>
		 
		<view style="font-size: 28rpx;margin-top: 30rpx;clear: both;">年龄</view>
		<view class="titleView"><input type="number" v-model="age" placeholder="请输入年龄" /></view>
		 
		<view style="font-size: 28rpx;margin-top: 30rpx;">养宠经验</view>
		<view class="sexView">
			<axb-check-box :list="jingyanList" @change="petExperience = $event" ></axb-check-box>
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">目前宠物</view>
		<view class="sexView">
			<axb-check-box :list="petNowList" @change="petNow = $event" ></axb-check-box>
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">住房情况</view>
		<view class="sexView">
			<axb-check-box :list="houseList" @change="house = $event" ></axb-check-box>
		</view> 
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">婚姻情况</view>
		<view class="sexView">
			<axb-check-box :list="hyList" @change="marriage = $event" ></axb-check-box>
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">月收入</view>
		<view class="sexView">
			<axb-check-box :list="incomeList" @change="monthlyIncome = $event" ></axb-check-box>
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
			<button class="confirm-btn" @click="submit">申请领养</button>
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
			}
		},
		onLoad(e) {
			this.openId = e.openId;
			this.adoptId = e.adoptId;
			const userInfo = uni.getStorageSync('userInfo');
		},
		methods: {
			// 提交
			submit() {
				if(!this.paramCheck)return
				const params = {
					adoptId:this.adoptId,
					age:this.age,
					city:this.city,
					content:this.content,
					house:this.house,
					marriage:this.marriage,
					monthlyIncome:this.monthlyIncome,
					openId:this.openId,
					petExperience:this.petExperience,
					petNow:this.petNow,
					phone:this.phone,
					province:this.province,
					sex:this.sex,
					status:this.status,
					wechat:this.wechat,
					work:this.work
				}
				uni.showLoading({
					title: '请求中，请稍后'
				});
				http.addApply(params).then(res=>{
					uni.hideLoading()
					if(res.code===0){
						uni.showToast({title: '提交成功',icon: 'none'});
						// setTimeout(function() {
						// 	uni.navigateTo({url: `/pages/myapply/myapply?openId=${this.openId}`});
						// }, 1500)
						return
					}
					uni.showToast({title: res.message,icon: 'none'});
				}).catch(res=>{
					uni.hideLoading()
					uni.showToast({title: '提交失败',icon: 'none'});
				}) 
			},
			error() {
				uni.showToast({
					title: '接口请求异常',
					icon: 'error',
					duration: 2000
				})
			},
			// 选择城市
			selectCity(item) {
				this.city = item.name
				this.showCity = false
			},
			closeModal() {
				this.showCity = false
			},
			paramCheck() {
				if(this.openId == null || this.openId == "") {
					uni.showToast({title: '您还未登录',icon: 'none'});
					return false
				}
				if(this.age == null || this.age == "") {
					uni.showToast({title: '请填写年龄',icon: 'none'});
					return false
				}
				if(this.content == null || this.content == "") {
					uni.showToast({title: '请填写一些打动对方的话吧',icon: 'none'});
					return false
				}
				if(this.house == null || this.house == "") {
					uni.showToast({title: '请选择住房情况',icon: 'none'});
					return false
				}
				if(this.marriage == null || this.marriage == "") {
					uni.showToast({title: '请选择婚姻状况',icon: 'none'});
					return false
				}
				if(this.monthlyIncome == null || this.monthlyIncome == "") {
					uni.showToast({title: '请选择收入',icon: 'none'});
					return false
				}
				if(this.petExperience == null || this.petExperience == "") {
					uni.showToast({title: '请选择养宠经验',icon: 'none'});
					return false
				}
				if(this.petNow == null || this.petNow == "") {
					uni.showToast({title: '请选择现有宠物',icon: 'none'});
					return false
				}
				if(this.phone == null || this.phone == "") {
					uni.showToast({title: '请填写手机',icon: 'none'});
					return false
				}
				if(this.city == null || this.city == "" || this.city == "请选择城市") {
					uni.showToast({title: '请选择城市',icon: 'none'});
					return false
				}
				if(this.wechat == null || this.wechat == "") {
					uni.showToast({title: '请填写微信',icon: 'none'});
					return false
				}
				if(this.work == null || this.work == "") {
					uni.showToast({title: '请填写工作情况',icon: 'none'});
					return false
				}
				return true
			}
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

	.bottom-view {
		width: 100%;
		height: 100rpx;
		display: flex;
		align-items: center;

		.confirm-btn {
			float: left;
			border-radius: 40rpx;
			background-color: #F8A323;
			width: 80%;
			height: 84rpx;
			line-height: 84rpx;
			color: #FFFFFF;
			font-size: 28rpx;
		}
	}
</style>
