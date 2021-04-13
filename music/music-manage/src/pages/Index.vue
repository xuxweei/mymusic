<template>
  <div>
    <el-row :gutter="20" class="mgb10">
      <el-col :span="6">
        <el-card>
          <div class="grid_content">
            <div class="grid_content_center">
              <div class="grid_num">{{consumerCount}}</div>
              <div>用户总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="grid_content">
            <div class="grid_content_center">
              <div class="grid_num">{{songCount}}</div>
              <div>歌曲总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="grid_content">
            <div class="grid_content_center">
              <div class="grid_num">{{singerCount}}</div>
              <div>歌手总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="grid_content">
            <div class="grid_content_center">
              <div class="grid_num">{{songListCount}}</div>
              <div>歌单总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20" class="mgb10">
      <el-col :span="12" class="col_height">
        <h3>用户性别比例</h3>
        <div style="background-color:white;height:200px;" id="pie">
          <ve-pie :data="consumerSex" :theme="option" :settings="chartSettings"></ve-pie>
        </div>
      </el-col>
      <el-col :span="12">
        <h3>歌曲类型分布</h3>
        <div style="background-color: white;height:200px;">
          <ve-histogram :data="songStyle" :theme="options3"></ve-histogram>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20" class="mgb10">
      <el-col :span="12">
        <h3 >歌手性别比例</h3>
        <div  style="background-color: white;height:200px;">
          <ve-pie :data="singerSex" :theme="options1" :settings="chartSettings"></ve-pie>
        </div>
      </el-col>
      <el-col :span="12">
        <h3>歌手国籍分布</h3>
        <div  style="background-color: white;height:200px;">
          <ve-histogram :data="country" :theme="options2" ></ve-histogram>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {
    getAllConsumer,
    getAllSinger,
    getAllSong,
    getAllSongList
  } from '../api/index';
  import echarts from 'echarts';
  export default {
    data() {
      this.chartSettings = {
        radius: 60,
        offsetY: 110
      }
      return {
        //用户总数
        consumerCount: 0,
        //歌曲总数
        songCount: 0,
        //歌手总数
        singerCount: 0,
        //歌单总数
        songListCount: 0,
        //所有用户
        consumer: [],
        //所有歌手
        singer: [],
        //所有歌曲
        song: [],
        songList: [],
        consumerSex: {
          columns: ['性别', '总数'],
          rows: [{
              '性别': '男',
              '总数': 0,
            },
            {
              '性别': '女',
              '总数': 0,
            }
          ]
        },
        singerSex: {
          columns: ['性别', '总数'],
          rows: [{
              '性别': '女',
              '总数': 0
            },
            {
              '性别': '男',
              '总数': 0
            },
            {
              '性别': '组合',
              '总数': 0
            },
            {
              '性别': '不明',
              '总数': 0
            },
          ]
        },
        songStyle: {
          //按照歌单风格分类
          columns: ['风格', '总数'],
          rows: [{
              '风格': '华语',
              '总数': 0
            },
            {
              '风格': '粤语',
              '总数': 0
            },
            {
              '风格': '欧美',
              '总数': 0
            },
            {
              '风格': '日语',
              '总数': 0
            },
            {
              '风格': '韩语',
              '总数': 0
            },
            {
              '风格': 'BGM',
              '总数': 0
            },
            {
              '风格': '治愈',
              '总数': 0
            },
            {
              '风格': '夜晚',
              '总数': 0
            },
            {
              '风格': '舒缓',
              '总数': 0
            }
          ]
        },
        country: {
          columns: ['国家', '总数'],
          rows: [{
              '国家': '中国内地',
              '总数': 0
            },
            {
              '国家': '中国香港',
              '总数': 0
            },
            {
              '国家': '中国台湾',
              '总数': 0
            },
            {
              '国家': '韩国',
              '总数': 0
            },
            {
              '国家': '日本',
              '总数': 0
            },
            {
              '国家': '欧美',
              '总数': 0
            },    
            {
              '国家': '其他',
              '总数': 0
            }
          ]
        },
        option: {
          color: ['#87cefa', '#ffc0cb']
        },
        options1: {
          color: ['#1E90FF', '#7B68EE','#fde931','#4bf059']
        },
        options2: {
          color: ['#FEED78'],
          tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          grid: {
            top:'7%',
            left: '3%',
            right: '4%',
            bottom: '50%',
            containLabel: true
          }
        },
        options3: {
          color: ['#FD8A61'],
          tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          grid: {
            top:'7%',
            left: '3%',
            right: '4%',
            bottom: '50%',
            containLabel: true
          }
        },

      }
    },
    mounted() {
      this.getConsumerCount();
      this.getSingerCount();
      this.getSongCount();
      this.getSongListCount();
    },

    methods: {
      getConsumerCount() {
        getAllConsumer().then(res => {
          this.consumer = res;
          this.consumerCount = res.length;
          this.consumerSex.rows[0]['总数'] = this.getConsumerSex(1, res)
          this.consumerSex.rows[1]['总数'] = this.getConsumerSex(0, res)
        })
      },
      //根据性别获取用户数
      getConsumerSex(sex, consumer) {
        let count = 0;
        for (let item of consumer) {
          if (sex == item.sex) {
            count++;
          }
        }
        return count;
      },
      getCountry(val) {
        for (let item of this.country.rows) {
          console.log(val);
          if (val.includes(item['国家'])) {
            item['总数']++;
            break;
          } 
        }
      },
      getSingerCount() {
        getAllSinger().then(res => {
          this.singer = res;
          this.singerCount = res.length;
          this.singerSex.rows[0]['总数'] = this.getConsumerSex(0, res);
          this.singerSex.rows[1]['总数'] = this.getConsumerSex(1, res);
          this.singerSex.rows[2]['总数'] = this.getConsumerSex(2, res);
          this.singerSex.rows[3]['总数'] = this.getConsumerSex(3, res);
          // console.log(res);
          for (let item of res) {
            this.getCountry(item.singer_location);
          }
        }).catch(err => {
          console.log(err)
        })
      },
      getSongCount() {
        getAllSong().then(res => {
          this.song = res;
          this.songCount = res.length;
        })
      },
      getSongListCount() {
        getAllSongList().then(res => {
          this.songList = res;
          this.songListCount = res.length;
          for (let item of res) {
            this.getStyle(item.style)
          }
        }).catch(err => {
          console.log(err)
        })
      },
      getStyle(val) {
        for (let item of this.songStyle.rows) {
          if (val.includes(item['风格'])) {
            item['总数']++
          }
        }
      },
    },

    components: {},
  }

</script>

<style>
  .grid_content {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
  }

  .grid_content_center {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: rgb(99, 99, 99);
  }

  .grid_num {
    font-size: 30px;
    font-weight: bold;
    /* color: #4bf059; */
  }

  .col_height {
    height: 150px;
  }

</style>
