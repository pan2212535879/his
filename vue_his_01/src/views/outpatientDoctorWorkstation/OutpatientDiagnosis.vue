<template>
  <div>
    <div>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card shadow="hover">
            <!--          这里是查询患者-->
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
          </el-card>

        </el-col>

        <el-col :span="12">
          <el-card shadow="hover">
            <!--          这里是待确诊患者-->
            <div>
              <span>待诊患者</span>
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
                        style="width: 401px">
                <el-table-column align="center" prop="caseNumber" label="病历号" width="100"></el-table-column>
                <el-table-column align="center" prop="realName" label="姓名" width="100"></el-table-column>
                <el-table-column align="center" prop="age" label="年龄" width="100"></el-table-column>
                <el-table-column align="center" fixed="right" label="操作" width="100">
                  <template slot-scope="scope">
                    <el-tooltip class="item" effect="dark" content="选择此病人，并给出最终诊断" placement="right">
                      <el-button type="text" size="mini" @click="choosePatient(scope.row)">选择</el-button>
                    </el-tooltip>

                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-card>

        </el-col>
      </el-row>
      <div>
        <el-divider content-position="center">确诊患者</el-divider>
      </div>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card shadow="hover">
            <!--          这里是门诊病历首页-->
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div v-show="isChoose">
                <el-form :model="medicalRecord" :rules="medicalRecordRules" ref="medicalRecord">
                  <el-descriptions class="margin-top" title="门诊病历首页" :column="3" size="mini" border>
                    <el-descriptions-item label="姓名">
                      <template>
                        <el-form-item prop="RealName">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.RealName"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="病历号">
                      <template>
                        <el-form-item prop="CaseNumber">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.CaseNumber"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="性别">
                      <template>
                        <el-form-item prop="Gender">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.Gender"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="年龄">
                      <template>
                        <el-form-item prop="Age">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.Age"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="主诉">
                      <template>
                        <el-form-item prop="Readme">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.Readme"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="现病史">
                      <template>
                        <el-form-item prop="Present">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.Present"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="现病治疗情况">
                      <template>
                        <el-form-item prop="PresentTreat">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.PresentTreat"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="既往史">
                      <template>
                        <el-form-item prop="History">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.History"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="过敏史">
                      <template>
                        <el-form-item prop="Allergy">
                          <el-input size="mini" :disabled="true" style="width: 100px" v-model="medicalRecord.Allergy"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="体格检查">
                      <template>
                        <el-form-item prop="Physique">
                          <el-input size="mini" type="textarea" style="width: 100px" v-model="medicalRecord.Physique"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="检查建议">
                      <template>
                        <el-form-item prop="Proposal">
                          <el-input size="mini" type="textarea" style="width: 100px" v-model="medicalRecord.Proposal"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="注意事项">
                      <template>
                        <el-form-item prop="Careful">
                          <el-input size="mini" type="textarea" style="width: 100px" v-model="medicalRecord.Careful"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="检查结果">
                      <template>
                        <el-form-item prop="CheckResult">
                          <el-input size="mini" type="textarea" style="width: 100px" v-model="medicalRecord.CheckResult"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="诊断结果">
                      <template>
                        <el-form-item prop="Diagnosis">
                          <el-tooltip placement="top">
                            <div slot="content">选中并输入关键字进行搜索<br/>支持模糊搜索</div>
                            <el-select
                                v-model="diseaseValue"
                                filterable
                                size="mini"
                                style="width: 100px"
                                remote
                                clearable
                                loading-text="拼命加载中..."
                                reserve-keyword
                                placeholder="请输入疾病关键词"
                                :remote-method="remoteMethod"
                                :loading="diseaseLoading">
                              <el-option
                                  v-for="item in diseaseOptions"
                                  :key="item.value"
                                  :label="item.label"
                                  :value="item.value">
                              </el-option>
                            </el-select>
                          </el-tooltip>

