<template>
  <div>
<!--    <div>OutpatientMedicalRecordHomePage</div>-->
<!--    <div>门诊病历首页</div>-->

    <div>
      <el-row :gutter="20">
        <el-col :span="8">
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
            <!--          <div>-->
            <!--            <div v-if="choosedPatient.RealName">姓名：{{choosedPatient.RealName}}</div>-->
            <!--            <div v-if="choosedPatient.CaseNumber">病历号：{{choosedPatient.CaseNumber}}</div>-->
            <!--            <div v-if="choosedPatient.Gender">性别：{{choosedPatient.Gender}}</div>-->
            <!--            <div v-if="choosedPatient.Age">年龄：{{choosedPatient.Age}}</div>-->
            <!--          </div>-->
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
<!--                <div>-->
<!--                  <el-divider content-position="left"></el-divider>-->
<!--                </div>-->
              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>

        <el-col :span="8">
          <el-card shadow="hover">
            <!--          这里是待诊患者查询结果-->
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
                        style="width: 361px">
                <el-table-column align="center" prop="caseNumber" label="病历号" width="90"></el-table-column>
                <el-table-column align="center" prop="realName" label="姓名" width="90"></el-table-column>
                <el-table-column align="center" prop="age" label="年龄" width="90"></el-table-column>
                <el-table-column align="center" fixed="right" label="操作" width="90">
                  <template slot-scope="scope">
                    <el-tooltip class="item" effect="dark" content="选择此病人，并给出初步诊断" placement="right">
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
<!--            <div>-->
<!--              <el-divider content-position="left"></el-divider>-->
<!--            </div>-->
          </el-card>
        </el-col>


        <el-col :span="8">
          <el-card shadow="hover">
            <!--          这里是已诊患者查询结果-->
            <div>
              <span>已诊患者</span>
              <el-table :data="historyPatientData"
                        stripe
                        border
                        :default-sort = "{prop: 'date', order: 'descending'}"
                        v-loading="historyLoading"
                        max-height="250"
                        min-height="250"
                        element-loading-text="正在加载..."
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        style="width: 361px">
                <el-table-column align="center" prop="caseNumber" label="病历号" width="120"></el-table-column>
                <el-table-column align="center" prop="realName" label="姓名" width="120"></el-table-column>
                <el-table-column align="center" prop="age" label="年龄" width="120"></el-table-column>
              </el-table>
              <div style="display: flex;justify-content: flex-end">
                <el-pagination
                    background
                    @current-change="currentChangeForHistoryPatient"
                    @size-change="sizeChangeForHistoryPatient"
                    :page-size="pageSizeForHistoryPatient"

                    :current-page.sync="currentPageForHistoryPatient"
                    layout="prev, pager, next, jumper, ->, total, slot"
                    :total="historyPatients.length">
                  <!--                        :total="total">-->
                </el-pagination>
              </div>
            </div>
