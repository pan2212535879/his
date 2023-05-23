<template>
  <div>
    <el-container>
      <User></User>
      <el-container direction="vertical">
        <div style="display: flex">
          <numberCard role="yiyuan" class="myCard" style="height: 90px"></numberCard>
          <numberCard role="yiyuan1" class="myCard" style="height: 90px"></numberCard>
          <numberCard role="yiyuanzixun" class="myCard" style="height: 90px"></numberCard>
        </div>
        <div style="display: flex">
          <numberCard role="yiyuan2" class="myCard" style="height: 90px"></numberCard>
          <numberCard role="yiyuanguanjia_yinshianpai" class="myCard" style="height: 90px"></numberCard>
          <numberCard role="yiyuanjieshaobeifen" class="myCard" style="height: 90px"></numberCard>
        </div>
      </el-container>
      <Clock style="margin-left: 20px;display: flex"></Clock>
    </el-container>
    <el-container>
      <PieChart></PieChart>
      <Rotation></Rotation>
    </el-container>
  </div>
</template>

<script>
import User from '../components/user'
import Clock from "../components/clock";
import NumberCard from "../components/NumberCard";
import Columnar from "../components/Columnar";
import PieChart from "../components/PieChart";
import Rotation from "../components/Rotation";

export default {
  name: "Index",

  components: {
    Clock,
    User,
    NumberCard,
    Columnar,
    PieChart,
    Rotation
  },

  data() {
    return {}
  }
}
</script>

<style scoped>
.myCard{
  margin-left: 10px;
  margin-bottom: 10px;
}

</style>