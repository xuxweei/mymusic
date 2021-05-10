<template>
  <div class="song_list_album">
    <div class="album_title">
      <p>{{ tempList.title }}</p>
    </div>
    <div class="album_slide">
      <div class="album_img">
        <img :src="attachImageUrl(tempList.pic)" />
      </div>
      <div class="album_info">
        <div style="height:35px;line-height:35px;">
          <i class="el-icon-user"></i>
          <span>{{ tempList.author }}</span>
        </div>
        <div style="height:35px;line-height:35px;">
          标签：<span>{{tempList.style}}</span>
        </div>
        <h2>简介：</h2>
        <span :title="tempList.songlist_intro">{{
          tempList.songlist_intro
        }}</span>
      </div>
    </div>
    <div class="album_content">
      <div class="album_score">
        <div style="margin-left:50px;">
          <h3>歌单评分:</h3>
          <el-rate v-model="average" disabled :colors="colors"> </el-rate>
          <!-- {{num}} -->
        </div>
        <span>{{ average * 2 }}</span>
        <div>
          <h3>评价：</h3>
          <div @click="setRank">
            <el-rate v-model="rank" :colors="colors" text-color="#ff9900" allow-half show-text :texts="texts"></el-rate>
          </div>
        </div>
        <div style="padding:0 50px;">
          <el-button type="primary" @click="playAll" style="margin-left:50px;" class="btn">
            <svg t="1620446338546" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
              p-id="1628" width="200" height="200" style="    width: 1rem;height: 1rem;font-size: 1.5rem;">
              <path
                d="M803.84 492.032 213.056 135.04C195.392 124.288 174.208 124.288 156.352 134.976 138.624 145.728 128 164.928 128 186.368l0 714.176c0 21.44 10.624 40.704 28.352 51.392C165.248 957.312 174.976 960 184.704 960c9.728 0 19.52-2.688 28.352-8.064l590.72-356.992c17.728-10.752 28.288-30.016 28.288-51.456C832.128 521.984 821.568 502.72 803.84 492.032zM189.568 891.712 187.136 193.792l578.624 349.696L189.568 891.712z"
                p-id="1629"></path>
            </svg>
            <span>
              播放全部
            </span>
          </el-button>

          <el-button icon="el-icon-chat-dot-square" class="btn" @click="toPinglun">评论</el-button>
        </div>
      </div>

      <div class="songs_body">
        <song-list-album-content :songList="listOfSongs">
        </song-list-album-content>
        <div ref="pinglun">
          <comment :playId="songListId" :type="1"></comment>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import {
    mixin
  } from "../mixins";
  import {
    mapGetters
  } from "vuex";
  import {
    listSongOfSongId,
    songOfSongId,
    addRank,
    getRankOfSongListID,
    rankNum
  } from "../api/index";
  import SongListAlbumContent from "../components/SongListAlbumContent";
  import Comment from "../components/Comment";
  export default {
    name: "songListAlbum",
    mixins: [mixin],
    data() {
      return {
        // 当前页面需要展示的歌曲列表
        songLists: [],
        // 前面传入的歌单id
        songListId: "",
        // 平均分
        average: 0,
        // 评价
        rank: 0,
        colors: ["#99A9BF", "#F7BA2A", "#ff5500"],
        texts:["差劲","失望","一般","良好","优秀"],
        value:0,
        num:0
      };
    },
    computed: {
      ...mapGetters([
        // 当前播放列表
        "listOfSongs",
        // 当前歌单对象
        "tempList",
        // 是否登录
        "loginIn",
        // 当前登录用户
        "userId"
      ])
    },
    created() {
      // 将前面传入的id给其赋值
      this.songListId = this.$route.params.id;
      this.getSongID();
      this.getRank(this.songListId);
      console.log("111", this.tempList);
      this.value = Integer.parse(this.value * 2)
    },

    methods: {
      // 获取当前歌单的歌曲列表
      getSongID() {
        listSongOfSongId(this.songListId)
          .then(res => {
            for (let item of res) {
              console.log(res);
              this.getSongList(item.song_id);
            }
            this.$store.commit("setListOfSongs", this.songLists);
          })
          .catch(err => {
            console.log(err);
          });
      },
      // 根据歌曲id获取歌曲信息
      getSongList(id) {
        songOfSongId(id)
          .then(res => {
            this.songLists.push(res);
          })
          .catch(err => {
            console.log(err);
          });

      },
      // 获取歌单评分
      getRank(id) {
        getRankOfSongListID(id)
          .then(res => {
            this.average = res / 2;
          })
          .catch(err => {
            console.log(err);
          });
          rankNum(id).then(res=>{
            console.log("222",res);
            this.num = res
          })
      },
      // 提交评分
      setRank() {
        if (this.loginIn) {
          let params = new URLSearchParams();
          params.append("songlist_id", this.songListId);
          params.append("consumer_id", this.userId);
          params.append("score", this.rank * 2);
          
          addRank(params)
            .then(res => {
              if (res.code == 1) {
                this.notify("评分成功", "success");
                this.getRank(this.songListId);
              } else {
                this.notify("评分失败", "error");
              }
            })
            .catch(err => {
              this.notify("你已经评价过啦~", "error");
              console.log(err);
            });
        } else {
          this.rank = null;
          this.notify("请先登录哦~", "warning");
        }
      },
      playAll() {
        this.$store.commit("setListOfSongs", this.songLists);
        this.toPlay(this.songLists[0].id, this.songLists[0].url, this.songLists[0].song_pic, this.songLists[0], this
          .songLists[0].song_name, this.songLists[0].lyric)
      },
      toPinglun() {
        this.$refs["pinglun"].scrollIntoView(true);
      }
    },

    components: {
      SongListAlbumContent,
      Comment
    }
  };

</script>

<style scoped>
  @import "../assets/css/songlistalbum.css";

</style>
