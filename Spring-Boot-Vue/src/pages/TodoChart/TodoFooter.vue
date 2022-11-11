<template>
  <div class="todoFooter">
    <p>{{countTodos}} / {{todos.length}}</p>
    <button @click="deleteTodo">완료 목록 삭제</button>
  </div>
</template>

<script>
export default {
  name: 'TodoFooter',
  props:["todos"],
  computed:{
    countTodos(){
      return this.todos.filter(function(item){return item.done;}).length;
    }
  },
  methods:{
    deleteTodo(){
      let change = this.todos.filter(function(item){return item.done==false;});
      this.$emit('deleteTodo',change);
    }
  }
}
</script>

<style>

</style>