<template>
<div id="PieChart"></div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "PieChart",
  methods: {
    myPieChart() {
      const chartDom = document.getElementById('PieChart');
      const myChart = echarts.init(chartDom);
      let option;

      option = {
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 735, name: 'Direct' +
                    '' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' },
              { value: 200, name: 'test Ads' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      myChart.setOption(option);
    }
  },
  mounted() {
    this.myPieChart()
  }
}
</script>

<style scoped>
#PieChart{
  height: 370px;
  width: 500px;
  margin-top: 15px;
  background: white;
  border-radius: 3px;
  box-shadow: 0px 3px 7px 0px rgb(0 0 0 / 50%);
}
</style>