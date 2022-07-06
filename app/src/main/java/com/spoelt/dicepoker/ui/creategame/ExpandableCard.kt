package com.spoelt.dicepoker.ui.creategame

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.ui.components.HorizontalSpacer
import com.spoelt.dicepoker.ui.theme.DicePokerTheme
import com.spoelt.dicepoker.ui.theme.ExpandableCardShape

@Composable
fun ExpandableCard(
    modifier: Modifier,
    selectedValue: Float,
    icon: ImageVector,
    initialTextResId: Int,
    selectionTextResId: Int,
    onCardClicked: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onCardClicked() },
        shape = ExpandableCardShape,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.border_width),
            color = MaterialTheme.colors.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_12)),
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
                text = if (selectedValue > 0) {
                    stringResource(id = selectionTextResId, selectedValue.toInt())
                } else {
                    stringResource(id = initialTextResId)
                }
            )
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_24)),
                imageVector = Icons.Default.ExpandMore,
                contentDescription = stringResource(id = R.string.content_description_expand_more)
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
@Suppress("UnusedPrivateMember")
private fun ExpandableCardInitialStatePreview() {
    DicePokerTheme {
        ExpandableCard(
            modifier = Modifier,
            selectedValue = 0f,
            icon = Icons.Default.ViewWeek,
            initialTextResId = R.string.select_number_of_columns,
            selectionTextResId = R.string.columns_selected,
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
@Suppress("UnusedPrivateMember")
private fun ExpandableCardSelectedValuePreview() {
    DicePokerTheme {
        ExpandableCard(
            modifier = Modifier,
            selectedValue = 2f,
            icon = Icons.Default.ViewWeek,
            initialTextResId = R.string.select_number_of_columns,
            selectionTextResId = R.string.columns_selected,
            onCardClicked = {}
        )
    }
}
