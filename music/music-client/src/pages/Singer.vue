<template>
     <div class="singer">
    <div class="singer_header">
        <ul>
          <li :class="{active:item.name==activeName}" v-for="(item,index) in singerStyle" :key="index" @click="handleChangeView(item)">
            {{item.name}}
          </li>
        </ul>
    </div>
    <div>
      <classify-song-list :classifySongList="datas"></classify-song-list>
      <div class="pagination">
        <el-pagination background layout="total,prev, pager, next" :total="singerDatas.length"
          @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    getAllSinger,getSingerOfSex
  } from '../api/index'
  import {
    mixin
  } from '../mixins'
  import ClassifySongList from '../components/ClassifySongList.vue'
  import {
    singerStyle
  } from '../assets/data/singer'
  export default {
    name: 'singer',
    mixins: [mixin],
    data() {
      return {
        // 歌手数据
        singerDatas: [],
        // 一页有15条数据
        pageSize: 15,
        // 当前页数
        currentPage: 1,
        // 歌手性别
        singerStyle:[],
        //当前风格
        activeName:'全部歌手' 
      }
    },
    computed: {
      // 计算当前表格中的数据
      datas() {
        return this.singerDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      }
    },
    mounted() {
      this.singerStyle = singerStyle
      this.getLists()
    },

    methods: {
      getLists() {
        getAllSinger().then(res => {
          this.currentPage = 1
          this.singerDatas = res
        })
      },
      // 获取当前页
      handleCurrentChange(val) {
        this.currentPage = val
      },
      // 根据性别显示对应歌手
      handleChangeView(item){
        this.activeName = item.name
        this.singerDatas = []
        if (item.name == '全部歌手') {
          this.getLists()
        }else{
          this.getSingerSex(item.type)
        }
      },
      // 根据性别查询
      getSingerSex(sex){
        getSingerOfSex(sex).then(res => {
          this.currentPage = 1
          this.singerDatas = res
        })
      }
    },

    components: {
      ClassifySongList
    },
  }

</script>

<style scoped>
  @import '../assets/css/singer.css';

</style>
