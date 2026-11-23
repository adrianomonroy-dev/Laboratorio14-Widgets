package com.tecsup.musichub

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class MusicHubDestination {
    Home,
    Library,
    Favorites,
}

@Suppress("DEPRECATION")
fun ComponentActivity.enableMusicHubEdgeToEdge() {
    enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
        navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
    )
    window.setBackgroundDrawable(ColorDrawable(0xFF101019.toInt()))
    window.statusBarColor = android.graphics.Color.TRANSPARENT
    window.navigationBarColor = android.graphics.Color.TRANSPARENT
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        window.isStatusBarContrastEnforced = false
        window.isNavigationBarContrastEnforced = false
    }
    WindowCompat.getInsetsController(window, window.decorView).apply {
        isAppearanceLightStatusBars = false
        isAppearanceLightNavigationBars = false
    }
}

private val Ink = Color(0xFF101019)
private val Panel = Color(0xFF1B1A27)
private val Accent = Color(0xFFB69CFF)
private val Muted = Color(0xFFB7B3C5)

fun ComponentActivity.navigateTo(destination: MusicHubDestination) {
    val target = when (destination) {
        MusicHubDestination.Home -> MainActivity::class.java
        MusicHubDestination.Library -> BibliotecaActivity::class.java
        MusicHubDestination.Favorites -> FavoritosActivity::class.java
    }
    startActivity(Intent(this, target))
}

@Composable
fun MusicHubThemeContent(
    destination: MusicHubDestination,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onNavigate: (MusicHubDestination) -> Unit,
) {
    MusicHubScreen(
        destination = destination,
        modifier = modifier.padding(contentPadding),
        onNavigate = onNavigate,
    )
}

@Composable
fun MusicHubScreen(
    destination: MusicHubDestination,
    modifier: Modifier = Modifier,
    onNavigate: (MusicHubDestination) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Ink)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        BrandHeader()
        when (destination) {
            MusicHubDestination.Home -> HomeContent(onNavigate)
            MusicHubDestination.Library -> LibraryContent()
            MusicHubDestination.Favorites -> FavoritesContent()
        }
        DestinationBar(current = destination, onNavigate = onNavigate)
    }
}

@Composable
private fun BrandHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(color = Accent, shape = CircleShape, modifier = Modifier.size(42.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text("♫", color = Ink, fontSize = 23.sp, fontWeight = FontWeight.Bold)
            }
        }
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text("MusicHub", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("TU ESPACIO MUSICAL", color = Muted, fontSize = 10.sp, letterSpacing = 1.4.sp)
        }
    }
}

@Composable
private fun HomeContent(onNavigate: (MusicHubDestination) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF30264D)),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("BUENAS VIBRAS, SIEMPRE", color = Accent, fontSize = 11.sp, letterSpacing = 1.2.sp)
                Text(
                    "Tu música,\ntu momento.",
                    color = Color.White,
                    fontSize = 32.sp,
                    lineHeight = 38.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text("Encuentra tus canciones y vuelve a lo que más te gusta.", color = Muted)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(onClick = { onNavigate(MusicHubDestination.Library) }) {
                        Text("Biblioteca")
                    }
                    TextButton(onClick = { onNavigate(MusicHubDestination.Favorites) }) {
                        Text("Favoritos", color = Color.White)
                    }
                }
            }
        }

        SectionHeading("Para escuchar ahora", "REPRODUCIENDO EN TU HUB")
        TrackCard(title = "Midnight City", artist = "M83", detail = "Canción · 4:03", symbol = "◖")
    }
}

@Composable
private fun LibraryContent() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        PageHeading("Tu Biblioteca", "Todo lo que quieres escuchar, en un solo lugar.")
        SectionHeading("Escuchado recientemente", "3 CANCIONES")
        TrackCard("Midnight City", "M83", "Canción · 4:03", "◖")
        TrackCard("Levitating", "Dua Lipa", "Canción · 3:23", "♫")
        TrackCard("Ojitos Lindos", "Bad Bunny, Bomba Estéreo", "Canción · 4:18", "♪")
    }
}

@Composable
private fun FavoritesContent() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        PageHeading("Tus Favoritos", "Las canciones que siempre quieres tener cerca.")
        SectionHeading("Canciones guardadas", "3 CANCIONES")
        TrackCard("Levitating", "Dua Lipa", "Pop · 3:23", "♥")
        TrackCard("Ojitos Lindos", "Bad Bunny, Bomba Estéreo", "Urbano · 4:18", "♥")
        TrackCard("Golden", "Harry Styles", "Pop · 3:28", "♥")
    }
}

@Composable
private fun PageHeading(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text(subtitle, color = Muted)
    }
}

@Composable
private fun SectionHeading(title: String, eyebrow: String) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(eyebrow, color = Accent, fontSize = 10.sp, letterSpacing = 1.3.sp)
        Text(title, color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun TrackCard(title: String, artist: String, detail: String, symbol: String) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Panel),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(color = Color(0xFF433668), shape = RoundedCornerShape(14.dp), modifier = Modifier.size(54.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Text(symbol, color = Accent, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
            Column(modifier = Modifier.weight(1f).padding(start = 14.dp)) {
                Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
                Text(artist, color = Muted, fontSize = 13.sp)
            }
            Text(detail.substringAfter("·").trim(), color = Muted, fontSize = 12.sp)
        }
    }
}

@Composable
private fun DestinationBar(current: MusicHubDestination, onNavigate: (MusicHubDestination) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        DestinationButton("Inicio", MusicHubDestination.Home, current, onNavigate, Modifier.weight(1f))
        DestinationButton("Biblioteca", MusicHubDestination.Library, current, onNavigate, Modifier.weight(1f))
        DestinationButton("Favoritos", MusicHubDestination.Favorites, current, onNavigate, Modifier.weight(1f))
    }
}

@Composable
private fun DestinationButton(
    label: String,
    destination: MusicHubDestination,
    current: MusicHubDestination,
    onNavigate: (MusicHubDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { onNavigate(destination) },
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (current == destination) Accent else Panel,
            contentColor = if (current == destination) Ink else Color.White,
        ),
    ) {
        Text(label, fontSize = 11.sp, maxLines = 1)
    }
}
