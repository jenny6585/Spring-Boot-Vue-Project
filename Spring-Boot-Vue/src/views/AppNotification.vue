<template>
  <div class="tables-basic">
    <b-breadcrumb>
      <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
      <b-breadcrumb-item active>Notification Basic</b-breadcrumb-item>
    </b-breadcrumb>
    <h2 class="page-title">Notification - <span class="fw-semi-bold">Static</span></h2>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: "AppNotification",
};
</script>

<style src="./Boards.scss" lang="scss" scoped />
