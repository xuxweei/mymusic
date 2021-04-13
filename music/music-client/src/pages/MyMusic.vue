<template>
  <div class="my_music">
    <div class="album_slide">
      <div class="album_img">
        <img :src="attachImageUrl(userpic)">
      </div>
      <ul class="album_info">
        <li>昵称：{{username}}</li>
        <li>性别：{{userSex}}</li>
        <li>生日：{{birth}}</li>
        <li>地区：{{location}}</li>
      </ul>
    </div>
    <div class="album_content">
      <div class="album_title">
        个人介绍：{{introduction}}
      </div>
      <div class="songs_body">
        <my-music-content :songList="collectList" style="padding: 0px 20px 20px 0px;">
            <template slot="title">我的收藏</template>
        </my-music-content>
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
    getConsumerById,
    getCollectOfUserId,
    songOfSongId
  } from '../api/index'
  import MyMusicContent from '../components/MyMusicContent'
  export default {
    name: 'myMusic',
    mixins: [mixin],
    data() {
      return {
        // 用户头像
        userpic: '',
        // 用户名 
        username: '',
        // 用户性别
        userSex: '',
        // 用户生日
        birth: '',
        // 地区
        location: '',
        // 个人介绍
        introduction: '',
        // 收藏列表
        collection: [],
        // 收藏列表(带歌曲详情)
        collectList: []
      }
    },
    computed: {
      ...mapGetters([
        'listOfSongs', //当前列表
        'userId' //当前登录用户id
      ])
    },
    mounted() {
      this.getUserMsg(this.userId)
      this.getCollection(this.userId)
    },
    methods: {
      getUserMsg(userId) {
        getConsumerById(userId)
          .then(res => {
            console.log(res);
            this.userpic = res.userpic
            this.username = res.username
            if (res.sex == 0) {
              this.userSex = '女'
            } else if (res.sex == 1) {
              this.userSex = '男'
            }
            this.birth = this.attachBirth(res.birth)
            this.location = res.location
            this.introduction = res.introduction
          })
          .catch(err => {
            console.log(err);
          })
      },
      // 获取我的收藏列表
      getCollection(userId) {
        getCollectOfUserId(userId)
          .then(res => {
            this.collection = res
            // 通过歌曲id获取歌曲信息
            for (let item of this.collection) {
              this.getSongsOfId(item.song_id)
            }
          })
          .catch(err => {
            console.log(err);
          })
      },
      // 通过歌曲id获取歌曲信息
      getSongsOfId(id) {
        songOfSongId(id)
          .then(res => {
            this.collectList.push(res)
          })
          .catch(err => {
            console.log(err);
          })
      }
    },

    components: {
      MyMusicContent
    },
  }

</script>

<style scoped>
  @import '../assets/css/mymusic.css';

</style>
