<template>
  <tr>
    <td>{{}}</td>
    <td><a href="#">Mark</a></td>
    <td>Otto</td>
    <td>ottoto@example.com</td>
    <td><b-badge variant="gray" pill>Pending</b-badge></td>
  </tr>
</template>

<script>
export default {
  name: "TableItem",
  props:{
    item:Object,
  }
}
</script>

<style>

</style>