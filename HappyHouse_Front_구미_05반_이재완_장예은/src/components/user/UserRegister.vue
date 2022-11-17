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
            <b-form-group label-cols-lg="3" label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="Enter your id"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label-cols-lg="3"
              label="비밀번호:"
              label-for="userpw"
            >
              <b-form-input
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="Enter your password"
                type="password"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label-cols-lg="3"
              label="비밀번호 확인:"
              label-for="userpw"
            >
              <b-form-input
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="Check your password"
                type="password"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label-cols-lg="3" label="이메일:" label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                required
                placeholder="Enter your email"
                type="email"
              ></b-form-input>
            </b-form-group>
            <b-form-group label-cols-lg="3" label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="Enter your name"
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
export default {
  name: "UserRegister",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpw: "",
        email: "",
      },
    };
  },
  computed: {
    validation() {
      return this.userId.length > 4 && this.userId.length < 13;
    },
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.user.userid &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.user.password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      err &&
        !this.user.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      this.registUser();
    },
    onReset(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.userpw = "";
      this.user.email = "";
      this.user.name = "";
      this.moveList();
    },
  },
};
</script>

<style>
</style>
