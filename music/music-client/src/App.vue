<template>
  <div id="app">
    <the-header></the-header>
    <router-view v-if="isRouterAlive" class="music_content"></router-view>
    <song-play></song-play>
    <the-aside-list></the-aside-list>
    <play-bar></play-bar>
    <go-top></go-top>
    <the-footer></the-footer>
  </div>
</template>

<script>
import GoTop from './components/GoTop.vue'
import PlayBar from './components/PlayBar.vue'
import SongPlay from './components/SongPlay.vue'
import TheFooter from './components/TheFooter.vue'
import TheHeader from './components/TheHeader.vue'
import TheAsideList from './components/TheAsideList.vue'
export default {
  components: { TheHeader, GoTop, TheFooter, SongPlay, PlayBar, SongPlay,TheAsideList },
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
}
</script>

<style>
@import './assets/css/app.css';
</style>
