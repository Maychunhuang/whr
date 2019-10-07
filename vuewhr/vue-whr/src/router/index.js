import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import Chat from '@/components/chat/Chat';
//import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    },
    {
      path: '/home',
      name: '主页',
      component: Home,
      hidden: true,
      meta:{
        requireAuth: 
        true
      },
      children:[
        {
          path:'/chat',
          name:'消息',
          component:Chat,
          hidden:true,
          meta:{
            requireAuth:true,
            keepAlive:false
          }
        }
      ]
    }
  ]
})
