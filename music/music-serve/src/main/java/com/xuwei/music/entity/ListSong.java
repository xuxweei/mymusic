package com.xuwei.music.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 歌单歌曲
 * @author 
 */
@Data
public class ListSong implements Serializable {
    private Integer id;

    /**
     * 歌曲id
     */
    private Integer song_id;

    /**
     * 歌单id
     */
    private Integer songlist_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSong_id() {
        return song_id;
    }

    public void setSong_id(Integer song_id) {
        this.song_id = song_id;
    }

    public Integer getSonglist_id() {
        return songlist_id;
    }

    public void setSonglist_id(Integer songlist_id) {
        this.songlist_id = songlist_id;
    }
}