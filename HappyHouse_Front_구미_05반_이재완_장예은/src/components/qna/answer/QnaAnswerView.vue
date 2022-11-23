<template>
  <b-container>
    <b-row class="mb-1">
      <b-col class="text-right">
        <!-- <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyQnaAns"
          class="mr-2"
        >
          답변수정
        </b-button> -->
        <b-button
          v-if="userInfo.userid === 'admin'"
          variant="outline-danger"
          size="sm"
          @click="deleteQnaAns"
        >
          답변삭제
        </b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>답변</h3><div><div>${qna.anstime}</div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <!-- <template #header>
            <h3 class="mb-0">답변</h3>
          </template> -->
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
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

<style></style>
