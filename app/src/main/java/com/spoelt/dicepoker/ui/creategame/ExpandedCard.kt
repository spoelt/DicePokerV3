package com.spoelt.dicepoker.ui.creategame

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.ui.components.HorizontalSpacer
import com.spoelt.dicepoker.ui.theme.DicePokerTheme
import com.spoelt.dicepoker.ui.theme.ExpandedCardShape

@Composable
fun ExpandedCard(
    modifier: Modifier,
    selectedValue: Float,
    onSelectedValueChanged: (Float) -> Unit,
    icon: ImageVector,
    selectionTextResId: Int,
    valueRange: ClosedFloatingPointRange<Float>,
    stepSize: Int,
    sliderEnabled: Boolean
) {
    Card(
        modifier = modifier,
        shape = ExpandedCardShape,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.border_width),
            color = MaterialTheme.colors.primary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium)),
                    imageVector = icon,
                    contentDescription = stringResource(id = R.string.content_description_columns_icon)
                )
                HorizontalSpacer(width = 12.dp)
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = selectionTextResId, selectedValue.toInt())
                )
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium)),
                    imageVector = Icons.Default.ExpandLess,
                    contentDescription = stringResource(id = R.string.content_description_expand_more)
                )
            }
            Slider(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_extra_large),
                        end = dimensionResource(id = R.dimen.padding_extra_large)
                    )
                    .fillMaxWidth(),
                value = selectedValue,
                onValueChange = { onSelectedValueChanged(it) },
                valueRange = valueRange,
                steps = stepSize,
                enabled = sliderEnabled
            )
        }
    }
}

@Preview(
    name = "Night Mode - Initial State",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode - Initial State",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun ExpandedCardInitialStatePreview() {
    DicePokerTheme {
        ExpandedCard(
            modifier = Modifier,
            selectedValue = 1f,
            onSelectedValueChanged = {},
            icon = Icons.Default.ViewWeek,
            selectionTextResId = R.string.columns_selected,
            valueRange = 1f..3f,
            stepSize = 1,
            sliderEnabled = true
        )
    }
}

@Preview(
    name = "Night Mode - Value Selected",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode - Value Selected",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun ExpandedCardValueSelectedPreview() {
    DicePokerTheme {
        ExpandedCard(
            modifier = Modifier,
            selectedValue = 2f,
            onSelectedValueChanged = {},
            icon = Icons.Default.ViewWeek,
            selectionTextResId = R.string.columns_selected,
            valueRange = 1f..3f,
            stepSize = 1,
            sliderEnabled = true
        )
    }
}
