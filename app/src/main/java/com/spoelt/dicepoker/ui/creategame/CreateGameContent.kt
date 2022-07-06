package com.spoelt.dicepoker.ui.creategame

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.constants.LOGO_WIDTH_PERCENTAGE
import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.ui.components.PrimaryButton
import com.spoelt.dicepoker.ui.components.VerticalSpacer
import com.spoelt.dicepoker.ui.theme.DicePokerTheme

@Suppress("MagicNumber")
private val COLUMN_RANGE = 1f..4f

@Suppress("MagicNumber")
private val PLAYER_RANGE = 1f..7f

@Suppress("MagicNumber")
private val PLAYER_STEPS = 4

/**
 * This Composable maintains the entire screen for handling the creation of games.
 *
 * @param[viewState] The current state of the screen to render.
 * @param[onColumnsSelected] A callback invoked when the user selects the number of columns.
 * @param[onPlayersSelected] A callback invoked when the user selects the number of players.
 * @param[onNextClicked] A callback invoked when the user clicks the button to create a new
 * game.
 * @param[onColumnCardClicked] A callback invoked when the user clicks the column card.
 * @param[onPlayerCardClicked] A callback invoked when the user clicks the player card.
 */
@Composable
fun CreateGameContent(
    viewState: CreateGameViewState,
    onColumnsSelected: (Float) -> Unit,
    onPlayersSelected: (Float) -> Unit,
    onNextClicked: () -> Unit,
    onColumnCardClicked: () -> Unit,
    onPlayerCardClicked: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_24))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DicePokerLogo()

                VerticalSpacer(height = dimensionResource(id = R.dimen.spacer_48))

                GameOptions(
                    gameOptions = viewState.gameOptions,
                    slidersEnabled = viewState.slidersEnabled,
                    isColumnCardExpanded = viewState.isColumnCardExpanded,
                    isPlayerCardExpanded = viewState.isPlayerCardExpanded,
                    onColumnsSelected = onColumnsSelected,
                    onPlayersSelected = onPlayersSelected,
                    onColumnCardClicked = onColumnCardClicked,
                    onPlayerCardClicked = onPlayerCardClicked
                )

                VerticalSpacer(height = dimensionResource(id = R.dimen.spacer_20))

                if (viewState is CreateGameViewState.CreationError) {
                    ErrorMessage(
                        messageId = R.string.error_creating_game
                    )

                    VerticalSpacer(height = dimensionResource(id = R.dimen.spacer_20))
                }

                PrimaryButton(
                    text = stringResource(id = R.string.next),
                    onClick = onNextClicked,
                    enabled = viewState.buttonEnabled
                )
            }

            if (viewState is CreateGameViewState.Creating) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                )
            }
        }
    }
}

/**
 * This Composable contains the logo displayed at the top of the screen.
 */
@Composable
fun DicePokerLogo() {
    Image(
        modifier = Modifier.fillMaxWidth(LOGO_WIDTH_PERCENTAGE),
        painter = painterResource(id = R.drawable.home_screen_logo_light),
        contentDescription = stringResource(R.string.content_description_logo_light),
        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
        contentScale = ContentScale.Crop
    )
}

/**
 * The [GameOptions] Composable contains two cards with which the user can select how the game
 * should be set up.
 *
 * @param[gameOptions] An object containing the number of columns & players.
 * @param[slidersEnabled] True if the slider is enabled, false otherwise.
 * @param[isColumnCardExpanded] True if the column card is expanded, false otherwise.
 * @param[isPlayerCardExpanded] True if the player card is expanded, false otherwise.
 * @param[onColumnsSelected] A callback that is invoke when the user changes the value of columns.
 * @param[onPlayersSelected] A callback that is invoke when the user changes the value of players.
 * @param[onColumnCardClicked] A callback that is invoke when the user clicks on the columns card.
 * @param[onPlayerCardClicked] A callback that is invoke when the user clicks on the players card.
 */
@Composable
private fun GameOptions(
    gameOptions: GameOptions,
    slidersEnabled: Boolean,
    isColumnCardExpanded: Boolean,
    isPlayerCardExpanded: Boolean,
    onColumnsSelected: (Float) -> Unit,
    onPlayersSelected: (Float) -> Unit,
    onColumnCardClicked: () -> Unit,
    onPlayerCardClicked: () -> Unit
) {
    GameOptionCard(
        isExpanded = isColumnCardExpanded,
        selectedValue = gameOptions.numberOfColumns,
        onSelectedValueChanged = onColumnsSelected,
        icon = Icons.Default.ViewWeek,
        initialTextResId = R.string.select_number_of_columns,
        selectionTextResId = R.string.columns_selected,
        valueRange = COLUMN_RANGE,
        sliderEnabled = slidersEnabled,
        onCardClicked = onColumnCardClicked
    )

    VerticalSpacer(height = dimensionResource(id = R.dimen.spacer_20))

    GameOptionCard(
        isExpanded = isPlayerCardExpanded,
        selectedValue = gameOptions.numberOfPlayers,
        onSelectedValueChanged = onPlayersSelected,
        icon = Icons.Default.Groups,
        initialTextResId = R.string.select_number_of_players,
        selectionTextResId = R.string.players_selected,
        valueRange = PLAYER_RANGE,
        sliderEnabled = slidersEnabled,
        steps = PLAYER_STEPS,
        onCardClicked = onPlayerCardClicked
    )
}

@Composable
fun ErrorMessage(
    messageId: Int
) {
    Text(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_12)),
        text = stringResource(id = messageId),
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
@Suppress("UnusedPrivateMember")
private fun CreateGameContentPreview(
    @PreviewParameter(CreateGameViewStateProvider::class)
    createGameViewState: CreateGameViewState
) {
    DicePokerTheme {
        CreateGameContent(
            viewState = createGameViewState,
            onColumnsSelected = {},
            onPlayersSelected = {},
            onNextClicked = {},
            onPlayerCardClicked = {},
            onColumnCardClicked = {}
        )
    }
}

class CreateGameViewStateProvider : PreviewParameterProvider<CreateGameViewState> {

    override val values: Sequence<CreateGameViewState>
        get() {
            val gameOptions = GameOptions(
                numberOfColumns = 1f,
                numberOfPlayers = 2f
            )

            return sequenceOf(
                CreateGameViewState.Initial,
                CreateGameViewState.Active(
                    gameOptions,
                    isColumnCardExpanded = true,
                    isPlayerCardExpanded = false
                ),
                CreateGameViewState.Creating(
                    gameOptions,
                    isColumnCardExpanded = true,
                    isPlayerCardExpanded = false
                ),
                CreateGameViewState.CreationError(
                    gameOptions = gameOptions,
                    errorMessage = "Something went wrong.",
                    isColumnCardExpanded = true,
                    isPlayerCardExpanded = false
                ),
                CreateGameViewState.Created
            )
        }
}
