<template>
  <div class="header">
    <!-- 折叠按钮 -->
    <div class="collapse_btn" @click="collapseChange">
      <i class="el-icon-menu"></i>
    </div>
    <div class="logo">music后台管理</div>
    <div class="header_right">
        <!-- 全屏 -->
      <div class="btn_fullscreen" @click="handleFullScreen">
        <el-tooltip :content="fullscreen?'退出全屏':'全屏'" placement="bottom">
          <i class="el-icon-full-screen"></i></el-tooltip>
      </div>
      <!-- 用户头像 -->
      <div class="user_avator">
          <img src="../assets/img/user1.jpg"/>
      </div>
      <!-- 用户名 下拉菜单 包含退出登录 -->
      <el-dropdown trigger="click" class="user_name" @command="handleCommand">
      <span class="el-dropdown-link">
        {{userName}}<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item icon="el-icon-switch-button" command="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    </div>
  </div>
</template>

<script>
  import bus from '../assets/js/bus'
  export default {
    data() {
      return {
        collapse: false,
        fullscreen: false
      }
    },
    computed:{
        userName(){
            return sessionStorage.getItem('userName');
        }
    },

    methods: {
      //侧边栏折叠
      collapseChange() {
        this.collapse = !this.collapse;
        bus.$emit('collapse', this.collapse);
      },
      //全屏事件
      handleFullScreen() {
        if (this.fullscreen) {
          if (document.exitFullscreen) {
            document.exitFullscreen();
          }else if(document.webkitCancelFullScreen){ //safari或部分谷歌浏览器
              document.webkitCancelFullScreen();
          }else if(document.mozCancelFullScreen){ //firefox
                document.mozCancelFullScreen();
          }else if(document.msExitFullScreen){ //ie
              document.msExitFullScreen();
          }
        }else{
            let element = document.documentElement;
            if(element.requestFullscreen){
                element.requestFullscreen();
            }else if(element.webkitRequestFullSreen){ //safari或部分谷歌浏览器
                element.webkitRequestFullSreen();
            }else if(document.mozRequestFullScreen){ //firefox
                document.mozRequestFullScreen();
          }else if(document.msRequestFullScreen){ //ie
              document.msRequestFullScreen();
          }
        }
        this.fullscreen = !this.fullscreen;
      },
      //handleCommand 下拉菜单
      handleCommand(command){
          if(command == "logout"){
              sessionStorage.removeItem('userName');
              this.$router.push('/');
          }
      }

    },

    components: {},
  }

</script>

<style>
  @import '../css/header.css'

</style>
