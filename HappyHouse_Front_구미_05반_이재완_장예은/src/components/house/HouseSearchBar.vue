<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="sm-2">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-2">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-2">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="yearList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-2">
        <b-form-select
          v-model="dealYear"
          :options="years"
          @change="monthList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-2">
        <b-form-select
          v-model="dealMonth"
          :options="monthes"
          @change="searchApt"
        ></b-form-select>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      dealYear: null,
      dealMonth: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "years",
      "monthes",
      "houses",
    ]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getYear",
      "getMonth",
      "getHouseList",
      "displayMarker3",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_YEAR_LIST",
      "CLEAR_MONTH_LIST",
      "CLEAR_APT_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    yearList() {
      this.CLEAR_YEAR_LIST();
      this.dealYear = null;
      if (this.dongCode) this.getYear(this.dongCode);
    },
    monthList() {
      this.CLEAR_MONTH_LIST();
      this.dealMonth = null;
      if (this.dealYear)
        this.getMonth({ dongCode: this.dongCode, dealYear: this.dealYear });
    },
    searchApt() {
      console.log(this.dealYear + " " + this.dealMonth);
      if (this.dealMonth) {
        this.getHouseList({
          dongCode: this.dongCode,
          dealYear: this.dealYear,
          dealMonth: this.dealMonth,
        });
      }
    },
  },
};
</script>

<style></style>
