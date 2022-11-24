import { apiInstance } from "./index.js";

const api = apiInstance();

function listNotification(param, success, fail) {
  api.get(`/notification`, { params: param }).then(success).catch(fail);
}

function writeNotification(notification, success, fail) {
  api.post(`/notification`, JSON.stringify(article)).then(success).catch(fail);
}

function getNotification(notificationno, success, fail) {
  api.get(`/notification/${notificationno}`).then(success).catch(fail);
}

function modifyNotification(notification, success, fail) {
  api.put(`/notification`, JSON.stringify(article)).then(success).catch(fail);
}


function deleteNotification(notificationno, success, fail) {
  api.delete(`/notification/${notificationno}`).then(success).catch(fail);
}

function getTotalCount(param,success, fail) {
  api.get(`/notification/total`,param).then(success).catch(fail);
}
export { listNotification, writeNotification, getNotification, modifyNotification, deleteNotification, getTotalCount };