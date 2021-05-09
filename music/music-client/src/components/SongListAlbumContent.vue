<template>
    <div class="content">
        <h4 class="title">
            <slot name="title"></slot>
        </h4>
        <ul>
            <li class="list_title">
                <div class="song_item">
                    <span class="item_index"></span>
                    <!-- 歌曲名 -->
                    <span class="item_title">歌曲名</span>
                    <!-- 歌手 -->
                    <span class="item_name">歌手</span>
                    <!-- 专辑 -->
                    <span class="item_intro">专辑</span>
                </div>
            </li>
            <li v-for="(item,index) in songList" :key="index">
                <div class="song_item" @click="toPlay(item.id,item.url,item.song_pic,index,item.song_name,item.lyric)">
                    <span class="item_index">{{index+1}}</span>
                    <!-- 歌曲名 -->
                    <span class="item_title">{{replaceLatterName(item.song_name)}}</span>
                    <!-- 歌手 -->
                    <span class="item_name">{{replaceFrontName(item.song_name)}}</span>
                    <!-- 专辑 -->
                    <span class="item_intro">{{item.song_intro}}</span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
  import { mixin } from '../mixins/index';
export default {
    name:'songlistalbumContent',
    props:[
        'songList'
    ],
    mixins: [mixin],
    data () {
      return {}
    },

    methods: {},

    components: {},
}
</script>

<style scoped>
@import '../assets/css/songlistalbumcontent.css';
</style>