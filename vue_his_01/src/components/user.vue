<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <el-container>
        <div class="box-userface">
          <img style="
              background: white;
              height: 80px;
              width: 80px;
              border: 1px solid #c0c0c0;
              border-radius: 50%;"
               src="../assets/logo.png" alt="logo">
        </div>
        <label class="userlable">{{ userInfo.realName }}</label>
      </el-container>
    </div>
    <el-container direction="vertical" style="margin-top: 0px; text-align: left; margin-left: 20px">
      <label class="userinfolsit">所属科室:
        <label style="margin-left: 80px">
          {{ userDept.deptName }}
        </label>
      </label>
      <label class="userinfolsit">用户类型:
        <label style="margin-left: 80px">
          {{ getUserTypeName() }}
        </label>
      </label>
    </el-container>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {},
      userTypeList: [
        {
          id: 1,
          name: '医院管理员'
        },
        {
          id: 2,
          name: '挂号收费员'
        },
        {
          id: 3,
          name: '门诊医生'
        },
        {
          id: 4,
          name: '医技医生'
        },
        {
          id: 5,
          name: '药房操作员'
        },
        {
          id: 6,
          name: '财务管理员'
        },
        {
          id: 7,
          name: '患者'
        },
      ],
      userDept: {}
    }
  },
  methods: {
    /**
     * 获取当前操作员
     * @returns {any}
     */
    getCurrentUser() {
      return JSON.parse(localStorage.getItem("User"))
    },

    /**
     * 设置当前操作员
     * @returns {Promise<void>}
     */
    async setCurrentUser() {
      this.userInfo = this.getCurrentUser()

      const resp = await this.$axios.get(`/queryUserDeptByID?DeptID=${this.userInfo.deptID}`)
      this.userDept = resp.data.data
    },

    getUserTypeName() {
      for (let i = 0; i < this.userTypeList.length; i++) {
        if (this.userInfo.useType === this.userTypeList[i].id) {
          return this.userTypeList[i].name
        }
      }
    }
  },
  created() {
    this.setCurrentUser()
  }
}
</script>

<style scoped>
.box-card {
  width: 370px;
  height: 198px;
  background-color: rgba(255, 255, 255, 1);
  box-shadow: 0px 3px 7px 0px rgb(0 0 0 / 50%) !important;
}

.box-userface {
  display: inline;
  float: left;
  margin-left: 10px;
}

.userlable {
  color: rgb(30, 50, 115);
  float: left;
  font-size: 30px;
  font-weight: bolder;
  margin-top: 20px;
  margin-left: 60px;
}

.userinfolsit {
  float: left;
  top: 0;
  font-size: 15px;
  margin-bottom: 5px;
  margin-left: 30px;
  padding: 0;
}
</style>
