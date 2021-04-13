<template>
    <div class="go_top" @click="returnTop">
        <div class="box_in"></div>
    </div>
</template>

<script>
export default {
    name:'goTop',
    data () {
      return {}
    },

    methods: {
        returnTop(){
            document.documentElement.scrollTop = document.body.scrollTop = 0;
        }
    },

    components: {},
}
</script>

<style>
@import '../assets/css/gotop.css';
</style>