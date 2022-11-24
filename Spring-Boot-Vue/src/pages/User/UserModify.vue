<template>
  <b-container class="back-modal bv-example-row mt-3">
    <!-- 모달 -->
    <div v-if="temp.pwdCheck">
      <!-- Using value -->
      <b-modal
        visible
        centered
        id="my-modal"
        header="test"
        title="Confirm Approval"
        hide-footer
        hide-header-close
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
              v-model="temp.comment"
              required
            ></b-form-input>
          </div>
        </div>

        <div class="col-12 text-center">
          <b-button
            variant="primary"
            size="sm"
            class="mt-2 mr-2"
            @click="checkPW()"
            >확인</b-button
          >
          <b-button
            variant="danger"
            size="sm"
            class="mt-2 ml-2"
            @click="moveList_bike()"
            >취소</b-button
          >
        </div>
      </b-modal>
    </div>
    <!-- 모달 -->
    <div v-else>
      <h2>내 정보 수정</h2>
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group
            id="userid-group"
            label="아이디:"
            label-for="id"
            description="아이디는 수정 불가합니다."
          >
            <b-form-input
              id="userid"
              readonly
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
    </div>
  </b-container>
</template>


<script>
import { mapState, mapActions } from "vuex";
import { modifyMember } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "UserModify",
  data() {
    return {
      temp: {
        comment: "",
        pwdCheck: true,
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
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.userpwd = this.userInfo.userpwd;
    this.user.email = this.userInfo.email;
    this.user.number = this.userInfo.number;
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm"]),

    async checkPW() {
      let made_user = {
        userid: this.userInfo.userid,
        userpwd: this.temp.comment,
      };
      console.log(made_user);

      await this.userConfirm(made_user);
      this.temp.comment = "";
      if (this.isLogin) {
        this.temp.pwdCheck = false;
        alert("비밀번호를 확인하였습니다");
      } else {
        alert("비밀번호를 다시 확인해주세요");
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
        !this.user.email &&
        ((msg = "이메일 입력해주세요"), (err = false), this.$refs.userpwd);
      err &&
        !this.user.number &&
        ((msg = "010-XXXX-XXXX"), (err = false), this.$refs.userpwd);

      if (!err) alert(msg);
      else {
        console.log("aaaaaaaaaaaa");
        this.modifyMember();
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
          let msg = "정보수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "정보수정 완료되었습니다.";
          }
          alert(msg);
          this.moveList_bike();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "login" });
    },
    moveList_bike() {
      this.$router.push({ name: "BikeMap" });
    },
  },
};
</script>

<style scoped>
.back-modal {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  backdrop-filter: blur(5px);
}
</style>
