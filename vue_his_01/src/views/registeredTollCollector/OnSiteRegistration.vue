<template>
  <div>
    <el-input placeholder="请输入姓名" class="my_el_input" v-model="query_name">
      <el-button slot="append" icon="el-icon-search" @click="doQuery"></el-button>
    </el-input>
    <el-button-group>
      <el-button style="margin-left: 50px;"  type="primary" @click="showAdvanceSearchView = !showAdvanceSearchView">
        <i :class="showAdvanceSearchView?'fa fa-angle-double-up':'fa fa-angle-double-down'"
           aria-hidden="true"></i>
        挂号
      </el-button>
    </el-button-group>
    <transition name="slide-fade">
      <div v-show="showAdvanceSearchView"
           style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
        <el-form :model="registerValue" :rules="registerRules" ref="registerValue">
          <el-row>
            <el-col :span="5">
              <el-form-item label="病历号:" prop="caseNumber">
                <el-input size="mini" style="width: 180px" prefix-icon="el-icon-edit"
                          v-model="registerValue.caseNumber" placeholder="病历号" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="姓名:" prop="realName">
                <el-input v-model="registerValue.realName" placeholder="姓名" size="mini"
                          style="width: 150px;" filterable>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <el-form-item label="性别:" prop="Gender">
                <el-select v-model="registerValue.gender" placeholder="性别" size="mini" style="width: 80px;">
                  <el-option
                      v-for="item in Gender"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="年龄:" prop="age">
                <el-input v-model="registerValue.age" placeholder="年龄" size="mini"
                          style="width: 140px;" filterable>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-form-item label="" prop="ageType">
                <el-select v-model="registerValue.ageType" placeholder="岁" size="mini" style="width: 80px;">
                  <el-option
                      v-for="item in ageType"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="出生日期:" prop="birthDate">
                <el-date-picker
                    v-model="registerValue.birthDate"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <el-form-item label="身份证号:" prop="iDnumber">
                <el-input v-model="registerValue.iDnumber" placeholder="身份证号" size="mini"
                          style="width: 180px;" filterable>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="家庭地址:" prop="homeAddress">
                <el-input v-model="registerValue.homeAddress" placeholder="家庭地址" size="mini"
                          style="width: 180px;" filterable>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <el-form-item label="午别:" prop="noon">
                <el-select v-model="registerValue.noon" placeholder="午别" size="mini" style="width: 80px;">
                  <el-option
                      v-for="item in Noon"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="挂号科室ID:" prop="deptID">
                <el-select v-model="registerValue.deptID" placeholder="挂号科室ID" size="mini"
                          style="width: 150px;" filterable>
                  <el-option label="普通内科" value="1"></el-option>
                  <el-option label="神经内科" value="2"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="看诊日期:" prop="visitDate">
                <el-date-picker
                    v-model="registerValue.visitDate"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <el-form-item label="挂号医生:" prop="userID">
                <el-select v-model="registerValue.userID" placeholder="请选择挂号医生" size="mini"
                          style="width: 155px;" filterable>
                  <el-option label="张仲景" value="1"></el-option>
                  <el-option label="王世民" value="2"></el-option>
                  <el-option label="李时珍" value="3"></el-option>
                  <el-option label="华佗" value="4"></el-option>
                  <el-option label="钱乙" value="5"></el-option>
                  <el-option label="安道全" value="6"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="挂号级别:" prop="registLeID">
                <el-select v-model="registerValue.registLeID" placeholder="挂号级别" size="mini" style="width: 100px;">
                  <el-option
                      v-for="item in RegistLeID"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="结算类别:" prop="settleID">
                <el-select v-model="registerValue.settleID" placeholder="自费" size="mini" style="width: 120px;">
                  <el-option
                      v-for="item in settleID"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="挂号员ID:" prop="registerID">
                <el-input v-model="registerValue.registerID" placeholder="挂号员ID" size="mini"
                          style="width: 160px;" filterable>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="挂号时间:" prop="registTime">
                <el-date-picker
                    v-model="registerValue.registTime"
                    type="datetime"
                    placeholder="选择日期时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <el-form-item label="看诊状态:" prop="visitState">
                <el-select v-model="registerValue.visitState" placeholder="看诊状态" size="mini" style="width: 165px;">
                  <el-option
                      v-for="item in VisitState"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="病历本:" prop="isBook">
                <el-select v-model="registerValue.isBook" placeholder="病历本" size="mini" style="width: 100px;">
                  <el-option
                      v-for="item in IsBook"
                      :key="item"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-button style="margin-left: 90%" type="success" @click="doAdd();">挂号</el-button>
              <!--              <el-button>重置</el-button>-->
            </el-col>
          </el-row>
        </el-form>
      </div>
    </transition>
      <el-card class="card">
        <el-table
            :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)"
            height="600"
            border
            stripe
            style="width: 100%">
          <el-table-column
              prop="caseNumber"
              fixed
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
              prop="birthDate"
              label="出生日期"
              width="100">
          </el-table-column>
          <el-table-column
              prop="visitDate"
              label="看诊时间"
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
              prop="isBook"
              label="是否收取病历本"
              :formatter="whetherBook"
              width="120">
          </el-table-column>
          <el-table-column
              prop="visitState"
              label="状态"
              :formatter="stateShift"
              width="100">
          </el-table-column>
          <!--        <el-descriptions-item label="状态" :span="2" v-if="registerValue.visitState===1"><el-tag type="success">已挂号</el-tag></el-descriptions-item>-->
          <!--        <el-descriptions-item label="状态" :span="2" v-else-if="registerValue.visitState===2"><el-tag type="warning">医生接诊</el-tag></el-descriptions-item>-->
          <el-table-column label="操作" width="130px">
            <template slot-scope="scope">
              <el-button type ="info" @click="doPrint(scope.row)" round>打印发票</el-button>
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
        :visible.sync="dialogPrint"
        width="45%"
        center>
      <el-form
          ref="form"
          :model="invoice"
          label-width="150px">
        <el-form-item label="发票编号:">
         <el-card class="box-card" v-text="invoice.invoiceNum">
         </el-card>
        </el-form-item>
        <el-form-item label="发票金额:">
          <el-card class="box-card" v-text="invoice.money">
          </el-card>
        </el-form-item>
        <el-form-item label="发票状态:">
          <el-card class="box-card" v-text="invoice.state">
          </el-card>
        </el-form-item>
        <el-form-item label="收/退费时间:">
          <el-card class="box-card" v-text="invoice.creationTime">
          </el-card>
        </el-form-item>
        <el-form-item label="收/退费人员ID:">
          <el-card class="box-card" v-text="invoice.userID">
          </el-card>
        </el-form-item>
        <el-form-item label="挂号ID:">
          <el-card class="box-card" v-text="invoice.registID">
          </el-card>
        </el-form-item>
        <el-form-item label="收费方式:">
          <el-card class="box-card" v-text="invoice.feeType">
          </el-card>
        </el-form-item>
        <el-form-item label="冲红发票号码:">
          <el-card class="box-card" v-text="invoice.back">
          </el-card>
        </el-form-item>
        <el-form-item label="发票状态:">
          <el-card class="box-card" v-text="invoice.dailyState">
          </el-card>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogPrint = false">取 消</el-button>
        <el-button type="primary" @click="dialogPrint = false">确 定</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
