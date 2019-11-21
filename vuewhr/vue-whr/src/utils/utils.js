import {getRequest} from './api'
import {Message, MenuItem} from 'element-ui'

export const isNotNullORBlank = (...args)=>{
    for(var i=0;i<args.length;i++){
        var argument = args[i]
        if(argument == null || argument == '' || argument == undefined){
            Message.warning({message:'数据不能为空!'})
            return false
        }
    }
    return true
}

//初始化菜单
export const initMenu = (router,store) => {
    if(store.state.routes.length > 0){
        return
    }
    //发送get请求
    getRequest('/config/sysmenu').then(resp=>{
        if(resp && resp.status == 200){
            //获取所有菜单，配置好相应的路由和组件
            var fmtRoutes = formatRoutes(resp.data)
            router.addRoutes(fmtRoutes)
            store.commit('initMenu',fmtRoutes)
            store.dispatch('connect')
        }
    })
}

//菜单路由跳转核心
export const formatRoutes = (routes) => {
    let fmRoutes = []
    routes.forEach(router=>{
        let {
            path,
            component,
            name,
            meta,
            iconCls,
            children
        } = router
        if(children && children instanceof Array){
            children = formatRoutes(children)
        }
        let fmRouter = {
            //菜单路由跳转
            path:path,
            component(resolve){
                if(component.startsWith('Home')){
                    require(['../components/'+component+'.vue'],resolve)
                }else if(component.startsWith('Emp')){
                    require(['../components/emp/' + component + '.vue'], resolve)
                }else if (component.startsWith("Per")) {
                    require(['../components/personnel/' + component + '.vue'], resolve)
                } else if (component.startsWith("Sal")) {
                    require(['../components/salary/' + component + '.vue'], resolve)
                } else if (component.startsWith("Sta")) {
                    require(['../components/statistics/' + component + '.vue'], resolve)
                } else if (component.startsWith("Sys")) {
                    require(['../components/system/' + component + '.vue'], resolve)
                }
            },
            name:name,
            iconCls:iconCls,
            meta:meta,
            children:children
        }
        fmRoutes.push(fmRouter)
    })
    return fmRoutes
}