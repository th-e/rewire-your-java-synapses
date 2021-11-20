final case class Note(val pitch: Pitch, val octave: Int, val duration: Int)

// hint: # is not allowed for identifiers: Db = C#
enum Pitch:
  case C, Db, D, Eb, E, F, Gb, G, Ab, A, Bb, B
