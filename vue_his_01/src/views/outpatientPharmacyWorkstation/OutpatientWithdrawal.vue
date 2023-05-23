<template>
  <div>
    <el-card class="box-card">
      <div class="search-box">
        <el-form :model="addForm" :inline="true">
          <el-input placeholder="请输入病历号"
                    style="width: 20%;"
                    v-model="addForm.caseNumber"
                    @clear="doQuery()">
          </el-input>
<!--          <el-input placeholder="请输入药品状态"-->
<!--                    style="width: 20%;"-->
<!--                    v-model="addForm.state"-->
<!--                    @clear="doQuery()">-->
<!--          </el-input>-->
          <el-form-item>
            <el-select v-model="addForm.state" placeholder="请选择药品状态">
              <el-option label="已缴费" value="3"></el-option>
              <el-option label="已发药" value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-button  icon="el-icon-search"
                      @click="doQuery()">
          </el-button>
          <el-button type="round" @click="back()" style="float:right" size="mini">全 退</el-button>
        </el-form>
      </div>

      <el-table :data = "prescriptionList.slice((currentPage-1)*pageSize,currentPage*pageSize)">
        <el-table-column label="病例号" prop="caseNumber"></el-table-column>
        <el-table-column label="处方号" prop="id"></el-table-column>
        <el-table-column label="开单医生" prop="userID"></el-table-column>
        <el-table-column label="药品名称" prop="drugsName"></el-table-column>
        <el-table-column label="规格" prop="drugsFormat"></el-table-column>
        <el-table-column label="数量" prop="amount"></el-table-column>
        <el-table-column label="单位" prop="drugsUnit"></el-table-column>
        <el-table-column label="单价" prop="drugsPrice"></el-table-column>
        <el-table-column label="总金额" prop="sum"></el-table-column>
        <el-table-column label="厂家" prop="manufacturer"></el-table-column>
        <el-table-column label="用法" prop="drugsUsage"></el-table-column>
        <el-table-column label="用量" prop="dosage"></el-table-column>
        <el-table-column label="状态" prop="state">
          <template slot-scope="scope">
            <span v-if="scope.row.state === 2">已开立</span>
            <span v-if="scope.row.state === 3">已缴费</span>
            <span v-if="scope.row.state === 4">已发药</span>
            <span v-if="scope.row.state === 5">已退药</span>
            <span v-if="scope.row.state === 6">已退费</span>
          </template>

        </el-table-column>
        <el-table-column label="操作" width="100px">
          <!--修改按钮-->
          <template slot-scope="scope">
            <el-button type="primary"  size="mini"
                       @click="enableUpdateDialog(scope.row)">退 药</el-button>
          </template>
        </el-table-column>

      </el-table>
      <el-pagination align='right'
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="[5, 10, 30, 40]"
                     :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="prescriptionList.length">
      </el-pagination>
    </el-card>

    <!--修改用户-->
    <el-dialog
        title="请输入退药数量"
        :visible.sync="updateDialogVisable"
        width="50%" @close="updateDialogClosed">
      <el-form :model="currentRow" ref="addFormRed" label-width="100px" :rules="FormRules">

        <el-form-item label="退药数量" prop="amount">
          <el-input v-model="currentRow.amount" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"  class = "dialog-footer">
        <el-button type="primary"
                   @click="enableDialog();doSum()">确 认</el-button>
    <el-button @click="updateDialogVisable = false">取 消</el-button>
  </span>

    </el-dialog>
    <el-dialog
        :visible.sync="DialogVisable"
        width="50%" @close="DialogClosed">
      <el-form :model="this.res" ref="addFormRed" label-width="100px" >
        <el-form-item label="退药总金额" prop="sum" >
          <el-input
              v-model="this.res"
              :disabled="true">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"  class = "dialog-footer">
    <el-button @click="DialogVisable = false">取 消</el-button>
            <el-button type="primary"
                       @click="doUpdate()">确 认</el-button>
  </span>
    </el-dialog>
    <el-dialog
        :visible.sync="DialogVisable2"
        width="50%" @close="DialogClosed2">
      <el-form :model="this.sum" ref="addFormRed" label-width="100px" >
        <el-form-item label="总退药金额" prop="sum" >
          <el-input
              v-model="this.sum"
              :disabled="true">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"  class = "dialog-footer">
    <el-button @click="DialogVisable2 = false">取 消</el-button>
            <el-button type="primary"
                       @click="backSure()">确 认</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      pageSize: 10,
      sum:'',
      prescriptionList: [],
      addDialogVisable:false,
      updateDialogVisable:false,
      DialogVisable2:false,
      DialogVisable:false,
      addForm:{
        caseNumber:'',
        id: '',
        userId: '',
        drugsFormat: '',
        amount: '',
        drugsUnit: '',
        drugsPrice: '',
        sum: '',
        manufacturer: '',
        drugsUsage: '',
        dosage: '',
        state: '',
      },
      currentRow: {
        caseNumber:'',
        id: '',
        userId: '',
        drugsFormat: '',
        amount: '',
        drugsUnit: '',
        drugsPrice: '',
        sum: '',
        manufacturer: '',
        drugsUsage: '',
        dosage: '',
        state: '',
      },
      res:'',
      FormRules:{
        amount: [
          {required:true,message:"退药数量不能超过药品数量",trigger:"blur"},
          {min:1,max:3,message: "退药数量不能超过药品数量",trigger: "blur"}
        ],
      }
    }
  },

  created() {
    this.doQuery()
  },

  mounted(){
    this.prescriptionList = this.$route.query.prescriptionList;
  },

  methods: {
    doSum(){
      this.res = this.currentRow.amount * this.currentRow.drugsPrice
    },

    doQuery(){
      let url='/outpatientPharmacyWorkstation/outpatientWithdrawal/selectAll';
      let params = this.$qs.stringify(this.addForm);
      console.log('test')
      const cn = this.addForm.caseNumber
      console.log(params)
      this.$axios.post(url,params)
          .then( (resp)=>{
            this.prescriptionList=resp.data.data;
          } )
          .catch( (err)=>{
            console.log('error=', err);
          } )
    },

    updateDialogClosed() {
      this.$refs.addFormRed.resetFields()
    },
    DialogClosed() {
      this.$refs.addFormRed.resetFields()
    },
    DialogClosed2() {
      this.$refs.addFormRed.resetFields()
    },
    handleSizeChange(val) {
      this.currentPage = 1;
      this.pageSize = val;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
    },
    doUpdate(){
      console.log('33333333')
      console.log(this.currentRow)
      let params =this.$qs.stringify(this.currentRow);
      console.log(params)
      let url='/outpatientPharmacyWorkstation/outpatientWithdrawal/update';
      this.$axios.post(url,params)
          .then( (resp)=>{
            let i=resp.data.code;
            if(i==200){
              location.reload();
              this.doQuery();
            }else{
              this.msg='退药失败'
            }
          } )
          .catch( (err)=>{
            console.log('error=', err);
          } )
      this.updateDialogVisable = false
    },

    enableUpdateDialog(param) {
      this.updateDialogVisable = true
      this.currentRow = param
      console.log('rrr')
      console.log(this.FormRules.amount[1])
      console.log('ddddd')
      this.FormRules.amount[1] = {min:0,max:this.currentRow.amount,message: "退药数量不能超过药品数量",trigger: "blur"}
      console.log(this.FormRules.amount[1])
      console.log('fff')
      console.log(this.currentRow)
    },
    enableDialog() {
      this.updateDialogVisable = false
      this.DialogVisable = true
      console.log('ddddd')
      console.log(this.currentRow)
    },
    enableDialog2() {
      this.DialogVisable2 = true
    },
    back(){
      this.DialogVisable2 = true;
      // console.log('33333333')
      // console.log(this.addForm.caseNumber)
      // let params =this.$qs.stringify(this.addForm);
      // console.log(params)
      // let url='/outpatientPharmacyWorkstation/outpatientWithdrawal/updateAll';
      // this.$axios.post(url,params)
      //     .then( (resp)=>{
      //       let i=resp.data.code;
      //       this.sum = resp.data.data.sum;
      //       if(i==200){
      //         this.enableDialog2();
      //       }else{
      //         this.msg='退药失败'
      //       }
      //     } )
      //     .catch( (err)=>{
      //       console.log('error=', err);
      //     } )
      // this.updateDialogVisable = false
    },

    backSure(){
      let params =this.$qs.stringify(this.addForm);
      let url='/outpatientPharmacyWorkstation/outpatientWithdrawal/updateAll';
      this.$axios.post(url,params)
          .then( (resp)=>{
            let i=resp.data.code;
            this.sum = resp.data.data.sum;
            if(i==200){
              this.doQuery();
            }else{
              this.msg='退药失败'
            }
          } )
          .catch( (err)=>{
            console.log('error=', err);
          } )
      this.DialogVisable2 = false;
    }

  }
}
</script>

<style scoped>

</style>
