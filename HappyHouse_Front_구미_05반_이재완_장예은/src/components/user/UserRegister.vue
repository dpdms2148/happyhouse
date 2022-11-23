<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="vue-tempalte">
          <b-form class="text-left" @submit="onSubmit" @reset="onReset">
            <h3 mt-3>Sign Up</h3>
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                ref="userid"
                v-model="user.userid"
                :state="this.useridck"
                @keyup="idCheck"
                aria-describedby="idinput-live-feedback"
              ></b-form-input>
              <b-form-invalid-feedback id="idinput-live-feedback">
                {{ msg }}
              </b-form-invalid-feedback>
              <b-form-valid-feedback id="idinput-live-feedback">
                사용 가능한 아이디입니다.
              </b-form-valid-feedback>
            </b-form-group>

            <b-form-group label="비밀번호" label-for="userpwd">
              <b-form-input
                id="userpwd"
                ref="userpwd"
                v-model="user.userpwd"
                type="password"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="비밀번호 재확인" label-for="userpwd">
              <b-form-input
                id="userpwdck"
                ref="userpwdck"
                v-model="userpwdck"
                :state="pwdckState"
                aria-describedby="pwdinput-live-feedback"
                type="password"
              ></b-form-input>
              <b-form-invalid-feedback id="pwdinput-live-feedback">
                비밀번호가 일치하지 않습니다.
              </b-form-invalid-feedback>
              <b-form-valid-feedback id="pwdinput-live-feedback">
                비밀번호가 일치합니다.
              </b-form-valid-feedback>
            </b-form-group>

            <b-form-group label="이메일" label-for="email">
              <b-form-input
                id="email"
                ref="email"
                v-model="user.email"
                type="email"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="이름" label-for="username">
              <b-form-input
                id="username"
                ref="username"
                v-model="user.username"
              ></b-form-input>
            </b-form-group>
            <b-button type="submit" class="btn-dark btn-block"
              >Sign Up</b-button
            >
            <p class="text-right mt-2 mb-4">
              Already have an account?
              <router-link
                :to="{ name: 'login' }"
                class="link"
                style="color: gray"
                >로그인</router-link
              >
            </p>
          </b-form>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { registUser, idCheck } from "@/api/member";

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
      useridck: null,
      msg: "",
    };
  },
  computed: {
    pwdckState() {
      if (this.userpwdck === "") return null;
      return this.user.userpwd === this.userpwdck ? true : false;
    },
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.userid &&
        ((msg = "아이디 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());
      err &&
        !this.userpwdck &&
        ((msg = "비밀번호 확인 입력해주세요"),
        (err = false),
        this.$refs.userpwdck.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일 입력해주세요"),
        (err = false),
        this.$refs.email.focus());
      err &&
        !this.user.username &&
        ((msg = "이름 입력해주세요"),
        (err = false),
        this.$refs.username.focus());

      if (!err) this.$alert(msg, "Warning", "warning");
      else {
        this.registUser();
      }
    },
    onReset(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.userpwd = "";
      this.user.email = "";
      this.user.name = "";
    },
    idCheck() {
      idCheck(
        this.user.userid,
        ({ data }) => {
          if (data === "success") {
            if (this.user.userid.length < 5) {
              this.useridck = false;
              this.msg = "아이디는 5자 이상이어야 합니다.";
            } else {
              this.useridck = true;
            }
          } else {
            this.useridck = false;
            this.msg = "이미 사용중인 아이디입니다.";
          }
        },
        (error) => {
          console.log(error);
        }
      );
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
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style>
.vue-tempalte {
  margin: auto;
  width: 50%;
  height: 100%;
}
</style>