<!--            <div>-->
<!--              <el-divider content-position="left"></el-divider>-->
<!--            </div>-->
          </el-card>

        </el-col>
      </el-row>
      <div>
        <el-divider content-position="left"></el-divider>
      </div>
      <el-row :gutter="20">
        <el-col :span="16">
          <el-card shadow="hover">
            <!--          这里是暂存病历首页、提交病历首页、清屏、存为模板-->
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div class="grid-content bg-purple" v-show="isChoose">
                <el-form :model="medicalRecord" :rules="medicalRecordRules" ref="medicalRecord" label-position="top">
                  <el-row>
                    <el-col :span="12">
                      <el-form-item prop="Readme" label="主诉">
                        <el-input type="textarea" v-model="medicalRecord.Readme" placeholder="请输入主诉" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item prop="Present" label="现病史">
                        <el-input type="textarea" v-model="medicalRecord.Present" placeholder="请输入现病史" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item prop="PresentTreat" label="现病治疗情况">
                        <el-input type="textarea" v-model="medicalRecord.PresentTreat" placeholder="请输入现病治疗情况" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item prop="History" label="既往史">
                        <el-input type="textarea" v-model="medicalRecord.History" placeholder="请输入既往史" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item prop="Allergy" label="过敏史">
                        <el-input type="textarea" v-model="medicalRecord.Allergy" placeholder="请输入过敏史" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item prop="Physique" label="体格检查">
                        <el-input type="textarea" v-model="medicalRecord.Physique" placeholder="请输入体格检查" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item prop="Proposal" label="检查建议">
                        <el-input type="textarea" v-model="medicalRecord.Proposal" placeholder="请输入检查建议" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item prop="Careful" label="注意事项">
                        <el-input type="textarea" v-model="medicalRecord.Careful" placeholder="请输入注意事项" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item prop="CheckResult" label="检查结果">
                        <el-input type="textarea" v-model="medicalRecord.CheckResult" placeholder="请输入检查结果" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item prop="Diagnosis" label="诊断结果">
                        <el-tooltip placement="top">
                          <div slot="content">选中并输入关键字进行搜索<br/>支持模糊搜索</div>
                          <el-select
                              v-model="diseaseValue"
                              filterable
                              size="large"
                              style="width: 300px"
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
<!--                        <el-input type="textarea" v-model="medicalRecord.Diagnosis" placeholder="请输入诊断结果" style="width: 400px;margin-right: 10px" clearable></el-input>-->
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item prop="Handling" label="处理意见">
                        <el-input type="textarea" v-model="medicalRecord.Handling" placeholder="请输入处理意见" style="width: 300px;margin-right: 10px" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-button-group style="margin: 50px 25px" type="flex" justify="center">
                        <el-button type="primary" @click="saveRecord('medicalRecord')" icon="el-icon-refresh">暂存</el-button>
                        <el-button type="success" @click="commitRecord('medicalRecord')" icon="el-icon-check">提交</el-button>
                        <el-button type="warning" @click="resetRecord('medicalRecord')" icon="el-icon-close">清屏</el-button>
                      </el-button-group>
                    </el-col>
                  </el-row>
                </el-form>
              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover">
            <!--          这里是引用模板病历、常用诊断管理、查看历史病历-->
            <div v-if="isChoose">
              <div class="grid-content bg-purple">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                  <el-tab-pane label="病历模板" name="first">
                    <el-radio-group v-model="templateRadio">
                      <el-radio :label="1" @change="initTemplate">全院</el-radio>
                      <el-radio :label="2" @change="initTemplate">科室</el-radio>
                      <el-radio :label="3" @change="initTemplate">个人</el-radio>
                    </el-radio-group>
                    <el-button :disabled="templateRadio===0" type="success" size="mini" @click="addTemplate" style="margin-left: 30px; margin-bottom: 5px" icon="el-icon-plus"></el-button>
                    <el-empty description="暂未选择病历模板范围" v-if="templateRadio===0"></el-empty>
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
                                  style="width: 361px">
                          <el-table-column align="center" type="index" label="序号" width="80"></el-table-column>
                          <el-table-column align="center" prop="name" label="名称" width="140"></el-table-column>
                          <el-table-column align="center" fixed="right" label="操作" width="140">
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
                  <!--                <el-tab-pane label="常用诊断" name="second">常用诊断</el-tab-pane>-->
                  <el-tab-pane label="历史病历" name="third">
                    <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
                    <el-collapse-transition>
                      <div v-show="isChoose">
                        <el-table :data="historyRegister" style="width: 361px">
                          <el-table-column prop="VisitDate" label="看诊日期" width="100px"></el-table-column>
                          <el-table-column prop="deptName" label="科室" width="100px"></el-table-column>
                          <el-table-column prop="diagnosis" label="诊断结果" width="100px">
                            <template slot-scope="scope">
                              <el-tag type="danger">{{scope.row.diagnosis}}</el-tag>
                            </template>
                          </el-table-column>
                          <el-table-column fixed="right" label="操作" width="60px">
                            <template slot-scope="scope">
                              <el-button-group>
                                <el-button style="color: #0f4bf8" type="text" size="mini" @click="historyRegisterDetail(scope.row)">详细</el-button>
                              </el-button-group>
                            </template>
                          </el-table-column>
                        </el-table>
                        <el-empty description="暂未选择患者" v-if="!isChooseDetail"></el-empty>
                        <el-collapse-transition>
                          <div v-show="isChooseDetail && historyRegister.length!==0">
                            <el-descriptions title="历史病历明细" direction="vertical" :column="4" border>
                              <el-descriptions-item label="主诉">{{historyRegisterInfo.Readme}}</el-descriptions-item>
                              <el-descriptions-item label="现病史">{{this.historyRegisterInfo.Present}}</el-descriptions-item>
                              <el-descriptions-item label="体格检查" :span="2">{{this.historyRegisterInfo.Physique}}</el-descriptions-item>
                              <el-descriptions-item label="诊断结果">
                                <template>
                                  <el-tag type="danger">{{this.historyRegisterInfo.Diagnosis}}</el-tag>
                                </template>
                              </el-descriptions-item>
                            </el-descriptions>
                          </div>
                        </el-collapse-transition>
                      </div>
                    </el-collapse-transition>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </div>
            <div v-else>
              <el-empty description="暂未选择患者"></el-empty>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-dialog title="添加模板" :visible.sync="addDialogVisible" width="80%">
        <el-form :model="addTemplateForm" :rules="addTemplateFormRules" ref="addTemplateForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>
            <el-descriptions-item label="模板名称">
              <template>
                <el-form-item prop="Name">
                  <el-input v-model="addTemplateForm.Name" size="large" prefix-icon="el-icon-edit-outline" placeholder="请输入模板名称"></el-input>
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
            <el-descriptions-item label="主诉">
              <template>
                <el-form-item prop="Readme">
                  <el-input type="textarea" v-model="addTemplateForm.Readme" placeholder="请输入主诉"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="现病史">
              <el-form-item prop="Present">
                <el-input type="textarea" v-model="addTemplateForm.Present" placeholder="请输入现病史"></el-input>
              </el-form-item>
            </el-descriptions-item>
            <el-descriptions-item label="现病治疗情况">
              <template>
                <el-form-item prop="PresentTreat">
                  <el-input type="textarea" v-model="addTemplateForm.PresentTreat" placeholder="请输入现病治疗情况"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="既往史">
              <template>
                <el-form-item prop="History">
                  <el-input type="textarea" v-model="addTemplateForm.History" placeholder="请输入既往史"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="过敏史">
              <template>
                <el-form-item prop="Allergy">
                  <el-input type="textarea" v-model="addTemplateForm.Allergy" placeholder="请输入过敏史"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="体格检查">
              <template>
                <el-form-item prop="Physique">
                  <el-input type="textarea" v-model="addTemplateForm.Physique" placeholder="请输入体格检查"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="检查建议">
              <template>
                <el-form-item prop="Proposal">
                  <el-input type="textarea" v-model="addTemplateForm.Proposal" placeholder="请输入检查建议"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="注意事项">
              <template>
                <el-form-item prop="Careful">
                  <el-input type="textarea" v-model="addTemplateForm.Careful" placeholder="请输入注意事项"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="检查结果">
              <template>
                <el-form-item prop="CheckResult">
                  <el-input type="textarea" v-model="addTemplateForm.CheckResult" placeholder="请输入检查结果"></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="诊断结果">
              <template>
                <el-form-item prop="Diagnosis">
                  <el-tooltip placement="top">
                    <div slot="content">选中并输入关键字进行搜索<br/>支持模糊搜索</div>
                    <el-select
                        v-model="diseaseValueInTemplate"
                        filterable
                        size="large"
                        style="width: 300px"
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
          </el-descriptions>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button type="info" @click="resetTemplate('addTemplateForm')" icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="addDialogVisible = false; resetTemplate('addTemplateForm')" icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="commitTemplate('addTemplateForm')" icon="el-icon-check">添 加</el-button>
          </el-button-group>

        </div>
      </el-dialog>
    </div>
  </div>

