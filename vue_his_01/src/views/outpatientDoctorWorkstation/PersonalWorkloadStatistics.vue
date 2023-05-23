<template>
  <div>
    <el-card class="card">
      <!--   表格盒子   -->
      <div class="table-box">
        <!---  模糊检索  --->
        <div class="search-box">
          <!--     本人信息盒子     -->
          <div class="user-info">
            <span style="font-size: large;font-weight: bolder">诊断医生:{{ userInfo.realName }}</span>
            <span style="margin-left: 30px;font-size: large;font-weight: bolder">诊断数量:{{ userInfo.registNum }}</span>
          </div>
          <el-form ref="dateForm" :model="dateForm">
            <!--     按钮盒子     -->
            <div style="float: right">
              <el-button @click="getRegisterByUserID" icon="el-icon-search" type="primary" plain round>查询</el-button>
              <el-button @click="resetForm" class="button-box" icon="el-icon-delete" type="info" plain round>清空</el-button>
            </div>
            <el-date-picker
                value-format="yyyy-MM-dd HH:mm:ss"
                v-model="dateForm.endDate"
                class="date-picker"
                placeholder="选择结束时间">
            </el-date-picker>
            <el-date-picker
                value-format="yyyy-MM-dd"
                v-model="dateForm.startDate"
                class="date-picker"
                placeholder="选择起始时间">
            </el-date-picker>
          </el-form>
        </div>
        <!--   页面表格   -->
        <el-table :data="registerList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                  style=" width: 100%; margin-top: 10px" stripe>
          <el-table-column label="患者编号" width="350">
            <template slot-scope="scope">
              <span style="color: #051525">{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column label="患者姓名" width="350">
            <template slot-scope="scope">
              <span style="color: #051525">{{ scope.row.realName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="看诊时间" width="350">
            <template slot-scope="scope">
              <span style="color: #051525">{{ scope.row.registTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="患者详情">
            <template slot-scope="scope">
              <el-button @click="getPatientCostListByID(scope.row)" type="text">点击查看详情...</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--   页脚分页   -->
        <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="currentPage"
                       :page-sizes="[2,8,16]"
                       :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="registerList.length">
        </el-pagination>
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

import moment from "moment";

export default {
  name: "PersonalWorkloadStatistics",
  data() {
    return {
      registerList: [],
      currentPage: 1,
      pageSize: 8,
      userInfo: {},
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
     * 获取本人的患者
     * @returns {Promise<void>}
     */
    async getRegisterByUserID() {
      const resp = await this.$axios
          .get(`/getRegisterAll?registerID=${this.getCurrentUser().id}&StartTime=${this.dateForm.startDate}&EndTime${this.dateForm.endDate}`)
      this.registerList = resp.data.data
      for (let i = 0; i < this.registerList.length; i++) {
        this.registerList[i].registTime = moment(this.registerList[i].registTime).format('YYYY-MM-DD HH:mm:ss');
      }
      this.setUserInfo()
    },
    /**
     * 获取患者费用列表
     * @returns {Promise<void>}
     */
    async getPatientCostListByID(param){
      this.dialog = true
      this.setRegisterInfo(param)
      const resp = await this.$axios
          .get(`/getPatientCostAll?RegistID=${this.registerInfo.id}&CreateOperID=${this.userInfo.id}`)
      this.patientCostList = resp.data.data
    },

    /**
     * 设置本人的基本信息
     */
    setUserInfo() {
      this.userInfo = this.getCurrentUser()
      this.userInfo.registNum = this.registerList.length
    },
    /**
     * 设置患者的基本信息
     * @param param
     */
    setRegisterInfo(param) {
      this.registerInfo = param;
    },
    /**
     * 获取当前操作员
     * @returns {any}
     */
    getCurrentUser() {
      return JSON.parse(localStorage.getItem("User"))
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
  },
  created() {
    this.getRegisterByUserID()
  }
}
</script>

<style scoped>
.table-box {
}

.user-info {
  float: left;
  margin-top: 20px;
  margin-left: 20px;
}

.information-box {
  background: white;
}

.card {
  background: #ececec;
}

.search-box {
  background: white;
  height: 60px;
}

.date-picker {
  margin-right: 10px;
  margin-top: 10px;
  float: right;
}

.button-box {
  margin-right: 10px;
  margin-top: 10px;
}
</style>