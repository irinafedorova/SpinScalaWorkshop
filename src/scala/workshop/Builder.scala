package scala.workshop

trait Builder[T] {
  def +=(elem: Any)
  def result: T
  def clear: Unit
}