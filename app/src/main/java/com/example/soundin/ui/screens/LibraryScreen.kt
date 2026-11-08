package com.example.soundin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soundin.ui.componets.PlaylistBottomSheet
import com.example.soundin.ui.componets.PlaylistCard
import com.example.soundin.ui.models.Playlist
import com.example.soundin.viewmodels.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = viewModel(),
    onNavigateToPlaylistDetail: (Playlist) -> Unit
) {
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val filteredPlaylists = viewModel.filteredPlaylists.collectAsStateWithLifecycle()
    var selectedPlaylist by remember { mutableStateOf<Playlist?>(null) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Library",
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                PrimaryTabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ) {
                    val tabs = listOf("All", "Favorites")
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { viewModel.onTabSelected(index) },
                            text = { Text(title) },
                            selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    }
                } // end of primary tab row
            }// end of column for top bar
        }// end of top bar
    ) { paddingValues ->
        if (filteredPlaylists.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                ) {
                Text(
                    text = if (selectedTab == 1)
                        "No favorite playlists yet"
                    else "No playlists ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    filteredPlaylists.value,
                    key = { it.id }
                ) { playlist ->
                    PlaylistCard(
                        playlist = playlist,
                        onClick = {
                            onNavigateToPlaylistDetail(playlist)
                        },
                        onLongClick = { selectedPlaylist = playlist }
                    )
                }
            }
        }
    }// end of scaffold
    selectedPlaylist?.let { playlist ->
        PlaylistBottomSheet(
            playlist = playlist,
            onDismiss = { selectedPlaylist = null },
            onToggleFavorite = { viewModel.toggleFavorite(it) },
            onDelete = { viewModel.deletePlaylist(it) }
        )

    }

}
