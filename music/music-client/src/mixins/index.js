import {
  likeSongOfName,
  likeSongOfIntro,
  getCollectOfUserId
} from "../api";
import {
  mapGetters
} from 'vuex'
export const mixin = {
  computed: {
    ...mapGetters([
      'loginIn',
      'userId',
      'isCollect'
    ])
  },
  methods: {
    //提示信息
    notify(title, type) {
      this.$notify({
        title: title,
        type: type
      })
    },
    //获取图片地址
    attachImageUrl(srcUrl) {
      //判断图片地址是否有值 有的话将其放入store的config里
      return srcUrl ? this.$store.state.config.HOST + srcUrl : '../assets/img/user.png';
    },
    //根据歌曲名字模糊搜索
    getSong() {
      if (!this.$route.query.keywords) {
        this.$store.commit('setListOfSongs', [])
        this.notify('输入内容不能为空', 'warning');
      } else {
        likeSongOfName(this.$route.query.keywords).then(res => {
          if (!res.length) {
            this.$store.commit('setListOfSongs', [])
            this.notify('很抱歉，未能找到相关搜索结果！', 'warning');
          } else {
            this.$store.commit('setListOfSongs', res)
          }
        }).catch(err => {
          console.log(err)
        });
      }
    },
    //根据专辑名模糊搜索
    getAlbum() {
      if (!this.$route.query.keywords) {
        this.$store.commit('setListOfSongs', [])
        this.notify('输入内容不能为空', 'warning');
      } else {
        likeSongOfIntro(this.$route.query.keywords).then(res => {
          if (!res.length) {
            this.$store.commit('setListOfSongs', [])
            this.notify('很抱歉，未能找到相关搜索结果！', 'warning');
          } else {
            this.$store.commit('setListOfSongs', res)
          }
        }).catch(err => {
          console.log(err)
        });
      }
    },
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
    //播放
    toPlay(id, url, pic, index, song_name, lyric) {
      this.$store.commit('setId', id);
      this.$store.commit('setUrl', this.$store.state.config.HOST + url);
      this.$store.commit('setPicUrl', this.$store.state.config.HOST + pic);
      this.$store.commit('setListIndex', this.$store.state.config.HOST + index);
      this.$store.commit('setTitle', this.replaceLatterName(song_name) + "-");
      this.$store.commit('setArtist', this.replaceFrontName(song_name));
      this.$store.commit('setLyric', this.parseLyric(lyric));
      this.$store.commit('setIsCollect',false)
      this.$store.commit('setCollectBtn', '#icon-xihuan')
      if (this.loginIn) {
        getCollectOfUserId(this.userId).then(res=>{
          for(let item of res){
            if (item.song_id == id) {
              this.$store.commit('setIsCollect',true)
              this.$store.commit('setCollectBtn', '#icon-xihuan1')
              break;
            }
          }
        })
      }
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
          if (value!='') {     
            //传入数组      
            result.push([parseInt(t[0],10)*60 + parseFloat(t[1]),value]);
          }
        }
      }
      //按照第一个元素(时间)来排序
      result.sort(function(a,b){
        return a[0]-b[0]
      });
      return result;
    },
    // 获取生日
    attachBirth(val){
      return val.substr(0,10)
    }

  }
}
