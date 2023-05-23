
<template>
  <div>
    <el-input
        class="el-text"
        placeholder="请输入内容"
        v-model="userInfo.RealName">
      <el-button slot="append" icon="el-icon-search"  @click="search">搜索</el-button>
    </el-input>
    <hr>
      <el-button type="info" icon="el-icon-edit" size="medium" @click="updatePrice" plain>医技项目补录</el-button>
      <el-button type="info" icon="el-icon-edit" size="medium" @click="maintainDialog = true" plain>常用项目维护</el-button>
    <div>
      <el-table
          :data="tableData.slice((currentPage - 1) * pageSize,currentPage * pageSize)"
          style="width: 100%" height="450">
        <el-table-column
            prop="id"
            label="序号"
            width="80">
        </el-table-column>
        <el-table-column
            prop="realName"
            label="姓名"
            width="160">
        </el-table-column>
        <el-table-column
            prop="registID"
            label="挂号号码"
            width="160">
        </el-table-column>
        <el-table-column
            prop="caseNumber"
            label="病历号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="age"
            label="年龄"
            width="160">
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别"
            width="160">
        </el-table-column>
        <el-table-column
            prop="name"
            label="项目名"
            width="160">
        </el-table-column>
        <el-table-column
            prop="state"
            label="状态"
            :formatter="isstate"
            width="160">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="updataState(scope.row)">登记</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,9, 20, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!--    常用项目维护-->
    <el-dialog width="45%" align="center" :visible.sync="maintainDialog">
      <el-transfer
          style="text-align: left; display: inline-block;"
          v-model="value"
          filterable
          @right-check-change="rightCopeChane"
          :titles="['全部项目', '常用项目']"
          :button-texts="['左移', '右移']"
          :format="{
        noChecked: '${total}',
        hasChecked: '${checked}/${total}'
      }"
          @change="handleChange"
          :data="data">
        <span slot-scope="{ option }">{{ option.key }} - {{ option.label }}</span>
        <el-button class="transfer-footer" slot="left-footer" size="small">操作</el-button>
        <el-button class="transfer-footer" slot="right-footer" size="small">查看选中</el-button>
      </el-transfer>
      <el-button type="info" style="margin-left: 10px" size="small" @click="maintainDialog = false">退出</el-button>
    </el-dialog>

    <!--    //弹出提示框-->
    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="40%">
      <div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
          <el-form-item label="项目编码" prop="itemCode">
            <el-input class="form-input" v-model="ruleForm.itemCode"></el-input>
          </el-form-item>
          <el-form-item label="项目名称" prop="itemName">
            <el-select v-model="ruleForm.itemName" placeholder="请选择项目名称" @change="selectid()">
              <el-option
                  v-for="item in fmeditem"
                  :key="item.itemName"
                  :label="item.itemName"
                  :value="item.itemName"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="价格" prop="price">
            <el-input class="form-input" v-model="ruleForm.price"></el-input>
          </el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button @click="dialogVisible = false">取 消</el-button>
        </el-form>
      </div>

    </el-dialog>


    <!--    //弹出提示框-->
    <el-dialog
        title="提示"
        :visible.sync="fmeditemVisbles"
        width="40%">
      <div>
        <el-form :model="fmeditemdetail" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
          <el-form-item label="项目编码" prop="itemCode">
            <el-input style="width: 90%" v-model="fmeditemdetail.itemCode"></el-input>
          </el-form-item>
          <el-form-item label="项目名称" prop="itemName">
            <el-input style="width: 90%" v-model="fmeditemdetail.itemName"></el-input>
          </el-form-item>
          <el-form-item label="deptID" prop="deptID">
            <el-input style="width: 90%" v-model="fmeditemdetail.deptID"></el-input>
          </el-form-item>
          <el-form-item label="CreationDate" prop="CreationDate">
            <el-input style="width: 90%" v-model="fmeditemdetail.CreationDate"></el-input>
          </el-form-item>
          <el-form-item label="price" prop="price">
            <el-input style="width: 90%" v-model="fmeditemdetail.price"></el-input>
          </el-form-item>
          <el-form-item label="mnemonicCode" prop="mnemonicCode">
            <el-input style="width: 90%" v-model="fmeditemdetail.mnemonicCode"></el-input>
          </el-form-item>
          <el-form-item label="expClassID" prop="expClassID">
            <el-input style="width: 90%" v-model="fmeditemdetail.expClassID"></el-input>
          </el-form-item>
          <el-form-item label="deptID" prop="deptID">
            <el-input style="width: 90%" v-model="fmeditemdetail.deptID"></el-input>
          </el-form-item>
          <el-form-item label="creationDate" prop="creationDate">
            <el-input style="width: 90%" v-model="fmeditemdetail.creationDate"></el-input>
          </el-form-item>
          <el-button type="info" style="margin-left: 80%" @click="fmeditemVisbles = false">退出</el-button>
        </el-form>
      </div>

    </el-dialog>
  </div>

