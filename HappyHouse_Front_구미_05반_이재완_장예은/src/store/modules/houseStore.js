import { sidoList, gugunList, dongList, yearList, monthList, houseList } from "@/api/house.js";
//import Flatted from 'flatted';
import {parse, stringify, toJSON, fromJSON} from 'flatted';

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
    marker: null,
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
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_MARKER_AND_INFO(state, data)
    {
      console.log(data);
      //state.kakao=data["kakao"];
      //console.log(JSON.parse(JSON.stringify(state.kakao)));
      //state.map=data["map"];
      //console.log(JSON.parse(JSON.stringify(state.map)));
      //state.locPosition=data["locPosition"];
      // Object.keys(data).forEach((key)=>{ 
      //   if(key=="marker")
      //   {
      //     state.marker=data[key];
      //   }
      // });
      //state.marker=Object.assign({},data.marker);
      //JSON.parse(JSON.stringify(data.marker));
      console.log(data.marker);
      var k=null;
      console.log(parse(stringify(data.marker)));
      k=parse(stringify(data.marker));
      console.log(k);
      state.marker=parse(stringify(toJSON((k))));
      console.log(state.marker);
      //console.log(JSON.parse(JSON.stringify(state.marker)));
      //state.markers.push(state.marker);
      //state.infowindow=data["infowindow"];
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
    displayMarker({commit},params)
    {
      console.log("displayMarker");
      var data=
          {
            kakao: params.kakao,
            map: params.map,
            locPosition:params.locPosition,
            marker: null,
            infowindow: null,
          };

      console.log(data.locPosition);
      // 마커를 생성합니다
        //var markers=[];
        data.marker = new data.kakao.maps.Marker({
          map: data.map,
          position: data.locPosition,
        });
        //var infowindows=[];
        data.infowindow = new data.kakao.maps.InfoWindow({
          content: '<div style="width:150px;text-align:center;padding:6px 0;">내 위치</div>',
        });
        data.infowindow.open(data.map, data.marker);
        //infowindows.push(infowindow);
        //markers.push(marker);
        // 지도 중심좌표를 접속위치로 변경합니다
        data.map.setCenter(data.locPosition);
        console.log(data);

        commit("SET_MARKER_AND_INFO",data);
    }
  },
};

export default houseStore;
