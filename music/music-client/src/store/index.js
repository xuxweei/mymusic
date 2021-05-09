import Vue from 'vue'
import Vuex from 'vuex'
import config from './config'
import user from './song'
import song from './user'
import mv from './mv'
Vue.use(Vuex)
//存放缓存数据
const store = new Vuex.Store({
  //将 store 分割成模块
  modules:{
      config,
      user,
      song,
      mv
  }
})

export default store