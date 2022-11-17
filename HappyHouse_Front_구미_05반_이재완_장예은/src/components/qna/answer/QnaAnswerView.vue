<template>
  <b-container>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyQna"
          class="mr-2"
          >답변수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteQna"
          >답변삭제</b-button
        >
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
export default {
  name: "QnaViewContent",
  props: {
    qna: Object,
  },
  computed: {
    message() {
      if (this.qna.answer) return this.qna.answer.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    moveModifyQna() {
      this.$router.replace({
        name: "qnamodify",
        params: { qnano: this.qna.qnano },
      });
      //   this.$router.push({ path: `/qna/modify/${this.qna.qnano}` });
    },
    deleteQna() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "qnadelete",
          params: { qnano: this.qna.qnano },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style>
</style>