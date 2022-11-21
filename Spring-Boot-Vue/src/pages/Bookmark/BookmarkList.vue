<template>
    <b-container>
        <!-- <bookmark-header></bookmark-header> -->
        <bookmark-input></bookmark-input>
        <table class="table table-bordered table-lg mt-lg mb-0">
        <thead class="text-uppercase">
            <tr>
                <th class="text-center">Bookmark</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(todo,index) in sameUser" :key="index">
                <td>
                    <b-form-checkbox size="lg" v-model="todo.checked" @click="modifyTodoStatus">
                        <span :class="{ completed: todo.checked }" >{{ todo.content }}</span>
                    </b-form-checkbox>
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
        moveList() {
            console.log("여기");
            this.$router.push({ name: "bookmarklist" });
        },
        modifyTodoStatus(){
            if(this.todo.checked === 0){
                this.todo.checked = 1;
                this.modifyTodo();
            }else{
                this.todo.checked = 0;
                this.modifyTodo();
            }
        },
        modifyTodo() {
            let param = {
                userid: this.todo.userid,
                content: this.todo.content,
                status: this.todo.checked,
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
                this.$router.go();
                //this.moveList();
                },
            );
        },
        removeTodo(){
            let param = this.numlist.filter(function(item){return item.checked===true;});//완료 애들 가져오기
            deleteTodo(
                param,
                ({ data }) => {
                let msg = "삭제 처리시 문제가 발생했습니다.";
                if (data === "success") {
                    msg = "삭제 완료되었습니다.";
                }
                alert(msg);
                // 현재 route를 /list로 변경.
                this.$router.go();
                //this.moveList();
                },
            )
        },
    },
};
</script>
<style src="../Bookmark/Bookmark.scss" lang="scss" scoped />