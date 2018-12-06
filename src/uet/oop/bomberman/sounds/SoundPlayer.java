package uet.oop.bomberman.sounds;

import uet.oop.bomberman.Game;

import javax.sound.sampled.LineEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class SoundPlayer implements Runnable {
    private ArrayList<SoundFile>    playList;
    private int                     current_index = 0;
    private boolean                 running ;

    public SoundPlayer( SoundFile... files) {

        playList = new ArrayList<SoundFile>();
        playList.addAll(Arrays.asList(files));
    }

    public int getCurrent_index() {
        return current_index;
    }

    @Override
    public void run() {
        running = true;
        SoundFile song = playList.get(current_index);
        song.gainControl.setValue(-15);
        song.play();
        System.out.println(running);
        while (running ) {
            if (!song.isPlaying()) {
                current_index++;
                if (current_index >= playList.size()) current_index = 0;
                song = playList.get(current_index);
                song.gainControl.setValue(-15);
                song.play();
            }
        }
    }
    public void stop(){
        running = false;
        for(SoundFile song: playList){
            song.clip.stop();
            song.clip.flush();
            song.clip.setFramePosition(0);
        }
    }
}
