package app.sylven.oompaloompas.view.oompas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.sylven.oompaloompas.databinding.FragmentOompasBinding
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import app.sylven.oompaloompas.viewModel.OompasViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class OompasFragment : Fragment(), OompasAdapter.OompaAdapterListener {

    private lateinit var viewDataBinding: FragmentOompasBinding
    private val oompasViewModel: OompasViewModel by viewModel()
    private lateinit var oompaLoompasAdapter: OompasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentOompasBinding.inflate(inflater, container, false)

        oompaLoompasAdapter = OompasAdapter(this)

        setupViews()
        fetchOompas()

        return viewDataBinding.root
    }

    private fun fetchOompas() {
        lifecycleScope.launch {
            oompasViewModel.fetchOompas().collectLatest { pagingData ->
                oompaLoompasAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupViews() {
        viewDataBinding.rvOompas.adapter = oompaLoompasAdapter
        viewDataBinding.rvOompas.adapter = oompaLoompasAdapter.withLoadStateHeaderAndFooter(
            header = OompasLoadingAdapter { oompaLoompasAdapter.retry() },
            footer = OompasLoadingAdapter { oompaLoompasAdapter.retry() }
        )
    }

    override fun onOompaSelected(oompa: OompaLoompaPageItem) {
        findNavController().navigate(OompasFragmentDirections
            .actionOompasFragmentToOompaDetailFragment(
                oompa.first_name+" "+oompa.last_name, oompa))
    }

}
