import { apiInstance } from "./index.js";
const api = apiInstance();

function listTodo(success, fail) {
  api
    .get(`/todo`)
    .then(success)
    .catch(fail);
}

function writeTodo(todo, success, fail) {
  fetch(`http://localhost:9999/vue/todo/write`, {
    method: "post",
    body: JSON.stringify(todo),
    headers: {
      // ***
      "Content-Type": "application/json", // ***
    }, // ***
  })
    .then(success)
    .catch(fail);

  //api.post(`/todo/write`, JSON.stringify(todo)).then(success).catch(fail);
}

function deleteTodo(param, success, fail) {
  fetch(`http://localhost:9999/vue/todo/delete`, {
    method: "post",
    body: JSON.stringify(param.numlist),
    headers: {
      // ***
      "Content-Type": "application/json", // ***
    }, // ***
  })
    .then(success)
    .catch(fail);
}

function modifyTodo(todo, success, fail) {
  api
    .put(`/todo`, JSON.stringify(todo))
    .then(success)
    .catch(fail);
}

export { listTodo, writeTodo, deleteTodo, modifyTodo };
