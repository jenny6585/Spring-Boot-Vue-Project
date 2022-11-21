<template>
    <b-container>
        <!-- <bookmark-header></bookmark-header> -->
        <bookmark-input></bookmark-input>
        <table class="table table-bordered table-lg mt-lg mb-0">
        <thead class="text-uppercase">
            <tr>
                <th>
                </th>
                <th class="text-center">Bookmark</th>
            </tr>
        </thead>
        <tbody v-for="(todo,index) in sameUser" :key="index" :todo="todo">
            <tr>
                <td>
                <div class="abc-checkbox">
                    <input type="checkbox"
                    id="checkbox15" v-model="todo.status"
                    />
                    <label for="checkbox15" />
                </div>
                </td>
                <td>
                    <span :class="{ completed: todo.status }" @click="modifyTodoStatus">{{ todo.content }}</span>
                </td>
            </tr>
        </tbody>
    </table>
    <b-button variant="danger" @click="removeTodo">Delete Visited Places</b-button>
    </b-container>
</template>

<script>
import { mapState } from "vuex";
import { listTodo, deleteTodo, modifyTodo} from "@/api/bookmark";
import Widget from '@/components/Widget/Widget';
import Sparklines from '../../components/Sparklines/Sparklines';
import BookmarkInput from '@/pages/Bookmark/BookmarkInput';
//import BookmarkHeader from '@/pages/Bookmark/BookmarkHeader.vue';

const memberStore = "memberStore";

export default {
    name: 'BookmarkList',
    data(){
        return{
            todos:[],
            numlist:[],
        };
    },
    created(){
        listTodo(
            ({ data }) => {
            this.todos = data;
            },
        );
    },
    components: { 
        Widget, 
        Sparklines,
       //BookmarkHeader,
        BookmarkInput,
    },
    computed:{
        ...mapState(memberStore, ["userInfo"]),
        sameUser(){
            
            return this.todos.filter((item)=>{return item.userid == this.userInfo.userid;});
        },
    },
    methods:{
        modifyTodoStatus(){
            if(this.todo.status === 0){
                this.todo.status = 1;
                this.modifyArticle();
            }else{
                this.todo.status = 0;
                this.modifyArticle();
            }
        },
        modifyTodo() {
            let param = {
                userid: this.todo.userid,
                content: this.todo.content,
                status: this.todo.status,
            };
            modifyTodo(
                param,
                ({ data }) => {
                let msg = "수정 처리시 문제가 발생했습니다.";
                if (data === "success") {
                    msg = "수정이 완료되었습니다.";
                }
                alert(msg);
                // 현재 route를 /list로 변경.
                this.moveList();
                },
            );
        },
        removeTodo(){
            let param = this.numlist.filter(function(item){return item.status===true;});//완료 애들 가져오기
            deleteTodo(
                param,
                ({ data }) => {
                let msg = "삭제 처리시 문제가 발생했습니다.";
                if (data === "success") {
                    msg = "삭제 완료되었습니다.";
                }
                alert(msg);
                // 현재 route를 /list로 변경.
                this.moveList();
                },
            )
        },
    },
};
</script>
<style src="../Bookmark/Bookmark.scss" lang="scss" scoped />