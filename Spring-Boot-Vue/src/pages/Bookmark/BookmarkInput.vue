<template>
    <b-input-group class="input-group-transparent search-group">
        <b-input-group-text slot="prepend"><i class="fi flaticon-search-2"></i></b-input-group-text>
        <b-input class="input-transparent" id="content" placeholder="Search Dashboard" v-model="todo.content" required/>
        <b-input-group-append>
            <b-button variant="outline-success" @click="onSubmit">등록</b-button>
        </b-input-group-append>
    </b-input-group>
</template>

<script>
import { writeTodo } from "@/api/bookmark";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
    name: "BookmarkInput",
    data() {
        return {
            todo: {
                todono: 0,
                userid: "",
                content: "",
                checked: 0,
            },
        };
    },
    computed:{
        ...mapState(memberStore, ["userInfo"]),
    },
    created(){
        this.todo.userid = this.userInfo.userid;
    },
    methods: {
        onSubmit() {
            let err = true;
            let msg = "";
            err && !this.todo.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

            if (!err) alert(msg);
            else this.registTodo();
        },
        onReset() {
            this.todo.todono = 0;
            this.todo.content = "";
            this.todo.checked = 0;
            this.todo.userid ="";
            this.moveList();
        },
        registTodo() {
            let param = {
                userid: this.todo.userid,
                content: this.todo.content,
                checked: 0,
            };
            console.log(param);
            writeTodo(
                param,
                ({ data }) => {
                let msg = "등록하시겠습니까?";
                if (data === "success") {
                    msg = "등록이 완료되었습니다.";
                }
                alert(msg);
                this.onReset();
                this.$router.go();
                //this.moveList();
                
                },
                
            );
            
            
        },
        moveList() {
            this.$router.push({ name: "bookmarklist" });
        },
    },
}
</script>

<style src="../Bookmark/Bookmark.scss" lang="scss" scoped />