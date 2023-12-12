import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Music {
    private String name;
    private String artist;
    private int duration;
    public Music(String name, String artist, int duration) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (artist: " + artist + "), duration: " + duration/60 + " min " + duration%60 + " sec";
    }

    public int getDuration() {
        return duration;

    }
}


