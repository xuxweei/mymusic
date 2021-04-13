<template>
    <div class="content">
        <h4 class="title">
            <slot name="title"></slot>
        </h4>
        <ul>
            <li class="list_title">
                <div class="song_item">
                    <span class="item_index"></span> 
                    <!-- 专辑 -->
                    <span class="item_intro">专辑名</span>
                    <!-- 歌手 -->
                    <span class="item_name">歌手</span>
                   
                </div>
            </li>
            <li v-for="(item,index) in songList" :key="index">
                <div class="song_item">
                    <span class="item_index"></span>
                    <!-- 专辑 -->
                    <span class="item_intro">{{item.song_intro}}</span>
                    <!-- 歌手 -->
                    <span class="item_name">{{replaceFrontName(item.song_name)}}</span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
  import { mapGetters } from 'vuex';
  import { mixin } from '../mixins/index';
export default {
    name:'albumContent',
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
@import '../assets/css/songcontent.css';
</style>