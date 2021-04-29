<!--
 * @Author: your name
 * @Date: 2021-04-20 09:33:13
 * @LastEditTime: 2021-04-29 17:38:02
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \Vue\mymusic\music\music-client\src\pages\Lyric.vue
-->
<template>
  <div class="song_lyric" :style="bgimg">
    <div class="bg">
      <h1 class="lyric_title">歌词</h1>
      <!-- 有歌词 -->
      <ul class="has_lyric" v-if="lyrics.length" key="index" ref="lyric">
        <li v-for="(item, index) in lyrics" v-bind:key="index">
          {{ item[1] }}
        </li>
      </ul>
      <!-- 没有歌词 -->
      <div v-else class="no_lyric" :key="no_lyric">
        <span>暂无歌词</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mixin } from "../mixins";
import { mapGetters } from "vuex";
export default {
  name: "lyric",
  mixins: [mixin],
  data() {
    return {
      //当前歌曲的歌词
      lyrics: [],
      pic: "",
      bgimg: {}
    };
  },
  computed: {
    ...mapGetters([
      "currentTime", //当前歌曲播放的位置
      "id", //当前播放的歌曲id
      "lyric", //歌词
      "listIndex", //当前歌曲在播放列表中的位置
      "listOfSongs" //当前播放列表
    ])
  },
  created() {
    //获取歌词文件 此时的lyric为转换后的 在缓存中
    this.lyrics = this.lyric;
    this.pic = window.sessionStorage.getItem("picUrl");
    console.log(this.pic);
    this.bgimg = {
      backgroundImage: "url(" + this.pic + ")",
      backgroundRepeat: "no-repeat",
      backgroundSize: "70% 120%",
      backgroundPosition: "center"
    };
  },
  watch: {
    id: function() {
      //当id改变让歌词变成新的id歌词文件
      this.lyrics = this.parseLyric(this.listOfSongs[this.listIndex].lyric);
    },
    currentTime: function() {
      //当有歌词时
      if (this.lyrics.length > 0) {
        //遍历歌词数组
        for (let i = 0; i < this.lyrics.length; i++) {
          //当时间等于当前歌词时间时变色
          if (this.currentTime >= this.lyrics[i][0]) {
            // 让歌词恢复原样
            for (let j = 0; j < this.lyrics.length; j++) {
              document.querySelectorAll(".has_lyric li")[j].style.color =
                "#b7b7b7";
              document.querySelectorAll(".has_lyric li")[j].style.fontSize =
                "14px";
            }
            if (i >= 0) {
              document.querySelectorAll(".has_lyric li")[i].style.color =
                "#fff";
              document.querySelectorAll(".has_lyric li")[i].style.fontSize =
                "20px";
              document.querySelectorAll(".has_lyric li")[i].style.fontWeight =
                "bold";
            }
            if (i > 6) {
              this.$refs.lyric.scrollTop = 40 * (i - 6);
            }
          }
        }
      }
    }
  },

  methods: {},

  components: {}
};
</script>

<style scoped>
@import "../assets/css/lyric.css";
</style>
