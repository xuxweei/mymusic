<template>
  <div class="community">
    <div class="community_main">
      <!-- 左半区域 -->
      <div class="community_left">
        <div class="community_header">
          <el-button icon="el-icon-edit" type="primary" size="mini" @click="dialogVisible = true">发表动态</el-button>
        </div>
        <div class="community_content">
          <ul class="community_list">
            <li class="community_list_item" v-for="(val, index) in data" :key="index">
              <div class="list_face">
                <a>
                  <img :src="attachImageUrl(val.userpic)" />
                </a>
              </div>
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
                      <!-- @click="setSongUrl(val.url, getSongName(val.url))" -->
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
                        <!--  -->
                        <div v-if="toggle === getSongName(val.url)">
                          <svg class="icon" aria-hidden="true" style="width: 2rem; height: 2rem">
                            <use xlink:href="#icon-zanting2"></use>
                          </svg>
                        </div>
                      </div>
                      <p class="song_name">{{ getSongName(val.url) }}</p>
                      <!-- <el-button v-if="toggle !== getSongName(val.url)" icon="el-icon-video-play" circle type="primary">
                      </el-button>
                      <el-button v-if="toggle === getSongName(val.url)" icon="el-icon-video-pause" circle
                        type="primary"></el-button> -->
                    </div>
                  </div>
                </div>
              </div>
              <div class="list_up">
                <!-- 点赞 @click="deleteUp(val.id)"-->
                <div class="up" ref="up" @click="postUp(val.id, val.up, index)">
                  <svg class="icon_dianz" v-if="lsts[index] === 1" style="color:#F36161;">
                    <use xlink:href="#icon-dianzan1"></use>
                  </svg>
                  <svg class="icon_dianz" v-else style="color:#000;">
                    <use xlink:href="#icon-dianzan1"></use>
                  </svg>
                  <span class="uptxt"> {{ val.up }}</span>
                </div>

                <!-- 评论 @click="commentVisible = true"-->
                <div class="up" ref="comment" style="right: 20px" >
                  <svg class="icon_dianz" style="color: #000">
                    <use xlink:href="#icon-pinglun"></use>
                  </svg>
                  <span class="uptxt"> {{ val.up }}</span>
                </div>
              </div>
              <!--v-if="commentVisible"  -->
        <div class="comment_list" >
          <comment :playId="val.id" :type="2"></comment>
        </div>
            </li>
          </ul>
        </div>
        
      </div>

      <!-- 右半区域 -->
      <div class="community_right_aside">
        <div class="user_info_box" v-if="loginIn">
          <li class="face">
            <img :src="attachImageUrl(userpic)" v-if="this.userpic != null" />
          </li>
          <h5 class="user_name">
            <a :title="username">{{ username }}</a>
          </h5>
          <p :title="introduction" class="intro">{{ introduction }}</p>
        </div>
        <div class="user_info_box" v-else @click="goLoginIn">
          <a>登录</a>
        </div>
        <div class="user_state"></div>
      </div>
    </div>

    <!-- 分页 -->
    <div>
      <div class="pagination">
        <el-pagination background layout="total,prev, pager, next" :total="communityDatas.length"
          @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize">
        </el-pagination>
      </div>
    </div>
    <el-dialog title="添加动态" :visible.sync="dialogVisible" width="30%" center :before-close="handleClose">
      <!-- 歌单名 -->
      <el-form :model="addForm" ref="addForm" class="demo-ruleForm" id="af">
        <el-form-item prop="content">
          <div class="community_txt">
            <el-input type="textarea" resize="none" placeholder="一起聊聊吧~" v-model="addForm.content" maxlength="150"
              show-word-limit :rows="4">
            </el-input>
          </div>
        </el-form-item>
        <el-form-item prop="type">
          <el-select v-model="addForm.type" placeholder="请选择类型" @change.native="getTypes" @blur.native="getTypes">
            <el-option label="歌曲" value="0"></el-option>
            <el-option label="视频" value="1"></el-option>
            <el-option label="图片" value="2"></el-option>
            <el-option label="文字" value="3"></el-option>
          </el-select>
        </el-form-item>
        <div>
          <el-form-item>
            <div class="icon_controller" @click="itemVisible = true">
              <span style="margin: -10px 15px">
                <i style="fontsize: 1.4rem; color: #888; cursor: pointer" class="el-icon-picture-outline"></i>
              </span>
              <span style="margin: -10px">
                <i style="fontsize: 1.4rem; color: #888; cursor: pointer" class="el-icon-video-camera"></i>
              </span>
              <span style="margin: -10px 15px">
                <svg class="icon" style="
                    fontsize: 1.4rem;
                    color: #888;
                    cursor: pointer;
                    width: 1.4rem;
                    height: 1.4rem;
                  ">
                  <use xlink:href="#icon-yinle"></use>
                </svg>
              </span>
            </div>
            <el-form-item v-if="itemVisible">
              <!-- 图片 -->
              <!-- <div v-if="this.addForm.type==2"> -->
              <!-- <el-upload class="avatar-uploader" action="http://localhost:8888/community/addWithUrl"
              :show-file-list="false" :on-change="showLogo" :auto-upload="false" multiple>
              <img v-if="this.addForm.img" :src="this.addForm.img" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload> -->
              <!-- <label style="margin-left: 15px; margin-right: 5px;">图片上传</label>
                <input type="file" name="file">
              </div>
              <div v-else-if="this.addForm.type==0">
                <label style="margin-left: 15px; margin-right: 5px;">歌曲上传</label>
                <input type="file" name="file">
              </div>
              <div v-else-if="this.addForm.type==1">
                <label style="margin-left: 15px; margin-right: 5px;">视频上传</label>
                <input type="file" name="file">
              </div>  :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload":on-success="handleSongSuccess"-->
              <el-upload v-if="this.addForm.type == 2" class="upload-demo" :show-file-list="false" action=""
                :auto-upload="false" :on-change="showLogo">
                <el-button size="mini" type="primary">上传图片</el-button>
              </el-upload>
              <el-upload v-else-if="this.addForm.type == 0" class="upload-demo" :show-file-list="false" action=""
                :auto-upload="false" multiple>
                <el-button size="mini" type="primary">上传歌曲</el-button>
              </el-upload>
              <el-upload v-else-if="this.addForm.type == 1" class="upload-demo" :show-file-list="false" action=""
                :auto-upload="false" multiple>
                <el-button size="mini" type="primary">上传视频</el-button>
              </el-upload>
            </el-form-item>
          </el-form-item>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCommunity">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {
    getAllCommunity,
    addCommunity,
    getConsumerById,
    addCommunityWithUrl,
    communityLike,
    likeThis,
    getLiked
  } from "../api/index";
  import {
    mapGetters
  } from "vuex";
  import {
    mixin
  } from "../mixins";
  import Comment from '../components/Comment'
  export default {
    inject: ["reload"],
    mixins: [mixin],
    name: "community",
    data() {
      return {
        //社区数据
        communityDatas: [],
        // 一页有15条数据
        pageSize: 15,
        // 当前页数
        currentPage: 1,
        textarea: "",
        // 用户头像
        userpic: "",
        // 用户名
        username: "",
        // 个人介绍
        introduction: "",
        headerurl: "http://localhost:8888/",
        toggle: false,
        isPlay: false,
        imageUrl: "",
        dialogVisible: false,
        dialogImageUrl: "",
        disabled: false,
        //添加框
        addForm: {
          name: "",
          type: "",
          content: "",
          time: ""
        },
        itemVisible: false,
        nextId: "",
        likeStatus: false,
        like: 1,
        lsts: [],
        communityId:'',
        commentVisible:false
      };
    },

    computed: {
      //计算当前搜索结果表里的数据
      data() {
        return this.communityDatas.slice(
          (this.currentPage - 1) * this.pageSize,
          this.currentPage * this.pageSize
        );
      },
      ...mapGetters(["activeName", "loginIn", "userId"])
    },
    mounted() {
      this.getUserMsg(this.userId);
      // this.getUserUp(this.userId);
    },

    created() {
      this.getData();
    },
    destroyed() {
      this.$store.commit("setIsPlay", false);
    },

    methods: {
      //关闭对话框
      handleClose(done) {
        this.$confirm("确认关闭？")
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      // 获取当前页
      handleCurrentChange(val) {
        this.currentPage = val;
      },
      // 获取全部community数据
      getData() {
        this.communityDatas = [];
        getAllCommunity().then(res => {
          console.log(res);
          this.communityDatas = res;
          this.currentPage = 1;
          this.nextId = res[res.length - 1].id;
          for (let item of res) {
            this.lsts.push(item.like_status);
          }
          console.log(this.lsts);
        });
        // this.reload();
      },
      //添加动态
      addCommunity() {
        if (this.loginIn) {
          this.$refs["addForm"].validate(valid => {
            if (valid) {
              let params = new FormData();
              params.set("name", this.username);
              params.set("type", this.addForm.type);
              // if (this.addForm.url != "") {
              //   params.append("url", this.addForm.url);
              // } else if (
              //   (this.addForm.type == 0 || this.addForm.type == 1) &&
              //   this.addForm.url == ""
              // ) {
              //   params.append("url", (this.addForm.url = ""));
              // }
              if (this.addForm.content != "") {
                params.set("content", this.addForm.content);
              } else {
                params.set("content", (this.addForm.content = ""));
              }
              if (this.addForm.img != "") {
                console.log(this.addForm.img);
                params.set("file", this.addForm.img);
              } else {
                params.set("file", (this.addForm.img = ""));
              }
              addCommunityWithUrl(params)
                .then(res => {
                  if (res.code == 1) {
                    this.getData();
                    this.notify("提交成功", "success");
                    this.reload();
                  } else {
                    this.notify("提交失败", "error");
                  }
                })
                .catch(err => {
                  console.log(err);
                });
            }
          });
        } else {
          this.notify("请先登录哦~", "warning");
        }
      },
      // 获取用户信息
      getUserMsg(userId) {
        getConsumerById(userId)
          .then(res => {
            // console.log(res);
            this.userpic = res.userpic;
            this.username = res.username;
            this.introduction = res.introduction;
          })
          .catch(err => {
            console.log(err);
          });
      },
      // 登录
      goLoginIn() {
        this.$router.push({
          path: "/loginIn"
        });
      },
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
      getTypes(e) {
        this.addForm.type = e.target.value;
      },
      uploadImg(id) {
        // this.dialogVisible = false
        // this.reload()
        return `${this.$store.state.config.HOST}/community/uploadImg?id=${id}`;
      },
      //跟新歌曲url
      uploadUrl(id) {
        return `${this.$store.state.config.HOST}/community/uploadUrl?id=${id}`;
      },

      validateFileSize(file, num, fileType) {
        let fileName = file.name;
        let index = fileName.lastIndexOf(".");
        let docuType = fileName.substr(index, fileName.length).toLowerCase();
        let fileTypeArr = [];
        let isRight = false;
        const isLtM = file.size / 1024 / 1024 < num;
        switch (fileType) {
          case 0:
            fileTypeArr = [".mp3"];
            isRight = fileTypeArr.includes(docuType);
            if (!isRight) {
              this.notify("上传视频只能是MP3格式", "error");
            }
            break;
          case 1:
            fileTypeArr = [".mp4"];
            isRight = fileTypeArr.includes(docuType);
            if (!isRight) {
              this.notify("上传视频只能是MP4格式", "error");
            }
            break;

          case 2:
            fileTypeArr = [".jpeg", ".jpg", ".png"];
            isRight = fileTypeArr.includes(docuType);
            if (!isRight) {
              this.notify("上传头像图片只能是jpg或png或jpeg格式", "error");
            }
            break;
        }
        if (!isLtM) {
          if (num < 10) {
            this.notify("上传头像图片大小不能超过10MB!", "error");
          } else {
            this.notify("上传文件大小不能超过" + num + "MB!", "error");
          }
        }
        return isLtM && isRight;
      },
      showLogo(file, fileList) {
        if (this.validateFileSize(file, 9, 2)) {
          console.log(file);
          this.addForm.img = file.raw;
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
                      this.lsts[index] = 0
                      this.notify("取消赞成功", "success");
                      this.getData();
                    } else {
                      this.lsts[index] = 1
                      console.log("这里");
                      this.notify("点赞成功", "success");
                      this.getData();
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

    components: {Comment}
  };

</script>

<style scoped>
  @import "../assets/css/community.css";

</style>
