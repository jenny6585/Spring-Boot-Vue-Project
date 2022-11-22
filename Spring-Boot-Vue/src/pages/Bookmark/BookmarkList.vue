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
            <tr v-for="(todo,index) in sameUser" :key="index" >
                <td v-if="todo.checked">
                    <input checked type="checkbox" size="lg" v-model="todo.checked" @change="modifyTodoStatus(`${index}`)" />
                        <span :class="{ completed: todo.checked }" >{{ todo.content }}</span>
                </td>
                <td v-else>
                    <input type="checkbox" size="lg"  v-model="todo.checked" @change="modifyTodoStatus(`${index}`)" />
                        <span :class="{ completed: todo.checked }" >{{ todo.content }}</span>
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
    watch:{

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
        modifyTodoStatus(num){
            console.log(this.todos[num]);
            
            if(this.todos[num].checked == true){
                this.todos[num].checked = 1;
                console.log("0->1");
                console.log(this.todos[num].checked);
                this.modifyTodo(num);
            }else{
                this.todos[num].checked = 0;
                console.log("1->0");
                console.log(this.todos[num].checked);
                this.modifyTodo(num);
            }
        },
        modifyTodo(num) {
            let param = {
                todono: this.todos[num].todono,
                userid: this.todos[num].userid,
                content: this.todos[num].content,
                checked: this.todos[num].checked,
            };
            console.log(param);
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
            let param = {
                numlist: this.todos.filter((item)=>{return item.checked==true && item.userid==this.userInfo.userid;})
            }
            deleteTodo(
                param,
                ({ data }) => {
                let msg = "정말 삭제하시겠습니까?";
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