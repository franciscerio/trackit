package com.fcerio.core.common.compose.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fcerio.core.common.compose.AppTheme
import com.fcerio.core.common.compose.R
import com.fcerio.core.common.compose.TrackitTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComposable(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    enabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    backgroundColor: Color = AppTheme.colors.neutralsBackground,
    activeBackgroundColor: Color = AppTheme.colors.primaryShade3,
    activeStrokeColor: Color = AppTheme.colors.primaryDefault,
    defaultStrokeColor: Color = AppTheme.colors.neutralsStroke,
    focusedTextColor: Color = AppTheme.colors.textPrimary,
    unfocusedTextColor: Color = AppTheme.colors.textTertiary,
    unfocusedPlaceholderColor: Color = AppTheme.colors.textTertiary,
    focusedPlaceholderColor: Color = AppTheme.colors.textPrimary,
    disabledContainerColor: Color = AppTheme.colors.neutralsBackground,
    activeColor: Color = AppTheme.colors.primaryDefault,
    focusedLeadingIconColor: Color = AppTheme.colors.textPrimary,
    unfocusedLeadingIconColor: Color = AppTheme.colors.textPrimary,
    searchIcon: @Composable (() -> Unit) = {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(id = R.string.search)
        )
    },
    clearButton: @Composable (() -> Unit)? = {
        IconButton(
            onClick = { onValueChanged("") }
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_clear),
                contentDescription = stringResource(id = R.string.clear)
            )
        }
    },
    placeholder: @Composable (() -> Unit)? = {
        Text(text = stringResource(id = R.string.search))
    },
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val isFocused by interactionSource.collectIsFocusedAsState()

    val resolvedBackgroundColor by animateColorAsState(
        targetValue = if (isFocused) activeStrokeColor else defaultStrokeColor,
        label = "Background Color"
    )

    BasicTextField(
        modifier = modifier
            .border(
                BorderStroke(1.dp, resolvedBackgroundColor),
                shape = RoundedCornerShape(12.dp)
            ),
        value = value,
        textStyle = textStyle,
        enabled = enabled,
        onValueChange = onValueChanged,
        interactionSource = interactionSource,
        maxLines = 1,
        singleLine = true,
        cursorBrush = SolidColor(activeColor),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ),
        decorationBox = { innerTextField ->

            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                leadingIcon = searchIcon,
                trailingIcon = clearButton.takeIf { value.isNotEmpty() },
                placeholder = placeholder?.let {
                    {
                        ProvideTextStyle(
                            value = textStyle,
                            content = it
                        )
                    }
                },
                contentPadding = PaddingValues(0.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = activeBackgroundColor,
                    unfocusedContainerColor = backgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = focusedTextColor,
                    unfocusedTextColor = unfocusedTextColor,
                    unfocusedPlaceholderColor = unfocusedPlaceholderColor,
                    focusedPlaceholderColor = focusedPlaceholderColor,
                    disabledContainerColor = disabledContainerColor,
                    disabledIndicatorColor = Color.Transparent,
                    focusedLeadingIconColor = focusedLeadingIconColor,
                    unfocusedLeadingIconColor = unfocusedLeadingIconColor

                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    TrackitTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val (testValue, setTestValue) = remember { mutableStateOf("") }
            SearchBarComposable(
                modifier = Modifier.fillMaxWidth(),
                value = testValue,
                onValueChanged = setTestValue
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Default")
            SearchBarComposable(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChanged = {}
            )
        }
    }
}

