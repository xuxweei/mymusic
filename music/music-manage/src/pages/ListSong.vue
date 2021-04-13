<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-collection"></i>歌单歌曲管理
    </div>
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="goBack">返回歌单</el-button>
        <el-button type="primary" size="mini" @click="delSelected">批量删除</el-button>
        <el-input placeholder="请输入歌曲名" v-model="select_word" size="mini" class="handle_input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button type="primary" size="mini" @click="dialogVisible = true">添加歌曲</el-button>
      </div>
    </div>
    <!-- 歌曲列表 -->
    <el-table :data="listSongData" style="width: 100%" size="mini" height="400px" border
      @selection-change="handleSelectionChange" ref="multipleTable" :cell-style="rowStyle"
      :cell-class-name="tableRowClassName">
      <el-table-column type="selection" width="50" align="center">
      </el-table-column>
      <!-- 名字 -->
      <el-table-column prop="song_name" label="歌手-歌曲" align="center">
      </el-table-column>
      <!-- 操作 -->
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" :total="listSongData.length"
        :current-page="currentPage" :page-size="pageSize" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <!-- 添加歌曲 -->
    <el-dialog title="添加歌曲" :visible.sync="dialogVisible" width="30%" center :before-close="handleClose">
      <!-- 歌手-歌曲 -->
      <el-form :model="addForm" ref="addForm" label-width="80px" class="demo-ruleForm">
        <el-form-item prop="singer_name" label="歌手" size="mini">
          <el-input v-model="addForm.singer_name" placeholder="歌手"></el-input>
        </el-form-item>
        <el-form-item prop="song_name" label="歌曲" size="mini">
          <el-input v-model="addForm.song_name" placeholder="歌曲"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="getSongId">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 删除对话框 -->
    <el-dialog title="删除歌曲" :visible.sync="delVisible" width="30%" center>
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
    listSongOfSongId,
    deleteListSongInfo,
    addListSongInfo,
    songOfSongId,
    songOfSongName
  } from "../api/index";
  export default {
    inject: ['reload'],
    mixins: [mixin],
    data() {
      return {
        //添加弹窗
        dialogVisible: false,
        //删除弹窗
        delVisible: false,
        //添加框
        addForm: {
          song_name: '',
          singer_name: ''
        },
        songlist_id: '', //歌单id
        listSongData: [],
        tempData: [],
        multipleSelection: [], //哪些项已被选中
        selectedNum: [],
        select_word: '',
        pageSize: 5, //每页显示条数
        currentPage: 1, //当前页数
        currentIndex: -1, //当前选择项
      }
    },
    computed: {
      //计算当前搜索结果表里的数据
      data() {
        return this.listSongData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
      }
    },

    created() {
      this.songlist_id = this.$route.query.id;
      this.getData();
    },
    destroyed() {
      this.$store.commit('setIsPlay', false)
    },

    watch: {
      //搜素框里面的内容发送变化时，搜索结果table列表的内容跟着变化
      select_word: function () {
        if (this.select_word == '') {
          this.listSongData = this.tempData;
        } else {
          this.listSongData = [];
          for (let i of this.tempData) {
            if (i.song_name.includes(this.select_word)) {
              this.listSongData.push(i);
            }
          }
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
      goBack(){
        window.history.go(-1)
      },
      //添加歌曲前的准备，获取到歌曲id
      getSongId(){
          let _this = this;
          var id = _this.addForm.singer_name + '-' + _this.addForm.song_name;
          songOfSongName(id).then(res => {
                  _this.addListSong(res[0].id);
              }
          )
      },
      //添加歌单歌曲
      addListSong(id) {
       let params = new URLSearchParams();
        params.append('song_id', id);
        params.append('songlist_id', this.songlist_id);

        addListSongInfo(params)
          .then((res) => {
            if (res.code == 1) {
              this.getData();
              this.notify("添加成功", "success");
            } else {
              this.notify("添加失败", "error");
            }
          })
          .catch(err => {
            console.log(err)
          });
        this.editVisible = false;
        this.reload();
      },
      //查询所有歌手
      getData() {
        this.tempData = [];
        this.listSongData = [];
        listSongOfSongId(this.songlist_id).then(res => {
            console.log(res)
          for (let item of res) {
            this.getSong(item.song_id);
          }
          this.currentPage = 1;
        })
      },
      //根据歌曲id查询歌曲列表,返回数据放到listSongData、tempData中
      getSong(id) {
          songOfSongId(id)
          .then(res=>{
              this.tempData.push(res);
              this.listSongData.push(res);
          })
          .catch(err=>{
              console.log(err);
          })
      },
      //获取当前页
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
      },

      // 删除歌曲信息
      deleteRow() {
        deleteListSongInfo(this.currentIndex,this.songlist_id)
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
      }
    },

    components: {}

  }

</script>

<style>
  @import "../css/song.css";

</style>
