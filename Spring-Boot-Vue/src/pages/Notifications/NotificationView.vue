<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="userInfo.userid === notification.userid">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyNotification"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteNotification"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${notification.notificationno}.
          ${notification.subject} [${notification.hit}]</h3><div><h6>${notification.userid}</div><div>${notification.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getNotification } from "@/api/notification";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "notificationsview",
  data() {
    return {
      notification: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.notification.content)
        return this.notification.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.notificationno;
    getNotification(
      param,
      ({ data }) => {
        this.notification = data;
      },
      (error) => {
        //console.log(error);
      }
    );
  },
  methods: {
    moveModifyNotification() {
      this.$router.replace({
        name: "notificationsmodify",
        params: { notificationno: this.notification.notificationno },
      });
      //   this.$router.push({ path: `/notification/modify/${this.notification.notificationno}` });
    },
    deleteNotification() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "notificationsdelete",
          params: { notificationno: this.notification.notificationno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "notificationslist" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>