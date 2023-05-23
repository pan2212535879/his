<template>
  <div>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="7">
          <el-input placeholder="请输入药品名称"
                    v-model="addForm.drugsName" clearable
                    @clear="doQuery()">
            <el-button slot="append" icon="el-icon-search"
                       @click="doQuery()">
            </el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisable = true">添加药品信息</el-button>
        </el-col>
      </el-row>

      <el-table :data = "drugsList.slice((currentPage-1)*pageSize,currentPage*pageSize)">
        <el-table-column label="id" prop="id"></el-table-column>
        <el-table-column label="药品编码" prop="drugsCode"></el-table-column>
        <el-table-column label="药品名称" prop="drugsName"></el-table-column>
        <el-table-column label="规格" prop="drugsFormat"></el-table-column>
        <el-table-column type="包装单位" prop="drugsUnit"></el-table-column>
        <el-table-column label="药品剂型" prop="drugsDosageID"></el-table-column>
        <el-table-column label="药品类型" prop="drugsTypeID"></el-table-column>
        <el-table-column label="拼音助记码" prop="mnemonicCode"></el-table-column>
        <el-table-column label="单价" prop="drugsPrice"></el-table-column>
        <el-table-column label="厂家" prop="manufacturer"></el-table-column>
        <el-table-column label="创建日期" prop="creationDate"></el-table-column>
        <el-table-column label="最后修改时间" prop="lastUpdateDate"></el-table-column>
<!--        <el-table-column label="删除标记" prop="delMark"></el-table-column>-->
        <el-table-column label="修改操作" width="100px">
          <!--修改按钮-->
          <template slot-scope="scope">
            <el-button type="primary"  size="mini"
                       @click="enableUpdateDialog(scope.row)">修改</el-button>
          </template>
        </el-table-column>

        <el-table-column label="删除操作" width="100px">
          <template slot-scope="scope">
            <el-button type="primary"  size="mini"
                       @click="doDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination align='right'
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="[10, 20, 30, 40]"
                     :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="drugsList.length">
      </el-pagination>
    </el-card>
    <!--添加药品信息-->
    <el-dialog
        title="添加药品信息"
        :visible.sync="addDialogVisable"
        width="50%" @close="addDialogClosed">
      <el-form :model="addForm"  ref="addFormRed" label-width="100px" >
        <el-form-item label="药品编码" prop="drugsCode">
          <el-input v-model="addForm.drugsCode"></el-input>
        </el-form-item>

        <el-form-item label="药品名称" prop="drugsName">
          <el-input v-model="addForm.drugsName"></el-input>
        </el-form-item>

        <el-form-item label="规格" prop="drugsFormat">
          <el-input v-model="addForm.drugsFormat"></el-input>
        </el-form-item>

        <el-form-item label="包装单位" prop="drugsUnit">
          <el-input v-model="addForm.drugsUnit"></el-input>
        </el-form-item>

        <el-form-item label="药品剂型" prop="drugsDosageID">
          <el-input v-model="addForm.drugsDosageID"></el-input>
        </el-form-item>

        <el-form-item label="药品类型" prop="drugsTypeID">
          <el-input v-model="addForm.drugsTypeID"></el-input>
        </el-form-item>

        <el-form-item label="拼音助记码" prop="mnemonicCode">
          <el-input v-model="addForm.mnemonicCode"></el-input>
        </el-form-item>

        <el-form-item label="单价" prop="drugsPrice">
          <el-input v-model="addForm.drugsPrice"></el-input>
        </el-form-item>

        <el-form-item label="厂家" prop="manufacturer">
          <el-input v-model="addForm.manufacturer"></el-input>
        </el-form-item>

