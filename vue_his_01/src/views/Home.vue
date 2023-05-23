<template>
  <el-container>
    <el-aside width="200px">
        <SideMenu></SideMenu>
    </el-aside>
    <el-container>
      <el-header>
        <img :src="require('@/assets/logo.png')" class="logo">
        <strong style="font-size: x-large;color: #1e3273;margin-left: 10px">
          诊所管理系统
        </strong>
        <div class="header-avatar">
          <el-avatar size="medium" :src="require('@/assets/logo.png')"></el-avatar>
          <el-dropdown style="margin-left: 10px">
            <span class="el-dropdown-link">
              {{ userInfo.realName }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="selectMenu({name: 'UserCenter', title: '个人中心'})">
                <router-link to="/userCenter" >个人中心</router-link></el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-main>
        <Tabs></Tabs>
        <div style="margin: 0 15px">
          <router-view></router-view>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideMenu from "@/views/inc/SideMenu";
import Tabs from "@/views/inc/Tabs";
export default {
  name: "Home",
  components: {
    SideMenu,
    Tabs,
  },
  data() {
    return {
      userInfo: {
        ID: "",
        realName: "",
        avatar: "https://img1.baidu.com/it/u=3108885758,3678256568&fm=26&fmt=auto",
      }
    }
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      this.userInfo = JSON.parse(localStorage.getItem("User"));
      console.log("userInfo.RealName = ", this.userInfo.realName);
    },
    logout() {
      this.$axios.post("/logout").then(res => {
        localStorage.clear();
        sessionStorage.clear();
        this.$store.commit("resetState");
        this.$router.push("/login");
      })
    },
    selectMenu(item) {
      this.$store.commit("addTab", item);
    }
  }
}
</script>

<style scoped>
.header-avatar {
  float: right;
  width: auto;
  display: flex;
  align-items: center;
}
.el-dropdown-link {
  cursor: pointer;
  color: white;
}
.el-container {
  padding: 0;
  margin: 0;
  height: 100vh;
}
.el-header {
  background: linear-gradient(to right , #afc3d9, #1e5b9a);
  padding-left: 0;
  color: #333;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  line-height: 200px;
}

.logo{
  height: 50px;
  width: 50px;
  margin-top: 5px;
  float: left;
}

.el-main {
  color: #333;
  padding: 0;
}

a {
  text-decoration: none;
}
</style>
