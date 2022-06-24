package com.spoelt.dicepoker.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.ui.theme.ButtonShape
import com.spoelt.dicepoker.ui.theme.DicePokerTheme

/**
 * This is a custom [Button] that provides the shape and styling expected in the DicePoker
 * application.
 *
 * @param[modifier] An optional [Modifier] to configure this component.
 * @param[text] The text inside the button.
 * @param[onClick] A callback invoked when the user clicks the button.
 * @param[backgroundColor] The color of the button in an enabled state.
 * @param[enabled] True if the button is enabled, false otherwise.
 */
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colors.primary,
    enabled: Boolean
) {
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor
    )
    Button(
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
        colors = buttonColors,
        shape = ButtonShape,
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text.toUpperCase(Locale.current)
        )
    }
}

@Preview(
    name = "Night Mode - Enabled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "enabled"
)
@Preview(
    name = "Day Mode - Enabled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "enabled"
)
@Composable
@Suppress("UnusedPrivateMember")
private fun EnabledPrimaryButtonPreview() {
    DicePokerTheme {
        PrimaryButton(
            text = "Primary Button",
            onClick = {},
            enabled = true
        )
    }
}

@Preview(
    name = "Night Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "disabled"
)
@Preview(
    name = "Day Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "disabled"
)
@Composable
@Suppress("UnusedPrivateMember")
private fun DisabledPrimaryButtonPreview() {
    DicePokerTheme {
        PrimaryButton(
            text = "Primary Button",
            onClick = {},
            enabled = false
        )
    }
}
