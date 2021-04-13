<template>
  <div class="swiper">
    <el-carousel :interval="5000" arrow="always" height="200px" type="card">
      <el-carousel-item v-for="(item,index) in swiperDataList" :key="index">
        <img :src="item.imageUrl" alt="">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        swiperDataList: [],
        bannerURL: 'http://localhost:3000/banner?type=0'
      }
    },
    created() {
      this.getBanner()
    },

    methods: {
      getBanner() {
        this.axios({
            methods: "get",
            url: this.bannerURL
          })
          .then(res => {
            console.log(res.data.banners);
            this.swiperDataList = res.data.banners;
          });
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import '../assets/css/swiper.css';

</style>
