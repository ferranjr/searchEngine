package com.ferran.searchEngine

import com.ferran.searchEngine.SearchEngine.{RankingList, DocId, DocName}


object Ranking {
  def getRanking(results: Map[DocId, (DocName, Int)]): RankingList = {
    results.toList.sortWith( (a,b) => a._2._2 > b._2._2 ).take(10)
      .map { res =>
        (res._1, res._2._1, res._2._2)
      }
  }

  def printRanking(in: RankingList):Unit = {
    println(
      in.map{
        case (id, name, percentage) =>
          s"Document $id ($name) : $percentage%"
      }.mkString("\n- ","\n- ","\n"))
  }

}