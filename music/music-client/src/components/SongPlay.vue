<template>
  <div class="song_audio">
    <!--（1）属性：controls，preload（2）事件：canplay，timeupdate，ended（3）方法：play()，pause() -->
    <!--controls：向用户显示音频控件（播放/暂停/进度条/音量）-->
    <!--preload：属性规定是否在页面加载后载入音频-->
    <!--canplay：当音频/视频处于加载过程中时，会发生的事件-->
    <!--timeupdate：当目前的播放位置已更改时-->
    <!--ended：当目前的播放列表已结束时-->
    <audio :src="url" ref="player" controls="controls" preload="true" @canplay="startPlay" @ended="ended"
      @timeupdate="timeupdate"></audio>
  </div>
</template>

<script>
  import {
    mapGetters
  } from 'vuex';
  export default {
    name: 'SongPlay',
    computed: {
      ...mapGetters([
        'id', // 音乐id
        'url', // 音乐链接
        'isPlay', // 播放状态
        'listOfSongs',
        'currentTime', //当前音乐播放位置
        'changeTime', //指定播放时间
        'autoNext', //是否自动播放下一首
        'volume' //音量
      ])
    },
    watch: {
      //监听是播放还是暂停状态
      isPlay: function () {
        this.togglePlay();
      },
      //跳转到指定时间播放
      changeTime() {
        let player = this.$refs.player;
        player.currentTime = this.changeTime;
      },
      //音量控制
      volume(val) {
        this.$refs.player.volume = val;
      }
    },

    methods: {
      //获取链接后准备播放
      startPlay() {
        let player = this.$refs.player;
        //记录音乐时长
        this.$store.commit('setDuration', player.duration);
        //开始播放
        player.play();
        this.$store.commit('setIsPlay', true);
      },
      //播放完后触发
      ended() {
        this.$store.commit('setIsPlay', false);
        this.$store.commit('setCurrentTime', 0);
        this.$store.commit('setAutoNext', !this.autoNext);
      },
      //开始、暂停
      togglePlay() {
        let player = this.$refs.player;
        if (this.isPlay) {
          player.play();
        } else {
          player.pause();
        }
      },
      //音乐播放更新时间
      timeupdate() {
        this.$store.commit('setCurrentTime', this.$refs.player.currentTime);
      }
    },

    components: {},
  }

</script>

<style scoped>
  .song_audio {
    display: none;
  }

</style>
