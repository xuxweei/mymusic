<!--获取歌曲列表放入缓存，监控歌曲，放入song-content 显示-->
<template>
  <div class="search_songs">
    <song-content :songList="listOfSongs"></song-content>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';
  import { mixin } from '../../mixins/index';
  import SongContent from '../SongContent.vue';
  export default {
    name: 'searchSongs',
    mixins: [mixin],
    data() {
      return {}
    },
    // 监控listOfSongs的值
    computed: {
      ...mapGetters([
        'listOfSongs'
      ])
    },
    mounted(){
        this.getSong();
        // this.getAlbum();

    },

    methods: {},

    components: {
      SongContent
    },
  }

</script>

<style scoped>
  @import '../../assets/css/searchsongs.css';

</style>
