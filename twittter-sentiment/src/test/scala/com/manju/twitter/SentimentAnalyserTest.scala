package com.manju.twitter

import org.scalatest._
import com.manju.twitter.SentimentAnalyser._

class SentimentAnalyserTest extends FlatSpec  {
  
  it should "detect not understood" in {
    println(detectSentiment(""))
    assert(detectSentiment("") == "NOT_UNDERSTOOD")
  }
  
  it should "detect a negative sentiment" in {
    println(detectSentiment("I am feeling very sad and frustrated."))
    assert(detectSentiment("I am feeling very sad and frustrated.") == "NEGATIVE")
  }
  
  it should "detect a neutral sentiment" in {
    println(detectSentiment("I'm watching a movie"))
    assert(detectSentiment("I'm watching a movie") == "NEUTRAL")
  }
  
  it should "detect a positive sentiment" in {
    println(detectSentiment("It was a nice experience."))
    assert(detectSentiment("It was a nice experience.") == "POSITIVE")
  }
  
  it should "detect a very positive sentiment" in {
    println(detectSentiment("It was a very nice experience."))
    assert(detectSentiment("It was a very nice experience.") == "VERY_POSITIVE")
  }
  
  it should "Test 6" in {
    println(detectSentiment("I love this world"))
    assert(detectSentiment("I love this world") == "POSITIVE")
  }
  
  it should "Test 7" in {
    println(detectSentiment("@jetblue you have a MUJI store in the JFK terminal but nowhere to get a BAGEL?#YOU"))
    assert(detectSentiment("@jetblue you have a MUJI store in the JFK terminal but nowhere to get a BAGEL?#YOU") == "NEGATIVE")
  }
  
  it should "Test 8" in {
    println(detectSentiment("@Seamless almost 2 hours and still no food delivery from Chanpen Thai in NYC. Not pleased and very hungry!!!!!"))
    assert(detectSentiment("@Seamless almost 2 hours and still no food delivery from Chanpen Thai in NYC. Not pleased and very hungry!!!!!") == "NEGATIVE")
  }
  
  it should "Test 9" in {
    println(detectSentiment("Seriously @Comcast, why must my wireless signal go out intermittently for hours/days every couple of weeks? I am so over this. #evil"))
    assert(detectSentiment("Seriously @Comcast, why must my wireless signal go out intermittently for hours/days every couple of weeks? I am so over this. #evil") == "NEGATIVE")
  }
  
  it should "Test 10" in {
    println(detectSentiment("Heads up Sys #Admins! Keep an eye on #Blackboard social channels tomorrow for an fun contest to get you pumped up for the Fall!"))
    assert(detectSentiment("Heads up Sys #Admins! Keep an eye on #Blackboard social channels tomorrow for an fun contest to get you pumped up for the Fall!") == "POSITIVE")
  }
}