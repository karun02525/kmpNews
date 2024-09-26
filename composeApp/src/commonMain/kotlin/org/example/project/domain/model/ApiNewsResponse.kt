package org.example.project.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponseApiNews(
    val status:String?,
    val totalResults:Int?,
    val articles:List<Articles?>
)

@Serializable
data class Articles(
    val source:Source?,
    val title:String?,
    val description:String?,
    val url:String?,
    val author:String?,
    @SerialName("urlToImage")
    val urlImage:String?,
    val content:String?,
    val publishedAt:String?,
)

@Serializable
data class Source(
  val id:String?,
    val name:String?
)

/*

{
  "status": "ok",
  "totalResults": 8541,
  "articles": [
    {
      "source": {
        "id": null,
        "name": "taz.de"
      },
      "author": "Uli Hannemann",
      "title": "Wie Tesla mit Krankmeldungen umgeht: Dein Chef prüft ob du krank bist",
      "description": "Wer für Tesla arbeiten soll, aber stattdessen krank zu Hause ist, bekommt schon mal unangemeldet Besuch von den Chefs. Wundert das noch irgendwen?",
      "url": "https://taz.de/Wie-Tesla-mit-Krankmeldungen-umgeht/!6035705/",
      "urlToImage": "https://taz.de/picture/7263216/948/36604803-1.jpeg",
      "publishedAt": "2024-09-25T17:52:00Z",
      "content": "Wer für Tesla arbeiten soll, aber stattdessen krank zu Hause ist, bekommt schon mal unangemeldet Besuch von den Chefs. Wundert das noch irgendwen?\r\nKranken hinterherspionieren: Mischung aus Argwohn, … [+5226 chars]"
    },

*/
