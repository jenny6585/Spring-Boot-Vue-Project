<template>
  <div class="todoInput">
    <input placeholder="할일을 넣어주세요" @keyup.enter="addTodo" v-model="msg">
  </div>
</template>

<script>
export default {
  name: 'TodoInput',
  data(){
    return{
      msg:"",
    }
  },
  methods:{
    addTodo(){
      let item = {done:false, text:this.msg};
      this.$emit('addTodo',item);
      this.msg="";
    }
  }
}
</script>

<style>

</style>