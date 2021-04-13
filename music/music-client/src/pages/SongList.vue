<template>
  <div class="song_list">
    <div class="song_list_header">
        <ul>
          <li :class="{active:item.name==activeName}" v-for="(item,index) in songStyle" :key="index" @click="handleChangeView(item.name)">
            {{item.name}}
          </li>
        </ul>
    </div>
    <div>
      <classify-song-list :classifySongList="datas"></classify-song-list>
      <div class="pagination">
        <el-pagination background layout="total,prev, pager, next" :total="songListDatas.length"
          @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    getAllSongList, getSongListLikeStyle
  } from '../api/index'
  import {
    mixin
  } from '../mixins'
  import ClassifySongList from '../components/ClassifySongList.vue'
  import {
    songStyle
  } from '../assets/data/songlist'
  export default {
    name: 'songList',
    mixins: [mixin],
    data() {
      return {
        // 歌单数据
        songListDatas: [],
        // 一页有15条数据
        pageSize: 15,
        // 当前页数
        currentPage: 1,
        // 歌单风格
        songStyle:[],
        //当前风格
        activeName:'全部歌单' 
      }
    },
    computed: {
      // 计算当前表格中的数据
      datas() {
        return this.songListDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      }
    },
    mounted() {
      this.songStyle = songStyle
      this.getLists()
    },

    methods: {
      getLists() {
        getAllSongList().then(res => {
          this.currentPage = 1
          this.songListDatas = res
        })
      },
      // 获取当前页
      handleCurrentChange(val) {
        this.currentPage = val
      },
      // 根据风格显示对应歌单
      handleChangeView(name){
        this.activeName = name
        this.songListDatas = []
        if (name == '全部歌单') {
          this.getLists()
        }else{
          this.getSongListOfStyle(name)
        }
      },
      getSongListOfStyle(style){
        getSongListLikeStyle(style).then(res => {
          this.currentPage = 1
          this.songListDatas = res
        })
      }
    },

    components: {
      ClassifySongList
    },
  }

</script>

<style scoped>
  @import '../assets/css/songlist.css';

</style>
