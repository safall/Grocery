package com.whitecatlabs.grocery.main.ui.items

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
import dagger.hilt.android.lifecycle.withCreationCallback

@AndroidEntryPoint
class ItemsFragment : Fragment() {

    private val viewModel by viewModels<ItemsViewModel>(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<ItemsViewModel.Factory> { factory ->
                factory.create(id = requireArguments().getString(KEY_ITEM_ID).orEmpty())
            }
        }
    )

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
            ItemsPage(
                title = requireArguments().getString(KEY_ITEM_TITLE) ?: "",
                viewState = viewState
            ) { event ->
                when (event) {
                    is ItemsContract.Event.BackButtonClickedEvent -> findNavController().popBackStack()
                    is ItemsContract.Event.ItemClickedEvent -> Unit
                    is ItemsContract.Event.ItemCheckedEvent -> viewModel.consumeEvent(event)
                }
            }
        }
    }

    companion object {
        const val KEY_ITEM_ID = "KEY_ITEM_ID"
        const val KEY_ITEM_TITLE = "KEY_ITEM_TITLE"
    }
}
