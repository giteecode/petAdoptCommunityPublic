<script>
	import {mapMutations} from 'vuex'
	export default {
		onLaunch: function() {
			console.log('App Launch')
			// 同步获取本地数据
			const userInfo = uni.getStorageSync('userInfo');
			console.log('userInfo', userInfo)
			// 判断本地缓存是否存在数据  
			if (userInfo !== "") {
				// 传到vuex里面储存起来,并改变登录状态
				this.login(userInfo)
			}
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		methods: {
			...mapMutations(['login'])
		}
	}
</script>

<style>
	/*每个页面公共css */
</style>
