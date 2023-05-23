<template>
  <div>
    <div>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card shadow="hover">
            <!--          这里是待检查患者列表-->
            <el-collapse-transition>
              <div>
                <span>待查患者</span>
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
                          style="width: 571px">
                  <el-table-column align="center" prop="caseNumber" label="病历号" width="150"></el-table-column>
                  <el-table-column align="center" prop="realName" label="姓名" width="200"></el-table-column>
                  <el-table-column align="center" prop="age" label="年龄" width="100"></el-table-column>
                  <el-table-column align="center" fixed="right" label="操作" width="120">
                    <template slot-scope="scope">
                      <el-tooltip class="item" effect="dark" content="选择此病人，并开立检查项目" placement="right">
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
            </el-collapse-transition>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card shadow="hover">
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div v-show="isChoose">
                <el-descriptions size="mini" title="患者基本信息" direction="vertical" :column="4" border>
                  <el-descriptions-item label="姓名" :span="2">{{choosedPatient.RealName}}</el-descriptions-item>
                  <el-descriptions-item label="病历号" :span="2">{{choosedPatient.CaseNumber}}</el-descriptions-item>
                  <!--              <el-descriptions-item label="性别" :span="2" v-if="choosedPatient.Gender===71"><el-tag type="success">男</el-tag></el-descriptions-item>-->
                  <!--              <el-descriptions-item label="性别" :span="2" v-else><el-tag type="warning">女</el-tag></el-descriptions-item>-->
                  <el-descriptions-item label="年龄" :span="2">{{choosedPatient.Age}}</el-descriptions-item>
                  <el-descriptions-item label="添加项目" :span="2">
                    <el-button size="mini" type="success" @click="openAddApply" icon="el-icon-plus"></el-button>
                  </el-descriptions-item>
                  <!--              <el-descriptions-item label="备注">-->
                  <!--                <el-tag size="small">学校</el-tag>-->
                  <!--              </el-descriptions-item>-->
                  <!--              <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>-->
                </el-descriptions>

              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>

