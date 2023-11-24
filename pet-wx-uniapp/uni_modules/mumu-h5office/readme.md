# mumu-h5office

## 说明

h5office是兼容 `浏览器端(h5)`  `iOS app端`  `Android app端` 的文档预览工具。支持权限配置 `下载`  `拷贝文字` `打印`。支持app端本地文件预览，网络文件预览。手机端支持触屏操作双指放大缩小，pc端UI美观好看

### 必读

服务器资源有限，每个appid都会有次数限制，

使用次数可以在小程序中进行刷新，刷新次数是没有限制的(需观看广告)

小程序中可以设置次数不足邮件提醒，当次数低于设置的数量时会通过邮件进行提示

当前插件的后端服务是共用一台 2核心、4G内存、30M带宽 服务器

可能在高峰期的时候文件预览速度会变慢。

如果对预览文件速度、预览次数有大量的需求，或者大文件预览。请联系开发者部署私有化的版本。

#### 本地文件预览功能说明

本地文件预览的本质，其实是先把文件上传到服务器，再通过服务器进行解析。

如果对文件隐私有需求，只有选择服务器端的服务进行私有部署。

[私有部署说明](https://h5office.cn/price)

### Appid 申请方法

![img](https://h5office.cn/images/getAppid.jpg)



## 简单使用	

**插件已支持 uni_modules 支持组件easycom，以下代码演示的是普通使用**

```html
<!-- HTML -->
<mumu-h5office v-model="isShow" :file="inputFile" appid="你申请的Appid"></mumu-h5office>
<button @click="openFile">打开网络文件</button>
```

```javascript
// script
import mumuH5office 
from "@/uni_modules/mumu-h5office/components/mumu-h5office/mumu-h5office.vue"
export default {
  components: { mumuH5office },
  data() {
    inputFile: '',
    isShow: false,
  },
  methods: {
    openFile() {
      this.inputFile = "https://h5plugin.mumudev.top/public/previewOffce/333.docx"
      this.isShow = true
    }
  }
}
```



## [官方文档：https://h5office.cn](https://h5office.cn)

## 预览

[浏览器中直接打开：https://h5plugin.mumudev.top/#/pages/h5office/h5office](https://h5plugin.mumudev.top/#/pages/h5office/h5office)



扫码预览：

![h5office预览](https://h5plugin.mumudev.top/public/h5office/qrcode.png)

下载完整演示代码、安卓app：

[官网下载页面：https://h5office.cn/uni-app/%E5%AE%89%E8%A3%85](https://h5office.cn/uni-app/%E5%AE%89%E8%A3%85)



## 属性、事件、其他组件

请前往官网进行查看：

[属性和事件文档说明：https://h5office.cn/uni-app/%E5%B1%9E%E6%80%A7%E5%92%8C%E4%BA%8B%E4%BB%B6](https://h5office.cn/uni-app/%E5%B1%9E%E6%80%A7%E5%92%8C%E4%BA%8B%E4%BB%B6)



## 支持作者

![支持作者](https://student.mumudev.top/wxMP.jpg)
