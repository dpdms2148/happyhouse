import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

function registUser(user, success, fail) {
  api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

function modifyUser(user, success, fail) {
  api.put(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

function deleteUser(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}

async function idCheck(userid, success, fail) {
  await api.get(`/user/${userid}`).then(success).catch(fail);
}

function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

export { login, registUser, modifyUser, deleteUser, idCheck, findById, tokenRegeneration, logout };
