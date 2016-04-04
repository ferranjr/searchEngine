package com.ferran.searchEngine.tools

import java.io.{FileFilter, File}

import scala.util.Try


object FileLoader {


  def getFilesList(folderName: String):Option[Seq[File]] = {
    Try{
      new File(folderName).listFiles.filter(_.isFile).toSeq
    }
    .toOption
  }
}
