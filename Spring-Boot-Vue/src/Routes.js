import Vue from 'vue';
import VueRouter from 'vue-router';

import Layout from '@/components/Layout/Layout';
import Bookmark from '@/pages/Bookmark/Bookmark';
import BikeMap from '@/pages/Visits/BikeMap';
import User from '@/pages/User/User';
import ErrorPage from '@/pages/Error/Error';
import BookmarkList from '@/pages/Bookmark/BookmarkList';
import UserRegister from '@/pages/User/UserRegister';
import UserLogin from '@/pages/User/UserLogin';
import UserMyPage from '@/pages/User/UserMyPage';
import UserModify from '@/pages/User/UserModify';
import Invitations from '@/pages/Invitations/Invitations';

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  //console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    //console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    //console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: '/user',
    name: 'user',
    component: User,
    children: [
      {
        path: "join",
        name: "join",
        component: UserRegister,
      },
      {
        path: "login",
        name: "login",
        component: UserLogin,
      },
      {
        path: "modify",
        name: "modify",
        component: UserModify,
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: UserMyPage,
      },
    ],
  },
  {
    path: '/app',
    name: 'Layout',
    component: Layout,
    children: [
      {
        path: 'bikemap',
        name: 'BikeMap',
        component: BikeMap,
      },
      {
        path: 'bookmark',
        name: 'Bookmark',
        component: Bookmark,
        redirect: "bookmark/bookmarklist",
        children: [
          {
            path: "bookmarklist",
            name: "bookmarklist",
            component: BookmarkList,
          },
        ],
      },
      {
        path: "board",
        name: "Boards",
        component: () => import("@/views/AppBoard"),
        redirect: "board/list",
        children: [
          {
            path: "list",
            name: "boardlist",
            component: () => import("@/pages/board/BoardList"),
          },
          {
            path: "write",
            name: "boardwrite",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/board/BoardWrite"),
          },
          {
            path: "view/:articleno",
            name: "boardview",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/board/BoardView"),
          },
          {
            path: "modify",
            name: "boardmodify",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/board/BoardModify"),
          },
          {
            path: "delete/:articleno",
            name: "boarddelete",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/board/BoardDelete"),
          },
        ],
      },
      {
        path: 'notifications',
        name: 'Notifications',
        component: () => import("@/views/AppNotification"),
        redirect: "Notifications/notificationslist",
        children: [
          
          {
            path: "notificationslist",
            name: "notificationslist",
            component: () => import("@/pages/Notifications/NotificationList"),
          },
          {
            path: "notificationswrite",
            name: "notificationswrite",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/Notifications/NotificationWrite"),
          },
          {
            path: "notificationsview/:articleno",
            name: "notificationsview",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/Notifications/NotificationView"),
          },
          {
            path: "notificationsmodify",
            name: "notificationsmodify",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/Notifications/NotificationModify"),
          },
          {
            path: "notificationsdelete/:articleno",
            name: "notificationsdelete",
            beforeEnter: onlyAuthUser,
            component: () => import("@/pages/Notifications/NotificationDelete"),
          },
        ],
      },
      {
        path: 'invitations',
        name: 'invitations',
        component: Invitations,
      },

    ],
  },
  {
    path: '*',
    name: 'Error',
    component: ErrorPage,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;