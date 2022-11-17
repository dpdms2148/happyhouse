import { apiInstance } from "./index.js";

const api = apiInstance();

function listQna(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function writeQna(qna, success, fail) {
  api.post(`/board`, JSON.stringify(qna)).then(success).catch(fail);
}

function getQna(qnano, success, fail) {
  api.get(`/board/${qnano}`).then(success).catch(fail);
}

function modifyQna(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteQna(articleno, success, fail) {
  api.delete(`/board/${qnano}`).then(success).catch(fail);
}

export { listQna, writeQna, getQna, modifyQna, deleteQna };
