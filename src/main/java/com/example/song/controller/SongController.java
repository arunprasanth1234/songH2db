/*
 * 
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

// Write your code here 
package com.example.song.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import com.example.song.service.SongH2Service;


@RestController
public class SongController{

    @Autowired
    public SongH2Service songService; 
     
    @GetMapping("/songs")
    public ArrayList<song> getsongs(){
        return songService.getSongs();
    }
    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int songId){
        return songService.getSongById(songId);
        if(song==null){
        throw new Response Status Exception(HttpStatus.Not_Found);
        }
        return song;
    }
    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song){
        return songService.addSong(song);
    }
    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song){
        return songService.updateSong(songId, song);
    }
    @DeleteMapping("/songs/{songId}")
    public void deletesong(@PathVariable("songId") int songId){
        songService.deleteSong(songId);
    }
}