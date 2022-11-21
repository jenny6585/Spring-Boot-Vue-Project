import { apiInstance } from "./index.js";

const api = apiInstance();

function listTodo(success, fail) {
    api.get(`/todo`).then(success).catch(fail);
}

function writeTodo(todo, success, fail) {
    api.post(`/todo`, JSON.stringify(todo)).then(success).catch(fail);
}

function deleteTodo(param, success, fail) {
    api.delete(`/todo`).then(success).catch(fail);
}

function modifyTodo(todo, success, fail) {
    api.put(`/todo`, JSON.stringify(todo)).then(success).catch(fail);
}

export { listTodo, writeTodo, deleteTodo, modifyTodo };