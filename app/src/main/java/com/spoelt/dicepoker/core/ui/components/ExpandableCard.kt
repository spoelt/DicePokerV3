package com.spoelt.dicepoker.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.core.ui.theme.DicePokerTheme
import com.spoelt.dicepoker.core.ui.theme.ExpandableCardShape

@Composable
fun ExpandableCard(
    modifier: Modifier,
    selectedValue: Int,
    icon: ImageVector,
    initialTextResId: Int,
    selectionTextResId: Int
) {
    Card(
        modifier = modifier,
        shape = ExpandableCardShape,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.border_width),
            color = MaterialTheme.colors.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
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
                text = if (selectedValue > 0) {
                    stringResource(id = selectionTextResId, selectedValue)
                } else {
                    stringResource(id = initialTextResId)
                }
            )
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium)),
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
            selectedValue = 0,
            icon = Icons.Default.ViewWeek,
            initialTextResId = R.string.select_number_of_columns,
            selectionTextResId = R.string.columns_selected
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
            selectedValue = 2,
            icon = Icons.Default.ViewWeek,
            initialTextResId = R.string.select_number_of_columns,
            selectionTextResId = R.string.columns_selected
        )
    }
}
