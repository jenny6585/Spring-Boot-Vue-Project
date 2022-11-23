import { apiInstance } from "./index.js";

const api = apiInstance();

function listBike(success, fail) {
  api.get(`/bike`).then(success).catch(fail);
}

function getBike(index, success, fail) {
  api.get(`/bike/${index}`).then(success).catch(fail);
}

function getTotalCount(param,success, fail) {
  api.get(`/bike/total`,param).then(success).catch(fail);
}

export { listBike,  getBike, getTotalCount };