import moment from 'moment';
export default {
  name: "OnSiteRegistration",
  data(){
    return{
      registerValue:{
        caseNumber:'',
        realName:'',
        gender:'',
        iDnumber:'',
        birthDate:'',
        age:'',
        ageType:'',
        homeAddress:'',
        visitDate:'',
        noon:'',
        deptID:'',
        userID:'',
        registLeID:'',
        settleID:'',
        isBook:'',
        registTime:'',
        registerID:'',
        visitState:'',
      },
      register:{
        CaseNumber:'60',
    },
      query_name:'',
      list:[],
      invoice:[],
      Gender:['男','女'],
      ageType:['岁'],
      settleID:['自费','市医保'],
      deptID:[],
      showAdvanceSearchView:false,
      currentPage: 1,
      pageSize:10,
      dialogPrint:false,
      VisitState:['已挂号','医生接诊','看诊结束','已退号'],
      Noon:['上午','下午'],
      IsBook:['是','否'],
      RegistLeID:['普通号','专家号'],
      registerRules: {
        caseNumber: [{required: true, message: '请输入病历号', trigger: 'blur'}],
        realName: [{required: true, message: '请输入真实姓名', trigger: 'blur'}],
        gender: [{required: true, message: '请输入性别', trigger: 'blur'}],
        age: [{required: true, message: '请输入年龄', trigger: 'blur'}],
        ageType: [{required: true, message: '请输入年龄类型', trigger: 'blur'}],
        visitDate: [{required: true, message: '请输入看诊时间', trigger: 'blur'}],
        noon: [{required: true, message: '请输入午别', trigger: 'blur'}],
        deptID: [{required: true, message: '请输入挂号科室ID', trigger: 'blur'}],
        userID: [{required: true, message: '请输入挂号医生ID', trigger: 'blur'}],
        registLeID: [{required: true, message: '请输入挂号级别', trigger: 'blur'}],
        settleID: [{required: true, message: '请输入结算类别', trigger: 'blur'}],
        registTime: [{required: true, message: '请输入挂号时间', trigger: 'blur'}],
        registerID: [{required: true, message: '请输入挂号员ID', trigger: 'blur'}],
        visitState: [{required: true, message: '请输入挂号状态', trigger: 'blur'}],
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
      this.$axios.post(`/register_clerk/selectAll?RealName=${this.query_name}`).then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    doQuery(){
      this.$axios.post(`/register_clerk/selectAll?RealName=${this.query_name}`).then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
      this.initData();
    },
    doAdd(){
      this.$refs['registerValue'].validate(valid => {
        if (valid) {
          if (this.registerValue.gender ==='男'){
            this.registerValue.gender = '71';
          } else {
            this.registerValue.gender = '72';
          }
          if (this.registerValue.settleID ==='自费'){
            this.registerValue.settleID = '1';
          } else {
            this.registerValue.settleID = '2';
          }
          if (this.registerValue.registLeID ==='专家号'){
            this.registerValue.registLeID = '1';
          } else {
            this.registerValue.registLeID = '2';
          }
          if (this.registerValue.visitState ==='已挂号'){
            this.registerValue.visitState = '1';
          } else if(this.registerValue.visitState === '医生接诊'){
            this.registerValue.visitState = '2';
          } else if(this.registerValue.visitState === '看诊结束') {
            this.registerValue.visitState = '3';
          } else {
            this.registerValue.visitState = '4';
          }
          if (this.registerValue.isBook ==='是'){
            this.registerValue.registLeID = '1';
          } else {
            this.registerValue.registLeID = '0';
          }
          this.registerValue.registTime = moment(this.registerValue.registTime).format('YYYY-MM-DD HH:mm:ss');
          let param=this.$qs.stringify(this.registerValue)
          this.$axios.post('/register_clerk/register',param).then( (resp)=> {
            console.log(resp.data.msg)
          }).catch( (err)=> {
            console.log("err",err);
          })
        }
      })
      this.initData();
    },
    doPrint(data){
      console.log(data);
      console.log(this.registerValue);
      this.dialogPrint = true;
      // let param=this.$qs.stringify(this.sterValue)
      this.$axios.post('/register_clerk/print',data).then( (resp)=> {
        this.invoice = resp.data.data
        console.log(this.invoice)
      }).catch( (err)=> {
        console.log("err",err);
      })
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
    isGender(row, column) {
      if(row[column.property] === 71){
        return '男'
      } else {
        return  '女'
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
.my_el_input {
  width: 400px;
}
</style>
