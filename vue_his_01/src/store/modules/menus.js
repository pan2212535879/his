import Vue from 'vue'
import Vuex from 'vuex'
import fa from "element-ui/src/locale/lang/fa";

Vue.use(Vuex)

export default {
    state: {
        menuList: [],
        permList: [],
        hasRoute: false,

        editableTabsValue: 'Index',
        editableTabs: [{
            title: '首页',
            name: 'Index',
        }],
    },
    mutations: {
        setMenuList(state, menus) {
            state.menuList = menus;
        },
        setPermList(state, perms) {
            state.permList = perms;
        },
        changeRouteStatus(state, hasRoute) {
            state.hasRoute = hasRoute;
            sessionStorage.setItem("hasRoute", hasRoute);
        },
        addTab(state, tab) {

            let index = state.editableTabs.findIndex(e => e.name === tab.name);
            console.log("tab2:", tab);
            if (index === -1 && tab.title !== undefined) {
                state.editableTabs.push({
                    title: tab.title,
                    name: tab.name,
                });
            }

            state.editableTabsValue = tab.name;
        },
        resetState: (state) => {
            state.token = '';
            state.menuList = [];
            state.permList = [];
            state.hasRoute = false;
            state.editableTabsValue = 'Index';
            state.editableTabs = [{
                title: '首页',
                name: 'Index',
            }];

        }

    },
    actions: {
    },

}
