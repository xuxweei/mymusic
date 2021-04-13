<template>
  <div class="back">
    <div class="title">music 后台管理系统</div>
    <div class="login">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm"
        style="margin-top:20px;" hide-required-asterisk="false">
        <el-form-item label="账号" prop="name" class="form_input" >
          <el-input v-model="ruleForm.name" placeholder="请输入用户名" class="form_input"
            style="width: 85%;" ref="name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" class="form_input">
          <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码" class="form_input"
            style="width: 85%;"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()">登录</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getLoginStatus} from "../api/index";
import {mixin} from "../mixins/index"
  export default {
    mixins:[mixin],
    data() {
      return {
        ruleForm: {
          name: "",
          password: "",
        },
        rules: {
          name: [{
            required:true,
            message:"请输入用户名",
            trigger: "blur"
          }],
          password: [{
            required:true,
            message:"请输入用户名",
            trigger: "blur"
          }],
        },
      };
    },

    methods: {
      submitForm() {
        let params = new URLSearchParams();
        params.append("name",this.ruleForm.name);
        params.append("password",this.ruleForm.password);
        getLoginStatus(params)
        .then((res)=>{
          if(res.code == 1){
            sessionStorage.setItem('userName',this.$refs.name.value);
            this.$router.push("/index");
            this.notify("登录成功","success");
          }else{
            this.notify("登录失败","error");
          }
        });
        console.log(this.$refs.name.value)
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }

    //   components: {},
  };

</script>

<style>
  @import "../css/login.css";

</style>
