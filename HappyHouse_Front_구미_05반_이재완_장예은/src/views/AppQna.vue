<template>
  <b-container fluid class="px-0">
    <div>
      <b-img
        :src="require('@/assets/happyhousesub.png')"
        alt="res image"
      ></b-img>
      <h1 class="mt-3" style="color: white">QnA</h1>
    </div>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AppQna",
};
</script>
<style scoped>
div {
  position: relative;
}
img {
  width: 100%;
}
h1 {
  position: absolute;
  top: 80%;
  left: 50%;
  width: 100%;
  transform: translate(-50%, -50%);
}
</style>
