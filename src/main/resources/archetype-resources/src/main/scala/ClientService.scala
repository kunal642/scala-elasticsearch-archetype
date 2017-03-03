package ${package}

import {package}.utils.EsManager

class ClientService @Inject()(esManager: ESManager) {

  val client: Client

  def add(firstName: String,
      lastName: String,
      title: String,
      price: Int,
      id: String): IndexResponse = {
    val jsonString = s"""
  {
    "title": "$title",
    "price": $price,
    "author":{
      "first": "$firstName",
      "last": "$lastName"
    }
  }"""
    client.prepareIndex("library", "books", id).setSource(jsonString).get()
  }

  def update(id: String, field: String, value: Any): UpdateResponse = {
    client.prepareUpdate("library", "books", id).setDoc(field, value).get()
  }

  def delete(id: Int) = {
    client.prepareDelete("library", "books", s"$id").get()
  }

  def searchAll = {
    client.admin().indices().prepareRefresh("library").get()
    client.prepareSearch("library").execute().actionGet()
  }

  def search(field: String, value: String) = {
    client.admin().indices().prepareRefresh("library").get()
    client.prepareSearch("library").setTypes("books")
      .setQuery(QueryBuilders.termQuery(s"$field", s"$value")).execute().actionGet()
  }

}