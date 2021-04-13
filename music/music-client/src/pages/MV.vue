<template>
  <div class="song_list">
    <div class="song_list_header">
      <ul>
        <li :class="{active:item.name==activeName}" v-for="(item,index) in mvStyle" :key="index"
          @click="handleChangeView(item.name)">
          {{item.name}}
        </li>
      </ul>
    </div>
    <div>
      <!-- :classifyMv="datas" -->
      <classify-mv>
        <li class="content_item" v-for="(value,index) in datas" :key="index" :title="value.name+'-'+value.artistName">
          <div class="kuo" style="padding-bottom: 56%;" @click="skipMV(value)">
            <img v-lazy="value.cover" class="item_img">
            <div class="mask" style="padding-bottom: 56%;">
              <svg t="1608619694217" class="icon" viewBox="0 0 1024 1024" version="1.1"
                xmlns="http://www.w3.org/2000/svg" p-id="3410" width="200" height="200" style="top: 20%;">
                <path d="M512 512m-512 0a512 512 0 1 0 1024 0 512 512 0 1 0-1024 0Z" fill="#555555" opacity=".3"
                  p-id="3411"></path>
                <path
                  d="M677.1712 555.8272L435.456 681.984a51.2 51.2 0 0 1-74.9056-44.4928L356.352 393.6256a51.2 51.2 0 0 1 73.2672-47.104l246.016 117.76a51.2 51.2 0 0 1 1.5872 91.5456z"
                  fill="#FFFFFF" p-id="3412"></path>
              </svg>
            </div>
          </div>
          <!-- <el-tooltip :content="{{value.name}}" placement="bottom" effect="light"> -->
            <p class="item_name" >{{value.name}}-{{value.artistName}}</p>
          <!-- </el-tooltip> -->
        </li>
      </classify-mv>
      <div class="pagination">
        <el-pagination background layout="total,prev, pager, next" :total="mvListDatas.length"
          @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    mixin
  } from '../mixins'
  import ClassifyMv from '../components/ClassifyMv.vue'
  import {
    mvStyle
  } from '../assets/data/mv'
  export default {
    // name: 'mv',
    mixins: [mixin],
    data() {
      return {
        // 歌单数据
        mvListDatas: [],
        // 一页有15条数据
        pageSize: 15,
        // 当前页数
        currentPage: 1,
        // 歌单风格
        mvStyle: [],
        //当前风格
        activeName: '全部',
        mvURL: "http://localhost:3000/mv/all?area=",
        offsetURL: '&offset=',
        mvid: '',
      }
    },
    created() {
      this.getLists()
    },
    mounted() {
      this.mvStyle = mvStyle
    },
    computed: {
      // 计算当前表格中的数据
      datas() {
        return this.mvListDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      },
    },

    methods: {
      getLists() {
        this.axios({
          methods: "get",
          url: this.mvURL + this.activeName
        }).then(res => {
          console.log(res.data.data);
          this.mvListDatas = res.data.data
          this.currentPage = 1
        })
      },
      // 获取当前页
      handleCurrentChange(val) {
        this.currentPage = val
      },
      // 根据风格显示对应歌单
      handleChangeView(name) {
        this.activeName = name
        this.mvListDatas = []
        if (name == '全部') {
          this.getLists()
        } else {
          this.getSongListOfStyle(name)
        }
      },
      getSongListOfStyle(style) {
        console.log(this.mvURL + style);
        return this.axios.get(this.mvURL + style).then(res => {
          this.mvListDatas = res.data.data
        })
      },
      skipMV(value) {
        console.log(value.id)
        this.$router.push({
          path: `/playmv?mvid=${value.id}`,
          query: {
            mvid: value.id
          }
        })
        // this.setMVId(`${mvid}`)
        this.$store.commit('setMVList', value)
        console.log(value);
      },
    },

    components: {
      ClassifyMv
    },
  }

</script>

<style scoped>
  @import "../assets/css/songlist.css";

</style>
