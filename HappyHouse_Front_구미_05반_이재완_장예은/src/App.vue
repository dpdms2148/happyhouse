<template>
  <div id="app">
    <the-sidebar-menu></the-sidebar-menu>
    <router-view></router-view>
  </div>
</template>

<script>
import TheSidebarMenu from "@/components/TheSidebarMenu";

export default {
  name: "App",
  components: {
    TheSidebarMenu,
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
