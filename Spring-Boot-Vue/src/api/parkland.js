import { apiInstance } from "./index.js";

const api = apiInstance();

function listPark(success, fail) {
  api.get(`/park`).then(success).catch(fail);
}

function listLand(success, fail) {
  api.get(`/park/land`).then(success).catch(fail);
}


export { listPark,listLand   };