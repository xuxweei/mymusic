<template>
  <div class="content_list">
        <ul class="section_content">
          <li class="content_item" v-for="(item,index) in classifySongList" :key="index">
            <div class="kuo" @click="goAlbum(item,item.name)">
              <img :src="attachImageUrl(item.pic)" class="item_img">
              <div class="mask">
                <svg t="1608619694217" class="icon" viewBox="0 0 1024 1024" version="1.1"
                  xmlns="http://www.w3.org/2000/svg" p-id="3410" width="200" height="200">
                  <path d="M512 512m-512 0a512 512 0 1 0 1024 0 512 512 0 1 0-1024 0Z" fill="#555555" opacity=".3"
                    p-id="3411"></path>
                  <path
                    d="M677.1712 555.8272L435.456 681.984a51.2 51.2 0 0 1-74.9056-44.4928L356.352 393.6256a51.2 51.2 0 0 1 73.2672-47.104l246.016 117.76a51.2 51.2 0 0 1 1.5872 91.5456z"
                    fill="#FFFFFF" p-id="3412"></path>
                </svg>
              </div>
            </div>
            <p class="item_name">{{item.name||item.title}}</p>
          </li>
        </ul>

  </div>
</template>

<script>
  import {
    mixin
  } from '../mixins/index';
  export default {
    name: 'classifySongList',
    props: ['classifySongList'],
    mixins: [mixin],
    data() {
      return {}
    },

    methods: {
      goAlbum(item,type){
        this.$store.commit('setTempList',item);
        console.log(item);
        // 歌手
        if (type) {
          this.$router.push({
            path:`singerAlbum/${item.id}`
          })
        }else{
          //歌单
          this.$router.push({
            path:`songListAlbum/${item.id}`
          })
        }
      }
    },

    components: {},
  }

</script>

<style>
  @import '../assets/css/contentlist.css';
</style>
