## 导入即用 全端支持

### 有问题 + wx : zy597172583  标注来意 可评论 看到及时回复
1.使用方式

```javascript
<template>
    <filter-popup :data="filterData" :form.sync="filterForm" v-model="popup.filter" title="全部筛选"              height="1104rpx" @finsh="subFinsh"></filter-popup>
</template>
<script>
import FilterPopup from "@/components/filter-popup/filter-popup";
export default {
    components: {
        FilterPopup,
    },
     data() {
        return {
            //筛选表单数据
            filterData: [
                {
                    children: false,//是否有子项
                    title: "意向度",
                    key: "intention_type", //键名 接收对象名字
                    keyValue: "value", //获取的值是哪个
                    isRadio: true, //是否单选 否则多选
                    data: [
                        {
                            title: "一般意向",
                            id: 1,
                            value: 1,
                        },
                        {
                            title: "中意向度",
                            id: 2,
                            value: 2,
                        },
                        {
                            title: "高意向度",
                            id: 3,
                            value: 3,
                        },
                    ],
                },
                {
                    children: false,//是否有子项
                    title: "手机号码",
                    key: "is_bind_phone", //键名 接收对象名字
                    keyValue: "value", //获取的值是哪个
                    isRadio: true, //是否单选 否则多选
                    data: [
                        {
                            title: "未绑定",
                            value: 0,
                        },
                        {
                            title: "已绑定",
                            value: 1,
                        },
                    ],
                },
                {
                    children: false,//是否有子项
                    title: "企微好友",
                    key: "is_work_customer", //键名 接收对象名字
                    keyValue: "value", //获取的值是哪个
                    isRadio: true, //是否单选 否则多选
                    data: [
                        {
                            title: "未添加",
                            value: 0,
                        },
                        {
                            title: "已添加",
                            value: 1,
                        },
                    ],
                },
                {
                    children: true,//是否有子项
                    isRadio: false, //是否单选
                    title: "标签内容",
                    key: "label", //键名 接收对象名字
                    keyValue: "id", //获取的值是哪个
                    data: [
                        {
                            title: "客户重要程度",
                            id: 22,
                            children: [
                                {
                                    title: "一般意向2",
                                    id: 32,
                                    value: 1,
                                },
                                {
                                    title: "一般意向3",
                                    id: 12,
                                    value: 1,
                                },
                            ],
                        },
                        {
                            title: "客户重要程度2",
                            id: 122,
                            children: [
                                {
                                    title: "一般意向2",
                                    id: 43,
                                    value: 1,
                                },
                                {
                                    title: "一般意向3",
                                    id: 23,
                                    value: 1,
                                },
                            ],
                        },
                    ],
                },
            ], //筛选数据
            filterForm: {}, //选中的表单
        };
    },
}
```

2.组件props

| 参数名     | 类型    | 介绍                                              |
| ---------- | ------- | ------------------------------------------------- |
| form       | Object  | .sync双向绑定的表单值 ， 可传入显示初始哪些被选中 |
| data       | Array   | 动态渲染选项的数据数组                            |
| title      | String  | 标题                                              |
| height     | String  | 弹出层高度 单位 rpx px upx 百分比 vw等            |
| themeColor | String  | 组件主体颜色  默认：\#0066ff                      |
| mask       | Boolean | 是否显示弹出遮盖层                                |

3.data 参数

| 参数名   | 类型    | 是否必填 | 介绍                                                         |
| -------- | ------- | -------- | ------------------------------------------------------------ |
| children | Boolean | 是       | 是否有子项                                                   |
| data     | Array   | 是       | 渲染出来的选项数据                                           |
| isRadio  | Boolean | 是       | 是否单选  单个选项指定，单选还是多选                         |
| title    | String  | 是       | 标签内容标题                                                 |
| key      | String  | 是       | 接收对象名字 会作为@finsh返回对象的键名                      |
| keyValue | String  | 是       | 获取的值是哪个 自定义指定获取哪个键值 value还是id或者自己定义的 |

4.事件

| 事件名 | 返回参数 | 简介                                      |
| ------ | -------- | ----------------------------------------- |
| finsh  | Object   | 点击确定时触发 返回参数为选中值的对象数组 |
| close  | 无       | 组件点击关闭时触发                        |

![image-20210730095456900](https://yzhsaas-cdn.qietongvip.com/asd.png)