<!--                          <el-input size="mini" type="textarea" style="width: 100px" v-model="medicalRecord.Diagnosis"></el-input>-->
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                    <el-descriptions-item label="处理意见">
                      <template>
                        <el-form-item prop="Handling">
                          <el-input size="mini" type="textarea" style="width: 100px" v-model="medicalRecord.Handling"></el-input>
                        </el-form-item>
                      </template>
                    </el-descriptions-item>
                  </el-descriptions>
                  <el-button-group style="margin: 10px 180px">
                    <el-button type="danger" size="large" @click="commitResult('medicalRecord')" icon="el-icon-document-checked">确诊</el-button>
                    <el-button type="info" size="large" @click="resetResult('medicalRecord')" icon="el-icon-document-delete">重置</el-button>
                  </el-button-group>
                </el-form>
              </div>
            </el-collapse-transition>
          </el-card>

        </el-col>
        <el-col :span="12">
          <el-card shadow="hover">
            <!--          这里是常用诊断-->
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div v-show="isChoose">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                  <el-tab-pane label="常用诊断" name="first">
                    <el-radio-group v-model="templateRadio">
                      <el-radio :label="1" @change="initTemplate">全院</el-radio>
                      <el-radio :label="2" @change="initTemplate">科室</el-radio>
                      <el-radio :label="3" @change="initTemplate">个人</el-radio>
                    </el-radio-group>
                    <el-button :disabled="templateRadio===0" type="success" size="mini" @click="addTemplate" style="margin-left: 30px; margin-bottom: 5px" icon="el-icon-plus"></el-button>
                    <el-empty description="暂未选择诊断模板范围" v-if="templateRadio===0"></el-empty>
                    <el-collapse-transition>
                      <div v-show="templateRadio!==0">
                        <el-table :data="templates"
                                  stripe
                                  border
                                  :default-sort = "{prop: 'date', order: 'descending'}"
                                  v-loading="templateLoading"

                                  element-loading-text="正在加载..."
                                  element-loading-spinner="el-icon-loading"
                                  element-loading-background="rgba(0, 0, 0, 0.8)"
                                  style="width: 601px">
                          <el-table-column align="center" type="index" label="序号" width="100"></el-table-column>
                          <el-table-column align="center" prop="diagnosis" label="诊断结果" width="170"></el-table-column>
                          <el-table-column align="center" prop="handling" label="处理意见" width="170"></el-table-column>
                          <el-table-column align="center" fixed="right" label="操作" width="130">
                            <template slot-scope="scope">
                              <el-button-group>
                                <div>
                                  <el-button style="color: #2fea7b" type="text" size="mini" @click="applyTemplate(scope.row)">应用</el-button>
                                  <el-divider direction="vertical"></el-divider>
                                  <el-button style="color: #ff0606" :disabled="canDelete(scope.row)" type="text" size="mini" @click="deleteDialogVisible = true">删除</el-button>
                                </div>
                              </el-button-group>
                              <el-dialog
                                  title="警告"
                                  :visible.sync="deleteDialogVisible"
                                  append-to-body
                                  width="20%">
                                <span>确认删除？</span>
                                <span slot="footer" class="dialog-footer">
                              <el-button @click="deleteDialogVisible = false" icon="el-icon-refresh-left">取 消</el-button>
                              <el-button type="danger" @click="deleteTemplate(scope.row, scope.index);deleteDialogVisible = false" icon="el-icon-delete">确 定</el-button>
                            </span>
                              </el-dialog>
                            </template>
                          </el-table-column>
                        </el-table>

                      </div>
                    </el-collapse-transition>

                  </el-tab-pane>

                  <el-tab-pane label="检验结果" name="second">
                    <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
                    <el-collapse-transition>
                      <div v-show="isChoose">
                        <el-table :data="checkResult"
                                  stripe
                                  border
                                  :default-sort = "{prop: 'date', order: 'descending'}"
                                  v-loading="resultLoading"
                                  max-height="250"
                                  min-height="250"
                                  element-loading-text="正在加载..."
                                  element-loading-spinner="el-icon-loading"
                                  element-loading-background="rgba(0, 0, 0, 0.8)"
                                  style="width: 601px">
                          <el-table-column align="center" prop="name" label="项目名称" width="100"></el-table-column>
                          <el-table-column align="center" prop="objective" label="目的要求" width="100"></el-table-column>
                          <el-table-column align="center" prop="position" label="检查部位" width="100"></el-table-column>
                          <el-table-column align="center" prop="isUrgent" label="是否加急" width="100"></el-table-column>
                          <el-table-column align="center" prop="num" label="数量" width="100"></el-table-column>
                          <el-table-column align="center" prop="creationTime" label="开立时间" width="100"></el-table-column>
                          <el-table-column align="center" prop="checkTime" label="检查时间" width="100"></el-table-column>
                          <el-table-column align="center" prop="result" label="检查结果" width="100"></el-table-column>
                          <el-table-column align="center" prop="resultTime" label="结果时间" width="100"></el-table-column>
                          <el-table-column align="center" fixed="right" label="操作" width="100">
                            <template slot-scope="scope">
                              <el-tooltip class="item" effect="dark" content="选择此项目，并应用到患者的检查结果" placement="right">
                                <el-button style="color: #83e746" type="text" size="mini" @click="applyResult(scope.row)">应用</el-button>
                              </el-tooltip>

                            </template>
                          </el-table-column>
                        </el-table>
                        <!--                    <el-empty description="暂未选择患者" v-if="!isChooseDetail"></el-empty>-->
                      </div>
                    </el-collapse-transition>
                  </el-tab-pane>
                  <el-tab-pane label="处置结果" name="third">
                    <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
                    <el-collapse-transition>
                      <div v-show="isChoose">
                        <el-table :data="dealResult"
                                  stripe
                                  border
                                  :default-sort = "{prop: 'date', order: 'descending'}"
                                  v-loading="dealLoading"
                                  max-height="250"
                                  min-height="250"
                                  element-loading-text="正在加载..."
                                  element-loading-spinner="el-icon-loading"
                                  element-loading-background="rgba(0, 0, 0, 0.8)"
                                  style="width: 601px">
                          <el-table-column align="center" prop="name" label="项目名称" width="100"></el-table-column>
                          <el-table-column align="center" prop="objective" label="目的要求" width="100"></el-table-column>
                          <el-table-column align="center" prop="position" label="处置部位" width="100"></el-table-column>
                          <el-table-column align="center" prop="isUrgent" label="是否加急" width="100"></el-table-column>
                          <el-table-column align="center" prop="num" label="数量" width="100"></el-table-column>
                          <el-table-column align="center" prop="creationTime" label="开立时间" width="100"></el-table-column>
                          <el-table-column align="center" prop="checkTime" label="处置时间" width="100"></el-table-column>
                          <el-table-column align="center" prop="result" label="处置结果" width="100"></el-table-column>
                          <el-table-column align="center" prop="resultTime" label="结果时间" width="100"></el-table-column>
                          <el-table-column align="center" fixed="right" label="操作" width="100">
                            <template slot-scope="scope">
                              <el-tooltip class="item" effect="dark" content="选择此项目，并应用到患者的检查结果" placement="right">
                                <el-button style="color: #83e746" type="text" size="mini" @click="applyDeal(scope.row)">应用</el-button>
                              </el-tooltip>

                            </template>
                          </el-table-column>
                        </el-table>
                        <!--                    <el-empty description="暂未选择患者" v-if="!isChooseDetail"></el-empty>-->
                      </div>
                    </el-collapse-transition>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </el-collapse-transition>
          </el-card>

        </el-col>
      </el-row>
    </div>
    <div>
      <el-dialog title="添加模板" :visible.sync="addDialogVisible" width="80%">
        <el-form :model="addTemplateForm" :rules="addTemplateFormRules" ref="addTemplateForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>
            <el-descriptions-item label="诊断结果">
              <template>
                <el-form-item prop="Diagnosis">
                  <el-tooltip placement="top">
                    <div slot="content">选中并输入关键字进行搜索<br/>支持模糊搜索</div>
                    <el-select
                        v-model="diseaseValueInTemplate"
                        filterable
                        size="large"
                        style="width: 100%"
                        remote
                        clearable
                        loading-text="拼命加载中..."
                        reserve-keyword
                        placeholder="请输入疾病关键词"
                        :remote-method="remoteMethodInTemplate"
                        :loading="diseaseLoadingInTemplate">
                      <el-option
                          v-for="item in diseaseOptionsInTemplate"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                      </el-option>
                    </el-select>
                  </el-tooltip>
                  <!--                <el-input v-model="addTemplateForm.Diagnosis" size="mini" placeholder="请输入诊断结果"></el-input>-->
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="处理意见">
              <template>
                <el-form-item prop="Handling">
                  <el-input type="textarea" v-model="addTemplateForm.Handling" placeholder="请输入处理意见"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="使用范围">
              <template>
                <el-form-item prop="Scope">
                  <el-select v-model="addTemplateForm.Scope" placeholder="请选择使用范围">
                    <el-option label="全院" value="全院"></el-option>
                    <el-option label="科室" value="科室"></el-option>
                    <el-option label="个人" value="个人"></el-option>
                  </el-select>
                </el-form-item>
              </template>
            </el-descriptions-item>
          </el-descriptions>
