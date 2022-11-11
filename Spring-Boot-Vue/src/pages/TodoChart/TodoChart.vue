<template>
  <div>
    <b-breadcrumb>
      <b-breadcrumb-item>YOU ARE HERE</b-breadcrumb-item>
      <b-breadcrumb-item active>TodoChart</b-breadcrumb-item>
    </b-breadcrumb>
    <h1 class="page-title">
      Chart - <span class="fw-semi-bold">ToDo</span>
    </h1>
    <b-row>
      <b-col xs="12" lg="6">
        <Widget
          title="<h5>Things Todo</h5>"
          customHeader close collapse
        >
          <h4>너 이거 해야해</h4>
          <div class="widget-padding-md w-100 h-100 text-left border rounded">
            <todo-input @addTodo="addTodo"></todo-input>
            <todo-list :todos="todos"></todo-list>
            <todo-footer @deleteTodo="deleteTodo" :todos="todos"></todo-footer>
          </div>
          
        </Widget>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Widget from '@/components/Widget/Widget';

import TodoInput from '@/pages/TodoChart/TodoInput';
import TodoList from '@/pages/TodoChart/TodoList';
import TodoFooter from '@/pages/TodoChart/TodoFooter';


export default {
  name: 'TodoChart',
  components: { 
    Widget,
    TodoInput,
    TodoList,
    TodoFooter
  },
  data(){
    return{
      todos:[],
    }
  },
  methods:{
    addTodo(item){
      this.todos.push(item);
    },
    deleteTodo(change){
      this.todos = change;
    }
  }
};
</script>

<style scoped>

</style>