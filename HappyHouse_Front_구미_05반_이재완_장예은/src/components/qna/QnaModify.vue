<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>QnA 수정</h3></b-alert>
      </b-col>
    </b-row>
    <qna-item-input type="modify" />
  </b-container>
</template>

<script>
import QnaItemInput from "@/components/qna/item/QnaItemInput";

export default {
  name: "QnaModify",
  components: {
    QnaItemInput,
  },
};
</script>

<style></style>
