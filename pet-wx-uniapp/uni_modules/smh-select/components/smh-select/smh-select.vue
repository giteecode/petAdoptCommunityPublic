<template>
	<view>
		<view class="select">
			<view class="select1">
				<view class="order">
					<view :class="{cur:curIndex==index,active1:curIndex==index}" v-for="(item,index) in list1" :key="index" class="order_1" @click="show(index)">{{item.title}}</view>
				</view>
			</view>
			<view v-if="curIndex==index" v-for="(item,index) in list1" :key="index" class="order1-1" style="height: 650rpx; overflow: auto;">
				<view class="order1">
					<view @click="selectItem(index1,index)" :class="{active:item1.status}" v-for="(item1,index1) in item.data" :key="index1">
						{{item1.text}}
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'smh-select',
		props:{
			list:{
				type:Array,
				default () {
					return []
				}
			}
		},
		data() {
			return {
				list1:this.list,
				curIndex:null,
				result:[]
			}
		},
		methods: {
			show(i){
				if(this.curIndex==i){
					this.curIndex=null
				}else{
					this.curIndex=i;
				}
				
			},
			selectItem(i,j){
				this.list1[j].data.forEach(el=>{
					el.status=false
				})
				this.list1[j].data[i].status=true
				this.list[j].title=this.list1[j].data[i].text
				//this.$set(this.list1[j].data[i],'status',true)
				this.curIndex=null
				var that = this
				this.result.length=0
				this.list1.forEach(el=>{
					el.data.forEach(el1=>{
						if(el1.status){
							that.result.push(el1)
						}
					})
				})
				// this.$emit('change',this.result)
				console.log(i,j)
			}
		}
	}
</script>

<style lang="scss" scoped>
	@font-face {
	  font-family: 'iconfont';
	  src: url('https://at.alicdn.com/t/font_2453967_lwt4zfkc2c.eot');
	  src: url('https://at.alicdn.com/t/font_2453967_lwt4zfkc2c.eot?#iefix') format('embedded-opentype'),
	  url('https://at.alicdn.com/t/font_2453967_lwt4zfkc2c.woff2') format('woff2'),
	  url('https://at.alicdn.com/t/font_2453967_lwt4zfkc2c.woff') format('woff'),
	  url('https://at.alicdn.com/t/font_2453967_lwt4zfkc2c.ttf') format('truetype'),
	  url('https://at.alicdn.com/t/font_2453967_lwt4zfkc2c.svg#iconfont') format('svg');
	}
.select{
	width: 100%;
	// height: 70rpx;
	// background-color: #fff;
	// z-index: 999;
	.select1{
		width: 420rpx;
		margin: 0 auto;
		height: 100%;
		display: flex;
		line-height: 70rpx;
		color: #969696;
		font-size: 28rpx;
		float: right;
		
		.order{
			width:calc(100% - 22rpx);
			display: flex;
			justify-content:space-between;
			.active1{
					color: #35CDC1;
					&::after{
						content: "\e6ee" !important;
						font-size: 22rpx;
						position: absolute;
						font-family: "iconfont" !important;
						color: #35CDC1;
						top: 2rpx;
					}
				
			}
			.order_1{
				position: relative;
				&::after{
					content: "\e6ed";
					font-size: 22rpx;
					position: absolute;
					font-family: "iconfont" !important;
					top: 2rpx;
				}
			}
			.order_2{
				position:relative;
				&::after{
					content: "\e6ed";
					font-size: 22rpx;
					position: absolute;
					font-family: "iconfont" !important;
					top: 2rpx;
				}
			}
			.order_3{
				margin-left: auto;
				position:relative;
				&::after{
					content: "\e6ed";
					font-size: 22rpx;
					position: absolute;
					font-family: "iconfont" !important;
					top: 2rpx;
				}
			}
		}
	}
	.order1-1{
		background-color: #fff;
		position: relative;
		z-index: 999;
		&>view{
			width: 690rpx;
			margin: 0 auto;
		}
		.handle{
			padding-bottom: 20rpx;
			display: flex;
			.left{
				width: 260rpx;
				height: 76rpx;
				background: #F8F8F8;
				border-radius: 40rpx;
				line-height: 76rpx;
				text-align: center;
				color: #999999;
				font-size: 28rpx;
			}
			.right{
				margin-left: auto;
				width: 400rpx;
				height: 76rpx;
				background: #35CDC1;
				border-radius: 40rpx;
				line-height: 76rpx;
				text-align: center;
				color: #FFFFFF;
				font-size: 28rpx;
			}
		}
		.gender,.age,.sign{
			.toptitle{
				color: #333333;
				font-size: 28rpx;
				display: block;
				padding-top: 30rpx;
				margin-bottom: 30rpx;
			}
			.gender1{
				display: flex;
				flex-wrap: wrap;
				view{
					width: 154rpx;
					height: 56rpx;
					background: #F8F8F8;
					border-radius: 30rpx;
					line-height: 56rpx;
					text-align: center;
					margin-right: 24rpx;
					margin-bottom: 24rpx;
					&:nth-child(4n){
						margin-right: 0;
					}
					text{
						color: #666666;
						font-size: 22rpx;
					}
				}
				.active{
					background-color: rgba(53,205,193,0.1);
					text{
						color: #35CDC1;
					}
				}
			}
		}
		// 城市选择
		.order1{
			width: 690rpx;
			//height: 500rpx;
			margin: 0 auto;
			background-color:#fff;
			//position: absolute;
			top: 80rpx;
			.active{
				color:#35CDC1;
				&::after{
					position: absolute;
					right: 0;
					font-family: "iconfont" !important;
					color: #35CDC1;
					font-size: 30rpx;
					font-style: normal;
					content: "\e6f5";
				}
			}
			&>view{
				width: 100%;
				height: 70rpx;
				font-size: 28rpx;
				font-family: PingFang SC;
				font-weight: 400;
				line-height: 70rpx;
				color: #333333;
				opacity: 1;
				border-bottom: 1px solid #F2F2F2;
				position: relative;
				&:last-child{
					border-bottom: 0;
				}
				
			}
		}
	}
	.cur{
		color: #35CDC1;
	}
}
</style>
