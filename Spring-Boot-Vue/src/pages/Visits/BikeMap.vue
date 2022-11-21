<template>
  <div class="visits-page">
    <b-breadcrumb>
      <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
      <b-breadcrumb-item active>bicycle map</b-breadcrumb-item>
    </b-breadcrumb>
    <b-row>
      <b-col lg="7">
        <KakaoMap />
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Widget from '@/components/Widget/Widget';
import KakaoMap from "@/views/Map";


export default {
  name: 'BikeMap',
  components: {
    Widget, KakaoMap,
  },
};
</script>

<style src="./Visits.scss" lang="scss" />
#map{
  width:1000px;
  height:500px
}
</style>
