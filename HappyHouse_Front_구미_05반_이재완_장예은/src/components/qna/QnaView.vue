<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>QnA</h3></b-alert>
      </b-col>
      <qna-view-content :qna="qna"></qna-view-content>
      <qna-answer-view v-if="qna.answer != null" :qna="qna"></qna-answer-view>
      <qna-answer-write
        v-else-if="userInfo.userid === 'admin'"
        type="register"
      ></qna-answer-write>
    </b-row>
  </b-container>
</template>

<script>
import { getQna } from '@/api/qna'
import { mapState } from 'vuex'
import QnaViewContent from '@/components/qna/QnaViewContent'
import QnaAnswerView from '@/components/qna/answer/QnaAnswerView'
import QnaAnswerWrite from '@/components/qna/answer/QnaAnswerWrite'

const memberStore = 'memberStore'
export default {
  name: 'QnaView',
  components: {
    QnaViewContent,
    QnaAnswerView,
    QnaAnswerWrite,
  },
  data() {
    return {
      qna: {},
    }
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
  },
  created() {
    let param = this.$route.params.qnano
    getQna(
      param,
      ({ data }) => {
        this.qna = data
      },
      (error) => {
        console.log(error)
      },
    )
  },
}
</script>

<style></style>
