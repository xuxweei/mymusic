<template>
  <div class="table">
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="delSelected">批量删除</el-button>
        <el-input placeholder="请输入歌手名" v-model="select_word" size="mini" class="handle_input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button type="primary" size="mini" @click="dialogVisible = true">添加歌手</el-button>
      </div>
    </div>
    <!-- 歌手列表 -->
    <el-table :data="data" style="width: 100%" size="mini" height="430px" border
      @selection-change="handleSelectionChange" ref="multipleTable" :cell-style="rowStyle"
      :cell-class-name="tableRowClassName">
      <el-table-column type="selection" width="50" align="center">
      </el-table-column>
      <!-- 图片 -->
      <el-table-column label="歌手图片" width="110" align="center">
        <template slot-scope="scope">
          <div class="singer_img">
            <img :src="getUrl(scope.row.pic)" style="width:100%" />
          </div>
          <el-upload class="upload-demo" :action="uploadSingerPic(scope.row.id)" :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload" :show-file-list="false">
            <el-button size="mini" type="primary">上传图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <!-- 名字 -->
      <el-table-column prop="name" label="歌手名" width="120" align="center">
      </el-table-column>
      <!-- 性别 -->
      <el-table-column label="性别" align="center" width="80">
        <template slot-scope="scope">
          {{getSingerSex(scope.row.sex)}}
        </template>
      </el-table-column>
      <!-- 生日 -->
      <el-table-column prop="singer_birth" label="生日" width="120" align="center">
        <template slot-scope="scope">
          {{getSingerBirth(scope.row.singer_birth)}}
        </template>
      </el-table-column>
      <!-- 地区 -->
      <el-table-column prop="singer_location" label="地区" width="120" align="center">
      </el-table-column>
      <!-- 简介 -->
      <el-table-column prop="singer_intro" label="歌手简介" align="center">
        <template slot-scope="scope">
          <p style="height:100px;overflow:scroll">{{scope.row.singer_intro}}</p>
        </template>
      </el-table-column>
      <!-- 歌曲管理 -->
      <el-table-column label="歌曲管理" align="center" width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="skipSong(scope.row.id,scope.row.name)">歌曲管理</el-button>
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
      <el-pagination background layout="total,prev, pager, next" :total="singerData.length" :current-page="currentPage"
        :page-size="pageSize" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <!-- 添加歌手 -->
    <el-dialog title="添加歌手" :visible.sync="dialogVisible" width="30%" center :before-close="handleClose">
      <!-- 歌手名 -->
      <el-form :model="addForm" ref="addForm" label-width="80px" class="demo-ruleForm" :rules="rules">
        <el-form-item prop="name" label="歌手名" size="mini">
          <el-input v-model="addForm.name" placeholder="歌手名"></el-input>
        </el-form-item>
        <!-- 性别 -->
        <el-form-item prop="sex" label="性别" size="mini">
          <el-radio-group v-model="addForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">组合</el-radio>
            <el-radio :label="3">不明</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 生日 -->
        <el-form-item prop="singer_birth" label="生日" size="mini">
          <el-date-picker v-model="addForm.singer_birth" type="date" placeholder="选择日期" style="width:100%">
          </el-date-picker>
        </el-form-item>
        <!-- 地区 -->
        <el-form-item prop="singer_location" label="地区" size="mini">
          <el-input v-model="addForm.singer_location" placeholder="地区"></el-input>
        </el-form-item>
        <!-- 介绍信息 -->
        <el-form-item prop="singer_intro" label="简介" size="mini">
          <el-input type="textarea" v-model="addForm.singer_intro" placeholder="简介"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSinger">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑歌手信息 -->
    <el-dialog title="编辑歌手信息" :visible.sync="editVisible" width="30%" center :before-close="handleClose">
      <!-- 歌手名 -->
      <el-form :model="editForm" ref="editForm" label-width="80px" class="demo-ruleForm" :rules="rules">
        <el-form-item prop="name" label="歌手名" size="mini">
          <el-input v-model="editForm.name" placeholder="歌手名"></el-input>
        </el-form-item>
        <!-- 性别 -->
        <el-form-item prop="sex" label="性别" size="mini">
          <el-radio-group v-model="editForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">组合</el-radio>
            <el-radio :label="3">不明</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 生日 -->
        <el-form-item label="生日" size="mini">
          <el-date-picker v-model="editForm.singer_birth" type="date" placeholder="选择日期" style="width:100%">
          </el-date-picker>
        </el-form-item>
        <!-- 地区 -->
        <el-form-item prop="singer_location" label="地区" size="mini">
          <el-input v-model="editForm.singer_location" placeholder="地区"></el-input>
        </el-form-item>
        <!-- 介绍信息 -->
        <el-form-item prop="singer_intro" label="简介" size="mini">
          <el-input type="textarea" v-model="editForm.singer_intro" placeholder="简介"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="editSinger">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 删除对话框 -->
    <el-dialog title="删除歌手" :visible.sync="delVisible" width="30%" center>
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
    addSingerInfo,
    getAllSinger,
    editSingerInfo,
    deleteSingerInfo
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
          name: '',
          sex: '',
          singer_birth: '',
          singer_location: '',
          singer_intro: ''
        },
        //编辑框
        editForm: {
          id: '',
          name: '',
          sex: '',
          singer_birth: '',
          singer_location: '',
          singer_intro: ''
        },
        singerData: [],
        tempData: [],
        multipleSelection: [], //哪些项已被选中
        selectedNum: [],
        select_word: '',
        pageSize: 5, //每页显示条数
        currentPage: 1, //当前页数
        currentIndex: -1, //当前选择项
        rules: {
          name: [{
            required: true,
            message: '歌手名不能为空',
            trigger: 'blur'
          }],
          sex: [{
            required: true,
            message: '性别不能为空',
            trigger: 'blur'
          }],
          singer_birth: [{
            required: true,
            message: '生日不能为空',
            trigger: 'blur'
          }],
          singer_location: [{
            required: true,
            message: '地区不能为空',
            trigger: 'blur'
          }],
          singer_intro: [{
            required: true,
            message: '歌手介绍不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    computed: {
      //计算当前搜索结果表里的数据
      data() {
        return this.singerData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
      }
    },

    created() {
      this.getData();
    },

    watch: {
      //搜素框里面的内容发送变化时，搜索结果table列表的内容跟着变化
      select_word: function () {
        if (this.select_word == '') {
          this.singerData = this.tempData;
        } else {
          this.singerData = [];
          for (let i of this.tempData) {
            if (i.name.includes(this.select_word)) {
              this.singerData.push(i);
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
      //添加歌手
      addSinger() {
        this.$refs['addForm'].validate(valid => {
          if (valid) {
            let birthday = this.addForm.singer_birth;
            let datetime = birthday.getFullYear() + '-' + (birthday.getMonth() + 1) + '-' + birthday.getDate();
            let params = new URLSearchParams();
            params.append('name', this.addForm.name);
            params.append('sex', this.addForm.sex);
            params.append('pic', '/img/singerPic/singerPic.png');
            params.append('singer_birth', datetime);
            params.append('singer_location', this.addForm.singer_location);
            params.append('singer_intro', this.addForm.singer_intro);

            addSingerInfo(params)
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
      //查询所有歌手
      getData() {
        this.tempData = [];
        this.singerData = [];
        getAllSinger().then(res => {
          this.singerData = res;
          this.tempData = res;
          this.currentPage = 1;
        })
      },
      //上传图片
      uploadSingerPic(id) {
        return `${this.$store.state.HOST}/singer/uploadSingerOfAvator?id=${id}`;
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
          name: row.name,
          sex: row.sex,
          singer_birth: row.singer_birth,
          singer_location: row.singer_location,
          singer_intro: row.singer_intro
        }
      },

      //编辑歌手信息
      editSinger() {
        this.$refs['editForm'].validate(valid => {
          if (valid) {
            let birthday = new Date(this.editForm.singer_birth);
            let datetime = birthday.getFullYear() + '-' + (birthday.getMonth() + 1) + '-' + birthday.getDate();
            let params = new URLSearchParams();
            params.append('id', this.editForm.id);
            params.append('name', this.editForm.name);
            params.append('sex', this.editForm.sex);
            params.append('singer_birth', datetime);
            params.append('singer_location', this.editForm.singer_location);
            params.append('singer_intro', this.editForm.singer_intro);

            editSingerInfo(params)
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
        deleteSingerInfo(this.currentIndex)
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
      skipSong(id, name) {
        this.$router.push({
          path: `/song`,
          query: {
            id: id,
            name: name
          }
        });
      }
    },

    components: {},
  }

</script>

<style>
  @import "../css/singer.css";

</style>
