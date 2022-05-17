package com.cwilliams.chipdog.widgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

@ExperimentalMaterial3WindowSizeClassApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
class ChipWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = ChipWidget()
}