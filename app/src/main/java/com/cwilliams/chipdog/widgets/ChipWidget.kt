package com.cwilliams.chipdog.widgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.lazy.GridCells.Companion.Adaptive
import androidx.glance.appwidget.lazy.LazyVerticalGrid
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight.Companion.Bold
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.cwilliams.chipdog.MainActivity
import com.cwilliams.chipdog.R

@ExperimentalMaterial3WindowSizeClassApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class ChipWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Column(GlanceModifier.background(Color.White).fillMaxSize()) {
            Text(
                text = LocalContext.current.getString(R.string.list_of_breeds),
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 22.sp,
                    color = ColorProvider(Color.Black)
                ),
                modifier = GlanceModifier.padding(20.dp)
            )
            LazyVerticalGrid(
                gridCells = Adaptive,
                modifier = GlanceModifier.padding(horizontal = 20.dp)
            ) {
                items(12) { item ->
                    Button(
                        text = item.toString(),
                        onClick = actionStartActivity<MainActivity>(),
                        modifier = GlanceModifier.fillMaxWidth().height(100.dp)
                    )
                }
            }
        }
    }
}