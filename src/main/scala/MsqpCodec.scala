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

import scodec.bits._
import scodec.Codec
import scodec.codecs._
import scodec.protocols.ip

sealed trait Region
object Region {
  case object UsEastCoast extends Region
  case object UsWestCoast extends Region
  case object SouthAmerica extends Region
  case object Europe extends Region
  case object Asia extends Region
  case object Australia extends Region
  case object MiddleEast extends Region
  case object Africa extends Region
  case object RestOfTheWorld extends Region

  implicit val codec: Codec[Region] = discriminated[Region]
    .by(uint8)
    .typecase(0, provide(UsEastCoast))
    .typecase(1, provide(UsWestCoast))
    .typecase(2, provide(SouthAmerica))
    .typecase(3, provide(Europe))
    .typecase(4, provide(Asia))
    .typecase(5, provide(Australia))
    .typecase(6, provide(MiddleEast))
    .typecase(7, provide(Africa))
    .typecase(255, provide(RestOfTheWorld))
}

case class IpPort(address: ip.v4.Address, port: ip.Port)
object IpPort {
  implicit val codec: Codec[IpPort] = cstring.xmap[IpPort](s => {
    val Array(addr, port) = s.split(':')
    IpPort(ip.v4.Address.fromStringValid(addr), ip.Port(port.toInt))
  }, ipPort => s"${ipPort.address}:${ipPort.port}")
}

case class Request(region: Region, ipPort: IpPort, filter: String)
object Request {
  implicit val codec: Codec[Request] = (constant(hex"31") ~> Region.codec :: IpPort.codec :: cstring).as[Request]
}

case class Response(servers: List[IpPort])
object Response {
  implicit val codec: Codec[Response] =
    (constant(hex"FFFFFFFF660A") ~> list((ip.v4.Address.codec :: ip.Port.codec).as[IpPort])).as[Response]
}
