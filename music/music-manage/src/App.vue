<template>
  <div id="App">
    <SongPlay/>
    <!-- <img src="./assets/logo.png"> -->
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
import SongPlay from './components/SongPlay';
export default {
  name: 'App',
  provide(){
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      isRouterAlive:true
    }
  },
  methods:{
    reload(){
      this.isRouterAlive = false;
      this.$nextTick(function(){
        this.isRouterAlive = true
      })
    }
  },
  components:{
    SongPlay
  }
}
</script>

<style>
@import "css/common.css";
</style>
