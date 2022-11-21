import { sidoList, gugunList, dongList, yearList, monthList, houseList } from "@/api/house.js";
//import { HttpStatusCode } from "axios";

// 카카오 정보들 전역변수로 선언
var map = null;
var kakao = null;
var marker = null;
var markers = [];
var infowindow = null;
var infowindows = [];
const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    years:[{ value: null, text: "선택하세요" }],
    monthes:[{ value: null, text: "선택하세요" }],
    kakao: null,
    map: null,
    marker: marker,
    markers: [],
    infowindow: null,
    infowindows: [],
    locPosition: null,
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_YEAR_LIST(state) {
      state.years = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_MONTH_LIST(state) {
      state.monthes = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
      console.log(state.sidos);
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dong });
      });
    },
    SET_YEAR_LIST(state, years) {
      years.forEach((year) => {
        state.years.push({ value: year, text: year });
      });
    },
    SET_MONTH_LIST(state, monthes) {
      monthes.forEach((month) => {
        state.monthes.push({ value: month, text: month });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
      console.log(state.houses);
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_MARKER_AND_INFO(state, data)
    {
      map = data["map"];
      kakao=data["kakao"];

      marker=(data["marker"]);
      markers.push(marker);

      infowindow = (data["infowindow"]);
      infowindows.push(infowindow);
    }
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      console.log(params);
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = { gugun: gugunCode };
      console.log(params);
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getYear: ({ commit },dongCode) => {
      const params={dong:dongCode};
      yearList(
        params,
        ({ data }) => {
          commit("SET_YEAR_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getMonth: ({ commit },dealYear) => {
      const params={
        dong:dealYear.dongCode,
        deal_year:dealYear.dealYear,
      };
      monthList(
        params,
        ({ data }) => {
          commit("SET_MONTH_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, house) => {
      const params = {
        lawd_cd: house.dongCode,
        deal_year: house.dealYear,
        deal_month: house.dealMonth,
      };
      houseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
          //동에 대한 마커 전부 찍음
          for (var i = 0; i < markers.length; i++) markers[i].setMap(null);
          markers = [];
          for (i = 0; i < infowindows.length; i++) infowindows[i].close();

          var coords = new kakao.maps.LatLng(data[0].lat, data[0].lng);
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);

          data.forEach((house) => {
              if (kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(house.lat, house.lng);
                
                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
                });
                console.log(marker);
                markers.push(marker);
              }
          });
        },
        (error) => {
          console.log(error);
        }
      );
      
      
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    //초기 내 위치로 이동해서 마커 찍음
    displayMarker({commit},params)
    {
      var data=
          {
            kakao: params.kakao,
            map: params.map,
            locPosition:params.locPosition,
            marker: null,
            infowindow: null,
          };
      // 마커를 생성합니다
        data.marker = new data.kakao.maps.Marker({
          map: data.map,
          position: data.locPosition,
        });
        data.infowindow = new data.kakao.maps.InfoWindow({
          content: '<div style="width:150px;text-align:center;padding:6px 0;">내 위치</div>',
        });
        data.infowindow.open(data.map, data.marker);
        // 지도 중심좌표를 접속위치로 변경합니다
        data.map.setCenter(data.locPosition);

        commit("SET_MARKER_AND_INFO",data);
    },
    //특정 마커의 설명 띄움 (인포윈도우)
    displayMarker2({commit},house) {
      for (var i = 0; i < infowindows.length; i++) infowindows[i].close();
        // 정상적으로 검색이 완료됐으면 
        if ( kakao.maps.services.Status.OK) {
          console.log("success");
          var coords = new kakao.maps.LatLng(house.lat, house.lng);

          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords
          });
          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;"> '+ house.apartmentName + '</div>'
          });
          infowindow.open(map, marker);
          infowindows.push(infowindow);
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
        }
    },
  },
};

export default houseStore;
