<template>
	<view class="style-flex style-flex-wrap">
		<filter-radio-item v-for="(item, idx) in items" :key="idx" :name="item.name" :value="item.value" :checked="item.checked" @change="radioChange"></filter-radio-item>
	</view>
</template>

<script>
	import filterRadioItem from './axb-checkbox-item.vue'
	export default {
		props:{
			list:{
				type:Array,
				default: []
			},
			multi:{
				type:Boolean,
				default:false
			}
		},
		components:{
			filterRadioItem
		},
		data() {
			return {
				items: this.list,
				resMulti: [],
			};
		},
		created(){
		},
		computed:{
		},
		methods:{
			handleChange:function(val){
				const arr = []
				// 是否为全部
				if(val==='all'){
					let allr = this.items.find((n) => n.value === val)
					this.items.forEach(function(item){
						let re = {}
						re.name = item.name
						re.value = item.value
						re.checked= !allr.checked
						arr.push(re)
					})
				} else{
					for(let i =0; i<this.items.length;i++){
						let r = {}
						r.name = this.items[i].name
						r.value = this.items[i].value
						if(this.multi){ // 多选
							if(this.items[i].value === val){ // 选中项处理
								r.checked = !this.items[i].checked
							}
							else{
								r.checked=this.items[i].checked
							}
						}else{ // 单选
							if(this.items[i].value === val){ // 选中项处理
								r.checked = !this.items[i].checked
							}else{
								r.checked = false
							}	
						}
						arr.push(r)
					}
				}
				// 判断是否全选 全选按钮未全选
				if(this.multi&&arr[0].value==='all'){
					const select = arr.slice(1)
					let r = select.every(function(val){
						return val.checked
					})
					arr[0].checked = r
				}
				this.items = arr
			},
			radioChange: function (e) {
				console.log('radio发生change事件，携带value值为：' + e)
				// 改变单击项 
				this.handleChange(e)
				const re = []
				if(this.multi){
					this.items.forEach(function(val){
						if(val.checked){
							re.push(val.value)
						}
					})
				}
				let r = this.items.find((val)=>{
					return val.value === e
				})
				let result = this.multi?re:r.checked?e:null
				// 返回选择项
				this.$emit('change',result)
			},
			reset:function(){
				this.items=this.list
			},
			setItems:function(val){
				this.items = val
			}
		}
	}
</script>

<style>
 .radio{
	display: inline-block;
	height: 60upx;
	line-height: 60upx;
	padding: 0 22upx;
	background: #f7f7f7;
	border: 1px solid #c3c3c3;
	border-radius: 10upx;
	font-size: 26upx;
	color: #7d7d7d;
	box-sizing: border-box;
 }
 .radio:not(:last-child){
	 margin: 0 20upx 30upx 0;
 }
 .radio-primay{
	 background: #d5e4fd;
	 color: #3c7ef6;
	 border: 1px solid #3c7ef6;
 }
 .radio-width-default{
	 width: 120upx;
	 text-align: center;
	 padding: 0;
 }
</style>
