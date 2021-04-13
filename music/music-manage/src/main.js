// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
/* eslint-disable */
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import 'babel-polyfill'
import VCharts from 'v-charts'
import './css/common.css'
import store from './store/index'
import ECharts from 'echarts'

Vue.config.productionTip = false
// 使用组件
Vue.use(ElementUI)
Vue.use(VCharts)
Vue.use(ECharts)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
