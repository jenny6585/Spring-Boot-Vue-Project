<template>
  <b-row class="mb-1">
    <b-col style="text-align: left" v-if="temp.checkLogin">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="email-group"
          label="받는 사람:"
          label-for="email"
          description="받는 사람 이메일을 입력하세요.(XXXX@XXX.com)"
        >
          <b-form-input
            id="sendMail"
            v-model="email.sendMail"
            type="text"
            required
            placeholder="받는 사람 입력..."
            class="bg-white"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="email.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
            class="bg-white"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1"
          >메일 전송</b-button
        >
      </b-form>
    </b-col>
    <b-col style="text-align: left" v-else>
      <Widget class="bit top" align-self="center">
        <h1 align-self="center" class="text-center">
          로그인 후 사용 가능합니다
        </h1>
        <img class="center" src="@/assets/sosad.png" alt="" />
      </Widget>
    </b-col>
  </b-row>
</template>

<script>
import { writeMail } from "@/api/invitation";
import { mapState } from "vuex";
import Widget from "@/components/Widget/Widget";

const memberStore = "memberStore";

export default {
  name: "invitations",
  data() {
    return {
      temp: {
        checkLogin: false,
      },
      email: {
        sendMail: "",
        content: "",
      },
    };
  },
  created() {
    if (this.userInfo.userid == null) {
      this.temp.checkLogin = false;
    } else {
      this.temp.checkLogin = true;
    }
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  components: {
    Widget,
  },
  methods: {
    onSubmit() {
      let param = {
        sendMail: this.email.sendMail,
        content: this.email.content,
      };
      writeMail(
        param,
        ({ data }) => {
          let msg = "이메일 발송시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "이메일 발송이 완료되었습니다.";
          }
          alert(msg);
          this.onReset();
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onReset() {
      this.email.sendMail = "";
      this.email.content = "";
    },
    moveList() {
      this.$router.push({ name: "BikeMap" });
    },
  },
};
</script>

<style scoped>
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}
</style>