<template>
    <div class="search_songs">
        <album-content :songList="listOfSongs"></album-content>
    </div>
</template>

<script>
  import { mapGetters } from 'vuex';
  import { mixin } from '../../mixins/index';
import AlbumContent from '../AlbumContent.vue'
export default {
    name:'searchAlbum',
    mixins: [mixin],
    data () {
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

    components: {AlbumContent},
}
</script>

<style scoped>
  @import '../../assets/css/searchsongs.css';
</style>