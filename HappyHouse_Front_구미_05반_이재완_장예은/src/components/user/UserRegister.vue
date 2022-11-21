<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit="onSubmit" @reset="onReset">
            <b-form-group label="아이디:" label-for="userid" label-cols-lg="3">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력"
                :state="idState"
                @keyup="idCheck"
                aria-describedby="input-live-feedback"
              ></b-form-input>
              <b-form-invalid-feedback id="input-live-feedback">
                이미 사용중인 아이디입니다.
              </b-form-invalid-feedback>
              <b-form-valid-feedback id="input-live-feedback">
                사용 가능한 아이디입니다.
              </b-form-valid-feedback>
            </b-form-group>

            <b-form-group
              label="비밀번호:"
              label-for="userpwd"
              label-cols-lg="3"
            >
              <b-form-input
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력"
                type="password"
              ></b-form-input>

              <b-form-input
                id="userpwdck"
                v-model="userpwdck"
                required
                placeholder="비밀번호 재입력"
                :state="pwdckState"
                aria-describedby="input-live-feedback"
                type="password"
                class="mt-2"
              ></b-form-input>
              <b-form-invalid-feedback id="input-live-feedback">
                비밀번호가 일치하지 않습니다.
              </b-form-invalid-feedback>
              <b-form-valid-feedback id="input-live-feedback">
                비밀번호가 일치합니다.
              </b-form-valid-feedback>
            </b-form-group>

            <b-form-group label-cols-lg="3" label="이메일:" label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                required
                placeholder="이메일 입력"
                type="email"
              ></b-form-input>
            </b-form-group>

            <b-form-group label-cols-lg="3" label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="이름 입력"
              ></b-form-input>
            </b-form-group>
            <b-button type="submit" variant="primary" class="m-1"
              >등록</b-button
            >
            <b-button type="reset" variant="danger" class="m-1">취소</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { registUser } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
  name: "UserRegister",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
      },
      userpwdck: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["isValidId"]),
    idState() {
      if (this.user.userid === "") {
        return null;
      }
    },
    pwdckState() {
      if (this.userpwdck === "") return null;
      return this.user.userpwd === this.userpwdck ? true : false;
    },
  },
  methods: {
    ...mapActions(memberStore, ["idValidCheck"]),
    async idCheck() {
      await this.idValidCheck(this.user.userid);
      console.log(this.isValidId);
    },
    onSubmit(event) {
      event.preventDefault();
      this.registUser();
    },
    onReset(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.userpwd = "";
      this.user.email = "";
      this.user.name = "";
      this.moveList();
    },
    registUser() {
      let param = {
        userid: this.user.userid,
        userpwd: this.user.userpwd,
        email: this.user.email,
        username: this.user.username,
      };
      registUser(
        param,
        ({ data }) => {
          let msg = "회원가입 중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원가입이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style>
</style>
