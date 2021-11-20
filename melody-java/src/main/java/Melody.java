import javax.sound.midi.MidiUnavailableException;

public class Melody {

    private MidiSynthesizer synthesizer = new MidiSynthesizer();

    public void play() {
        try {
            synthesizer.synth.open();
            synthesizer.synth.close();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }


    }
}