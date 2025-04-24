package com.fcerio.core.common.compose.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextComposable(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = MaterialTheme.colorScheme.primary,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier.wrapContentSize(),
        color = textColor,
        text = title,
        style = style,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleBarPreview() {
    TextComposable(title = "Title")
}
