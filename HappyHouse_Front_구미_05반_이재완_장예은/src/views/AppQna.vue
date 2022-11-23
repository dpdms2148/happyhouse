<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div>
      <b-img :src="require('@/assets/happyhouse.png')" fluid alt="res image"></b-img>
    </div>
    <h2 class="mt-3">QnA</h2>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AppQna",
};
</script>
<style>
</style>
