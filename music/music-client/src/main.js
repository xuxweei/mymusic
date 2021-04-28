/*
 * @Author: your name
 * @Date: 2021-04-20 09:33:13
 * @LastEditTime: 2021-04-28 13:49:37
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: \Vue\mymusic\music\music-client\src\main.js
 */
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './assets/css/common.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import store from './store/index'
import './assets/iconfont/iconfont.css'
import axios from 'axios'
import VueAxios from "vue-axios";
import VueLazyload from 'vue-lazyload'
import VideoPlayer from 'vue-video-player'
import VueParticles from 'vue-particles'

require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')
//引入 hls,视频直播(m3u8)必须引入的
import 'videojs-contrib-hls'
//播放rtmp视频
import 'videojs-flash'
//如果你需要自定义播放器的样式，自己新建一个css
require('./assets/css/myvideo.css')
Vue.use(VideoPlayer)
Vue.use(VueParticles)

Vue.use(VueLazyload)

// or with options
Vue.use(VueLazyload, {
  // preLoad: 1.3,
  error: require('./assets/img/error.gif'),
  loading: require('./assets/img/loading.gif'),
  // attempt: 1
})

Vue.use(VueAxios, axios)

require('./assets/iconfont/iconfont.js')
Vue.config.productionTip = false
// 使用组件
Vue.use(ElementUI)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
