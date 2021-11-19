import Pitch.*

class Melody:

  val synthesizer = Synthesizer()

  def play() =
    synthesizer.synth.open()
    c(); a(); c(); a(); g(); f(); g(); f3()
    synthesizer.synth.close()

  def c() = synthesizer.play(Note(C, 6, 250))
  def a() = synthesizer.play(Note(A, 5, 500))
  def g() = synthesizer.play(Note(G, 5, 250))
  def f() = synthesizer.play(Note(F, 5, 250))
  def f3() = synthesizer.play(Note(F, 5, 750))
