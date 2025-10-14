package com.gauravbajaj.interviewready.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gauravbajaj.interviewready.data.model.DataModel
import com.gauravbajaj.interviewready.ui.viewmodel.HomeViewModel
import com.gauravbajaj.interviewready.ui.base.UIState
import com.gauravbajaj.interviewready.ui.components.ErrorMessage
import com.gauravbajaj.interviewready.ui.components.LoadingIndicator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (DataModel) -> Unit = {}
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState) {
        if (uiState is UIState.Initial) {
            viewModel.loadUsers()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Title") }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                UIState.Initial -> {
                    Text(
                        text = "No content yet",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                UIState.Loading -> {
                    LoadingIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is UIState.Success -> {
                    val successState = uiState as UIState.Success

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(successState.data) { user ->
                            Text(
                                text = user.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onItemClick(user) }
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                is UIState.Error -> {
                    val successState = uiState as UIState.Error
                    ErrorMessage(
                        message = successState.message,
                        onRetry = {viewModel.loadUsers()}
                    )
                }
            }
        }
    }
}
