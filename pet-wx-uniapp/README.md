#选择城市，城市搜索弹窗
## 引入
import choseCity from "@/components/chose-city/chose-city"
export default {
	components: {
		choseCity
	},
	data() {
		return {
			showCity:true   // 默弹窗显示
		}
	},
	methods: { 
		selectCity(item) {
			console.log('-您选择的城市-',item)
			// this.showCity = false
		},
		closeModal() {
			this.showCity = false
		},
	}
}
## 页面引用
<chose-city @selectCity="selectCity" v-if="showCity" @closeModal="closeModal"></chose-city>
showCity：弹窗是否显示
selectCity：选择后的回调
closeModal：点击取消的回调