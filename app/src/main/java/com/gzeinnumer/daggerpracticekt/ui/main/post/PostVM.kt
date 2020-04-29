package com.gzeinnumer.daggerpracticekt.ui.main.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.gzeinnumer.daggerpracticekt.SessionManager
import com.gzeinnumer.daggerpracticekt.network.mainApi.MainApi
import com.gzeinnumer.daggerpracticekt.network.mainApi.model.ResponsePost
import com.gzeinnumer.daggerpracticekt.ui.main.MainResource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PostVM @Inject constructor(val sessionManager: SessionManager, val  mainApi: MainApi) :
    ViewModel() {

    companion object {
        private const val TAG = "PostVM"
    }

    init {
        Log.d(TAG, "PostVM: ready")
    }

    var posts :MediatorLiveData<MainResource<List<ResponsePost>>>? = null
    fun observePosts(): LiveData<MainResource<List<ResponsePost>>>? {
        if (posts == null) {
            posts = MediatorLiveData<MainResource<List<ResponsePost>>>()
            posts?.value = MainResource.loading()
            val source: LiveData<MainResource<List<ResponsePost>>> =
                LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPotsFromUser(sessionManager.authUser.value?.data?.id!!)
                        .onErrorReturn{
                            Log.d(TAG, "apply: ", it)
                            val responsePost = ResponsePost()
                            responsePost.id = -1
                            val p: ArrayList<ResponsePost> = ArrayList<ResponsePost>()
                            p.add(responsePost)
                            p
                        }
                        .map(object : Function<List<ResponsePost>, MainResource<List<ResponsePost>>>{
                            override fun apply(t: List<ResponsePost>): MainResource<List<ResponsePost>> {
                                if (t.isNotEmpty()) {
                                    if (t[0].id == -1) {
                                        return MainResource.error("Ada yang salah")
                                    }
                                }
                                return MainResource.success(t)
                            }
                        })
                        .subscribeOn(Schedulers.io())
                )
            posts?.addSource(
                source,
                Observer { listMainResource ->
                    posts?.value = listMainResource
                    posts?.removeSource(
                        source
                    )
                })
        }
        return posts
    }
}
