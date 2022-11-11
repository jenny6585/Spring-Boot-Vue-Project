<template>
  <div class="todoList">
    <div v-for="(item,index) in todos" :key="index">
      <input type="checkbox" v-model="item.done">
      <span :class="{donestyle:item.done}">{{item.text}}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TodoList',
  props:["todos"],
}
</script>

<style>
  .donestyle{
    text-decoration: line-through;
    color: lightgray;
  }
</style>