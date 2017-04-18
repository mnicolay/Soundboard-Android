package edu.depaul.csc472.nicolay_m_finalproject.sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.depaul.csc472.nicolay_m_finalproject.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SoundList {

    /**
     * An array of sample (dummy) items.
     */
    public static ArrayList<Sound> SOUNDS = new ArrayList<Sound>();
    public static ArrayList<Sound> SOUNDS_BACKUP = new ArrayList<Sound>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Sound> SOUND_MAP = new HashMap<String, Sound>();
    public static final Map<String, Sound> SOUND_MAP_BACKUP = new HashMap<String, Sound>();




    public static void addSound(Sound sound) {
        if(!SOUNDS.contains(sound))
            SOUNDS.add(sound);
        SOUND_MAP.put(sound.getLabel(), sound);
//        changeButtonNum(sound);

    }

    public static void backup(){
        SOUNDS_BACKUP = SOUNDS;
        for(Sound s : SOUNDS)
            SOUND_MAP_BACKUP.put(s.getLabel(), s);
    }

    public static void restore(){
        SOUNDS = SOUNDS_BACKUP;
        for(Sound s : SOUNDS)
            SOUND_MAP.put(s.getLabel(), s);

    }

    public static void makeSoundList(ArrayList<Sound> allSounds){
        for(Sound sound : allSounds)
            addSound(sound);
    }

    public static void changeButtonNum(Sound sound){
        for(Sound s : SOUNDS)
            if(!s.equals(sound) && s.getButtonNum() == sound.getButtonNum()) {
                int i = SOUNDS.indexOf(s);
                s.setButtonNum(9);
                SOUNDS.set(i, new Sound(s.getLabel(), s.getFilePath(), s.getResourceID(), 9));
            }
        SOUNDS.set(SOUNDS.indexOf(sound), sound);
        SOUND_MAP.put(sound.getLabel(), sound);
    }


//    static {
//        for (Sound sound : sounds) {
//            addSound(sound);
//        }
//
//    }

}
