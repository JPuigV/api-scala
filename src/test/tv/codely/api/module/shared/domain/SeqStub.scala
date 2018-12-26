package tv.codely.api.module.shared.domain

object SeqStub {
  def randomOf[T](apply: => T): Seq[T] = (0 to IntStub.randomBetween(1, 2)).map(_ => apply)
}
