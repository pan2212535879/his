<template>
  <div>
    <el-card class="card">
      <div class="search-box">
        <el-form :model="invoiceInFormation" :inline="true">
          <el-input placeholder="请输入收费员ID"
                    style="width: 200px"
                    v-model="invoiceInFormation.userID">
          </el-input>
          <span class="demonstration"></span>
          <el-date-picker
              v-model="invoiceInFormation.CreationTime"
              type="datetime"
              placeholder="选择日期时间">
          </el-date-picker>
          <span class="demonstration"></span>
          <el-button  icon="el-icon-search"
                      @click="doQuery()">
          </el-button>
        </el-form>
      </div>
      <el-table
          :data="list"
          height="600"
          border
          stripe
          style="width: 100%">
        <el-table-column
            prop="id"
            fixed
            label="ID"
            width="60">
        </el-table-column>
        <el-table-column
            prop="invoiceNum"
            label="发票号码"
            width="80">
        </el-table-column>
        <el-table-column
            prop="money"
            label="发票金额"
            width="100">
        </el-table-column>
        <el-table-column
            prop="state"
            label="发票状态"
            width="80">
        </el-table-column>
        <el-table-column
            prop="creationTime"
            label="收/退费时间"
            width="155">
        </el-table-column>
        <el-table-column
            prop="userID"
            label="收/退费人员ID"
            width="120">
        </el-table-column>
        <el-table-column
            prop="registID"
            label="挂号ID"
            width="100">
        </el-table-column>
        <el-table-column
            prop="feeType"
            label="收费方式"
            width="100">
        </el-table-column>
        <el-table-column
            prop="back"
            label="冲红发票号码"
            width="110">
        </el-table-column>
        <el-table-column
            prop="dailyState"
            label="发票状态"
            width="100">
        </el-table-column>
        <el-table-column label="日结" width="100">
          <template slot-scope="scope">
            <el-button type="success" @click="doDaily(scope.row)">日结</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import moment from 'moment';
export default {
  data() {
    return {
      list:[],
      start_time:'',
      end_time:'',
      get_time:'',
      invoiceInFormation:{
        id: '',
        invoiceNum: '',
        money: '',
        userID: '',
        state: '',
        registID: '',
        feeType: '',
        dailyState: '',
        CreationTime: '',
        back: '',
      }
    }
  },
  mounted() {
    this.doQuery();
  },
  methods:{
    getCurrentTime() {
      //获取当前时间并打印
      var _this = this;
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth()+1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      _this.gettime = yy+'/'+mm+'/'+dd+' '+hh+':'+mf+':'+ss;
      console.log(_this.gettime)
    },
    doQuery(){
      // this.start_time = moment(this.invoiceInFormation.createTime).format("yyyy-MM-dd HH:mm:ss");
      console.log("开始时间",this.invoiceInFormation.CreationTime);
      this.$axios.post('/daily/selectBydate?',this.invoiceInFormation).then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    doDaily(Data){
      this.$axios.post('/daily/updateInvoice?',Data).then( (resp)=> {
      }).catch( (err)=> {
        console.log("err",err);
      })
      this.doQuery()
    }
  }
}
</script>

<style scoped="scoped">
.card {
  background: #ececec;
  border:1px solid;
}
.box-card {
  width: 480px;
  height: 50px;
}
.input-with-select {
  background-color: #fff;
}

.my_el_input {
  width: 400px;
}
</style>
