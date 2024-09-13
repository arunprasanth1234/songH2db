/*
 * 
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.song.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Service
public class SongH2Service implements SongRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public void deleteSong(int songId){
        db.update("delete from book where songId=?", songId);
    }
    @Override
    public Song updateSong(int songId, Song song){
        if(song.getSongName() != null){
            db.update("update song set songName=? where songId=?", song.getSongName(), songId);
        }
        if(song.getLyricist() != null){
            db.update("update song set Lyricist=? whrer songId=?", song.getLyricist(), songId);
        }
        if(song.getSinger() != null){
            db.update("update song set Singer=? whrer songId=?", song.getSinger(), songId);
        }
        if(song.getMusicDirector() != null){
            db.update("update song set musicDirector=? whrer songId=?", song.getMusicDirector(), songId);
        }
        return getSongById(songId);
    }
    @Override
    public Song getSongById(int songId){
        try{
            Song song =db.queryForObject("Select * from song where songId=?", new SongRowMapper(), songId);
            return song;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.Not_Found);
        }
    }
    @Override
    public ArrayList <Song> getSongs(){
        List<Song> songList =db.query("Select * form song", new SongRowMapper());
        ArrayList<Song> songs = new ArrayList<>(songList);
        return songs;
    }
    @Override 
    public Song addSong(Song song){
        db.update("insert into song(songName, Lyricist, Singer, musicDirector) values(?, ?, ?, ?)",
        song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        Book SavedBook=db.queryForObject("Select * from book where songName=? and Lyricist=? and singer=? and 
        musicDirector=?", new SongRowMapper(), song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        return SavedBook;
    }
}