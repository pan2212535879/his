<template>
  <div>
    <el-card class="card">
      <el-input placeholder="请输入挂号ID" class="my_el_input" v-model="query_registID">
        <el-button slot="append" icon="el-icon-search" @click="doQuery"></el-button>
      </el-input>
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
            prop="medicalID"
            label="病例ID"
            width="80">
        </el-table-column>
        <el-table-column
            prop="registID"
            label="挂号ID"
            width="100">
        </el-table-column>
        <el-table-column
            prop="objective"
            label="目的要求"
            width="80">
        </el-table-column>
        <el-table-column
            prop="CreationTime"
            label="开立时间"
            width="155">
        </el-table-column>
        <el-table-column
            prop="name"
            label="项目名称"
            width="120">
        </el-table-column>
        <el-table-column
            prop="checkOperID"
            label="检查人员ID"
            width="120">
        </el-table-column>
        <el-table-column
            prop="doctorID"
            label="开立医生ID"
            width="120">
        </el-table-column>
        <el-table-column
            prop="position"
            label="检查部位"
            width="120">
        </el-table-column>
        <el-table-column
            prop="state"
            label="状态"
            :formatter="isstate"
            width="120">

        </el-table-column>
        <el-table-column label="操作" width="100px">
          <template slot-scope="scope">
            <el-button type ="warning"  @click="doGet(scope.row)" plain>录入</el-button>
          </template>
        </el-table-column>

      </el-table>
    </el-card>
    <el-dialog
        title="发票信息"
        :visible.sync="dialogUpdate"
        width="45%"
        center>
      <el-form
          ref="invoiceInFormation"
          :model="checkApplyInFormation"
          label-width="150px"
          :rules="invoiceRules">
        <el-form-item label="ID:" prop="invoiceNum">
          <el-input class="box-card" v-model="checkApplyInFormation.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="病例ID:" prop="money">
          <el-input class="box-card" v-model="checkApplyInFormation.medicalID" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="挂号ID:" prop="state">
          <el-input class="box-card" v-model="checkApplyInFormation.registID" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="目的要求:">
          <el-input class="box-card" v-model="checkApplyInFormation.objective" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="检查部位:">
          <el-input class="box-card" v-model="checkApplyInFormation.position" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="开立医生ID:">
          <el-input class="box-card" v-model="checkApplyInFormation.doctorID" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="开立时间:">
          <el-input class="box-card" v-model="checkApplyInFormation.CreationTime" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="检查人员ID:" prop="back">
          <el-input class="box-card" v-model="checkApplyInFormation.checkOperID" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="结果:" prop="result">
          <el-input class="box-card" v-model="checkApplyInFormation.result" placeholder="结果"></el-input>
        </el-form-item>
      </el-form>
      <!--      <template slot-scope="scope">-->
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogUpdate = false">取 消</el-button>
            <el-button type="primary" @click="doUpdate()">录入</el-button>
        </span>
      <!--      </template>-->
    </el-dialog>
  </div>
</template>

<script>
export default {
  data(){
    return {
      list: [],
      invoice: [],
      query_registID:'',
      checkApplyInFormation: {
        id: '',
        medicalID: '',
        registID: '',
        objective: '',
        state: '',
        CreationTime:'',
        name:'',
        checkOperID:'',
        doctorID:'',
        position:'',
        Result:'',
      },
      dialogUpdate: false,
      temp:'',
      invoiceRules: {
        result: [{required: true, message: '请输入检查结果', trigger: 'blur'}],
      }
    }
  },
  created() {
    this.doQuery();
  },
  mounted() {
    this.initData();
  },
  methods:{
    initData(){
      this.$axios.post('/input/selectAllCheckApply').then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    isstate(row, column) {
      if(row[column.property] === 1){
        return '暂存'
      } else if(row[column.property] === 2){
        return  '已开立'
      }else if(row[column.property] === 3){
        return  '已交费'
      }else if(row[column.property] === 4){
        return  '已登记'
      }else if(row[column.property] === 5){
        return  '执行完'
      }else if(row[column.property] === 6){
        return  '已退费'
      } else if(row[column.property] === 7){
        return  '已作废'
      }
    },
    doQuery(){
      this.$axios.post(`/input/selectAllCheckApply?RegistID=${this.query_registID}`).then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
      this.initData();
    },
    doUpdate(){
      console.log(this.checkApplyInFormation);
      // this.$refs['checkApplyInFormation'].validate(valid => {
      //   if(valid){
          this.$axios.post('/input/updateCheckApply', this.checkApplyInFormation).then((resp) => {
            console.log('checkApplyInFormation=', this.checkApplyInFormation);
            this.initData();
          }).catch((err) => {
            console.log("err", err);
          })
          this.dialogUpdate = false;
          this.initData();
      //   }
      // })
    },
    doGet(data){
      this.dialogUpdate=true;
      console.log(data);
      this.invoice = data;
      this.checkApplyInFormation = data;
    },
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
