import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import kaoqin from '@/views/modules/kaoqin/list'
    import kehu from '@/views/modules/kehu/list'
    import lingjian from '@/views/modules/lingjian/list'
    import lingjianOrder from '@/views/modules/lingjianOrder/list'
    import qingjia from '@/views/modules/qingjia/list'
    import shebei from '@/views/modules/shebei/list'
    import shebeiOrder from '@/views/modules/shebeiOrder/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryKaoqin from '@/views/modules/dictionaryKaoqin/list'
    import dictionaryLingjian from '@/views/modules/dictionaryLingjian/list'
    import dictionaryLingjianOrder from '@/views/modules/dictionaryLingjianOrder/list'
    import dictionaryQingjiaYesno from '@/views/modules/dictionaryQingjiaYesno/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShebei from '@/views/modules/dictionaryShebei/list'
    import dictionaryShebeiOrder from '@/views/modules/dictionaryShebeiOrder/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryKaoqin',
        name: '考勤结果',
        component: dictionaryKaoqin
    }
    ,{
        path: '/dictionaryLingjian',
        name: '机电零件类型',
        component: dictionaryLingjian
    }
    ,{
        path: '/dictionaryLingjianOrder',
        name: '订单类型',
        component: dictionaryLingjianOrder
    }
    ,{
        path: '/dictionaryQingjiaYesno',
        name: '报名状态',
        component: dictionaryQingjiaYesno
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShebei',
        name: '机电设备类型',
        component: dictionaryShebei
    }
    ,{
        path: '/dictionaryShebeiOrder',
        name: '订单类型',
        component: dictionaryShebeiOrder
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '公告',
        component: gonggao
      }
    ,{
        path: '/kaoqin',
        name: '考勤',
        component: kaoqin
      }
    ,{
        path: '/kehu',
        name: '客户',
        component: kehu
      }
    ,{
        path: '/lingjian',
        name: '机电零件',
        component: lingjian
      }
    ,{
        path: '/lingjianOrder',
        name: '机电零件订单',
        component: lingjianOrder
      }
    ,{
        path: '/qingjia',
        name: '请假',
        component: qingjia
      }
    ,{
        path: '/shebei',
        name: '机电设备',
        component: shebei
      }
    ,{
        path: '/shebeiOrder',
        name: '机电设备订单',
        component: shebeiOrder
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
