// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {putRequest} from './utils/api'
import {deleteRequest} from './utils/api'
import {isNotNullORBlank} from './utils/utils'
import {initMenu} from './utils/utils'
import 'font-awesome/css/font-awesome.min.css'
import './utils/filter_utils'

//prototype这个属性是干嘛的？
Vue.prototype.getRequest = getRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.deleteRequest = deleteRequest
Vue.prototype.isNotNullORBlank = isNotNullORBlank

//不知道这是什么意思？
Vue.config.productionTip = false
Vue.use(ElementUI)

//？？？没明白
router.beforeEach((to,from,next)=>{
  if(to.name == 'Login'){
    next()
    return
  }
  var name = store.state.user.name
  if(name == '未登录'){
    if(to.meta.requireAuth || to.name == null){
      next({path:'/',query:{redirect:to.path}})
    }else{
      next()
    }
  }else{
    initMenu(router,store)
    if(to.path == '/chat')
      store.commit("updateMsgList",[])
    next()
  }

})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
