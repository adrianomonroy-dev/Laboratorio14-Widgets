package com.tecsup.musichub

import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.background
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.DpSize

private val musicHubWidgetSizes = setOf(
    DpSize(180.dp, 160.dp),
    DpSize(240.dp, 160.dp),
    DpSize(240.dp, 220.dp),
)

class MusicHubWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MusicHubCustomWidget()
}

class MusicHubCustomWidget : GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Responsive(musicHubWidgetSizes)

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val openLibrary = Intent(context, BibliotecaActivity::class.java)
        val openFavorites = Intent(context, FavoritosActivity::class.java)
        provideContent {
            MusicHubWidgetContent(
                libraryAction = actionStartActivity(openLibrary),
                favoritesAction = actionStartActivity(openFavorites),
            )
        }
    }
}

@androidx.compose.runtime.Composable
private fun MusicHubWidgetContent(
    libraryAction: androidx.glance.action.Action,
    favoritesAction: androidx.glance.action.Action,
) {
    val size = LocalSize.current
    val isWide = size.width >= 220.dp
    val isTall = size.height >= 210.dp

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFF17131F))
            .cornerRadius(24.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "MUSICHUB",
                style = TextStyle(color = ColorProvider(Color(0xFFB69CFF)), fontSize = 12.sp),
            )
            Spacer(modifier = GlanceModifier.defaultWeight())
            Text(
                text = "SELECCIÓN DEL DÍA",
                style = TextStyle(color = ColorProvider(Color(0xFFB7B3C5)), fontSize = 10.sp),
            )
        }
        Spacer(modifier = GlanceModifier.height(12.dp))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isWide) {
                AlbumArtwork(size = 60.dp)
                TrackDetails(modifier = GlanceModifier.padding(start = 12.dp), showAlbum = true)
            } else {
                AlbumArtwork(size = 44.dp)
                TrackDetails(
                    modifier = GlanceModifier.padding(start = 10.dp).defaultWeight(),
                    showAlbum = false,
                )
            }
        }
        if (isTall) {
            Spacer(modifier = GlanceModifier.height(10.dp))
            Text(
                text = "Una mezcla nocturna para seguir descubriendo.",
                style = TextStyle(color = ColorProvider(Color(0xFFB7B3C5)), fontSize = 12.sp),
            )
        }
        Spacer(modifier = GlanceModifier.height(if (isTall) 14.dp else 10.dp))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ShortcutChip(
                label = "Biblioteca",
                action = libraryAction,
                modifier = GlanceModifier.defaultWeight(),
            )
            Spacer(modifier = GlanceModifier.width(8.dp))
            ShortcutChip(
                label = "Favoritos",
                action = favoritesAction,
                modifier = GlanceModifier.defaultWeight(),
            )
        }
    }
}

@androidx.compose.runtime.Composable
private fun AlbumArtwork(size: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = GlanceModifier
            .size(size)
            .background(Color(0xFF35264F))
            .cornerRadius(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "♫",
            style = TextStyle(color = ColorProvider(Color(0xFFB69CFF)), fontSize = 25.sp),
        )
    }
}

@androidx.compose.runtime.Composable
private fun TrackDetails(modifier: GlanceModifier, showAlbum: Boolean) {
    Column(modifier = modifier) {
        Text(
            text = "Midnight City",
            style = TextStyle(color = ColorProvider(Color.White), fontSize = 18.sp),
        )
        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = if (showAlbum) "M83 · Hurry Up, We're Dreaming" else "M83 · 4:03",
            style = TextStyle(color = ColorProvider(Color(0xFFB7B3C5)), fontSize = 12.sp),
        )
    }
}

@androidx.compose.runtime.Composable
private fun ShortcutChip(
    label: String,
    action: androidx.glance.action.Action,
    modifier: GlanceModifier,
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .background(Color(0xFFB69CFF))
            .cornerRadius(14.dp)
            .clickable(action)
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = TextStyle(color = ColorProvider(Color(0xFF17131F)), fontSize = 11.sp),
        )
    }
}
