<template>
  <div class="tables-basic">
    <b-breadcrumb>
      <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
      <b-breadcrumb-item active>꽁지사항</b-breadcrumb-item>
    </b-breadcrumb>
    <h2 class="page-title">
      <span class="fw-semi-bold">공지사항</span>
    </h2>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: "Notifications",
};
</script>

<style src="./Boards.scss" lang="scss" scoped />
