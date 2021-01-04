package app.sylven.oompaloompas.view.oompas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sylven.oompaloompas.R
import app.sylven.oompaloompas.databinding.ItemOompaBinding
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import app.sylven.oompaloompas.util.DiffUtilCallBack
import coil.load

class OompasAdapter(private val listener: OompaAdapterListener) :
    PagingDataAdapter<OompaLoompaPageItem, OompasAdapter.OompaViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OompaViewHolder {
        val view = ItemOompaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OompaViewHolder(view)
    }

    override fun onBindViewHolder(holder: OompaViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it, listener) }
    }

    class OompaViewHolder(itemBinding: ItemOompaBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val imageView: ImageView = itemBinding.ivImage
        private val tvName: TextView = itemBinding.tvName
        private val tvAge: TextView = itemBinding.tvAge
        private val ivGender: ImageView = itemBinding.ivGender
        private val tvProfession: TextView = itemBinding.tvProfession

        fun bindPost(
            oompaLoompaPageItem: OompaLoompaPageItem,
            listener: OompaAdapterListener
        ) {
            with(oompaLoompaPageItem) {
                tvName.text = ("$id $first_name $last_name").toUpperCase()
                imageView.load(image) {
                    crossfade(true)
                    placeholder(R.drawable.ic_missing_image)
                    fallback(R.drawable.ic_missing_image)
                    error(R.drawable.ic_missing_image)
                }
                tvAge.text = age.toString()+"y/o"
                ivGender.setImageResource(if (gender == "F") R.drawable.ic_icon_female else R.drawable.ic_icon_male)
                tvProfession.text = profession

                itemView.setOnClickListener { listener.onOompaSelected(oompaLoompaPageItem) }
            }
        }
    }

    interface OompaAdapterListener {
        fun onOompaSelected(oompa: OompaLoompaPageItem)
    }

}
