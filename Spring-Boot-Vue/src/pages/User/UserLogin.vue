<template>
  <form class="mt-4" @submit.prevent="login">
    <h2>LOGIN</h2>
    <h2>-----------</h2>
    <b-form-group class="mt-5" label="ID" label-for="id">
      <b-input-group>
        <b-input-group-text slot="prepend"
          ><i class="la la-user text-white"></i
        ></b-input-group-text>
        <b-alert show variant="danger" v-if="isLoginError"
          >아이디 또는 비밀번호를 확인하세요.</b-alert
        >
        <input
          id="userid"
          v-model="user.userid"
          class="form-control input-transparent pl-3"
          type="id"
          required
          placeholder="ID"
          @keyup.enter="confirm"
        />
      </b-input-group>
    </b-form-group>
    <b-form-group class="mt-5" label="Password" label-for="password">
      <b-input-group>
        <b-input-group-text slot="prepend"
          ><i class="la la-lock text-white"></i
        ></b-input-group-text>
        <input
          id="userpwd"
          v-model="user.userpwd"
          class="form-control input-transparent pl-3"
          type="password"
          required
          placeholder="Password"
          @keyup.enter="confirm"
        />
      </b-input-group>
    </b-form-group>
    <div class="auth-widget-footer mt-4">
      <b-button
        type="button"
        variant="danger"
        class="auth-btn mt-5"
        size="sm"
        @click="confirm"
      >
        <span class="auth-btn-circle">
          <i class="la la-caret-right"></i>
        </span>
        Login
      </b-button>

      <p class="widget-auth-info mt-4">Don't have an account? Sign up now!</p>
      <b-button
        type="button"
        variant="primary"
        class="auth-btn mb-5"
        size="sm"
        @click="movePage"
      >
        Create an Account
      </b-button>

      <div class="social-buttons mt-5">
        <b-button variant="success" class="social-button">
          <i class="social-icon social-naver"></i>
          <p class="social-text">NAVER</p>
        </b-button>
        <b-button variant="warning" class="social-button">
          <i class="social-icon social-kakao"></i>
          <p class="social-text">KAKAO</p>
        </b-button>
        <b-button variant="primary" class="social-button">
          <i class="social-icon social-google"></i>
          <p class="social-text">GOOGLE</p>
        </b-button>
      </div>
    </div>
  </form>
</template>

<script>
import Widget from "@/components/Widget/Widget";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  components: {
    Widget,
  },
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);

      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "BikeMap" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
    findPW() {
      this.$router.push({ name: "userfind" });
    },
  },
  created() {
    if (window.localStorage.getItem("authenticated") === "true") {
      this.$router.push({ name: "BikeMap" });
    }
  },
};
</script>

<style scoped>
.text-black {
  color: black;
  font-weight: bold;
}
</style>
