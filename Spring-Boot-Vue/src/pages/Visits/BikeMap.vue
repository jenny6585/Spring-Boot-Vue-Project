<template>
  <div class="visits-page">
    <b-breadcrumb>
      <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
      <b-breadcrumb-item active>bicycle map</b-breadcrumb-item>
    </b-breadcrumb>
    <b-row>
      <b-col lg="7">
        <ka-kao-map />
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Widget from '@/components/Widget/Widget';
import KaKaoMap from "@/views/KaKaoMap.vue";


export default {
  name: 'BikeMap',
  components: {
    Widget,
    KaKaoMap,
  },
};
</script>

<style src="./Visits.scss" lang="scss" />
