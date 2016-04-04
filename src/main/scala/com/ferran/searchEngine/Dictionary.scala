package com.ferran.searchEngine

import com.ferran.searchEngine.SearchEngine.{DocName, DocId, Dictionary}

object Dictionary {
  def searchWord(dictionary: Dictionary, word: String):Map[DocId, DocName] = {
    dictionary.getOrElse(word.toLowerCase, Map.empty[DocId, DocName])
  }

  def searchWords(dictionary: Dictionary, textWords: String):Map[DocId,(DocName, Int)] = {

    val words = textWords.split(" ").map(_.toLowerCase)
    val totalWords = words.length

    words.foldLeft(Map.empty[DocId, (DocName, Int)]){
      case (acc, word) =>
        searchWord(dictionary, word)
          .foldLeft(acc){ case(a,r) =>
            a.get(r._1) match {
              case Some((_, count)) =>
                a.updated( r._1, (r._2, count + 1))
              case None =>
                a.updated( r._1, (r._2, 1))
            }
          }
    }
      .map{ r =>
        r.copy( _2 = r._2.copy( _2 = (r._2._2 * 100)/totalWords))
      }
  }

}
