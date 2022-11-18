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
import {mapState} from "vuex";

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
      map:null,
      marker:null,
      markers:[],
      infowindow:null,
      infowindows:[],
      locPosition:null,
    };
  },
  computed:
  {
    ...mapState("houseStore",{house: "house"})
  },
  methods:
  {
    initMap()
    {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition((position)=>{
          var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

          this.locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를
															// geolocation으로 얻어온
															// 좌표로 생성합니다 
          const container=document.getElementById("map");
          const options={
            center: this.locPosition,
            level:5,
          };
          this.map=new kakao.maps.Map(container, options);
          this.displayMarker(this.locPosition);
        });
    },
    displayMarker(locPosition)
    {
      console.log("displayMarker");
      console.log(locPosition);
      // 마커를 생성합니다
        this.marker = new kakao.maps.Marker({
          map: this.map,
          position: locPosition,
        });

        this.infowindow = new kakao.maps.InfoWindow({
          content: '<div style="width:150px;text-align:center;padding:6px 0;">내 위치</div>',
        });
        this.infowindow.open(this.map, this.marker);
        this.infowindows.push(this.infowindow);
        this.markers.push(this.marker);
        // 지도 중심좌표를 접속위치로 변경합니다
        this.map.setCenter(locPosition);
    }
    
  },
  
  mounted() {
    if(!window.kakao||!window.kakao.maps)
    {
      const script=document.createElement("script");
      const AppKey=decodeURIComponent(process.env.VUE_APP_KAKAOMAP_KEY);

      script.src=`//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${AppKey}`;
    
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
