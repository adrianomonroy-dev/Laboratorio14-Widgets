package com.tecsup.musichub

import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
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

class MusicHubWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MusicHubCustomWidget()
}

class MusicHubCustomWidget : GlanceAppWidget() {
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
            Box(
                modifier = GlanceModifier
                    .size(52.dp)
                    .background(Color(0xFF35264F))
                    .cornerRadius(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "♫",
                    style = TextStyle(color = ColorProvider(Color(0xFFB69CFF)), fontSize = 25.sp),
                )
            }
            Column(modifier = GlanceModifier.padding(start = 12.dp)) {
                Text(
                    text = "Midnight City",
                    style = TextStyle(color = ColorProvider(Color.White), fontSize = 18.sp),
                )
                Spacer(modifier = GlanceModifier.height(4.dp))
                Text(
                    text = "M83 · Hurry Up, We're Dreaming",
                    style = TextStyle(color = ColorProvider(Color(0xFFB7B3C5)), fontSize = 12.sp),
                )
            }
        }
        Spacer(modifier = GlanceModifier.height(12.dp))
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
            style = TextStyle(color = ColorProvider(Color(0xFF17131F)), fontSize = 13.sp),
        )
    }
}