</template>

<script>
export default {

  data() {
    return {
      data: [],
      value: [],
      dialogVisibles: false,
      fmeditemVisbles: false,
      fmeditem: [],
      fmeditemdetail: {},
      maintainDialog: false,
      ruleForm: {
        itemCode: '',
        itemName: '',
        price: ''
      },
      rules: {
        itemCode: [
          {required: true, message: '请输入项目编码', trigger: 'blur'},
          {min: 9, max: 9, message: '长度在 9 个字符', trigger: 'blur'}
        ],
        itemName: [
          {required: true, message: '请输入项目名称', trigger: 'blur'}
        ],
        price: [
          {required: true, message: '请输入金额', trigger: 'blur'}
        ]
      },
      tableData: [],
      userInfo: {
        RealName: ''
      },
      pageSize: 7,
      currentPage: 1,
      total: 0,
      userMessage: {
        ID: '',
        State: ''
      },
      dialogVisible: false,
    }
  },
  created() {
    this.search()
    this.getAllfmeditem()
  },
  methods: {
    rightCopeChane(id) {
      if (id.length > 1) return;
      if (id.length !== 0) {
        this.println(id[0]);
      }
    },
    isstate(row, column) {
      if(row[column.property] === 1){
        return '暂存'
      } else if(row[column.property] === 2){
        return  '已开立'
      }else if(row[column.property] === 3){
        return  '已交费'
      }else if(row[column.property] === 4){
        return  '已登记'
      }else if(row[column.property] === 5){
        return  '执行完'
      }else if(row[column.property] === 6){
        return  '已退费'
      } else if(row[column.property] === 7){
        return  '已作废'
      }
    },
    println(id) {
      console.log(id);
      this.fmeditemdetail = this.fmeditem[id];
      console.log(this.fmeditemdetail);
      this.fmeditemVisbles = true;
    },
    handleChange(value, direction, movedKeys) {
      console.log(value, direction, movedKeys);
    },
    selectid() {
      for (var i = 0; i < this.fmeditem.length; i++) {
        if (this.fmeditem[i].itemName == this.ruleForm.itemName) {
          this.ruleForm.itemCode = this.fmeditem[i].itemCode;
          this.ruleForm.price = this.fmeditem[i].price;
          break;
        }
      }
    },
    getAllfmeditem() {
      this.$axios.post('getFmeditemAll').then(res => {
        this.fmeditem = res.data.data;
        console.log(res);
        this.data = [];
        for (let i = 0; i < res.data.data.length; i++) {
          this.data.push({
            key: i,
            label: ` ${res.data.data[i].itemName}`,

          });
        }
      })

      console.log("-----------")
      console.log(this.data)
    },
    // 模糊搜索RealName,用axios向后端发送post请求,传输入的userInfo.RealName值
    // (当我们进行post请求的时候，axios会帮我们转换请求数据和响应数据以及自动转换JSON数据)
    // res.date.date后面的date才是从后端传来的要求的数据
    search() {
      console.log(this.userInfo.RealName);
      this.$axios.post('/getCheckApplyAll', this.$qs.stringify(this.userInfo)).then(res => {
        console.log(res.data.data);
        this.tableData = res.data.data;
        this.total = res.data.data.length;
      })
    },
    handleSizeChange(val) {
      this.currentPage = 1;
      this.pageSize = val;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    updataState(row) {
      console.log(row.state);
      if (row.state !== 3) {
        this.$message({
          type: 'error',
          message: "不允许修改"
        })
      } else {
        console.log(row.id);
        row.state = 4;
        this.userMessage.ID = row.id;
        this.userMessage.State = row.state;
        this.$axios.post('/updateCheckApplyState', this.$qs.stringify(this.userMessage)).then(res => {
          console.log(res);
          this.search();
        })
      }
    },
    updatePrice() {
      this.dialogVisible = true
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
      console.log(this.ruleForm)
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }


}
</script>

<style>
.form-input {
  width: 80%;
}
.el-text{
  width: 300px;
}
</style>
