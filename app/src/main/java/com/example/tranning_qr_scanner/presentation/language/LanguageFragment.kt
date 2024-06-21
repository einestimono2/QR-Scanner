package com.example.tranning_qr_scanner.presentation.language

import androidx.recyclerview.widget.GridLayoutManager
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.Constants.CURRENT_LANGUAGE_KEY
import com.example.tranning_qr_scanner.core.utils.extension.dp
import com.example.tranning_qr_scanner.core.utils.RecyclerViewItemSpacing
import com.example.tranning_qr_scanner.core.utils.helper.AppCache
import com.example.tranning_qr_scanner.data.model.LanguageModel
import com.example.tranning_qr_scanner.databinding.LanguageFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LanguageFragment : BaseFragment<LanguageFragmentBinding, LanguageViewModel>() {
    @Inject
    lateinit var cacheHelper: AppCache

    override val viewModelClass = LanguageViewModel::class.java

    override fun inflateLayout() = LanguageFragmentBinding.inflate(layoutInflater)

    override fun bind() {
        /* */
        viewModel.setDefaultLanguage(LanguageModel.LANGUAGES.first().code)

        /* Recycler View */
        val rv = binding.languageFragRv
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        rv.addItemDecoration(RecyclerViewItemSpacing(12.dp))

        rv.adapter = LanguageAdapter(languages = LanguageModel.LANGUAGES, requireContext(), viewModel)

        /* Nav button */
        binding.languageFragToolbarBtn.setOnClickListener {
            cacheHelper.setValue(CURRENT_LANGUAGE_KEY, viewModel.selectedLanguage)
            navigate(R.id.action_languageFragment_to_introFragment)
        }
    }
}