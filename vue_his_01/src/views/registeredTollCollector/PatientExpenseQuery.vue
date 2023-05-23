<template>
  <div>
    <el-card class="card">
      <el-card class="box-card">
        <div class="search-box">
          <el-form :model="addForm" :inline="true">
            <el-input placeholder="请输入病历ID"
                      style="width: 20%;"
                      v-model="addForm.caseNumber"
                      @clear="doQuery()">
            </el-input>
            <span class="demonstration"></span>
            <el-date-picker
                v-model="addForm.startTime"
                type="date"
                @clear="doQuery()"
                placeholder="选择起始日期">
            </el-date-picker>
            <span class="demonstration"></span>
            <el-date-picker
                v-model="addForm.endTime"
                type="date"
                @clear="doQuery()"
                placeholder="选择结束日期">
            </el-date-picker>

            <el-button  icon="el-icon-search"
                        @click="doQuery()">
            </el-button>
          </el-form>
        </div>

        <el-table :data = "costsList"
                  border
                  stripe>
          <el-table-column label="ID" prop="id" width="60"></el-table-column>
          <el-table-column label="病历号" prop="caseNumber" width="80"></el-table-column>
          <el-table-column label="发票ID" prop="invoiceID" width="80"></el-table-column>
          <el-table-column label="患者姓名" prop="realName" width="80"></el-table-column>
          <el-table-column label="项目类型" prop="itemType" width="80">
            <template slot-scope="scope">
              <span v-if="scope.row.itemType === 1">非药品</span>
              <span v-if="scope.row.itemType === 2">药品</span>
            </template>
          </el-table-column>
          <el-table-column label="项目名称" prop="name"></el-table-column>
          <el-table-column label="项目单价" prop="price" width="80"></el-table-column>
          <el-table-column label="项目科室" prop="deptName" width="100"></el-table-column>
          <el-table-column label="数量" prop="amount" width="60"></el-table-column>
          <el-table-column label="开立时间" prop="createtime" width="160"></el-table-column>
          <el-table-column label="收、退费时间" prop="payTime" width="160"></el-table-column>
          <el-table-column label="收费方式" prop=constantName"></el-table-column>
          <el-table-column label="是否退费" prop="backID">
            <template slot-scope="scope">
              <span v-if="scope.row.backID === null">未退费</span>
              <span v-if="scope.row.backID === 1">退费</span>
            </template>
          </el-table-column>

        </el-table>
      </el-card>
    </el-card>

  </div>
</template>

<script>
import moment from "moment";

export default {
  data() {
    return {
      costsList: [],
      addForm:{
        id: '',
        registID:'',
        invoiceID: '',
        caseNumber:'',
        itemType:'',
        realName:'',
        name :'',
        price : '',
        amount :'',
        deptName:'',
        createtime:'',
        payTime: '',
        constantName:'',
        backID:'',
        startTime:'',
        endTime:''
      },
    }
  },

  created() {
    this.doQuery()
  },

  mounted(){
    this.costsList = this.$route.query.costsList;
  },

  methods: {
    doQuery(){
      let url='/registeredTollCollector/patientExpenseQuery/selectAll';
      let params = this.$qs.stringify(this.addForm);
      // console.log("开始时间",this.addForm.startTime);
      console.log('testtesttest')
      console.log(params)
      this.$axios.post(url,params)
          .then( (resp)=>{
            console.log("我是resp:",resp)
            this.costsList=resp.data.data;
          } )
          .catch( (err)=>{
            console.log('error=', err);
          } )
    },

  }
}
</script>

<style scoped="scoped">
.card {
  background: #ececec;
  border:1px solid;
}
</style>

