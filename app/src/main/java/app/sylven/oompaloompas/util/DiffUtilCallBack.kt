package app.sylven.oompaloompas.util

import androidx.recyclerview.widget.DiffUtil
import app.sylven.oompaloompas.model.OompaLoompaPageItem

class DiffUtilCallBack : DiffUtil.ItemCallback<OompaLoompaPageItem>() {
    override fun areItemsTheSame(oldItem: OompaLoompaPageItem, newItem: OompaLoompaPageItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OompaLoompaPageItem, newItem: OompaLoompaPageItem): Boolean {
        return oldItem.id == newItem.id
        // We don't expect any data of the oompa to change, otherwise we would check it here
    }
}