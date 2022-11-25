import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function joinMember(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function modifyMember(user, success, fail) {
  await api.put(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function findMember(userid, success, fail) {
  await api.post(`/user/find`, JSON.stringify(userid)).then(success).catch(fail);
}
function deleteMember(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}
export { login, findById, tokenRegeneration, logout, joinMember, modifyMember, findMember,deleteMember };
