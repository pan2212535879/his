<template>
  <div style="text-align: center;">
    <h2>欢迎 {{ userInfo.RealName }} 光临用户中心</h2>
    <div>
      <h3>
        您的身份：
        <el-tag v-if="userInfo.UseType === 1" effect="dark" type="danger">医院管理员</el-tag>
        <el-tag v-else-if="userInfo.UseType === 2" effect="dark" type="info">挂号收费员</el-tag>
        <el-tag v-else-if="userInfo.UseType === 3" effect="dark" type="success">门诊医生</el-tag>
        <el-tag v-else-if="userInfo.UseType === 4" effect="dark" type="success">医技医生</el-tag>
        <el-tag v-else-if="userInfo.UseType === 5" effect="dark" type="primary">药房操作员</el-tag>
        <el-tag v-else-if="userInfo.UseType === 6" effect="dark" type="danger">财务管理员</el-tag>
        <el-tag v-else-if="userInfo.UseType === 7" effect="dark" type="danger">患者</el-tag>
        <el-tag v-else>未知身份</el-tag>
      </h3>
    </div>

    <el-form :model="passForm" status-icon :rules="rules" ref="passForm" label-width="100px">
      <el-form-item label="旧密码" prop="currentPass">
        <el-input type="Password" v-model="passForm.currentPass" autocomplete="off" clearable></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="Password">
        <el-input type="Password" v-model="passForm.Password" autocomplete="off" clearable></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="Password" v-model="passForm.checkPass" autocomplete="off" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="submitForm('passForm')" icon="el-icon-check">提交</el-button>
        <el-button type="info" @click="resetForm('passForm')" icon="el-icon-close">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: "UserCenter",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.passForm.Password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      userInfo: {
        ID: '',
        UserName: '',
        RealName: '',
        UseType: '',
      },
      passForm: {
        Password: '',
        checkPass: '',
        currentPass: ''
      },
      rules: {
        Password: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
        ],
        checkPass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        currentPass: [
          { required: true, message: '请输入当前密码', trigger: 'blur' },
        ]
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      let user = JSON.parse(localStorage.getItem("User"));
      this.userInfo.ID = user.id;
      this.userInfo.UserName = user.userName;
      this.userInfo.RealName = user.realName;
      this.userInfo.UseType = user.useType;
      console.log("this.userInfo : ", this.userInfo);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          this.$axios.get('/userCenter/changePassword', {
            params: {
              ID: this.userInfo.ID,
              password: this.passForm.Password,
              checkPass: this.passForm.checkPass,
              currentPass: this.passForm.currentPass
            }
          }).then(res => {

            this.$router.push("/login");
          })
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.el-form {
  width: 420px;
  margin: 50px auto;
}
</style>
