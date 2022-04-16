package com.spoelt.dicepoker.core.ui.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.core.ui.theme.DicePokerTheme

@Composable
fun GameOptionCard(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    selectedValue: Int,
    onSelectedValueChanged: (Int) -> Unit,
    icon: ImageVector,
    initialTextResId: Int,
    selectionTextResId: Int,
    valueRange: ClosedFloatingPointRange<Float>,
    stepSize: Int = 1,
    sliderEnabled: Boolean
) {
    when {
        isExpanded -> ExpandedCard(
            modifier = modifier,
            selectedValue = selectedValue,
            onSelectedValueChanged = onSelectedValueChanged,
            icon = icon,
            selectionTextResId = selectionTextResId,
            valueRange = valueRange,
            stepSize = stepSize,
            sliderEnabled = sliderEnabled
        )
        else -> ExpandableCard(
            modifier = modifier,
            selectedValue = selectedValue,
            icon = icon,
            initialTextResId = initialTextResId,
            selectionTextResId = selectionTextResId
        )
    }
}

@Preview(
    name = "Night Mode - Expendable",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode - Expendable",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun GameOptionCardExpandablePreview() {
    DicePokerTheme {
        GameOptionCard(
            isExpanded = false,
            selectedValue = 1,
            onSelectedValueChanged = {},
            icon = Icons.Default.ViewWeek,
            initialTextResId = R.string.select_number_of_columns,
            selectionTextResId = R.string.columns_selected,
            valueRange = 1f..3f,
            sliderEnabled = true
        )
    }
}

@Preview(
    name = "Night Mode - Expendable",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode - Expendable",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun GameOptionCardExpandedPreview() {
    DicePokerTheme {
        GameOptionCard(
            isExpanded = true,
            selectedValue = 1,
            onSelectedValueChanged = {},
            icon = Icons.Default.ViewWeek,
            initialTextResId = R.string.select_number_of_columns,
            selectionTextResId = R.string.columns_selected,
            valueRange = 1f..3f,
            sliderEnabled = true
        )
    }
}
