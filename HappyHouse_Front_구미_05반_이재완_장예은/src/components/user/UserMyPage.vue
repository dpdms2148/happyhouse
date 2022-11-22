<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-table-simple hover>
          <colgroup>
            <col style="width: 20%" />
            <col style="" />
            <col style="width: 25%" />
          </colgroup>
          <b-tbody>
            <b-tr>
              <b-th>아이디</b-th>
              <b-td>{{ userInfo.userid }}</b-td>
              <b-td></b-td>
            </b-tr>

            <b-tr>
              <b-th scope="row">이름</b-th>
              <b-td>{{ userInfo.username }}</b-td>
              <b-td></b-td>
            </b-tr>

            <b-tr v-show="isPwdShow">
              <b-th scope="row">비밀번호</b-th>
              <b-td>********</b-td>
              <b-td>
                <b-button
                  variant="outline-dark"
                  size="sm"
                  @click="isPwdShow = !isPwdShow"
                  block
                >
                  비밀번호 변경
                </b-button>
              </b-td>
            </b-tr>

            <b-tr v-show="!isPwdShow">
              <b-th scope="row">비밀번호</b-th>
              <b-td>
                <b-input
                  id="userpwd"
                  v-model="user.userpwd"
                  type="password"
                  placeholder="변경할 비밀번호를 입력해주세요"
                ></b-input>
              </b-td>
              <b-td>
                <b-button
                  variant="light"
                  type="button"
                  @click="isPwdShow = !isPwdShow"
                  class="m-1"
                  size="sm"
                  style="width: 40%"
                  >취소</b-button
                >
                <b-button
                  variant="dark"
                  type="submit"
                  class="m-1"
                  size="sm"
                  @click="modifyUserinfo"
                  style="width: 40%"
                  >완료</b-button
                >
              </b-td>
            </b-tr>

            <b-tr v-show="isEmailShow">
              <b-th scope="row">이메일</b-th>
              <b-td>{{ userInfo.email }}</b-td>
              <b-td>
                <b-button
                  variant="outline-dark"
                  size="sm"
                  @click="isEmailShow = !isEmailShow"
                  block
                  >이메일 변경</b-button
                >
              </b-td>
            </b-tr>

            <b-tr v-show="!isEmailShow">
              <b-th scope="row">이메일</b-th>
              <b-td>
                <b-input
                  id="email"
                  v-model="user.email"
                  type="email"
                  placeholder="변경할 이메일을 입력해주세요"
                ></b-input>
              </b-td>
              <b-td>
                <b-button
                  variant="light"
                  type="button"
                  @click="isEmailShow = !isEmailShow"
                  class="m-1"
                  size="sm"
                  style="width: 40%"
                  >취소</b-button
                >
                <b-button
                  variant="dark"
                  type="submit"
                  class="m-1"
                  size="sm"
                  @click="modifyUserinfo"
                  style="width: 40%"
                  >완료</b-button
                >
              </b-td>
            </b-tr>

            <b-tr>
              <b-th scope="row">가입일</b-th>
              <b-td>{{ userInfo.joindate }}</b-td>
              <b-td></b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
        <b-link @click="deleteUserinfo" style="color: gray">탈퇴하기</b-link>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { modifyUser, deleteUser } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      isPwdShow: true,
      isEmailShow: true,
      user: {
        userpwd: "",
        email: "",
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    modifyUserinfo() {
      let param = {
        userid: this.userInfo.userid,
        userpwd:
          this.user.userpwd === "" ? this.userInfo.userpwd : this.user.userpwd,
        email: this.user.email === "" ? this.userInfo.email : this.user.email,
      };
      modifyUser(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.go();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    deleteUserinfo() {
      let userid = this.userInfo.userid;
      deleteUser(
        userid,
        ({ data }) => {
          let msg = "회원탈퇴 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "탈퇴가 완료되었습니다.";
            this.userLogout(userid);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
          }
          alert(msg);
          if (this.$route.path != "/") this.$router.push({ name: "main" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scope>
table {
  text-align: left;
  border: 20px;
}
</style>
