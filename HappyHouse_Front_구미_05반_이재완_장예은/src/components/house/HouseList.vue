<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <b-row class="m-2" style="background-color: lightgray;">
      <b-col cols="2" class="test-center align-self-center"><h3><b-icon icon="house-fill"></b-icon></h3></b-col>
      <b-col cols="2" class="test-center align-self-center"><h4>아파트</h4></b-col>
      <b-col cols="2" class="test-center align-self-center"><h4>행정동</h4></b-col>
      <b-col cols="2" class="test-center align-self-center"><h4>면적&emsp;</h4></b-col>
      <b-col cols="2" class="test-center align-self-center"><h4>층&emsp;</h4></b-col>
      <b-col cols="2" class="test-center align-self-center"><h4>거래금액&emsp;</h4></b-col>
    </b-row>
    <div style="width:100%; height:350px; overflow-y: scroll;">
    <house-list-item v-for="(house, index) in houses" :key="index" :house="house" />
    </div>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
  
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, {houses: "houses"}),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  
};
</script>

<style>
</style>
