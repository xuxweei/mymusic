//左侧导航栏
const navMsg = [
    {name:'首页',path:'/'},
    {name:'歌单',path:'/songList'},
    {name:'歌手',path:'/singer'},
    {name:'MV',path:'/mv'},
    {name:'我的音乐',path:'/myMusic'},
    {name:'社区',path:'/community'}
]

// 右侧导航栏
const loginMsg = [
    {name:'登录',path:'/loginIn'},
    {name:'注册',path:'/signUp'}
]
// 用户下拉菜单
const menuList = [
    {name:'设置',path:'/setting'},
    {name:'退出',path:0}
]
export {
    navMsg,
    loginMsg,
    menuList
}