<!--        <el-col :span="8">-->
<!--&lt;!&ndash;          这里是组套管理&ndash;&gt;-->
<!--        </el-col>-->
      </el-row>
      <div>
        <el-divider content-position="center">患者检查项目表</el-divider>
      </div>
      <el-row :gutter="20">
        <el-col :span="24">
          <el-card shadow="hover">
            <!--          这里是患者的项目明细-->
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div v-show="isChoose" style="width: 100%">
                <el-table :data="itemData"
                          stripe
                          border
                          :default-sort = "{prop: 'date', order: 'descending'}"
                          v-loading="checkApplyLoading"

                          element-loading-text="正在加载..."
                          element-loading-spinner="el-icon-loading"
                          element-loading-background="rgba(0, 0, 0, 0.8)"
                          style="width: 100%">
                  <el-table-column align="center" prop="medicalID" label="病历ID" width="75px"></el-table-column>
                  <el-table-column align="center" prop="name" label="项目名称" width="150px"></el-table-column>
                  <el-table-column align="center" prop="itemID" label="项目ID" width="75px"></el-table-column>
                  <el-table-column align="center" prop="objective" label="目的要求" width="125px"></el-table-column>
                  <el-table-column align="center" prop="position" label="检查部位" width="125px"></el-table-column>
                  <el-table-column align="center" prop="isUrgent" label="是否加急" width="100px">
                    <template slot-scope="scope">
                      <el-tag v-if="scope.row.isUrgent === 1" type="danger" size="mini">是</el-tag>
                      <el-tag v-else type="primary" size="mini">否</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="result" label="检查结果" width="150px"></el-table-column>
                  <el-table-column align="center" prop="state" label="状态" :formatter="applyState" width="113px">
                    <template slot-scope="scope">
                      <el-tag v-if="scope.row.state === 1" type="primary" size="mini">暂存</el-tag>
                      <el-tag v-if="scope.row.state === 2" type="success" size="mini">已开立</el-tag>
                      <el-tag v-if="scope.row.state === 3" type="success" size="mini">已交费</el-tag>
                      <el-tag v-if="scope.row.state === 4" type="info" size="mini">已登记</el-tag>
                      <el-tag v-if="scope.row.state === 5" type="warning" size="mini">执行完</el-tag>
                      <el-tag v-if="scope.row.state === 6" type="danger" size="mini">已退费</el-tag>
                      <el-tag v-if="scope.row.state === 7" type="danger" size="mini">已作废</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" label="操作" width="300px">
                    <template slot-scope="scope">
                      <el-button-group>
                        <div>
                          <el-button v-if="!(scope.row.state===2) && !(scope.row.state===5)" style="color: #89e250" type="text" size="mini" :disabled="scope.row.state===2" @click="tempApply(scope.row)">暂存</el-button>
                          <el-button v-else type="text" size="mini" :disabled="scope.row.state===2 || scope.row.state===5" @click="tempApply(scope.row)">暂存</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-button v-if="!(scope.row.state===2) && !(scope.row.state===5)" style="color: #e2cf1c" type="text" size="mini" :disabled="scope.row.state===2" @click="openEditApply(scope.row)">编辑</el-button>
                          <el-button v-else type="text" size="mini" :disabled="scope.row.state===2 || scope.row.state===5" @click="openEditApply(scope.row)">编辑</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-button v-if="!(scope.row.state===2) && !(scope.row.state===5)" style="color: #0796b8" type="text" size="mini" :disabled="scope.row.state===2" @click="commitApply(scope.row)">开立</el-button>
                          <el-button v-else type="text" size="mini" :disabled="scope.row.state===2 || scope.row.state===5" @click="commitApply(scope.row)">开立</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-button v-if="!(scope.row.state===2) && !(scope.row.state===5)" style="color: #fa7b0c" type="text" size="mini" :disabled="scope.row.state===2" @click="deleteApply(scope.row)">删除</el-button>
                          <el-button v-else type="text" size="mini" :disabled="scope.row.state===2 || scope.row.state===5" @click="deleteApply(scope.row)">删除</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-button v-if="!(scope.row.state!==2) && !(scope.row.state===5)" style="color: #fa0707" type="text" size="mini" :disabled="scope.row.state!==2" @click="cancelApply(scope.row)">作废</el-button>
                          <el-button v-else type="text" size="mini" :disabled="scope.row.state!==2 || scope.row.state===5" @click="cancelApply(scope.row)">作废</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <!--                      <el-button type="text" size="mini" :disabled="scope.row.state!==5" @click="lookResult(scope.row)">结果</el-button>-->
                          <el-popover
                              placement="right"
                              width="400"
                              trigger="click">
                            <el-descriptions :data="scope.row" size="mini" title="患者检查结果信息" direction="vertical" :column="4" border>
                              <el-descriptions-item :span="2" width="100" property="name" label="项目名称">{{scope.row.name}}</el-descriptions-item>
                              <el-descriptions-item :span="2" width="100" property="objective" label="目的要求">{{scope.row.objective}}</el-descriptions-item>
                              <el-descriptions-item :span="2" width="100" property="position" label="检查部位">{{scope.row.position}}</el-descriptions-item>
                              <el-descriptions-item :span="2" width="100" property="result" label="检查结果">{{scope.row.result}}</el-descriptions-item>
                            </el-descriptions>
                            <el-button v-if="!(scope.row.state!==5)" style="color: #d828ff" type="text" size="mini" :disabled="scope.row.state!==5" slot="reference">结果</el-button>
                            <el-button v-else type="text" size="mini" :disabled="scope.row.state!==5" slot="reference">结果</el-button>
                          </el-popover>
                        </div>
                      </el-button-group>
                    </template>
                  </el-table-column>
                </el-table>
                <el-pagination
                    @size-change="sizeChange"
                    @current-change="currentChange"
                    background
                    :hide-on-single-page="applyCheckList.length <= pageSize"
                    :current-page="currentPage"
                    :page-sizes="[5, 10, 20, 50]"
                    :page-size="pageSize"
                    :current-page.sync="currentPage"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="applyCheckList.length">
                </el-pagination>
              </div>
            </el-collapse-transition>
          </el-card>


        </el-col>
<!--        <el-col :span="8">-->
<!--&lt;!&ndash;          这里是常用项目管理&ndash;&gt;-->
<!--          -->
<!--        </el-col>-->
      </el-row>
    </div>

    <div>
      <el-dialog title="添加项目" :visible.sync="addDialogVisible" width="80%">
        <el-form :model="addApplyForm" :rules="addApplyFormRules" ref="addApplyForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>
            <el-descriptions-item label="项目名称">
              <template>
                <el-form-item prop="name">
                  <el-select v-model="addApplyForm.name" placeholder="请选择项目">
                    <el-option v-for="item in itemName"
                               :key="item.ID"
                               :label="item.ItemName"
                               :value="item.ItemName">

                    </el-option>
                  </el-select>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="目的要求">
              <template>
                <el-form-item prop="objective">
                  <el-input type="textarea" v-model="addApplyForm.objective" placeholder="请输入目的要求"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="检查部位">
              <template>
                <el-form-item prop="position">
                  <el-input type="textarea" v-model="addApplyForm.position" placeholder="请输入检查部位"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="是否加急">
              <template>
                <el-form-item prop="isUrgent">
                  <el-radio-group v-model="addApplyForm.isUrgent">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                  </el-radio-group>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="数量">
              <template>
                <el-form-item prop="num">
                  <el-input-number v-model="addApplyForm.num" :min="1" label="请输入数量"></el-input-number>
