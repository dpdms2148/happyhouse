<template>
  <b-container fluid class="px-0 background">
    <b-row>
      <b-col ></b-col>
      <b-col cols="6" class="mt-5">
        <b-jumbotron  
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
        >
          <h3>아파트 최신 소식</h3>

          <b-jumbotron
            bg-variant="light"
            text-variant="dark"
            border-variant="dark"
          >
            <b-input-group size="sm" class="mb-2">
              <b-input-group-prepend is-text>
                <b-icon icon="search"></b-icon>
              </b-input-group-prepend>
              <b-form-input
                type="search"
                placeholder="기사 검색"
              ></b-form-input>
            </b-input-group>
            <b-table striped hover :items="items" @row-selected="onRowSelected">
              <div style="width: 100%; height: 350px; overflow: auto"></div>
            </b-table>
            <!-- <b-row border-variant="dark"><a href="https://n.news.naver.com/article/001/0013594370?sid=101">[1] 1인가구 늘고 금리 오르자 초소형 아파트 매입비중 역대 최고</a></b-row> -->
            <!--<b-row><a href="https://n.news.naver.com/article/243/0000034834?sid=101">[2] “나혼자 산다” 초소형 아파트 매매 비중 역대 최대</a></b-row>
        <b-row><a href="https://n.news.naver.com/article/009/0005049372?sid=101">[3] 부동산 얼어붙는데 이건 뜨네...매입비율 역대최고 ‘초소형 아파트’</a></b-row>
        <b-row><a href="https://n.news.naver.com/article/081/0003319280?sid=101">[4] 공시가 2020년 수준으로 낮춘다… 9억 이상 아파트 최대 수혜</a></b-row>
        <b-row><a href="https://n.news.naver.com/article/016/0002069339?sid=101">[5] 내년 아파트 공시가 현실화율 69%로…2020년 수준으로 낮춘다 [부동산360]</a></b-row> -->
          </b-jumbotron>
          <!-- </b-col> -->
        </b-jumbotron>

        <!-- </b-col> -->
      </b-col>
      <b-col ></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "AppMain",
  props: {
    msg: String,
  },
  data() {
    return {
      items: [
        { 제목: ` 1인가구 늘고 금리 오르자 초소형 아파트 매입비중 역대 최고` },
        { 제목: ` “나혼자 산다” 초소형 아파트 매매 비중 역대 최대` },
        {
          제목: ` 부동산 얼어붙는데 이건 뜨네...매입비율 역대최고 ‘초소형 아파트’`,
        },
        { 제목: ` 공시가 2020년 수준으로 낮춘다… 9억 이상 아파트 최대 수혜` },
        {
          제목: ` 내년 아파트 공시가 현실화율 69%로…2020년 수준으로 낮춘다 [부동산360]`,
        },
      ],
    };
  },
  methods: {
    onRowSelected(items) {
      console.log("ddd");
      console.log(items.제목);
    },
  },
};
</script>

<style scoped>
.background {
  height: 100vh;
  background: url("@/assets/happyhousemain.png") no-repeat center;
  background-size: cover;
  overflow: hidden;
}
</style>
