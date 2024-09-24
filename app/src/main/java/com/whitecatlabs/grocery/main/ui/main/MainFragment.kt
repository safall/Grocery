package com.whitecatlabs.grocery.main.ui.main

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
import com.whitecatlabs.grocery.R
import com.whitecatlabs.grocery.main.ui.items.ItemsFragment.Companion.KEY_ITEM_ID
import com.whitecatlabs.grocery.main.ui.items.ItemsFragment.Companion.KEY_ITEM_TITLE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

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
            MainPage(viewState) { event ->
                when (event) {
                    is MainContract.Event.BackButtonClickedEvent -> requireActivity().finish()
                    is MainContract.Event.ItemClickedEvent -> findNavController().navigate(
                        R.id.mainFragmentToItemsFragment,
                        Bundle().apply {
                            putString(KEY_ITEM_ID, event.id)
                            putString(KEY_ITEM_TITLE, event.title)
                        }
                    )
                }

            }
        }
    }
}
