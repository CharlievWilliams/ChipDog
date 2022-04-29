package com.cwilliams.chipdog.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cwilliams.chipdog.ui.theme.typography
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@ExperimentalMaterial3Api
@Composable
fun InitialCard(initial: String, isRefreshing: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colors.secondary)
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = initial.uppercase(),
                style = typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.placeholder(
                    visible = isRefreshing,
                    highlight = PlaceholderHighlight.shimmer()
                ),
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}