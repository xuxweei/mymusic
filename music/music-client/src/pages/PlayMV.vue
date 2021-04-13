<template>
  <div class="mv" style="margin-top:100px;">
    <div class="mv_title">
      <h2>
        <svg t="1615457339518" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="2167" width="200" height="200">
          <path
            d="M908.334954 269.29202c40.090849 0 72.706997 32.616148 72.706997 72.706997v340.999992c0 40.090849-32.616148 72.706997-72.706997 72.706997H116.783953c-40.090849 0-72.706997-32.616148-72.706997-72.706997V341.999017c0-40.090849 32.616148-72.706997 72.706997-72.706997h791.551001m0-40.96H116.783953c-62.776695 0-113.666997 50.890302-113.666997 113.666997v340.999992c0 62.776695 50.890302 113.666997 113.666997 113.666997h791.552c62.776695 0 113.666997-50.890302 113.666997-113.666997V341.999017c-0.000999-62.775696-50.891301-113.666997-113.667996-113.666997z"
            fill="#515151" p-id="2168" data-spm-anchor-id="a313x.7781069.0.i0" class=""></path>
          <path
            d="M469.485518 690.207969V454.79886c0-19.42503 1.165861-42.750252 3.44064-69.987652h-1.137889c-3.968125 17.136265-7.492683 29.068613-10.545701 35.798041l-117.245503 269.59872h-25.475122L201.497225 422.677229c-2.747317-6.119024-6.271875-18.745694-10.545701-37.866021h-1.387645c1.526509 16.525861 2.30375 40.543407 2.30375 72.054634v233.341128h-43.818209V334.791056h62.161296l104.869588 243.679032c7.93625 18.662775 13.237073 33.189588 15.817554 43.596425h1.609428c7.964222-21.881631 13.542775-36.714146 16.761631-44.512531l106.701799-242.762926h59.192195v355.416913h-45.677393zM874.951555 334.791056L744.384062 690.207969h-50.950243L565.419832 334.791056h50.922271l95.684558 277.867644c3.218857 9.172043 5.577553 19.813651 7.104063 31.885862H720.296585c1.220808-10.545701 3.885206-21.34016 8.020168-32.343415l97.51577-277.410091h49.119032z"
            fill="#515151" p-id="2169" data-spm-anchor-id="a313x.7781069.0.i1" class=""></path>
        </svg>
        <span>{{MVDetailData.name}}</span>
      </h2>
      <span class="name">{{MVDetailData.artistName}}</span>
    </div>
    <div class="mv_content">
      <div class="mv_box">
        <div class="mv_video">
          <div class="mv_player">
            <video-player class="videoplayer vjs-custom-skin" ref="mvplay" :options="videoPlayerOptions"
              :playsinline="true" style="object-fit:fill;" @fullscreenchange="onFullScreen"></video-player>
          </div>
        </div>

      </div>
      <div class="mv_info">
        <h3>
          <span>MV简介</span>
        </h3>
        <div class="info">
          <p>发布时间：{{MVDetailData.publishTime}}</p>
          <p>播放次数：{{this.playCount}}</p>
          <div class="tally"  v-if="videoGroup.length">
            <span>标签 : </span>
            <el-breadcrumb separator-class="el-icon-arrow-right" v-for="value in videoGroup" :key="value.id">
              <el-breadcrumb-item style="margin: 3px 0 0 5px;"> {{value.name}}</el-breadcrumb-item>
            </el-breadcrumb>
          </div>

        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import {
    mixin
  } from '../mixins'
  import {
    mapGetters,
  } from 'vuex'
  import {
    videoPlayer
  } from 'vue-video-player'

  export default {
    // name: 'playmv',
    mixins: [mixin],
    data() {
      return {
        MVId: '',
        MVDetailURL: 'http://localhost:3000/mv/detail?mvid=',
        MVUrl: 'http://localhost:3000/mv/url?id=',
        MVDetailData: [],
        mvData: [],
        playCount: 0,
        videoGroup: []
      }
    },
    computed: {
      ...mapGetters([
        'mvid', //当前mvId
        'mvList',
      ]),
      videoPlayerOptions() {
        const videoPlayerOptions = {
          autoplay: false, // 是否自动播放。
          muted: false, // 是否静音播放，默认情况下将会消除任何音频。
          loop: false, // 是否循环播放。
          preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
          language: 'zh-CN',
          sources: [{
            type: 'video/mp4',
            src: this.mvData.url // 视频url地址
          }],
          poster: this.MVDetailData.cover,
          width: '640px',
          height: '360px',
          notSupportedMessage: '此视频暂时无法播放...', //允许覆盖Video.js无法播放媒体源时显示的默认信息。
          controlBar: {
            timeDivider: true,
            durationDisplay: true,
            remainingTimeDisplay: false,
            fullscreenToggle: true,
          }
        }
        return videoPlayerOptions
      }
    },
    watch: {},

    created() {
      this.MVId = this.$route.query.mvid;
      this.mv = this.mvList
      this.getMVDetail()
      this.getMVUrl()

    },

    methods: {
      getMVDetail() {
        this.axios({
          methods: 'get',
          url: this.MVDetailURL + this.MVId
        }).then(res => {
          console.log('mv', res.data.data)
          this.MVDetailData = res.data.data
          this.progress = res.data.data.duration
          console.log('mvTime', this.progress)
          this.playCount = this.formatCount(res.data.data.playCount)
          this.videoGroup = res.data.data.videoGroup
        })
      },
      getMVUrl() {
        return this.axios.get(this.MVUrl + this.MVId).then(res => {
          this.mvData = res.data.data
        });
      },
      //全屏事件
      onFullScreen() {
        const mvplay = this.$refs.mvplay.player;
        mvplay.requestFullscreen()
        mvplay.isFullscreen(true)
        mvplay.play()
      },
      formatCount(val) {
        let count = parseInt(val)
        let wan = parseInt(count / 10000)
        let result = ''
        if (wan > 0) {
          result = wan + "万次"
        } else {
          result = count + "次"
        }
        return result
      }
    },

    components: {
      videoPlayer
    },
  }

</script>

<style scoped>
  @import "../assets/css/mv.css";

</style>
