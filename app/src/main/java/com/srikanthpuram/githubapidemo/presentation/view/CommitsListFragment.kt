package com.srikanthpuram.githubapidemo.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.srikanthpuram.githubapidemo.R
import com.srikanthpuram.githumapidemo.data.remote.api.base.Status
import com.srikanthpuram.githubapidemo.databinding.CommitsListFragmentBinding
import com.srikanthpuram.githubapidemo.presentation.datasource.CommitsListAdapter
import com.srikanthpuram.githubapidemo.presentation.viewmodel.CommitsListViewModel
import com.srikanthpuram.githubapidemo.presentation.viewmodel.UsersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommitsListFragment : Fragment() {

    private val commitsListViewModel: CommitsListViewModel by viewModel()
    private lateinit var itemViewer: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: CommitsListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.commits_list_fragment, container, false)

        binding.commitsListViewModel = commitsListViewModel

        itemViewer = binding.root.findViewById(R.id.itemCommitViewer)
        initAdapterAndObserve()

        return binding.root
    }

    private fun initAdapterAndObserve() {
        val commitsListAdapter = CommitsListAdapter()

        itemViewer.layoutManager = LinearLayoutManager(context)
        itemViewer.adapter = commitsListAdapter

        Transformations.switchMap(commitsListViewModel.dataSource) { dataSource -> dataSource.loadStateLiveData }
            .observe(this, Observer {
                when(it) {
                    Status.LOADING -> {
                        commitsListViewModel.isWaiting.set(true)
                        commitsListViewModel.errorMessage.set(null)
                    }
                    Status.SUCCESS -> {
                        commitsListViewModel.isWaiting.set(false)
                        commitsListViewModel.errorMessage.set(null)
                    }
                    Status.EMPTY -> {
                        commitsListViewModel.isWaiting.set(false)
                        commitsListViewModel.errorMessage.set(getString(R.string.msg_users_list_is_empty))
                    }
                    else -> {
                        commitsListViewModel.isWaiting.set(false)
                        commitsListViewModel.errorMessage.set(getString(R.string.msg_fetch_users_list_has_error))
                    }
                }
            })

        commitsListViewModel.commitsLiveData.observe(this, Observer {
            commitsListAdapter.submitList(it)
        })
    }

}
