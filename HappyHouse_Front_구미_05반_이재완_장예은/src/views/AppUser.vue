<template>
  <b-container fluid class="px-0"> 
    <div>
      <b-img :src="require('@/assets/happyhouse.png')" fluid alt="res image"></b-img>
    </div>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppUser",
};
</script>

<style>
</style>
