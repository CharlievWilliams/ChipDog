package com.cwilliams.chipdog.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.*
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.Column
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.cwilliams.chipdog.R

class ChipWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Column(GlanceModifier.height(150.dp).background(Color.White).padding(20.dp)) {
            Text(text = LocalContext.current.getString(R.string.subscription))
            Button(
                text = LocalContext.current.getString(R.string.cancel),
                onClick = actionRunCallback<LogActionCallback>()
            )
        }
    }
}

class LogActionCallback : ActionCallback {
    override suspend fun onRun(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        // log("Item with id $glanceId and params $parameters clicked.")
    }
}