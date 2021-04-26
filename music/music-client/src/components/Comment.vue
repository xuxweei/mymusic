<template>
  <div class="comment">
    <h2>评论</h2>
    <div class="comment_msg">
      <div class="comment_img">
        <img v-if="this.loginIn" :src="attachImageUrl(userpic)" alt="" />
      </div>
      <el-input
        class="comment_input"
        placeholder="请输入内容"
        v-model="textarea"
        type="textarea"
        :rows="3"
      >
      </el-input>
      <el-button type="primary" class="sub_btn" @click="postComment"
        >评论</el-button
      >
    </div>
    <div class="popular_title">
      <p style="font-size: 16px;">精彩评论 ({{ commentList.length }})</p>
    </div>
    <ul class="popular" v-for="(item, index) in commentList" :key="index">
      <li>
        <div class="popular_img">
          <img :src="attachImageUrl(avator[index])" alt="" />
        </div>
        <div class="popular_msg">
          <ul>
            <li class="name">{{ userName[index] }}</li>
            <li class="content">{{ item.content }}</li>
            <li class="time">{{ item.create_time }}</li>
          </ul>
        </div>
        <div class="up" ref="up" @click="postUp(item.id, item.up, index)">
          <svg class="icon" v-if="lsts[index] === 1" style="color:#F36161;">
            <use xlink:href="#icon-dianzan1"></use>
          </svg>
          <svg class="icon" v-else style="color:#000;">
            <use xlink:href="#icon-dianzan1"></use>
          </svg>
          {{ item.up }}
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { mixin } from "../mixins/index";
import {
  addComment,
  setLike,
  getAllComment,
  getConsumerById,
  setUp
} from "../api/index";
export default {
  name: "comment",
  inject: ["reload"],
  props: [
    "playId", //歌曲或歌单的id
    "type" //歌曲0 歌单1
    // 'likeStatus'
  ],
  mixins: [mixin],
  data() {
    return {
      // 输入的评论内容
      textarea: "",
      // 评论列表
      commentList: [],
      // 用户头像
      avator: [],
      // 用户昵称
      userName: [],
      likeStatus: false,
      like: 1,
      uid: "",
      lsts: []
    };
  },
  computed: {
    ...mapGetters([
      "id", //当前歌单或歌曲id
      "userId", //当前登录的用户id
      "loginIn", //当前用户是否登录
      "userpic" //当前登录用户的头像
    ])
  },
  mounted() {
    this.getComment();
  },
  methods: {
    // 提交评论
    postComment() {
      if (this.loginIn) {
        let params = new URLSearchParams();
        if (this.type == 0) {
          params.append("song_id", this.playId);
        } else {
          params.append("songlist_id", this.playId);
        }
        params.append("user_id", this.userId);
        params.append("type", this.type);
        params.append("content", this.textarea);
        addComment(params)
          .then(res => {
            if (res.code == 1) {
              this.notify("评论成功", "success");
              this.textarea = "";
              this.getComment(this.songListId);
              this.reload();
            } else {
              this.notify("评论失败", "error");
            }
          })
          .catch(err => {
            this.notify("评论失败", "error");
            console.log(err);
          });
      } else {
        this.notify("请先登录哦~", "warning");
      }
    },
    // 评论列表
    getComment() {
      this.uid = this.userId;
      console.log(this.uid);
      getAllComment(this.type, this.playId, this.userId)
        .then(res => {
          this.commentList = res;
          console.log(res);
          for (let item of res) {
            this.getUsers(item.user_id);
            this.lsts.push(item.like_status);
          }
          console.log(this.lsts);
        })
        .catch(err => {
          this.notify("要登录才能才看评论哦~", "error");
          console.log(err);
        });
      // console.log(this.userId);
    },
    // 获取用户的头像、昵称
    getUsers(id) {
      getConsumerById(id)
        .then(res => {
          this.avator.push(res.userpic);
          this.userName.push(res.username);
        })
        .catch(err => {
          this.notify("出错了", "error");
          console.log(err);
        });
    },
    // 给评论点赞
    postUp(id, up, index) {
      if (this.loginIn) {
        let pam = new URLSearchParams();
        pam.append("consumer_id", this.userId);
        pam.append("comment_id", id);
        setUp(pam)
          .then(r => {
            if (r.code == 1) {
              let params = new URLSearchParams();
              params.append("id", id);
              params.append("up", up + this.like);
              setLike(params).then(res => {
                if (res.code == 1) {
                  console.log(this.$refs.up[index].children[0]);
                  if (this.likeStatus) {
                    console.log("哪里");
                    this.notify("取消赞成功", "success");
                    this.$refs.up[index].children[0].style.color = "#000";
                    this.getComment(this.songListId);
                    this.likeStatus = false;
                    this.like = 1;
                  } else {
                    console.log("这里");
                    this.notify("点赞成功", "success");
                    this.$refs.up[index].children[0].style.color = "#F36161";
                    this.getComment(this.songListId);
                    this.likeStatus = true;
                    this.like = -1;
                  }
                }
              });
            } else {
              this.notify("点赞失败", "error");
            }
          })
          .catch(err => {
            this.notify("点赞失败", "error");
            console.log(err);
          });
      } else {
        this.notify("请先登录哦~", "warning");
      }
    }
  },

  components: {}
};
</script>

<style scoped>
@import "../assets/css/comment.css";
</style>
