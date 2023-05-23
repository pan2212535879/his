<template>
  <div class="banner">
    <div class="item">
      <img :src=dataList[currentIndex]>
    </div>
    <div class="page" v-if="this.dataList.length > 1">
      <ul>
        <li @click="gotoPage(prevIndex)">&lt;</li>
        <li v-for="(item,index) in dataList" @click="gotoPage(index)" :class="{'current':currentIndex === index}">
          {{ index + 1 }}
        </li>
        <li @click="gotoPage(nextIndex)">&gt;</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "Rotation",
  data() {
    return {
      dataList: [
        require('../assets/img/img_01.jpg'),
        require('../assets/img/img_02.jpg'),
        require('../assets/img/img_03.jpg'),
        require('../assets/img/img_04.jpg')
      ],
      currentIndex: 0,   //默认显示图片
    }
  },
  methods: {
    gotoPage(index) {
      this.currentIndex = index;
    },
    //定时器
    runInv() {
      setInterval(() => {
        this.gotoPage(this.nextIndex)
      }, 2000)
    }
  },

  mounted() {
    this.runInv()
  },

  computed: {
    //上一张
    prevIndex() {
      if (this.currentIndex === 0) {
        return this.dataList.length - 1;
      } else {
        return this.currentIndex - 1;
      }
    },
    //下一张
    nextIndex() {
      if (this.currentIndex === this.dataList.length - 1) {
        return 0;
      } else {
        return this.currentIndex + 1;
      }
    }
  },
}
</script>

<style scoped>
img {
  border-radius: 3px;
  box-shadow: 0px 3px 7px 0px rgb(0 0 0 / 50%);
}

* {
  margin: 0;
  padding: 0;
}

ul li {
  list-style: none;
  float: left;
  width: 30px;
  height: 40px;
  line-height: 40px;
  text-align: center;
  cursor: pointer;
  color: rgba(255, 255, 255, .8);
  font-size: 14px;
}

.banner {
  max-width: 1200px;
  position: relative;
  margin-top: 15px;
  margin-left: 15px;
}

.banner img {
  width: 100%;
  display: block;
}

.banner .page {
  background: rgba(0, 0, 0, .5);
  position: absolute;
  right: 0;
  bottom: 0;
  width: 100%;
}

.banner .page ul {
  float: right;
}

.current {
  color: #ff6700;
}

</style>