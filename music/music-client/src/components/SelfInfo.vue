<template>
  <div class="info">
    <div class="title">
      <span>编辑个人资料</span>
    </div>
    <hr />
    <div class="personal">
      <el-form :model="InfoForm" :rules="rules" ref="InfoForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="InfoForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="InfoForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="InfoForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phone_num">
          <el-input v-model="InfoForm.phone_num" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="InfoForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="生日" prop="birth">
          <el-date-picker v-model="InfoForm.birth" type="date" placeholder="选择日期" style="width:100%;">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="个人介绍" prop="introduction">
          <el-input v-model="InfoForm.introduction" placeholder="个人介绍"></el-input>
        </el-form-item>
        <el-form-item label="地区" prop="location">
          <el-input style="width:100%;" v-model="InfoForm.location">
          </el-input>
        </el-form-item>
      </el-form>
      <div class="btn">
        <el-button type="primary" @click="saveMsg">保存</el-button>
        <el-button @click="goback(-1)">取消</el-button>
      </div>
    </div>

  </div>
</template>

<script>
  import {
    mapGetters
  } from 'vuex'
  import {
    mixin
  } from '../mixins'
  import {
    rules
  } from '../assets/data/form'
  import {
    getConsumerById,
    updateUserMsg
  } from '../api/index'
  export default {
    name: 'SelfInfo',
    mixins: [mixin],
    data() {
      return {
        InfoForm: {
          //    用户名
          username: '',
          //   密码
          password: '',
          //   性别
          sex: '',
          //   手机号
          phone_num: '',
          //   邮箱
          email: '',
          //   生日
          birth: '',
          // 简介
          introduction: '',
          //地区
          location: ''
        },
        // 提交规则
        rules: {}
      }
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    },
    created() {
      this.rules = rules;
    },
    mounted() {
      this.getUserMsg(this.userId)
    },
    methods: {
      goback(index) {
        this.$router.go(index)
      },
      getUserMsg(userId) {
        getConsumerById(userId)
          .then(res => {
            console.log(res);
            this.InfoForm.username = res.username
            this.InfoForm.password = res.password
            this.InfoForm.phone_num = res.phone_num
            this.InfoForm.sex = res.sex
            this.InfoForm.email = res.email
            this.InfoForm.birth = this.attachBirth(res.birth)
            this.InfoForm.location = res.location
            this.InfoForm.introduction = res.introduction
          })
          .catch(err => {
            console.log(err);
          })
      },
      saveMsg() {
        let _this = this;
        let d = new Date(this.InfoForm.birth)
        let datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate()
        let params = new URLSearchParams()
        params.append('id', this.userId)
        params.append('username', this.InfoForm.username)
        params.append('password', this.InfoForm.password)
        params.append('sex', this.InfoForm.sex)
        params.append('phone_num', this.InfoForm.phone_num)
        params.append('email', this.InfoForm.email)
        params.append('birth', datetime)
        params.append('introduction', this.InfoForm.introduction)
        params.append('location', this.InfoForm.location)
        updateUserMsg(params)
          .then(res => {
            if (res.code == 1) {
              console.log('点击了确定')
              _this.$store.commit('setUsername', this.InfoForm.username)
              _this.notify('保存成功', 'success')
            } else {
              _this.notify('保存失败', 'error')
            }
          })
          .catch(err => {
            console.log(err)
          })
      },
      changeIndex(value) {
        this.$store.commit('setActiveName', value)
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import '../assets/css/selfinfo.css';

</style>
