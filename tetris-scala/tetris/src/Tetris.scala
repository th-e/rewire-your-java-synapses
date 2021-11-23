import indigo._, scala.scalajs.js.annotation.JSExportTopLevel
import indigo.IndigoLogger._

@JSExportTopLevel("IndigoGame")
object Tetris extends IndigoSandbox[Unit, TetrisModel]:

  val config: GameConfig         = GameConfig.default.withViewport(400, 400).withFrameRate(5)
  val assets: Set[AssetType]     = Set()
  val animations: Set[Animation] = Set()
  val fonts: Set[FontInfo]       = Set()
  val shaders: Set[Shader]       = Set()

  def setup(assetCollection: AssetCollection, dice: Dice): Outcome[Startup[Unit]] =
    Outcome(Startup.Success(()))

  def initialModel(startupData: Unit): Outcome[TetrisModel] =
    Outcome(TetrisModel(Point(10, 0), 20, Point(0, 0)))

  def updateModel(context: FrameContext[Unit], model: TetrisModel): GlobalEvent => Outcome[TetrisModel] =

    case KeyboardEvent.KeyDown(Key.LEFT_ARROW) => Outcome(model.copy(direction = Point(-1, 0)))

    case KeyboardEvent.KeyDown(Key.RIGHT_ARROW) => Outcome(model.copy(direction = Point(1, 0)))

    case FrameTick =>
      val nextPosition: Point  = model.position + Point(0, 1) + model.direction
      val nextDirection: Point = Point(0, 0)
      Outcome(model.copy(position = nextPosition, direction = nextDirection))

    case _ => Outcome(model)

  def present(context: FrameContext[Unit], model: TetrisModel): Outcome[SceneUpdateFragment] =
    val boxSize = Rectangle(1, 1, 18, 18)
    consoleLog(model.position.toString)
    Outcome(
      SceneUpdateFragment(
        Shape.Box(boxSize, Fill.Color(RGBA.Red)).moveTo((model.position + model.direction) * model.gridSize)
      )
    )

final case class TetrisModel(position: Point, gridSize: Int, direction: Point)
