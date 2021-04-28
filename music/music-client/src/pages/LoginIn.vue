<template>
  <div class="login_container">
    <!-- <login-logo></login-logo> -->
    <div class="login">
      <div class="login_head">
        <span>用户登录</span>
      </div>
      <el-form
        :model="loginForm"
        :rules="rules"
        ref="loginForm"
        class="demo-ruleForm"
      >
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="用户名"
            prefix-icon="iconfont icon-user"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="loginForm.password"
            placeholder="密码"
            prefix-icon="iconfont icon-3702mima"
          ></el-input>
        </el-form-item>
        <el-form-item prop="validate" class="validate_form">
          <el-input
            v-model="loginForm.validate"
            class="validate_code"
            placeholder="验证码"
          ></el-input>
          <div class="code" @click="refreshCode">
            <identify :identifyCode="identifyCode"></identify>
          </div>
        </el-form-item>
        <div v-if="errorInfo" class="errInfo">
          <span>{{ errInfo }}</span>
        </div>
        <div class="login_btn">
          <el-button @click="goSignUp()">注册</el-button>
          <el-button type="primary" @click="handleLoginIn">登录</el-button>
        </div>
      </el-form>
    </div>
    <!-- <div class="login_container"> -->
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
    <!-- </div> -->
  </div>
</template>

<script>
import { mixin } from "../mixins";
import { LoginIn } from "../api/index";
import Identify from "../components/Identify.vue";
export default {
  name: "LoginIn",
  mixins: [mixin],
  data() {
    return {
      identifyCodes: "1234567890abcdefghijklmnopqrstuvwxyz",
      identifyCode: "",
      errorInfo: false,
      loginForm: {
        //    用户名
        username: "",
        //   密码
        password: "",
        // 验证码
        validate: ""
      },
      rules: {
        username: [
          {
            required: true,
            triggle: "blur",
            message: "请输入用户名"
          }
        ],
        password: [
          {
            required: true,
            trigger: "blur",
            message: "请输入密码"
          }
        ],
        validate: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  mounted() {
    this.identifyCode = "";
    this.makeCode(this.identifyCodes, 4);
    this.changeIndex("登录");
  },
  methods: {
    goSignUp() {
      this.changeIndex("注册");
      this.$router.push({
        path: "/signUp"
      });
    },
    handleLoginIn() {
      let _this = this;
      let params = new URLSearchParams();
      params.append("username", this.loginForm.username);
      params.append("password", this.loginForm.password);
      if (_this.loginForm.validate != this.identifyCode) {
        _this.errorInfo = true;
        _this.errInfo = "验证码错误";
        this.refreshCode();
      } else {
        LoginIn(params)
          .then(res => {
            if (res.code == 1) {
              console.log("点击了确定");
              _this.notify("登录成功", "success");
              _this.$store.commit("setLoginIn", true);
              _this.$store.commit("setUserId", res.userMsg.id);
              _this.$store.commit("setUsername", res.userMsg.username);
              _this.$store.commit("setAvator", res.userMsg.userpic);

              setTimeout(function() {
                _this.changeIndex("首页");
                _this.$router.push({
                  path: "/"
                });
              }, 2000);
            } else {
              _this.errorInfo = true;
              console.log(res);
              _this.errInfo = res.msg;
              // 重新生成验证码
              this.refreshCode();
              _this.notify("用户名或密码错误", "error");
            }
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
        ];
      }
      console.log(this.identifyCode);
    },
    changeIndex(value) {
      this.$store.commit("setActiveName", value);
    }
  },

  components: {
    Identify
  }
};
</script>

<style scoped>
@import "../assets/css/loginin.css";
</style>
