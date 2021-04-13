<template>
  <div class="singer_album">
    <div class="album_slide">
      <div class="singer_img">
        <img :src="attachImageUrl(singer.pic)" alt="">
      </div>
      <ul class="info">
        <li v-if="singer.sex==0||singer.sex==1||singer.sex==2">{{getSingerSex(singer.sex)}}</li>
        <li v-if="singer.sex==0||singer.sex==1">生日:{{attachBirth(singer.singer_birth)}}</li>
        <li v-else-if="singer.sex==2">出道日：{{attachBirth(singer.singer_birth)}}</li>
        <li>地区：{{singer.singer_location}}</li>
      </ul>
    </div>
    <div class="album_content">
      <div class="intro">
        <h2>{{singer.name}}</h2>
        <span>{{singer.singer_intro}}</span>
      </div>
      <div class="content">
        <song-content :songList="listOfSongs" style="padding: 0px 20px 20px 0px;"></song-content>
        <!-- <comment :playId="singerId" :type="0"></comment> -->
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
    songOfSingerId
  } from '../api/index'
  import SongContent from '../components/SongContent'
  export default {
    name: 'singerAlbum',
    mixins: [mixin],
    data() {
      return {
        singerId: '', //当前歌手id
        singer: {}, //当前歌手对象
      }
    },
    computed: {
      ...mapGetters([
        'listOfSongs', //当前列表
        'tempList', //当前歌手对象
        'loginIn', //是否已登录
        'userId' //当前登录用户id
      ])
    },
    created() {
      this.singerId = this.$route.params.id;
      this.singer = this.tempList;
      this.getSongOfSingerId()
    },

    methods: {
      // 根据歌手id查询歌曲
      getSongOfSingerId() {
        songOfSingerId(this.singerId).then(res => {
          this.$store.commit('setListOfSongs', res)
        }).catch(err => {
          console.log(err);
        })
      },
      // 获取性别
      getSingerSex(value) {
        if (value == 0) {
          return '女'
        } else if (value == 1) {
          return '男'
        }else if (value == 2) {
          return '组合'
        }
        return ''
      }
    },

    components: {SongContent},
  }

</script>

<style scoped>
  @import '../assets/css/singeralbum.css';

</style>
