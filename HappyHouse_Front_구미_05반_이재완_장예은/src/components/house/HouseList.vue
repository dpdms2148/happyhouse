<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-table
          hover
          :items="houses"
          :fields="fields"
          @row-clicked="selectHouse"
        >
          <template #cell(apartmentName)="data">
            {{ data.item.apartmentName }}
          </template>
          <template #cell(area)="data"> {{ data.item.area }}㎡ </template>
          <template #cell(dealAmount)="data">
            {{
              (parseInt(data.item.dealAmount.replace(",", "")) * 10000) | price
            }}원
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
  <b-container v-else class="bv-example-row mt-3 align-self-center">
    <b-img
      :src="require('@/assets/happyhousemini.png')"
      alt="res image"
    ></b-img>
    <h3>Find Happy House!</h3>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      isColor: false,
      fields: [
        {
          key: "apartmentName",
          label: "아파트",
          sortable: false,
        },
        {
          key: "dong",
          label: "동",
          sortable: false,
        },
        {
          key: "area",
          label: "면적",
          sortable: true,
        },
        {
          key: "floor",
          label: "층",
          sortable: false,
        },
        {
          key: "dealAmount",
          label: "가격",
          sortable: true,
        },
      ],
      items: [],
      house: null,
    };
  },
  computed: {
    ...mapState(houseStore, { houses: "houses" }),
  },
  created() {
    this.items = this.houses;
  },
  methods: {
    ...mapActions(houseStore, ["displayMarker2"]),
    selectHouse(house) {
      this.displayMarker2(house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
img {
  width: 150px;
  height: 150px;
}
</style>
