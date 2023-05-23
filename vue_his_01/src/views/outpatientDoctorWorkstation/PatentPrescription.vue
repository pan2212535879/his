<template>
  <div>
    <div>
      <el-row :gutter="20">
        <el-col :span="6">
<!--      这里是搜索患者-->
          <el-card shadow="hover">
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
                  <!--              <el-descriptions-item label="备注">-->
                  <!--                <el-tag size="small">学校</el-tag>-->
                  <!--              </el-descriptions-item>-->
                  <!--              <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>-->
                </el-descriptions>
<!--                <div>-->
<!--                  <el-divider content-position="left"></el-divider>-->
<!--                </div>-->
              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>
        <el-col :span="8">
<!--      这里是待开处方患者列表-->
          <el-card shadow="hover">
            <div>
              <span>待开处方患者</span>
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
                    <el-tooltip class="item" effect="dark" content="选择此病人，并开立处方" placement="right">
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
        <el-col :span="10">
<!--      这里是常用药管理-->
          <el-card shadow="hover">
            <el-empty description="暂未选择处方明细" v-if="!isChooseDetail"></el-empty>
            <el-collapse-transition>
              <div v-show="isChooseDetail">
                <div>
                  <el-empty description="暂未选择详细" v-if="!isChooseDetail"></el-empty>
                  <el-collapse-transition>
                    <div v-show="isChooseDetail">
                      <el-radio-group v-model="templateRadio">
                        <el-radio :label="1" @change="initTemplate">全院</el-radio>
                        <el-radio :label="2" @change="initTemplate">科室</el-radio>
                        <el-radio :label="3" @change="initTemplate">个人</el-radio>
                      </el-radio-group>
                      <div>
                        <span>成药模板表</span>
                        <el-button :disabled="templateRadio===0" type="success" size="mini" @click="addTemplate" style="margin-left: 30px; margin-bottom: 5px" icon="el-icon-plus"></el-button>
                      </div>
                      <el-empty description="暂未选择模板范围" v-if="!isChooseTemplate"></el-empty>
                      <el-collapse-transition>
                        <div v-show="isChooseTemplate">
                          <el-table :data="drugsTemplateData"
                                    stripe
                                    border
                                    :default-sort = "{prop: 'date', order: 'descending'}"
                                    v-loading="templateLoading"
                                    max-height="250"
                                    min-height="250"
                                    element-loading-text="正在加载..."
                                    element-loading-spinner="el-icon-loading"
                                    element-loading-background="rgba(0, 0, 0, 0.8)"
                                    style="width: 100%">
                            <el-table-column align="center" prop="id" label="ID" width="50" show-overflow-tooltip></el-table-column>
                            <el-table-column align="center" prop="name" label="名称" width="100" show-overflow-tooltip>
                              <template slot-scope="scope">
                                <el-tag type="danger" size="mini">{{scope.row.name}}</el-tag>
                              </template>
                            </el-table-column>
                            <el-table-column align="center" prop="userID" label="医生 ID" width="50" show-overflow-tooltip></el-table-column>
                            <el-table-column align="center" prop="creationTime" label="创建时间" width="75" show-overflow-tooltip></el-table-column>
                            <el-table-column align="center" prop="scope" label="使用范围" width="75" show-overflow-tooltip>
                              <template slot-scope="scope">
                                <el-tag v-if="scope.row.scope === '全院'" type="danger" size="mini">{{scope.row.scope}}</el-tag>
                                <el-tag v-if="scope.row.scope === '科室'" type="warning" size="mini">{{scope.row.scope}}</el-tag>
                                <el-tag v-if="scope.row.scope === '个人'" type="success" size="mini">{{scope.row.scope}}</el-tag>
                              </template>
                            </el-table-column>
                            <el-table-column align="center" fixed="right" label="操作" width="100" show-overflow-tooltip>
                              <template slot-scope="scope">
                                <el-button-group>
                                  <div>
                                    <el-tooltip class="item" effect="dark" content="选择此模板，并查看模板详细" placement="right">
                                      <el-button type="text" size="mini" @click="chooseTemplate(scope.row)">选择</el-button>
                                    </el-tooltip>
                                    <el-divider direction="vertical"></el-divider>
                                    <el-button type="text" size="mini" @click="deleteTemplate(scope.row)">删除</el-button>
                                  </div>
                                </el-button-group>
                              </template>
                            </el-table-column>
                          </el-table>
                        </div>
                      </el-collapse-transition>
                    </div>
                  </el-collapse-transition>
                </div>
                <div>
                  <el-divider content-position="center"></el-divider>
                </div>
                <div>
                  <el-empty description="暂未选择模板" v-if="(!isChooseTemplateDetail) && (isChooseDetail) && (isChooseTemplate)" style="height: 200px"></el-empty>
                  <el-collapse-transition>
                    <div v-show="isChooseTemplateDetail">
                      <div>
                        <span>成药模板明细表</span>
                        <el-button size="mini" style="margin-left: 10px" type="success" @click="addTemplateDetailDialogVisible = true" icon="el-icon-plus"></el-button>
                      </div>
                      <el-table :data="drugsTemplateDetailData"
                                stripe
                                border
                                :default-sort = "{prop: 'date', order: 'descending'}"
                                v-loading="templateDetailLoading"
                                max-height="250"
                                min-height="250"
                                element-loading-text="正在加载..."
                                element-loading-spinner="el-icon-loading"
                                element-loading-background="rgba(0, 0, 0, 0.8)"
                                style="width: 100%">
                        <el-table-column align="center" prop="id" label="ID" width="50" show-overflow-tooltip></el-table-column>
                        <el-table-column align="center" prop="drugsTempID" label="成药模板 ID" width="50" show-overflow-tooltip></el-table-column>
                        <el-table-column align="center" prop="drugsID" label="药品 ID" width="50" show-overflow-tooltip></el-table-column>
                        <el-table-column align="center" prop="drugsUsage" label="用法" width="60" show-overflow-tooltip></el-table-column>
                        <el-table-column align="center" prop="dosage" label="用量" width="75" show-overflow-tooltip></el-table-column>
                        <el-table-column align="center" prop="frequency" label="频次" width="75" show-overflow-tooltip></el-table-column>
                        <el-table-column align="center" fixed="right" label="操作" width="100" show-overflow-tooltip>
                          <template slot-scope="scope">
                            <el-button-group>
                              <div>
                                <el-tooltip class="item" effect="dark" content="选择此模板明细，并应用" placement="right">
                                  <el-button type="text" size="mini" @click="applyTemplateDetail(scope.row)">应用</el-button>
                                </el-tooltip>
                                <el-divider direction="vertical"></el-divider>
                                <el-button type="text" size="mini" @click="deleteTemplateDetail(scope.row)">删除</el-button>
                              </div>
                            </el-button-group>
                          </template>
                        </el-table-column>
                      </el-table>
                    </div>
                  </el-collapse-transition>
                </div>
              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="15">
