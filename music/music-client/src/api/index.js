import Axios from "axios";
import {
  get,
  post,
  deletes
} from "./http";

//判断管理员是否登录成功
//   export const getLoginStatus = (params) => post(`admin/login/status`, params);

//==================歌手相关=====================
//查询所有歌手
export const getAllSinger = () => get(`singer/allSinger`);
// 根据歌手性别查询
export const getSingerOfSex = (sex)=>get(`singer/singerOfSex?sex=${sex}`)

// ==================歌曲相关=====================
//查询所有歌曲
export const getAllSong = () => get(`song/allSong`);
// 根据歌手id查询歌曲
export const songOfSingerId = (id) => get(`song/singer/detail?singer_id=${id}`);
//根据歌曲id查询歌曲列表
export const songOfSongId = (id) => get(`song/detail?song_id=${id}`);
//根据歌曲名查询歌曲列表
export const likeSongOfName = (song_name) => get(`song/likeSongOfName?song_name=${song_name}`);
//根据专辑名查询歌曲列表
export const likeSongOfIntro = (song_intro) => get(`song/likeSongOfIntro?song_intro=${song_intro}`);
//根据歌曲url查询歌曲列表
export const getSongBySongUrl = (url) => get(`song/songOfSongUrl?url=${url}`);

// ==================歌单相关=====================
//查询所有歌单
export const getAllSongList = () => get(`songList/allSongList`);
//添加歌单
export const addSongListInfo = (params) => post(`songList/add`, params);
// 根据歌单名字模糊查询
export const getSongListLikeTitle = (keywords) => get(`songList/songListLikeTitle?title=${keywords}`)
// 根据风格模糊查询歌单列表
export const getSongListLikeStyle = (style) => get(`songList/songListLikeStyle?style=${style}`)
// ==================歌单歌曲相关=====================
// 根据歌曲id查询歌单
export const listSongOfSongId = (songlist_id) => get(`listSong/detail?songlist_id=${songlist_id}`);
//添加歌单歌曲
export const addListSongInfo = (params) => post(`listSong/add`, params);

//==================前端用户相关=====================
//查询所有用户
export const getAllConsumer = () => get(`consumer/allConsumer`);
//编辑用户信息
export const editConsumerInfo = (params) => post(`consumer/update`, params);
// 注册
export const SignUp = (params) => post(`consumer/add`, params)
// 登录
export const LoginIn = (params) => post(`consumer/login`, params)
// 用户名
export const getUsername = (username) => get(`consumer/consumerByUserName?username=${username}`)
//根据用户id查询用户
export const getConsumerById = (id) => get(`/consumer/selectConsumerById?id=${id}`)
// 下载音乐
export const download = (url) => Axios({
  method: 'get',
  url: url,
  responseType: 'blob'
})
// 更新用户信息
export const updateUserMsg = (params) => post(`consumer/update`, params)

//==================评价=====================
// 新增评价
export const addRank = (params) => post(`/rank/add`, params)
//获取指定歌单的评分
export const getRankOfSongListID = (songlist_id) => get(`/rank/average?songlist_id=${songlist_id}`)

//==================评论=====================
// 新增评论
export const addComment = (params) => post('/comment/add', params)
// 点赞
export const setLike = (params) => post('/comment/like', params)
// 返回当前歌单或歌曲的评论列表
export const getAllComment = (type, id,uid) => {
  if (type == 0) {
    return get(`/comment/commentOfSongId?song_id=${id}`) //歌曲
  } else {
    return get(`/comment/commentOfSongListId?songlist_id=${id}&consumer_id=${uid}`) //歌单
  }
}

// ==================点赞=====================
// 点赞
export const setUp = (liked) => post(`/up/like`, liked)
//获取点赞状态
export const getLike = (uid,cid) => get(`/up/getLikedById?uid=${uid}&cid=${cid}`)
// ==================收藏=====================
// 新增收藏
export const setCollect = (params) => post(`/collect/add`,params)
// 指定用户的收藏列表
export const getCollectOfUserId = (userId) => get(`/collect/collectOfUserId?user_id=${userId}`)
// ==================社区=====================
//添加动态
export const addCommunity = (params) => post(`community/add`, params);
//添加动态
export const addCommunityWithUrl = (params) => post(`/community/addWithUrl`, params);
// export const uploadImgCom = (id) => post(`community/uploadImg`,id)
//查询所有动态
export const getAllCommunity = () => get(`community/allCommunity`);
//查询指定动态
export const getCommunityOfUsername = (name) => get(`community/communityOfUsername?name=${name}`);
//查询指定id
export const getCommunityOfId = (id) => get(`community/selectById?id=${id}`);
// 删除动态
export const deleteCommunity = (id) => deletes(`/community/delete?id=${id}`)