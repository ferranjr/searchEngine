package com.ferran.searchEngine

import java.io.File

import com.ferran.searchEngine.tools.{FileLoader, Tools}

import scala.collection.immutable.HashMap
import scala.io.Source

object SearchEngine {

  type DocId      = Int
  type DocName    = String
  type Dictionary = HashMap[String, Map[DocId, DocName]]

  type RankingList = List[(DocId, DocName, Int)]

  def loadWords(dictionary: Dictionary, file: File, index: Int):Dictionary = {
    val fileName = file.getName
    Tools.wordSplitter(Source.fromFile(file).mkString)
      .foldLeft(dictionary){
        case (dict, word) =>
          dict.get(word.toLowerCase) match {
            case Some(docs) =>
              dict.updated(word.toLowerCase,
                {
                  if(docs.contains(index)) docs
                  else docs ++ Map(index -> fileName)
                }
              )
            case None =>
              dict.updated(word.toLowerCase, Map(index -> fileName))
          }
        }
  }


  final def main(args:Array[String]) = {
    val pathFolder = args(0)
    val filesOpt = FileLoader.getFilesList(pathFolder)

    // Create our Dictionary
    val dictionary =
      filesOpt.map{
        files =>
          files.zipWithIndex.foldLeft(HashMap.empty[String, Map[DocId, DocName]]){
            case (dict, (file, index)) => loadWords(dict, file, index)
          }
      }
      .getOrElse(HashMap.empty)

    println(s"Files loaded")
    print(s"search> ")
    var line = scala.io.StdIn.readLine()
    while(line != ":quit"){
      Ranking.printRanking(Ranking.getRanking(Dictionary.searchWords(dictionary,line)))
      print(s"search> ")
      line = scala.io.StdIn.readLine()
    }
  }
}