<!--      这里是患者成药处方表-->
          <el-card shadow="hover">
            <el-empty description="暂未选择患者" v-if="!isChoose"></el-empty>
            <el-collapse-transition>
              <div v-show="isChoose">
                <div>
                  <span>患者成药处方表</span>
                  <el-button size="mini" style="margin-left: 10px" type="success" @click="addDialogVisible = true" icon="el-icon-plus"></el-button>
                </div>
                <el-table :data="prescriptionData"
                          stripe
                          border
                          :default-sort = "{prop: 'date', order: 'descending'}"
                          v-loading="prescriptionLoading"
                          max-height="250"
                          min-height="250"
                          element-loading-text="正在加载..."
                          element-loading-spinner="el-icon-loading"
                          element-loading-background="rgba(0, 0, 0, 0.8)"
                          style="width: 100%">
                  <el-table-column align="center" prop="id" label="ID" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="medicalID" label="病历 ID" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="registID" label="挂号 ID" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="userID" label="开立医生 ID" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="prescriptionName" label="处方名称" width="90" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-tag type="primary" size="mini">{{scope.row.prescriptionName}}</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="prescriptionTime" label="开立时间" width="90" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="prescriptionState" label="处方状态" width="90" :formatter="applyState" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-tag v-if="scope.row.prescriptionState === 1" type="primary" size="mini">暂存</el-tag>
                      <el-tag v-if="scope.row.prescriptionState === 3" type="success" size="mini">已发送</el-tag>
                      <el-tag v-if="scope.row.prescriptionState === 4" type="warning" size="mini">已删除</el-tag>
                      <el-tag v-if="scope.row.prescriptionState === 0" type="danger" size="mini">已作废</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" fixed="right" label="操作" style="width: 100%">
                    <template slot-scope="scope">
                      <el-button-group>
                        <div>
                          <el-button style="color: #89e250" v-if="!(scope.row.prescriptionState===3)" :disabled="scope.row.prescriptionState===3" type="text" size="mini" @click="tempPrescription(scope.row)">暂存</el-button>
                          <el-button v-else :disabled="scope.row.prescriptionState===3" type="text" size="mini" @click="tempPrescription(scope.row)">暂存</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-button style="color: #e2cf1c" v-if="!(scope.row.prescriptionState===3 || scope.row.prescriptionState===0 || scope.row.prescriptionState===4)" :disabled="scope.row.prescriptionState===3 || scope.row.prescriptionState===0 || scope.row.prescriptionState===4" type="text" size="mini" @click="sendPrescription(scope.row)">发送</el-button>
                          <el-button v-else :disabled="scope.row.prescriptionState===3 || scope.row.prescriptionState===0 || scope.row.prescriptionState===4" type="text" size="mini" @click="sendPrescription(scope.row)">发送</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-button style="color: #fa7b0c" v-if="!(scope.row.prescriptionState===3)" :disabled="scope.row.prescriptionState===3" type="text" size="mini" @click="deletePrescription(scope.row)">删除</el-button>
                          <el-button v-else :disabled="scope.row.prescriptionState===3" type="text" size="mini" @click="deletePrescription(scope.row)">删除</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-button style="color: #fa0707" v-if="!(scope.row.prescriptionState!==3)" :disabled="scope.row.prescriptionState!==3" type="text" size="mini" @click="cancelPrescription(scope.row)">作废</el-button>
                          <el-button v-else :disabled="scope.row.prescriptionState!==3" type="text" size="mini" @click="cancelPrescription(scope.row)">作废</el-button>
                          <el-divider direction="vertical"></el-divider>
                          <el-tooltip class="item" effect="dark" content="选择此处方，并显示处方明细" placement="right">
                            <el-button type="text" size="mini" @click="chooseDetail(scope.row)">明细</el-button>
                          </el-tooltip>
                        </div>

                      </el-button-group>
                    </template>
                  </el-table-column>
                </el-table>
                <el-pagination
                    @size-change="sizeChange"
                    @current-change="currentChange"
                    background

                    :current-page="currentPage"
                    :page-sizes="[5, 10, 20, 50]"
                    :page-size="pageSize"
                    :current-page.sync="currentPage"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="prescription.length">
                </el-pagination>

              </div>
            </el-collapse-transition>
          </el-card>
        </el-col>
        <el-col :span="9">
