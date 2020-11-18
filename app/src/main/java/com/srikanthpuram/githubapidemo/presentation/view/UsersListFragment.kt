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
import com.srikanthpuram.githubapidemo.databinding.UsersListFragmentBinding
import com.srikanthpuram.githumapidemo.data.remote.api.base.Status
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubUserModel
import com.srikanthpuram.githubapidemo.presentation.datasource.UsersListAdapter
import com.srikanthpuram.githubapidemo.presentation.viewmodel.UsersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersListFragment : Fragment(), UsersListAdapter.UsersListAdapterInteraction {

    private val usersListViewModel: UsersListViewModel by viewModel()
    private lateinit var itemViewer: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: UsersListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.users_list_fragment, container, false)

        binding.usersListViewModel = usersListViewModel

        itemViewer = binding.root.findViewById(R.id.itemViewer)
        initAdapterAndObserve()

        return binding.root
    }

    private fun initAdapterAndObserve() {
        val usersListAdapter = UsersListAdapter(this)

        itemViewer.layoutManager = LinearLayoutManager(context)
        itemViewer.adapter = usersListAdapter

        Transformations.switchMap(usersListViewModel.dataSource) { dataSource -> dataSource.loadStateLiveData }
            .observe(this, Observer {
                when(it) {
                    Status.LOADING -> {
                        usersListViewModel.isWaiting.set(true)
                        usersListViewModel.errorMessage.set(null)
                    }
                    Status.SUCCESS -> {
                        usersListViewModel.isWaiting.set(false)
                        usersListViewModel.errorMessage.set(null)
                    }
                    Status.EMPTY -> {
                        usersListViewModel.isWaiting.set(false)
                        usersListViewModel.errorMessage.set(getString(R.string.msg_users_list_is_empty))
                    }
                    else -> {
                        usersListViewModel.isWaiting.set(false)
                        usersListViewModel.errorMessage.set(getString(R.string.msg_fetch_users_list_has_error))
                    }
                }
            })

        Transformations.switchMap(usersListViewModel.dataSource) { dataSource -> dataSource.totalCount }
            .observe(this, Observer {totalCount ->
                totalCount?.let { usersListViewModel.totalCount.set(it)}
            })

        usersListViewModel.usersLiveData.observe(this, Observer {
            usersListAdapter.submitList(it)
        })
    }

    override fun onUserItemClick(githubUserModel: GithubUserModel) {
     //TODO Launch the User Details screen when user selects a Github user from the list
    }
}
