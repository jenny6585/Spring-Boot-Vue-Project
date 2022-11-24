<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자:" label-for="id">
          <b-form-input
            id="id"
            :disabled="isUserid"
            v-model="notification.userid"
            type="text"
            required
            readonly
            :placeholder="this.userInfo.userid"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="notification.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notification.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import {
  writeNotification,
  modifyNotification,
  getNotification,
} from "@/api/notification";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "NotificationInputItem",
  data() {
    return {
      notification: {
        notificationno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.notificationno;
      getNotification(
        param,
        ({ data }) => {
          this.notification = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      err &&
        !this.notification.subject &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject);
      err &&
        !this.notification.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content);
      if (!err) alert(msg);
      else
        this.type === "register"
          ? this.registNotification()
          : this.modifyNotification();
    },
    onReset(event) {
      event.preventDefault();
      this.notification.articleno = 0;
      this.notification.subject = "";
      this.notification.content = "";
      this.moveList();
    },
    registNotification() {
      let param = {
        userid: this.userInfo.userid,
        subject: this.article.subject,
        content: this.article.content,
      };
      console.log(param.userid);
      writeNotification(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyNotification() {
      let param = {
        notificationno: this.notification.articleno,
        userid: this.notification.userid,
        subject: this.notification.subject,
        content: this.notification.content,
      };
      modifyNotification(
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
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "notificationslist" });
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>