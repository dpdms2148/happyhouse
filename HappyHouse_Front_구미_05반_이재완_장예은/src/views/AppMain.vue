<template>
  <b-container class="bv-example-row mt-3 text-center">
    <!-- <b-img :src="require('@/assets/roof.jpg')" fluid-grow></b-img> -->
    <!-- <b-row><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img></b-row> -->
    <div :style="{'backgroundImage':'url(https://www.visitseoul.net/comm/getImage?srvcId=MEDIA&parentSn=24068&fileTy=MEDIA&fileNo=1)'}"> </div>
    <!-- <b-row><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img></b-row> -->
    <div :style="{'backgroundImage':'url(https://www.visitseoul.net/comm/getImage?srvcId=MEDIA&parentSn=24068&fileTy=MEDIA&fileNo=1)'}"> </div>
   
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-jumbotron bg-variant="muted" text-variant="dark" border-variant="dark">
          <h3>아파트 최신 소식</h3>
          <!-- <template #header>SSAFY Home</template> -->
          <!-- <template #lead>
            슬기로운 싸피 생활 (:8기편👍)<br />
            1학기동안 모두 고생 & 수고많으셨어요😍~~<br />
            2학기 가서도 행복한 하루 ⭐️ 하루 되길 바랄께요.<br />
            7전 8~~~~~~~~~~~~~~~~~~~~~~~끼를 보여주세요.🌈<br />
            그리고 각자 생각하는 곳에 취업!!! 꼭!꼭!꼭! 하세용.🐹
          </template> -->
          <!-- <hr class="my-4" /> -->
          <!-- <p>Vue + Bootstrap활용.</p>
          <p>Bootstrap-vue는 버전 <b>4.6.1</b>을 권장합니다.</p>
          <p><b>BoardList.vue</b>를 바꿔가면서 테스트하세요.</p>
          <p>Bootstrap의 <b>table</b> 사용법을 익히게됩니다.</p> -->
        <!-- <b-col> -->
          
          <b-jumbotron bg-variant="light" text-variant="dark" border-variant="dark">
            
            <b-input-group size="sm" class="mb-2">
      <b-input-group-prepend is-text>
        <b-icon icon="search"></b-icon>
      </b-input-group-prepend>
      <b-form-input type="search" placeholder="기사 검색"></b-form-input>
    </b-input-group>
            <b-table striped hover :items="items"  @row-selected="onRowSelected">
              <div style="width:100%; height:350px; overflow:auto"></div>
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
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: 'AppMain',
  props: {
    msg: String,
  },
  data()
  {
    return{
      items:[
      {제목: ` 1인가구 늘고 금리 오르자 초소형 아파트 매입비중 역대 최고`},
      {제목: ` “나혼자 산다” 초소형 아파트 매매 비중 역대 최대`},
      {제목: ` 부동산 얼어붙는데 이건 뜨네...매입비율 역대최고 ‘초소형 아파트’`},
      {제목: ` 공시가 2020년 수준으로 낮춘다… 9억 이상 아파트 최대 수혜`},
      {제목: ` 내년 아파트 공시가 현실화율 69%로…2020년 수준으로 낮춘다 [부동산360]`},
    ]
    }
  },
  methods: {
      onRowSelected(items) {
        console.log("ddd");
        console.log(items.제목);
      },
  }
};
</script>

<style scoped>

.background {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url('@/assets/happyhousemain.png');
  background-size: cover;
  background-position: center;
}
</style>
