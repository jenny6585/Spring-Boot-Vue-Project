<template>
  <b-container class="bv-example-row mt-3">
    <h2>회원가입</h2>
    <b-col style="text-align: left">
      <b-form @submit="onSubmit">
        <b-form-group
          id="userid-group"
          label="아이디:"
          label-for="id"
          description="아이디를 입력하세요."
        >
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="user.userid"
            type="text"
            required
            placeholder="아이디 입력..."
            class="bg-white"
          ></b-form-input>
        </b-form-group>
        <b-button class="mb-4" variant="primary" @click="idDouble"
          >아이디 중복검사</b-button
        >
        <div v-if="temp.idCheck">
          <h6 class="warn-id" v-if="temp.alertid">
            사용 불가능한 아이디입니다...
          </h6>
          <h6 class="ok-id" v-else-if="!temp.alertid">
            사용가능한 아이디입니다...
          </h6>
        </div>

        <b-form-group
          id="username-group"
          label="이름:"
          label-for="name"
          description="이름를 입력하세요."
        >
          <b-form-input
            id="username"
            v-model="user.username"
            type="text"
            required
            placeholder="이름 입력..."
            class="bg-white"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userpwd-group"
          label="비밀번호:"
          label-for="pw"
          description="비밀번호를 입력하세요."
        >
          <b-form-input
            id="userpwd"
            v-model="user.userpwd"
            type="password"
            required
            placeholder="비밀번호 입력..."
            class="bg-white"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userpwdCheck-group"
          label="비밀번호:"
          label-for="pw"
          description="비밀번호를 다시 입력하세요."
        >
          <b-form-input
            id="userpwdCheck"
            v-model="temp.userpwdCheck"
            type="password"
            required
            placeholder="비밀번호 입력..."
            class="bg-white"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="email-group"
          label="이메일:"
          label-for="email"
          description="이메일를 입력하세요."
        >
          <b-form-input
            id="email"
            v-model="user.email"
            type="text"
            required
            placeholder="이메일 입력..."
            class="bg-white"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="number-group"
          label="전화번호:"
          label-for="number"
          description="전화번호를 입력하세요."
        >
          <b-form-input
            id="number"
            v-model="user.number"
            type="text"
            required
            placeholder="전화번호 입력..."
            class="bg-white"
          ></b-form-input>
        </b-form-group>

        <div class="form-group">
          <label>약관 동의</label>
          <div data-toggle="buttons">
            <label class="btn btn-primary active">
              <span>AGREE </span>
              <input id="agree" type="checkbox" autocomplete="off" />
            </label>
            <a class="ml-3" href="#">이용약관</a>에 동의합니다.
          </div>
        </div>

        <b-button type="submit" variant="primary" class="m-1"
          >회원가입</b-button
        >
      </b-form>
    </b-col>
  </b-container>
</template>

<script>
import { joinMember, findMember } from "@/api/member";

export default {
  name: "UserRegister",
  data() {
    return {
      temp: {
        userpwdCheck: "",
        idCheck: false,
        alertid: false,
      },
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        number: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  methods: {
    idDouble() {
      let userid = this.user.userid;
      console.log(userid);
      findMember(
        userid,
        ({ data }) => {
          let msg = "아이디가 이미 존재합니다.";
          if (data === "success") {
            msg = "사용 가능";
            this.temp.alertid = false;
          } else {
            this.temp.alertid = true;
          }
          alert(msg);
          this.temp.idCheck = true;
        },
        (error) => {
          console.log(error);
        }
      );
    },

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.user.userid &&
        ((msg = "아이디 입력해주세요"), (err = false), this.$refs.userid);
      err &&
        !this.user.username &&
        ((msg = "이름 입력해주세요"), (err = false), this.$refs.username);
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호 입력해주세요"), (err = false), this.$refs.userpwd);
      err &&
        !this.temp.userpwdCheck &&
        ((msg = "비밀번호를 다시 입력해주세요"),
        (err = false),
        this.$refs.userpwdCheck);
      err &&
        !this.user.email &&
        ((msg = "이메일 입력해주세요"), (err = false), this.$refs.userpwd);
      err &&
        !this.user.number &&
        ((msg = "010-XXXX-XXXX"), (err = false), this.$refs.userpwd);

      if (!err) alert(msg);
      else {
        console.log("aaaaaaaaaaaa");
        this.registUser();
      }
    },

    registUser() {
      let param = {
        userid: this.user.userid,
        username: this.user.username,
        userpwd: this.user.userpwd,
        email: this.user.email,
        number: this.user.number,
      };
      joinMember(
        param,
        ({ data }) => {
          let msg = "회원가입 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원가입이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
      this.moveList();
    },
    moveList() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped>
.warn-id {
  color: red;
  font-weight: bold;
}
.ok-id {
  font-weight: bold;
}
</style>