<!--                  <el-input type="number" v-model="addApplyForm.num" style="width: 200px;" placeholder="请输入数量"></el-input>-->
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="开立时间">
              <template>
                <el-form-item prop="creationTime">
                  <el-date-picker
                      v-model="addApplyForm.creationTime"
                      type="datetime"
                      placeholder="选择开立时间"
                      default-time="12:00:00">
                  </el-date-picker>
                </el-form-item>
              </template>
            </el-descriptions-item>
          </el-descriptions>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button type="info" @click="resetApply('addApplyForm')" icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="addDialogVisible = false; resetApply('addApplyForm')" icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="addApply('addApplyForm')" icon="el-icon-plus">添 加</el-button>
          </el-button-group>

        </div>
      </el-dialog>

      <el-dialog title="修改项目" :visible.sync="editDialogVisible" width="80%" @close="editDialogVisible = false; resetApply('addApplyForm')">
        <el-form :model="addApplyForm" :rules="addApplyFormRules" ref="addApplyForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>
            <el-descriptions-item label="项目名称">
              <template>
                <el-form-item prop="name">
                  <el-select v-model="addApplyForm.name" placeholder="请选择项目">
                    <el-option v-for="item in itemName"
                               :key="item.ID"
                               :label="item.ItemName"
                               :value="item.ItemName">

                    </el-option>
                  </el-select>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="目的要求">
              <template>
                <el-form-item prop="objective">
                  <el-input type="textarea" v-model="addApplyForm.objective" placeholder="请输入目的要求"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="检查部位">
              <template>
                <el-form-item prop="position">
                  <el-input type="textarea" v-model="addApplyForm.position" placeholder="请输入检查部位"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="是否加急">
              <template>
                <el-form-item prop="isUrgent">
                  <el-radio-group v-model="addApplyForm.isUrgent">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                  </el-radio-group>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="数量">
              <template>
                <el-form-item prop="num">
                  <el-input-number v-model="addApplyForm.num" :min="1" label="请输入数量"></el-input-number>
