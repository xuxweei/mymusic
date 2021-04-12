package com.xuwei.music.dao;

import com.xuwei.music.entity.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 歌手Dao
 * */
@Repository
public interface SingerDao {
    /*增加*/
    public int addSinger(Singer singer);

    /*修改*/
    public int updateSinger(Singer singer);

    /*删除*/
    public int deleteSinger(Integer id);

    /*根据id查询*/
    public Singer selectSingerById(Integer id);

    /*查询所有歌手*/
    public List<Singer> allSinger();

    /*根据歌手名字模糊查询*/
    public List<Singer> singerOfName(String name);


    /*根据性别查询*/
    public List<Singer> singerOfSex(Integer sex);

    /*修改歌手图片*/
    public int updateSingerImg(Singer singer);
}
