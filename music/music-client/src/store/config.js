import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
//存放缓存数据
const config = ({
  state: {
    //后台访问地址根目录
    HOST: 'http://localhost:8888',
    //当前选中的菜单名
    activeName: '',
    // 是否显示歌曲播放列表
    showAside: false,
    // 用户是否已登录
    loginIn: false,
    // 当前歌曲是否已收藏
    isCollect: false,
    collectBtn: '#icon-xihuan',
  },
  getters: {
    activeName: state => {
      let activeName = state.activeName;
      if (!activeName) {
        activeName = JSON.parse(window.sessionStorage.getItem('activeName'));
      }
      return activeName;
    },
    showAside: state => {
      let showAside = state.showAside;
      if (!showAside) {
        showAside = JSON.parse(window.sessionStorage.getItem('showAside'));
      }
      return showAside;
    },
    loginIn: state => {
      let loginIn = state.loginIn;
      if (!loginIn) {
        loginIn = JSON.parse(window.sessionStorage.getItem('loginIn'));
      }
      return loginIn;
    },
    isCollect: state => {
      let isCollect = state.isCollect;
      if (!isCollect) {
        isCollect = JSON.parse(window.sessionStorage.getItem('isCollect'));
      }
      return isCollect;
    },
    collectBtn: state => {
      let collectBtn = state.collectBtn;
      if (!collectBtn) {
        collectBtn = JSON.parse(window.sessionStorage.getItem('collectBtn'));
      }
      return collectBtn;
    },
  },
  mutations: {
    setActiveName: (state, activeName) => {
      state.activeName = activeName;
      window.sessionStorage.setItem('activeName', JSON.stringify(activeName));
    },
    setShowAside: (state, showAside) => {
      state.showAside = showAside;
      window.sessionStorage.setItem('showAside', JSON.stringify(showAside));
    },
    setLoginIn: (state, loginIn) => {
      state.loginIn = loginIn;
      window.sessionStorage.setItem('loginIn', JSON.stringify(loginIn));
    },
    setIsCollect: (state, isCollect) => {
      state.isCollect = isCollect;
      window.sessionStorage.setItem('isCollect', JSON.stringify(isCollect));
    },
    setCollectBtn: (state, collectBtn) => {
      state.collectBtn = collectBtn;
      window.sessionStorage.setItem('collectBtn', JSON.stringify(collectBtn));
    },
  }
})

export default config
