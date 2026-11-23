package com.tecsup.musichub

import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import androidx.glance.Button
import androidx.glance.ButtonDefaults
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SimpleWidget : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = SimpleWidgetContent()
}

class SimpleWidgetContent : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val openMusicHub = Intent(context, MainActivity::class.java)
        val openLibrary = Intent(context, BibliotecaActivity::class.java)
        val openFavorites = Intent(context, FavoritosActivity::class.java)
        val homeLabel = context.getString(R.string.simple_widget_open)
        val libraryLabel = context.getString(R.string.simple_widget_library)
        val favoritesLabel = context.getString(R.string.simple_widget_favorites)
        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("MusicHub", style = TextStyle(fontSize = 20.sp))
                Row(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        text = homeLabel,
                        onClick = actionStartActivity(openMusicHub),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorProvider(Color(0xFFB69CFF)),
                            contentColor = ColorProvider(Color(0xFF101019)),
                        ),
                    )
                    Spacer(modifier = GlanceModifier.width(8.dp))
                    Button(
                        text = libraryLabel,
                        onClick = actionStartActivity(openLibrary),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorProvider(Color(0xFFB69CFF)),
                            contentColor = ColorProvider(Color(0xFF101019)),
                        ),
                    )
                }
                Button(
                    text = favoritesLabel,
                    onClick = actionStartActivity(openFavorites),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorProvider(Color(0xFFB69CFF)),
                        contentColor = ColorProvider(Color(0xFF101019)),
                    ),
                )
            }
        }
    }
}
