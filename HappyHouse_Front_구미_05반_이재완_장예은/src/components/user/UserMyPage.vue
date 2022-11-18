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
          <b-tbody>
            <b-tr>
              <b-th>아이디</b-th>
              <b-td>{{ userInfo.userid }}</b-td>
              <b-td colspan="2"></b-td>
            </b-tr>

            <b-tr>
              <b-th scope="row">이름</b-th>
              <b-td>{{ userInfo.username }}</b-td>
              <b-td colspan="2">
                <b-button variant="outline-dark" size="sm">이름 변경</b-button>
              </b-td>
            </b-tr>

            <b-tr>
              <b-th scope="row">비밀번호</b-th>
              <b-td>********</b-td>
              <b-td colspan="2">
                <b-button variant="outline-dark" size="sm">
                  비밀번호 변경
                </b-button>
              </b-td>
            </b-tr>

            <b-tr>
              <b-th scope="row">이메일</b-th>
              <b-td>{{ userInfo.email }}</b-td>
              <b-td colspan="2">
                <b-button variant="outline-dark" size="sm">
                  이메일 변경
                </b-button>
              </b-td>
            </b-tr>

            <b-tr>
              <b-th scope="row">가입일</b-th>
              <b-td>{{ userInfo.joindate }}</b-td>
              <b-td colspan="2"></b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from 'vuex'

const memberStore = 'memberStore'

export default {
  name: 'UserMyPage',
  components: {},
  computed: {
    ...mapState(memberStore, ['userInfo']),
  },
}
</script>

<style>
table {
  text-align: left;
  border: 20px;
}
</style>
