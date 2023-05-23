<template>
  <div>
    <div>患者费用明细查询</div>
    <div>
      <el-row :gutter="20">
        <el-col :span="12">
          <!--          这里是搜索费用明细的患者-->
          <el-card shadow="hover">
            <!--            这里是查询患者-->
            <div>
              <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">
                  查询类型<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="a">姓名</el-dropdown-item>
                  <el-dropdown-item command="b">病历号</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              <el-form :model="searchPatient" :rules="searchPatientRules" ref="searchPatient">
                <el-form-item :prop="prop">
                  <el-input v-if="searchByName===true" v-model="searchPatient.RealName" placeholder="请输入患者姓名进行查询" style="width: 200px;margin-right: 10px" prefix-icon="el-icon-question" clearable></el-input>
                  <el-input v-else v-model="searchPatient.CaseNumber" placeholder="请输入患者病历号进行查询" style="width: 200px;margin-right: 10px" prefix-icon="el-icon-question" clearable></el-input>
                  <el-button-group>
                    <el-button icon="el-icon-search" size="small" type="primary" @click="search('searchPatient')">查询</el-button>
                    <el-button icon="el-icon-refresh" size="small" type="info" @click="reset('searchPatient')">重置</el-button>
                  </el-button-group>
                </el-form-item>
              </el-form>
            </div>
            <div>
              <el-divider content-position="left"></el-divider>
            </div>
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div v-show="isChoose">
                <el-descriptions size="mini" title="患者基本信息" direction="vertical" :column="4" border>
                  <el-descriptions-item label="姓名" :span="2">{{choosedPatient.RealName}}</el-descriptions-item>
                  <el-descriptions-item label="病历号" :span="2">{{choosedPatient.CaseNumber}}</el-descriptions-item>
                  <el-descriptions-item label="性别" :span="2" v-if="choosedPatient.Gender===71"><el-tag type="success">男</el-tag></el-descriptions-item>
                  <el-descriptions-item label="性别" :span="2" v-else><el-tag type="warning">女</el-tag></el-descriptions-item>
                  <el-descriptions-item label="年龄" :span="2">{{choosedPatient.Age}}</el-descriptions-item>
                </el-descriptions>
              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>
        <el-col :span="12">
          <!--          这里是查询费用详细患者-->
          <div>
            <span>待查询费用明细患者</span>
            <el-table :data="patientData"
                      stripe
                      border
                      :default-sort = "{prop: 'date', order: 'descending'}"
                      v-loading="loading"
                      max-height="250"
                      min-height="250"
                      element-loading-text="正在加载..."
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      style="width: 100%">
              <el-table-column align="center" prop="caseNumber" label="病历号" style="width: 25%"></el-table-column>
              <el-table-column align="center" prop="realName" label="姓名" style="width: 25%"></el-table-column>
              <el-table-column align="center" prop="age" label="年龄" style="width: 25%"></el-table-column>
              <el-table-column align="center" fixed="right" label="操作" style="width: 25%">
                <template slot-scope="scope">
                  <el-tooltip class="item" effect="dark" content="选择此病人，并确认诊毕" placement="right">
                    <el-button type="text" size="mini" @click="choosePatient(scope.row)">选择</el-button>
                  </el-tooltip>

                </template>
              </el-table-column>
            </el-table>
            <div style="display: flex;justify-content: flex-end">
              <el-pagination
                  background
                  @current-change="currentChangeForPatient"
                  @size-change="sizeChangeForPatient"
                  :page-size="pageSizeForPatient"

                  :current-page.sync="currentPageForPatient"
                  layout="prev, pager, next, jumper, ->, total, slot"
                  :total="patients.length">
                <!--                        :total="total">-->
              </el-pagination>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="24">
          <el-card shadow="hover">
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div v-show="isChoose">
                <el-table
                    :data="costData"
                    stripe
                    border
                    :default-sort = "{prop: 'date', order: 'descending'}"
                    v-loading="costLoading"
                    max-height="500"
                    min-height="250"
                    element-loading-text="正在加载..."
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    style="width: 100%">
                  <el-table-column align="center" prop="id" label="ID" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="registID" label="挂号 ID" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="invoiceID" label="发票 ID" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="itemID" label="项目 ID" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="itemType" label="项目类型" style="width: 10%" :filters="[{ text: '非药品', value: 1 }, { text: '药品', value: 2 }]" :filter-method="filterTag" filter-placement="bottom-end">
                    <template slot-scope="scope">
                      <el-tag :type="scope.row.itemType === 1 ? 'primary' : 'danger'" disable-transitions>{{scope.row.itemType === 1 ? '非药品' : '药品'}}</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="name" label="项目名称" style="width: 10%"></el-table-column>
                  <el-table-column align="center" prop="price" label="项目单价" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="amount" label="数量" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="deptID" label="执行科室 ID" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="createtime" label="开立时间" style="width: 10%"></el-table-column>
                  <el-table-column align="center" prop="createOperID" label="开立人员 ID" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="payTime" label="收/退费时间" style="width: 10%"></el-table-column>
                  <el-table-column align="center" prop="registerID" label="收/退费人员 ID" style="width: 5%"></el-table-column>
                  <el-table-column align="center" prop="feeType" label="收费方式" style="width: 10%"></el-table-column>
                  <el-table-column align="center" prop="backID" label="退费对应记录 ID" style="width: 5%"></el-table-column>
                </el-table>
              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "PatientExpenseDetailsQuery",
  data() {
    return {
      prop: 'RealName',
      presentUser: {},
      searchByName: true,
      searchPatient: {
        RealName: '',
        CaseNumber: '',
        DeptID: null,
        UserID: null,
      },
      searchPatientRules: {
        RealName: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        CaseNumber: [{required: true, message: '请输入病历号', trigger: 'blur'}]
      },
      loading: false,
      isChoose: false,
      choosedPatient: {
        RealName: '',
        CaseNumber: '',
        Gender: '',
        Age: '',
      },
      patients: [],
      currentPageForPatient: 1,
      pageSizeForPatient: 5,
      costLoading: false,
      cost: [],
      currentPageForCost: 1,
      pageSizeForCost: 10,
    }
  },
  mounted() {
    this.initUser();// 初始化医生
    this.initPatient();// 初始化患者挂号信息表
  },
  computed: {
    patientData() {
      console.log("patients.length = ", this.patients.length);
      if (this.patients.length > 0) {
        return this.patients.slice( (this.currentPageForPatient -1) * this.pageSizeForPatient, this.currentPageForPatient * this.pageSizeForPatient) || [];
      }
      console.log("patients = ", this.patients);
      return this.patients;
    },
    costData() {
      console.log("cost.length = ", this.cost.length);
      if (this.cost.length > 0) {
        return this.cost.slice( (this.currentPageForCost -1) * this.pageSizeForCost, this.currentPageForCost * this.pageSizeForCost) || [];
      }
      console.log("cost = ", this.cost);
      return this.cost;
    }
  },
  methods: {
    handleCommand(command) {
      // this.$message('click on item ' + command);
      if (command === 'a') {
        this.searchByName = true;
        this.prop = 'RealName';
      } else {
        this.searchByName = false;
        this.prop = 'CaseNumber';
      }
      this.$message('searchByName = ' + this.searchByName);
    },
    sizeChangeForPatient(currentSize) {
      this.pageSizeForPatient = currentSize;
      this.initPatient();
    },
    currentChangeForPatient(currentPage) {
      this.currentPageForPatient = currentPage;
      this.initPatient();
    },
    search(ruleForm) {
      let url = '';
      let param = null;
      let user = JSON.parse(localStorage.getItem("User"));
      console.log("user = ", user);
      this.searchPatient.UserID = user.id;
      this.searchPatient.DeptID = user.deptID;
      if (this.searchByName === true) {
        console.log("进入if");
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            url = '/outpatientDoctorWorkStation/patientExpenseDetailsQuery/searchByName';
            param = this.$qs.stringify(this.searchPatient);
            console.log("搜索的患者：", this.searchPatient);
            this.$axios.post(url, param).then(res => {
              console.log("病历号搜索结果：", res);
              this.patients = res.data.data;
            })
          }
        })
      } else {
        console.log("进入else");
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            url = '/outpatientDoctorWorkStation/patientExpenseDetailsQuery/searchByNumber';
            param = this.$qs.stringify(this.searchPatient);
            console.log("搜索的患者：", this.searchPatient);
            this.$axios.post(url, param).then(res => {
              console.log("姓名搜索结果：", res);
              this.patients = res.data.data;
            })
          }
        })
      }
    },
    reset(ruleForm) {
      this.$refs[ruleForm].resetFields();
      this.initPatient();
    },
    initUser() {
      // console.log("scope.row.userId: ", scope.row.userId);
      console.log("presentUser.id: ", this.presentUser.id);
      this.presentUser = JSON.parse(localStorage.getItem("User"));
      console.log("初始化医生：", this.presentUser);
    },
    initPatient() {
      this.loading = true;
      let url = '/outpatientDoctorWorkStation/patientExpenseDetailsQuery/initPatients';
      let param = JSON.parse(localStorage.getItem("User"));
      this.$axios.post(url, param).then(res => {
        this.loading = false;
        console.log("所有的待查询费用明细的病人：", res);
        this.patients = res.data.data;
      })
    },
    choosePatient(row) {
      this.$nextTick(() => {
        this.isChoose = true;
        this.choosedPatient.RealName = row.realName;
        this.choosedPatient.CaseNumber = row.caseNumber;
        this.choosedPatient.Gender = row.gender;
        this.choosedPatient.Age = row.age;
      })
      console.log("选择的患者：", row);
      let url = '/outpatientDoctorWorkStation/patientExpenseDetailsQuery/choosePatient';
      let param = row;
      this.$axios.post(url, param).then(res => {
        this.cost = res.data.data;
      })
    },
    filterTag(value, row) {
      return row.itemType === value;
    },
  }
}
</script>

<style scoped>

</style>