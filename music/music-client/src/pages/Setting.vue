<template>
    <div class="setting">
      <div class="leftControl">
        <div class="settingsMainTitle">
          设置
        </div>
        <ul class="setting_aside">
          <li v-for="(item,index) in settingList" :key="index" :class="{activeColor:activeName==item.name}"
          @click="handleSetting(item)">
            {{item.name}}
          </li>
        </ul>
      </div>
      <div class="contentControl">
        <component :is="componentSrc"></component>
      </div>
    </div>
</template>

<script>
import {
    mixin
  } from '../mixins'
import SelfInfo from '../components/SelfInfo'
import UploadAvator from '../components/UploadAvator'
export default {
   name: 'setting',
    mixins: [mixin],
    data() {
      return {
        settingList:[
          {name:'个人信息',path:'SelfInfo'},
          {name:'修改头像',path:'UploadAvator'},
        ],
        activeName:'个人信息',
        componentSrc:'SelfInfo'
      }
    },

    methods: {
      handleSetting(item){
        this.activeName = item.name
        this.componentSrc = item.path
      }
    },

    components: {SelfInfo,UploadAvator},
}
</script>

<style scoped>
@import '../assets/css/setting.css';
</style>