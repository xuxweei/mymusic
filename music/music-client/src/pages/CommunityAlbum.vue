<template>
  <div class="content">
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
        <community-content :communityList="communityList" style="padding: 0px 20px 20px 0px;">
          <template slot="title">我的动态</template>
        </community-content>
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
    getCommunityOfUsername
  } from '../api/index'
  import CommunityContent from '../components/CommunityContent'
  export default {
    name: 'CommunityAlbum',
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
        // 动态列表
        communityList: [],
      }
    },
    computed: {
      ...mapGetters([
        'listOfSongs', //当前列表
        'userId' //当前登录用户id
      ])
    },
    created() {
      this.username = this.$route.query.usname

    },
    mounted() {
      this.getUserMsg(this.userId)
    //   this.getCommunity(this.username)
    },

    methods: {
      getUserMsg(userId) {
        getConsumerById(userId)
          .then(res => {
            console.log(res);
            this.userpic = res.userpic
            // this.username = this.$route.query.usname
            this.getCommunity(this.username)
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
      getCommunity(name) {
        getCommunityOfUsername(name)
          .then(res => {
            console.log(res);
            this.communityList = res
            
          })
          .catch(err => {
            console.log(err);
          })

      },
      // 通过歌曲id获取歌曲信息
      //   getSongsOfId(id) {
      //     songOfSongId(id)
      //       .then(res => {
      //         this.collectList.push(res)
      //       })
      //       .catch(err => {
      //         console.log(err);
      //       })
      //   }
    },

    components: {
      CommunityContent
    },
  }

</script>

<style scoped>
  @import '../assets/css/communityalbum.css';

</style>
