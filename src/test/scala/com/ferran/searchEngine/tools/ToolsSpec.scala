package com.ferran.searchEngine.tools

import org.scalatest.{Matchers, FlatSpec}


class ToolsSpec extends FlatSpec with Matchers {

  import Tools._

  "Word Splitter" should "split words form a normal string and lowercase" in {
    val stringTest = "This is a simple string test"

    val res = wordSplitter(stringTest)

    res.length should ===(5)
    res should contain only ("This", "is", "simple", "string", "test")
  }

  it should "split words being aware of characters like numbers, !, % ..." in {
    val stringTest = "String TEST! 12345, must, -work%"

    val res = wordSplitter(stringTest)

    res.length should ===(4)
    res should contain only ("String", "TEST", "must", "work")
  }

}
