<template>
  <div>
        <b-breadcrumb>
        <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
        <b-breadcrumb-item active>Bookmark</b-breadcrumb-item>
        </b-breadcrumb>
        <h1 class="page-title">
        Bookmark - <span class="fw-semi-bold">My Favorite Places</span>
        </h1>
        <b-row>
        <b-col xs="12" lg="6">
            <Widget>
                <h3><span class="fw-semi-bold">BookMark</span></h3>
                <router-view></router-view>
            </Widget>
        </b-col>
        </b-row>
    </div>
</template>

<script>
import Widget from '@/components/Widget/Widget';

export default {
  name: "Bookmark",
  components: { 
        Widget,
  }
};
</script>

<style src="../Bookmark/Bookmark.scss" lang="scss" scoped />