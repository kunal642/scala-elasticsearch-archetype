package ${package}.utils

import java.net.InetAddress
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient

object EsManager {

  val settings: Settings = Settings.builder().put("cluster.name", "elasticsearch").build()

  val client:Client = new PreBuiltTransportClient(settings)
    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))

}