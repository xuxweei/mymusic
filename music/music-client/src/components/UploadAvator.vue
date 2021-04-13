<template>
  <div class="upload">
    <p class="title">修改头像</p>
    <hr />
    <div class="section">
      <el-upload
         drag 
         class="upload-demo" 
         :action="uploadUrl()" 
         :show-file-list="false"
         :on-success="handleAvatarSuccess" 
         :before-upload="beforeAvatarUpload">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过10M</div>
      </el-upload>
    </div>
  </div>
</template>

<script>
  import {
    mixin
  } from '../mixins'
  import {
    mapGetters
  } from 'vuex'
  export default {
    name: 'uploadAvator',
    inject: ['reload'],
    mixins: [mixin],
    data() {
      return {}
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    },

    methods: {
      //上传地址
      uploadUrl() {
        return `${this.$store.state.config.HOST}/consumer/uploadConsumerOfAvator?id=${this.userId}`
      },
      //上传成功
      handleAvatarSuccess(res, file) {
        if (res.code === 1) {
          console.log("res:", res)
          this.$store.commit('setAvator', res.consumer_pic);
          this.notify('修改成功', 'success');
          this.reload();
        } else {
          this.notify('修改失败', 'error');
        }
      },
      //上传前的检验
      beforeAvatarUpload(file) {
        const isImg = (file.type === 'image/jpeg') || (file.type === 'image/png') || (file.type === 'image/jpg');
        const isLt10M = (file.size / 1024 / 1024) < 10;
        if (!isImg) {
          this.notify('上传头像图片只能是jpg或png或jpeg格式', 'error');
          return false;
        }
        if (!isLt10M) {
          this.notify('上传头像图片大小不能超过10MB!', 'error');
          return false;
        }
        return true;
      }
    },

    components: {},
  }

</script>

<style scoped>
  @import '../assets/css/uploadavator.css';

</style>
