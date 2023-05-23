import axios from "axios";
import router from "@/router";
import Element from "element-ui"
import { Message } from "element-ui";

axios.defaults.baseURL = "http://localhost:8899"

const request = axios.create({
    timeout: 5000,
    // headers: {
    //     'Content-Type': "application/json; charset=utf-8"
    // }
})
// let num = 0;

request.interceptors.request.use(config => {
    // config.headers['Authorization'] = localStorage.getItem("token");
    return config;
})

request.interceptors.response.use(response => {
        var res = response.data;
        console.log("response = ", response);
        console.log("response.data.code = ", response.data.code);
        switch (res.code) {
            case 200:
                if (res.msg.includes("搜索")||res.msg.includes("挂")||res.msg.includes("提交")||res.msg.includes("更新")||res.msg.includes("删除")||res.msg.includes("开立")||res.msg.includes("登")||res.msg.includes("收费")){
                    Element.Message.success({showClose: true, message : response.data.msg});
                }
                return response
            case 303:
                    Element.Message.error({showClose: true, message: res.msg + ", 错误类型:303登录失败"});
                return Promise.reject(res.msg);
            case 400:
                Element.Message.error({showClose: true, message: res.msg + ", 错误类型:400请求参数出错"});
                return Promise.reject(res.msg);
            case 401:
                Element.Message.error({showClose: true, message: res.msg + ", 错误类型:401没有登录"});
                return Promise.reject(res.msg);
            case 403:
                Element.Message.error({showClose: true, message: res.msg + ", 错误类型:403没有权限"});
                return Promise.reject(res.msg);
            case 410:
                Element.Message.error({showClose: true, message: res.msg + ", 错误类型:410已被删除"});
                return Promise.reject(res.msg);
            case 423:
                Element.Message.error({showClose: true, message: res.msg + ", 错误类型:423已被锁定"});
                return Promise.reject(res.msg);
            case 500:
                Element.Message.error({showClose: true, message: res.msg + ", 错误类型:500服务器出错"});
                return Promise.reject(res.msg);
            case 4001:
                Element.Message.error({showClose: true, message: res.msg + ", 错误类型:4001异常"});
                return Promise.reject(res.msg);
        }
        // if (response.data.code === 200) {
        //     Element.Message.success({showClose: true, message : response.data.msg});
        //     return response
        // } else {
        //     Element.Message.error(!res.msg ? '系统异常' : res.msg);
        //     return Promise.reject(response.data.msg);
        // }
    }, error => {
        console.log("error = ", error);
        if (error.response.data) {
            error.message = error.response.data.msg;
        }
        if (error.response.status === 401) {
            router.push('/login');
        }
        Element.Message.error(error.message, {duration:3000})
        return Promise.reject(error);
    }
)


export default request
