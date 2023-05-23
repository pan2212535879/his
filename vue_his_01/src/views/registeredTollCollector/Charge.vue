<template>
  <div>
    <el-card class="card">
      <el-input placeholder="请输入病例号" class="my_el_input" v-model="query_num">
        <el-button slot="append" icon="el-icon-search" @click="doQuery"></el-button>
      </el-input>
      <el-table
          :data="list"
          height="250"
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
            prop="caseNumber"
            label="病历号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="realName"
            label="姓名"
            width="100">
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别"
            :formatter="isGender"
            width="60">
        </el-table-column>
        <el-table-column
            prop="idnumber"
            label="身份证号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="deptID"
            label="挂号科室ID"
            width="100">
        </el-table-column>
        <el-table-column
            prop="visitDate"
            label="看诊日期"
            width="100">
        </el-table-column>
        <el-table-column
            prop="registTime"
            label="挂号日期"
            width="170">
        </el-table-column>
        <el-table-column
            prop="visitState"
            label="是否已诊"
            :formatter="whetherDiagnose"
            width="80">
        </el-table-column>
        <el-table-column
            prop="visitState"
            label="状态"
            :formatter="stateShift"
            width="100">
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button type ="primary"  @click="getCharge(scope.row)" round size="mini">显示账目</el-button>
            <el-button type ="success"  @click="doCharge(scope.row)" round size="mini" :disabled="disabledCharge">收费</el-button>
          </template>
        </el-table-column>
      </el-table>
      <br>
      <el-row :gutter="20">
        <el-col :span="12">
          <h1 style="margin-left: 40%">药品费账目</h1>
          <hr>
          <el-table
              :data="drugs_list"
              border
              stripe
              height="250">
            <el-table-column
                prop="drugsCode"
                fixed
                label="药品编号"
                width="140">
            </el-table-column>
            <el-table-column
                prop="drugsName"
                fixed
                label="药品名称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="drugsFormat"
                fixed
                label="药品规格"
                width="130">
            </el-table-column>
            <el-table-column
                prop="manufacturer"
                fixed
                label="生产产家"
                width="100">
            </el-table-column>
            <el-table-column
                prop="drugsPrice"
                fixed
                label="药品单价(元)"
                width="120">
            </el-table-column>
          </el-table>
        </el-col>
        <el-col :span="12">
          <h1 style="margin-left: 40%">非药品费账目</h1>
          <hr>
          <el-table
              :data="items_list"
              border
              stripe
              height="250">
            <el-table-column
                prop="itemCode"
                fixed
                label="项目编号"
                width="120">
            </el-table-column>
            <el-table-column
                prop="itemName"
                fixed
                label="项目名称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="format"
                fixed
                label="规格"
                width="120">
            </el-table-column>
            <el-table-column
                prop="recordType"
                fixed
                label="项目类型"
                :formatter="recordType"
                width="120">
            </el-table-column>
            <el-table-column
                prop="price"
                fixed
                label="单价(元)"
                width="120">
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-card>
    <el-dialog
        title="发票信息"
        :visible.sync="dialogInvoice"
        width="45%"
        center>
      <el-form
          ref="invoiceInFormation"
          :model="invoiceInFormation"
          label-width="150px">
        <el-form-item label="发票编号:">
          <el-card class="box-card" v-text="invoiceInFormation.invoiceNum">
          </el-card>
        </el-form-item>
        <el-form-item label="发票金额:">
          <el-card class="box-card" v-text="invoiceInFormation.money">
          </el-card>
        </el-form-item>
        <el-form-item label="发票状态:">
          <el-card class="box-card" v-text="invoiceInFormation.state">
          </el-card>
        </el-form-item>
        <el-form-item label="收/退费时间:">
          <el-card class="box-card" v-text="invoiceInFormation.creationTime">
          </el-card>
        </el-form-item>
        <el-form-item label="收/退费人员ID:">
          <el-card class="box-card" v-text="invoiceInFormation.userID">
          </el-card>
        </el-form-item>
        <el-form-item label="挂号ID:">
          <el-card class="box-card" v-text="invoiceInFormation.registID">
          </el-card>
        </el-form-item>
        <el-form-item label="收费方式:">
          <el-card class="box-card" v-text="invoiceInFormation.feeType">
          </el-card>
        </el-form-item>
        <el-form-item label="冲红发票号码:">
          <el-card class="box-card" v-text="invoiceInFormation.back">
          </el-card>
        </el-form-item>
        <el-form-item label="发票状态:">
          <el-card class="box-card" v-text="invoiceInFormation.dailyState">
          </el-card>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogInvoice = false">取 消</el-button>
        <el-button type="primary" @click="dialogInvoice = false">确 定</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Charge",
  data(){
    return{
      list:[],
      items_list:[],
      drugs_list:[],
      query_num:'',
      registerID:'',
      disabledCharge:true,
      dialogInvoice:false,
      invoiceInFormation: {
        id: '',
        invoiceNum: '',
        money: '',
        userID: '',
        state: '',
        registID: '',
        feeType: '',
        dailyState: '',
        createTime: '',
        back: '',
      },
    }
  },
  methods:{
    doQuery(){
      this.$axios.post(`/register_clerk/selectAll?CaseNumber=${this.query_num}`).then( (resp)=> {
        this.list = resp.data.data;
        this.registerID = this.list[0].ID;
        console.log("registerID",this.registerID);
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    getCharge(data){
      this.disabledCharge = false;
      console.log("data=",data);
      console.log(data.id);
      this.$axios.post(`/charge/allItem?ID=${data.id}`).then( (resp)=> {
        this.items_list = resp.data.data;
        console.log('items_list=',this.items_list);
      }).catch( (err)=> {
        console.log("err",err);
      })
      this.$axios.post(`/charge/allDrugs?ID=${data.id}`).then( (resp)=> {
        this.drugs_list = resp.data.data;
        console.log('drugs_list=',this.drugs_list);
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    doCharge(data){
      this.$axios.post(`/charge/allCharge?ID=${data.id}`).then( (resp)=> {
        this.invoiceInFormation = resp.data.data;
        console.log('invoiceInFormation=',this.invoiceInFormation);
      }).catch( (err)=> {
        console.log("err",err);
      })
      this.dialogInvoice = true;
    },
    isGender(row, column) {
      if(row[column.property] === 71){
        return '男'
      } else {
        return  '女'
      }
    },
    stateShift(row, column){
      if(row[column.property] === 1){
        return '已挂号'
      }else if(row[column.property] === 2){
        return '医生接诊'
      }else if(row[column.property] === 3){
        return '看诊结束'
      }else {
        return '已退号'
      }
    },
    whetherDiagnose(row, column){
      if(row[column.property] < 2) {
        return '否'
      } else {
        return '是'
      }
    },
    whetherBook(row, column){
      if(row[column.property] === '1') {
        return '是'
      }else {
        return '否'
      }
    },
    recordType(row, column){
      if(row[column.property] === 1) {
        return '检查';
      } else if(row[column.property] === 2) {
        return '检验';
      } else {
        return '处置';
      }
    }
  }
}
</script>

<style scoped="scoped">
.card {
  background: #ececec;
  border:1px solid;
}
.my_el_input {
  width: 400px;
}
.box-card {
  width: 480px;
  height: 50px;
}
</style>
