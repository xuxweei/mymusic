//歌曲相关
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
//存放缓存数据
const mv = ({
  state: {
    mvid: '',
    mvList: {},
    //音量
    sound: 10,
    //音乐时长
    progress: 0,
    //播放状态的图标
    playButton: '#icon-bofang2',
    isPlaying: false, //是否播放中
  },
  getters: {
    mvid: state => {
      let mvid = state.mvid;
      if (!mvid.length) {
        mvid = JSON.parse(window.sessionStorage.getItem('mvid'));
      }
      return mvid;
    },
    mvList: state => {
      let mvList = state.mvList;
      if (!mvList) {
        mvList = JSON.parse(window.sessionStorage.getItem('mvList') || null);
      }
      return mvList;
    },
    isPlaying: state => {
      let isPlaying = state.isPlaying;
      if (!isPlaying) {
        isPlaying = JSON.parse(window.sessionStorage.getItem('isPlaying') || null);
      }
      return isPlaying;
    },
    playButton: state => {
      let playButton = state.playButton;
      if (!playButton.length) {
        playButton = JSON.parse(window.sessionStorage.getItem('playButton') || null);
      }
      return playButton;
    },
    progress: state => {
      let progress = state.progress;
      if (!progress.length) {
        progress = JSON.parse(window.sessionStorage.getItem('progress') || null);
      }
      return progress;
    },
    sound: state => {
      let sound = state.sound;
      if (!sound) {
        sound = JSON.parse(window.sessionStorage.getItem('sound') || null);
      }
      return sound;
    }
  },
  mutations: {
    setMVId: (state, mvid) => {
      state.mvid = mvid
      window.sessionStorage.setItem('mvid', JSON.stringify(mvid));
    },
    setMVList: (state, mvList) => {
      state.mvList = mvList
      window.sessionStorage.setItem('mvList', JSON.stringify(mvList));
    },
    setPlayButton: (state, playButton) => {
      state.playButton = playButton
      window.sessionStorage.setItem('playButton', JSON.stringify(playButton));
    },
    setIsPlaying: (state, isPlaying) => {
      state.isPlaying = isPlaying
      window.sessionStorage.setItem('isPlaying', JSON.stringify(isPlaying));
    },
    setProgress: (state, progress) => {
      state.progress = progress
      window.sessionStorage.setItem('progress', JSON.stringify(progress));
    },
    setSound: (state, sound) => {
      state.sound = sound
      window.sessionStorage.setItem('sound', JSON.stringify(sound));
    },
  }
})

export default mv
