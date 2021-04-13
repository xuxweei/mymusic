import {
  get,
  post,
  deletes
} from "./http";

//判断管理员是否登录成功
export const getLoginStatus = (params) => post(`admin/login/status`, params);

//==================歌手相关=====================
//查询所有歌手
export const getAllSinger = () => get(`singer/allSinger`);
//添加歌手
export const addSingerInfo = (params) => post(`singer/add`, params);
//编辑歌手信息
export const editSingerInfo = (params) => post(`singer/update`, params);
//删除歌手信息
export const deleteSingerInfo = (id) => deletes(`singer/delete?id=${id}`);

// ==================歌曲相关=====================
//查询所有歌曲
export const getAllSong = () => get(`song/allSong`);
// 根据歌手id查询歌曲
export const songOfSingerId = (id) => get(`song/singer/detail?singer_id=${id}`);
//编辑歌曲信息
export const editSongInfo = (params) => post(`song/update`, params);
//删除歌曲信息
export const deleteSongInfo = (id) => deletes(`song/delete?id=${id}`);
//根据歌曲id查询歌曲列表
export const songOfSongId = (id) => get(`song/detail?song_id=${id}`);
//根据歌曲名查询歌曲列表
export const songOfSongName = (song_name) => get(`song/songNameDetail?song_name=${song_name}`);


// ==================歌单相关=====================
//查询所有歌单
export const getAllSongList = () => get(`songList/allSongList`);
//添加歌单
export const addSongListInfo = (params) => post(`songList/add`, params);
//编辑歌单信息
export const editSongListInfo = (params) => post(`songList/update`, params);
//删除歌单信息
export const deleteSongListInfo = (id) => deletes(`songList/delete?id=${id}`);

// ==================歌单歌曲相关=====================
// 根据歌曲id查询歌单
export const listSongOfSongId = (songlist_id) => get(`listSong/detail?songlist_id=${songlist_id}`);
//删除歌曲信息
export const deleteListSongInfo = (song_id,songlist_id) => deletes(`listSong/delete?song_id=${song_id}&songlist_id=${songlist_id}`);
//添加歌单歌曲
export const addListSongInfo = (params) => post(`listSong/add`, params);

//==================前端用户相关=====================
//查询所有用户
export const getAllConsumer = () => get(`consumer/allConsumer`);
//添加用户
export const addConsumerInfo = (params) => post(`consumer/add`, params);
//编辑用户信息
export const editConsumerInfo = (params) => post(`consumer/update`, params);
//删除用户信息
export const deleteConsumerInfo = (id) => deletes(`consumer/delete?id=${id}`);
//根据用户id查询用户
export const getConsumerById = (id) => get(`/consumer/selectConsumerById?id=${id}`)
// ==================收藏=====================
// 删除收藏
export const deleteCollect = (userId,song_id) => get(`/collect/deleteByUserIdAndSongId?user_id=${userId}&song_id=${song_id}`)
// 指定用户的收藏列表
export const getCollectOfUserId = (userId) => get(`/collect/collectOfUserId?user_id=${userId}`)
// ==================收藏=====================
// 删除收藏
export const deleteComment = (id) => get(`/comment/delete?id=${id}`)
// 返回当前歌单的评论列表
export const getCommentOfSongListId = (songlist_id) => get(`/comment/commentOfSongListId?songlist_id=${songlist_id}`) //歌单
// ==================社区=====================
//添加用户
export const addCommunity = (params) => post(`community/add`, params);
//查询所有用户
export const getAllCommunity = () => get(`community/allCommunity`);
//查询指定用户
export const getCommunityOfUsername = (name) => get(`community/communityOfUsername?name=${name}`);
//查询指定id
export const getCommunityOfId = (id) => get(`community/selectById?id=${id}`);
// 删除动态
export const deleteCommunity = (id) => deletes(`/community/delete?id=${id}`)
//删除歌曲信息
// export const deleteListSongInfo = (id) => deletes(`community/deleteSource?id=${id}`);