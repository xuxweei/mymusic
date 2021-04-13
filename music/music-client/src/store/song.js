//歌曲相关
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
//存放缓存数据
const song = ({
  state: {
      //当前歌曲列表
   listOfSongs:[],
   isPlay: false, //是否播放中
   url: '', //歌曲地址
   id: '' ,//歌曲id
   //播放状态的图标
   playBtn:'#icon-bofang1',
   //音乐时长
   duration:0,
   //当前音乐播放位置
   currentTime:0,
   //指定播放时间
   changeTime:0,
   //歌名
   title:'',
   //歌手名
   artist:'',
   //歌曲图片
   picUrl:'',
   //是否自动播放下一首
   autoNext:true,
    //未处理的歌词
    lyric:[],
    //单个歌单信息
    tempList:{},
    //当前歌曲在歌单中的位置
    listIndex:null,
    //音量
    volume:10
  },
  getters: {
    listOfSongs:state =>{
        let listOfSongs = state.listOfSongs;
        if(!listOfSongs.length){
            listOfSongs = JSON.parse(window.sessionStorage.getItem('listOfSongs')||null);
        }
        return listOfSongs;
    },
    isPlay: state => {
      let isPlay = state.isPlay;
      if(!isPlay.length){
        isPlay = JSON.parse(window.sessionStorage.getItem('isPlay')||null);
      }
      return isPlay;
    },
    url: state => {
      let url = state.url;
      if(!url.length){
        url = JSON.parse(window.sessionStorage.getItem('url')||null);
      }
      return url;
    },
    id: state => {
      let id = state.id;
      if(!id.length){
        id = JSON.parse(window.sessionStorage.getItem('id')||null);
      }
      return id;
    },
    playBtn: state => {
      let playBtn = state.playBtn;
      if(!playBtn.length){
        playBtn = JSON.parse(window.sessionStorage.getItem('playBtn')||null);
      }
      return playBtn;
    },
    duration: state => {
      let duration = state.duration;
      if(!duration.length){
        duration = JSON.parse(window.sessionStorage.getItem('duration')||null);
      }
      return duration;
    },
    currentTime: state => {
      let currentTime = state.currentTime;
      if(!currentTime.length){
        currentTime = JSON.parse(window.sessionStorage.getItem('currentTime')||null);
      }
      return currentTime;
    },
    changeTime: state => {
      let changeTime = state.changeTime;
      if(!changeTime.length){
        changeTime = JSON.parse(window.sessionStorage.getItem('changeTime')||null);
      }
      return changeTime;
    },
    title: state => {
      let title = state.title;
      if(!title){
        title = JSON.parse(window.sessionStorage.getItem('title')||null);
      }
      return title;
    },
    artist: state => {
      let artist = state.artist;
      if(!artist){
        artist = JSON.parse(window.sessionStorage.getItem('artist')||null);
      }
      return artist;
    },
    picUrl: state => {
      let picUrl = state.picUrl;
      if(!picUrl){
        picUrl = JSON.parse(window.sessionStorage.getItem('picUrl')||null);
      }
      return picUrl;
    },
    autoNext: state => {
      let autoNext = state.autoNext;
      if(!autoNext){
        autoNext = JSON.parse(window.sessionStorage.getItem('autoNext')||null);
      }
      return autoNext;
    },
    lyric: state => {
      let lyric = state.lyric;
      if(!lyric){
        lyric = JSON.parse(window.sessionStorage.getItem('lyric')||null);
      }
      return lyric;
    },
    tempList: state => {
      let tempList = state.tempList;
      if(!tempList){
        tempList = JSON.parse(window.sessionStorage.getItem('tempList')||null);
      }
      return tempList;
    },
    listIndex: state => {
      let listIndex = state.listIndex;
      if(!listIndex){
        listIndex = JSON.parse(window.sessionStorage.getItem('listIndex')||null);
      }
      return listIndex;
    },
    volume: state => {
      let volume = state.volume;
      if(!volume){
        volume = JSON.parse(window.sessionStorage.getItem('volume')||null);
      }
      return volume;
    }

  },
  mutations: {
    setListOfSongs: (state, listOfSongs) => {
      state.listOfSongs = listOfSongs;
      window.sessionStorage.setItem('listOfSongs',JSON.stringify(listOfSongs));
    },
    setIsPlay: (state, isPlay) => {
      state.isPlay = isPlay;
      window.sessionStorage.setItem('isPlay',JSON.stringify(isPlay));
    },
    setUrl: (state, url) => {
      state.url = url
      window.sessionStorage.setItem('url',JSON.stringify(url));
    },
    setId: (state, id) => {
      state.id = id
      window.sessionStorage.setItem('id',JSON.stringify(id));
    },
    setPlayBtn: (state, playBtn) => {
      state.playBtn = playBtn
      window.sessionStorage.setItem('playBtn',JSON.stringify(playBtn));
    },
    setDuration: (state, duration) => {
      state.duration = duration
      window.sessionStorage.setItem('duration',JSON.stringify(duration));
    },
    setCurrentTime: (state, currentTime) => {
      state.currentTime = currentTime
      window.sessionStorage.setItem('currentTime',JSON.stringify(currentTime));
    },
    setChangeTime: (state, changeTime) => {
      state.changeTime = changeTime
      window.sessionStorage.setItem('changeTime',JSON.stringify(changeTime));
    },
    setTitle: (state, title) => {
      state.title = title
      window.sessionStorage.setItem('title',JSON.stringify(title));
    },
    setArtist: (state, artist) => {
      state.artist = artist
      window.sessionStorage.setItem('artist',JSON.stringify(artist));
    },
    setPicUrl: (state, picUrl) => {
      state.picUrl = picUrl
      window.sessionStorage.setItem('picUrl',JSON.stringify(picUrl));
    },
    setAutoNext: (state, autoNext) => {
      state.autoNext = autoNext
      window.sessionStorage.setItem('autoNext',JSON.stringify(autoNext));
    },
    setLyric: (state, lyric) => {
      state.lyric = lyric
      window.sessionStorage.setItem('lyric',JSON.stringify(lyric));
    },
    setTempList: (state, tempList) => {
      state.tempList = tempList
      window.sessionStorage.setItem('tempList',JSON.stringify(tempList));
    },
    setListIndex: (state, listIndex) => {
      state.listIndex = listIndex
      window.sessionStorage.setItem('listIndex',JSON.stringify(listIndex));
    },
    setVolume: (state, volume) => {
      state.volume = volume
      window.sessionStorage.setItem('volume',JSON.stringify(volume));
    },
  }
})

export default song
