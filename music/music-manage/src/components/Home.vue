<template>
  <div>
    <homeHeader></homeHeader>
    <homeAside></homeAside>
    <div class="content_box" :class="{'content_collapse':collapse}">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import homeHeader from './Header'
  import homeAside from './Aside'
  import bus from "../assets/js/bus"
  export default {
    data() {
      return {
          collapse:false
      }
    },
    created(){
        //通过bus进行组件间的通信，来折叠侧边栏
      bus.$on('collapse', msg => {
        this.collapse = msg;
      })
    },

    methods: {},

    components: {
      homeHeader,
      homeAside
    },
  }

</script>

<style>
</style>
