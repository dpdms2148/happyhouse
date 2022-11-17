<template>
  <b-container>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyQna"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteQna"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${qna.qnano}.
          ${qna.subject} [${qna.hit}]</h3><div><h6>${qna.userid}</div><div>${qna.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
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
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
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