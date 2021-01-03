package app.sylven.oompaloompas.view.oompas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sylven.oompaloompas.databinding.ItemLoadingStateBinding

class OompasLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<OompasLoadingAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(itemBinding: ItemLoadingStateBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private val tvErrorMessage: TextView = itemBinding.tvErrorMessage
        private val progressBar: ProgressBar = itemBinding.progressBar
        private val btnRetry: Button = itemBinding.btnRetry

        init {
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val view = ItemLoadingStateBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(view, retry)
    }

}
