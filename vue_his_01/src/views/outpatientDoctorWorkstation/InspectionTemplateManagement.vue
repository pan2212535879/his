<template>
  <div>
    <el-card class="card">
      <!---  模糊检索  --->
      <div class="search-box">
        <el-form ref="formInline" :rules="formInlineRules" :model="formInline" :inline="true" class="demo-form-inline">
          <el-form-item prop="userID" label="创建者" class="form">
            <el-select clearable v-model="formInline.userID" filterable placeholder="创建者">
              <template v-for="item in userList">
                <el-option :label="item.realName" :value="item.id" :key="item.id"></el-option>
              </template>
            </el-select>
          </el-form-item>
          <el-form-item prop="recordType" label="模板类型" class="form">
            <el-select clearable v-model="formInline.recordType" filterable placeholder="模板类型">
              <template v-for="item in recordTypeList">
                <el-option :label="item.name" :value="item.id" :key="item.id"></el-option>
              </template>
            </el-select>
          </el-form-item>
          <el-form-item prop="name" class="form">
            <el-input v-model="formInline.name" placeholder="请输入模板名称" class="input"></el-input>
          </el-form-item>
          <el-button
              round
              size="mini"
              icon="el-icon-search"
              class="inputButton"
              @click="getInsTemplateAll()"
              style="margin-top: 16px"
              type="primary">查询
          </el-button>
          <el-button
              round
              size="mini"
              icon="el-icon-delete"
              @click="resetForm('formInline')"
              style="margin-top: 16px"
              type="info">清空
          </el-button>
          <el-button
              round
              size="mini"
              @click="enableAddDialog"
              icon="el-icon-plus"
              style="margin-top: 16px; margin-right: 10px; float: right"
              type="primary">添加模板
          </el-button>
        </el-form>
      </div>

      <!--   页面表格   -->
      <el-table :data="insTemplateAllList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                style="width: 100%; margin-top: 10px" stripe>
        <el-table-column label="模板编号" width="170">
          <template slot-scope="scope">
            <span style="color: #051525">{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="模板名称" width="180">
          <template slot-scope="scope">
            <span style="color: #051525">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="模板作者" width="170">
          <template slot-scope="scope">
            <span style="color: #051525">{{ getUserName(scope.row.userID) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" width="220">
          <template slot-scope="scope">
            <span style="color: #051525">{{ scope.row.creationTime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="适用范围" width="170">
          <template slot-scope="scope">
            <span style="color: #051525">{{ getScopeName(scope.row.scope) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="模板类型" width="170">
          <template slot-scope="scope">
            <span style="color: #051525">{{ getRecordTypeName(scope.row.recordType) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="150">
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
                     :page-sizes="[1,5,10,20]"
                     :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="insTemplateAllList.length">
      </el-pagination>
    </el-card>

    <!--   修改模板dialog   -->
    <el-dialog
        :visible.sync="dialog.updateDialog"
        width="50%">
      <el-form :model="currentRow">
        <el-descriptions title="修改信息" :column="3" border>
          <el-descriptions-item span="1" label="模板名称">
            <el-form-item style="margin: 0;padding: 0">
              <el-input v-model="currentRow.name"></el-input>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="2" label="创建时间">
            <el-form-item style="margin: 0;padding: 0">
              <el-input :disabled="true" v-model="currentRow.creationTime"></el-input>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="1" label="模板作者">
            <el-form-item style="margin: 0;padding: 0">
              <el-select clearable v-model="currentRow.userID" filterable placeholder="创建者">
                <template v-for="item in userList">
                  <el-option :label="item.realName" :value="item.id" :key="item.id"></el-option>
                </template>
              </el-select>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="1" label="适用范围">
            <el-form-item style="margin: 0;padding: 0">
              <el-select clearable v-model="currentRow.scope" filterable placeholder="模板类型">
                <template v-for="item in scopeList">
                  <el-option :label="item.name" :value="item.id" :key="item.id"></el-option>
                </template>
              </el-select>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="1" label="模板类型">
            <el-form-item style="margin: 0;padding: 0">
              <el-select clearable v-model="currentRow.recordType" filterable placeholder="模板类型">
                <template v-for="item in recordTypeList">
                  <el-option :label="item.name" :value="item.id" :key="item.id"></el-option>
                </template>
              </el-select>
            </el-form-item>
          </el-descriptions-item>
        </el-descriptions>
        <div style="margin-top: 30px; margin-left: 70%">
          <el-button
              @click="dialog.updateDialog = false; updateInsTemplateAll(currentRow)"
              type="primary">
            确定
          </el-button>
          <el-button
              @click="dialog.updateDialog = false"
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
      <p>注意: 只能本人对模板进行操作！</p>
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
        width="50%">
      <el-form ref="addRow" :rules="addRowRules" :model="addRow">
        <el-descriptions title="增加模板" :column="3" border>
          <el-descriptions-item span="1" label="模板名称">
            <el-form-item prop="name" style="margin: 0;padding: 0">
              <el-input v-model="addRow.name"></el-input>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="2" label="创建时间">
            <el-form-item prop="creationTime" style="margin: 0;padding: 0">
              <el-date-picker
                  style="width: 100%"
                  v-model="addRow.creationTime"
                  type="datetime"
                  placeholder="选择日期时间">
              </el-date-picker>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="1" label="模板作者">
            <el-form-item prop="userID" style="margin: 0;padding: 0">
              <el-select clearable v-model="addRow.userID" filterable placeholder="创建者">
                <template v-for="item in userList">
                  <el-option :label="item.realName" :value="item.id" :key="item.id"></el-option>
                </template>
              </el-select>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="1" label="适用范围">
            <el-form-item prop="scope" style="margin: 0;padding: 0">
              <el-select clearable v-model="addRow.scope" filterable placeholder="模板类型">
                <template v-for="item in scopeList">
                  <el-option :label="item.name" :value="item.id" :key="item.id"></el-option>
                </template>
              </el-select>
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item span="1" label="模板类型">
            <el-form-item prop="recordType" style="margin: 0;padding: 0">
              <el-select clearable v-model="addRow.recordType" filterable placeholder="模板类型">
                <template v-for="item in recordTypeList">
                  <el-option :label="item.name" :value="item.id" :key="item.id"></el-option>
                </template>
              </el-select>
            </el-form-item>
          </el-descriptions-item>
        </el-descriptions>
        <div style="margin-top: 30px; margin-left: 70%">
          <el-button
              @click="addSubmitForm('addRow')"
              type="primary">
            确定
          </el-button>
          <el-button
              @click="dialog.addDialog = false"
              type="info">
            取消
          </el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import moment from 'moment';

export default {
  name: "InspectionTemplateManagement",
  data() {
    return {
      form: '',
      currentPage: 1,
      pageSize: 10,
      insTemplateAllList: [],
      userList: [],
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
        userID: '',
        recordType: '',
        name: ''
      },
      formInlineRules: {
        userID: [
          {required: false, message: '', trigger: 'blur'},
        ],
        recordType: [
          {required: false, message: '', trigger: 'blur'},
        ],
        name: [
          {required: false, message: '', trigger: 'blur'},
        ]
      },
      addRowRules: {
        name: [{required: true, message: '请输入表单名称', trigger: 'blur'}],
        userID: [{required: false, message: '请输入时间', trigger: 'blur'}],
        creationTime: [{ required: true, message: '请选择', trigger: 'change' }],
        scope: [{ required: true, message: '请选择', trigger: 'change' }],
        recordType: [{ required: true, message: '请选择', trigger: 'change' }],
      },
      currentRow: {},
      addRow: {
        name: '',
        userID: null,
        creationTime: null,
        scope: null,
        recordType: null,
        delMark: null
      },
      dialog: {
        updateDialog: false,
        deleteDialog: false,
        addDialog: false
      }
    }
  },

  created() {
    this.getUserAll();
    this.getInsTemplateAll();
  },

  methods: {
    /**
     * 获取模板列表
     * @returns {Promise<void>}
     */
    async getInsTemplateAll() {
      const resp = await this.$axios
          .get(`/getCheckTemplateAll?UserID=${this.formInline.userID}&RecordType=${this.formInline.recordType}&Name=${this.formInline.name}&${new Date().getTime()}`);
      this.insTemplateAllList = resp.data.data;
      /**
       * 数据过滤，将标记为删除的数据滤除
       * 并转化日期格式
       */
      this.insTemplateAllList = this.tableDataNew;
      for (let i = 0; i < this.insTemplateAllList.length; i++) {
        this.insTemplateAllList[i].creationTime = moment(this.insTemplateAllList[i].creationTime).format('YYYY-MM-DD HH:mm:ss');
      }
    },
    /**
     * 获取模板列表通过id
     * @param id
     * @returns {Promise<void>}
     */
    async getInsTemplateAllByID(id) {
      const resp = await this.$axios.get(`/getCheckTemplateByID?ID=${id}`);
      console.log(resp.data.data)
    },
    /**
     * 获取所用用户列表
     * @returns {Promise<void>}
     */
    async getUserAll() {
      const resp = await this.$axios.get(`/queryUserAll`);
      if (resp != null) {
        this.userList = resp.data.data;
      }
    },
    /**
     * 删除模板（假删除，数据库标记给数据为删除）
     * @param param
     */
    deleteInsTemplateAll(param) {
      //获取当前操作员
      const user = JSON.parse(localStorage.getItem("User"))
      // 设置当前操作员
      param.operatorID = user.id
      param.creationTime = moment(param.creationTime)

      this.$axios.post(`/deleteCheckTemplate`, param)
      window.setTimeout(this.getInsTemplateAll, 200)
    },
    /**
     * 修改模板
     * @param param
     */
    updateInsTemplateAll(param) {
      //获取当前操作员
      const user = JSON.parse(localStorage.getItem("User"))
      // 设置当前操作员
      param.operatorID = user.id
      param.creationTime = moment(param.creationTime)

      this.$axios.post(`/updateCheckTemplate`, param)
      window.setTimeout(this.getInsTemplateAll, 200)
    },
    /**
     * 增加模板
     */
    addInsTemplate() {
      this.addRow.creationTime = moment(this.addRow.creationTime).format()

      this.$axios.post(`/addCheckTemplate`, this.addRow)
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
    },
    /**
     * 获取模板类型的中文名通过id
     * @param rid
     * @returns {string}
     */
    getRecordTypeName(rid) {
      for (let i = 0; i < this.recordTypeList.length; i++) {
        if (rid === this.recordTypeList[i].id) {
          return this.recordTypeList[i].name
        }
      }
    },
    /**
     * 获取模板作者中文名通过id
     * @param uid
     * @returns {string}
     */
    getUserName(uid) {
      for (let i = 0; i < this.userList.length; i++) {
        if (uid === this.userList[i].id) {
          return this.userList[i].realName
        }
      }
    },
    /**
     * 获取适用范围中文名通多id
     * @param sid
     * @returns {string}
     */
    getScopeName(sid) {
      for (let i = 0; i < this.scopeList.length; i++) {
        if (sid === this.scopeList[i].id) {
          return this.scopeList[i].name
        }
      }
    },
  },

  computed: {
    /**
     * 数据过滤器
     * @returns {*[]}
     */
    tableDataNew: function () {
      return this.insTemplateAllList.filter((data) => {
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