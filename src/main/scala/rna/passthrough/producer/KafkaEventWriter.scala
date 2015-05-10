package rna.passthrough.producer

import kafka.producer.{KeyedMessage, ProducerConfig, Producer}

/**
 * Created by Vidhya Arvind on 5/9/15.
 */
class KafkaEventWriter(config: ProducerConfig) {

    val kafkaProducer = new Producer[String, String](config)

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() {
        kafkaProducer.close()
      }
    })

    def write(topic: String, event: String): Unit = {
        val data = new KeyedMessage[String, String](topic, event)
        kafkaProducer.send(data)
    }

    def close() = kafkaProducer.close()
}
