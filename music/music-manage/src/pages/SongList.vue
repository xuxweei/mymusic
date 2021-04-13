<template>
  <div class="table">
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="delSelected">批量删除</el-button>
        <el-input placeholder="请输入歌单名/歌单风格" v-model="select_word" size="mini" class="handle_input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button type="primary" size="mini" @click="dialogVisible = true">添加歌单</el-button>
      </div>
    </div>
    <!-- 歌单列表 -->
    <el-table :data="data" style="width: 100%" size="mini" height="430px" border
      @selection-change="handleSelectionChange" ref="multipleTable" :cell-style="rowStyle"
      :cell-class-name="tableRowClassName">
      <el-table-column type="selection" width="50" align="center">
      </el-table-column>
      <!-- 图片 -->
      <el-table-column label="歌单图片" width="110" align="center">
        <template slot-scope="scope">
          <div class="songlist_img">
            <img :src="getUrl(scope.row.pic)" style="width:100%" />
          </div>
          <el-upload class="upload-demo" :action="uploadSongListPic(scope.row.id)" :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload" :show-file-list="false">
            <el-button size="mini" type="primary">上传图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <!-- 名字 -->
      <el-table-column prop="title" label="歌单名" width="120" align="center">
      </el-table-column>
      <!-- 风格 -->
      <el-table-column prop="style" label="歌单风格" width="120" align="center">
      </el-table-column>
      <!-- 歌单创作者-->
      <el-table-column prop="author" label="歌单作者" width="120" align="center">
      </el-table-column>
      <!-- 简介 -->
      <el-table-column prop="songlist_intro" label="歌单简介" align="center">
        <template slot-scope="scope">
          <p style="height:100px;overflow:scroll">{{scope.row.songlist_intro}}</p>
        </template>
      </el-table-column>
      <!-- 歌曲管理 -->
      <el-table-column label="歌曲管理" align="center" width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="skipSong(scope.row.id,scope.row.title)">歌单歌曲管理</el-button>
        </template>
      </el-table-column>
      <!-- 评论管理 -->
      <el-table-column label="评论" align="center" width="80">
        <template slot-scope="scope">
          <el-button type="success" plain size="mini" @click="skipComment(scope.row.id)">评论</el-button>
        </template>
      </el-table-column>
      <!-- 操作 -->
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" :total="songListData.length"
        :current-page="currentPage" :page-size="pageSize" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <!-- 添加歌单 -->
    <el-dialog title="添加歌单" :visible.sync="dialogVisible" width="30%" center :before-close="handleClose">
      <!-- 歌单名 -->
      <el-form :model="addForm" ref="addForm" label-width="80px" class="demo-ruleForm" :rules="rules">
        <el-form-item prop="title" label="歌单名" size="mini">
          <el-input v-model="addForm.title" placeholder="歌单名"></el-input>
        </el-form-item>
        <el-form-item prop="style" label="歌单风格" size="mini">
          <el-input v-model="addForm.style" placeholder="歌单风格"></el-input>
        </el-form-item>
        <!-- 歌单作者-->
        <el-form-item prop="author" label="歌单作者" size="mini">
          <el-input v-model="addForm.author" placeholder="歌单作者"></el-input>
        </el-form-item>
        <!-- 介绍信息 -->
        <el-form-item prop="songlist_intro" label="简介" size="mini">
          <el-input type="textarea" v-model="addForm.songlist_intro" placeholder="简介"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSongList">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑歌单信息 -->
    <el-dialog title="编辑歌单信息" :visible.sync="editVisible" width="30%" center :before-close="handleClose">
      <!-- 歌单名 -->
      <el-form :model="editForm" ref="editForm" label-width="80px" class="demo-ruleForm" :rules="rules">
        <el-form-item prop="title" label="歌单名" size="mini">
          <el-input v-model="editForm.title" placeholder="歌单名"></el-input>
        </el-form-item>
        <!-- 歌单风格 -->
        <el-form-item prop="style" label="歌单风格" size="mini">
          <el-input v-model="editForm.style" placeholder="歌单风格"></el-input>
        </el-form-item>
        <!-- 歌单作者 -->
        <el-form-item prop="author" label="歌单作者" size="mini">
          <el-input v-model="editForm.author" placeholder="歌单作者"></el-input>
        </el-form-item>
        <!-- 介绍信息 -->
        <el-form-item prop="songlist_intro" label="简介" size="mini">
          <el-input type="textarea" v-model="editForm.songlist_intro" placeholder="简介"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="editSongList">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 删除对话框 -->
    <el-dialog title="删除歌单" :visible.sync="delVisible" width="30%" center>
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
    addSongListInfo,
    getAllSongList,
    editSongListInfo,
    deleteSongListInfo
  } from "../api/index"
  import {
    mixin
  } from "../mixins/index"
  export default {
    inject: ['reload'],
    mixins: [mixin],
    data() {
      return {
        //添加弹窗
        dialogVisible: false,
        //编辑弹窗
        editVisible: false,
        //删除弹窗
        delVisible: false,
        //添加框
        addForm: {
          title: '',
          style: '',
          author: '',
          songlist_intro: ''
        },
        //编辑框
        editForm: {
          id: '',
          title: '',
          style: '',
          author: '',
          songlist_intro: ''
        },
        songListData: [],
        tempData: [],
        multipleSelection: [], //哪些项已被选中
        selectedNum: [],
        select_word: '',
        pageSize: 5, //每页显示条数
        currentPage: 1, //当前页数
        currentIndex: -1, //当前选择项
        rules: {
          title: [{
            required: true,
            message: '标题不能为空',
            trigger: 'blur'
          }],
          style: [{
            required: true,
            message: '风格不能为空',
            trigger: 'blur'
          }],
          author: [{
            required: true,
            message: '作者不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    computed: {
      //计算当前搜索结果表里的数据
      data() {
        return this.songListData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
      }
    },

    created() {
      this.getData();
    },

    watch: {
      //搜素框里面的内容发送变化时，搜索结果table列表的内容跟着变化
      select_word: function () {
        if (this.select_word == '') {
          this.songListData = this.tempData;
        } else {
          this.songListData = [];
          for (let i of this.tempData) {
            if (i.title.includes(this.select_word) | i.style.includes(this.select_word)) {
              this.songListData.push(i);
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
      //添加歌单
      addSongList() {
        this.$refs['addForm'].validate(valid => {
          if (valid) {
            let params = new URLSearchParams();
            params.append('title', this.addForm.title);
            params.append('pic', '/img/songListPic/songList.png');
            params.append('style', this.addForm.style);
            params.append('author', this.addForm.author);
            // params.append('songlist_intro', this.addForm.songlist_intro);

            if (this.addForm.songlist_intro != '') {
              params.append('songlist_intro', this.addForm.songlist_intro);
            } else {
              params.append('songlist_intro', this.addForm.songlist_intro = '暂无歌单介绍');
            }
            addSongListInfo(params)
              .then(res => {
                if (res.code == 1) {
                  this.getData();
                  this.notify("添加成功", "success");
                  this.reload();
                } else {
                  this.notify("添加失败", "error");
                }
              })
              .catch(err => {
                console.log(err)
              });
            this.dialogVisible = false;
          }
        })


      },
      //查询所有歌单
      getData() {
        this.tempData = [];
        this.songListData = [];
        getAllSongList().then(res => {
          this.songListData = res;
          this.tempData = res;
          this.currentPage = 1;
        })
      },
      //上传图片
      uploadSongListPic(id) {
        return `${this.$store.state.HOST}/songList/updateSongListListOfAvator?id=${id}`;
      },
      //获取当前页
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
      },
      //弹出编辑框
      handleEdit(index, row) {
        console.log(index, row);
        this.editVisible = true;
        this.editForm = {
          id: row.id,
          title: row.title,
          style: row.style,
          author: row.author,
          songlist_intro: row.songlist_intro
        }
      },

      //编辑歌单信息
      editSongList() {
        this.$refs['editForm'].validate(valid => {
          if (valid) {
            let params = new URLSearchParams();
            params.append('id', this.editForm.id);
            params.append('title', this.editForm.title);
            params.append('style', this.editForm.style);
            params.append('author', this.editForm.author);
            params.append('songlist_intro', this.editForm.songlist_intro);

            editSongListInfo(params)
              .then(res => {
                if (res.code == 1) {
                  this.getData();
                  this.notify("编辑成功", "success");
                } else {
                  this.notify("编辑失败", "error");
                }
              })
              .catch(err => {
                console.log(err)
              });
            this.editVisible = false;
            this.reload();
          }
        })

      },
      // 确定删除
      deleteRow() {
        deleteSongListInfo(this.currentIndex)
          .then(res => {
            if (res) {
              this.getData()
              this.notify('删除成功', 'success')
            } else {
              this.notify('删除失败', 'error')
            }
          })
          .catch(err => {
            console.log(err)
          })
        this.delVisible = false
      },
      skipSong(id, title) {
        this.$router.push({
          path: `/listsong`,
          query: {
            id: id,
            tilte: title
          }
        });
      },
      skipComment(id){
        this.$router.push({
          path:'/comment',
          query:{id}
        })
      }
    },

    components: {},
  }

</script>

<style>
  @import '../css/songlist.css';

</style>
