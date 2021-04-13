<template>
  <div class="content_list">
    <ul class="playlist_header">
      <li class="playlist_header_name">歌单</li>
      <li class="playlist_header_author">创建人</li>
    </ul>
    <ul class="playlist_list">
      <li class="playlist_item" v-for="(item,index) in songListContent" :key="index">
        <div class="kuo" @click="goAlbum(item,item.name)">
          <img :src="attachImageUrl(item.pic)" class="item_img">
        </div>
        <h4 class="item_name">
          <span>{{item.name||item.title}}</span>
          <div class="playlist_author">{{item.author}}</div>
        </h4>
      </li>
    </ul>
  </div>
</template>

<script>
  import {
    mixin
  } from '../mixins/index';
  export default {
    name: 'songListContent',
    props: ['songListContent'],
    mixins: [mixin],
    data() {
      return {}
    },

    methods: {
      goAlbum(item, type) {
        this.$store.commit('setTempList', item);
        console.log(item);
        // 歌手
        if (type) {
          this.$router.push({
            path: `singerAlbum/${item.id}`
          })
        } else {
          //歌单
          this.$router.push({
            path: `songListAlbum/${item.id}`
          })
        }
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import '../assets/css/songlistcontent.css';

</style>
