# smh-select 下拉多条件筛选
# ### 安装方式

本组件符合[easycom](https://uniapp.dcloud.io/collocation/pages?id=easycom)规范，
`HBuilderX 2.5.5`起，只需将本组件导入项目，在页面`template`中即可直接使用，无需在页面中`import`和注册`components`，
如直接使用不行，则还是需要

### 基本用法

在 ``template`` 中的使用

```html
<!-- 基本用法 -->
<smh-select :list="list" @change="show"></smh-select>
data() {
	return {
		list:[
				{
					title:'距离',
					data:[
						{text:'1KM',identity:1,status:false},
						{text:'2KM',identity:2,status:false},
						{text:'3KM',identity:3,status:false},
						{text:'4KM',identity:4,status:false},
					]
				},
				{
					title:'排序',
					data:[
						{text:'好评优先',identity:1,status:false},
						{text:'销量优先',identity:2,status:false},
						{text:'价格优先',identity:3,status:false},
						{text:'配送优先',identity:4,status:false},
					]
				},
				{
					title:'时间',
					data:[
						{text:'三日内',identity:1,status:false},
						{text:'七日内',identity:2,status:false},
						{text:'一个月内',identity:3,status:false}
					]
				},
			]
	}
}
<!-- 基本用法 -->
@change对应的方法为选中值的集合，每选择一个选项，都会触发change事件，并返回全部筛选项，可在控制台Console查看返回数据
methods: {
	show(e){
		console.log(e)
	}
}
<!-- json属性解释 -->
identity可以理解为数据的id，因为获取数据之后，肯定要将id传入后台以获取返回数据，status为控制页面是否选中的状态