<!--
 * @Author: your name
 * @Date: 2021-04-20 09:33:13
 * @LastEditTime: 2021-04-29 17:59:51
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
      <li
        v-for="(item, index) in songList"
        :key="index"
        @click="changeActive(index)"
      >
        <div
          :class="{ active_item: index == isActive }"
          class="song_item"
          @click="
            toPlay(
              item.id,
              item.url,
              item.song_pic,
              index,
              item.song_name,
              item.lyric
            )
          "
        >
          <span class="item_index" v-if="index != isActive">{{
            index + 1
          }}</span>
          <i
            class="el-icon-video-play"
            v-else
            style="font-size: 1.5em;height: 50px;width: 5%;color: #f36161;line-height: 50px;"
          ></i>
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
import { mixin } from "../mixins/index";
export default {
  name: "myMusicContent",
  props: ["songList"],
  mixins: [mixin],
  data() {
    return {
      isActive: false
    };
  },

  methods: {
    changeActive(index) {
      this.isActive = index;
    }
    // clickHandle: function(e) {
    //   console.log(this);
    //   console.log("点击了...");
    //   console.log(e.currentTarget); //e.currentTarget  表示绑定事件的元素
    //   console.log(e.target); // e.target  表示当前点击的元素
    //   var currentTarget = e.currentTarget;
    //   console.log(currentTarget.getAttribute("flagid"));
    //   var flagId = currentTarget.getAttribute("flagid");
    //   console.log(flagId);

    //   if (flagId == 0) {
    //     //当前是未点击状态下的情况
    //     var liList = document.querySelectorAll("li"); //获取列表项
    //     console.log(liList.length);
    //     //循环遍历列表，使列表项的flagID都置为初始化值：0
    //     for (var i = 0; i < liList.length; i++) {
    //       console.log(liList[i]);
    //       liList[i].setAttribute("flagid", 0);
    //       liList[i].setAttribute("class", " ");
    //     }
    //     flagId = 1;

    //     currentTarget.setAttribute("flagid", flagId);

    //     currentTarget.setAttribute("class", "changeBg"); //添加点击效果，原生JS动态添加class属性
    //   }
    //   // else{
    //   //     //此时flagid = 1时，点击时对按钮的操作
    //   //     flagId = 0;

    //   //     currentTarget.setAttribute('flagid',flagId);  //原生JS动态修改flagID属性值

    //   //     liList[i].setAttribute('class',' ');//不添加点击效果，原生JS动态添加属性class
    //   // }
    // }
  },

  components: {}
};
</script>

<style scoped>
@import "../assets/css/songcontent.css";
</style>
