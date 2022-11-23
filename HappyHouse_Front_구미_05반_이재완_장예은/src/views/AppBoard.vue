<template>
  <b-container fluid class="px-0">
    <div>
      <b-img :src="require('@/assets/happyhouse.png')" fluid alt="res image"></b-img>
    </div>
    <h2 class="mt-3">Notice</h2>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AppBoard",
};
</script>
<style>
</style>
