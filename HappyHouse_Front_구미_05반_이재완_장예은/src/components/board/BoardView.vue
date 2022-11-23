<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="userInfo.userid === article.userid">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
        >
          글수정
        </b-button>
        <b-button variant="outline-danger" size="sm" @click="deleteArticle">
          글삭제
        </b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
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
// import moment from "moment";
import { getArticle, deleteArticle } from '@/api/board'
import { mapState } from 'vuex'

const memberStore = 'memberStore'

export default {
  name: 'BoardDetail',
  data() {
    return {
      article: {},
    }
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
    message() {
      if (this.article.content)
        return this.article.content.split('\n').join('<br>')
      return ''
    },
  },
  created() {
    let param = this.$route.params.articleno
    getArticle(
      param,
      ({ data }) => {
        this.article = data
      },
      (error) => {
        console.log(error)
      },
    )
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: 'boardmodify',
        params: { articleno: this.article.articleno },
      })
    },
    deleteArticle() {
      this.$confirm('정말로 삭제하시겠습니까?', 'Question', 'question').then(
        () => {
          let param = this.$route.params.articleno
          deleteArticle(
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
              this.$router.push({ name: 'boardlist' })
            },
            (error) => {
              console.log(error)
            },
          )
        },
      )
    },
    moveList() {
      this.$router.push({ name: 'boardlist' })
    },
  },
}
</script>

<style></style>
