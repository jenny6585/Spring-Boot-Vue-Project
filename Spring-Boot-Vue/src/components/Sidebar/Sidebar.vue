<template>
  <b-collapse
    class="sidebar-collapse"
    id="sidebar-collapse"
    :visible="sidebarOpened"
  >
    <nav :class="{ sidebar: true }">
      <header class="logo">
        <router-link to="/app"
          ><span class="fw-bold">서울 탐방</span></router-link
        >
      </header>

      <ul class="nav">
        <h5 class="navTitle">따릉이 지도</h5>
        <NavLink
          :activeItem="activeItem"
          header="Bike Map"
          link="/app/bikemap"
          iconName="flaticon-home-3"
          index="bikemap"
          isHeader
        />
        <h5 class="navTitle">즐겨찾기</h5>
        <NavLink
          header="Bookmark"
          link="/app/Bookmark"
          iconName="flaticon-pin-1"
          index="bookmark"
          isHeader
        />
        <h5 class="navTitle">게시판</h5>
        <NavLink
          header="공유 게시판"
          link="/app/board"
          iconName="flaticon-equal-3"
          index="board"
          isHeader
        />
        <NavLink
          header="공지사항"
          link="/app/notifications"
          iconName="flaticon-bell"
          index="notifications"
          isHeader
        />
        <NavLink
          header="초대장"
          link="/app/invitations"
          iconName="flaticon-message-circle"
          index="invitations"
          isHeader
        />
      </ul>
    </nav>
  </b-collapse>
</template>

<script>
import { mapState, mapActions } from "vuex";
import NavLink from "./NavLink/NavLink";

export default {
  name: "Sidebar",
  components: { NavLink },
  data() {
    return {
      alerts: [
        {
          id: 0,
          title: "Sales Report",
          value: 15,
          footer: "Calculating x-axis bias... 65%",
          color: "primary",
        },
        {
          id: 1,
          title: "Personal Responsibility",
          value: 20,
          footer: "Provide required notes",
          color: "danger",
        },
      ],
    };
  },
  methods: {
    ...mapActions("layout", ["changeSidebarActive", "switchSidebar"]),
    setActiveByRoute() {
      const paths = this.$route.fullPath.split("/");
      paths.pop();
      this.changeSidebarActive(paths.join("/"));
    },
  },
  created() {
    this.setActiveByRoute();
  },
  computed: {
    ...mapState("layout", {
      sidebarOpened: (state) => !state.sidebarClose,
      activeItem: (state) => state.sidebarActiveElement,
    }),
  },
};
</script>

<!-- Sidebar styles should be scoped -->
<style src="./Sidebar.scss" lang="scss" scoped/>
