<template>
  <div>
    <div class="mod_search">
      <div class="mod_search_input">
        <input type="text" class="search_input__input" placeholder="请输入搜索内容" @keyup.enter="goSearch()"
          v-model="keywords">
        <button class="search_input__btn" @click="goSearch()">
          <svg t="1608627675622" class="icon icon_search" viewBox="0 0 1024 1024" version="1.1"
            xmlns="http://www.w3.org/2000/svg" p-id="7958" width="200" height="200">
            <path
              d="M437.554329 0.016686a432.32097 432.32097 0 1 0 0 864.64194 436.568334 436.568334 0 0 0 437.592252-432.32097A433.799963 433.799963 0 0 0 437.554329 0.016686z m0 89.915177a345.591316 345.591316 0 0 1 346.615234 342.405793 345.591316 345.591316 0 0 1-346.577311 342.481638 360.798395 360.798395 0 0 1-246.953873-98.599519A341.306029 341.306029 0 0 1 86.653808 432.337656a352.227822 352.227822 0 0 1 350.900521-342.405793z"
              fill="#585858" p-id="7959"></path>
            <path
              d="M1007.876703 925.449022L849.472783 767.045102a54.267659 54.267659 0 0 0-77.059317 0 63.748382 63.748382 0 0 0-17.141147 38.529658 46.493466 46.493466 0 0 0 17.141147 38.529658l158.40392 162.689207a63.748382 63.748382 0 0 0 38.529659 17.141148 46.493466 46.493466 0 0 0 38.529658-17.141148 59.501018 59.501018 0 0 0 0-81.344603z"
              fill="#00a155" p-id="7960" data-spm-anchor-id="a313x.7781069.0.i19" class="selected"></path>
          </svg>
        </button>
      </div>
    </div>
    <div class="search">
      <nav class="searchList_nav" ref="change">
        <span @click="handleChangeView('Songs')" :class="{isActive:toggle=='Songs'}">歌曲</span>
        <span @click="handleChangeView('Album')" :class="{isActive:toggle=='Album'}">专辑</span>
        <span @click="handleChangeView('SongList')" :class="{isActive:toggle=='SongList'}">歌单</span>
        <span @click="handleChangeView('Lyric')" :class="{isActive:toggle=='Lyric'}">歌词</span>
      </nav>
      <component :is="currentView"></component>
    </div>
  </div>
</template>

<script>
  import searchSongs from '../components/search/SearchSongs';
  import searchSongList from '../components/search/SearchSongList';
  import searchAlbum from '../components/search/SearchAlbum';
  import searchLyric from '../components/search/SearchLyric';
  import searchNone from '../components/search/SearchNone';
  export default {
    inject: ['reload'],
    name: 'search',
    data() {
      return {
        toggle: 'Songs',
        currentView: 'searchSongs',
        //搜索关键字
        keywords: ''
      }
    },

    methods: {
      //切换组件
      handleChangeView(component) {
        this.currentView = 'search' + component;
        this.toggle = component;
      },
      goSearch() {
        console.log('点击了搜索');
        this.$router.push({
          path: '/search',
          query: {
            keywords: this.keywords
          }
        })
        // this.$router.go(0)
        this.reload();
      }
    },

    components: {
      searchSongs,
      searchSongList,
      searchAlbum,
      searchLyric
    },
  }

</script>

<style scoped>
  @import '../assets/css/search.css';

</style>
