<template>
  <div class="login-wrap">
    <video id="v1" autoplay loop muted>
      <source src="../assets/bg.mp4" type="video/mp4">
    </video>
    <div class="login-box">
      <el-row justify="center" type="flex" align="middle">
        <el-col>
          <div style="margin-left: 40px">
            <h2 style="text-align: center">诊所管理系统</h2>
            <el-image :src="require('@/assets/logo.png')" style="height: 150px; width: 150px; margin-left: 35px"></el-image>
            <p></p>
          </div>
        </el-col>
        <el-col :span="1">
          <div class="grid-content bg-purple-light">
            <el-divider direction="vertical"></el-divider>
          </div>
        </el-col>
        <el-col>
          <div>
            <el-form :model="User" :rules="rules" ref="User" label-width="80px">
              <el-form-item label="用户名" prop="UserName" style="width: 300px">
                <el-input style="width: 200px;" v-model="User.UserName"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="Password" style="width: 300px">
                <el-input type="password" style="width: 200px;" v-model="User.Password"></el-input>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" icon="el-icon-s-custom" @click="submitForm('User')">登录</el-button>
                  <el-button @click="resetForm('User')" icon="el-icon-refresh">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      User: {
        UserName: '',
        Password: '',
      },
      rules: {
        UserName: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        Password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      },
    };
  },
  created() {
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        // 这个valid 表示的是验证是否通过输出回是一个布尔类型的值
       // 这里就可以做一些回滚啊一些操做,
        if (valid) {
          let param = this.$qs.stringify(this.User);
          console.log("param = ", param);
          this.$axios.post('/login', param).then(res => {
            console.log("res = ", res);
            localStorage.setItem("User", JSON.stringify(res.data.data));
            // const jwt = res.headers['Authorization']
            // console.log(jwt);
            // this.$store.commit('SET_TOKEN', jwt);
            // console.log("-----",res.headers['Authorization'])
            this.$router.push('/index');
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }
}
</script>

<style scoped>
.el-form-item__label{
  font-weight: bolder;
  font-size: medium;
}

.el-divider {
  height: 200px;
}

.login-wrap {
  position: absolute;
  width: 100%;
  height: 100%;
}

.login-box {
  background-color: rgba(255, 255, 255, 0.9);
  position: relative;
  height: 40vh;
  width: 80vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.el-row{
  top: 50%;
  transform: translate(0, -50%);
}

.captchaImg {
  float: left;
  margin-left: 8px;
  border-radius: 4px;
}

video {
  position: fixed;
  right: 0px;
  top: 0px;
  min-width: 100%;
  min-height: 100%;
  height: auto;
  width: auto;
  z-index: -1;
}

source {
  min-width: 100%;
  min-height: 100%;
  height: auto;
  width: auto;
}
</style>
