<template>
  <b-container>
    <b-row class="mb-1">
      <b-col style="text-align: left;">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group id="content-group" label="답변:" label-for="content">
            <b-form-textarea
              id="content"
              placeholder="답변 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>
          <b-button type="submit" variant="outline-primary" class="m-1">
            답변등록
          </b-button>
          <b-button type="reset" variant="outline-danger" class="m-1">
            초기화
          </b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getQna, writeQnaAnswer, modifyQnaAnswer } from '@/api/qna'
export default {
  name: 'QnaAnswerWrite',
  data() {
    return {
      qna: {
        qnano: 0,
        answer: '',
      },
    }
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === 'modify') {
      let param = this.$route.params.qnano
      getQna(
        param,
        ({ data }) => {
          this.qna = data.answer
        },
        (error) => {
          console.log(error)
        },
      )
    }
  },
  methods: {
    onsubmit(event) {
      event.preventDefault()
      this.type === 'register' ? this.registQnaAnswer() : this.modifyQnaAnswer()
    },
    onReset(event) {
      event.preventDefault()
      this.qna.qnano = 0
      this.qna.answer = ''
    },
    registQnaAnswer() {
      let param = {
        answer: this.qna.answer,
      }
      writeQnaAnswer(
        param,
        ({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.'
          if (data === 'success') {
            msg = '등록이 완료되었습니다.'
          }
          alert(msg)
          this.moveList()
        },
        (error) => {
          console.log(error)
        },
      )
    },
    modifyQnaAnswer() {
      let param = {
        qnano: this.qna.qnano,
        answer: this.qna.answer,
      }
      modifyQnaAnswer(
        param,
        ({ data }) => {
          let msg = '수정 처리시 문제가 발생했습니다.'
          if (data === 'success') {
            msg = '수정이 완료되었습니다.'
          }
          alert(msg)
          this.moveList()
        },
        (error) => {
          console.log(error)
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
