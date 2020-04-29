package com.gzeinnumer.daggerpracticekt.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gzeinnumer.daggerpracticekt.R
import com.gzeinnumer.daggerpracticekt.ui.auth.AuthResource
import com.gzeinnumer.daggerpracticekt.ui.main.MainResource
import com.gzeinnumer.daggerpracticekt.util.VerticalSpacingItemDecoration
import com.gzeinnumer.daggerpracticekt.vm.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class PostFragment : DaggerFragment() {

    private val TAG = "PostFragment"

    lateinit var viewModel: PostVM
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d(TAG, "onViewCreated: ")

        recyclerView = view.findViewById(R.id.recycler_view)
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostVM::class.java)

        initRecyclerView()
        subscribeObservers()
    }
    private fun subscribeObservers() {
        viewModel.observePosts()?.removeObservers(viewLifecycleOwner)
        viewModel.observePosts()?.observe(
            viewLifecycleOwner,
            Observer { listMainResource ->
                if (listMainResource != null) {
                    Log.d(TAG, "onChanged: " + listMainResource.data)
                    when (listMainResource.status) {
                        MainResource.Status.LOADING -> Log.d(TAG, "onChanged: LOADING...")
                        MainResource.Status.SUCCESS -> {
                            Log.d(TAG, "onChanged: SUCCESS got post...")
                            postsRecyclerAdapter.setPosts(listMainResource.data!!)
                        }
                        MainResource.Status.ERROR -> Log.d(
                            TAG,
                            "onChanged: ERROR " + listMainResource.message
                        )
                    }
                }
            })
    }
    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpacingItemDecoration(15)
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = postsRecyclerAdapter
    }
}
