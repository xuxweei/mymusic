<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-box"></i>社区资源管理
    </div>
    <div class="container">
      <div class="handle_box">
        <el-button type="primary" size="mini" @click="goBack">返回社区</el-button>
      </div>
    </div>
    <!-- 歌曲列表 -->
    <el-table :data="sourceData" style="width: 100%" size="mini" height="400px" border>
      <el-table-column prop="type" label="动态类型" width="80" align="center">
        <template slot-scope="scope">
          {{getType(scope.row.type)}}
        </template>
      </el-table-column>
      <!-- 名字 -->

      <el-table-column v-if="type==0" label="歌曲详情" align="center">
        <div>
          {{song_name}}
        </div>
      </el-table-column>
      <el-table-column label="图片详情" align="center" v-if="type==2">
        <template slot-scope="scope">
          <!-- <div class="img"> -->
          <!-- <img :src="getUrl(scope.row.img)" style="width:100%" /> -->
          <!-- </div> -->
          <div class="demo-image__preview">
            <el-image style="width: 100px; height: 100px" :src="getUrl(scope.row.img)"
              :preview-src-list="[getUrl(scope.row.img)]">
            </el-image>
          </div>
        </template>
      </el-table-column>
      <el-table-column v-else-if="type==1" label="视频详情" align="center">
        <div class="demo">
          <!-- <video-player class="video-player vjs-custom-skin" ref="videoPlayer" :playsinline="true"
            :options="playerOptions">
          </video-player> -->
          <video style="height:300px;" controls :src="videoUrl"></video>
        </div>
      </el-table-column>
      <el-table-column v-else-if="type==0" width="100" label="歌曲播放" align="center">
        <template slot-scope="scope">
          <div @click="setSongUrl(scope.row.url, song_name)">
            <el-button v-if="toggle !== song_name" icon="el-icon-video-play" circle type="primary"></el-button>
            <el-button v-if="toggle === song_name" icon="el-icon-video-pause" circle type="primary"></el-button>
          </div>
        </template>
      </el-table-column>

      <el-table-column v-else label="资源详情" align="center">
        暂无资源
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {
    mixin
  } from "../mixins/index";
  import {
    getCommunityOfId
  } from "../api/index"
  import {
    mapGetters
  } from 'vuex';
  export default {
    name: 'resource',
    inject: ['reload'],
    mixins: [mixin],
    data() {
      return {
        sourceData: '',
        id: '',
        type: '',
        song_name: '',
        toggle: false,
        videoUrl: '',
        headerurl: 'http://localhost:8888/',
      }
    },
    created() {
      this.id = this.$route.query.id;
      this.getData();
    },
    computed: {
      ...mapGetters([
        'isPlay' //播放状态
      ])
    },
    destroyed() {
      this.$store.commit('setIsPlay', false)
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
      goBack() {
        window.history.go(-1)
      },
      //查询所有歌手
      getData() {
        this.sourceData = [];
        getCommunityOfId(this.id).then(res => {
          console.log(res)
          this.sourceData.push(res);
          this.type = res.type
          this.song_name = this.getSongName(res.url)
          this.videoUrl = this.headerurl + res.url

        }).catch(err => {
          console.log(err);
        })
      },
      getType(t) {
        if (t == 0) {
          return '歌曲';
        }
        if (t == 1) {
          return '视频';
        }
        if (t == 2) {
          return '图片';
        }
        if (t == 3) {
          return '文字';
        }
        return t;
      },
      getSongName(url) {
        var name = String(url).substr(url.lastIndexOf('/') + 14);
        // song_name = 
        return name.substr(0, name.lastIndexOf("."));
        // return regex.test(String(url))
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

    components: {},
  }

</script>

<style scoped>
  @import "../css/song.css";

  .img {
    width: 80px;
    height: 80px;
    border-radius: 5px;
    margin-bottom: 5px;
    overflow: hidden;
  }

  .demo {
    display: inline-block;
    height: 300px;
    text-align: center;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
    margin-right: 4px;
  }

</style>
