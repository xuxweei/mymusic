<template>
  <div class="search_song_Lists">
    <song-list-content :songListContent="songListDatas"></song-list-content>
  </div>
</template>

<script>
  import SongListContent from '../SongListContent.vue'
  import {
    getSongListLikeTitle
  } from '../../api/index'
  import {
    mixin
  } from '../../mixins'
  export default {
    name: 'searchSongList',
    mixins:[mixin],
    data() {
      return {
        songListDatas: []
      }
    },
    mounted() {
      this.getLists()
    },

    methods: {
      getLists() {
        if (!this.$route.query.keywords) {
          this.notify('您输入的内容为空', 'warning')
        } else {
          getSongListLikeTitle(this.$route.query.keywords).then(res => {
            if (res) {
              this.songListDatas = res
            } else {
              this.notify('暂时没有该歌单信息哦~', 'warning')
            }
          })
        }
      }
    },

    components: {
      SongListContent
    },
  }

</script>

<style scoped>
  @import '../../assets/css/searchsonglist.css';
</style>
