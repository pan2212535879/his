<template>
  <div>
    <el-card class="card">
      <!--   表格盒子   -->
      <div class="table-box">

        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="临床工作量统计" name="first">
            <div class="search-box">
              <!---  模糊检索  --->
              <el-form ref="dateForm" :model="dateForm">
                <el-date-picker
                    value-format="yyyy-MM-dd"
                    v-model="dateForm.startDate"
                    class="date-picker"
                    placeholder="选择起始时间">
                </el-date-picker>
                <el-date-picker
                    value-format="yyyy-MM-dd"
                    v-model="dateForm.endDate"
                    class="date-picker"
                    placeholder="选择结束时间">
                </el-date-picker>
                <!--     按钮盒子     -->
                <div style="float: left">
                  <el-button @click="search(1)" icon="el-icon-search" type="primary" plain round>查询</el-button>
                  <el-button @click="resetForm" class="button-box" icon="el-icon-delete" type="info" plain round>清空
                  </el-button>
                </div>
              </el-form>
            </div>
            <!--   页面表格   -->
            <el-table :data="clinicalDeptList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                      style=" width: 100%; margin-top: 10px"
                      border
                      stripe>
              <el-table-column fixed="left" label="科室名称" width="120">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.deptName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="西药费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.xyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="中成药" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.zcyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="中草药" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.cyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="挂号费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.ghf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="诊疗费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.zlf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="检验费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.jyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="处置费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.czf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="麻醉费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.mzf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="麻醉药费" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.mzyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="检验材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.jyclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="超声检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.csjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="超声材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.csclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="放射检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.fsjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="放射材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.fsclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="MRI检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.MRIjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="MRI材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.MRIclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="CT检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.CTjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="CT材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.CTclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="门诊手术" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.CTjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="处置材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.czclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column fixed="right" label="其它">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.czclf | rounding }}</span>
                </template>
              </el-table-column>
            </el-table>
            <!--   页脚分页   -->
            <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange"
                           :current-page="currentPage"
                           :page-sizes="[6,12,24]"
                           :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="clinicalDeptList.length">
            </el-pagination>
          </el-tab-pane>
          <el-tab-pane label="医技工作量统计" name="second">
            <div class="search-box">
              <!---  模糊检索  --->
              <el-form ref="dateForm" :model="dateForm">
                <el-date-picker
                    value-format="yyyy-MM-dd"
                    v-model="dateForm.startDate"
                    class="date-picker"
                    placeholder="选择起始时间">
                </el-date-picker>
                <el-date-picker
                    value-format="yyyy-MM-dd"
                    v-model="dateForm.endDate"
                    class="date-picker"
                    placeholder="选择结束时间">
                </el-date-picker>
                <!--     按钮盒子     -->
                <div style="float: left">
                  <el-button @click="search(2)" icon="el-icon-search" type="primary" plain round>查询</el-button>
                  <el-button @click="resetForm" class="button-box" icon="el-icon-delete" type="info" plain round>清空
                  </el-button>
                </div>
              </el-form>
            </div>
            <!--   页面表格   -->
            <el-table :data="clinicalDeptList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                      style=" width: 100%; margin-top: 10px"
                      border
                      stripe>
              <el-table-column fixed="left" label="科室名称" width="120">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.deptName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="西药费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.xyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="中成药" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.zcyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="中草药" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.cyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="挂号费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.ghf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="诊疗费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.zlf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="检验费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.jyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="处置费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.czf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="麻醉费" width="70">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.mzf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="麻醉药费" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.mzyf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="检验材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.jyclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="超声检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.csjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="超声材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.csclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="放射检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.fsjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="放射材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.fsclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="MRI检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.MRIjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="MRI材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.MRIclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="CT检查" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.CTjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="CT材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.CTclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="门诊手术" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.CTjcf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column label="处置材料" width="80">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.czclf | rounding }}</span>
                </template>
              </el-table-column>
              <el-table-column fixed="right" label="其它">
                <template slot-scope="scope">
                  <span style="color: #051525">{{ scope.row.czclf | rounding }}</span>
                </template>
              </el-table-column>
            </el-table>
            <!--   页脚分页   -->
            <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange"
                           :current-page="currentPage"
                           :page-sizes="[4,8,12]"
                           :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="clinicalDeptList.length">
            </el-pagination>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-card>

    <!--     患者信息提示框     -->
    <el-dialog width="50%" :visible.sync="dialog">
      <el-descriptions
          class="information-box"
          :column="3"
          border>
        <el-descriptions-item>
          <template slot="label">
            患者编号
          </template>
          {{ registerInfo.id }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            患者姓名
          </template>
          {{ registerInfo.realName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            看诊时间
          </template>
          {{ registerInfo.registTime }}
        </el-descriptions-item>
        <el-descriptions-item span="3">
          <template slot="label">
            收费详情
          </template>
          <el-table :show-header="false" :data="patientCostList">
            <el-table-column width="200">
              <template slot-scope="scope">
                <span style="color: #051525">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column width="200">
              <template slot-scope="scope">
                <span v-if="scope.row.expName!==null" style="color: #051525">{{ scope.row.expName }}</span>
                <span v-else style="color: #051525">药费</span>
              </template>
            </el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <span style="color: #051525">{{ scope.row.price }}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-descriptions-item>
      </el-descriptions>
      <div style="margin-top: 30px; margin-left: 80%">
        <el-button
            @click="dialog = false"
            type="primary">
          确定
        </el-button>
        <el-button
            @click="dialog= false"
            type="info">
          取消
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "WorkloadStaticsOfOutpatientDepartment",
  data() {
    return {
      activeName: 'first',
      clinicalDeptList: [],
      testList: [],
      medicalDeptList: [],
      workloadStaticsList: [],
      currentPage: 1,
      pageSize: 8,
      registerInfo: {},
      patientCostList: {},
      dialog: false,
      dateForm: {
        startDate: '',
        endDate: ''
      },
    }
  },
  methods: {
    /**
     * 获取各科室的工作记录
     * @returns {Promise<void>}
     */
    async getRegisterByUserID() {
      const resp = await this.$axios
          .get(`/outpatientFinancial/getWorkloadStatisticsAll?StartTime=${this.dateForm.startDate}&EndTime${this.dateForm.endDate}`)
      this.workloadStaticsList = resp.data.data
      console.log(this.workloadStaticsList)
    },
    /**
     * 获取科室列表
     * @returns {Promise<void>}
     */
    async getDeptList(type) {
      const resp = await this.$axios
          .get(`/outpatientFinancial/getDepartmentAll?DeptType=${type}`)
      this.clinicalDeptList = resp.data.data
    },

    /**
     * 计算各科室费用
     * 无奈方式，请不要复用
     */
    test() {
      for (let i = 0; i < this.clinicalDeptList.length; i++) {
        for (let j = 0; j < this.workloadStaticsList.length; j++) {
          if (this.workloadStaticsList[j].deptName === this.clinicalDeptList[i].deptName) {
            switch (this.workloadStaticsList[j].constantName) {
              case '西药':
                this.clinicalDeptList[i].xyf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '中成药':
                this.clinicalDeptList[i].zcyf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '中草药':
                this.clinicalDeptList[i].cyf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
            }
            switch (this.workloadStaticsList[j].expName) {
              case '挂号费':
                this.clinicalDeptList[i].ghf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '诊疗费':
                this.clinicalDeptList[i].zlf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '检验费':
                this.clinicalDeptList[i].jyf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '处置费':
                this.clinicalDeptList[i].czf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '检验材料费':
                this.clinicalDeptList[i].jyclf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '超声检查费':
                this.clinicalDeptList[i].csjcf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '超声材料费':
                this.clinicalDeptList[i].csclf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '放射检查费':
                this.clinicalDeptList[i].fsjcf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '放射材料费':
                this.clinicalDeptList[i].fsclf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case 'MRI检查费':
                this.clinicalDeptList[i].mrijcf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case 'MRI材料费':
                this.clinicalDeptList[i].mriclf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case 'CT检查费':
                this.clinicalDeptList[i].ctjcf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case 'CT材料费':
                this.clinicalDeptList[i].ctclf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '处置材料费':
                this.clinicalDeptList[i].czclf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '麻醉费':
                this.clinicalDeptList[i].mzf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '麻醉药费':
                this.clinicalDeptList[i].mzyf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '门诊手术费':
                this.clinicalDeptList[i].mzssf += (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
              case '其它':
                this.clinicalDeptList[i].qt+= (this.workloadStaticsList[j].price*this.workloadStaticsList[j].amount);
                break;
            }
          }
        }
      }
    },
    /**
     * 切换页面大小
     * @param val
     */
    handleSizeChange(val) {
      this.currentPage = 1;
      this.pageSize = val;
    },
    /**
     * 当前页改变时触发 跳转其他页
     * @param val
     */
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    /**
     * 重置搜索表单
     */
    resetForm() {
      this.dateForm.startDate = ''
      this.dateForm.endDate = ''
    },

    handleClick(tab, event) {
      if(this.activeName === 'first') {
        this.getDeptList(1)
        window.setTimeout(this.test, 200)
      } else if(this.activeName === 'second') {
        this.getDeptList(2)
        window.setTimeout(this.test, 200)
      }
    },

    resetExpense(param){
      param.ghf = 0;
      param.zlf = 0;
      param.jyf = 0;
      param.jyclf = 0;
      param.csjcf = 0;
      param.csclf = 0;
      param.fsjcf = 0;
      param.fsclf = 0;
      param.MRIjcf = 0;
      param.MRIclf = 0;
      param.CTjcf = 0;
      param.CTclf = 0;
      param.xyf = 0;
      param.zcyf = 0;
      param.cyf = 0;
      param.czf = 0;
      param.czclf = 0;
      param.mzf = 0;
      param.mzyf = 0;
      param.mzssf = 0;
      param.qt = 0;
    },

    search(type){
      this.getDeptList(type);
      this.getRegisterByUserID();
      window.setTimeout(this.test, 200)
    }
  },
  created() {
    this.getRegisterByUserID()
    this.getDeptList(1)
    window.setTimeout(this.test, 200)
  },

  filters: {
    rounding (value) {
      if (!value) {//在这里进行一次传递数据判断.如果传递进来的为空值,返回其空字符串.解决其问题
        return 0;
      }
      return value.toFixed(2);
    }
  }
}
</script>

<style scoped>
.table-box {
}

.information-box {
  background: white;
}

.card {
  background: #ececec;
}

.search-box {
  height: 40px;
}

.date-picker {
  margin-right: 10px;
  float: left;
}

.button-box {
  margin-right: 10px;
}

strong {
  margin-top: 15px;
  margin-left: 10px;
  font-size: 24px;
  float: left;
  color: #575757;
}
</style>