package rna.passthrough.producer

import java.util.Properties
import kafka.producer.ProducerConfig
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config


/**
 * Created by Vidhya Arvind on 5/10/15.
 *
 */
object KafkaWriter {

  val conf: Config = ConfigFactory.load();
  val props = new Properties()
  props.setProperty("metadata.broker.list", conf.getString("metadata.broker.list"))
  props.setProperty("serializer.class", conf.getString("serializer.class"))
  //props.setProperty("partitioner.class", conf.getString("partitioner.class"))
  props.setProperty("request.required.acks", conf.getString("request.required.acks"))

  val topic = conf.getString("topic")

  val kafkaProperties = new ProducerConfig(props)

  val producer = new KafkaEventWriter(kafkaProperties)

}
