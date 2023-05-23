<template>
  <div id="clock"></div>
</template>

<script>
import * as echarts from "echarts";

export default {
  name: "clock",
  methods: {
    myClockEcharts() {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('clock'));
      const option = {
        series: [
          //小时
          {
            name: 'hour',
            type: 'gauge',
            startAngle: 90,
            endAngle: -270,
            min: 0,
            max: 12,
            splitNumber: 12,
            clockwise: true,
            axisLine: {
              lineStyle: {
                width: 8,
                color: [[1, 'rgba(0,0,0,0.7)']],
                shadowColor: 'rgba(0, 0, 0, 0.5)',
                shadowBlur: 15
              }
            },
            splitLine: {
              lineStyle: {
                shadowColor: 'rgba(0, 0, 0, 0.3)',
                shadowBlur: 3,
                shadowOffsetX: 1,
                shadowOffsetY: 2
              }
            },
            axisLabel: {
              fontSize: 10,
              distance: 15,
              formatter: function (value) {
                if (value === 0) {
                  return '';
                }
                return value + '';
              }
            },
            pointer: {
              icon: 'path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z',
              width: 6,
              length: '55%',
              offsetCenter: [0, '8%'],
              itemStyle: {
                color: '#C0911F',
                shadowColor: 'rgba(0, 0, 0, 0.3)',
                shadowBlur: 8,
                shadowOffsetX: 2,
                shadowOffsetY: 4
              }
            },
            detail: {
              show: false
            },
            title: {
              offsetCenter: [0, '30%']
            },
            data: [
              {
                value: 0
              }
            ]
          },
          //分钟
          {
            name: 'minute',
            type: 'gauge',
            startAngle: 90,
            endAngle: -270,
            min: 0,
            max: 60,
            clockwise: true,
            axisLine: {
              show: false
            },
            splitLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              show: false
            },
            pointer: {
              icon: 'path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z',
              width: 4,
              length: '70%',
              offsetCenter: [0, '8%'],
              itemStyle: {
                color: '#C0911F',
                shadowColor: 'rgba(0, 0, 0, 0.3)',
                shadowBlur: 8,
                shadowOffsetX: 2,
                shadowOffsetY: 4
              }
            },
            anchor: {
              show: true,
              size: 20,
              showAbove: false,
              itemStyle: {
                borderWidth: 15,
                borderColor: '#C0911F',
                shadowColor: 'rgba(0, 0, 0, 0.3)',
                shadowBlur: 8,
                shadowOffsetX: 2,
                shadowOffsetY: 4
              }
            },
            detail: {
              show: false
            },
            title: {
              offsetCenter: ['0%', '-40%']
            },
            data: [
              {
                value: 0
              }
            ]
          },
          //秒
          {
            name: 'second',
            type: 'gauge',
            startAngle: 90,
            endAngle: -270,
            min: 0,
            max: 60,
            animationEasingUpdate: 'bounceOut',
            clockwise: true,
            axisLine: {
              show: false
            },
            splitLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              show: false
            },
            pointer: {
              icon: 'path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z',
              width: 2,
              length: '85%',
              offsetCenter: [0, '8%'],
              itemStyle: {
                color: '#C0911F',
                shadowColor: 'rgba(0, 0, 0, 0.3)',
                shadowBlur: 8,
                shadowOffsetX: 2,
                shadowOffsetY: 4
              }
            },
            anchor: {
              show: true,
              size: 15,
              showAbove: true,
              itemStyle: {
                color: '#C0911F',
                shadowColor: 'rgba(0, 0, 0, 0.3)',
                shadowBlur: 8,
                shadowOffsetX: 2,
                shadowOffsetY: 4
              }
            },
            detail: {
              show: false
            },
            title: {
              offsetCenter: ['0%', '-40%']
            },
            data: [
              {
                value: 0
              }
            ]
          }
        ]
      };
      setInterval(function () {
        const date = new Date();
        const second = date.getSeconds();
        const minute = date.getMinutes() + second / 60;
        const hour = (date.getHours() % 12) + minute / 60;
        option.animationDurationUpdate = 300;
        myChart.setOption({
          series: [
            {
              name: 'hour',
              animation: hour !== 0,
              data: [{value: hour}]
            },
            {
              name: 'minute',
              animation: minute !== 0,
              data: [{value: minute}]
            },
            {
              animation: second !== 0,
              name: 'second',
              data: [{value: second}]
            }
          ]
        });
      }, 1000);

      myChart.setOption(option);
    }
  },
  mounted() {
    this.myClockEcharts()
  }
}
</script>

<style scoped>
#clock {
  width: 200px;
  height: 200px;
  display: flex;
  float: right;
  background: white;
  border-radius: 90%;
  box-shadow: 0px 3px 7px 0px rgb(0 0 0 / 50%);
}
</style>