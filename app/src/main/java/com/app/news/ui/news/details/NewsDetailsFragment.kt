package com.app.news.ui.news.details

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.app.news.R
import com.app.news.data.model.Article
import com.app.news.databinding.FragmentNewsDetailsBinding
import com.app.news.ui.news.list.NewsViewModel
import com.app.news.ui.news.list.SharedTransitionArg
import com.app.news.utils.formatTo
import com.app.news.utils.toDate
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewsDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)
            .get(NewsViewModel::class.java)
    }
    private lateinit var binding: FragmentNewsDetailsBinding
    private lateinit var article: Article
    private var builder = CustomTabsIntent.Builder()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        binding = FragmentNewsDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: NewsDetailsFragmentArgs by navArgs()
        val sharedArg: SharedTransitionArg =safeArgs.transitionArgument
        binding.title.transitionName= sharedArg.map["title"]
        viewModel.articleForDetails.observe(viewLifecycleOwner,{
            this.article = it
            showData(it)
        })

        binding.readFullStory.setOnClickListener {
            if (this::article.isInitialized) {
                var customTabsIntent: CustomTabsIntent = builder.build();
                customTabsIntent.launchUrl(requireContext(), Uri.parse(article.url))
            }
        }
    }

    private fun showData(article: Article) {
        binding.title.text = article.title
        val date = (article.publishedAt)?.toDate()?.formatTo("dd MMM yyyy")
        val sourceAndTime = "${article.source?.name} on $date "
        binding.sourceAndTime.text = sourceAndTime
        binding.content.text = article.content?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
        Glide.with(binding.imageView)
            .load(article.urlToImage)
            .placeholder(R.drawable.place_holder_image)
            //.apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(binding.imageView)
    }


}