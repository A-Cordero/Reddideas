package com.aridev.cordero.twitdeas.core

object Constants {
    const val baseUrl = "https://reddit.com"

    const val apiApps = ""
    val listWordsBlock = arrayOf("steal","useful","developer","ITT","reddit")

}

var language = "EN"
var style = "dark" // dark
enum class Tags(val url : String){
    APPS("https://www.reddit.com/r/AskReddit/comments/85j3ki/reddit_whats_your_million_dollar_app_idea_that/.json?sort=new&limit=30&after="),
}