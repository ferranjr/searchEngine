package com.ferran.searchEngine

import org.scalatest.{Matchers, FlatSpec}


class RankingSpec extends FlatSpec with Matchers {

  import Ranking._

  """Ranking should""" should "order results properly" in {

    val test = Map( 1 -> ("test1", 100),
                    2 -> ("test2", 10),
                    3 -> ("test3", 50),
                    4 -> ("test4", 90)
                )

    getRanking(test) should contain inOrderOnly((1, "test1",100),(4,"test4",90), (3,"test3", 50), (2, "test2", 10))
  }

  it should "only serve top 10 results" in {

    val test = Map( 1 -> ("test1", 100),
                    2 -> ("test2", 10),
                    3 -> ("test3", 50),
                    4 -> ("test4", 90),
                    5 -> ("test5", 91),
                    6 -> ("test6", 92),
                    7 -> ("test7", 93),
                    8 -> ("test8", 94),
                    9 -> ("test9", 95),
                    10 -> ("test10", 96),
                    11 -> ("test11", 97),
                    12 -> ("test12", 98)
                  )

    getRanking(test) should contain inOrderOnly(
      (1, "test1",100),(12,"test12",98), (11,"test11", 97), (10, "test10", 96), (9,"test9",95),
      (8, "test8",94),(7,"test7",93), (6,"test6", 92), (5, "test5", 91), (4,"test4",90)
    )

  }

}
