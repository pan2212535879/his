<template>
  <el-menu
      :default-active="this.$store.state.menus.editableTabsValue"
      class="el-menu-vertical-demo"
      text-color="#fff"
      background-color="#143f6b"
      active-text-color="#ffd04b">
    <router-link to="/index">
      <el-menu-item index="Index" @click="selectMenu({name: 'Index', title: '首页'})">
        <template slot="title">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </template>
      </el-menu-item>
    </router-link>
    <el-submenu :index="menu.name" v-for="(menu, index) in menuList" :key="index">
      <template slot="title">
        <i :class="menu.icon"></i>
        <span>{{ menu.title }}</span>
      </template>
      <router-link :to="item.path" v-for="(item, subIndex) in menu.children" :key="subIndex">
        <el-menu-item class="menu-children" :index="item.name" @click="selectMenu(item)">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </template>
        </el-menu-item>
      </router-link>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "SideMenu",
  data() {
    return {}
  },
  computed: {
    menuList: {
      get() {
        return this.$store.state.menus.menuList
      }
    }
  },
  methods: {
    selectMenu(item) {
      console.log("item:", item);
      this.$store.commit("addTab", item);
    }
  }
}
</script>

<style scoped>
.el-menu-vertical-demo {
  height: 100%;
  text-align: left;
  width: 100%;
}

a {
  text-decoration: none;
}

.el-menu {
  border: 0;
  background-color: #143f6b;
}
</style>