package com.srikanthpuram.githubapidemo.presentation.datasource

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.srikanthpuram.githubapidemo.R
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubCommits

class CommitsListAdapter(): PagedListAdapter<GithubCommits, CommitsListAdapter.CommitsListViewHolder>(commitsDiffCallback) {

    lateinit var context: Context

    inner class CommitsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commitItem: RelativeLayout = itemView.findViewById(R.id.list_item_commit)
        val author: TextView = itemView.findViewById(R.id.author)
        val timeStamp: TextView = itemView.findViewById(R.id.timestamp)
        val commiteMessage: TextView = itemView.findViewById(R.id.commitmessage)
        val commitHash: TextView = itemView.findViewById(R.id.commithash)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsListAdapter.CommitsListViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_commits, parent, false)
        return CommitsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommitsListAdapter.CommitsListViewHolder, position: Int) {
        val githubCommitsItem = getItem(position)
        githubCommitsItem.let {
            holder.author.text = it?.commit?.author?.name ?: ""
            holder.timeStamp.text = it?.commit?.author?.date ?: ""
            holder.commiteMessage.text = it?.commit?.message ?: ""
            holder.commitHash.text = it?.sha
        }
    }

    companion object {
        val commitsDiffCallback = object : DiffUtil.ItemCallback<GithubCommits>() {
            override fun areItemsTheSame(oldItem: GithubCommits, newItem: GithubCommits): Boolean {
                return oldItem.sha == newItem.sha
            }
            override fun areContentsTheSame(oldItem: GithubCommits, newItem: GithubCommits): Boolean {
                return oldItem == newItem
            }
        }
    }
}