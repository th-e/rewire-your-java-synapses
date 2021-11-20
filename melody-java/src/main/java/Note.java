public class Note {
    private Pitch pitch;
    private int octave;
    private int durtion;

    public Note(Pitch pitch, int octave, int durtion) {
        this.pitch = pitch;
        this.octave = octave;
        this.durtion = durtion;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public int getOctave() {
        return octave;
    }

    public int getDurtion() {
        return durtion;
    }
}
