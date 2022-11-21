<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange"><b-icon icon="house-fill"></b-icon> House Service</h3>
    <b-row>
      <b-col>      
        <div id="map"></div>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <house-list />
      </b-col>
      <b-col cols="6">
        <house-detail />
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import {mapActions} from "vuex";

export default {
  name: "AppHouse",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
  },
    data()
  {
    return{
    };
  },
  methods:
  {
    ...mapActions("houseStore",{
      //initMap: "initMap",
      displayMarker: "displayMarker",
    }),
    initMap()
    {
            // GeoLocation을 이용해서 접속 위치를 얻어옵니다
            console.log("init map");
            navigator.geolocation.getCurrentPosition((position)=>
            {
              var data=
              {
                kakao: kakao,
                map: null,
                locPosition:null,
              };
              var lat = position.coords.latitude, // 위도
              lon = position.coords.longitude; // 경도
              data.locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를
															// geolocation으로 얻어온
															// 좌표로 생성합니다 
              const container=document.getElementById("map");
              const options={
                center: data.locPosition,
                level:6,
              };
              data.map=new kakao.maps.Map(container, options);
              console.log(data.map);

              this.displayMarker({
                  map: data.map,
                  kakao: kakao, 
                  locPosition: data.locPosition
              });
            });
      }
  },
  
  mounted() {
    if(!window.kakao||!window.kakao.maps)
    {
      const script=document.createElement("script");
      const AppKey=decodeURIComponent(process.env.VUE_APP_KAKAOMAP_KEY);

      script.src=`//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${AppKey}&libraries=services`;

      /* global kakao */
      script.addEventListener("load",()=>
      {
        kakao.maps.load(this.initMap);
        console.log("loaded",kakao);  
      });
      document.head.appendChild(script);
    }
    else{
      console.log("이미 로딩됨: ",window.kakao);
      this.initMap();
      
    }
  },
};
</script>
<style scoped>
#map{
  width:1110px;
  height:400px;
}
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
