import Vue from 'vue'
import Router from 'vue-router'

import login from '../pages/Login.vue'
import index from '../pages/Index.vue'
import home from '../components/Home.vue'
import consumer from '../pages/Consumer.vue'
import singer from '../pages/Singer.vue'
import song from '../pages/Song.vue'
import songlist from '../pages/SongList.vue'
import community from '../pages/Community.vue'
import listsong from '../pages/ListSong.vue'
import collect from '../pages/Collect.vue'
import comment from '../pages/Comment.vue'
import resource from '../pages/Resource.vue'



Vue.use(Router)

const router = new Router({
  linkActiveClass: 'router-active',
  routes: [{
      path: '/',
      redirect: '/login'
    }, {
      path: '/login',
      component: login
    },
    {
      path: '/home',
      component: home,
      children: [{
          path: '/index',
          component: index
        },
        {
          path: '/consumer',
          component: consumer
        },
        {
          path: '/singer',
          component: singer
        },
        {
          path: '/songlist',
          component: songlist
        },
        {
          path: '/song',
          component: song
        }, 
        {
          path: '/listsong',
          component: listsong
        }, 
        {
          path: '/community',
          component: community
        }, 
        {
          path: '/collect',
          component: collect
        }, 
        {
          path: '/comment',
          component: comment
        },
        {
          path: '/resource',
          component: resource
        }
      ]
    }
  ]
})
/* eslint-disable */
export default router
