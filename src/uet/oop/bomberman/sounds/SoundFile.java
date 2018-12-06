package uet.oop.bomberman.sounds;

import javax.sound.sampled.*;
import java.io.File;

public class SoundFile implements LineListener{
    private File                soundFile;
    private AudioInputStream    ais;
    private AudioFormat         format;
    private DataLine.Info       info;
    protected Clip                clip;
    private volatile boolean    playing;
    public FloatControl        gainControl;

    public static final SoundFile step = new SoundFile(".\\res\\sounds\\footstep.wav");
    public static final SoundFile explosion = new SoundFile(".\\res\\sounds\\explosion.wav");
    public static final SoundFile enemy_die = new SoundFile(".\\res\\sounds\\enemy_die.wav");
    public static final SoundFile bomber_die = new SoundFile(".\\res\\sounds\\bomber_die.wav");
//    public static final SoundFile game_over = new SoundFile(".\\res\\sounds\\game_over_music.wav");
    public static final SoundFile point = new SoundFile(".\\res\\sounds\\point.wav");
    public static final SoundFile powerup = new SoundFile(".\\res\\sounds\\powerup.wav");
    public static final SoundFile next_level = new SoundFile(".\\res\\sounds\\next_level.wav");
    public static final SoundFile place_bomb = new SoundFile(".\\res\\sounds\\place_bomb.wav");

    public static final SoundFile background_song1 = new SoundFile(".\\res\\sounds\\background_song1.wav");


    public SoundFile(String filePath){
        try {
            soundFile = new File(filePath);
            ais = AudioSystem.getAudioInputStream(soundFile);
            format = ais.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isPlaying(){
        return playing;
    }

    public void play(){
        try {
            if(!playing) {
                gainControl.setValue(-10);
                clip.start();
                playing = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(LineEvent event) {
        if(event.getType() == LineEvent.Type.START){
            playing = true;
        }
        else if(event.getType() == LineEvent.Type.STOP){
            clip.stop();
            clip.flush();
            playing = false;
            clip.setFramePosition(0);
        }

    }
}
