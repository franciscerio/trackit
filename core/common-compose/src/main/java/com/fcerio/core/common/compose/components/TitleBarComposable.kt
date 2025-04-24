package com.fcerio.core.common.compose.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TitleBarComposable(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable (RowScope.() -> Unit)? = null,
    style: TextStyle = MaterialTheme.typography.titleMedium,
) {
    Row(
        modifier = modifier.padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f), text = title, style = style
        )
        actions?.invoke(this)
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleBarPreview() {
    TitleBarComposable(title = "Title")
}
