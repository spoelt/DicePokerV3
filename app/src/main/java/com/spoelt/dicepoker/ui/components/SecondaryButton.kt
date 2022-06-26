package com.spoelt.dicepoker.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.ui.theme.ButtonShape
import com.spoelt.dicepoker.ui.theme.DicePokerTheme

/**
 * This is a custom [TextButton] that provides the shape and styling expected in the DicePoker
 * application.
 *
 * @param[modifier] An optional [Modifier] to configure this component.
 * @param[text] The text inside the button.
 * @param[onClick] A callback invoked when the user clicks the button.
 * @param[enabled] True if the button is enabled, false otherwise.
 */
@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean
) {
    TextButton(
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
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
private fun EnabledSecondaryButtonPreview() {
    DicePokerTheme {
        SecondaryButton(
            text = "Secondary Button",
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
private fun DisabledSecondaryButtonPreview() {
    DicePokerTheme {
        SecondaryButton(
            text = "Secondary Button",
            onClick = {},
            enabled = false
        )
    }
}
