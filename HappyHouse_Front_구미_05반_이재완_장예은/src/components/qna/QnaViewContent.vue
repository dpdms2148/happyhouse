<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="userInfo.userid === qna.userid">
        <b-button variant="outline-info" @click="moveModifyQna" class="mr-2">
          글수정
        </b-button>
        <b-button variant="outline-danger" @click="deleteQna">
          글삭제
        </b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${qna.qnano}.
          ${qna.subject} [${qna.hit}]</h3><div><h6>${qna.userid}</div><div>${qna.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from 'vuex'
import { deleteQna } from '@/api/qna'

const memberStore = 'memberStore'

export default {
  name: 'QnaViewContent',
  props: {
    qna: Object,
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
    message() {
      if (this.qna.content) return this.qna.content.split('\n').join('<br>')
      return ''
    },
  },
  methods: {
    moveModifyQna() {
      this.$router.replace({
        name: 'qnamodify',
        params: { qnano: this.qna.qnano },
      })
      //   this.$router.push({ path: `/qna/modify/${this.qna.qnano}` });
    },
    deleteQna() {
      this.$confirm('정말로 삭제하시겠습니까?', 'Question', 'question').then(
        () => {
          // this.$router.replace({
          //   name: "qnadelete",
          //   params: { articleno: this.qna.qnano },
          // });
          let param = this.$route.params.qnano
          deleteQna(
            param,
            ({ data }) => {
              let msg = '삭제 처리시 문제가 발생했습니다.'
              if (data === 'success') {
                msg = '삭제가 완료되었습니다.'
                this.$alert(msg, 'Success', 'success')
              } else {
                this.$alert(msg, 'Error', 'error')
              }
              // 현재 route를 /list로 변경.
              this.$router.push({ name: 'qnalist' })
            },
            (error) => {
              console.log(error)
            },
          )
        },
      )
    },
    moveList() {
      this.$router.push({ name: 'qnalist' })
    },
  },
}
</script>

<style></style>
