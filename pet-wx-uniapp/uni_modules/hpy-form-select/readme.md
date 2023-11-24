
## hpy-form-select 下拉选择器 基于 [picker](https://uniapp.dcloud.io/component/picker?id=picker) 简单封装

> **组件名：hpy-form-select

从底部弹起的滚动选择器。支持五种选择器，通过mode来区分，分别是普通选择器，多列选择器，时间选择器，日期选择器，省市区选择器，默认是普通选择器

## API

### FilePicker Props

<table>
	<tr>
		<th>属性名</th>
		<th>类型</th>
		<th>默认值</th>
		<th>说明</th>
    </tr>
	<tr>
		<td>islot</td>
		<td>Boolean | String</td>
		<td>false</td>
		<td>是否使用插槽</td>
	</tr>
	<tr>
		<td>title</td>
		<td>String</td>
		<td>请选择</td>
		<td>默认标题</td>
	</tr>
	<tr>
		<td>mode</td>
		<td>String</td>
		<td>选择模式</td>
		<td>selector | multiSelector</td>
	</tr>
	<tr>
		<td>start</td>
		<td>String</td>
		<td></td>
		<td>表示有效日期范围的开始，字符串格式为"YYYY-MM-DD"</td>
	</tr>
	<tr>
		<td>end</td>
		<td>String</td>
		<td></td>
		<td>表示有效日期范围的结束，字符串格式为"YYYY-MM-DD"</td>
	</tr>
	<tr>
		<td>fields</td>
		<td>String</td>
		<td>day</td>
		<td>有效值 year、month、day，表示选择器的粒度，默认为 day，App 端未配置此项时使用系统 UI</td>
	</tr>
	<tr>
		<td>customItem</td>
		<td>String</td>
		<td></td>
		<td>可为每一列的顶部添加一个自定义的项</td>
	</tr>
	<tr>
		<td>dataList</td>
		<td>Array</td>
		<td></td>
		<td>选择器数据</td>
	</tr>
	<tr>
		<td>text</td>
		<td>String</td>
		<td></td>
		<td>显示值的key</td>
	</tr>
	<tr>
		<td>name</td>
		<td>String</td>
		<td></td>
		<td>保存值的key</td>
	</tr>
	<tr>
		<td>v-model | value</td>
		<td>Array | Object</td>
		<td></td>
		<td>绑定的数据</td>
	</tr>
	<tr>
		<td>disabled</td>
		<td>Boolean | String</td>
		<td>false</td>
		<td>是否禁用</td>
	</tr>
	<tr>
		<td>hideBorder</td>
		<td>Boolean | String</td>
		<td>false</td>
		<td>是否隐藏边框</td>
	</tr>
	<tr>
		<td>hideArrow</td>
		<td>Boolean | String</td>
		<td>false</td>
		<td>是否隐藏箭头</td>
	</tr>
</table>


## 使用示例
## dataList 为对象数组时，name为实际保存的value值，text为显示的值
## dataList 为对象数组[{name:'张三', age:'18'}, {name:'李四', age:'19'}]时，需要设置name、text属性，name为实际保存的value值，text为显示的值
## <hpy-form-select :dataList="dataList" text="name" name="age" v-model="formData.age" @change="change" />
## ======================================================================================================
## dataList 为普通数组['张三', '李四'] name、text可不设置
## <hpy-form-select :dataList="dataList" v-model="formData.name" @change="change" />
## 类似：
## <select><option value="name">text</option></select>


```html
	<hpy-form-select :dataList="hobbyList" text="text" name="value" v-model="formData.hobbySelect" @change="change" />
	 
	<uni-group title="基础信息" margin-top="0">
		<uni-forms-item label="姓名" name="name">
			<uni-easyinput type="text" v-model="formData.name" placeholder="请输入姓名" />
		</uni-forms-item>
		
		<uni-forms-item label="爱好2" name="hobby2">
			<hpy-form-select :dataList="hobbyList" text="text" name="value" v-model="formData.hobbySelect"/>
		</uni-forms-item>
		
		<uni-forms-item label="爱好3" name="hobby3">
			<hpy-form-select :dataList="hobbyList" text="text" name="value" v-model="formData.hobbySelect3" islot="true">
				<view class="my-select">可自定义显示</view>
			</hpy-form-select>
		</uni-forms-item>
		
		<uni-forms-item label="出生时间(年)" name="birthYear" >
			<hpy-form-select mode="date" fields="year" v-model="formData.birthYear" />
		</uni-forms-item>
	</uni-group>
```

```javascript
export default {
		data() {
			return {
				formData:{
					hobbySelect:''
				},
				hobbyList: [{
					text: '足球',
					value: 1
				}, {
					text: '篮球',
					value: 2
				}, {
					text: '游泳',
					value: 3
				}],
			}
		},
		methods:{
			change(e){
				console.log(e.index);
				console.log(e.value);
				console.log(e.data);
			}
		}
}

```