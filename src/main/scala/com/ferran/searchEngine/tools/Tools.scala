package com.ferran.searchEngine.tools

import scala.util.matching.Regex


object Tools {

  /**
   * Splitting string into words
   * @param str
   * @return
   */
  def wordSplitter(str: String):List[String] = {
    val pattern = "([a-z,A-Z])\\w+".r
    pattern.findAllIn(str).toList
  }




}
