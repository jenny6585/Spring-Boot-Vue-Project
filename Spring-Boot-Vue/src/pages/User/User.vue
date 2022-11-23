<template>
  <div class="auth-page">
    <b-container >
      <Widget class="widget-auth-new mx-auto" title="<h3 class='mt-0'></h3>" customHeader>
        <p class="widget-auth-info">
        </p>
        <router-view></router-view>
        <br>
      </Widget>
    </b-container>

    <footer class="auth-footer">
      Light Blue Vue Admin Dashboard Template - Made by <a href="https://flatlogic.com" target="_blank">Flatlogic</a>
    </footer>
  </div>
</template>

<script>
import Widget from '@/components/Widget/Widget';

export default {
  name: 'User',
  components:{
    Widget
  }
};
</script>

<style scoped>
</style>