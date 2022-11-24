<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="vue-tempalte">
          <b-form class="text-left">
            <h3 mt-3>Login</h3>
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>

            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >

            <b-button type="button" class="btn-dark btn-block" @click="confirm"
              >Login</b-button
            >
            <p class="text-right mt-2 mb-4">
              <router-link
                :to="{ name: 'join' }"
                class="link"
                style="color: gray"
                >회원가입</router-link
              >
            </p>
          </b-form>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "main" });
      }
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
