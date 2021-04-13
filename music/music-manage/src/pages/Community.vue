<template>
  <div class="table">
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="delSelected">批量删除</el-button>
        <el-input placeholder="请输入要查询的用户动态" v-model="select_word" size="mini" class="handle_input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button type="primary" size="mini" @click="dialogVisible = true">添加动态</el-button>
      </div>
    </div>
    <!-- 动态列表 -->
    <el-table :data="data" style="width: 100%" size="mini" height="430px" border
      @selection-change="handleSelectionChange" ref="multipleTable" :cell-style="rowStyle"
      :cell-class-name="tableRowClassName">
      <el-table-column type="selection" width="50" align="center">
      </el-table-column>
      <!-- 动态创建者-->
      <el-table-column prop="name" label="动态创建者" width="120" align="center">
      </el-table-column>
      <el-table-column prop="type" label="动态类型" width="80" align="center">
        <template slot-scope="scope">
          {{getType(scope.row.type)}}
        </template>
      </el-table-column>
      <el-table-column prop="time" label="发布时间" width="180" align="center">
        <template slot-scope="scope">
          <p style="height:100px;line-height: 100px;">{{getTime(scope.row.create_time)}}</p>
        </template>
      </el-table-column>
      <!-- 动态内容 -->
      <el-table-column prop="content" label="动态内容" align="center">
        <template slot-scope="scope">
          <p style="height:100px;overflow:scroll">{{scope.row.content}}</p>
        </template>
      </el-table-column>
      <el-table-column prop="song_intro" label="上传资源" align="center" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="skipSource(scope.row.id)">查看资源</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="song_intro" label="上传资源" align="center" width="150">
        <template slot-scope="scope">
          <el-upload class="upload-demo" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload"
            :show-file-list="false" :action="uploadImg(scope.row.id)">
            <el-button size="mini" type="primary">上传图片</el-button>
          </el-upload>
          <el-upload class="upload-demo" :on-success="handleSongSuccess" :before-upload="beforeSongUpload"
            :show-file-list="false" :action="uploadUrl(scope.row.id)">
            <el-button size="mini" type="primary">上传歌曲</el-button>
          </el-upload>
          <el-upload class="upload-demo" :on-success="handleUrlSuccess" :before-upload="beforeUrlUpload"
            :show-file-list="false" :action="uploadUrl(scope.row.id)">
            <el-button size="mini" type="primary">上传视频</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <!-- </el-form-item>
      </el-form> -->
      <!-- 操作 -->
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" :total="communityData.length"
        :current-page="currentPage" :page-size="pageSize" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <!-- 添加歌单 -->
    <el-dialog title="添加动态" :visible.sync="dialogVisible" width="30%" center :before-close="handleClose">
      <!-- 歌单名 -->
      <el-form :model="addForm" ref="addForm" label-width="80px" class="demo-ruleForm" :rules="rules">
        <!-- 动态作者-->
        <el-form-item prop="name" label="动态创建者" size="mini">
          <el-input v-model="addForm.name" placeholder="动态创建者"></el-input>
        </el-form-item>
        <el-form-item prop="type" label="类型" size="mini">
          <el-select v-model="addForm.type" placeholder="请选择类型" @change.native="getTypes" @blur.native="getTypes">
            <el-option label="歌曲" value="0"></el-option>
            <el-option label="视频" value="1"></el-option>
            <el-option label="图片" value="2"></el-option>
            <el-option label="文字" value="3"></el-option>
          </el-select>
        </el-form-item>
        <!-- 介绍信息 -->
        <el-form-item prop="content" label="动态内容" size="mini">
          <el-input type="textarea" v-model="addForm.content" placeholder="动态内容"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCommunity">确 定</el-button>
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
    addCommunity,
    getAllCommunity,
    deleteCommunity
  } from "../api/index"
  import {
    mixin
  } from "../mixins/index"
  export default {
    name:'community',
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
          type: '',
          content: '',
          time: ''
        },
        communityData: [],
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
            message: '动态创建者不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    computed: {
      //计算当前搜索结果表里的数据
      data() {
        return this.communityData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
      }
    },

    created() {
      this.getData();
    },
    watch: {
      //搜素框里面的内容发送变化时，搜索结果table列表的内容跟着变化
      select_word: function () {
        if (this.select_word == '') {
          this.communityData = this.tempData;
        } else {
          this.communityData = [];
          for (let i of this.tempData) {
            if (i.name.includes(this.select_word) | i.content.includes(this.select_word)) {
              this.communityData.push(i);
            }
          }
        }
      }
    },


    methods: {
      getType(t) {
        if (t == 0) {
          return '歌曲';
        }
        if (t == 1) {
          return '视频';
        }
        if (t == 2) {
          return '图片';
        }
        if (t == 3) {
          return '文字';
        }
        return t;
      },
      getTypes(e) {
        this.addForm.type = e.target.value
      },
      //查询所有歌单
      getData() {
        this.tempData = [];
        this.communityData = [];
        getAllCommunity().then(res => {
          this.communityData = res;
          this.tempData = res;
          this.currentPage = 1;
        })
      },
      //添加歌单
      addCommunity() {
        this.$refs['addForm'].validate(valid => {
          if (valid) {
            let params = new URLSearchParams();
            params.append('name', this.addForm.name);
            params.append('type', this.addForm.type);
            if (this.addForm.url != '') {
              params.append('url', this.addForm.url);
            } else if ((this.addForm.type == 0 || this.addForm.type == 1) && this.addForm.url == '') {
              params.append('url', this.addForm.url = '');
            }
            if (this.addForm.content != '') {
              params.append('content', this.addForm.content);
            } else {
              params.append('content', this.addForm.content = '');
            }
            if (this.addForm.img != '') {
              console.log(this.addForm.img);
              params.append('img', this.addForm.img);
            } else {
              params.append('img', this.addForm.img = '');
            }
            addCommunity(params)
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
      //关闭对话框
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      //切换播放音乐
      // setSongUrl(url, song_name) {
      //   this.$store.commit('setUrl', this.$store.state.HOST + url);
      //   this.toggle = song_name;
      //   console.log(this.toggle)
      //   if (this.isPlay) {
      //     console.log(this.isPlay)
      //     this.$store.commit('setIsPlay', false);
      //   } else {
      //     this.$store.commit('setIsPlay', true);
      //   }
      // },
      // 确定删除
      deleteRow() {
        deleteCommunity(this.currentIndex)
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
      //获取当前页
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
      },
      uploadImg(id) {
        return `${this.$store.state.HOST}/community/uploadImg?id=${id}`;
      },
      //跟新歌曲url
      uploadUrl(id) {
        return `${this.$store.state.HOST}/community/uploadUrl?id=${id}`;
      },
      getTime(da) {
        return String(da).substr(0, 16);
      },
      //上传视频前的校验
      beforeUrlUpload(file) {
        //获取文件扩展名
        var testMsg = file.name.substring(file.name.lastIndexOf('.') + 1);
        const isLt10M = (file.size / 1024 / 1024);
        // 判断是否为mp4格式
        const extension = (testMsg === 'mp4');
        if (!extension) {
          this.$message({
            message: "上传文件只能是mp4格式",
            type: "error"
          });
          return false;
        }
        if (!isLt10M) {
          this.$message.error('上传视频大小不能超过 10MB!');
          return false;
        }
        return true;
      },
      //上传视频成功之后
      handleUrlSuccess(res, file) {
        let _this = this;
        if (res.code === 1) {
          // _this.imageUrl = URL.createObjectURL(file.raw);
          console.log(URL.createObjectURL(file.raw));
          _this.getData();
          _this.$notify({
            title: '上传成功',
            type: 'success'
          });
        } else {
          _this.$notify({
            title: '上传失败',
            type: 'error'
          });
        }
      },
      skipSource(id) {
        this.$router.push({
          path: `/resource`,
          query: {
            id: id,
          }
        });
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import '../css/songlist.css';

  .upload-demo {
    margin: 10px 0;
  }

</style>
