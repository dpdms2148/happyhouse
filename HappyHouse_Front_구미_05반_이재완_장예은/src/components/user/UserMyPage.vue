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
            <col style="width: 130px" />
            <col style="" />
            <col style="width: 140px" />
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
                  type="email"
                  placeholder="변경할 비밀번호를 입력해주세요"
                ></b-input>
                <b-button
                  variant="light"
                  type="button"
                  @click="isPwdShow = !isPwdShow"
                  class="m-1"
                  >취소</b-button
                >
                <b-button variant="dark" type="button" class="m-1"
                  >완료</b-button
                >
              </b-td>
              <b-td></b-td>
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
                  type="email"
                  placeholder="변경할 이메일을 입력해주세요"
                ></b-input>
                <b-button
                  variant="light"
                  type="button"
                  @click="isEmailShow = !isEmailShow"
                  class="m-1"
                  >취소</b-button
                >
                <b-button variant="dark" type="button" class="m-1"
                  >완료</b-button
                >
              </b-td>
              <b-td></b-td>
            </b-tr>

            <b-tr>
              <b-th scope="row">가입일</b-th>
              <b-td>{{ userInfo.joindate }}</b-td>
              <b-td></b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      isNameShow: true,
      isPwdShow: true,
      isEmailShow: true,
    };
  },
};
</script>

<style>
table {
  text-align: left;
  border: 20px;
}
</style>
