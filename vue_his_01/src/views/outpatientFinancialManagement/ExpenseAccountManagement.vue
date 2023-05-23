<template>
  <div>
    <el-card class="card">
      <!---  模糊检索  --->
      <div class="search-box">
        <el-form ref="formInline" :model="formInline" :inline="true" class="demo-form-inline">
          <el-form-item prop="name" class="form">
            <el-input v-model="formInline.expName" placeholder="请输入费用科目名称" class="input"></el-input>
          </el-form-item>
          <el-button
              round
              icon="el-icon-search"
              class="inputButton"
              @click="getInsTemplateAll()"
              type="primary">查询
          </el-button>
          <el-button
              round
              @click="enableAddDialog"
              icon="el-icon-plus"
              style="margin-top: 10px; margin-right: 10px; height: 40px; float: right"
              type="primary">添加费用项目
          </el-button>
        </el-form>
      </div>

      <!--   页面表格   -->
      <el-table :data="ExpenseClassList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                style="width: 100%; margin-top: 10px" stripe>
        <el-table-column label="id号" width="350">
          <template slot-scope="scope">
            <span style="color: #051525">{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="费用科目名称" width="350">
          <template slot-scope="scope">
            <span style="color: #051525">{{ scope.row.expName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="费用科目编码" width="350">
          <template slot-scope="scope">
            <span style="color: #051525">{{ scope.row.expCode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right">
          <template slot-scope="scope">
            <el-button
                type="primary"
                @click="enableUpdateDialog(scope.row)"
                size="mini">修改
            </el-button>
            <el-button
                type="danger"
                @click="enableDeleteDialog(scope.row)"
                size="mini">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--   页脚分页   -->
      <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="[4,8,12]"
                     :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="ExpenseClassList.length">
      </el-pagination>
    </el-card>

    <!--   修改模板dialog   -->
    <el-dialog
        :visible.sync="dialog.updateDialog"
        width="25%">
      <el-form ref="currentRow" :rules="addRowRules" :model="currentRow" label-width="110px">
        <el-form-item prop="expName" label="费用科目名称">
          <el-input v-model="currentRow.expName"></el-input>
        </el-form-item>
        <el-form-item prop="expCode" label="费用科目编码">
          <el-input v-model="currentRow.expCode"></el-input>
        </el-form-item>
        <div style="margin-top: 30px; margin-left: 50%">
          <el-button
              @click="dialog.updateDialog = false; updateInsTemplateAll(currentRow)"
              type="primary">
            确定
          </el-button>
          <el-button
              @click="dialog.updateDialog = false; resetForm('currentRow')"
              type="info">
            取消
          </el-button>
        </div>
      </el-form>
    </el-dialog>
    <!--   删除模板dialog   -->
    <el-dialog
        center
        title="删除信息窗口"
        :visible.sync="dialog.deleteDialog"
        width="20%">
      <h3>是否确定删除该信息</h3>
      <div style="margin-top: 50px; margin-left: 100px">
        <el-button
            @click="deleteInsTemplateAll(currentRow); dialog.deleteDialog = false"
            type="primary">
          确定
        </el-button>
        <el-button
            @click="dialog.deleteDialog = false"
            type="info">
          取消
        </el-button>
      </div>
    </el-dialog>
    <!--   增加模板dialog   -->
    <el-dialog
        title="增加信息窗口"
        :visible.sync="dialog.addDialog"
        width="25%">
      <el-form ref="addRow" :rules="addRowRules" :model="addRow" label-width="110px">
        <el-form-item prop="expName" label="费用科目名称">
          <el-input v-model="addRow.expName"></el-input>
        </el-form-item>
        <el-form-item prop="expCode" label="费用科目编码">
          <el-input v-model="addRow.expCode"></el-input>
        </el-form-item>
        <div style="margin-top: 30px; margin-left: 50%">
          <el-button
              @click="addSubmitForm('addRow')"
              type="primary">
            确定
          </el-button>
          <el-button
              @click="dialog.addDialog = false; resetForm('addRow')"
              type="info">
            取消
          </el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "ExpenseAccountManagement",
  data() {
    return {
      form: '',
      currentPage: 1,
      pageSize: 8,
      ExpenseClassList: [],
      recordTypeList: [
        {
          id: 1,
          name: '检查模板'
        },
        {
          id: 2,
          name: '检验模板'
        },
        {
          id: 3,
          name: '处置模板'
        }
      ],
      scopeList: [
        {
          id: '1',
          name: '全院'
        },
        {
          id: '2',
          name: '科室'
        },
        {
          id: '3',
          name: '个人'
        }
      ],
      formInline: {
        expName: ''
      },
      addRowRules: {
        expName: [{required: true, message: '请输入费用科目名称', trigger: 'blur'}],
        expCode: [{required: true, message: '请输入费用科目编码', trigger: 'blur'}],
      },
      currentRow: {},
      addRow: {
        expName: '',
        expCode: ''
      },
      dialog: {
        updateDialog: false,
        deleteDialog: false,
        addDialog: false
      }
    }
  },

  created() {
    this.getInsTemplateAll();
  },

  methods: {
    /**
     * 获取模板列表
     * @returns {Promise<void>}
     */
    async getInsTemplateAll() {
      const resp = await this.$axios
          .get(`/expenseclass/getExpenseClassAll?ExpName=${this.formInline.expName}`);
      this.ExpenseClassList = resp.data.data;

      /**
       * 数据过滤，将标记为删除的数据滤除
       */
      this.ExpenseClassList = this.tableDataNew;
    },
    /**
     * 删除模板（假删除，数据库标记给数据为删除）
     * @param param
     */
    deleteInsTemplateAll(param) {
      this.$axios.post(`/expenseclass/deleteExpenseClass`, param)
      window.setTimeout(this.getInsTemplateAll, 200)
    },
    /**
     * 修改模板
     * @param param
     */
    updateInsTemplateAll(param) {
      this.$axios.post(`/expenseclass/updateExpenseClass`, param)
      window.setTimeout(this.getInsTemplateAll, 200)
    },
    /**
     * 增加模板
     */
    addInsTemplate() {
      this.$axios.post(`/expenseclass/addExpenseClass`, this.addRow)
      window.setTimeout(this.getInsTemplateAll, 200)
      console.log(this.addRow)
    },

    /**
     * 增加表单提交验证
     * @param formName
     */
    addSubmitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.addInsTemplate()
          this.dialog.addDialog = false;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
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
     * @param formName
     */
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    /**
     * 显示修改弹窗
     * @param param
     */
    enableUpdateDialog(param) {
      this.dialog.updateDialog = true
      this.currentRow = param
    },
    /**
     * 显示删除弹窗
     * @param param
     */
    enableDeleteDialog(param) {
      this.dialog.deleteDialog = true
      this.currentRow = param
    },
    /**
     * 显示增加弹窗
     */
    enableAddDialog() {
      //获取当前操作员id
      const user = JSON.parse(localStorage.getItem("User"))
      this.addRow.userID = user.id
      this.dialog.addDialog = true
    }
  },

  computed: {
    /**
     * 数据过滤器
     * @returns {*[]}
     */
    tableDataNew: function () {
      return this.ExpenseClassList.filter((data) => {
        return data.delMark === 1
      })
    }
  }
}
</script>

<style scoped>
.card {
  background: #ececec;
}

.input {
  width: 300px;
}

.inputButton {
  margin-top: 10px;
  color: white;
}

.search-box {
  background: white;
  height: 60px;
}

.form {
  margin-top: 10px;
  margin-left: 10px;
}

</style>