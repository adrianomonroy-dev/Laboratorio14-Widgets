package com.tecsup.musichub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import com.tecsup.musichub.ui.theme.MusicHubTheme

class FavoritosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableMusicHubEdgeToEdge()
        setContent {
            MusicHubTheme(darkTheme = true, dynamicColor = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF101019),
                ) { innerPadding ->
                    MusicHubThemeContent(
                        destination = MusicHubDestination.Favorites,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = innerPadding,
                        onNavigate = ::navigateTo,
                    )
                }
            }
        }
    }
}