</template>

<script>
import moment from 'moment';

export default {
  name: "OutpatientMedicalRecordHomePage",

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
      historyLoading: false,
      patients: [],
      historyPatients: [],
      currentPageForPatient: 1,
      currentPageForHistoryPatient: 1,
      pageSizeForPatient: 3,
      pageSizeForHistoryPatient: 3,
      isChoose: false,
      choosedPatient: {
        RealName: '',
        CaseNumber: '',
        Gender: '',
        Age: '',
      },
      medicalRecord: {
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
      medicalRecordRules: {
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
      activeName: 'first',
      templateRadio: 0,
      templates: [],
      templateLoading: false,
      addDialogVisible: false,
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
      historyRegister: [],
      historyRegisterInfo: {
        Readme: '',
        Present: '',
        Physique: '',
        Diagnosis: '',
      },
      isChooseDetail: false,
      deleteDialogVisible: false,

      diseaseLoading: false,
      diseaseValue: [],
      diseaseOptions: [],
      diseaseList: [],
      diseaseStates: [],

      diseaseLoadingInTemplate: false,
      diseaseValueInTemplate: [],
      diseaseOptionsInTemplate: [],
      diseaseListInTemplate: [],
      diseaseStatesInTemplate: [],



      // patientData: []
    }
  },
  mounted() {
    this.initUser();// 初始化医生
    this.initPatient();// 初始化患者挂号信息表
    this.initHistoryPatient();// 初始化已诊患者信息表
    // this.initChose();// 初始化已选择患者信息
    if (this.isChoose === true) {
      this.initTemplate();
    }
    this.initDiseasePrepare();
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
    historyPatientData() {
      if (this.historyPatients.length > 0) {
        return this.historyPatients.slice( (this.currentPageForHistoryPatient -1) * this.pageSizeForHistoryPatient, this.currentPageForHistoryPatient * this.pageSizeForHistoryPatient) || [];
      }
      return this.historyPatients;
    }
  },
  methods: {
    initUser() {
      // console.log("scope.row.userId: ", scope.row.userId);
      console.log("presentUser.id: ", this.presentUser.id);
      this.presentUser = JSON.parse(localStorage.getItem("User"));
      console.log("初始化医生：", this.presentUser);
    },
    canDelete(row) {
      return row.userID !== this.presentUser.id;
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
            url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/searchByName';
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
            url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/searchByNumber';
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
      this.initHistoryPatient();
    },
    initPatient() {
      this.loading = true;
      let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/initPatients';
      let param = JSON.parse(localStorage.getItem("User"));
      this.$axios.post(url, param).then(res => {
        this.loading = false;
        console.log("所有的病人：", res);
        this.patients = res.data.data;
      })
    },
    initHistoryPatient() {
      this.historyLoading = true;
      let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/initHistoryPatients';
      let param = JSON.parse(localStorage.getItem("User"));
      this.$axios.post(url, param).then(res => {
        this.historyLoading = false;
        console.log("所有的已诊病人：", res);
        this.historyPatients = res.data.data;
      })
    },
    sizeChangeForPatient(currentSize) {
      this.pageSizeForPatient = currentSize;
      this.initPatient();
    },
    sizeChangeForHistoryPatient(currentSize) {
      this.pageSizeForHistoryPatient = currentSize;
      this.initHistoryPatient();
    },
    currentChangeForPatient(currentPage) {
      this.currentPageForPatient = currentPage;
      this.initPatient();
    },
    currentChangeForHistoryPatient(currentPage) {
      this.currentPageForHistoryPatient = currentPage;
      this.initHistoryPatient();
    },
    initChose() {
      if (this.isChoose === true) {
        console.log("选择的患者：", )
      }
    },
    choosePatient(row) {
      this.resetRecord('medicalRecord');
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
      this.initDisease();
    },
    initRecord(number) {
      let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/initRecord';
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
          this.diseaseValue = res.data.data.diagnosis;
        }

      })
    },
    saveRecord(ruleForm) {
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
          this.medicalRecord.CaseState = 1;
          console.log("即将暂存的记录：", this.medicalRecord);
          let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/saveRecord';
          let param = this.$qs.stringify(this.medicalRecord);
          this.$axios.post(url, param).then(res => {
            console.log("暂存结果：", res);
          })
        }
      })
    },
    commitRecord(ruleForm) {
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
          this.medicalRecord.CaseState = 2;
          console.log("即将提交的记录：", this.medicalRecord);
          let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/commitRecord';
          let param = this.$qs.stringify(this.medicalRecord);
          this.$axios.post(url, param).then(res => {
            console.log("提交结果：", res);
          })
        }
      })
    },
    resetRecord(ruleForm) {
      this.$refs[ruleForm].resetFields();
    },
    handleClick(tab, event) {
      if (tab.label === "病历模板") {
        console.log("病历模板");
        this.isChooseDetail = false;
      } else {
        console.log("历史病历，选择的患者病历号：", this.choosedPatient.CaseNumber);
        // this.historyRegister
        let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/historyRegister';
        let param = this.$qs.stringify(this.choosedPatient);
        this.$axios.post(url, param).then(res => {
          console.log("历史病历号：", res);
          moment(res.data.data.visitDate).format('YYYY-MM-DD HH:mm:ss')
          this.historyRegister = res.data.data;
        })
      }
    },
    initTemplate() {
      let url = '';
      let param = '';
      if (this.templateRadio === 1) { // 全院
        url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/getRecordTemplateByHospital'
        this.$axios.post(url).then(res => {
          console.log("初始化生成的模板：", res);
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
        url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/getRecordTemplateByDepartment'
        param = JSON.parse(localStorage.getItem("User"));
        this.$axios.post(url, param).then(res => {
          console.log("初始化生成的模板：", res);
          this.templates = res.data.data;
        })
      } else { // 个人
        url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/getRecordTemplatePersonally'
        param = JSON.parse(localStorage.getItem("User"));
        this.$axios.post(url, param).then(res => {
          console.log("初始化生成的模板：", res);
          this.templates = res.data.data;
        })
      }

    },
    applyTemplate(row) {
      console.log("即将应用的模板：", row);
      this.$nextTick(() => {
        // this.medicalRecord = row;
        this.medicalRecord.Readme = row.readme;
        this.medicalRecord.Present = row.present;
        this.medicalRecord.PresentTreat = row.presentTreat;
        this.medicalRecord.History = row.history;
        this.medicalRecord.Allergy = row.allergy;
        this.medicalRecord.Physique = row.physique;
        this.medicalRecord.Proposal = row.proposal;
        this.medicalRecord.Careful = row.careful;
        this.medicalRecord.CheckResult = row.checkResult;
        this.diseaseValue = row.diagnosis;
        this.medicalRecord.Diagnosis = row.diagnosis;
        this.medicalRecord.Handling = row.handling;
      })
      console.log("即将应用的模板2：", this.medicalRecord);
    },
    deleteTemplate(row, index) {
      let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/deleteRecordTemplate';
      let param = row;
      this.$axios.post(url, param).then(res => {
        this.initTemplate();
        console.log("删除结果：", res);
        // this.templates.splice(index, 1);
      })


    },
    addTemplate() {
      this.addDialogVisible = true;

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
          let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/commitTemplate';
          this.addTemplateForm.DeptID = this.presentUser.deptID;
          this.addTemplateForm.UserID = this.presentUser.id;
          console.log("当前时间：", moment().format('YYYY-MM-DD HH:mm:ss'));
          this.addTemplateForm.CreationTime = moment().format('YYYY-MM-DD HH:mm:ss');
          let param = this.$qs.stringify(this.addTemplateForm);

          console.log("即将添加的模板：", param);
          this.$axios.post(url, param).then(res => {
            this.addDialogVisible = false;
            this.initTemplate();
          })
        }
      })
    },
    historyRegisterDetail(row) {
      this.isChooseDetail = true;
      // console.log("详细：", row);
      let url = '/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/historyRegisterDetail';
      this.$axios.post(url, row).then(res => {
        console.log("细节：", res);
        this.$nextTick(() => {
          this.historyRegisterInfo.Readme = res.data.data.readme;
          this.historyRegisterInfo.Present = res.data.data.present;
          this.historyRegisterInfo.Diagnosis = res.data.data.diagnosis;
          this.historyRegisterInfo.Physique = res.data.data.physique;
          console.log("细节2：", this.historyRegisterInfo);
        })  

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
  }
}
</script>

<style scoped>
[v-cloak]{
  display: none;
}
.el-table .warning-row {
  background: #ec1838;
}

.el-table .success-row {
  background: #3ae77f;
}


</style>
