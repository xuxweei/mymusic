<template>
  <div class="content">
    <h4 class="title">
      <slot name="title"></slot>
    </h4>
    <ul>
      <li class="list_title">
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
      <li v-for="(item,index) in songList" :key="index" @click="changeActive(index)">
        <div class="song_item" :class="{active_item:index===getIndex}"
          @click="toPlay(item.id,item.url,item.song_pic,index,item.song_name,item.lyric)">
          <i v-if="index===getIndex" class="el-icon-video-play"
            style="font-size: 1.5em;height: 50px;width: 5%;color: #f36161;line-height: 50px;"></i>
          <!-- 歌曲名 -->
          <span class="item_title">{{replaceLatterName(item.song_name)}}</span>
          <!-- 歌手 -->
          <span class="item_name">{{replaceFrontName(item.song_name)}}</span>
          <!-- 专辑 -->
          <span class="item_intro">{{item.song_intro}}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
  import {
    mixin
  } from '../mixins/index';
  export default {
    name: 'songContent',
    props: [
      'songList'
    ],
    mixins: [mixin],
    data() {
      return {
        getIndex: "",
      }
    },

    methods: {
      changeActive: function (index) {
        this.getIndex = index;
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import '../assets/css/songcontent.css';

</style>
