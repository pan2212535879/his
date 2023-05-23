import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/Index.vue'
import User from '../views/sys/User.vue'
import Role from '../views/sys/Role.vue'
import Menu from '../views/sys/Menu.vue'
import UserCenter from "@/views/UserCenter";
import axios from "../axios";
import store from "../store"
import qs from "qs";

Vue.use(VueRouter)

Vue.prototype.$qs = qs;

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    redirect: '/login',
    children: [
      {
        path: '/index',
        name: 'Index',
        component: Index
      },
      {
        path: '/userCenter',
        name: 'UserCenter',
        component: UserCenter
      },
    ]
  },

  {
    path: '/login',
    name: 'Login',
    //项目路由懒加载，秒开
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// router.afterEach((to, from) => {
//
// })

router.beforeEach((to, from, next) => {
  let hasRoute = store.state.menus.hasRoute
  let param = localStorage.getItem("User");
  console.log("本地User1 = ", param);
  if (!hasRoute && JSON.parse(localStorage.getItem("User")) !== null) {
    let param = JSON.parse(localStorage.getItem("User"));
    console.log("本地User2 = ", param);
    axios.post("/sys/menu/nav", param).then(res => {
      let authorization = [];
      let temp = {};
      temp.data = {
        nav: res.data.data,
        authorization: authorization
      }

      console.log("菜单nav = ", temp.data);
      //拿到menuList
      store.commit("setMenuList", temp.data.nav)
      //拿到用户权限
      // store.commit("setPermList", temp.data.authorization)

      //动态绑定路由
      let newRoutes = router.options.routes;
      temp.data.nav.forEach(menu => {
        if (menu.children) {
          menu.children.forEach(e => {
            //转成路由
            let route = menuToRoute(e);
            //把路由添加到路由管理中
            if (route) {
              newRoutes[0].children.push(route)
            }
          })
        }
      })
      router.addRoutes(newRoutes);
      hasRoute = true;
      store.commit("changeRouteStatus", hasRoute)
    })
  }



  next();
})

//导航转成路由
const menuToRoute = (menu) => {
  if (!menu.component) {
    return null;
  }
  let route = {
    name: menu.name,
    path: menu.path,
    meta: {
      icon: menu.icon,
      title: menu.title
    }
  }
  route.component = () => import('@/views/' + menu.component + '.vue')
  return route
}

export default router
