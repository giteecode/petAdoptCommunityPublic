<template>
	<view class="chose-city">
		<!-- 城市搜索 -->
		<view class="city-search-wrap">
			<view class="search">
				<view class="l-search">
					<view class="icon-search">
						<view class="cuIcon-search"></view>
					</view>
					<input class="input-search" type="text" :value="inputValue" placeholder="请输入城市" placeholder-style="color:#8E8F97"
					 :focus="searchFocus" @input="searchChange" />
					<text class="clear-input iconfont icon-icon-test" v-if="isClearBtn" @click="inputValue = ''"></text>
				</view>
				<view class="r-cancel" @click="closeModal">取消</view>
			</view>
			<!-- 搜索列表  -->
			<view class="reach-content" v-show="inputValue">
				<block v-show="searchData.length">
					<view class="li" v-for="item in searchData" :key="item.citycode" @click="selectCity(item)">
						{{item.name}}
					</view>
				</block>
				<view class="has-no-data" v-show="hasNoData">没有找到匹配数据~</view>
			</view>
		</view>
		<!-- 城市列表 -->
		<scroll-view class="scroll-view" scroll-y scroll-with-animation="true" enable-back-to-top="true" :scroll-into-view="toIndex"
		 @scroll="scrollHandle" v-if="!inputValue">
			<view class="block">
				<!-- 您所在的地区 -->
				<view class="area list-item" id="area">
					<view class="title-wrapp">
						<view class="c-title">
							<text class="l">您所在的地区</text>
						</view>
					</view>
					<view class="ul">
						<view class="li now font-clamp" @click="selectCity(myCityObj,'refresh')">
							<image src="https://fulu-mall.oss-cn-hangzhou.aliyuncs.com/e0e5e497b1714e8fa6135df32e4bf114.png" class="icon"
							 v-if="hasLocation"></image>
							<image src="https://fulu-mall.oss-cn-hangzhou.aliyuncs.com/8ed89f5bcaac48c6bd9139261f86c6b6.png" class="icon"
							 v-else></image>
							<text class="text">{{ hasLocation ? myCityObj.name:'定位失败' }}</text>
						</view>
					</view>
				</view>
				<!-- 历史记录 -->
				<view class="area list-item" id="record" v-if="recordList.length">
					<view class="title-wrapp">
						<view class="c-title">
							<text class="l">历史记录</text>
						</view>
					</view>
					<view class="ul">
						<view class="li font-clamp" v-for="item in recordList" :key="item.citycode" @click="selectCity(item)">
							{{ item.name }}
						</view>
					</view>
				</view>
			</view>
			<!-- 城市列表  -->
			<view class="city-list">
				<view class="list list-item" v-for="(item, key) of cityList" :key="key" :id="item.nameType">
					<view class="c-title">{{ item.nameType }}</view>
					<view class="item" v-for="innerItem in item.list" :key="innerItem.citycode" @click="selectCity(innerItem)">
						{{ innerItem.name }}
					</view>
				</view>
			</view>
		</scroll-view>

		<!-- 字母列表 -->
		<view class="alphabet" @touchstart="touchStart" @touchend="touchEnd" @touchmove.stop="touchMove">
			<view v-for="(item, index) in alphabet" :key="index" @touchstart="getLetter" @touchend="setLetter" :id="item">
				<view class="item" :class="{ active: currentLetter == item }">
					{{ item == 'area' ? '当前' : item == 'record' ? '历史' : item }}
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	// 高德地图
	import amap from '@/components/amap-wx.130.js'
	import cityJson from '@/static/dataJson/city.json'
	export default {
		data() {
			return {
				isIPX: null,
				regionId: null, // 区域ID
				isToggle: true,
				isReach: false,
				inputValue: '',
				searchData: [], // 搜索的数据
				isClearBtn: false,
				toIndex: '', // 跳转的索引的字母
				tipsLetter: '', // 滑动显示字母
				timer: null,
				hasNoData: false,
				searchFocus: false,
				letterDetails: [],
				currentLetter: 'area', //默认选择
				cityArr: [],
				recordList: [],
				cityList: [],
				hasLocation: false,
				myCityObj: {},
				alphabet:[],
				key: '1f7cda077d0ba1353fde5548a5c44b8a',
			};
		},
		mounted() {
			this.cityArr = cityJson.data.list
			if (this.cityArr && this.cityArr[0]) {
				this.cityArr.map(v => {
					v.nameType = v.pinyin.substr(0, 1)
				})
				this.cityList = this.groupArr(this.cityArr, 'nameType')
			}
			this.recordList = cityJson.data.recordList
			this.alphabet = cityJson.data.alphabet
			this.getLocation()
		},
		watch: {
			// 城市搜索输入框
			inputValue(newVal) {
				this.isClearBtn = newVal ? true : false;

				if (this.timer) {
					clearTimeout(this.timer);
				}

				if (!this.inputValue) {
					this.searchData = [];
					return;
				}
				this.timer = setTimeout(() => {
					const result = [];
					this.cityList.map(v => {
						v.list.forEach((item) => {
							if (/^[a-zA-Z]+$/.test(item.pinyin) && item.pinyin.toLowerCase().includes(this.inputValue.toLowerCase()) ||
								item.name.includes(this.inputValue)) {
								result.push(item);
							}
						});
					})
					this.searchData = result;
					if (this.searchData.length === 0) {
						this.hasNoData = true;
					} else {
						this.hasNoData = false;
					}
				}, 500);
			},
			isReach(val) {
				this.searchFocus = val;
			},
		},
		methods: {
			getLocation() {
				uni.showLoading({
					title: '获取位置信息中'
				});
				new amap.AMapWX({
					key: this.key
				}).getRegeo({
					success: (data) => {
						console.log(data)
						var myCityObj = {
							name:data[0].regeocodeData.addressComponent.city.replace(/市$/, ''),
							citycode:data[0].regeocodeData.addressComponent.city 
						}
						this.myCityObj = myCityObj
						this.hasLocation = true
						uni.setStorageSync('nowCityObj', myCityObj)
						
						uni.hideLoading();
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
			groupArr(list, field) {
				var fieldList = [],
					att = [];
				list.map((e) => {
					fieldList.push(e[field])
				})
				//数组去重
				fieldList = fieldList.filter((e, i, self) => {
					return self.indexOf(e) == i
				})
				for (var j = 0; j < fieldList.length; j++) {
					//过滤出匹配到的数据
					var arr = list.filter((e) => {
						return e[field] == fieldList[j];
					})
					att.push({
						nameType: arr[0].nameType,
						list: arr
					})
				}
				return att;
			},
			selectCity(item,type) {
				if(type === 'refresh' && !this.hasLocation){
					// 获取定位
					return this.getLocation()
				}
				// console.log('选择的城市：', item);
				uni.setStorageSync('myCityObj', item)
				this.$emit('selectCity', item)
				// 当前项目是需要选择到区域，所以选择城市后回到区县的地方
				this.toIndex = 'area';
				setTimeout(() => {
					this.toIndex = '';
				}, 1000);
			},
			closeModal(){
				this.$emit('closeModal')
			},
			//列表滚动，和右边字母表对应
			scrollHandle(e) {
				let view = uni.createSelectorQuery().in(this).selectAll('.list-item');
				view
					.boundingClientRect((d) => {
						let top = d[0].top;
						d.forEach((item) => {
							item.top = item.top - top;
							item.bottom = item.bottom - top;
							this.letterDetails.push({
								id: item.id,
								top: item.top,
								bottom: item.bottom,
							});
						});
					})
					.exec();

				const scrollTop = e.detail.scrollTop;
				this.letterDetails.some((item) => {
					if (scrollTop >= item.top && scrollTop <= item.bottom - 20) {
						this.currentLetter = item.id;
						//当前固定用的是粘性定位，如果不用粘性定位，在这里设置
						return true;
					}
				});
			},

			//搜索
			searchChange(e) {
				let {
					value
				} = e.detail;
				this.inputValue = value;
			},
			// 触发开始
			touchStart(e) {
				// console.log(e);
			},
			//移动时
			touchMove(e) {
				uni.vibrateShort();
				let y = e.touches[0].clientY;
				let offsettop = e.currentTarget.offsetTop;

				//判断选择区域,只在选择区才会生效
				if (y > offsettop) {
					let num = parseInt((y - offsettop) / 15); //右边每个字母元素的高度
					let letter = this.alphabet[num];
					this.tipsLetter = letter;

					let curentLetter = this.letterTransform(letter);
					uni.showToast({
						title: curentLetter,
						icon: 'none',
					});
				}
			},
			//触发结束
			touchEnd() {
				this.toIndex = this.tipsLetter;
			},
			//移动开始获取字母，并放大提示
			getLetter(e) {
				uni.vibrateShort();
				let {
					id
				} = e.currentTarget;
				this.tipsLetter = id;

				let curentLetter = this.letterTransform(id);
				uni.showToast({
					title: curentLetter,
					icon: 'none',
				});
			},
			//移动结束设置字母，赋值到toIndex
			setLetter() {
				this.toIndex = this.tipsLetter;
			},

			//提示字母转换
			letterTransform(letter) {
				let str = '';
				if (letter == 'area') {
					str = '当前';
				} else if (letter == 'record') {
					str = '历史';
				} else {
					str = letter;
				}
				return str;
			},
		},
	}
</script>

<style lang="less" scoped>
	.chose-city {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		z-index: 99;
		background: #fff;
	}

	.city-search-wrap {
		width: 100%;
		box-sizing: border-box;

		.search {
			width: 750rpx;
			height: 110rpx;
			display: flex;
			align-items: center;
			font-size: 28rpx;
			color: #222;
			padding: 14rpx 36rpx;
			box-sizing: border-box;
			background: #fff;

			.l-search {
				width: 597rpx;
				position: relative;
				height: 72rpx;
				line-height: 72rpx;

				.icon-search {
					font-size: 28rpx;
					position: absolute;
					left: 30rpx;
					top: 0;
					color: #8e8f97;
					font-weight: 700;
					height: 72rpx;
					line-height: 72rpx;
				}
				.input-search {
					width: 597rpx;
					height: 72rpx;
					box-sizing: border-box;
					padding: 0 84rpx 0 84rpx;
					text-align: left;
					background: #f4f5f9;
					border-radius: 12rpx;
					border: 0;
				}

				.clear-input {
					font-size: 30rpx;
					position: absolute;
					right: 10rpx;
					top: 50%;
					transform: translateY(-50%);
					padding: 10rpx;
					color: #8e8f97;
				}
			}

			.r-cancel {
				width: 80rpx;
				box-sizing: border-box;
				padding-left: 24rpx;
				font-size: 28rpx;
				height: 72rpx;
				line-height: 72rpx;
				background: transparent;
				border: 0;
				color: #519AD2;
			}
		}
	}

	.reach-content {
		padding-left: 36rpx;
		box-sizing: border-box;
		.li {
			width: 714rpx;
			font-size: 28rpx;
			height: 100rpx;
			line-height: 100rpx;
			color: #333;
			position: relative;
			box-sizing: border-box;
			border-bottom: 2rpx solid #F5F5F5;
		}
	}

	.block {
		padding: 0 36rpx;
		box-sizing: border-box;
	}

	.top-search {
		line-height: 72rpx;
		padding: 14rpx 30rpx 0;
		box-sizing: border-box;
		margin-bottom: 26rpx;

		.item {
			background: #F5F5F5;
			border-radius: 12rpx;
			font-size: 28rpx;
			text-align: center;
			color: #999999;
			/* #ifdef MP-ALIPAY */
			height: 72rpx;
			line-height: 72rpx;

			/* #endif */
			text {
				padding-left: 20rpx;
				color: #c1c2cd;
				vertical-align: middle;
				position: relative;
				top: -4rpx;
			}

		}
	}

	.scroll-view {
		width: 100%;
		height: calc(100vh - 110rpx);
		box-sizing: border-box;
	}

	.area {
		margin-bottom: 8rpx;

		.title-wrapp {
			position: sticky;
			top: 0;
			left: 0;
			background: #fff;
		}

		.c-title {
			width: 100%;
			box-sizing: border-box;
			font-size: 28rpx;
			color: #999999;
			margin-bottom: 24rpx;
			display: inline-flex;
			justify-content: space-between;
			align-items: center;

			.r {
				font-size: 24rpx;
				color: #8e8f97;
				display: inline-block;
				align-items: center;

				.iconfont {
					font-size: 24rpx;
				}
			}
		}

		.ul {
			display: flex;
			flex-wrap: wrap;

			.li {
				width: 155rpx;
				padding: 0 10rpx;
				box-sizing: border-box;
				height: 72rpx;
				line-height: 68rpx;
				text-align: center;
				font-size: 32rpx;
				color: #333;
				border-radius: 8rpx;
				margin: 0 18rpx 28rpx 0;
				border: 2rpx solid #E2E2E2;

				&:nth-child(4n) {
					margin-right: 0;
				}

				&.now {
					width: auto;
					padding: 0 32rpx 0 22rpx;

					.icon {
						width: 50rpx;
						height: 50rpx;
						background-size: 100%;
						vertical-align: middle;
						position: relative;
						top: -4rpx;
					}

					.text {
						padding-left: 10rpx;
					}
				}

				&.active {
					font-weight: 500;
					background: #ffde45;
				}
			}

			.hover {
				background: #ffde45;
			}
		}
	}

	.city-list {
		width: 750rpx;
		padding-bottom: 50rpx;

		.c-title {
			height: 60rpx;
			line-height: 60rpx;
			font-size: 30rpx;
			font-weight: 500;
			color: #272636;
			background: #fff;
			box-sizing: border-box;
			padding-left: 36rpx;
			position: sticky;
			top: 0;
			left: 0;
			z-index: 2;
		}

		.item {
			width: 714rpx;
			margin-left: 36rpx;
			padding: 0 36rpx 0 0;
			height: 100rpx;
			line-height: 100rpx;
			color: #333;
			font-size: 28rpx;
			box-sizing: border-box;
			border-bottom: 2rpx solid #F5F5F5;
		}
	}

	.alphabet {
		position: fixed;
		right: 0;
		bottom: 20%;
		width: calc(750rpx - 680rpx);
		text-align: center;
		font-size: 20rpx;
		font-weight: 700;
		color: #8e8f97;
		z-index: 99;

		.item {
			height: 15px;
			line-height: 15px;
		}

		.active {
			color: #222;
		}
	}

	.has-no-data {
		font-size: 24rpx;
		text-align: center;
		color: #8e8f97;
		margin-top: 50rpx;
	}
</style>
