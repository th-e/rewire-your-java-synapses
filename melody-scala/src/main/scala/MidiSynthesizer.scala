import javax.sound.midi.MidiChannel
import javax.sound.midi.MidiSystem
import javax.sound.midi.Synthesizer
import Pitch.*

class MidiSynthesizer: 

  val instrument = 3
  val volume = 127
  val synth = MidiSystem.getSynthesizer
  val channel = synth.getChannels.toList(instrument)

  def play(note: Note) =
    playMidi(midiId(note.pitch, note.octave), note.duration)

  def playMidi(midiId: Int, duration: Int) =
    println(midiId)
    channel.noteOn(midiId, volume)
    Thread.sleep(duration)
    channel.noteOff(midiId)

  def midiId(pitch: Pitch, octave: Int) = pitch.ordinal + octave * 12

