<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-present"></i>用户收藏列表
    </div>
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="goBack">返回用户</el-button>
        <el-button icon="el-icon-delete" type="primary" size="mini" @click="delSelected">批量删除</el-button>
        <el-input placeholder="请输入歌曲名" v-model="select_word" size="mini" class="handle_input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
      </div>
    </div>
    <!-- 歌曲列表 -->
    <el-table :data="data" style="width: 100%" size="mini" height="400px" border
      @selection-change="handleSelectionChange" ref="multipleTable" :cell-style="rowStyle"
      :cell-class-name="tableRowClassName">
      <el-table-column type="selection" width="50" align="center">
      </el-table-column>
      <!-- 图片 -->
      <el-table-column label="歌曲图片" width="110" align="center">
        <template slot-scope="scope">
          <div class="song_img">
            <img :src="getUrl(scope.row.song_pic)" style="width:100%" />
          </div>
          <div class="play" @click="setSongUrl(scope.row.url, scope.row.song_name)">
            <div v-if="toggle !== scope.row.song_name">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-bofanganniu"></use>
              </svg>
            </div>
            <div v-if="toggle === scope.row.song_name">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-zanting"></use>
              </svg>
            </div>
          </div>
        </template>
      </el-table-column>
      <!-- 名字 -->
      <el-table-column prop="song_name" label="歌曲名" align="center">
      </el-table-column>
      <!-- 专辑 -->
      <el-table-column prop="song_intro" label="专辑" align="center">
      </el-table-column>
      <!-- 操作 -->
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" :total="songData.length" :current-page="currentPage"
        :page-size="pageSize" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <!-- 删除对话框 -->
    <el-dialog title="取消收藏" :visible.sync="delVisible" width="30%" center>
      <div align="center">删除内容不可恢复,确认删除?</div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delVisible = false">取 消</el-button>
        <el-button type="primary" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {
    mixin
  } from "../mixins/index";
  import {
    mapGetters
  } from 'vuex';
  import '@/assets/js/iconfont.js';
  import {
    songOfSongId,
    getCollectOfUserId,
    deleteCollect
  } from "../api/index";
  export default {
    name: 'collect',
    inject: ['reload'],
    mixins: [mixin],
    props: [
      'id'
    ],
    data() {
      return {
        //删除弹窗
        delVisible: false,
        songData: [],
        tempData: [],
        multipleSelection: [], //哪些项已被选中
        selectedNum: [],
        select_word: '',
        pageSize: 5, //每页显示条数
        currentPage: 1, //当前页数
        currentIndex: -1, //当前选择项
        toggle: false, //播放器的图标状态
      }
    },
    computed: {
      //计算当前搜索结果表里的数据
      data() {
        return this.songData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
      },
      ...mapGetters([
        'isPlay' //播放状态
      ])
    },

    created() {
      this.user_id = this.$route.query.id;
      this.getData();
    },
    destroyed() {
      this.$store.commit('setIsPlay', false)
    },

    watch: {
      //搜素框里面的内容发送变化时，搜索结果table列表的内容跟着变化
      select_word: function () {
        if (this.select_word == '') {
          this.songData = this.tempData;
          this.getData()
        } else {
          this.songData = [];
          for (let i of this.tempData) {
            if (i.song_name.includes(this.select_word)) {
              this.songData.push(i);
            } 
          }
          this.currentPage = 1;
        }
      }
    },

    methods: {
      //关闭对话框
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      //返回上一层
      goBack() {
        window.history.go(-1)
      },

      //查询该用户所有收藏歌曲
      getData() {
        this.tempData = [];
        this.songData = [];
        getCollectOfUserId(this.user_id).then(res => {
          for (let item of res) {
            this.getSong(item.song_id);
          }
            this.currentPage = 1;
        })
      },

      //获取当前页
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
      },
      //根据歌曲id查询歌曲列表,返回数据放到songData、tempData中
      getSong(id) {
        songOfSongId(id)
          .then(res => {
            this.tempData.push(res);
            this.songData.push(res);
          })
          .catch(err => {
            console.log(err);
          })
      },
      // 删除歌曲信息
      deleteRow() {
        deleteCollect(this.user_id, this.currentIndex.id)
          .then((res) => {
            console.log(res)
            if (res) {
              this.getData();
              this.notify("删除成功", "success");
            } else {
              this.notify("删除失败", "error");
            }
          }).catch(err => {
            console.log(err);
          })
        this.delVisible = false;
      },
      //批量删除已选择的项
      delSelected() {
        for (let item of this.multipleSelection) {
          this.handleDelete(item);
          this.deleteRow();
        }
        this.multipleSelection = [];
        this.reload()
      },
      //切换播放音乐
      setSongUrl(url, song_name) {
        this.$store.commit('setUrl', this.$store.state.HOST + url);
        this.toggle = song_name;
        console.log(this.toggle)
        if (this.isPlay) {
          console.log(this.isPlay)
          this.$store.commit('setIsPlay', false);
        } else {
          this.$store.commit('setIsPlay', true);
        }
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import "../css/collect.css";

</style>
