<template>
  <div class="singer_album">
    <div class="album_slide">
      <div class="singer_img">
        <img :src="attachImageUrl(singer.pic)" alt="">
      </div>
     <div style="padding:0 50px;position: absolute;top: 350px;left: 30px;">
          <el-button type="primary" @click="playAll" style="margin-left:50px;" class="btn">
            <svg t="1620446338546" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
              p-id="1628" width="200" height="200" style="    width: 1rem;height: 1rem;font-size: 1.5rem;">
              <path
                d="M803.84 492.032 213.056 135.04C195.392 124.288 174.208 124.288 156.352 134.976 138.624 145.728 128 164.928 128 186.368l0 714.176c0 21.44 10.624 40.704 28.352 51.392C165.248 957.312 174.976 960 184.704 960c9.728 0 19.52-2.688 28.352-8.064l590.72-356.992c17.728-10.752 28.288-30.016 28.288-51.456C832.128 521.984 821.568 502.72 803.84 492.032zM189.568 891.712 187.136 193.792l578.624 349.696L189.568 891.712z"
                p-id="1629"></path>
            </svg>
            <span>
              播放歌手歌曲
            </span>
          </el-button>
        </div>
    </div>  
    <div class="album_content">
      <div class="intro">
        <h2>{{singer.name}}</h2>
        <ul class="info">
        <li v-if="singer.sex==0||singer.sex==1||singer.sex==2" style="width:20%;">{{getSingerSex(singer.sex)}}</li>
        <li v-if="singer.sex==0||singer.sex==1" style="width:70%;">生日:{{attachBirth(singer.singer_birth)}}</li>
        <li v-else-if="singer.sex==2" style="width:70%;">出道日：{{attachBirth(singer.singer_birth)}}</li>
        <li>地区：{{singer.singer_location}}</li>
      </ul>
        <p>{{singer.singer_intro}}</p>
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
        singer: {}, //当前歌手对象,
        songLists:[]
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
          this.songLists.push(res)
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
      },
       playAll() {
        // this.$store.commit("setListOfSongs", this.songLists);
        console.log(this.songLists);
        this.toPlay(this.songLists[0][0].id, this.songLists[0][0].url, this.songLists[0][0].song_pic, this.songLists[0][0], this
          .songLists[0][0].song_name, this.songLists[0][0].lyric)
      },
    },

    components: {SongContent},
  }

</script>

<style scoped>
  @import '../assets/css/singeralbum.css';

</style>
