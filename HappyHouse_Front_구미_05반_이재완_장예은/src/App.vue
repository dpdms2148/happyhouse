<template>
  <div id="app">
    <the-header-navbar></the-header-navbar>
    <router-view></router-view>
    <AppFooter></AppFooter>
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";
import AppFooter from "@/components/AppFooter";

export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    AppFooter,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

a.router-link-exact-active {
  color: #89bfef;
}

a.router-link-exact-active:hover {
  text-decoration: none;
}
</style>
