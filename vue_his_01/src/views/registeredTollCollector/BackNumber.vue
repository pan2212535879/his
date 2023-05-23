<template>
  <div>
    <el-card class="card">
      <el-input placeholder="请输入挂号ID" class="my_el_input" v-model="query_registID">
        <el-button slot="append" icon="el-icon-search" @click="doQuery"></el-button>
      </el-input>
      <el-table
          :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)"
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
            :formatter="whetherstate"
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
            :formatter="whetherDaily"
            width="100">
        </el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button type ="warning"  @click="doGet(scope.row)" plain>修改</el-button>
            <el-button type ="danger"  @click="doBack(scope.row)" @plain>退号</el-button>
          </template>
        </el-table-column>

      </el-table>
      <el-pagination
          align='right'
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-sizes="[1, 5, 10, 20, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="list.length">
      </el-pagination>
    </el-card>
    <el-dialog
        title="发票信息"
        :visible.sync="dialogUpdate"
        width="45%"
        center>
      <el-form
          ref="invoiceInFormation"
          :model="invoiceInFormation"
          label-width="150px"
          :rules="invoiceRules">
        <el-form-item label="发票编号:" prop="invoiceNum">
          <el-input class="box-card" v-model="invoiceInFormation.invoiceNum" placeholder="发票编号"></el-input>
        </el-form-item>
        <el-form-item label="发票金额:" prop="money">
          <el-input class="box-card" v-model="invoiceInFormation.money" placeholder="发票金额"></el-input>
        </el-form-item>
        <el-form-item label="发票状态:" prop="state">
          <el-input class="box-card" v-model="invoiceInFormation.state" placeholder="发票状态"></el-input>
        </el-form-item>
        <el-form-item label="收/退费时间:">
          <el-input class="box-card" v-model="invoiceInFormation.creationTime" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="收/退费人员ID:">
          <el-input class="box-card" v-model="invoiceInFormation.userID" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="挂号ID:">
          <el-input class="box-card" v-model="invoiceInFormation.registID" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="收费方式:">
          <el-input class="box-card" v-model="invoiceInFormation.feeType" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="冲红发票号码:" prop="back">
          <el-input class="box-card" v-model="invoiceInFormation.back" placeholder="冲红发票号码"></el-input>
        </el-form-item>
        <el-form-item label="发票状态:" prop="dailyState">
          <el-input class="box-card" v-model="invoiceInFormation.dailyState" placeholder="发票状态"></el-input>
        </el-form-item>
      </el-form>
<!--      <template slot-scope="scope">-->
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogUpdate = false">取 消</el-button>
            <el-button type="primary" @click="doUpdate()">修改</el-button>
        </span>
<!--      </template>-->
    </el-dialog>
    <el-dialog
        title="发票信息"
        :visible.sync="dialogBackNum"
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
        <el-button @click="dialogBackNum = false">取 消</el-button>
        <el-button type="primary" @click="dialogBackNum = false">确 定</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data(){
    return {
      currentPage: 1,
      pageSize:10,
      list: [],
      invoice: [],
      query_registID:'',
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
      dialogUpdate: false,
      dialogBackNum: false,
      temp:'',
      invoiceRules: {
        invoiceNum: [{required: true, message: '请输入发票号', trigger: 'blur'}],
        money: [{required: true, message: '请输入金额', trigger: 'blur'}],
        state: [{required: true, message: '请输入发票状态', trigger: 'blur'}],
        dailyState: [{required: true, message: '请输入日结审核状态', trigger: 'blur'}],
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
      this.$axios.post('/backnumber/selectAllInvoice').then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    doQuery(){
      this.$axios.post(`/backnumber/selectAllInvoice?RegistID=${this.query_registID}`).then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
      this.initData();
    },
    doUpdate(){
      console.log(this.invoiceInFormation)
      this.$refs['invoiceInFormation'].validate(valid => {
        if(valid){
          this.$axios.post('/backnumber/updateInvoice', this.invoiceInFormation).then((resp) => {
            console.log('list=', this.invoice)
          }).catch((err) => {
            console.log("err", err);
          })
          this.dialogUpdate = false;
        }
      })
    },
    doAdd(data){
      this.$axios.post('/backnumber/addInvoice',data).then( (resp)=> {
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    doGet(data){
      this.dialogUpdate=true;
      console.log(data);
      this.invoice = data;
      this.invoiceInFormation = data;
    },
    doBack(data){
      this.$confirm('此操作将退回挂号, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/backnumber/backNumber',data).then( (resp)=> {
          this.invoiceInFormation = resp.data.data;
          console.log("我是msg",resp.data.msg);
          if(resp.data.msg === '退号成功') {
            this.dialogBackNum = true;
          }
        }).catch( (err)=> {
          console.log("err",err);
        })
        this.initData();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退号'
        });
      });
    },
    whetherDaily(row,column){
      if(row[column.property] === 1){
        return '未日结'
      }else if(row[column.property] === 2){
        return '已提交'
      }else {
        return '已审核'
      }
    },
    whetherstate(row,column){
      if(row[column.property] === 1){
        return '正常'
      }else {
        return '作废'
      }
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.initData();
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.initData();
      // console.log(`当前页: ${val}`);
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
