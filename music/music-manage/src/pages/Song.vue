<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-mic"></i>歌曲管理
    </div>
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="goBack">返回歌手</el-button>
        <el-button type="primary" size="mini" @click="delSelected">批量删除</el-button>
        <el-input placeholder="请输入歌曲名" v-model="select_word" size="mini" class="handle_input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button type="primary" size="mini" @click="dialogVisible = true">添加歌曲</el-button>
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
      <el-table-column prop="song_name" label="歌曲名" width="140" align="center">
      </el-table-column>
      <!-- 歌词 -->
      <el-table-column prop="lyric" label="歌词" align="center">
        <template slot-scope="scope">
          <p style="height:100px;overflow:scroll">
            <ul style="height:100px;overflow:scoll;">
              <li v-for="(item,index) in parseLyric(scope.row.lyric)" :key="index">
                {{item}}
              </li>
            </ul>
          </p>
        </template>
      </el-table-column>
      <!-- 专辑 -->
      <el-table-column prop="song_intro" label="专辑" width="150" align="center">
      </el-table-column>
      <!-- 资源更新 -->
      <el-table-column prop="song_intro" label="资源更新" align="center" width="150">
        <template slot-scope="scope">
          <el-upload class="upload-demo" :action="uploadSongPic(scope.row.id)" :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload" :show-file-list="false">
            <el-button size="mini" type="primary">上传图片</el-button>
          </el-upload>
          <br />
          <el-upload class="upload-demo" :action="uploadSongUrl(scope.row.id)" :on-success="handleSongSuccess"
            :before-upload="beforeSongUpload" :show-file-list="false">
            <el-button size="mini" type="primary">更新歌曲</el-button>
          </el-upload>
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
      <el-pagination background layout="total,prev, pager, next" :total="songData.length" :current-page="currentPage"
        :page-size="pageSize" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <!-- 添加歌曲 -->
    <el-dialog title="添加歌曲" :visible.sync="dialogVisible" width="30%" center :before-close="handleClose">
      <!-- 歌手-歌曲 -->
      <el-form :model="addForm" ref="addForm" label-width="80px" class="demo-ruleForm" action="" id="tf" :rules="rules">
        <el-form-item prop="song_name" label="歌曲名" size="mini">
          <el-input v-model="addForm.song_name" placeholder="歌曲名"></el-input>
        </el-form-item>
        <el-form-item prop="song_intro" label="专辑" size="mini">
          <el-input type="text" v-model="addForm.song_intro"></el-input>
        </el-form-item>
        <el-form-item prop="lyric" label="歌词" size="mini">
          <el-input type="textarea" v-model="addForm.lyric"></el-input>
        </el-form-item>
        <div>
          <label style="margin-left: 15px; margin-right: 5px;">歌曲上传</label>
          <input type="file" name="file">
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSong">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑歌手信息 -->
    <el-dialog title="编辑歌曲信息" :visible.sync="editVisible" width="30%" center :before-close="handleClose">
      <!-- 歌手名 -->
      <el-form :model="editForm" ref="editForm" label-width="80px" class="demo-ruleForm" :rules="rules"> 
        <el-form-item prop="song_name" label="歌曲名" size="mini">
          <el-input v-model="editForm.song_name" placeholder="歌曲名"></el-input>
        </el-form-item>
        <!-- 专辑 -->
        <el-form-item prop="song_intro" label="专辑" size="mini">
          <el-input type="text" v-model="editForm.song_intro" placeholder="专辑"></el-input>
        </el-form-item>
        <!-- 歌词 -->
        <el-form-item prop="lyric" label="歌词" size="mini">
          <el-input type="textarea" v-model="editForm.lyric" placeholder="歌词"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="editSong">确 定</el-button>
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
    mapGetters
  } from 'vuex';
  import '@/assets/js/iconfont.js';
  import {
    songOfSingerId,
    editSongInfo,
    deleteSongInfo
  } from "../api/index";
  export default {
    inject: ['reload'],
    mixins: [mixin],
    data() {
      return {
        //歌手id
        singer_id: '',
        //歌手名
        singer_name: '',
        //添加弹窗
        dialogVisible: false,
        //编辑弹窗
        editVisible: false,
        //删除弹窗
        delVisible: false,
        //添加框
        addForm: {
          song_name: '',
          singer_name: '',
          song_intro: '',
          lyric: ''
        },
        //编辑框
        editForm: {
          id: '',
          song_name: '',
          song_intro: '',
          lyric: ''
        },
        songData: [],
        tempData: [],
        multipleSelection: [], //哪些项已被选中
        selectedNum: [],
        select_word: '',
        pageSize: 5, //每页显示条数
        currentPage: 1, //当前页数
        currentIndex: -1, //当前选择项
        toggle: false, //播放器的图标状态
        rules: {
          song_name: [{
            required: true,
            message: '歌曲名不能为空',
            trigger: 'blur'
          }],
          song_intro: [{
            required: true,
            message: '专辑不能为空',
            trigger: 'blur'
          }],
          url: [{
            required: true,
            message: '歌曲文件不能为空',
            trigger: 'blur'
          }]
        }
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
      this.singer_id = this.$route.query.id;
      this.singer_name = this.$route.query.name;
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
        } else {
          this.songData = [];
          for (let i of this.tempData) {
            if (i.song_name.includes(this.select_word)) {
              this.songData.push(i);
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
      //添加歌手
      addSong() {
        this.$refs['addForm'].validate(valid => {
          if (valid) {
            let _this = this;
            var form = new FormData(document.getElementById('tf'));
            form.append('singer_id', this.singer_id);
            form.set('song_name', this.singer_name + '-' + this.addForm.song_name);
            form.append('song_intro', this.addForm.song_intro);
            if (this.addForm.lyric != '') {
              form.set('lyric', this.addForm.lyric);
            } else {
              form.set('lyric', this.addForm.lyric = '[00:00:00]暂无歌词');
            }
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
              // req.readyState == 4 获取到返回的完整数据
              // req.status == 200 和后台正常交互完成
              if (req.readyState === 4 && req.status === 200) {
                let res = JSON.parse(req.response);
                if (res.code) {
                  _this.getData();
                  _this.addForm = {};
                  _this.notify(res.msg, 'success');
                } else {
                  _this.notify('保存失败', 'error');
                }
              }
            }
            req.open('post', `${_this.$store.state.HOST}/song/add`, false);
            req.send(form);
            _this.dialogVisible = false;
            this.reload();
          }
        })

      },
      //查询所有歌手
      getData() {
        this.tempData = [];
        this.songData = [];
        songOfSingerId(this.singer_id).then(res => {
          this.songData = res;
          this.tempData = res;
          this.currentPage = 1;
        })
      },
      //上传图片
      uploadSongPic(id) {
        return `${this.$store.state.HOST}/song/updateSongOfAvator?id=${id}`;
      },
      //跟新歌曲url
      uploadSongUrl(id) {
        return `${this.$store.state.HOST}/song/updateSongOfUrl?id=${id}`;
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
          singer_id: this.singer_id,
          song_name: row.song_name,
          song_intro: row.song_intro,
          lyric: row.lyric
        }
      },

      //编辑歌手信息
      editSong() {
        this.$refs['editForm'].validate(valid => {
          if (valid) {
            let params = new URLSearchParams();
            params.append('id', this.editForm.id);
            params.append('singer_id', this.singer_id);
            params.append('song_name', this.editForm.song_name);
            params.append('song_intro', this.editForm.song_intro);
            params.append('lyric', this.editForm.lyric);

            editSongInfo(params)
              .then((res) => {
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

      // 删除歌曲信息
      deleteRow() {
        deleteSongInfo(this.currentIndex)
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
      //解析歌词
      parseLyric(text) {
        //将歌词转为数组
        let lines = text.split("\n");
        let pattern = /(\[\d{2}:\d{2}.(\d{3}|\d{2})\]|\[\d{2}:\d{2}\])/g;
        let result = [];
        //遍历数组将所有符合正则的条件转为空
        for (let item of lines) {
          let value = item.replace(pattern, '');
          result.push(value);
        }
        return result;
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

    components: {}

  }

</script>

<style scoped>

  @import "../css/song.css";

</style>
