package com.cwilliams.chipdog.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
fun BreedCard(isRefreshing: Boolean, breed: String, navigateToNextScreen: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clickable {
                navigateToNextScreen(breed)
            },
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .height(80.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = breed.replaceFirstChar { it.titlecase() },
                style = typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.placeholder(
                    visible = isRefreshing,
                    highlight = PlaceholderHighlight.shimmer()
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .placeholder(
                        visible = isRefreshing,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )
        }
    }
}