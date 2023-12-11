public class Music {
    private String name;
    private String artist;
    private Integer duration;

    public Music() {
    }

    public Music(String name, String artist, Integer duration) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return name + " (artist: " + artist + "), duration: " + duration/60 + " min " + duration%60 + " sec";
    }
}
