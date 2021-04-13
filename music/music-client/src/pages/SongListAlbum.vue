<template>
  <div class="song_list_album">
    <div class="album_title">
      <p>{{tempList.title}}</p>
    </div>
    <div class="album_slide">
      <div class="album_img">
        <img :src="attachImageUrl(tempList.pic)">
      </div>
      <div class="album_info">
        <h2>简介：</h2>
        <span>{{tempList.songlist_intro}}</span>
      </div>
    </div>
    <div class="album_content">
      <div class="album_score">
        <div style="margin-left:50px;">
          <h3>歌单评分:</h3>
          <el-rate v-model="average" disabled :colors="colors">
          </el-rate>
        </div>
        <span>{{average*2}}</span>
        <div>
          <h3>评价：</h3>
          <div @click="setRank">
            <el-rate v-model="rank" :colors="colors" allow-half show-text></el-rate>
          </div>
        </div>
      </div>
      <div class="songs_body">
        <song-list-album-content :songList="listOfSongs">
        </song-list-album-content>
        <comment :playId="songListId" :type="1"></comment>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    mixin
  } from '../mixins'
  import {
    mapGetters
  } from 'vuex'
  import {
    listSongOfSongId,
    songOfSongId,
    addRank,
    getRankOfSongListID
  } from '../api/index'
  import SongListAlbumContent from '../components/SongListAlbumContent'
  import Comment from '../components/Comment'
  export default {
    name: 'songListAlbum',
    mixins: [mixin],
    data() {
      return {
        // 当前页面需要展示的歌曲列表
        songLists: [],
        // 前面传入的歌单id
        songListId: '',
        // 平均分
        average: 0,
        // 评价
        rank: 0,
        colors: ['#99A9BF', '#F7BA2A', '#ff5500']
      }
    },
    computed: {
      ...mapGetters([
        // 当前播放列表
        'listOfSongs',
        // 当前歌单对象
        'tempList',
        // 是否登录
        'loginIn',
        // 当前登录用户
        'userId'
      ])
    },
    created() {
      // 将前面传入的id给其赋值
      this.songListId = this.$route.params.id;
      this.getSongID();
      this.getRank(this.songListId);
    },

    methods: {
      // 获取当前歌单的歌曲列表
      getSongID() {
        listSongOfSongId(this.songListId).then(res => {
            for (let item of res) {
              console.log(res)
              this.getSongList(item.song_id)
            }
            this.$store.commit('setListOfSongs', this.songLists)
          })
          .catch(err => {
            console.log(err);
          })
      },
      // 根据歌曲id获取歌曲信息
      getSongList(id) {
        songOfSongId(id).then(res => {
            this.songLists.push(res)
          })
          .catch(err => {
            console.log(err);
          })
      },
      // 获取歌单评分
      getRank(id) {
        getRankOfSongListID(id).then(res => {
            this.average = res / 2;
          })
          .catch(err => {
            console.log(err);
          })
      },
      // 提交评分
      setRank() {
        if (this.loginIn) {
          let params = new URLSearchParams();
          params.append('songlist_id', this.songListId)
          params.append('consumer_id', this.userId)
          params.append('score', this.rank * 2)
          addRank(params).then(res => {
              if (res.code == 1) {
                this.notify('评分成功', 'success')
                this.getRank(this.songListId)
              } else {
                this.notify('评分失败', 'error')
              }
            })
            .catch(err => {
              this.notify('你已经评价过啦~', 'error')
              console.log(err)
            })
        } else {
          this.rank = null;
          this.notify('请先登录哦~', 'warning')
        }
      }
    },

    components: {
      SongListAlbumContent,
      Comment
    },
  }

</script>

<style scoped>
  @import '../assets/css/songlistalbum.css';

</style>
