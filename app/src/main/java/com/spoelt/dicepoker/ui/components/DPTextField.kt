package com.spoelt.dicepoker.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.ui.theme.DicePokerTheme
import com.spoelt.dicepoker.ui.theme.TextFieldShape

/**
 * This is a custom implementation of an [OutlinedTextField] to ensure that it has the DicePoker
 * branding and styling that we expect.
 *
 * @param[modifier] An optional [Modifier].
 * @param[text] The current text inside the input.
 * @param[onTextChanged] A callback invoked whenever the user modifies the text inside this input.
 * @param[labelText] The label that shows above the input when focused.
 */
@Composable
fun DPTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String,
    errorMessage: String?
) {
    Column {
        OutlinedTextField(
            modifier = modifier
                .heightIn(dimensionResource(id = R.dimen.text_field_height))
                .fillMaxWidth(),
            value = text,
            onValueChange = onTextChanged,
            label = { Text(text = labelText) },
            shape = TextFieldShape,
            isError = errorMessage != null
        )
        errorMessage?.let { message ->
            Text(
                modifier = Modifier.padding(top = 4.dp, start = 16.dp),
                text = message,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Preview(
    name = "Night Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    group = "empty"
)
@Preview(
    name = "Day Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    group = "empty"
)
@Composable
@Suppress("UnusedPrivateMember")
private fun EmptyDPTextFieldPreview() {
    DicePokerTheme {
        DPTextField(
            text = "",
            onTextChanged = {},
            labelText = "Label",
            errorMessage = null
        )
    }
}

@Preview(
    name = "Night Mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    group = "filled"
)
@Preview(
    name = "Day Mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    group = "filled"
)
@Composable
@Suppress("UnusedPrivateMember")
private fun FilledDPTextFieldPreview() {
    DicePokerTheme {
        DPTextField(
            text = "Text Field",
            onTextChanged = {},
            labelText = "Label",
            errorMessage = null
        )
    }
}

@Preview(
    name = "Night Mode - Error",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    group = "error"
)
@Preview(
    name = "Day Mode - Error",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    group = "error"
)
@Composable
@Suppress("UnusedPrivateMember")
private fun DPTextFieldWithErrorPreview() {
    DicePokerTheme {
        DPTextField(
            text = "Text Field",
            onTextChanged = {},
            labelText = "Label",
            errorMessage = "Unknown error"
        )
    }
}
