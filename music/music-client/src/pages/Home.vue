<template>
  <div class="home">
    <swiper></swiper>
    <div class="section" v-for="(item,index) in songsList" :key="index">
      <div class="section_title">{{item.name}}</div>
      <ContentList :contentList="item.list"></ContentList>
    </div>
  </div>
</template>

<script>
  import Swiper from '../components/Swiper';
  import ContentList from '../components/ContentList';
  import {
    getAllSinger,
    getAllSongList
  } from '../api/index';
  export default {
    name: 'home',
    data() {
      return {
        songsList: [{
            name: '歌单',
            list: []
          },
          {
            name: '歌手',
            list: []
          }
        ]
      }
    },

    created(){
      this.getSongList('songList');
      this.getSinger('singer');
    },

    methods: {
      //获取前10条歌单
      getSongList(path) {
        getAllSongList().then((res) => {
          this.songsList[0].list = res.slice(0, 15);
        }).catch((err) => {
          console.log(err);
        })
      },
      getSinger(){
        getAllSinger().then((res)=>{
          this.songsList[1].list = res.slice(0, 15);
        }).catch((err) => {
          console.log(err);
        })
      }
    },

    components: {
      Swiper,
      ContentList
    },
  }

</script>

<style>
  @import '../assets/css/home.css';

</style>