<!--      这里是患者成药处方明细表-->
          <el-card shadow="hover">
            <el-empty description="暂未选择处方明细" v-if="!isChooseDetail"></el-empty>
            <el-collapse-transition>
              <div v-show="isChooseDetail">
                <div>
                  <span>患者成药处方明细表</span>
                  <el-button size="mini" style="margin-left: 10px" type="success" @click="addDrugDialogVisible = true" icon="el-icon-plus"></el-button>
                </div>
                <el-table
                    :data="detailData"
                    stripe
                    border
                    :default-sort = "{prop: 'date', order: 'descending'}"
                    v-loading="detailLoading"
                    max-height="250"
                    min-height="250"
                    element-loading-text="正在加载..."
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    style="width: 100%">
                  <el-table-column align="center" prop="id" label="ID" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="prescriptionID" label="成药处方 ID" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="drugsID" label="药品 ID" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="drugsName" label="药品名称" width="100" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <el-tag type="primary" size="mini">{{scope.row.drugsName}}</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="drugsUsage" label="用法" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="dosage" label="用量" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="frequency" label="频次" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="amount" label="数量" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" prop="state" label="状态" width="50" show-overflow-tooltip></el-table-column>
                  <el-table-column align="center" fixed="right" label="操作" width="50">
                    <template slot-scope="scope">
                      <el-button-group>
                        <el-button style="color: #ff0000" type="text" size="mini" @click="deleteDrugs(scope.row)">删药</el-button>
                      </el-button-group>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-collapse-transition>



          </el-card>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-dialog title="添加处方" :visible.sync="addDialogVisible" width="80%">
        <el-form :model="addPrescriptionForm" :rules="addPrescriptionFormRules" ref="addPrescriptionForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>

            <el-descriptions-item label="处方名称">
              <template>
                <el-form-item prop="prescriptionName">
                  <el-input v-model="addPrescriptionForm.prescriptionName" placeholder="请输入处方名称" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="开立时间">
              <template>
                <el-form-item prop="prescriptionTime">
                  <el-date-picker
                      v-model="addPrescriptionForm.prescriptionTime"
                      value-format="yyyy-MM-dd HH:mm:ss"
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
            <el-button type="info" @click="resetPrescription('addPrescriptionForm')" icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="addDialogVisible = false; resetPrescription('addPrescriptionForm')"  icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="addPrescription('addPrescriptionForm')"  icon="el-icon-plus">添 加</el-button>
          </el-button-group>

        </div>
      </el-dialog>
      <el-dialog title="添加药品" :visible.sync="addDrugDialogVisible" width="80%">
        <el-form :model="addDrugForm" :rules="addDrugFormRules" ref="addDrugForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>
            <el-descriptions-item label="药品名称">
              <template>
                <el-form-item prop="drugsName">
                  <el-tooltip placement="top">
                    <div slot="content">选中并输入关键字进行搜索<br/>支持模糊搜索</div>
                    <el-select
                        v-model="drugsValue"
                        filterable
                        size="mini"
                        style="width: 100px"
                        remote
                        clearable
                        loading-text="拼命加载中..."
                        reserve-keyword
                        placeholder="请输入药品关键词"
                        :remote-method="remoteMethod"
                        :loading="drugsLoading">
                      <el-option
                          v-for="item in drugsOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                      </el-option>
                    </el-select>
                  </el-tooltip>

                </el-form-item>
              </template>
            </el-descriptions-item>

            <el-descriptions-item label="用法">
              <template>
                <el-form-item prop="drugsUsage">
                  <el-input v-model="addDrugForm.drugsUsage" placeholder="请输入用法" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="用量">
              <template>
                <el-form-item prop="dosage">
                  <el-input v-model="addDrugForm.dosage" placeholder="请输入用量" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="频次">
              <template>
                <el-form-item prop="frequency">
                  <el-input v-model="addDrugForm.frequency" placeholder="请输入频次" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="数量">
              <template>
                <el-form-item prop="amount">
                  <el-input-number v-model="addDrugForm.amount" :min="1" label="请输入数量"></el-input-number>
