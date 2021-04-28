<template>
  <div class="the_header">
    <div class="header_logo" @click="goHome">
      <svg class="icon">
        <use xlink:href="#icon-ziyuan"></use>
      </svg>
      music
    </div>
    <ul class="navbar">
      <li
        v-for="item in navMsg"
        :key="item.path"
        @click="goPage(item.path, item.name)"
        :class="{ active: item.name == activeName }"
      >
        {{ item.name }}
      </li>
      <li class="noHover">
        <div class="header_search">
          <input
            type="text"
            placeholder="搜索音乐"
            @keyup.enter="goSearch()"
            v-model="keywords"
          />
          <div class="search_btn" @click="goSearch()">
            <svg
              t="1608625769713"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="4490"
              width="200"
              height="200"
            >
              <path
                d="M240.776 240.872A302.952 302.952 0 0 0 157.328 512c18.048-81.912 60.48-161.952 126.576-228.096C350.048 217.736 430.088 175.328 512 157.28c-95.28-17.952-197.52 9.864-271.224 83.592z"
                fill="#707070"
                p-id="4491"
              ></path>
              <path
                d="M959.6 802.736l-114.096-114.096a110.64 110.64 0 0 0-87-31.992c107.304-155.208 91.8-369.744-46.392-507.96-155.568-155.616-407.856-155.616-563.472 0-155.568 155.616-155.568 407.928 0 563.544 138.192 138.216 352.56 153.6 507.864 46.296-2.4 31.2 8.304 63.312 31.992 87l114.096 114.12c43.2 43.104 113.784 43.104 156.888 0 43.32-43.104 43.32-113.712 0.12-156.912zM665.84 665.84c-130.008 130.008-340.68 130.008-470.64 0-130.008-130.008-130.008-340.728 0-470.736 129.96-130.008 340.632-130.008 470.64 0 129.984 129.912 129.984 340.728 0 470.736z"
                fill="#707070"
                p-id="4492"
              ></path>
            </svg>
          </div>
        </div>
      </li>
      <li
        v-show="!loginIn"
        v-for="item in loginMsg"
        :key="item.path"
        @click="goPage(item.path, item.name)"
        :class="{ active: item.name == activeName }"
      >
        {{ item.name }}
      </li>
    </ul>
    <div class="header_right" v-show="loginIn">
      <div id="user">
        <img :src="attachImageUrl(userpic)" v-if="this.userpic != null" />
      </div>
      <ul class="menu">
        <li
          v-for="(item, index) in menuList"
          :key="index"
          @click="goMenuList(item.path)"
        >
          {{ item.name }}
        </li>
      </ul>
    </div>
    <!-- <div class="dialogAnniu">
      <el-button type="text" @click="open">点击</el-button>
    </div> -->
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { navMsg, loginMsg, menuList } from "../assets/data/theheader";
export default {
  inject: ["reload"],
  name: "TheHeader",
  data() {
    return {
      //左侧导航栏
      navMsg: [],
      // 右侧导航栏
      loginMsg: [],
      //搜索关键字
      keywords: "",
      // 用户下拉菜单
      menuList: []
    };
  },
  created() {
    this.navMsg = navMsg;
    this.loginMsg = loginMsg;
    this.menuList = menuList;
  },
  computed: {
    ...mapGetters(["activeName", "loginIn", "userpic"])
  },
  mounted() {
    // 点击头像 显示菜单
    document.querySelector("#user").addEventListener(
      "click",
      function(e) {
        document.querySelector(".menu").classList.add("show");
        // 阻止冒泡继续进行
        e.stopPropagation();
      },
      false
    );
    document.querySelector(".menu").addEventListener(
      "click",
      function(e) {
        // 点击菜单内部时，阻止事件冒泡 这样点击内部时，菜单不会消失
        e.stopPropagation();
      },
      false
    );
    // 点击外部时，菜单关闭
    document.addEventListener(
      "click",
      function(e) {
        document.querySelector(".menu").classList.remove("show");
      },
      false
    );
  },
  methods: {
    // open() {
    //   this.$alert('<strong>这是 弹窗</strong>', '弹窗', {
    //     dangerouslyUseHTMLString: true
    //   });
    // },
    //提示信息
    notify(title, type) {
      this.$notify({
        title: title,
        type: type
      });
    },
    changeIndex(value) {
      this.$store.commit("setActiveName", value);
    },
    goHome() {
      this.changeIndex("首页");
      this.$router.push({
        path: "/"
      });
    },
    goPage(path, name) {
      if (!this.loginIn && path == "/myMusic") {
        this.notify("请先登陆哦", "warning");
      } else {
        this.$store.commit("setActiveName", name);
        this.$router.push({
          path: path
        });
      }
    },
    goSearch() {
      this.$router.push({
        path: "/search",
        query: {
          keywords: this.keywords
        }
      });
      this.reload();
    },
    //获取图片地址
    attachImageUrl(srcUrl) {
      //判断图片地址是否有值 有的话将其放入store的config里
      return srcUrl
        ? this.$store.state.config.HOST + srcUrl
        : "../assets/img/user.png";
    },
    goMenuList(path) {
      if (path == 0) {
        this.$store.commit("setLoginIn", false);
        this.$store.commit("setIsCollect", false);
        this.$store.commit("setCollectBtn", "#icon-xihuan");
        //刷新页面
        this.$router.go(0);
        if (this.$route.path == "/myMusic") {
          this.goHome();
        }
        this.reload();
      } else {
        this.$router.push({
          path: path
        });
        this.reload();
        this.$router.go(0);
      }
    }
  },

  components: {}
};
</script>

<style>
@import "../assets/css/theheader.css";
</style>
