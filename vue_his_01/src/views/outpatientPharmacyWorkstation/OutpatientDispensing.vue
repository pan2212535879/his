<template>
  <div>
    <section ref="print" class="recordImg" id="printRecord">
    </section>
    <div ref="print">
      <el-card class="box-card">
        <div class="search-box" >
          <el-form :model="addForm" :inline="true">
            <el-input placeholder="请输入病历号"
                      style="width: 20%;"
                      v-model="addForm.caseNumber"
                      @clear="doQuery()">
            </el-input>
<!--            <el-input placeholder="请输入药品状态"-->
<!--                      style="width: 20%;"-->
<!--                      v-model="addForm.state"-->
<!--                      @clear="doQuery()">-->
<!--            </el-input>-->
            <el-form-item>
              <el-select v-model="addForm.state" placeholder="请选择药品状态">
                <el-option label="已缴费" value="3"></el-option>
                <el-option label="已发药" value="4"></el-option>
              </el-select>
            </el-form-item>
            <el-button  icon="el-icon-search"
                        @click="doQuery()">
            </el-button>
<!--            <el-button type="round" @click="PrintRow" style="float:right">打 印</el-button>-->
          </el-form>
        </div>
<!--        <el-table-->
<!--            :data="prescriptionList"-->
<!--            style="width: 100%"-->
<!--            class="ddd"-->
<!--            max-height="300">-->
<!--          <el-table-column-->
<!--              prop="caseNumber"-->
<!--              label="病历号"-->
<!--              width="180">-->
<!--          </el-table-column>-->
<!--          <el-table-column-->
<!--              prop="realName"-->
<!--              label="患者姓名"-->
<!--              width="180">-->
<!--          </el-table-column>-->
<!--          <el-table-column-->
<!--              prop="age"-->
<!--              label="年龄">-->
<!--          </el-table-column>-->
<!--          <el-table-column-->
<!--              prop="gender"-->
<!--              label="性别"-->
<!--              width="180">-->
<!--          </el-table-column>-->
<!--          <el-table-column-->
<!--              prop="prescriptionState"-->
<!--              label="处方状态">-->
<!--            <template slot-scope="scope">-->
<!--              <span v-if="scope.row.prescriptionState === 1">暂存</span>-->
<!--              <span v-if="scope.row.prescriptionState === 2">已开立</span>-->
<!--              <span v-if="scope.row.prescriptionState === 0">已作废</span>-->
<!--            </template>-->
<!--          </el-table-column>-->
<!--          <el-table-column-->
<!--              prop="payTime"-->
<!--              label="收费日期">-->
<!--          </el-table-column>-->
<!--        </el-table>-->

        <el-table
            :data="prescriptionList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
            height="600"
            border
            stripe
            style="width: 100%">
          <el-table-column label="病例号" prop="caseNumber"></el-table-column>
          <el-table-column label="处方号" prop="id"></el-table-column>
          <el-table-column label="开单医生" prop="userID"></el-table-column>
          <el-table-column label="药品名称" prop="drugsName"></el-table-column>
          <el-table-column label="规格" prop="drugsFormat"></el-table-column>
          <el-table-column label="数量" prop="amount"></el-table-column>
          <el-table-column label="单位" prop="drugsUnit"></el-table-column>
          <el-table-column label="单价" prop="drugsPrice"></el-table-column>
          <el-table-column label="总金额" prop="sum"></el-table-column>
          <el-table-column label="厂家" prop="manufacturer"></el-table-column>
          <el-table-column label="用法" prop="drugsUsage"></el-table-column>
          <el-table-column label="用量" prop="dosage"></el-table-column>
          <el-table-column label="发药状态" prop="state">
            <template slot-scope="scope">
              <span v-if="scope.row.state === 2">已开立</span>
              <span v-if="scope.row.state === 3">已缴费</span>
              <span v-if="scope.row.state === 4">已发药</span>
              <span v-if="scope.row.state === 5">已退药</span>
              <span v-if="scope.row.state === 6">已退费</span>
            </template>

          </el-table-column>
          <el-table-column label="操作" width="100px">
            <!--修改按钮-->
            <template slot-scope="scope">
              <el-button type="primary" size="mini"
                         @click="doUpdate(scope.row)">发 药
              </el-button>
            </template>
          </el-table-column>

        </el-table>
        <div style="display: flex;justify-content: flex-end">
          <el-pagination
              background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage"
              :page-sizes="[1, 5, 10, 20, 100]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="prescriptionList.length">
          </el-pagination>
        </div>
      </el-card>
    </div>
  </div>

</template>

<script>
// 在当前vue文件内引用
import printJS from 'print-js'
export default {
  data() {
    return {
      formInline: {
        user: '',
        region: ''
      },
      caseNumber:'',
      prescriptionList: [],
      updateDialogVisable: false,
      addForm: {
        caseNumber:'',
        id: '',
        userId: '',
        medicalID:'',
        drugsFormat: '',
        amount: '',
        drugsUnit: '',
        drugsPrice: '',
        sum: '',
        manufacturer: '',
        drugsUsage: '',
        dosage: '',
        state: '',
      },
      currentRow: {
        id: '',
        userId: '',
        drugsFormat: '',
        amount: '',
        drugsUnit: '',
        drugsPrice: '',
        sum: '',
        manufacturer: '',
        drugsUsage: '',
        dosage: '',
        state: '',
      },
      currentPage: 1,
      pageSize:10,
    }
  },

  created() {
    this.doQuery()
  },

  mounted() {
    this.prescriptionList = this.$route.query.prescriptionList;
  },

  methods: {

    doQuery() {
      let url = '/outpatientPharmacyWorkstation/outpatientDispensing/selectAll';
      let params = this.$qs.stringify(this.addForm);
      console.log('test')
      console.log(params)
      this.$axios.post(url, params)
          .then((resp) => {
            this.prescriptionList = resp.data.data;
          })
          .catch((err) => {
            console.log('error=', err);
          })
    },

    initData(){
      this.$axios.post(`/outpatientPharmacyWorkstation/outpatientDispensing/selectAll?caseNumber=${this.caseNumber}`).then( (resp)=> {
        this.list = resp.data.data;
        console.log('list=',this.list)
      }).catch( (err)=> {
        console.log("err",err);
      })
    },
    doUpdate(param) {
      console.log('33333333')
      console.log(param)
      if (!confirm('取药操作确认？')) {
        return;
      }
      this.currentRow = param
      let params = this.$qs.stringify(this.currentRow);
      let url = '/outpatientPharmacyWorkstation/outpatientDispensing/update';
      this.$axios.post(url, params)
          .then((resp) => {
            let i = resp.data.code;
            if (i == 200) {
              // location.reload();
              this.doQuery();
            } else {
              this.msg = '取药失败'
            }
          })
          .catch((err) => {
            console.log('error=', err);
          })
    },

    enableUpdateDialog(param) {
      this.updateDialogVisable = true
      this.currentRow = param
    },
    // PrintRow(index, row){
    //   this.$print(this.$refs.print) // 使用
    // },
    handleSizeChange(val) {
      this.pageSize = val;
      this.initData();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.initData();
    }
  }
}
</script>

<style scoped>
.search-box{
  height: 70px;

}

.ddd{
  height: 250px;
}
</style>
