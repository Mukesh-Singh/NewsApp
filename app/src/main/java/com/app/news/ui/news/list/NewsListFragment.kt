package com.app.news.ui.news.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ComplexColorCompat
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.news.R
import com.app.news.data.model.Article
import com.app.news.databinding.FragmentNewsListBinding
import com.app.news.databinding.RowNewsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewsListFragment : DaggerFragment() {
    private lateinit var binding: FragmentNewsListBinding;
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)
            .get(NewsViewModel::class.java)
    }
    private val adapter= NewsAdapter()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter.itemClickListener = object: NewsAdapter.ItemClickListener {
            override fun onItemClicked(item: Article,  binding: RowNewsBinding) {
                moveToDetails(item, binding)
            }

        }
        binding.swipeRefresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.primary));
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel.newsData.observe(viewLifecycleOwner,{
            adapter.updateList(it)
        })

        viewModel.progress.observe(viewLifecycleOwner,{
            if (it){
                if (!binding.swipeRefresh.isRefreshing) {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
            else{
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
            }
        })

        viewModel.error.observe(viewLifecycleOwner,{
            if (it != null) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        })


    }

    private fun moveToDetails(article: Article, binding: RowNewsBinding) {
        viewModel.articleForDetails.value = article
        val extras = FragmentNavigatorExtras(
            binding.title to  binding.title.transitionName,
        )
        val map = HashMap<String, String>()
        map["title"] = binding.title.transitionName
        val transitionArg = SharedTransitionArg()
        transitionArg.map = map
        val directions = NewsListFragmentDirections.listToDetails(transitionArg)
        findNavController().navigate(directions, extras)
    }

}