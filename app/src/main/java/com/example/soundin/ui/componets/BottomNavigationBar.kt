package com.example.soundin.ui.componets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.soundin.ui.navigation.SoundinRoutes

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
){
    NavigationBar {
        val items = listOf(
            Triple(SoundinRoutes.LIBRARY, Icons.Default.LibraryMusic,"Library"),
            // challenge add search and profile
            Triple(SoundinRoutes.SEARCH,Icons.Default.Search,"Search"),
            Triple(SoundinRoutes.PROFILE,Icons.Default.Person, "Profile")
        )
        items.forEach { (route,icon,label) ->
            NavigationBarItem(
                selected = currentRoute == route,
                onClick = {onNavigate(route)},
                icon = {Icon(icon, contentDescription = label)},
                label = {Text (label)}
            )
        }
    }
}