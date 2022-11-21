<template>
  <router-view />
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: 'App',
  computed:{
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    const currentPath = this.$router.history.current.path;

    if (window.localStorage.getItem('authenticated') === 'false') {
      if(this.isLogin==false){
        this.$router.push('/user/login');
      }
      
    }

    if (currentPath === '/' || currentPath === '/app') {
      this.$router.push('/app/bikemap');
    }
  },
};
</script>

<style src="./styles/theme.scss" lang="scss" />
