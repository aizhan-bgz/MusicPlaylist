import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Music {
    private String title;
    private String artist;
    private int duration;
    public Music(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        int minutes = duration / 60;
        int seconds = duration % 60;

        return title + " - " + artist + " (" + minutes + " мин " + seconds + " сек)";

    }

    public int getDuration() {
return duration;

    }
}


