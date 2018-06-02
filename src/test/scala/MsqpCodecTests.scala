/*
 * Copyright 2018 https://github.com/2m/msqp4s/graphs/contributors
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

package lt.dvim.msqp

import java.nio.file.{Files, Paths}

import scodec.bits.ByteVector
import utest._

object MsqpCodecTests extends TestSuite {

  val tests = Tests {
    "msqp codec" - {
      "basic-request" - cycleRequest()
      //"basic-response" - cycle()
    }
  }

  def cycleRequest()(implicit path: framework.TestPath) = {
    val name = path.value.last
    val original = name.byteVector.toBitVector
    val decoded = Request.codec.decodeValue(original).require
    val encoded = Request.codec.encode(decoded).require
    encoded ==> original
  }

  implicit class StringOps(name: String) {
    def byteVector = ByteVector(Files.readAllBytes(Paths.get(s"src/test/resources/samples/$name.bin")))
  }
}
