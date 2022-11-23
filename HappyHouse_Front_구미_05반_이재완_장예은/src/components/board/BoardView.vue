<template>
  <b-container class="bv-example-row mt-3">
    <hr/>
    <b-row class="mb-1">
      <b-col class="text-left">
        [{{article.articleno}}]
        <span class="title">{{ article.subject }}</span>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <div class="d-inline-block float-left">
          <i class="bx" :class="'bx-face' || 'bx-square-rounded'" />
          <span>{{ article.userid }}</span>
          |
          <i class="bx" :class="'bx-time-five' || 'bx-square-rounded'" />
          <span>{{ article.regtime }}</span>
        </div>
        <div class="d-inline-block float-right">
          <i class="bx" :class="'bx-show' || 'bx-square-rounded'" />
          <span>{{ article.hit }}</span>
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
      <b-col class="text-right" v-if="userInfo.userid === article.userid">
        <b-button
          variant="outline-info"
          @click="moveModifyArticle"
          class="mr-2"
          squared
        >
          수정
        </b-button>
        <b-button variant="outline-danger" @click="deleteArticle" squared>
          삭제
        </b-button>
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

<style>
.title{
  font-size: 30px;
}
</style>
