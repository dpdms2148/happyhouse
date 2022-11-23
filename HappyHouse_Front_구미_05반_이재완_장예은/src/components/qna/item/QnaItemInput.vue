<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            v-model="userInfo.userid"
            type="text"
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목:" label-for="subject">
          <b-form-input
            id="subject"
            v-model="qna.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

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
        >
          글작성
        </b-button>
        <b-button type="submit" variant="primary" class="m-1" v-else>
          글수정
        </b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeQna, modifyQna, getQna } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaInputItem",
  data() {
    return {
      qna: {
        qnano: 0,
        userid: "",
        subject: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.qnano;
      getQna(
        param,
        ({ data }) => {
          this.qna = data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.qna.userid = this.userInfo.userid;
      this.type === "register" ? this.registQna() : this.modifyQna();
    },
    onReset(event) {
      event.preventDefault();
      this.qna.qnano = 0;
      this.qna.subject = "";
      this.qna.content = "";
    },
    registQna() {
      let param = {
        userid: this.qna.userid,
        subject: this.qna.subject,
        content: this.qna.content,
      };
      writeQna(
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
    modifyQna() {
      let param = {
        qnano: this.qna.qnano,
        userid: this.qna.userid,
        subject: this.qna.subject,
        content: this.qna.content,
      };
      modifyQna(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
            this.$alert(msg, "Success", "success");
          } else {
            this.$alert(msg, "Error", "error");
          }
          // 현재 route를 /list로 변경.
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
