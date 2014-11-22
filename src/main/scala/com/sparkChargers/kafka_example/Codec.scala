package com.sparkChargers.kafka_example


import com.novus.salat
import com.novus.salat.global._
import kafka.serializer.{Decoder, Encoder}
import kafka.utils.VerifiableProperties
import org.apache.commons.io.Charsets


class LogDecoder(props: VerifiableProperties) extends Decoder[Log] {
  def fromBytes(bytes: Array[Byte]): Log = {
    salat.grater[Log].fromJSON(new String(bytes, Charsets.UTF_8))
  }
}

class LogEncoder(props: VerifiableProperties) extends Encoder[Log] {
  def toBytes(Log: Log): Array[Byte] = {
    salat.grater[Log].toCompactJSON(Log).getBytes(Charsets.UTF_8)
  }
}
