<template>
  <b-container fluid class="px-0">
    <div>
      <b-img
        :src="require('@/assets/happyhousesub.png')"
        fluid
        alt="res image"
      ></b-img>
      <h1 class="mt-3" style="color: white;">Notice</h1>
    </div>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: 'AppBoard',
}
</script>
<style scope>
div {
  position: relative;
}
h1 {
  position: absolute;
  top: 90%;
  left: 50%;
  width: 100%;
  transform: translate(-50%, -50%);
}
</style>
