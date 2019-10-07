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
    getRequest('/config/sysmenu').then(resp=>{
        if(resp && resp.status == 200){
            var fmtRoutes = formatRoutes(resp.data)
            router.addRoutes(fmtRoutes)
            store.commit('initMenu',fmtRoutes)
            store.dispatch('connect')
        }
    })
}


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
            path:path,
            component(resolver){
                if(component.startsWith('Home')){
                    require(['../components/'+component+'.vue'],resolver)
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