<!--                  <el-input v-model="addDrugForm.amount" placeholder="请输入数量" clearable></el-input>-->
                </el-form-item>
              </template>
            </el-descriptions-item>
          </el-descriptions>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button type="info" @click="resetDrug('addDrugForm')"  icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="addDrugDialogVisible = false; resetDrug('addDrugForm')"  icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="addDrugs('addDrugForm')"  icon="el-icon-plus">添 加</el-button>
          </el-button-group>

        </div>
      </el-dialog>
      <el-dialog title="添加成药模板" :visible.sync="addTemplateDialogVisible" width="80%">
        <el-form :model="addTemplateForm" :rules="addTemplateFormRules" ref="addTemplateForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>
            <el-descriptions-item label="模板名称">
              <template>
                <el-form-item prop="name">
                  <el-input v-model="addTemplateForm.name" placeholder="请输入模板名称" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>

            <el-descriptions-item label="创建时间">
              <template>
                <el-form-item prop="creationTime">
                  <el-date-picker
                      v-model="addTemplateForm.creationTime"
                      type="datetime"
                      placeholder="选择开立时间"
                      default-time="12:00:00">
                  </el-date-picker>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="使用范围">
              <template>
                <el-form-item prop="scope">
                  <el-select v-model="addTemplateForm.scope" placeholder="请选择使用范围" clearable>
                    <el-option label="全院" value="全院"></el-option>
                    <el-option label="科室" value="科室"></el-option>
                    <el-option label="个人" value="个人"></el-option>
                  </el-select>
