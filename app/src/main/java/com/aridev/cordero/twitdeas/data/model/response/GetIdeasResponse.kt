package com.aridev.cordero.twitdeas.data.model.response

import com.aridev.cordero.twitdeas.data.model.app.Idea
import com.google.gson.annotations.SerializedName

class GetIdeasResponse : ArrayList<IdeasResponseElement>()

class IdeasResponseElement(
    var data : GetIdeasResponseData
)

class GetIdeasResponseData(
    var after: String?,
    var before : String?,
    var children : ArrayList<ChildData>
)

class ChildData(
    @SerializedName("subreddit_id")
    var subredditId : String,
    var data : Idea
)
