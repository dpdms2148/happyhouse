<template>
  <b-container fluid class="px-0">
    <div>
      <b-img
        :src="require('@/assets/happyhousesm.png')"
        alt="res image"
      ></b-img>
      <h1 class="mt-3 text-white">Notice</h1>
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
img{
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
