<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button v-if="userInfo == null" style="display: none"></b-button>
        <b-button v-else variant="outline-dark" @click="writeQna()">
          <i class="bx" :class="'bx-edit' || 'bx-square-rounded'"></i
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="qnas.length">
        <b-table
          striped
          hover
          :items="qnas"
          :fields="fields"
          @row-clicked="viewQna"
        >
          <template #cell(subject)="data">
            <router-link
              style="color: black"
              :to="{
                name: 'qnaview',
                params: { qnano: data.item.qnano },
              }"
            >
              {{ data.item.subject }}
            </router-link>
          </template>
          <template #cell(answer)="data">
            <b-button v-if="data.item.answer == null" variant="light" size="sm"
              >답변 대기</b-button
            >
            <b-button v-else variant="info" size="sm">답변 완료</b-button>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listQna } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "QnaList",
  data() {
    return {
      qnas: [],
      fields: [
        { key: "qnano", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
        { key: "answer", label: "상태", tdClass: "tdState" },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listQna(
      param,
      ({ data }) => {
        this.qnas = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    writeQna() {
      this.$router.push({ name: "qnawrite" });
    },
    viewQna(qna) {
      this.$router.push({
        name: "qnaview",
        params: { qnano: qna.qnano },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 230px;
  text-align: left;
}
.tdState {
  width: 70px;
  text-align: center;
}
</style>
