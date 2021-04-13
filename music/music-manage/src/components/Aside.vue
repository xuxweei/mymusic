<template>
  <div class="sidebar">
    <!-- 使用了 router 会自动转向相对应的页面 -->
    <el-menu 
      class="el-menu-vertical-demo sidebar_el_menu" 
      background-color="#545c64" 
      text-color="#fff"
      active-text-color="#ffd04b" 
      :default-active="onRoutes" 
      :collapse="collapse" 
      router
      @open="handleOpen"
      @close="handleClose">
      <template v-for="item in items">
        <template>
          <el-menu-item :index="item.index" :key="item.index" >
            <i :class="item.icon"></i>
            <span>{{item.title}}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
  import bus from "../assets/js/bus"
  export default {
    data() {
      return {
        collapse: false,
        items: [{
            icon: 'el-icon-location',
            index: 'index',
            title: '系统首页'
          },
          {
            icon: 'el-icon-user',
            index: 'consumer',
            title: '用户管理'
          },
          {
            icon: 'el-icon-document',
            index: 'singer',
            title: '歌手管理'
          },

          {
            icon: 'el-icon-setting',
            index: 'songList',
            title: '歌单管理'
          },
          {
            icon: 'el-icon-house',
            index: 'community',
            title: '社区管理'
          }
        ]
      }
    },

    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },

    components: {},

    computed: {
      onRoutes() {
        return this.$route.path.replace('/', '');
      }
    },

    created() {
      //通过bus进行组件间的通信，来折叠侧边栏
      bus.$on('collapse', msg => {
        this.collapse = msg;
      })
    }
  }

</script>

<style>
  @import '../css/aside.css'

</style>
