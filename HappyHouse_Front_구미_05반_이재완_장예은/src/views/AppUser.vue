<template>
  <b-container fluid class="px-0">
    <div>
      <b-img
        :src="require('@/assets/happyhousesub.png')"
        alt="res image"
      ></b-img>
    </div>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppUser",
};
</script>

<style scoped>
div {
  position: relative;
}
img {
  width: 100%;
}
</style>
