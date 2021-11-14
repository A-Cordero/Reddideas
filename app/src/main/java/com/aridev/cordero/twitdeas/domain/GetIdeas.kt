package com.aridev.cordero.twitdeas.domain

import android.os.Build
import androidx.annotation.RequiresApi
import com.aridev.cordero.twitdeas.core.Constants
import com.aridev.cordero.twitdeas.core.Tags
import com.aridev.cordero.twitdeas.data.model.response.GetIdeasResponseData
import com.aridev.cordero.twitdeas.data.retrofitRepository.ApiClient
import com.aridev.cordero.twitdeas.data.retrofitRepository.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class GetIdeas : KoinComponent{
    private val retrofitService = RetrofitService()
    private val api: ApiClient by inject()
    @RequiresApi(Build.VERSION_CODES.N)
    operator fun invoke(
        urlResource : String,
        callback: (success: GetIdeasResponseData?, error: String?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                retrofitService(
                    api.getIdeas(
                        urlIdea = urlResource
                    )
                ) { success, error ->
                    if(error.isNullOrEmpty()) {
                        var listIdeas = GetIdeasResponseData("","", arrayListOf())
                        listIdeas.after = success!![1].data.after
                        listIdeas.before = success!![1].data.before
                        success!![1].data.children.forEachIndexed { index, childData ->
                            if(index == 0 || index == success[1].data.children.size - 1) {

                            } else if( childData.data.body == "[deleted]") {

                            } else if(Arrays.stream(Constants.listWordsBlock).anyMatch(childData.data.body::contains)){

                            }else {
                                listIdeas.children.add(childData)
                            }
                        }
                        callback.invoke(listIdeas, error)
                    } else {
                        callback.invoke(null,"Ha ocurrido un error, intentelo nuevamente.")
                    }
                }
            }
        }
    }
}