package scala.workshop

trait Traversable {
  def foreach(f: Any => Unit): Unit
}