package app.sylven.oompaloompas.view.detail

import android.app.Application
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import app.sylven.oompaloompas.R
import app.sylven.oompaloompas.databinding.FragmentOompaDetailBinding
import app.sylven.oompaloompas.model.OompaLoompa
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import app.sylven.oompaloompas.viewModel.OompaDetailViewModel
import app.sylven.oompaloompas.viewModel.OompaDetailViewModelFactory
import coil.load
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OompaDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentOompaDetailBinding
    private lateinit var viewModel: OompaDetailViewModel
    private lateinit var oompa: OompaLoompaPageItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentOompaDetailBinding.inflate(inflater, container, false)

        val application: Application = requireActivity().application
        oompa = OompaDetailFragmentArgs.fromBundle(requireArguments()).oompaDetailFragmentModel
        val factory = OompaDetailViewModelFactory(application, oompa)
        viewModel = ViewModelProvider(this, factory).get(
            OompaDetailViewModel::class.java
        )

        fetchOompaInfo()

        return viewDataBinding.root
    }

    private fun fetchOompaInfo() {
        lifecycleScope.launch {
            fetchOompaDetails()
            viewModel.getSelectedOompa()?.collectLatest {
                with(viewDataBinding) {
                    tvName.text = (oompa.first_name+" "+oompa.last_name).toUpperCase()
                    ivImage.load(oompa.image) {
                        crossfade(true)
                        placeholder(R.drawable.ic_missing_image)
                        fallback(R.drawable.ic_missing_image)
                        error(R.drawable.ic_missing_image)
                    }
                    tvId.text = oompa.id.toString()
                    tvAge.text = oompa.age.toString()
                    ivGender.setImageResource(if (oompa.gender == "F") R.drawable.ic_icon_female else R.drawable.ic_icon_male)
                    tvGender.text = oompa.gender
                    tvHeight.text = "${oompa.height}cm"
                    tvProfession.text = oompa.profession
                    tvCountry.text = oompa.country
                    tvEmail.text = oompa.email
                    tvFavColor.text = oompa.favorite?.color?.capitalize(java.util.Locale.getDefault()) ?: "Not defined"
                    tvFavFood.text = oompa.favorite?.food?.capitalize(java.util.Locale.getDefault()) ?: "Not defined"
                    tvFavRandomString.text = oompa.favorite?.random_string ?: "Not defined"
                    tvFavSong.text = oompa.favorite?.song ?: "Not defined"

                    var isFavRandomStringClicked = false
                    tvFavRandomString.setOnClickListener {
                        if (isFavRandomStringClicked) {
                            (it as TextView).maxLines = 3
                            isFavRandomStringClicked = false
                        } else {
                            (it as TextView).maxLines = Integer.MAX_VALUE
                            isFavRandomStringClicked = true
                        }
                    }

                    var isFavSongStringClicked = false
                    tvFavSong.setOnClickListener {
                        if (isFavSongStringClicked) {
                            (it as TextView).maxLines = 3
                            isFavSongStringClicked = false
                        } else {
                            (it as TextView).maxLines = Integer.MAX_VALUE
                            isFavSongStringClicked = true
                        }
                    }
                }
            }
        }
    }

    private fun fetchOompaDetails() {
        lifecycleScope.launch {
            viewModel.fetchOompaDetails().collectLatest {
                updateInfo(it)
            }
        }
    }

    private fun updateInfo(oompaDetails: OompaLoompa) {
        with(viewDataBinding) {
            tvQuote.text = oompaDetails.quota
            tvQuote.visibility = View.VISIBLE
            var isQuoteStringClicked = false
            tvQuote.setOnClickListener {
                if (isQuoteStringClicked) {
                    (it as TextView).maxLines = 3
                    isQuoteStringClicked = false
                } else {
                    (it as TextView).maxLines = Integer.MAX_VALUE
                    isQuoteStringClicked = true
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvDescription.text = Html.fromHtml(oompaDetails.description, Html.FROM_HTML_MODE_COMPACT);
            } else {
                tvDescription.text = Html.fromHtml(oompaDetails.description);
            }
            cvDescription.visibility = View.VISIBLE
            var isDescriptionStringClicked = false
            tvDescription.setOnClickListener {
                if (isDescriptionStringClicked) {
                    (it as TextView).maxLines = 3
                    isDescriptionStringClicked = false
                } else {
                    (it as TextView).maxLines = Integer.MAX_VALUE
                    isDescriptionStringClicked = true
                }
            }
        }
    }

}
