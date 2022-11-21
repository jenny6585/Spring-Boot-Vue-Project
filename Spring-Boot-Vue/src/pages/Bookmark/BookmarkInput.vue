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
                status: 0,
            },
        };
    },
    computed:{
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        onSubmit(event) {
            event.preventDefault();

            let err = true;
            let msg = "";
            err && !this.todo.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

            if (!err) alert(msg);
            else this.registTodo();
        },
        onReset(event) {
            event.preventDefault();
            this.todo.todono = 0;
            this.todo.content = "";
            this.todo.status = 0;
            this.moveList();
        },
        registTodo() {
            let param = {
                userid: this.userInfo.userid,
                content: this.todo.content,
                status: 0,
            };
            console.log(this.todo.content);
            writeTodo(
                param,
                ({ data }) => {
                let msg = "등록 처리시 문제가 발생했습니다.";
                if (data === "success") {
                    msg = "등록이 완료되었습니다.";
                }
                alert(msg);
                this.moveList();
                },
            );
            this.onReset();
        },
        moveList() {
            console.log("여기");
            this.$router.push({ name: "bookmarklist" });
        },
    },
}
</script>

<style src="../Bookmark/Bookmark.scss" lang="scss" scoped />