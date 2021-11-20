import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiSynthesizer {

    private static int INSTRUMENT = 3;
    private static int VOLUME = 127;
    public Synthesizer synth;
    private MidiChannel channel;

    public MidiSynthesizer()  {
        try {
            synth = MidiSystem.getSynthesizer();
            channel = synth.getChannels()[INSTRUMENT];
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(Note note){
        try {
            playMidi(midiId(note.getPitch(), note.getOctave()), note.getDurtion());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void playMidi(int midiId, int duration) throws InterruptedException {
        System.out.println(midiId);
        channel.noteOn(midiId, VOLUME);
        Thread.sleep(duration);
        channel.noteOff(midiId);
    }


    private int midiId(Pitch pitch, int octave) {
        return pitch.ordinal() + octave * 12;
    }
}
