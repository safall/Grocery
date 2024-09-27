package com.whitecatlabs.grocery.main.ui.addcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryFragment : Fragment() {
    private val viewModel: AddCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            consumeWindowInsets = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as ComposeView).setContent {
            val viewState = viewModel.uiState.collectAsStateWithLifecycle().value
            AddCategoryPage(viewState) { event ->
                when (event) {
                    is AddCategoryContract.Event.BackButtonClickedEvent -> findNavController().popBackStack()
                    is AddCategoryContract.Event.ItemCheckedEvent -> viewModel.consumeEvent(event)
                }
            }
        }
    }
}
