<template>
  <div class="play_bar" :class="{show:!toggle}">
    <div @click="toggle=!toggle" class="item_up" :class="{turn:toggle}">
      <svg class="icon icon_small">
        <use xlink:href="#icon-xiangxia"></use>
      </svg>
    </div>
    <div class="kongjian">
      <!-- 上一首 -->
      <div class="item" @click="prev">
        <svg class="icon">
          <use xlink:href="#icon-shangyishou"></use>
        </svg>
      </div>
      <!-- 播放 -->
      <div class="item" @click="togglePlay">
        <svg class="icon" aria-hidden="true">
          <use :xlink:href="playBtn"></use>
        </svg>
      </div>
      <!-- 下一首 -->
      <div class="item" @click="next">
        <svg class="icon">
          <use xlink:href="#icon-xiayishou"></use>
        </svg>
      </div>
      <!-- 歌曲图片 -->
      <div class="item item_img" @click="toLyric">
        <img :src="picUrl" />
      </div>
      <!-- 播放进度 -->
      <div class="playing_speed">
        <!-- 播放开始时间 -->
        <div class="current_time">
          {{nowTime}}
        </div>
        <div class="progress_box">
          <div class="item_song_title">
            <div>{{this.title}}</div>
            <div>{{this.artist}}</div>
          </div>
          <div class="progress" ref="progress" @mousemove="mousemove">
            <!-- 进度条 -->
            <div class="bg" ref="bg" @click="updatemove">
              <div class="cur_progress" ref="cur_progress" :style="{width:currentLength+'%'}"></div>
            </div>
            <!-- 拖动块 -->
            <div class="idot" ref="idot" :style="{left:currentLength+'%'}" @mousedown="mousedown" @mouseup="mouseup">
            </div>
          </div>
        </div>
        <!-- 播放结束时间 -->
        <div class="left_time">
          {{endTime}}
        </div>
        <!-- 音量 -->
        <div class="item item_volume">
          <svg v-if="volume != 0" class="icon icon_small">
            <use xlink:href="#icon-yinliang"></use>
          </svg>
          <svg v-else class="icon">
            <use xlink:href="#icon-jingyin"></use>
          </svg>
          <el-slider v-model="volume" vertical class="volume">
          </el-slider>
        </div>
        <!-- 收藏 -->
        <div class="item" @click="collection">
          <svg class="icon">
            <use :xlink:href="collectBtn"></use>
          </svg>
        </div>
        <!-- 下载 -->
        <div class="item" @click="download">
          <svg class="icon icon_small">
            <use xlink:href="#icon-xiazai"></use>
          </svg>
        </div>
        <!-- 列表 -->
        <div class="item" @click="changeAside">
          <svg class="icon icon_small">
            <use xlink:href="#icon-liebiao3"></use>
          </svg>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    mapGetters
  } from 'vuex'
  import {
    download,
    setCollect,
    getCollectOfUserId,
    cancelCollection
  } from '../api/index'
  export default {
    name: 'PlayBar',
    data() {
      return {
        //当前播放进度的时间
        nowTime: '00:00',
        //歌曲总时间
        endTime: '00:00',
        //当前进度条位置
        currentLength: 0,
        //进度条总长度
        progressLength: 0,
        //拖拽开始位置
        mouseStartX: 0,
        //拖拽开始结束的标志，当开始拖拽时，它的值才会变成true
        tag: false,
        //音量 默认10
        volume: 100,
        //显示隐藏播放器页面
        toggle: true
      }
    },
    computed: {
      ...mapGetters([
        'id', // 音乐id
        'url', // 音乐链接
        'isPlay', // 播放状态  
        'playBtn', //播放状态的图标
        'picUrl', //正在播放的音乐图片    
        'title', //歌名
        'artist', //歌手名      
        'duration', //音乐时长 
        'currentTime', //当前音乐播放位置
        'listIndex', //当前歌曲在播放列表中的位置
        'listOfSongs', //当前播放列表
        'autoNext', //自动播放下一首
        'loginIn', //用户是否已登录
        'userId', //当前登录用户的id
        'isCollect', //当前播放的歌曲是否已收藏
        'collectBtn', //播放状态的图标
      ])
    },
    watch: {
      //切换播放状态的图标
      isPlay(val) {
        if (val) {
          this.$store.commit('setPlayBtn', '#icon-zanting1');
        } else {
          this.$store.commit('setPlayBtn', '#icon-bofang1');
        }
      },

      // 当前时间
      currentTime() {
        this.nowTime = this.formatTime(this.currentTime);
        this.endTime = this.formatTime(this.duration);
        this.currentLength = (this.currentTime / this.duration) * 100;
      },
      //音量变化
      volume() {
        this.$store.commit('setVolume', this.volume / 100);
      },
      //自动播放
      autoNext() {
        this.next();
      }
    },
    mounted() {
      this.progressLength = this.$refs.progress.getBoundingClientRect().width;
      //给音量图标添加点击事件 被点击时加上 show_volume 让音量控制显示
      document.querySelector('.item_volume').addEventListener('click', function (e) {
        document.querySelector('.volume').classList.add('show_volume');
        e.stopPropagation();
      }, false);
      document.querySelector('.volume').addEventListener('click', function (e) {
        e.stopPropagation();
      }, false);
      document.addEventListener('click', function () {
        document.querySelector('.volume').classList.remove('show_volume');
      }, false);
    },

    methods: {
      //控制音乐播放、暂停
      togglePlay() {
        if (this.isPlay) {
          this.$store.commit('setIsPlay', false);
        } else {
          this.$store.commit('setIsPlay', true);
        }
      },
      //解析时间
      formatTime(val) {
        //转成整型
        let theTime = parseInt(val);
        //返回值
        let result = '';
        //小时
        let hour = parseInt(theTime / 3600);
        //分钟
        let minute = parseInt((theTime / 60) % 60);
        //秒
        let second = parseInt(theTime % 60);
        if (hour > 0) {
          if (hour < 10) {
            result = "0" + hour + ":";
          } else {
            result = hour + ":";
          }
        }
        if (minute > 0) {
          if (minute < 10) {
            result = result + "0" + minute + ":";
          } else {
            result = result + minute + ":";
          }
        } else {
          result = result + "00:";
        }
        if (second > 0) {
          if (second < 10) {
            result = result + "0" + second;
          } else {
            result = result + second;
          }
        } else {
          result = result + "00";
        }
        return result;
      },
      //拖拽开始
      mousedown(e) {
        this.mouseStartX = e.clientX;
        this.tag = true;
      },
      //拖拽结束
      mouseup() {
        this.tag = false;
      },
      mousemove(e) {
        if (!this.duration) {
          return false;
        }
        if (this.tag) {
          //滑块移动的距离
          // clientX点击位置距离当前body可视区域的x坐标
          let movementX = e.clientX - this.mouseStartX;
          // 现在进度条的距离
          // getBoundingClientRect获取某个元素相对于视窗的位置集合
          let currentLength = this.$refs.cur_progress.getBoundingClientRect().width;
          // 总的进度条长度
          this.progressLength = this.$refs.progress.getBoundingClientRect().width
          let newPercent = ((movementX + currentLength) / this.progressLength) * 100;
          if (newPercent > 100) {
            newPercent = 100;
          }
          this.currentLength = newPercent;
          this.mouseStartX = e.clientX;
          this.changeTime(newPercent);
        }
      },
      //更改歌曲进度
      changeTime(percent) {
        let newCurrentTime = (percent * 0.01) * this.duration;
        this.$store.commit('setChangeTime', newCurrentTime);
      },
      //点击进度条切换播放进度
      updatemove(e) {
        if (!this.tag) {
          //进度条的左侧坐标
          let currentLength = this.$refs.bg.offsetLeft;
          let newPercent = ((e.clientX - currentLength) / this.progressLength) * 100;
          if (newPercent > 100) {
            newPercent = 100;
          } else if (newPercent < 0) {
            newPercent = 0;
          }
          this.currentLength = newPercent;
          this.changeTime(newPercent);
        }
      },
      //显示播放中的歌曲列表
      changeAside() {
        this.$store.commit('setShowAside', !this.showAside)
      },
      //上一首
      prev() {
        //当前处于不可能状态或只有一首歌的时候不执行
        if ((this.listIndex != -1) && (this.listOfSongs.length > 1)) {
          //不是第一首音乐
          if (this.listIndex > 0) {
            //直接返回上一首
            this.$store.commit('setListIndex', this.listIndex - 1);
          } else { //当前是第一首
            //切换到倒数第一首
            this.$store.commit('setListIndex', this.listOfSongs.length - 1);
          }
          this.toPlay(this.listOfSongs[this.listIndex].url);
        }
      },
      //下一首
      next() {
        //当前处于不可能状态或只有一首歌的时候不执行
        if ((this.listIndex != -1) && (this.listOfSongs.length > 1)) {
          //不是最后一首音乐
          if (this.listIndex < this.listOfSongs.length - 1) {
            //直接返回下一首
            this.$store.commit('setListIndex', this.listIndex + 1);
          } else { //当前是最后一首
            //切换到第一首
            this.$store.commit('setListIndex', 0);
          }
          this.toPlay(this.listOfSongs[this.listIndex].url);
        }
      },
      //播放音乐
      //获取名字前半部分--歌手名
      replaceFrontName(str) {
        let arr = str.split('-');
        return arr[0];
      },
      //获取名字后半部分--歌名
      replaceLatterName(str) {
        let arr = str.split('-');
        return arr[1];
      },
      //解析歌词
      parseLyric(text) {
        //歌词先分解为数组
        let lines = text.split("\n");
        //正则分割 时间表达式
        let pattern = /\[\d{2}:\d{2}.(\d{2}|\d{3})\]/g;
        let result = [];
        //对于歌词格式不正确
        if (!(/\[.+\]/.test(text))) {
          return [
            [0, text]
          ]
        }
        //去掉前面格式不正确的行
        while (!pattern.test(lines[0])) {
          lines = lines.slice(1)
        }
        //遍历每一行，形成一个带着两元素的数组,第一个元素是时间，第二个元素是歌词
        for (let item of lines) {
          //存放前面的时间段
          let time = item.match(pattern)
          //存放后面的歌词
          let value = item.replace(pattern, '')
          for (let index of time) {
            //取出时间 换算成数组 从第一个到倒数第一个
            let t = index.slice(1, -1).split(":")
            // parseInt(t[0],10)十进制转换
            if (value != '') {
              result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value]);
            }
          }
        }
        //按照第一个元素(时间)来排序
        result.sort(function (a, b) {
          return a[0] - b[0]
        });
        return result;
      },
      //播放
      toPlay: function (url) {
        if (url && url != this.url) {
          this.$store.commit('setId', this.listOfSongs[this.listIndex].id);
          this.$store.commit('setUrl', this.$store.state.config.HOST + url);
          this.$store.commit('setPicUrl', this.$store.state.config.HOST + this.listOfSongs[this.listIndex].song_pic);
          this.$store.commit('setTitle', this.replaceLatterName(this.listOfSongs[this.listIndex].song_name) + "-");
          this.$store.commit('setArtist', this.replaceFrontName(this.listOfSongs[this.listIndex].song_name));
          this.$store.commit('setLyric', this.parseLyric(this.listOfSongs[this.listIndex].lyric));
          this.$store.commit('setIsCollect', false)
          this.$store.commit('setCollectBtn', '#icon-xihuan')
          if (this.loginIn) {
            getCollectOfUserId(this.userId).then(res => {
              for (let item of res) {
                console.log(item)
                if (item.song_id == this.id) {
                  this.$store.commit('setIsCollect', true)
                  this.$store.commit('setCollectBtn', '#icon-xihuan1')
                  break;
                }
              }
            })
          }
        }
      },
      //转向歌词页面
      toLyric() {
        this.$router.push({
          path: '/lyric'
        })
      },
      //下载
      download() {
        //获取到blob值 res为返回值
        download(this.url).then(res => {
          // 返回的数据内容   
          let content = res.data
          // 创建下载功能
          let songLink = document.createElement('a')
          songLink.download = `${this.artist}-${this.title}.mp3`
          //执行隐藏的下载操作
          songLink.style.display = 'none'
          //字符内容转变成blob地址
          let blob = new Blob([content])
          // 链接地址
          songLink.href = URL.createObjectURL(blob)
          // 加入document里
          document.body.appendChild(songLink)
          //触发点击
          songLink.click()
          //然后移除这个加的控件 
          document.body.removeChild(songLink)
        }).catch(err => {
          console.log(err)
        })
      },
      notify(title, type) {
        this.$notify({
          title: title,
          type: type
        })
      },
      // 收藏
      collection() {
        if (this.loginIn) {
          var params = new URLSearchParams()
          params.append('user_id', this.userId)
          // if (this.type == 0) {
          params.append('song_id', this.id)
          // } else {
          // params.append('songlist_id', this.id)
          // }
          params.append('type', 0)
          setCollect(params).then(res => {
            console.log(res);
            if (res.code == 1) {
              // if (!this.isCollect) {
              // this.$store.commit('setIsCollect', true)
              this.$store.commit('setCollectBtn', '#icon-xihuan1')
              this.$store.commit('setIsCollect', true)
              // }
              this.notify('收藏成功', 'success')
            } else if (res.code == 2) {
              cancelCollection(this.userId, this.id).then(rs => {
                console.log(rs);
                this.notify('取消收藏', 'warning')
                this.$store.commit('setCollectBtn', '#icon-xihuan')
                this.$store.commit('setIsCollect', false)
              })


            } else {
              this.notify('收藏失败', 'error')
            }
          })
        } else {
          this.notify('请先登录', 'warning')
        }
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import "../assets/css/playbar.css";

</style>