<!--                  <el-input v-model="addTemplateForm.scope" placeholder="请输入使用范围" clearable></el-input>-->
                </el-form-item>
              </template>
            </el-descriptions-item>

          </el-descriptions>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button type="info" @click="resetTemplate('addTemplateForm')" icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="addTemplateDialogVisible = false; resetTemplate('addTemplateForm')" icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="addTemplateConfirm('addTemplateForm')" icon="el-icon-plus">添 加</el-button>
          </el-button-group>

        </div>
      </el-dialog>
      <el-dialog title="添加成药明细模板" :visible.sync="addTemplateDetailDialogVisible" width="80%">
        <el-form :model="addTemplateDetailForm" :rules="addTemplateDetailFormRules" ref="addTemplateDetailForm" width="80%">
          <el-descriptions class="margin-top" :column="3" size="small" border>
            <el-descriptions-item label="药品名称">
              <template>
                <el-form-item prop="drugsName">
                  <el-tooltip placement="top">
                    <div slot="content">选中并输入关键字进行搜索<br/>支持模糊搜索</div>
                    <el-select
                        v-model="drugsValue"
                        filterable
                        size="mini"
                        style="width: 100px"
                        remote
                        clearable
                        loading-text="拼命加载中..."
                        reserve-keyword
                        placeholder="请输入药品关键词"
                        :remote-method="remoteMethod"
                        :loading="drugsLoading">
                      <el-option
                          v-for="item in drugsOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                      </el-option>
                    </el-select>
                  </el-tooltip>

                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="用法">
              <template>
                <el-form-item prop="drugsUsage">
                  <el-input v-model="addTemplateDetailForm.drugsUsage" placeholder="请输入用法" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>

            <el-descriptions-item label="用量">
              <template>
                <el-form-item prop="dosage">
                  <el-input v-model="addTemplateDetailForm.dosage" placeholder="请输入用量" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>
            <el-descriptions-item label="频次">
              <template>
                <el-form-item prop="frequency">
                  <el-input v-model="addTemplateDetailForm.frequency" placeholder="请输入频次" clearable></el-input>
                </el-form-item>
              </template>
            </el-descriptions-item>

          </el-descriptions>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button-group>
            <el-button type="info" @click="resetTemplateDetail('addTemplateDetailForm')" icon="el-icon-refresh">重 置</el-button>
            <el-button type="primary" @click="addTemplateDetailDialogVisible = false; resetTemplateDetail('addTemplateDetailForm')" icon="el-icon-close">取 消</el-button>
            <el-button type="success" @click="addTemplateDetailConfirm('addTemplateDetailForm')" icon="el-icon-plus">添 加</el-button>
          </el-button-group>

        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "PatentPrescription",
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
        RegistID: '',
        MedicalID: '',
      },
      patients: [],
      currentPageForPatient: 1,
      pageSizeForPatient: 3,
      prescription: [],
      // prescription: {
      //   ID: '',
      //   MedicalID: '',
      //   RegistID: '',
      //   UserID: '',
      //   PrescriptionName: '',
      //   PrescriptionTime: '',
      //   PrescriptionState: '',
      // },
      prescriptionRules: {
        ID: [{required: true, message: '请输入ID', trigger: 'blur'}],
        MedicalID: [{required: true, message: '请输入病历ID', trigger: 'blur'}],
        RegistID: [{required: true, message: '请输入挂号ID', trigger: 'blur'}],
        UserID: [{required: true, message: '请输入开立医生ID', trigger: 'blur'}],
        PrescriptionName: [{required: true, message: '请输入处方名称', trigger: 'blur'}],
        PrescriptionTime: [{required: true, message: '请输入开立时间', trigger: 'blur'}],
        PrescriptionState: [{required: true, message: '请输入处方状态', trigger: 'blur'}],
      },
      addPrescriptionForm: {
        id: '',
        medicalID: '',
        registID: '',
        userID: '',
        prescriptionName: null,
        prescriptionTime: null,
        prescriptionState: '',
      },
      addPrescriptionFormRules: {
        id: [{required: true, message: '请输入ID', trigger: 'blur'}],
        medicalID: [{required: true, message: '请输入病历ID', trigger: 'blur'}],
        registID: [{required: true, message: '请输入挂号ID', trigger: 'blur'}],
        userID: [{required: true, message: '请输入开立医生ID', trigger: 'blur'}],
        prescriptionName: [{required: true, message: '请输入处方名称', trigger: 'blur'}],
        prescriptionTime: [{required: true, message: '请输入开立时间', trigger: 'blur'}],
        prescriptionState: [{required: true, message: '请输入处方状态', trigger: 'blur'}],
      },
      addDialogVisible: false,
      currentPage: 1,
      currentPageForDetail: 1,
      pageSize: 5,
      pageSizeForDetail: 5,
      isChooseDetail: false,
      detailLoading: false,
      detail: [],
      addDrugDialogVisible: false,
      addDrugForm: {
        id: '',
        prescriptionID: '',
        drugsID: '',
        drugsUsage: '',
        dosage: '',
        frequency: '',
        amount: '',
        state: 2,
        drugsName: '',
      },
      addDrugFormRules: {
        id: [{required: true, message: '请输入ID', trigger: 'blur'}],
        prescriptionID: [{required: true, message: '请输入处方ID', trigger: 'blur'}],
        drugsID: [{required: true, message: '请输入药品ID', trigger: 'blur'}],
        drugsUsage: [{required: true, message: '请输入用法', trigger: 'blur'}],
        dosage: [{required: true, message: '请输入用量', trigger: 'blur'}],
        frequency: [{required: true, message: '请输入频次', trigger: 'blur'}],
        amount: [{required: true, message: '请输入数量', trigger: 'blur'}],
        state: [{required: true, message: '请输入药品状态', trigger: 'blur'}],
        drugsName: [{required: true, message: '请输入药品名称', trigger: 'blur'}],
      },
      drugsLoading: false,
      drugsValue: [],
      drugsOptions: [],
      drugsList: [],
      drugsStates: [],
      chooseTemp: null,
      templateLoading: false,
      drugsTemplate: [],
      currentPageForTemplate: 1,
      pageSizeForTemplate: 3,
      isChooseTemplate: false,
      templateRadio: 0,
      isChooseTemplateDetail: false,
      drugsTemplateDetail: [],
      currentPageForTemplateDetail: 1,
      pageSizeForTemplateDetail: 3,
      templateDetailLoading: false,
      tempTemplate: [],
      addTemplateDialogVisible: false,
      addTemplateForm: {
        id: '',
        name: '',
        userID: '',
        creationTime: '',
        scope: '',
        delMark: 1
      },
      addTemplateFormRules: {
        id: [{required: true, message: '请输入ID', trigger: 'blur'}],
        name: [{required: true, message: '请输入模板名称', trigger: 'blur'}],
        userID: [{required: true, message: '请输入医生ID', trigger: 'blur'}],
        creationTime: [{required: true, message: '请输入创建时间', trigger: 'blur'}],
        scope: [{required: true, message: '请输入使用范围', trigger: 'blur'}],
        delMark: 1
      },
      addTemplateDetailDialogVisible: false,
      addTemplateDetailForm: {
        id: '',
        drugsTempID: '',
        drugsID: '',
        drugsUsage: '',
        dosage: '',
        frequency: '',
        drugsName: ''
      },
      addTemplateDetailFormRules: {
        id: [{required: true, message: '请输入ID', trigger: 'blur'}],
        drugsTempID: [{required: true, message: '请输入成药模板ID', trigger: 'blur'}],
        drugsID: [{required: true, message: '请输入药品ID', trigger: 'blur'}],
        drugsUsage: [{required: true, message: '请输入用法', trigger: 'blur'}],
        dosage: [{required: true, message: '请输入用量', trigger: 'blur'}],
        frequency: [{required: true, message: '请输入频次', trigger: 'blur'}],
        drugsName: [{required: true, message: '请输入药品名称', trigger: 'blur'}],
      },
      prescriptionLoading: false,


    }
  },
  mounted() {
    this.initUser();
    this.initPatient();
    this.initDrugsPrepare();
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
    prescriptionData() {
      if (this.prescription.length > 0) {
        return this.prescription.slice( (this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize) || [];
      }
      return this.prescription;
    },
    detailData() {
      if (this.detail.length > 0) {
        return this.detail.slice( (this.currentPageForDetail - 1) * this.pageSizeForDetail, this.currentPageForDetail * this.pageSizeForDetail) || [];
      }
      return this.detail;
    },
    drugsTemplateData() {
      if (this.drugsTemplate.length > 0) {
        return this.drugsTemplate.slice( (this.currentPageForTemplate - 1) * this.pageSizeForTemplate, this.currentPageForTemplate * this.pageSizeForTemplate) || [];
      }
      return this.drugsTemplate;
    },
    drugsTemplateDetailData() {
      if (this.drugsTemplateDetail.length > 0) {
        return this.drugsTemplateDetail.slice( (this.currentPageForTemplateDetail - 1) * this.pageSizeForTemplateDetail, this.currentPageForTemplateDetail * this.pageSizeForTemplateDetail) || [];
      }
      return this.drugsTemplateDetail;
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
      this.initPrescription(this.choosedPatient.CaseNumber);
    },
    sizeChange(currentSize) {
      this.pageSize = currentSize;
      this.initPrescription(this.choosedPatient.CaseNumber);
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
            url = '/outpatientDoctorWorkStation/patentPrescription/searchByName';
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
            url = '/outpatientDoctorWorkStation/patentPrescription/searchByNumber';
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
    resetRecord(ruleForm) {
      this.$refs[ruleForm].resetFields();
    },
    reset(ruleForm) {
      this.$refs[ruleForm].resetFields();
      this.initPatient();
      // this.initHistoryPatient();
    },
    choosePatient(row) {
      // this.resetRecord('medicalRecord');
      console.log("选择的病人：", row);
      // this.$refs['medicalRecord'].resetFields();

      this.$nextTick(() => {
        this.isChoose = true;
        this.choosedPatient.RealName = row.realName;
        this.choosedPatient.CaseNumber = row.caseNumber;
        this.choosedPatient.Gender = row.gender;
        this.choosedPatient.Age = row.age;
        this.choosedPatient.RegistID = row.id;
        this.getMedicalID(row.caseNumber);
      })
      console.log("choosedPatient = ", this.choosedPatient);

      // this.medicalRecord.CaseNumber = row.caseNumber;
      // this.medicalRecord.RegistID = row.id;
      //this.initRecord(row.caseNumber);// 初始化已选择患者的病历信息
      // this.initDisease();
      this.initPrescription(row.caseNumber);// 初始化已选择患者的成药处方表
    },
    sizeChangeForPatient(currentSize) {
      this.pageSizeForPatient = currentSize;
      this.initPatient();
    },
    currentChangeForPatient(currentPage) {
      this.currentPageForPatient = currentPage;
      this.initPatient();
    },
    initPatient() {
      this.loading = true;
      let url = '/outpatientDoctorWorkStation/patentPrescription/initPatients';
      let param = JSON.parse(localStorage.getItem("User"));
      this.$axios.post(url, param).then(res => {
        this.loading = false;
        console.log("所有的病人：", res);
        this.patients = res.data.data;
      })
    },
    initPrescription(number) {
      console.log("患者的病历号CaseNumber：", number);
      let url = '/outpatientDoctorWorkStation/patentPrescription/initPrescription';
      let param = {
        CaseNumber: number
      };
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        console.log("初始化成药信息：", res.data.data);
        this.prescription = res.data.data;
        // this.prescription.MedicalID = res.data.data.medicalID;
        // this.prescription.RegistID = res.data.data.registID;
        // this.prescription.UserID = res.data.data.userID;
        // this.prescription.PrescriptionName = res.data.data.prescriptionName;
        // this.prescription.PrescriptionTime = res.data.data.prescriptionTime;
        // this.prescription.PrescriptionState = res.data.data.prescriptionState;
      })
    },
    tempPrescription(row) {
      let url = '/outpatientDoctorWorkStation/patentPrescription/tempPrescription';
      row.prescriptionState = 1;// 暂存
      let param = row;
      console.log("即将暂存的处方：", param);
      this.$axios.post(url, param).then(res => {

      })
    },
    sendPrescription(row) {
      let url = '/outpatientDoctorWorkStation/patentPrescription/sendPrescription';
      row.prescriptionState = 3;// 发送
      let param = row;
      console.log("即将发送的处方：", param);
      this.$axios.post(url, param).then(res => {

      })
    },
    // commitPrescription(row) {
    //   let url = '/outpatientDoctorWorkStation/patentPrescription/commitPrescription';
    //   row.prescriptionState = 2;// 开立
    //   let param = row;
    //   console.log("即将开立的处方：", param);
    //   this.$axios.post(url, param).then(res => {
    //
    //   })
    // },
    deletePrescription(row) {
      let url = '/outpatientDoctorWorkStation/patentPrescription/deletePrescription';
      row.prescriptionState = 4;// 删除
      let param = row;
      console.log("即将删除的处方：", param);
      this.$axios.post(url, param).then(res => {

      })
    },
    cancelPrescription(row) {
      let url = '/outpatientDoctorWorkStation/patentPrescription/cancelPrescription';
      row.prescriptionState = 0;// 作废
      let param = row;
      console.log("即将作废的处方：", param);
      this.$axios.post(url, param).then(res => {

      })
    },
    chooseDetail(row) {
      this.isChooseDetail = true;
      console.log("选择的处方：", row);
      this.initDetail(row);
      this.chooseTemp = row;
    },
    initDetail(row) {
      let url = '/outpatientDoctorWorkStation/patentPrescription/initDetail';
      let param = row;
      this.$axios.post(url, param).then(res => {
        if (res.data.data) {
          this.detail = res.data.data;
        }

        console.log("处方明细：", this.detail);
      })
      this.initDrugs();
    },
    addPrescription(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log("名称：", this.addPrescriptionForm.prescriptionName, ", 时间：", this.addPrescriptionForm.prescriptionTime);
          console.log("addPrescriptionForm：", this.addPrescriptionForm);
          // console.log("addPrescriptionForm-------------",this.choosedPatient.MedicalID);
          this.getMedicalID(this.choosedPatient.CaseNumber);//???
          this.addPrescriptionForm.medicalID = this.choosedPatient.MedicalID;
          this.addPrescriptionForm.registID = this.choosedPatient.RegistID;
          this.addPrescriptionForm.userID = this.presentUser.id;
          this.addPrescriptionForm.prescriptionState = 1;

          // console.log("前：", this.addPrescriptionForm.prescriptionName);
          console.log("即将添加的处方：", this.addPrescriptionForm);
          // console.log("后：", this.addPrescriptionForm.prescriptionName);
          let url = '/outpatientDoctorWorkStation/patentPrescription/addPrescription';
          let param = this.addPrescriptionForm;
          console.log("param-------------"+param);
          this.$axios.post(url, param).then(res => {
            this.initPrescription(this.choosedPatient.CaseNumber);
          })
          this.addDialogVisible = false;
          this.$refs[formName].resetFields();
        }
      })

    },
    getMedicalID(CaseNumber) { //病历号转换为病历ID
      let url = '/outpatientDoctorWorkStation/patentPrescription/getMedicalID';
      let param = {
        CaseNumber : CaseNumber
      }

      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        console.log("转换结果：", res.data.data)
        this.choosedPatient.MedicalID = res.data.data;
      })
    },
    resetPrescription(formName) {
      console.log("重置");
      this.addApplyForm = {};
      this.$refs[formName].resetFields();
      this.initPrescription(this.choosedPatient.CaseNumber);
    },
    addDrugs(formName) {
      // let str = this.drugsValue.toString();
      // console.log("选择的药品：", str);
      // let result = '';
      // let index = 0;
      // for (var i=0; i<str.length; i++) {
      //   if (str[i] === ':') {
      //     index = i;
      //     break;
      //   }
      // }
      // for (var i=index+1; i<str.length; i++) {
      //   result += str[i];
      // }
      this.addDrugForm.drugsName = this.drugsValue;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.addDrugForm.prescriptionID = this.chooseTemp.id;
          this.addDrugForm.drugsID = this.drugsValue;
          this.addDrugForm.state = 2;

          console.log("即将添加的药：", this.addDrugForm);
          let url = '/outpatientDoctorWorkStation/patentPrescription/addDrugs';
          let param = this.addDrugForm;
          this.$axios.post(url, this.$qs.stringify(param)).then(res => {
            this.initDetail(this.chooseTemp);
            this.resetDrug(formName);
            this.addDrugDialogVisible = false;
          })
        }

      })

    },
    deleteDrugs(row) {
      console.log("即将删除的药：", row);
      let url = '/outpatientDoctorWorkStation/patentPrescription/deleteDrugs';
      let param = row;
      this.$axios.post(url, param).then(res => {
        this.initDetail(this.chooseTemp);
      })

    },
    resetDrug(formName) {
      console.log("重置");
      this.addDrugForm = {};
      this.$refs[formName].resetFields();
    },
    remoteMethod(query) {
      if (query !== '') {
        this.drugsLoading = true;
        setTimeout(() => {
          this.drugsLoading = false;
          this.drugsOptions = this.drugsList.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.drugsOptions = [];
      }
    },
    initDrugsPrepare() {
      let url = '/outpatientDoctorWorkStation/patentPrescription/initDrugsPrepare';
      this.$axios.post(url).then(res => {
        this.drugsStates = res.data.data;
        console.log("drugsStates : ", res.data.data);

      })
    },
    initDrugs() {
      //this.initDiseasePrepare();
      var i = 0;
      console.log("执行initDisease");
      this.drugsList = this.drugsStates.map(item => {
        // console.log("ITEM = ", item);
        return {value: `${item.ID}`, label: `${item.DrugsName}`}
      })


    },
    initTemplate() {
      this.isChooseTemplate = true;
      let url = '';
      let param = '';
      if (this.templateRadio === 1) { // 全院
        url = '/outpatientDoctorWorkStation/patentPrescription/getDrugsTemplateByHospital'
        this.$axios.post(url).then(res => {
          console.log("初始化生成的模板：", res);
          this.drugsTemplate = res.data.data;
          console.log("this.templates.length:", this.drugsTemplate.length);
        })
      } else if (this.templateRadio === 2) { // 科室
        url = '/outpatientDoctorWorkStation/patentPrescription/getDrugsTemplateByDepartment'
        param = JSON.parse(localStorage.getItem("User"));
        this.$axios.post(url, param).then(res => {
          console.log("初始化生成的模板：", res);
          this.drugsTemplate = res.data.data;
        })
      } else { // 个人
        url = '/outpatientDoctorWorkStation/patentPrescription/getDrugsTemplatePersonally'
        param = JSON.parse(localStorage.getItem("User"));
        this.$axios.post(url, param).then(res => {
          console.log("初始化生成的模板：", res);
          this.drugsTemplate = res.data.data;
        })
      }
    },
    addTemplate() {
      this.addTemplateDialogVisible = true;
    },
    addTemplateConfirm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = '/outpatientDoctorWorkStation/patentPrescription/addTemplateConfirm';
          let param = this.addTemplateForm;
          this.addTemplateForm.userID = this.presentUser.id;
          console.log("即将添加的模板：", this.addTemplateForm);
          this.$axios.post(url, param).then(res => {
            this.initTemplate();
            this.addTemplateDialogVisible = false;
          })

        }
      })
    },
    resetTemplate(formName) {
      this.$refs[formName].resetFields();
    },
    chooseTemplate(row) {
      this.tempTemplate = row;
      this.isChooseTemplateDetail = true;
      console.log("选择的模板：", row);
      let url = '/outpatientDoctorWorkStation/patentPrescription/chooseTemplate';
      let param = row;
      this.$axios.post(url, param).then(res => {
        this.drugsTemplateDetail = res.data.data;
      })
    },
    deleteTemplate(row) {
      console.log("删除的模板：", row);
      let url = '/outpatientDoctorWorkStation/patentPrescription/deleteTemplate';
      let param = row;
      this.$axios.post(url, param).then(res => {
        this.initTemplate();
        this.isChooseTemplateDetail = false;
      })
    },
    applyTemplateDetail(row) {
      console.log("this.chooseTemp：", this.chooseTemp);

      this.addDrugForm.prescriptionID = this.chooseTemp.id;
      this.addDrugForm.drugsID = row.drugsID;
      this.addDrugForm.drugsUsage = row.drugsUsage;
      this.addDrugForm.dosage = row.dosage;
      this.addDrugForm.frequency = row.frequency;
      this.addDrugForm.amount = 1;
      this.addDrugForm.state = 2;
      console.log("即将应用的模板明细2：", this.addDrugForm);
       // this.addDrugs();
      // 1.直接把数据传到后端
      let url = '/outpatientDoctorWorkStation/patentPrescription/addDrugs';
      let param = this.addDrugForm;
      this.$axios.post(url, this.$qs.stringify(param)).then(res => {
        // 2.刷新成药处方明细表
        this.initDetail(this.chooseTemp);
        this.addDrugForm.prescriptionID = '';
        this.addDrugForm.drugsID = '';
        this.addDrugForm.drugsUsage = '';
        this.addDrugForm.dosage = '';
        this.addDrugForm.frequency = '';
        this.addDrugForm.amount = '';
      })


    },
    deleteTemplateDetail(row) {
      console.log("即将删除的模板明细：", row);
      let url = '/outpatientDoctorWorkStation/patentPrescription/deleteTemplateDetail';
      let param = row;
      this.$axios.post(url, param).then(res => {
        // 刷新成药明细表
        this.chooseTemplate(this.tempTemplate);
      })
    },
    addTemplateDetailConfirm(formName) {
      this.addTemplateDetailForm.drugsName = this.drugsValue;
      this.addTemplateDetailForm.drugsTempID = this.tempTemplate.id;
      this.addTemplateDetailForm.drugsID = this.drugsValue;

      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log("即将添加的成药模板明细：", this.addTemplateDetailForm);
          let url = '/outpatientDoctorWorkStation/patentPrescription/addTemplateDetailConfirm';
          let param = this.addTemplateDetailForm;
          this.$axios.post(url, param).then(res => {
            // 刷新成药明细表
            this.chooseTemplate(this.tempTemplate);
            this.addTemplateDetailDialogVisible = false;
          })
        }
      })
    },
    resetTemplateDetail(formName) {
      this.$refs[formName].resetFields();
    },
    applyState(row, column) {
      switch (row[column.property]) {
        case 0:
          return '已作废';
        case 1:
          return '暂存';
        case 3:
          return '已发送';
        case 4:
          return '已删除';
      }
    },
  }
}
</script>

<style scoped>

</style>
