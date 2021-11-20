public class Note {
    private final Pitch pitch;
    private final int octave;
    private final int duration;

    public Note(Pitch pitch, int octave, int duration) {
        this.pitch = pitch;
        this.octave = octave;
        this.duration = duration;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public int getOctave() {
        return octave;
    }

    public int getDuration() {
        return duration;
    }
}
