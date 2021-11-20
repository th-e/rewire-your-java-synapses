import Pitch.*

class MelodyAsValues:

  def play() =
    // note the reverse
    val melody = List(
      Note(C, 6, 250),
      Note(A, 5, 500),
      Note(C, 6, 250),
      Note(A, 5, 500),
      Note(G, 5, 250),
      Note(F, 5, 250),
      Note(G, 5, 250),
      Note(F, 5, 750)
    ).reverse

    // impure world
    val synthesizer = MidiSynthesizer()
    synthesizer.synth.open()
    melody.foreach(note => synthesizer.play(note))
    synthesizer.synth.close()
