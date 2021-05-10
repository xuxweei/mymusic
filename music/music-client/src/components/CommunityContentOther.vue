<template>
  <div class="content">
    <h4 class="title" style="border-bottom:1px solid #000;padding-bottom:5px;">
      <slot name="title"></slot>
    </h4>
    <div class="community_content">
      <ul class="community_list">
        <li class="community_list_item" v-for="(val, index) in communityList" :key="index">
          <div class="list_content">
            <div class="list_name">
              <a>{{ val.name }}</a>
            </div>
            <div class="list_txt">
              <p>{{ val.content }}</p>
            </div>
            <div class="list_source">
              <div class="list_source_img" v-if="val.type == 2">
                <img :src="attachImageUrl(val.img)" />
              </div>
              <video :src="headerurl + val.url" v-else-if="val.type == 1" style="height: 300px" controls></video>
              <div class="song_box" v-else-if="val.type == 0">
                <div class="song_wrapper">
                  <div class="song_img">
                    <img :src="attachImageUrl(val.songpic)" style="width: 100%" />
                  </div>
                  <div class="play" @click="
                          toPlay(
                            val.id,
                            val.url,
                            val.songpic,
                            index,
                            getSongName(val.url),
                            val.lyric
                          )
                        ">
                    <div v-if="toggle !== getSongName(val.url)">
                      <svg class="icon" aria-hidden="true" style="width: 2rem; height: 2rem">
                        <use xlink:href="#icon-bofang2"></use>
                      </svg>
                    </div>
                    <div v-if="toggle === getSongName(val.url)">
                      <svg class="icon" aria-hidden="true" style="width: 2rem; height: 2rem">
                        <use xlink:href="#icon-zanting2"></use>
                      </svg>
                    </div>
                  </div>
                  <p class="song_name">{{ getSongName(val.url) }}</p>
                </div>
              </div>
            </div>
            <!-- <div class="delete_btn" @click="deleteCommunities(val.id)">
              <el-button type="text">删除</el-button>
            </div> -->
          </div>
          <div class="list_up">
            <!-- 点赞 @click="deleteUp(val.id)"-->
            <div class="up" ref="up"  @click="postUp(val.id, val.up, index)">
              <svg class="icon_dianz" v-if="lsts[index] === 1" style="color:#F36161;">
                <use xlink:href="#icon-dianzan1"></use>
              </svg>
              <svg class="icon_dianz" v-else style="color:#000;">
                <use xlink:href="#icon-dianzan1"></use>
              </svg>
              <!-- <span class="uptxt"> {{ val.up }}</span> -->
            </div>

            <!-- 评论-->
            <div class="up" ref="comment" style="right: 20px">
              <svg class="icon_dianz" style="color: #000">
                <use xlink:href="#icon-pinglun"></use>
              </svg>
              <span class="uptxt" style="font-size: 12px;">评论</span>
            </div>
          </div>
          <div class="comment_list">
            <comment :playId="val.id" :type="2"></comment>
          </div>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
  import {
    mixin
  } from "../mixins/index";
  import Comment from "../components/Comment";
  import {
    communityLike,
    likeThis,
  } from '../api/index'
  import {
    mapGetters
  } from "vuex";
  export default {
    name: 'CommunityContent',
    inject: ["reload"],
    props: ["communityList"],
    mixins: [mixin],
    data() {
      return {
        headerurl: "http://localhost:8888/",
        toggle: false,
        lsts: [],
        like: 1,
       
      }
    },
     computed: {
      ...mapGetters(["activeName", "loginIn", "userId"])
    },
  

    methods: {
      getSongName(url) {
        var name = String(url).substr(url.lastIndexOf("/") + 14);
        return name.substr(0, name.lastIndexOf("."));
      },
      //切换播放音乐
      setSongUrl(url, song_name) {
        // let player = this.$refs.player;
        // this.$store.commit('setUrl', this.$store.state.config.HOST + url);
        this.toggle = song_name;
        console.log(this.toggle);
        if (this.isPlay) {
          console.log(this.isPlay);
          // this.$store.commit('setIsPlay', false);
          this.isPlay = false;
        } else {
          this.isPlay = true;
          // this.$store.commit('setIsPlay', true);
        }
      },
        postUp(id, up, index) {
        if (this.loginIn) {
          let pam = new URLSearchParams();
          pam.append("consumer_id", this.userId);
          pam.append("community_id", id);
          communityLike(pam)
            .then(r => {
              if (r.code == 1) {
                let params = new URLSearchParams();
                params.append("id", id);
                if (this.lsts[index] == 1) {
                  params.append("up", up - this.like);
                } else {
                  params.append("up", up + this.like);
                }
                likeThis(params).then(res => {
                  if (res.code == 1) {
                    console.log(this.$refs.up[index].children[0]);
                    if (this.lsts[index] == 1) {
                      console.log("哪里");
                      this.lsts[index] = 0;
                      this.notify("取消赞成功", "success");
                      // this.getData();
                      this.$refs.up[index].children[0].style.color = '#000'
                      
                      
                    } else {
                      this.lsts[index] = 1;
                      console.log("这里");
                      this.notify("点赞成功", "success");
                      // this.getData();
                      this.$refs.up[index].children[0].style.color = '#F36161'
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
      },
      // deleteCommunities(id) {
      //   deleteCommunity(id).then(res => {
      //     console.log(res);
      //     this.notify("删除成功", "success");
      //     this.reload()
      //   })
      // }
    },

    components: {
      Comment
    },
  }

</script>

<style scoped>
  @import "../assets/css/communitycontent.css";

</style>
