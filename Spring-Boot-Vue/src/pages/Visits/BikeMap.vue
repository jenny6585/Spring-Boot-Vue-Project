<template>
  <div class="visits-page">
    <h1 class="page-title">Lets go on a Trip &nbsp;
      <small>
        <small>...on a bike</small>
      </small>
    </h1>
    <b-row>
      <b-col lg="7">
        <Widget class="bg-transparent">
          

        </Widget>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Widget from '@/components/Widget/Widget';
import Map from './components/Map/Map';


export default {
  name: 'BikeMap',
  components: {
    Widget, Map
  },
};
</script>

<style src="./Visits.scss" lang="scss" />