<!--          <el-row>-->
<!--            <el-col :span="8">-->
<!--              -->
<!--            </el-col>-->
<!--            <el-col :span="8">-->
<!--              -->
<!--            </el-col>-->
<!--            <el-col :span="8">-->
<!--              -->
<!--            </el-col>-->
<!--          </el-row>-->

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button type="info" @click="resetTemplate('addTemplateForm')" icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="addDialogVisible = false; resetTemplate('addTemplateForm')" icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="commitTemplate('addTemplateForm')" icon="el-icon-plus">添 加</el-button>
          </el-button-group>

        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "OutpatientDiagnosis",
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
      patients: [],
      currentPageForPatient: 1,
      pageSizeForPatient: 3,
      isChoose: false,
      choosedPatient: {
        RealName: '',
        CaseNumber: '',
        Gender: '',
        Age: '',
      },
      medicalRecord: {
        RealName: '',
        CaseNumber: '',
        Gender: '',
        Age: '',
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
      medicalRecordRules: {
        RealName: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        CaseNumber: [{required: true, message: '请输入病历号', trigger: 'blur'}],
        Gender: [{required: true, message: '请输入性别', trigger: 'blur'}],
        Age: [{required: true, message: '请输入年龄', trigger: 'blur'}],
        Readme: [{required: true, message: '请输入主诉', trigger: 'blur'}],
        Present: [{required: true, message: '请输入现病史', trigger: 'blur'}],
        PresentTreat: [{required: true, message: '请输入现病治疗情况', trigger: 'blur'}],
        History: [{required: true, message: '请输入既往史', trigger: 'blur'}],
        Allergy: [{required: true, message: '请输入过敏史', trigger: 'blur'}],
        Physique: [{required: true, message: '请输入体格检查', trigger: 'blur'}],
        Proposal: [{required: true, message: '请输入检查建议', trigger: 'blur'}],
        Careful: [{required: true, message: '请输入注意事项', trigger: 'blur'}],
        CheckResult: [{required: true, message: '请输入检查结果', trigger: 'blur'}],
        Diagnosis: [{required: true, message: '请输入诊断结果', trigger: 'blur'}],
        Handling: [{required: true, message: '请输入处理意见', trigger: 'blur'}],
      },
      checkResult: [],
      dealResult: [],
      resultLoading: false,
      dealLoading: false,
      activeName: 'first',
      templateRadio: 0,
      addDialogVisible: false,
      templates: [],
      templateLoading: false,
      deleteDialogVisible: false,
      addTemplateForm: {
        Name: '',
        DeptID: null,
        UserID: null,
        CreationTime: '',
        Scope: '',
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
        DelMark: 1,
      },
      addTemplateFormRules: {
        Name: [{required: true, message: '请输入模板名称', trigger: 'blur'}],
        DeptID: [{required: true, message: '请输入部门ID', trigger: 'blur'}],
        UserID: [{required: true, message: '请输入医生ID', trigger: 'blur'}],
        CreationTime: [{required: true, message: '请输入创建时间', trigger: 'blur'}],
        Scope: [{required: true, message: '请输入使用范围', trigger: 'blur'}],
        Readme: [{required: true, message: '请输入主诉', trigger: 'blur'}],
        Present: [{required: true, message: '请输入现病史', trigger: 'blur'}],
        PresentTreat: [{required: true, message: '请输入现病治疗情况', trigger: 'blur'}],
        History: [{required: true, message: '请输入既往史', trigger: 'blur'}],
        Allergy: [{required: true, message: '请输入过敏史', trigger: 'blur'}],
        Physique: [{required: true, message: '请输入体格检查', trigger: 'blur'}],
        Proposal: [{required: true, message: '请输入检查建议', trigger: 'blur'}],
        Careful: [{required: true, message: '请输入注意事项', trigger: 'blur'}],
        CheckResult: [{required: true, message: '请输入检查结果', trigger: 'blur'}],
        Diagnosis: [{required: true, message: '请输入诊断结果', trigger: 'blur'}],
        Handling: [{required: true, message: '请输入处理意见', trigger: 'blur'}],
        DelMark: [{required: true, message: '请输入是否删除', trigger: 'blur'}],
      },

      diseaseLoading: false,
      diseaseValue: [],
      diseaseOptions: [],
      diseaseList: [],
      diseaseStates: [],
      isChooseCategory: false,
      diseaseCategoryLoading: false,
      diseaseCategoryValue: [],
      diseaseCategoryOptions: [],
      diseaseCategoryList: [],
      diseaseCategoryStates: [],
      diseaseLoadingInTemplate: false,
      diseaseValueInTemplate: [],
      diseaseOptionsInTemplate: [],
      diseaseListInTemplate: [],
      diseaseStatesInTemplate: [],




    }
  },
  mounted() {
    this.initUser();
    this.initPatient();
    this.initDiseasePrepare();
    //this.initCategoryDiseasePrepare();
    //this.initDisease();
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
    initUser() {
      // console.log("scope.row.userId: ", scope.row.userId);
      console.log("presentUser.id: ", this.presentUser.id);
      this.presentUser = JSON.parse(localStorage.getItem("User"));
      console.log("初始化医生：", this.presentUser);
    },
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
            url = '/outpatientDoctorWorkStation/outpatientDiagnosis/searchByName';
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
            url = '/outpatientDoctorWorkStation/outpatientDiagnosis/searchByNumber';
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
      // this.initHistoryPatient();
    },
    initPatient() {
      this.loading = true;
      let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/initPatients';
      let param = JSON.parse(localStorage.getItem("User"));
      this.$axios.post(url, param).then(res => {
        this.loading = false;
        console.log("所有的病人：", res);
        this.patients = res.data.data;
      })
    },
    choosePatient(row) {
      console.log("选择的病人：", row);
      this.$refs['medicalRecord'].resetFields();

      this.$nextTick(() => {
        this.isChoose = true;
        this.choosedPatient.RealName = row.realName;
        this.choosedPatient.CaseNumber = row.caseNumber;
        this.choosedPatient.Gender = row.gender;
        this.choosedPatient.Age = row.age;
      })

      this.medicalRecord.RealName = row.realName;
      this.medicalRecord.Gender = row.gender;
      this.medicalRecord.Age = row.age;
      this.medicalRecord.CaseNumber = row.caseNumber;

      // this.medicalRecord.RegistID = row.id;
      this.initRecord(row.caseNumber);// 初始化已选择患者的病历信息
      this.initCheckResult(row.caseNumber);// 初始化已选择患者的检查信息
      this.initDealResult(row.caseNumber);// 初始化已选选择的处置信息
      this.initDisease();
      //this.initCategoryDisease();

    },
    initRecord(number) {
      let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/initRecord';
      let param = {
        CaseNumber: number,
      }
      console.log("number = ", number);
      console.log("param = ", param);
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        console.log("初始化病历信息：", res);
        if (res.data.data) {
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
          this.medicalRecord.state = res.data.data.state;

          this.diseaseValue = res.data.data.diagnosis;
        }

      })
    },
    initCheckResult(number) {
      let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/initCheckResult';
      let param = {
        CaseNumber: number
      };
      console.log("CaseNumber = ", number);
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        console.log("患者的检查结果：", res.data.data);
        this.checkResult = res.data.data;
      })

    },
    initDealResult(number) {
      let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/initDealResult';
      let param = {
        CaseNumber: number
      };
      console.log("CaseNumber = ", number);
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        console.log("患者的处置结果：", res.data.data);
        this.dealResult = res.data.data;
      })
    },
    applyResult(row) {
      console.log("即将应用的结果：", row);
      this.medicalRecord.CheckResult = row.result;
    },
    applyDeal(row) {
      console.log("即将应用的结果：", row);
      this.medicalRecord.CheckResult = row.result;
    },
    commitResult(ruleForm) {
      let str = this.diseaseValue.toString();
      console.log("选择的疾病：", str);
      let result = '';
      let index = 0;
      for (var i=0; i<str.length; i++) {
        if (str[i] === ':') {
          index = i;
          break;
        }
      }
      for (var i=index+1; i<str.length; i++) {
        result += str[i];
      }
      this.medicalRecord.Diagnosis = result;
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          console.log("即将确诊的信息：", this.medicalRecord);
          let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/confirm';
          let param = this.$qs.stringify(this.medicalRecord);
          this.$axios.post(url, param).then(res => {

          })
        }
      })
    },
    resetResult(ruleForm) {
      this.$refs[ruleForm].fields.forEach(function (e) {
        // console.log("e.prop = ", e.prop);
        if(e.prop === 'Physique' || e.prop === 'Proposal' || e.prop === 'Careful' || e.prop === 'CheckResult' || e.prop === 'Diagnosis' || e.prop === 'Handling'){
          e.resetField();
        }
      })
    },
    handleClick(tab, event) {
      if (tab.label === "常用诊断") {
        console.log("常用诊断");
        // this.isChooseDetail = false;
      } else if (tab.label === "检验结果") {
        // console.log("历史病历，选择的患者病历号：", this.choosedPatient.CaseNumber);
        // // this.historyRegister
        // let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/historyRegister';
        // let param = this.$qs.stringify(this.choosedPatient);
        // this.$axios.post(url, param).then(res => {
        //   console.log("历史病历号：", res);
        //   moment(res.data.data.visitDate).format('YYYY-MM-DD HH:mm:ss')
        //   this.historyRegister = res.data.data;
        // })
      } else {

      }
    },
    initTemplate() {
      let url = '';
      let param = '';
      if (this.templateRadio === 1) { // 全院
        url = '/outpatientDoctorWorkStation/outpatientDiagnosis/getDiagnosisTemplateByHospital'
        this.$axios.post(url).then(res => {
          console.log("初始化生成的诊断：", res);
          this.templates = res.data.data;
          console.log("this.templates.length:", this.templates.length);
          for (var i=0; i<this.templates.length; i++){
            console.log("this.presentUser.ID:", this.presentUser.ID)
            console.log("this.presentUser.id:", this.presentUser.id)
            console.log("this.templates[i].UserID:", this.templates[i].UserID)
            console.log("this.templates[i].userID:", this.templates[i].userID)
            // if (this.presentUser.ID === this.templates[i].UserID) {
            //
            // }
          }
        })
      } else if (this.templateRadio === 2) { // 科室
        url = '/outpatientDoctorWorkStation/outpatientDiagnosis/getDiagnosisTemplateByDepartment'
        param = JSON.parse(localStorage.getItem("User"));
        this.$axios.post(url, param).then(res => {
          console.log("初始化生成的诊断：", res);
          this.templates = res.data.data;
        })
      } else { // 个人
        url = '/outpatientDoctorWorkStation/outpatientDiagnosis/getDiagnosisTemplatePersonally'
        param = JSON.parse(localStorage.getItem("User"));
        this.$axios.post(url, param).then(res => {
          console.log("初始化生成的诊断：", res);
          this.templates = res.data.data;
        })
      }

    },
    addTemplate() {
      this.addDialogVisible = true;

    },
    applyTemplate(row) {
      console.log("即将应用的模板：", row);
      this.$nextTick(() => {

        // this.medicalRecord = row;
        // this.medicalRecord.Readme = row.readme;
        // this.medicalRecord.Present = row.present;
        // this.medicalRecord.PresentTreat = row.presentTreat;
        // this.medicalRecord.History = row.history;
        // this.medicalRecord.Allergy = row.allergy;
        // this.medicalRecord.Physique = row.physique;
        // this.medicalRecord.Proposal = row.proposal;
        // this.medicalRecord.Careful = row.careful;
        // this.medicalRecord.CheckResult = row.checkResult;
        this.diseaseValue = row.diagnosis;
        this.medicalRecord.Diagnosis = row.diagnosis;
        this.medicalRecord.Handling = row.handling;
      })
      // console.log("即将应用的模板2：", this.medicalRecord);
    },
    deleteTemplate(row, index) {
      let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/deleteDiagnosisTemplate';
      let param = row;
      this.$axios.post(url, param).then(res => {
        this.initTemplate();
        console.log("删除结果：", res);
        // this.templates.splice(index, 1);
      })
    },
    canDelete(row) {
      console.log("row.userID : ", row.userID);
      console.log("this.presentUser.id : ", this.presentUser.id);
      return row.userID !== this.presentUser.id;
    },
    resetTemplate(ruleForm) {
      this.$refs[ruleForm].resetFields();
    },
    commitTemplate(formName) {
      let str = this.diseaseValueInTemplate.toString();
      console.log("选择的疾病：", str);
      let result = '';
      let index = 0;
      for (var i=0; i<str.length; i++) {
        if (str[i] === ':') {
          index = i;
          break;
        }
      }
      for (var i=index+1; i<str.length; i++) {
        result += str[i];
      }
      this.addTemplateForm.Diagnosis = result;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/commitTemplate';
          this.addTemplateForm.DeptID = this.presentUser.deptID;
          this.addTemplateForm.UserID = this.presentUser.id;
          console.log("当前时间：", moment().format('YYYY-MM-DD HH:mm:ss'));
          this.addTemplateForm.CreationTime = moment().format('YYYY-MM-DD HH:mm:ss');
          let param = this.$qs.stringify(this.addTemplateForm);

          console.log("即将添加的模板：", this.addTemplateForm);
          this.$axios.post(url, param).then(res => {
            this.addDialogVisible = false;
            this.initTemplate();
          })
        }
      })
    },
    remoteMethod(query) {
      if (query !== '') {
        this.diseaseLoading = true;
        setTimeout(() => {
          this.diseaseLoading = false;
          this.diseaseOptions = this.diseaseList.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.diseaseOptions = [];
      }
    },
    remoteMethodInTemplate(query) {
      if (query !== '') {
        this.diseaseLoadingInTemplate = true;
        setTimeout(() => {
          this.diseaseLoadingInTemplate = false;
          this.diseaseOptionsInTemplate = this.diseaseListInTemplate.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.diseaseOptionsInTemplate = [];
      }
    },
    initDiseasePrepare() {
      let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/initDiseasePrepare';
      this.$axios.post(url).then(res => {
        this.diseaseStates = res.data.data;
        this.diseaseStatesInTemplate = res.data.data;
        console.log("diseaseStates : ", res.data.data);

      })
    },
    initDisease() {
      //this.initDiseasePrepare();
        var i = 0;
        var j = 0;
        console.log("执行initDisease");
        this.diseaseList = this.diseaseStates.map(item => {
          return {value: `${++i}:${item}`, label: `${item}`}
        })
        this.diseaseListInTemplate = this.diseaseStatesInTemplate.map(item => {
          return {value: `${++j}:${item}`, label: `${item}`}
        })


    },
    initCategoryDiseasePrepare() {
      let url = '/outpatientDoctorWorkStation/outpatientDiagnosis/initCategoryDiseasePrepare';
      this.$axios.post(url).then(res => {
        this.diseaseCategoryStates = res.data.data;
        console.log("diseaseCategoryStates : ", res.data.data);

      })
    },
    initCategoryDisease() {
      //this.initDiseasePrepare();

      console.log("执行initCategoryDisease");
      var i = 0;
      this.diseaseCategoryList = this.diseaseCategoryStates.map(item => {
        return {value: `${++i}:${item}`, label: `${item}`}
      })


    },
    remoteCategoryMethod(query) {
      if (query !== '') {
        this.diseaseCategoryLoading = true;
        setTimeout(() => {
          this.diseaseCategoryLoading = false;
          this.diseaseCategoryOptions = this.diseaseCategoryList.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.diseaseCategoryOptions = [];
      }
    },

  }
}
</script>

<style scoped>
.aaa {
  height: 40px;
  width: 70px;
}
</style>