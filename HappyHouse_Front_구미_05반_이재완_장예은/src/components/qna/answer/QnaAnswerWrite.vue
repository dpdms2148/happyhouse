<template>
  <b-container>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-form-textarea
              id="content"
              v-model="qna.content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>

          <b-button
            type="submit"
            variant="primary"
            class="m-1"
            v-if="this.type === 'register'"
            >글작성</b-button
          >
          <b-button type="submit" variant="primary" class="m-1" v-else
            >글수정</b-button
          >
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/qna";

export default {
  name: "QnaAnswerWrite",
  data() {
    return {
      qna: {
        answer: "",
      },
      isUserid: false,
    };
  },
  computed: {
    message() {
      if (this.qna.answer) return this.qna.answer.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.qna.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.qna.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.qna.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registQna() : this.modifyQna();
    },
    onReset(event) {
      event.preventDefault();
      this.qna.qnano = 0;
      this.qna.subject = "";
      this.qna.content = "";
      this.moveList();
    },
    registQna() {
      http
        .post(`/qna`, {
          userid: this.qna.userid,
          subject: this.qna.subject,
          content: this.qna.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyQna() {
      http
        .put(`/qna`, {
          qnano: this.qna.qnano,
          userid: this.qna.userid,
          subject: this.qna.subject,
          content: this.qna.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style>
</style>