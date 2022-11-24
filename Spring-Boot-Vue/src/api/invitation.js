import { apiInstance } from "./index.js";

const api = apiInstance();


function writeMail(email, success, fail) {
  api.post(`/email/write`, JSON.stringify(email)).then(success).catch(fail);
}



export { writeMail };