<!--        <el-form-item label="创建日期" prop="creationDate">-->
<!--          <el-input v-model="addForm.creationDate"></el-input>-->
<!--        </el-form-item>-->

      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisable = false">取 消</el-button>
    <el-button type="primary" @click="doAdd()">确 定</el-button>
  </span>
    </el-dialog>

    <!--修改药品信息-->
    <el-dialog
        title="修改药品信息"
        :visible.sync="updateDialogVisable"
        width="50%" @close="updateDialogClosed">
      <el-form :model="currentRow" ref="addFormRed" label-width="100px">

        <el-form-item label="药品编码" prop="drugsCode">
          <el-input v-model="currentRow.drugsCode"></el-input>
        </el-form-item>

        <el-form-item label="药品名称" prop="drugsName">
          <el-input v-model="currentRow.drugsName"></el-input>
        </el-form-item>

        <el-form-item label="规格" prop="drugsFormat">
          <el-input v-model="currentRow.drugsFormat"></el-input>
        </el-form-item>

        <el-form-item label="包装单位" prop="drugsUnit">
          <el-input v-model="currentRow.drugsUnit"></el-input>
        </el-form-item>

        <el-form-item label="药品剂型" prop="drugsDosageID">
          <el-input v-model="currentRow.drugsDosageID"></el-input>
        </el-form-item>

        <el-form-item label="药品类型" prop="drugsTypeID">
          <el-input v-model="currentRow.drugsTypeID"></el-input>
        </el-form-item>

        <el-form-item label="拼音助记码" prop="mnemonicCode">
          <el-input v-model="currentRow.mnemonicCode"></el-input>
        </el-form-item>

        <el-form-item label="单价" prop="drugsPrice">
          <el-input v-model="currentRow.drugsPrice"></el-input>
        </el-form-item>

        <el-form-item label="厂家" prop="manufacturer">
          <el-input v-model="currentRow.manufacturer"></el-input>
        </el-form-item>

        <el-form-item label="创建日期" prop="creationDate">
          <el-input v-model="currentRow.creationDate"></el-input>
        </el-form-item>

        <el-form-item label="修改日期" prop="lastUpdateDate">
          <el-input v-model="currentRow.lastUpdateDate"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"  class = "dialog-footer">
    <el-button @click="updateDialogVisable = false">取 消</el-button>
    <el-button type="primary" @click="doUpdate()">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      pageSize: 7,
      drugsList: [],
      total: 0,
      addDialogVisable:false,
      updateDialogVisable:false,
      addForm:{
        DelMark:1,
        drugsCode: '',
        drugsTypeID:'',
        drugsName: '',
        drugsUnit:'',
        drugsFormat:'',
        manufacturer :'',
        drugsDosageID : '',
        drugsPrice :'',
        creationDate:'',
        mnemonicCode:'',
        lastUpdateDate:'',
        searchText: '',
      },
      currentRow: {
        drugsCode: '',
        drugsTypeID:'',
        drugsName: '',
        drugsUnit:'',
        drugsFormat:'',
        manufacturer :'',
        drugsDosageID : '',
        drugsPrice :'',
        creationDate:'',
        mnemonicCode:'',
        lastUpdateDate:'',
      },
    }
  },

  created() {
    this.doQuery()
  },

  mounted(){
    this.drugsList = this.$route.query.drugsList;
  },

  methods: {

    doQuery(){
      let url='/outpatientPharmacyWorkstation/drugCatalogueManagement/selectAll';
      let params = this.$qs.stringify(this.addForm);
      this.$axios.post(url,params)
          .then( (resp)=>{
            this.drugsList=resp.data.data;
          } )
          .catch( (err)=>{
            console.log('error=', err);
          } )
    },

    addDialogClosed() {
      this.$refs.addFormRed.resetFields()
    },

    updateDialogClosed() {
      this.$refs.addFormRed.resetFields()
    },

    //添加用户
    doAdd(){
      let url='/outpatientPharmacyWorkstation/drugCatalogueManagement/add';
      let param=this.$qs.stringify(this.addForm);
      console.log('tttttttt')
      console.log(this.addForm)
      this.$axios.post(url,param)
          .then((resp)=>{
            let i=resp.data.code;
            console.log('rrrrrrr')
            console.log(resp.data)
            if(i==200){
              this.$router.push('/outpatientPharmacyWorkstation/drugCatalogueManagement/')
              location.reload();
            }else{
              this.msg='增加失败，请处理'
            }
          })
          .catch((err)=>{
            console.log('error=',err)
            this.msg='增加失败，请处理'
          })
      this.addDialogVisable = false
    },

    doUpdate(){
      let url='/outpatientPharmacyWorkstation/drugCatalogueManagement/update';
      let param=this.$qs.stringify(this.currentRow);
      console.log('ttttttteeeeeeeeeerrrt')
      console.log(param)
      this.$axios.post(url,param)
          .then((resp)=>{
            let i=resp.data.code;
            if(i==200){
              this.$router.push('/outpatientPharmacyWorkstation/drugCatalogueManagement')
              location.reload();
            }else{
              this.msg='修改失败，请处理'
            }
          })
          .catch((err)=>{
            console.log('error=',err)
          })
      this.updateDialogVisable = false
    },

    enableUpdateDialog(param) {
      this.updateDialogVisable = true
      this.currentRow = param
    },

    //删除
    doDelete(row){
      let url='/outpatientPharmacyWorkstation/drugCatalogueManagement/delete';
      // let params = this.$qs.stringify(this.addForm);
      // this.$axios.post(url,params)
      //     .then( (resp)=>{
      //       this.drugsList=resp.data.data;
      //     } )
      //     .catch( (err)=>{
      //       console.log('error=', err);
      //     } )
      let param = row;
      console.log(row.id);
      console.log("药品删除"+param.name);
      console.log("药品删除"+param.id);

      this.$axios.post(url, {id:row.id}).then(res => {
        // this.initApply(this.choosedPatient.ID);
        // console.log("currentPage = ", this.currentPage);
        // console.log("pageSize = ", this.pageSize);
      });
      this.doQuery();
    },
      handleSizeChange(val) {
        this.currentPage = 1;
        this.pageSize = val;
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
    },
  }
}
</script>

<style scoped>

</style>
