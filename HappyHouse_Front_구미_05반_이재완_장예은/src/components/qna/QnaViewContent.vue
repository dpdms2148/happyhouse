<template>
  <b-container class="bv-example-row mt-3">
    <hr />
    <b-row>
      <b-col class="text-left">
        [{{ qna.qnano }}]
        <span class="title">{{ qna.subject }}</span>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <div class="d-inline-block float-left">
          <i class="bx" :class="'bx-face' || 'bx-square-rounded'" />
          <span>{{ qna.userid }}</span>
          |
          <i class="bx" :class="'bx-time-five' || 'bx-square-rounded'" />
          <span>{{ qna.regtime }}</span>
        </div>
        <div class="d-inline-block float-right">
          <i class="bx" :class="'bx-show' || 'bx-square-rounded'" />
          <span>{{ qna.hit }}</span>
        </div>
      </b-col>
    </b-row>
    <hr />
    <b-row class="mb-1">
      <b-col>
        <div v-html="message" class="text-left"></div>
      </b-col>
    </b-row>
    <hr />
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-dark" @click="moveList" squared>
          목록
        </b-button>
      </b-col>
      <b-col class="text-right" v-if="userInfo.userid === qna.userid">
        <b-button
          variant="outline-info"
          @click="moveModifyQna"
          class="mr-2"
          squared
        >
          수정
        </b-button>
        <b-button variant="outline-danger" @click="deleteQna" squared>
          삭제
        </b-button>
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

<style>
.title {
  font-size: 30px;
}
</style>
