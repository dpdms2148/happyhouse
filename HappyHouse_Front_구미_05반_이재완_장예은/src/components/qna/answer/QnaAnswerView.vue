<template>
  <b-container>
    <div class="answer">
      <b-row class="mb-1">
        <b-col class="text-left">
          <span class="title"
            ><i
              class="bx"
              :class="'bx-message' || 'bx-square-rounded'"
            />답변</span
          >
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <div class="d-inline-block float-left">
            <i class="bx" :class="'bx-time-five' || 'bx-square-rounded'" />
            <span>{{ qna.anstime }}</span>
          </div>
        </b-col>
      </b-row>
      <hr />
      <b-row class="mb-1">
        <b-col>
          <div v-html="message" class="text-left"></div>
        </b-col>
      </b-row>
      <hr />
      <b-row class="mb-1" v-if="userInfo.userid === 'admin'">
        <b-col class="text-right">
          <b-button variant="outline-danger" @click="deleteQnaAns" squared>
            삭제
          </b-button>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import { deleteQnaAnswer } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "QnaViewContent",
  props: {
    qna: Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.qna.answer) return this.qna.answer.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    // moveModifyQnaAns() {
    //   this.$router.replace({
    //     name: 'qnamodify',
    //     params: { qnano: this.qna.qnano },
    //   })
    //   //   this.$router.push({ path: `/qna/modify/${this.qna.qnano}` });
    // },
    deleteQnaAns() {
      this.$confirm("정말로 삭제하시겠습니까?", "Question", "question").then(
        () => {
          let param = this.$route.params.qnano;
          deleteQnaAnswer(
            param,
            ({ data }) => {
              let msg = "삭제 처리시 문제가 발생했습니다.";
              if (data === "success") {
                msg = "삭제가 완료되었습니다.";
                this.$alert(msg, "Success", "success");
              } else {
                this.$alert(msg, "Error", "error");
              }
              // 현재 route를 /list로 변경.
              this.$router.push({ name: "qnalist" });
            },
            (error) => {
              console.log(error);
            }
          );
        }
      );
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style scoped>
.title {
  font-size: 30px;
}
.answer {
  padding: 20px;
  border-radius: 10px;
  background-color: rgb(240, 240, 240);
}
</style>
