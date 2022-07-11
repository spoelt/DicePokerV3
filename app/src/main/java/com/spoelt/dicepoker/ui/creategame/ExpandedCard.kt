package com.spoelt.dicepoker.ui.creategame

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.ui.components.HorizontalSpacer
import com.spoelt.dicepoker.ui.theme.DicePokerTheme
import com.spoelt.dicepoker.ui.theme.ExpandedCardShape
import kotlin.math.roundToInt

@Composable
fun ExpandedCard(
    modifier: Modifier,
    selectedValue: Int,
    onSelectedValueChanged: (Int) -> Unit,
    icon: ImageVector,
    selectionTextResId: Int,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    sliderEnabled: Boolean,
    onCardClicked: () -> Unit
) {
    var sliderPosition by remember(selectedValue) {
        mutableStateOf(selectedValue.toFloat())
    }

    Card(
        modifier = modifier.clickable { onCardClicked() },
        shape = ExpandedCardShape,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.border_width),
            color = MaterialTheme.colors.primary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_12))
                .fillMaxWidth()
        ) {
            HeaderWithIcons(
                icon = icon,
                selectionTextResId = selectionTextResId,
                sliderPosition = sliderPosition
            )
            Slider(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_48),
                        end = dimensionResource(id = R.dimen.padding_48)
                    )
                    .fillMaxWidth(),
                value = sliderPosition,
                onValueChange = {
                    // slider position is first rounded to nearest integer value in order to
                    // prevent the slider thumb from jumping once the number conversion is complete
                    sliderPosition = it.roundToInt().toFloat()
                },
                onValueChangeFinished = {
                    onSelectedValueChanged(sliderPosition.toInt())
                },
                valueRange = valueRange,
                steps = steps,
                enabled = sliderEnabled
            )
        }
    }
}

@Composable
private fun HeaderWithIcons(
    icon: ImageVector,
    selectionTextResId: Int,
    sliderPosition: Float
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_24)),
            imageVector = icon,
            contentDescription = stringResource(id = R.string.content_description_columns_icon)
        )
        HorizontalSpacer(width = dimensionResource(id = R.dimen.spacer_12))
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = selectionTextResId, sliderPosition.toInt())
        )
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_24)),
            imageVector = Icons.Default.ExpandLess,
            contentDescription = stringResource(id = R.string.content_description_expand_more)
        )
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
            selectedValue = 1,
            onSelectedValueChanged = {},
            icon = Icons.Default.ViewWeek,
            selectionTextResId = R.string.columns_selected,
            valueRange = 1f..3f,
            steps = 1,
            sliderEnabled = true,
            onCardClicked = {}
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
            selectedValue = 2,
            onSelectedValueChanged = {},
            icon = Icons.Default.ViewWeek,
            selectionTextResId = R.string.columns_selected,
            valueRange = 1f..3f,
            steps = 1,
            sliderEnabled = true,
            onCardClicked = {}
        )
    }
}
