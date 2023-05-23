<template>
  <div id="Columnar"></div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "Columnar",
  methods: {
    myColumnar(){
      const chartDom = document.getElementById('Columnar');
      const myChart = echarts.init(chartDom);
      let option;

      option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: 'Direct',
            type: 'bar',
            barWidth: '60%',
            data: [10, 52, 200, 334, 390, 330, 220]
          }
        ]
      };

      myChart.setOption(option);
    }
  },
  mounted() {
    this.myColumnar()
  }
}
</script>

<style scoped>
#Columnar{
  height: 500px;
  width: 1200px;
  margin-top: 15px;
  background: white;
  border-radius: 3px;
  box-shadow: 0px 3px 7px 0px rgb(0 0 0 / 50%);
}
</style>