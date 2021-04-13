//用户相关
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
//存放缓存数据
const user = ({
  state: {
    //用户id
    userId: '',
    //用户名
    username: '',
    // 用户头像
    userpic: false,
  },
  getters: {
    userId: state => {
      let userId = state.userId;
      if (!userId) {
        userId = JSON.parse(window.sessionStorage.getItem('userId'));
      }
      return userId;
    },
    username: state => {
      let username = state.username;
      if (!username) {
        username = JSON.parse(window.sessionStorage.getItem('username'));
      }
      return username;
    },
    userpic: state => {
      let userpic = state.userpic;
      if (!userpic) {
        userpic = JSON.parse(window.sessionStorage.getItem('userpic'));
      }
      return userpic;
    }
  },
  mutations: {
    setUserId: (state, userId) => {
      state.userId = userId;
      window.sessionStorage.setItem('userId', JSON.stringify(userId));
    },
    setUsername: (state, username) => {
      state.username = username;
      window.sessionStorage.setItem('username', JSON.stringify(username));
    },
    setAvator: (state, userpic) => {
      state.userpic = userpic;
      window.sessionStorage.setItem('userpic', JSON.stringify(userpic));
    }
  }
})

export default user
