<template>
  <b-container>
    <hr style="border: solid 1px" />
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group label="답변" label-for="answer" label-cols-lg="1">
            <b-form-textarea
              id="answer"
              ref="answer"
              v-model="qna.answer"
              rows="10"
            ></b-form-textarea>
          </b-form-group>
          <hr />
          <b-button
            type="submit"
            variant="outline-info"
            class="m-1 float-right"
            squared
            v-if="this.type === 'register'"
          >
            답변등록
          </b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { writeQnaAnswer, modifyQnaAnswer } from "@/api/qna";

export default {
  name: "QnaAnswerWrite",
  data() {
    return {
      qna: {
        //       qnano: 0,
        //       userid: '',
        //       subject: '',
        //       content: '',
        answer: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.qna.answer &&
        ((msg = "답변 입력해주세요"), (err = false), this.$refs.answer.focus());

      if (!err) this.$alert(msg, "Warning", "warning");
      else {
        this.type === "register"
          ? this.registQnaAnswer()
          : this.modifyQnaAnswer();
      }
    },
    onReset(event) {
      event.preventDefault();
      this.qna.answer = "";
    },
    registQnaAnswer() {
      let param = {
        qnano: this.$route.params.qnano,
        answer: this.qna.answer,
      };
      writeQnaAnswer(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            this.$alert(msg, "Success", "success");
          } else {
            this.$alert(msg, "Error", "error");
          }
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyQnaAnswer() {
      let param = {
        qnano: this.$route.params.qnano,
        answer: this.qna.answer,
      };
      modifyQnaAnswer(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
            this.$alert(msg, "Success", "success");
          } else {
            this.$alert(msg, "Error", "error");
          }
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style></style>
