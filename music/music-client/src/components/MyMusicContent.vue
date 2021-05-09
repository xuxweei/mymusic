<!--
 * @Author: your name
 * @Date: 2021-04-20 09:33:13
 * @LastEditTime: 2021-04-30 11:45:24
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \Vue\mymusic\music\music-client\src\components\MyMusicContent.vue
-->
<template>
  <div class="content">
    <h4 class="title" style="border-bottom:1px solid #000;padding-bottom:5px;">
      <slot name="title"></slot>
    </h4>
    <ul>
      <li class="list_title" flagid="0">
        <div class="song_item">
          <span class="item_index"></span>
          <!-- 歌曲名 -->
          <span class="item_title">歌曲名</span>
          <!-- 歌手 -->
          <span class="item_name">歌手</span>
          <!-- 专辑 -->
          <span class="item_intro">专辑</span>
        </div>
      </li>
      <li class="lis" v-for="(item, index) in songList" :key="index" @click="changeActive(index)">
        <div :class="{active_item:index===getIndex}" class="song_item" @click="
            toPlay(
              item.id,
              item.url,
              item.song_pic,
              index,
              item.song_name,
              item.lyric
            )
          ">
          <span class="item_index">{{ index + 1 }}</span>
          <!-- <i class="el-icon-video-play"
            style="font-size: 1.5em;height: 50px;width: 5%;color: #f36161;line-height: 50px;"></i> -->
          <!-- 歌曲名 -->
          <span class="item_title">{{
            replaceLatterName(item.song_name)
          }}</span>
          <!-- 歌手 -->
          <span class="item_name">{{ replaceFrontName(item.song_name) }}</span>
          <!-- 专辑 -->
          <span class="item_intro">{{ item.song_intro }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
  import {
    mixin
  } from "../mixins/index";
  export default {
    name: "myMusicContent",
    props: ["songList"],
    mixins: [mixin],
    data() {
      return {
        isActive: true,
        getIndex: "",
      };
    },

    methods: {
      changeActive: function (index) {
        this.getIndex = index;
        this.$store.commit("setListOfSongs", this.songList);
      }
    },

    components: {}
  };

</script>

<style scoped>
  @import "../assets/css/songcontent.css";

</style>