<!--                  <el-input type="number" v-model="addApplyForm.num" style="width: 200px;" placeholder="请输入数量"></el-input>-->
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="开立时间">
              <template>
                <el-form-item prop="creationTime">
                  <el-date-picker
                      v-model="addApplyForm.creationTime"
                      type="datetime"
                      placeholder="选择开立时间"
                      default-time="12:00:00">
                  </el-date-picker>
                </el-form-item>
              </template>
            </el-descriptions-item>
          </el-descriptions>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button type="info" @click="resetApply('addApplyForm')" icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="editDialogVisible = false; resetApply('addApplyForm')" icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="editApply('addApplyForm')" icon="el-icon-edit">修 改</el-button>
          </el-button-group>

        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "InspectionApplication",
  data() {
    return {
      prop: 'RealName',
      searchByName: true,
      presentUser: {},
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
      patients: [],
      currentPageForPatient: 1,
      currentPage: 1,
      pageSizeForPatient: 3,
      pageSize: 5,
      loading: false,
      choosedPatient: {
        ID: '',
        RegistID: '',
        RealName: '',
        CaseNumber: '',
        Gender: '',
        Age: '',
      },
      checkApplyLoading: false,
      addDialogVisible: false,
      applyCheckList: [],
      applyList: {
        ID: '',
        MedicalID: '',
        RegistID: '',
        ItemID: '',
        Name: '',
        Objective: '',
        Position: '',
        IsUrgent: '',
        Num: '',
        CreationTime: '',
        DoctorID: '',
        CheckOperID: '',
        ResultOperID: '',
        CheckTime: '',
        Result: '',
        ResultTime: '',
        State: '',
        RecordType: '',
      },
      isChoose: false,
      addApplyForm: {
        id: '',
        medicalID: '',
        registID: '',
        itemID: '',
        name: '',
        objective: '',
        position: '',
        isUrgent: '',
        num: '',
        creationTime: '',
        doctorID: '',
        checkOperID: '',
        resultOperID: '',
        checkTime: '',
        result: '',
        resultTime: '',
        state: '',
        recordType: '',
      },
      addApplyFormRules: {
        id: [{required: true, message: '请输入ID', trigger: 'blur'}],
        medicalID: [{required: true, message: '请输入病历首页ID', trigger: 'blur'}],
        registID: [{required: true, message: '请输入病历号ID', trigger: 'blur'}],
        itemID: [{required: true, message: '请输入项目ID', trigger: 'blur'}],
        name: [{required: true, message: '请输入项目名称', trigger: 'blur'}],
        objective: [{required: true, message: '请输入目的要求', trigger: 'blur'}],
        position: [{required: true, message: '请输入检查部位', trigger: 'blur'}],
        isUrgent: [{required: true, message: '请输入是否加急', trigger: 'blur'}],
        num: [{required: true, message: '请输入数量', trigger: 'blur'}],
        creationTime: [{required: true, message: '请输入开立时间', trigger: 'blur'}],
        doctorID: [{required: true, message: '请输入开立医生ID', trigger: 'blur'}],
        checkOperID: [{required: true, message: '请输入检查人员ID', trigger: 'blur'}],
        resultOperID: [{required: true, message: '请输入结果录入人员ID', trigger: 'blur'}],
        checkTime: [{required: true, message: '请输入检查时间', trigger: 'blur'}],
        result: [{required: true, message: '请输入检查结果', trigger: 'blur'}],
        resultTime: [{required: true, message: '请输入结果时间', trigger: 'blur'}],
        state: [{required: true, message: '请输入状态', trigger: 'blur'}],
        recordType: [{required: true, message: '请输入记录类型', trigger: 'blur'}],
      },
      itemName: [],
      editDialogVisible: false,
    }
  },
  mounted() {
    this.initUser();// 初始化医生
    this.initPatient();
    if (this.isChoose === true) {
      this.initApply(this.choosedPatient.ID);
    }

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
    itemData() {
      if (this.applyCheckList.length > 0) {
        return this.applyCheckList.slice( (this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize) || [];
      }
      return this.applyCheckList;
    }
  },
  methods: {
    initUser() {
      // console.log("scope.row.userId: ", scope.row.userId);
      console.log("presentUser.id: ", this.presentUser.id);
      this.presentUser = JSON.parse(localStorage.getItem("User"));
      console.log("初始化医生：", this.presentUser);
    },
    currentChange(currentPage) {
      this.currentPage = currentPage;
      this.initApply(this.choosedPatient.ID);
    },
    sizeChange(currentSize) {
      this.pageSize = currentSize;
      this.initApply(this.choosedPatient.ID);
    },



    currentChangeForPatient(currentPage) {
      this.currentPageForPatient = currentPage;
      this.initPatient();
    },
    sizeChangeForPatient(currentSize) {
      this.pageSizeForPatient = currentSize;
      this.initPatient();
    },
    initPatient() {
      this.loading = true;
      let url = '/outpatientDoctorWorkStation/inspectionApplication/initPatients';
      let param = JSON.parse(localStorage.getItem("User"));
      this.$axios.post(url, param).then(res => {
        this.loading = false;
        console.log("所有的病人：", res);
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
        this.choosedPatient.ID = row.id;
        this.choosedPatient.RegistID = row.registID;
        // this.choosedPatient.Gender = row.gender;
        this.choosedPatient.Age = row.age;
      })

      this.applyList.MedicalID = row.id;
      // this.applyList.RegistID = row.id;
      this.initApply(row.id);// 初始化已选择患者的检查申请表信息
    },
    initApply(number) {
      let url = '/outpatientDoctorWorkStation/inspectionApplication/initApply';
      let param = {

        ID: number,
      }
      console.log("number = ", number);
      console.log("param = ", param);
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        console.log("初始化病历信息：", res);
        if (res.data.data) {
          this.applyCheckList = res.data.data;
          console.log("初始化病历信息2：", this.applyCheckList);

          this.applyList.ID = res.data.data.id;
          this.applyList.MedicalID = res.data.data.medicalID;
          this.applyList.RegistID = res.data.data.registID;
          this.applyList.ItemID = res.data.data.itemID;
          this.applyList.Name = res.data.data.name;
          this.applyList.Objective = res.data.data.objective;
          this.applyList.Position = res.data.data.position;
          this.applyList.IsUrgent = res.data.data.isUrgent;
          this.applyList.Num = res.data.data.num;
          this.applyList.CreationTime = res.data.data.creationTime;
          this.applyList.DoctorID = res.data.data.doctorID;
          this.applyList.CheckOperID = res.data.data.checkOperID;
          this.applyList.ResultOperID = res.data.data.resultOperID;
          this.applyList.CheckTime = res.data.data.checkTime;
          this.applyList.Result = res.data.data.result;
          this.applyList.ResultTime = res.data.data.resultTime;
          this.applyList.State = res.data.data.state;
          console.log("initApply的this.applyList", res.data.data);
        }

      })
    },
    applyState(row, column) {
      switch (row[column.property]) {
        case 7:
          return '已作废';
        case 1:
          return '暂存';
        case 2:
          return '已开立';
        case 3:
          return '已交费';
        case 4:
          return '已登记';
        case 5:
          return '执行完';
        case 6:
          return '已退费';
      }
    },
    tempApply(row) { //暂存
      let url = '/outpatientDoctorWorkStation/inspectionApplication/tempApply';
      row.state = 1;
      let param = row;
      console.log("即将暂存的项目：", param);
      this.$axios.post(url, param).then(res => {

      })
    },
    openEditApply(row) { //编辑
      this.editDialogVisible = true;
      this.initItemName();
      this.addApplyForm = row;
    },
    editApply() {
      this.$refs['addApplyForm'].validate((valid) => {
        if (valid) {
          let url = '/outpatientDoctorWorkStation/inspectionApplication/editApply';
          let param = this.addApplyForm;
          console.log("即将修改的项目：", param);
          this.$axios.post(url, param).then(res => {
            this.editDialogVisible = false;
          })
        }
      })
    },
    commitApply(row) { //开立
      let url = '/outpatientDoctorWorkStation/inspectionApplication/commitApply';
      row.state = 2;
      let param = row;
      console.log("即将开立的项目：", param);
      this.$axios.post(url, param).then(res => {

      })
    },
    deleteApply(row) { //删除
      let url = '/outpatientDoctorWorkStation/inspectionApplication/deleteApply';
      // row.state = 1;
      let param = row;
      console.log("即将删除的项目：", param);
      this.$axios.post(url, param).then(res => {
        this.initApply(this.choosedPatient.ID);
        console.log("currentPage = ", this.currentPage);
        console.log("pageSize = ", this.pageSize);
      })
    },
    cancelApply(row) { //作废
      if (row.state !== 6) { //未在医技登记
        let url = '/outpatientDoctorWorkStation/inspectionApplication/cancelApply';
        row.state = 7;
        let param = row;
        console.log("即将作废的项目：", param);
        this.$axios.post(url, param).then(res => {

        })
      } else {
        Element.Message.warning("此项目已被登记，无法作废！");
      }

    },
    openAddApply() {
      this.addDialogVisible = true;
      this.initItemName();
    },
    addApply(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log("ApplyList：", this.applyList);
          this.addApplyForm.medicalID = this.choosedPatient.ID;
          this.addApplyForm.registID = this.choosedPatient.RegistID;
          this.addApplyForm.doctorID = this.presentUser.id;
          this.addApplyForm.checkOperID = 1;
          this.addApplyForm.resultOperID = 1;
          this.addApplyForm.itemID = this.itemNameToID(this.addApplyForm.name);
          this.addApplyForm.state = 1;
          this.addApplyForm.recordType = 1;
          console.log("即将添加的项目：", this.addApplyForm);
          let url = '/outpatientDoctorWorkStation/inspectionApplication/addApply';
          let param = this.addApplyForm;
          this.$axios.post(url, param).then(res => {
            this.initApply(this.choosedPatient.ID);
          })
          this.addDialogVisible = false;
          this.$refs[formName].resetFields();
        }
      })

    },
    itemNameToID(name) {
      // console.log("数组长度：", this.itemName.length);
      for (let i=0; i<this.itemName.length; i++) {
        // console.log("执行第", i, "次循环");
        if (name === this.itemName[i].ItemName) {
          return this.itemName[i].ID;
        }
      }
      return null
    },
    resetApply(formName) {
      console.log("重置");
      this.addApplyForm = {};
      this.$refs[formName].resetFields();
      this.initApply(this.choosedPatient.ID);
    },
    initItemName() {
      let url = '/outpatientDoctorWorkStation/inspectionApplication/initItemName';
      this.$axios.post(url).then(res => {
        console.log("项目名称：", res);
        this.itemName = res.data.data;
      })
    }
  }
}
</script>

<style scoped>

</style>
