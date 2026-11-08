package com.example.soundin.ui.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.soundin.ui.models.Playlist
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistBottomSheet(
    playlist: Playlist,
    onDismiss: () -> Unit,
    onToggleFavorite: (Playlist) -> Unit,
    onDelete: (Playlist) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            // Playlist name header
            Text(
                text = playlist.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            HorizontalDivider()
            //Toggle Favorite option
            ListItem(
                headlineContent = {
                    Text(
                        text = if (playlist.isFavorite) "Remove from Favorites" else "Add to Favorites",
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = if (playlist.isFavorite) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = if (playlist.isFavorite) "Remove from Favorites"
                        else "Add to Favorites",
                        tint = if (playlist.isFavorite) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.onSurface
                    )
                },
                modifier = Modifier.clickable {
                    onToggleFavorite(playlist)
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { onDismiss() }
                }
            )
            ListItem(
                headlineContent = {
                    Text("Delete Playlist",
                        color = MaterialTheme.colorScheme.error
                    )
                },
                // Challenge complete the icon delete and modifier click
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Playlist",
                        tint = MaterialTheme.colorScheme.error
                    )
                },
                modifier = Modifier.clickable {
                    onDelete(playlist)
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { onDismiss() }
                }
            )
        }
    }
}