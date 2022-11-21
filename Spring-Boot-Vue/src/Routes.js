import Vue from 'vue';
import VueRouter from 'vue-router';

import Layout from '@/components/Layout/Layout';
import Bookmark from '@/pages/Bookmark/Bookmark';
import Tables from '@/pages/Tables/Tables';
import Notifications from '@/pages/Notifications/Notifications';
import Icons from '@/pages/Icons/Icons';
import Maps from '@/pages/Maps/Maps';
import Charts from '@/pages/Charts/Charts';
import BikeMap from '@/pages/Visits/BikeMap';
import User from '@/pages/User/User';
import ErrorPage from '@/pages/Error/Error';
import BookmarkList from '@/pages/Bookmark/BookmarkList';
import UserRegister from '@/pages/User/UserRegister';
import UserLogin from '@/pages/User/UserLogin';
import UserMyPage from '@/pages/User/UserMyPage';

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
        path: 'tables',
        name: 'Tables',
        component: Tables,
      },
      {
        path: 'notifications',
        name: 'Notifications',
        component: Notifications,
      },
      {
        path: 'components/icons',
        name: 'Icons',
        component: Icons,
      },
      {
        path: 'components/maps',
        name: 'Maps',
        component: Maps,
      },
      {
        path: 'components/charts',
        name: 'Charts',
        component: Charts,
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