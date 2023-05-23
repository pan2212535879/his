<template>
  <div id="number-card" :style="cardStyle">
    <div class="main">
      <span class="head-img">
        <i :class="headImg"></i>
      </span>
      <div class="text">
        <label class="number" :style="numberStyle">{{ num }}</label>
        <label class="explain">{{ exp }}</label>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/font_3063249_hpan52807f/iconfont.css'

export default {
  name: 'MyChart',
  data() {
    return {
      roleName: this.role,
      wh: this.w,
      num: this.number,
      exp: this.explain,
      numberColor: 'purple',
      headImg: 'iconfont icon-yiyuan',
      numberStyle: 'color: purple;',
      cardStyle: 'width: 61.8px; height: 100px;',
      cardData: {
        yiyuan: {
          headImg: 'iconfont icon-yiyuan',
          numberColor: '#496DC3'
        },
        yiyuanjieshaobeifen: {
          headImg: 'iconfont icon-yiyuanjieshaobeifen',
          numberColor: '#3C4C71'
        },
        yiyuan1: {
          headImg: 'iconfont icon-yiyuan1',
          numberColor: '#3C4C71'
        },
        yiyuan2: {
          headImg: 'iconfont icon-yiyuan2',
          numberColor: '#3C4C71'
        },
        yiyuanzixun: {
          headImg: 'iconfont icon-yiyuanzixun',
          numberColor: '#496DC3'
        },
        yiyuanguanjia_yinshianpai: {
          headImg: 'iconfont icon-ico_yiyuanguanjia_yinshianpai',
          numberColor: '#496DC3'
        }
      }
    }
  },
  props: {
    w: {
      type: String,
      default: '110'
    },
    number: {
      type: String,
      default: '1200'
    },
    explain: {
      type: String,
      default: '用户访问量'
    },
    role: {
      validator(value) {
        return ['yiyuan', 'yiyuan1', 'yiyuan2', 'yiyuanjieshaobeifen', 'yiyuanzixun', 'yiyuanguanjia_yinshianpai'].includes(value)
      },
      default: 'yiyaun'
    }
  },
  methods: {},
  mounted() {
    this.cardStyle = `width: ${this.wh * 2.115}px; height: ${this.wh * 2 / 5 * 1.9 + 2}px;`
    this.numberColor = this.cardData[this.roleName].numberColor
    this.numberStyle = `color: ${this.numberColor};`
    this.headImg = this.cardData[this.roleName].headImg
  }
}
</script>

<style scoped>
.main {
  position: relative;
  width: 100%;
  height: 100%;
  background-color: white;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 3px 7px 0px rgb(0 0 0 / 50%);
  border-radius: 10px;
}

.main:hover {
  background: rgba(255, 255, 255, 0.65);
  transform: scale(102%, 102%);
}

.head-img {
  margin-left: 20px;
  position: relative;
  top: 50%;
  transform: translateY(-50%);
  float: left;
}

.text {
  position: relative;
  left: 20px;
  top: 50%;
  transform: translateY(-50%);
}

.number {
  display: block;
  font-size: 24px;
  color: #496DC3;
  margin: 5px;
  font-weight: bold;
  text-shadow: 2px 2px 2px grey;
}

.explain {
  font-size: 15px;
  display: block;
  margin: 5px;
  color: #7b7b7b;
}

.iconfont{
  font-size: 80px;
}
</style>
