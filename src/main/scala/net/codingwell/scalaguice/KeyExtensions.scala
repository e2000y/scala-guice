/*
 *  Copyright 2010-2014 Benjamin Lings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.codingwell.scalaguice

import com.google.inject._
import com.google.inject.name.Names
import java.lang.annotation.{Annotation => JAnnotation}
import scala.reflect.ClassTag

object KeyExtensions {

  implicit class ScalaTypeLiteral[T](val self: TypeLiteral[T]) extends AnyVal {
    def toKey: Key[T] = Key.get(self)
    def annotatedWith(annotation: JAnnotation): Key[T] = Key.get(self, annotation)
    def annotatedWith(clazz: Class[_ <: JAnnotation]): Key[T] = Key.get(self, clazz)
    def annotatedWith[TAnn <: JAnnotation: ClassTag]: Key[T] = Key.get(self, cls[TAnn])
    def annotatedWithName(name: String): Key[T] = annotatedWith(Names.named(name))
  }
}
