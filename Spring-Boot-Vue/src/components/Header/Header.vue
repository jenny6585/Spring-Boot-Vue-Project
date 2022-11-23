<template>
  <b-navbar toggleable="md" class="app-header d-print-none">
    <b-navbar-nav class="navbar-nav-mobile ml-auto">
      <b-nav-text class="mr-3"> </b-nav-text>
      <b-nav-form class="d-sm-down-none mr-3">
        <b-input-group class="input-group-transparent search-group">
          <b-input-group-text slot="prepend"
            ><i class="fi flaticon-search-2"></i
          ></b-input-group-text>
          <b-input
            class="input-transparent"
            id="search-input"
            placeholder="Search Dashboard"
          />
        </b-input-group>
      </b-nav-form>
      <!-- 로그인 했을때 -->
      <div v-if="userInfo">
        <b-nav-item-dropdown
          right
          class="avatar-toggle float-left"
          menu-class="py-0"
        >
          <template slot="button-content">
            <span class="avatar rounded-circle thumb-sm-1 float-left mr-2">
              <img
                class="rounded-circle"
                src="../../assets/people/a7.png"
                alt="..."
              />
            </span>
            <span class="text-white"
              >{{ userInfo.username }}({{ userInfo.userid }})님
              환영합니다.</span
            >
          </template>
          <notifications />
<<<<<<< HEAD
          <b-nav-item no-caret right class="mr-2">
            <b-dropdown-item float-left>
              <i class="la la-user" />
              <router-link
                :to="{ name: 'mypage' }"
                class="link align-self-center mr-2"
                >내정보보기</router-link
              >
            </b-dropdown-item>
            <b-dropdown-item float-left>
              <i class="fi flaticon-settings-10 px-2 float-left" />
              <router-link
                :to="{ name: 'modify' }"
                class="link align-self-center"
                >내정보 수정</router-link
              >
            </b-dropdown-item>
          </b-nav-item>
        </b-nav-item-dropdown>
        <div class="mt-3 ml float-left">
          <div>
            <i class="fi flaticon-power-1 px-2 float-left" /><span
              @click="logout"
              class="link align-self-center"
              >LOGOUT</span
            >
          </div>
        </div>
=======
          <b-nav-item class="divider d-md-down-none"></b-nav-item>
        <b-nav-item no-caret right class="mr-2">
          <template slot="button-content">
            
          </template>
          <b-dropdown-item float-left>
            <i class="la la-user" />
            <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
          </b-dropdown-item>
          <b-dropdown-item float-left>
            <i class="fi flaticon-settings-10 px-2 float-left" />
            <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보 수정</router-link>
          </b-dropdown-item>
          <b-dropdown-divider />
        </b-nav-item>
        <b-nav-item class="d-md-down-none float-left" @click="logout">
          <i class="fi flaticon-power-1 px-2" />
        </b-nav-item>
        </b-nav-item-dropdown>
        

      </div>
      <!-- 로그인 안했을때 -->
      <div v-else>
        <b-button variant="primary" href="/user/login">로그인 / 회원가입</b-button>
>>>>>>> 969988c1d05da1afe4ab674afb384aec0b4291cf
      </div>
      <!-- 로그인 안했을때 -->
      <b-navbar-nav v-else>
        <b-button variant="primary" href="/user/login"
          >로그인 / 회원가입</b-button
        >
      </b-navbar-nav>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import Notifications from "@/components/Notifications/Notifications";

const memberStore = "memberStore";

export default {
  name: "Header",
  components: { Notifications },
  data() {
    return {
      showNavbarAlert: true,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
    ...mapState("layout", {
      sidebarClose: (state) => state.sidebarClose,
      sidebarStatic: (state) => state.sidebarStatic,
    }),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    onClickLogout() {
      console.log(this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "login" });
    },
    ...mapActions("layout", ["switchSidebar", "changeSidebarActive"]),
    switchSidebarMethod() {
      if (!this.sidebarClose) {
        this.switchSidebar(true);
        this.changeSidebarActive(null);
      } else {
        this.switchSidebar(false);
        const paths = this.$route.fullPath.split("/");
        paths.pop();
        this.changeSidebarActive(paths.join("/"));
      }
    },
    logout() {
      window.localStorage.setItem("authenticated", false);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "login" });
    },
  },
};
</script>

<style src="./Header.scss" lang="scss" />
