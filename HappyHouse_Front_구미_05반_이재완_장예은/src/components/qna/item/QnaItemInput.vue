<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit">
        <hr />
        <b-form-group
          id="userid-group"
          label="작성자"
          label-for="userid"
          label-cols-lg="1"
        >
          <b-form-input
            id="userid"
            v-model="userInfo.userid"
            type="text"
            readonly
          ></b-form-input>
        </b-form-group>
        <hr />
        <b-form-group
          id="subject-group"
          label="제목"
          label-for="subject"
          label-cols-lg="1"
        >
          <b-form-input
            id="subject"
            ref="subject"
            v-model="qna.subject"
            type="text"
          ></b-form-input>
        </b-form-group>
        <hr />
        <b-form-group
          id="content-group"
          label="내용"
          label-for="content"
          label-cols-lg="1"
        >
          <b-form-textarea
            id="content"
            ref="content"
            v-model="qna.content"
            rows="10"
          ></b-form-textarea>
        </b-form-group>
        <hr />
        <b-button variant="outline-dark" class="m-1" @click="moveList" squared>
          목록
        </b-button>
        <b-button
          type="submit"
          variant="outline-info"
          class="m-1 float-right"
          squared
          v-if="this.type === 'register'"
        >
          등록
        </b-button>
        <b-button
          type="submit"
          variant="outline-info"
          class="m-1 float-right"
          squared
          v-else
        >
          수정
        </b-button>
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
      let err = true;
      let msg = "";
      !this.qna.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.qna.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) this.$alert(msg, "Warning", "warning");
      else {
        this.qna.userid = this.userInfo.userid;
        this.type === "register" ? this.registQna() : this.modifyQna();
      }
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
