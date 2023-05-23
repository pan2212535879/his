<template>
  <div>
    <div>诊毕</div>
    <div>
      <el-row :gutter="20">
        <el-col :span="12">
<!--          这里是搜索待诊毕患者-->
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
<!--          这里是待诊毕患者详细-->
          <div>
            <span>待诊毕患者</span>
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
                <el-descriptions size="mini" title="患者病历信息" direction="vertical" :column="8" border>
                  <el-descriptions-item label="ID" :span="3">{{medicalRecord.ID}}</el-descriptions-item>
                  <el-descriptions-item label="病历号" :span="3">{{medicalRecord.CaseNumber}}</el-descriptions-item>
                  <el-descriptions-item label="挂号 ID" :span="3">{{medicalRecord.RegistID}}</el-descriptions-item>
                  <el-descriptions-item label="主诉" :span="3">{{medicalRecord.Readme}}</el-descriptions-item>
                  <el-descriptions-item label="现病史" :span="3">{{medicalRecord.Present}}</el-descriptions-item>
                  <el-descriptions-item label="现病治疗情况" :span="3">{{medicalRecord.PresentTreat}}</el-descriptions-item>
                  <el-descriptions-item label="既往史" :span="3">{{medicalRecord.History}}</el-descriptions-item>
                  <el-descriptions-item label="过敏史" :span="3">{{medicalRecord.Allergy}}</el-descriptions-item>
                  <el-descriptions-item label="体格检查" :span="3">{{medicalRecord.Physique}}</el-descriptions-item>
                  <el-descriptions-item label="检查建议" :span="3"><el-tag effect="dark" size="mini" type="success">{{medicalRecord.Proposal}}</el-tag></el-descriptions-item>
                  <el-descriptions-item label="注意事项" :span="3"><el-tag effect="dark" size="mini" type="warning">{{medicalRecord.Careful}}</el-tag></el-descriptions-item>
                  <el-descriptions-item label="检查结果" :span="3"><el-tag effect="dark" size="mini" type="danger">{{medicalRecord.CheckResult}}</el-tag></el-descriptions-item>
                  <el-descriptions-item label="诊断结果" :span="3"><el-tag effect="dark" size="mini" type="danger">{{medicalRecord.Diagnosis}}</el-tag></el-descriptions-item>
                  <el-descriptions-item label="处理意见" :span="3"><el-tag effect="dark" size="mini" type="primary">{{medicalRecord.Handling}}</el-tag></el-descriptions-item>
                  <el-descriptions-item label="病历状态" :span="3" v-if="medicalRecord.CaseState === 1">
                    <el-tag  effect="dark" size="mini" type="primary">暂存</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item label="病历状态" :span="3" v-else-if="medicalRecord.CaseState === 2">
                    <el-tag  effect="dark" size="mini" type="success">已提交</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item label="病历状态" :span="3" v-else>
                    <el-tag  effect="dark" size="mini" type="danger">诊毕</el-tag>
                  </el-descriptions-item>
                </el-descriptions>
                <el-popover
                    placement="top"
                    width="160"
                    v-model="endVisible">
                  <p>确定诊毕？</p>
                  <div style="text-align: right; margin: 0">
                    <el-button size="mini" type="text" @click="endVisible = false" icon="el-icon-close">取消</el-button>
                    <el-button size="mini" type="danger" @click="commitEnd" icon="el-icon-check">确定</el-button>
                  </div>
                  <el-button slot="reference" size="large" :disabled="medicalRecord.CaseState === 3" style="text-align: center; margin-left: 500px" type="danger" icon="el-icon-aim">诊毕</el-button>
                </el-popover>
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
  name: "AfterDiagnosis",
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
      medicalRecord: {
        ID: '',
        CaseNumber: '',
        RegistID: '',
        Readme: '',
        Present: '',
        PresentTreat: '',
        History: '',
        Allergy: '',
        Physique: '',
        Proposal: '',
        Careful: '',
        CheckResult: '',
        Diagnosis: '',
        Handling: '',
        CaseState: 1,
      },
      endVisible: false,

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
            url = '/outpatientDoctorWorkStation/afterDiagnosis/searchByName';
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
            url = '/outpatientDoctorWorkStation/afterDiagnosis/searchByNumber';
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
      let url = '/outpatientDoctorWorkStation/afterDiagnosis/initPatients';
      let param = JSON.parse(localStorage.getItem("User"));
      this.$axios.post(url, param).then(res => {
        this.loading = false;
        console.log("所有的待诊毕病人：", res);
        this.patients = res.data.data;
      })
    },
    choosePatient(row) {
      console.log("选择的病人：", row);
      // this.$refs['medicalRecord'].resetFields();

      this.$nextTick(() => {
        this.isChoose = true;
        this.choosedPatient.RealName = row.realName;
        this.choosedPatient.CaseNumber = row.caseNumber;
        this.choosedPatient.Gender = row.gender;
        this.choosedPatient.Age = row.age;
      })

      this.medicalRecord.CaseNumber = row.caseNumber;
      this.medicalRecord.RegistID = row.id;
      this.initRecord(row.caseNumber);// 初始化已选择患者的病历信息

    },
    sizeChangeForPatient(currentSize) {
      this.pageSizeForPatient = currentSize;
      this.initPatient();
    },
    currentChangeForPatient(currentPage) {
      this.currentPageForPatient = currentPage;
      this.initPatient();
    },
    initRecord(number) {
      let url = '/outpatientDoctorWorkStation/afterDiagnosis/initRecord';
      let param = {
        CaseNumber: number,
      }
      console.log("number = ", number);
      console.log("param = ", param);
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        console.log("初始化病历信息：", res);
        if (res.data.data) {
          this.medicalRecord.ID = res.data.data.id;
          this.medicalRecord.Readme = res.data.data.readme;
          this.medicalRecord.Present = res.data.data.present;
          this.medicalRecord.PresentTreat = res.data.data.presentTreat;
          this.medicalRecord.History = res.data.data.history;
          this.medicalRecord.Allergy = res.data.data.allergy;
          this.medicalRecord.Physique = res.data.data.physique;
          this.medicalRecord.Proposal = res.data.data.proposal;
          this.medicalRecord.Careful = res.data.data.careful;
          this.medicalRecord.CheckResult = res.data.data.checkResult;
          this.medicalRecord.Diagnosis = res.data.data.diagnosis;
          this.medicalRecord.Handling = res.data.data.handling;
          this.medicalRecord.CaseState = res.data.data.caseState;
        }

      })
    },
    commitEnd() {
      this.endVisible = false;
      console.log("即将诊毕的患者：", this.choosedPatient);
      console.log("即将诊毕的病历：", this.medicalRecord);
      let url = '/outpatientDoctorWorkStation/afterDiagnosis/commitEndPatient';
      let param = this.choosedPatient;
      // 先修改挂号状态
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        // 再修改病历状态
        url = '/outpatientDoctorWorkStation/afterDiagnosis/commitEndMedicalRecord';
        this.medicalRecord.CaseState = 3;
        param = this.medicalRecord;
        this.$axios.post(url, this.$qs.stringify(param)).then(res => {
          this.initRecord(this.medicalRecord.CaseNumber);
        })
      })

    }
  }
}
</script>

<style scoped>

</style>
