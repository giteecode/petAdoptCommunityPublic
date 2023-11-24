<template>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		onLoad(e) {
			// this.getLocation()
			// this.chooseLocation()
			// this.viewMapLocation('120.210825', '30.209049', '111')
		},
		methods: {
			viewMapLocation(longitude, latitude, address) {
				//  这里因为我是子组件传参过来了导致 经纬度变成了String 类型所以进行了一次转换
				latitude = Number(latitude);
				longitude = Number(longitude);
				// 获取定位信息
				uni.getLocation({
					type: 'wgs84', //返回可以用于uni.openLocation的经纬度
					// 用户允许获取定位
					success: function(res) {
						console.log(res, '经纬度')
						if (res.errMsg == "getLocation:ok") {
							console.log(latitude)
							console.log(longitude)
							uni.openLocation({
								// 传入你要去的纬度
								latitude: latitude,
								// 传入你要去的经度
								longitude: longitude,
								// 传入你要去的地址信息 不填则为空
								address: address,
								// 缩放大小
								scale: 18,
								success: function() {
									console.log('success');
								}
							});
						}
					},
					// 用户拒绝获取定位后 再次点击触发
					fail: function(res) {
						console.log(res)
						if (res.errMsg == "getLocation:fail auth deny") {
							uni.showModal({
								content: '检测到您没打开获取信息功能权限，是否去设置打开？',
								confirmText: "确认",
								cancelText: '取消',
								success: (res) => {
									if (res.confirm) {
										uni.openSetting({
											success: (res) => {
												console.log('确定');
											}
										})
									} else {
										console.log('取消');
										return false;
									}
								}
							})
						}
					}
				});
			},
			getLocation() {
				uni.getLocation({
					type: 'gcj02', //返回可以用于uni.openLocation的经纬度
					success: function(res) {
						const latitude = res.latitude;
						const longitude = res.longitude;
						console.log('1',res)
						uni.openLocation({
							latitude: latitude,
							longitude: longitude,
							success: function() {
								console.log('success');
							}
						});
					}
				});
			},
			chooseLocation() {
				uni.chooseLocation({
				    success: function (res) {
				        console.log('位置名称：' + res.name);
				        console.log('详细地址：' + res.address);
				        console.log('纬度：' + res.latitude);
				        console.log('经度：' + res.longitude);
				    }
				});
			}
		}
	}
</script>

<style>
</style>
