package com.manju.twitter

/**
 * @author Manjunath Kempaiah
 * @version 1.0
 * 
 * Defined couple of enums which will be used in Sentiment Analysis.
 *
 */
sealed trait SENTIMENT_TYPE 
case object NEGATIVE extends SENTIMENT_TYPE
case object NEUTRAL extends SENTIMENT_TYPE
case object POSITIVE extends SENTIMENT_TYPE
case object VERY_POSITIVE extends SENTIMENT_TYPE
case object NOT_UNDERSTOOD extends SENTIMENT_TYPE