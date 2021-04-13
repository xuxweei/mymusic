<template>
  <div class="song_audio">
    <audio :src="url" id="player" controls="controls" preload="true" @canplay="startPlay" @ended="ended"></audio>
  </div>
</template>

<script>
  import {
    mapGetters
  } from 'vuex';
  export default {
    name: 'SongPlay',
    data() {
      return {
        //   song:"http://localhost:8888/song/1608198567518TWICE%20(%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4)%20-%20What%20is%20Love_.mp3"
      }
    },
    computed: {
      ...mapGetters([
        'id', // 音乐id
        'url', // 音乐链接
        'isPlay' // 播放状态
      ])
    },
    watch: {
      //监听是播放还是暂停状态
      isPlay: function(){
        this.togglePlay();
      }
    },

    methods: {
      //获取链接后准备播放
      startPlay() {
        let player = document.querySelector('#player');
        //开始播放
        player.play();
      },
      //播放完后触发
      ended() {
        this.isPlay = false;
      },
      //开始、暂停
      togglePlay() {
        let player = document.querySelector('#player');
        if (this.isPlay) {
          player.play();
        } else {
          player.pause();
        }
      }
    },

    components: {},
  }

</script>

<style>
#player{
    display: none;
}
</style>
