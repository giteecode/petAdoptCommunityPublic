<template>
	<view class="content">
		<view style="font-size: 28rpx;">添加图片</view>
		<view style="font-size: 24rpx;margin-top: 6rpx;color: #999999;">(最多上传8张图片，单张图片大小不能超过10MB)</view>
		<view class="imgView" @click="choseImg()" v-if="imgArr.length === 0">
			<image src="../../static/upload.png"></image>
		</view>
		<!-- 预览图片 -->
		<view class="preImage" v-if="imgArr.length !== 0">
			<view v-for="(item, index) in imgArr" :key="index" style="float: left;margin-left: 20rpx;">
				<image mode="aspectFill" :src='item' @click="previewImg(item)"></image>
				<image @click="deleteImg(item)" style="width: 30rpx;height: 30rpx;margin-bottom: 130rpx;margin-left: -6rpx;" src="../../static/x.png"></image>
			</view>
			<!-- <view @click="choseImg()" class="addView">
				<image src="../../static/add2.png"></image>
			</view> -->
		</view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;clear: both;">标题</view>
		<view class="titleView"><input type="text" v-model="petInfo.title" placeholder="请输入宠物标题（最多20个字）" /></view>
		
		<view style="font-size: 28rpx;margin-top: 30rpx;">昵称</view>
		<view class="nicknameView"><input type="text" v-model="petInfo.nickname" maxlength="10" placeholder="请输入宠物昵称（最多8个字）" /></view>

		<view style="font-size: 28rpx;margin-top: 30rpx;">联系电话</view>
		<view class="phoneView"><input type="number" maxlength="11" v-model="petInfo.phone" placeholder="请输入手机号码" /></view>

		<view style="font-size: 28rpx;margin-top: 30rpx;">宠物种类</view>
		<view class="typeView">
			<axb-check-box :list="typeList" @change="chosePetType"></axb-check-box>
		</view>

		<view style="font-size: 28rpx;margin-top: 30rpx;">性别</view>
		<view class="sexView">
			<axb-check-box :list="sexList" @change="choseSex"></axb-check-box>
		</view>

		<view style="font-size: 28rpx;margin-top: 30rpx;">健康状态</view>
		<view class="bodyView">
			<!-- 多选 :multi="true" -->
			<axb-check-box :multi="true" :list="bodyList" @change="chosePhysicalCondition"></axb-check-box>
		</view>

		<view style="font-size: 28rpx;margin-top: 30rpx;">年龄</view>
		<view class="ageView">
			<axb-check-box :list="ageList" @change="choseAge"></axb-check-box>
		</view>

		<view style="font-size: 28rpx;margin-top: 30rpx;">领养条件</view>
		<view class="conditionView">
			<axb-check-box :multi="true" :list="conditionList" @change="choseConditions"></axb-check-box>
		</view>

		<view style="font-size: 28rpx;margin-top: 30rpx;">城市</view>
		<!-- 选择城市 -->
		<view class="city-view" @click="showCity = true">
			<view class="city">{{city}}</view>
		</view>
		<chose-city @selectCity="selectCity" v-if="showCity" @closeModal="closeModal"></chose-city>

		<view style="font-size: 28rpx;margin-top: 30rpx;">TA的故事</view>
		<view class="contentView">
			<textarea maxlength="200" v-model="petInfo.content" placeholder="请输入宠物描述/送养原因...(最多200字)"></textarea>
		</view>

		<view class="bottom-view">
			<button class="confirm-btn" @click="submit(petInfo)">发布</button>
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
				typeList: [{
						name: "猫咪",
						value: "1",
						checked: 0
					},
					{
						name: "狗狗",
						value: "2",
						checked: 0
					}
				],
				sexList: [{
						name: "男孩",
						value: "1",
						checked: 0
					},
					{
						name: "女孩",
						value: "2",
						checked: 0
					}
				],
				bodyList: [{
						name: "已免疫",
						value: "已免疫",
						checked: 0
					},
					{
						name: "已驱虫",
						value: "已驱虫",
						checked: 0
					},
					{
						name: "已绝育",
						value: "已绝育",
						checked: 0
					}
				],
				ageList: [{
						name: "0-3月",
						value: "0-3月",
						checked: 0
					},
					{
						name: "4-6月",
						value: "4-6月",
						checked: 0
					},
					{
						name: "7-12月",
						value: "7-12月",
						checked: 0
					},
					{
						name: "1年以上",
						value: "1年以上",
						checked: 0
					}
				],
				conditionList: [{
						name: "仅限同城",
						value: "仅限同城",
						checked: 0
					},
					{
						name: "工作稳定",
						value: "工作稳定",
						checked: 0
					},
					{
						name: "接收回访",
						value: "接收回访",
						checked: 0
					},
					{
						name: "签订领养协议",
						value: "签订领养协议",
						checked: 0
					},
					{
						name: "不得遗弃、转让、贩卖、虐待",
						value: "不得遗弃、转让、贩卖、虐待",
						checked: 0
					}
				],
				addressShow: false,
				city: "选择城市",
				imgArr: [],
				petInfo: {},
				openId: ""
			}
		},
		onLoad(e) {
			http.getFlag().then(res => {
				if(res.data) {
					uni.switchTab({url: '/pages/home/index'})
				}
			})
			this.openId = e.openId
			const userInfo = uni.getStorageSync('userInfo')
			this.petInfo.phone = userInfo.phone
		},
		methods: {
			addresspick(obj) {
				this.city = obj.city.AreaName
				this.petInfo.city = obj.city.AreaName	
			},
			choseImg() {
				var me = this;
				var serverUrl = this.serverUrl;
				uni.chooseImage({
					count: 8,
					sizeType: ['original', 'compressed'],
					success: function(res) {
						let arr = res.tempFiles
						for(let i = 0; i < arr.length; i++) {
							// 图片大小限制10MB
							if(arr[i].size > 10458760) {
								uni.showToast({
									title: "图片过大",
									icon: 'error'
								});
								return
							}
						}
						uni.showLoading({title: "上传中"});
						http.uploadFile(res.tempFilePaths).then(picRes => {
							me.petInfo.pictures = picRes
							me.imgArr = picRes
							uni.hideLoading()
							uni.showToast({
								title: "上传成功",
								icon: 'success'
							});
						})
					}
				});
			},
			previewImg(item) {
				uni.previewImage({
					current: item,
					urls: this.imgArr
				})
			},
			deleteImg(item) {
				http.deletePic({picture: item}).then(res => {
					if(res.success) {
						let arr = this.imgArr
						this.imgArr = arr.filter(function(i) {
						    return i != item
						});
						return;
					}
					this.error()
				})
			},
			chosePetType: function(value) {
				this.petInfo.type = value
			},
			choseSex: function(value) {
				this.petInfo.sex = value
			},
			chosePhysicalCondition: function(value) {
				this.petInfo.physicalCondition = value
			},
			choseAge: function(value) {
				this.petInfo.age = value
			},
			choseConditions: function(value) {
				this.petInfo.conditions = value
			},
			// 提交
			submit() {
				this.petInfo.openId = this.openId
				if(!this.paramCheck()) return
				http.addPetAdopt(this.petInfo).then(res => {
					if(res.success) {
						uni.showToast({
							title: '发布成功',
							icon: 'success',
							duration: 2000
						})
						setTimeout(function(){
							uni.switchTab({
								url: '/pages/home/index'
							});
						}, 1500)
						return;
					}
					this.error()
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
				this.petInfo.city = item.name
				this.showCity = false
			},
			closeModal() {
				this.showCity = false
			},
			paramCheck() {
				if(this.petInfo.openId == null || this.petInfo.openId == "") {
					uni.showToast({title: '您还未登录',icon: 'none'});
					return false
				}
				if(this.petInfo.pictures == null || this.petInfo.pictures == "") {
					uni.showToast({title: '请上传图片',icon: 'none'});
					return false
				}
				if(this.petInfo.title == null || this.petInfo.title == "") {
					uni.showToast({title: '请填写标题',icon: 'none'});
					return false
				}
				if(this.petInfo.nickname == null || this.petInfo.nickname == "") {
					uni.showToast({title: '请填写昵称',icon: 'none'});
					return false
				}
				if(this.petInfo.phone == null || this.petInfo.phone == "") {
					uni.showToast({title: '请填写手机号',icon: 'none'});
					return false
				}
				if(this.petInfo.type == null || this.petInfo.type == "") {
					uni.showToast({title: '请选择种类',icon: 'none'});
					return false
				}
				if(this.petInfo.sex == null || this.petInfo.sex == "") {
					uni.showToast({title: '请选择性别',icon: 'none'});
					return false
				}
				if(this.petInfo.physicalCondition == null || this.petInfo.physicalCondition == "") {
					uni.showToast({title: '请选择健康状态',icon: 'none'});
					return false
				}
				if(this.petInfo.age == null || this.petInfo.age == "") {
					uni.showToast({title: '请选择年龄',icon: 'none'});
					return false
				}
				if(this.petInfo.conditions == null || this.petInfo.conditions == "") {
					uni.showToast({title: '请选择领养条件',icon: 'none'});
					return false
				}
				if(this.petInfo.city == null || this.petInfo.city == "") {
					uni.showToast({title: '请选择城市',icon: 'none'});
					return false
				}
				if(this.petInfo.content == null || this.petInfo.content == "") {
					uni.showToast({title: '请留下TA的故事',icon: 'none'});
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
