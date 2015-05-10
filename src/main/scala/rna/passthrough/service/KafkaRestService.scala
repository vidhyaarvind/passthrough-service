package rna.passthrough.service

/**
 * Created by Vidhya Arvind on 5/9/15.
 */

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import rna.passthrough.producer.KafkaWriter
import com.twitter.finatra._


object KafkaRestService extends HttpServer {

  override def configureHttp(router: HttpRouter) {
    router.
      filter[CommonFilters].
      add[KafkaRestController]
  }

  class KafkaRestController extends Controller {

    /**
     * Basic Example
     *
     * curl http://localhost:7070/ => "hello world"
     */
    get("/status") { request: Request =>
      response.ok.body("Hello World").toFuture
    }

    post("/event") { request: Request =>
      request.params.get("data") match {
        case Some(value) => {
          KafkaWriter.producer.write(KafkaWriter.topic, value.toString)
          response.ok.body(value.toString).toFuture
        }
        case None => response.notFound("No param").toFutureException
      }
    }
  }
}
