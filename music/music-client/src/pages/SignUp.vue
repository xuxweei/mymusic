<template>
  <div class="login_container">
    <!-- <login-logo></login-logo> -->
    <div class="signUp">
      <div class="signUp_head">
        <span>用户注册</span>
      </div>
      <el-form
        :model="registerForm"
        :rules="rules"
        ref="registerForm"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名" prop="username" class="white-font">
          <el-input
            v-model="registerForm.username"
            placeholder="用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" class="white-font">
          <el-input
            type="password"
            v-model="registerForm.password"
            placeholder="密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex" class="white-font">
          <el-radio-group v-model="registerForm.sex" class="white-font">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phone_num" class="white-font">
          <el-input
            v-model="registerForm.phone_num"
            placeholder="手机号"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email" class="white-font">
          <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="生日" prop="birth" class="white-font">
          <el-date-picker
            v-model="registerForm.birth"
            type="date"
            placeholder="选择日期"
            style="width:100%;"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="个人介绍" prop="introduction" class="white-font">
          <el-input
            v-model="registerForm.introduction"
            placeholder="个人介绍"
          ></el-input>
        </el-form-item>
        <el-form-item label="地区" prop="location" class="white-font">
          <v-distpicker
            style="width:100%;"
            @selected="onSelected"
            v-model="registerForm.location"
            only-province
          ></v-distpicker>
        </el-form-item>
        <div class="login_btn">
          <el-button @click="goback(-1)">取消</el-button>
          <el-button type="primary" @click="SignUp">确定</el-button>
        </div>
      </el-form>
    </div>
    <vue-particles
      color="#fff"
      :particleOpacity="0.7"
      :particlesNumber="60"
      shapeType="circle"
      :particleSize="4"
      linesColor="#fff"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="2"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="push"
      class="lizi"
      style="height:100%"
    >
    </vue-particles>
  </div>
</template>

<script>
import VDistpicker from "v-distpicker";
import { mixin } from "../mixins";
import { SignUp } from "../api/index";
import { rules } from "../assets/data/form";
export default {
  name: "SignUp",
  mixins: [mixin],
  data() {
    return {
      registerForm: {
        //    用户名
        username: "",
        //   密码
        password: "",
        //   性别
        sex: "",
        //   手机号
        phone_num: "",
        //   邮箱
        email: "",
        //   生日
        birth: "",
        // 简介
        introduction: "",
        //地区
        location: ""
      },
      // 提交规则
      rules: {}
    };
  },
  created() {
    this.rules = rules;
  },

  methods: {
    goback(index) {
      this.$router.go(index);
    },
    onSelected(data) {
      console.log(data.province.value);
      this.registerForm.location = data.province.value;
    },
    SignUp() {
      let _this = this;
      let d = this.registerForm.birth;
      let datetime =
        d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
      let params = new URLSearchParams();
      params.append("username", this.registerForm.username);
      params.append("password", this.registerForm.password);
      params.append("sex", this.registerForm.sex);
      params.append("phone_num", this.registerForm.phone_num);
      params.append("email", this.registerForm.email);
      params.append("birth", datetime);
      params.append("introduction", this.registerForm.introduction);
      params.append("location", this.registerForm.location);
      params.append("userpic", "/img/consumerPic/consumerPic.png");
      SignUp(params)
        .then(res => {
          if (res.code == 1) {
            console.log("点击了确定");
            _this.notify("注册成功", "success");
            setTimeout(function() {
              _this.changeIndex("首页");
              _this.$router.push({
                path: "/"
              });
            }, 2000);
          } else {
            _this.notify("注册失败", "error");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    changeIndex(value) {
      this.$store.commit("setActiveName", value);
    }
  },

  components: {
    VDistpicker
  }
};
</script>

<style scoped>
@import "../assets/css/signup.css";
</style>
