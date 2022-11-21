<template>
  <div class="tables-basic">
    <b-breadcrumb>
      <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
      <b-breadcrumb-item active>board Basic</b-breadcrumb-item>
    </b-breadcrumb>
    <h2 class="page-title">board - <span class="fw-semi-bold">Static</span></h2>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: "AppBoard",
};
</script>

<style src="./Boards.scss" lang="scss" scoped />
