import App from './App'

import share from './store/share.js'
Vue.mixin(share)

import store from './store'   //引入vuex
// 挂载vuex
Vue.prototype.$store = store;

import request from './common/http.js'
Vue.prototype.$request = request

// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
    ...App,
	store 
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif