<template>
  <b-container>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form>
          <b-form-group id="content-group" label="답변:" label-for="content">
            <b-form-textarea
              id="content"
              placeholder="답변 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1"
            >글작성</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "QnaAnswerWrite",
};
</script>

<style>
</style>