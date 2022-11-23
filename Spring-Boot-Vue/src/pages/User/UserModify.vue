<template>
  <b-container class="bv-example-row mt-3">
    <!-- 모달 -->
    <div>
      <!-- Using value -->
      <b-modal
        visible
        centered
        id="my-modal"
        header="test"
        title="Confirm Approval"
        hide-footer
        hide-header-close
        no-close-on-backdrop
        no-close-on-esc
      >
        <template #modal-header>
          <div class="mx-auto">
            <h5>비밀번호를 입력해주세요</h5>
          </div>
        </template>

        <div class="col-12">
          <div class="ml-5 mr-5">
            <b-form-input
              type="password"
              id="pin-input"
              v-model="pin"
              required
            ></b-form-input>
          </div>
        </div>
        <div class="col-12 text-center">
          <slot name="pin-error" class="text-danger text-center">{{
            temp.comment
          }}</slot>
        </div>
        <div class="col-12"></div>
        <div class="col-12 text-center">
          <b-button
            variant="primary"
            size="sm"
            class="mt-2 mr-2"
            v-if="temp.commemt == userInfo.userpwd"
            >확인</b-button
          >
          <b-button
            variant="danger"
            size="sm"
            class="mt-2 ml-2"
            @click="movelist"
            >취소</b-button
          >
        </div>
      </b-modal>
    </div>
    <!-- 모달 -->

    <h2>내 정보 수정</h2>
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
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
            class="bg-white"
          ></b-form-input>
        </b-form-group>

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

        <b-button type="submit" variant="primary" class="m-1"
          >정보 수정</b-button
        >
      </b-form>
    </b-col>
  </b-container>
</template>


<script>
import { mapState } from "vuex";
import { modifyMember } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "UserModify",
  data() {
    return {
      temp: {
        comment:"",
        pwdCheck: false,
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
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.userpwd = this.userInfo.userpwd;
    this.user.email = this.userInfo.email;
    this.user.number = this.userInfo.number;
  },
  methods: {
    checkPW(comment){
      if(this.user.userid == this.temp.comment){
        //@click="show=false"
      }
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
    onReset(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.username = "";
      this.user.userpwd = "";
      this.user.email = "";
      this.user.number = 0;
      this.moveList();
    },
    modifyMember() {
      let param = {
        userid: this.user.userid,
        username: this.user.username,
        userpwd: this.user.userpwd,
        email: this.user.email,
        number: this.user.number,
      };
      modifyMember(
        param,
        ({ data }) => {
          let msg = "회원가입 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원가입이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>
