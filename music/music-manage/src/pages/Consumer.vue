<template>
  <div class="table">
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="delSelected">批量删除</el-button>
        <el-input placeholder="请输入用户名" v-model="select_word" size="mini" class="handle_input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button type="primary" size="mini" @click="dialogVisible = true">添加新用户</el-button>
      </div>
    </div>
    <!-- 用户列表 -->
    <el-table :data="data" style="width: 100%" size="mini" height="430px" border
      @selection-change="handleSelectionChange" ref="multipleTable" :cell-style="rowStyle"
      :cell-class-name="tableRowClassName">
      <el-table-column type="selection" width="50" align="center">
      </el-table-column>
      <!-- 图片 -->
      <el-table-column label="用户图片" width="110" align="center">
        <template slot-scope="scope">
          <div class="consumer_img">
            <img :src="getUrl(scope.row.userpic)" style="width:100%" />
          </div>
          <el-upload class="upload-demo" :action="uploadConsumerPic(scope.row.id)" :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload" :show-file-list="false">
            <el-button size="mini" type="primary">上传图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <!-- 名字 -->
      <el-table-column prop="username" label="用户名" width="120" align="center">
      </el-table-column>
      <!-- 性别 -->
      <el-table-column label="性别" align="center" width="80">
        <template slot-scope="scope">
          {{getConsumerSex(scope.row.sex)}}
        </template>
      </el-table-column>
      <!-- 手机号 -->
      <el-table-column prop="phone_num" label="用户手机号" width="120" align="center">
      </el-table-column>
      <!-- 电子邮箱 -->
      <el-table-column prop="email" label="用户电子邮箱" width="120" align="center">
      </el-table-column>
      <!-- 生日 -->
      <el-table-column prop="birth" label="生日" width="120" align="center">
        <template slot-scope="scope">
          {{getConsumerBirth(scope.row.birth)}}
        </template>
      </el-table-column>
      <!-- 地区 -->
      <el-table-column prop="location" label="地区" width="120" align="center">
      </el-table-column>
      <!-- 简介 -->
      <el-table-column prop="introduction" label="用户简介" align="center">
      </el-table-column>
      <!-- 收藏管理 -->
      <el-table-column label="收藏" align="center" width="80">
        <template slot-scope="scope">
          <el-button type="success" plain size="mini" @click="skipCollect(scope.row.id)">收藏</el-button>
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
      <el-pagination background layout="total,prev, pager, next" :total="consumerData.length"
        :current-page="currentPage" :page-size="pageSize" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <!-- 添加用户 -->
    <el-dialog title="添加用户" :visible.sync="dialogVisible" width="30%" center :before-close="handleClose">
      <!-- 用户名 -->
      <el-form :model="addForm" ref="addForm" label-width="80px" class="demo-ruleForm" :rules="rules">
        <el-form-item prop="username" label="用户名" size="mini">
          <el-input v-model="addForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input v-model="addForm.password" placeholder="密码" :type="IsPassword">
            <i slot="suffix" :class="iconPassword" @click="showPassword"></i>
          </el-input>
        </el-form-item>
        <!-- 性别 -->
        <el-form-item prop="sex" label="性别" size="mini">
          <el-radio-group v-model="addForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 手机号 -->
        <el-form-item prop="phone_num" label="手机号" size="mini">
          <el-input v-model="addForm.phone_num" placeholder="手机号"></el-input>
        </el-form-item>
        <!-- 电子邮箱 -->
        <el-form-item prop="email" label="电子邮箱" size="mini">
          <el-input v-model="addForm.email" placeholder="电子邮箱"></el-input>
        </el-form-item>
        <!-- 生日 -->
        <el-form-item prop="birth" label="生日" size="mini">
          <el-date-picker v-model="addForm.birth" type="date" placeholder="选择日期" style="width:100%">
          </el-date-picker>
        </el-form-item>
        <!-- 介绍信息 -->
        <el-form-item prop="introduction" label="简介" size="mini">
          <el-input type="text" v-model="addForm.introduction" placeholder="简介"></el-input>
        </el-form-item>
        <!-- 地区 -->
        <el-form-item prop="location" label="地区" size="mini">
          <el-input v-model="addForm.location" placeholder="地区"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addConsumer">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑用户信息 -->
    <el-dialog title="编辑用户信息" :visible.sync="editVisible" width="30%" center :before-close="handleClose">
      <!-- 用户名 -->
      <el-form :model="editForm" ref="editForm" label-width="80px" class="demo-ruleForm" :rules="rules">
        <el-form-item prop="username" label="用户名" size="mini">
          <el-input v-model="editForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input v-model="editForm.password" placeholder="密码" :type="IsPassword">
            <i slot="suffix" :class="iconPassword" @click="showPassword"></i>
          </el-input>
        </el-form-item>
        <!-- 性别 -->
        <el-form-item prop="sex" label="性别" size="mini">
          <el-radio-group v-model="editForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 手机号 -->
        <el-form-item prop="phone_num" label="手机号" size="mini">
          <el-input v-model="editForm.phone_num" placeholder="手机号"></el-input>
        </el-form-item>
        <!-- 电子邮箱 -->
        <el-form-item prop="email" label="电子邮箱" size="mini">
          <el-input v-model="editForm.email" placeholder="电子邮箱"></el-input>
        </el-form-item>
        <!-- 生日 -->
        <el-form-item label="生日" size="mini">
          <el-date-picker v-model="editForm.birth" type="date" placeholder="选择日期" style="width:100%">
          </el-date-picker>
        </el-form-item>
        <!-- 介绍信息 -->
        <el-form-item prop="introduction" label="简介" size="mini">
          <el-input type="textarea" v-model="editForm.introduction" placeholder="简介"></el-input>
        </el-form-item>
        <!-- 地区 -->
        <el-form-item prop="location" label="地区" size="mini">
          <el-input v-model="editForm.location" placeholder="地区"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="editConsumer">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 删除对话框 -->
    <el-dialog title="删除用户" :visible.sync="delVisible" width="30%" center>
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
    addConsumerInfo,
    getAllConsumer,
    editConsumerInfo,
    deleteConsumerInfo
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
        //改变是否显示密码
        IsPassword: "password",
        //用于更换Input中的图标
        iconPassword: "el-input__icon el-icon-view",
        //添加框
        addForm: {
          username: '',
          password: '',
          sex: '',
          phone_num: '',
          email: '',
          birth: '',
          introduction: '',
          location: ''
        },
        //编辑框
        editForm: {
          id: '',
          username: '',
          password: '',
          sex: '',
          phone_num: '',
          email: '',
          birth: '',
          introduction: '',
          location: ''
        },
        consumerData: [],
        tempData: [],
        multipleSelection: [], //哪些项已被选中
        selectedNum: [],
        select_word: '',
        pageSize: 5, //每页显示条数
        currentPage: 1, //当前页数
        currentIndex: -1, //当前选择项
        rules: {
          username: [{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '密码不能为空',
            trigger: 'blur'
          }],
          phone_num: [{
            required: true,
            message: '手机号不能为空',
            trigger: 'blur'
          }],
          email: [{
            type: 'email',
            message: '请输入正确的电子邮箱格式',
            trigger: ['blur', 'change']
          }],
          birth: [{
            required: true,
            message: '生日不能为空',
            trigger: 'blur'
          }],
          location: [{
            required: true,
            message: '地区不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    computed: {
      //计算当前搜索结果表里的数据
      data() {
        return this.consumerData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
      }
    },

    created() {
      this.getData();
    },

    watch: {
      //搜素框里面的内容发送变化时，搜索结果table列表的内容跟着变化
      select_word: function () {
        if (this.select_word == '') {
          this.consumerData = this.tempData;
        } else {
          this.consumerData = [];
          for (let i of this.tempData) {
            if (i.username.includes(this.select_word)) {
              this.consumerData.push(i);
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
      //查询所有用户
      getData() {
        this.tempData = [];
        this.consumerData = [];
        getAllConsumer().then(res => {
          this.consumerData = res;
          this.tempData = res;
          this.currentPage = 1;
        })
      },
      //密码的隐藏和显示
      showPassword() {
        //点击图标 密码隐藏或显示
        if (this.IsPassword == "text") {
          this.IsPassword = "password";
          //更新图标
          this.iconPassword = "el-input__icon el-icon-view";
        } else {
          this.IsPassword = "text";
          this.iconPassword = "el-input__icon el-icon-loading";
        };
      },
      //获取用户性别
      getConsumerSex(value) {
        if (value == 0) {
          return '女';
        }
        if (value == 1) {
          return '男';
        }
        return value;
      },
      //获取用户生日
      getConsumerBirth(day) {
        return String(day).substr(0, 10);
      },
      //添加用户
      addConsumer() {
        this.$refs['addForm'].validate(valid => {
          if (valid) {
            let birthday = this.addForm.birth;
            let datetime = birthday.getFullYear() + '-' + (birthday.getMonth() + 1) + '-' + birthday.getDate();
            let params = new URLSearchParams();
            params.append('username', this.addForm.username);
            params.append('password', this.addForm.password);
            params.append('sex', this.addForm.sex);
            params.append('phone_num', this.addForm.phone_num);
            // params.append('email', this.addForm.email);
            if (this.addForm.email != '') {
              params.append('email', this.addForm.email);
            } else {
              params.append('email', this.addForm.email = '暂无电子邮箱');
            }
            params.append('birth', datetime);
            // params.append('introduction', this.addForm.introduction);
            if (this.addForm.introduction != '') {
              params.append('introduction', this.addForm.introduction);
            } else {
              params.append('introduction', this.addForm.introduction = '这个人很懒，暂无介绍');
            }
            params.append('location', this.addForm.location);
            params.append('userpic', '/img/consumerPic/consumerPic.png');

            addConsumerInfo(params)
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
      //上传图片
      uploadConsumerPic(id) {
        return `${this.$store.state.HOST}/consumer/uploadConsumerOfAvator?id=${id}`;
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
          username: row.username,
          password: row.password,
          sex: row.sex,
          phone_num: row.phone_num,
          email: row.email,
          birth: row.birth,
          introduction: row.introduction,
          location: row.location
        }
      },

      //编辑用户信息
      editConsumer() {
        this.$refs['editForm'].validate(valid => {
          if (valid) {
            let birthday = new Date(this.editForm.birth);
            let datetime = birthday.getFullYear() + '-' + (birthday.getMonth() + 1) + '-' + birthday.getDate();
            let params = new URLSearchParams();
            params.append('id', this.editForm.id);
            params.append('username', this.editForm.username);
            params.append('password', this.editForm.password);
            params.append('sex', this.editForm.sex);
            params.append('phone_num', this.editForm.phone_num);
            params.append('email', this.editForm.email);
            params.append('birth', datetime);
            params.append('introduction', this.editForm.introduction);
            params.append('location', this.editForm.location);

            editConsumerInfo(params)
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
        deleteConsumerInfo(this.currentIndex)
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
      // 跳转到用户的收藏列表
      skipCollect(id){
        this.$router.push({
          path:'/collect',
          query:{id}
        })
      }
    },

    components: {},
  }

</script>


<style>
  @import '../css/consumer.css';

</style>
