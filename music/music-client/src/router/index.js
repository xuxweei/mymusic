import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/home.vue'
import SongList from '../pages/SongList.vue'
import Singer from '../pages/Singer.vue'
import Mv from '../pages/MV.vue'
import Community from '../pages/Community.vue'
import MyMusic from '../pages/MyMusic.vue'
import Search from '../pages/Search.vue'
import Lyric from '../pages/Lyric.vue'
import SignUp from '../pages/SignUp.vue'
import LoginIn from '../pages/LoginIn.vue'
import Setting from '../pages/Setting.vue'
import SingerAlbum from '../pages/SingerAlbum.vue'
import SongListAlbum from '../pages/SongListAlbum.vue'
import PlayMV from '../pages/PlayMV.vue'
Vue.use(Router)
//获取原型对象上的push函数
const originalPush = Router.prototype.push
//修改原型对象中的push方法
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  routes: [{
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/songList',
      name: 'SongList',
      component: SongList
    },
    {
      path: '/singer',
      name: 'Singer',
      component: Singer
    },
    {
      path: '/mv',
      name: 'Mv',
      component: Mv
    },
    {
      path: '/community',
      name: 'Community',
      component: Community
    },
    {
      path: '/myMusic',
      name: 'MyMusic',
      component: MyMusic
    },
    {
      path: '/search',
      name: 'Search',
      component: Search
    },
    {
      path: '/lyric',
      name: 'Lyric',
      component: Lyric
    },
    {
      path: '/signUp',
      name: 'SignUp',
      component: SignUp
    },
    {
      path: '/loginIn',
      name: 'LoginIn',
      component: LoginIn
    },
    {
      path: '/setting',
      name: 'Setting',
      component: Setting
    },
    {
      path: '/songListAlbum/:id',
      name: 'SongListAlbum',
      component: SongListAlbum
    },
    {
      path: '/singerAlbum/:id',
      name: 'SingerAlbum',
      component: SingerAlbum
    },
    {
      path: '/playmv',
      name: 'PlayMV',
      component: PlayMV
    },
  ],
  //轮播
  scrollBehavior(to, from, savedPosition) {
    return {
      x: 0,
      y: 0
    }
  }
})
