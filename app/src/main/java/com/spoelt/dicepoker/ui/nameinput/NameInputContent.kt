package com.spoelt.dicepoker.ui.nameinput

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.constants.BUTTON_VISIBILITY_MILLIS
import com.spoelt.dicepoker.constants.EMPTY_STRING
import com.spoelt.dicepoker.constants.SINGLE_LINE
import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.domain.model.Player
import com.spoelt.dicepoker.domain.model.PlayerNameDirection
import com.spoelt.dicepoker.domain.model.Score
import com.spoelt.dicepoker.ui.components.PrimaryButton
import com.spoelt.dicepoker.ui.components.VerticalSpacer
import com.spoelt.dicepoker.ui.theme.DicePokerTheme
import com.spoelt.dicepoker.ui.theme.TextFieldShape
import java.util.UUID

@Composable
fun NameInputContent(
    viewState: NameInputViewState,
    onCloseClicked: () -> Unit,
    onPlayerNameChanged: (String) -> Unit,
    onGoBackClicked: (PlayerNameDirection) -> Unit,
    onGoForwardClicked: (PlayerNameDirection) -> Unit,
    onStartGame: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_24))
    ) {
        CloseButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.End),
            onCloseClicked = onCloseClicked
        )

        VerticalSpacer(height = dimensionResource(id = R.dimen.spacer_24))

        Row(modifier = Modifier.fillMaxSize()) {
            BackButtonColumn(
                canGoBack = viewState.canGoBack,
                onGoBackClicked = onGoBackClicked
            )
            PlayerNameInputColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                name = viewState.players[viewState.currentIndex].name
                    ?: EMPTY_STRING,
                playerNumber = viewState.currentIndex.plus(1),
                onPlayerNameChanged = onPlayerNameChanged,
                isButtonVisible = viewState.isStartGameButtonVisible,
                onStartGame = onStartGame
            )
            ForwardButtonColumn(
                canGoForward = viewState.canGoForward,
                onGoForwardClicked = onGoForwardClicked
            )
        }
    }
}

@Composable
private fun CloseButton(
    modifier: Modifier,
    onCloseClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.primary
        ),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_zero)),
        onClick = onCloseClicked
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_24)),
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(R.string.close_btn_content_desc)
        )
    }
}

@Composable
private fun BackButtonColumn(
    canGoBack: Boolean,
    onGoBackClicked: (PlayerNameDirection) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            enabled = canGoBack,
            onClick = { onGoBackClicked(PlayerNameDirection.BACK) }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = stringResource(id = R.string.back_btn_content_desc)
            )
        }
    }
}

@Composable
private fun PlayerNameInputColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    name: String,
    playerNumber: Int,
    isButtonVisible: Boolean,
    onPlayerNameChanged: (String) -> Unit,
    onStartGame: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        Text(
            text = stringResource(id = R.string.name_input_header, playerNumber),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        VerticalSpacer(height = dimensionResource(id = R.dimen.spacer_12))

        OutlinedTextField(
            modifier = Modifier
                .heightIn(dimensionResource(id = R.dimen.text_field_height))
                .fillMaxWidth(),
            shape = TextFieldShape,
            value = name,
            onValueChange = onPlayerNameChanged,
            maxLines = SINGLE_LINE,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(
            visible = isButtonVisible,
            enter = fadeIn(
                animationSpec = tween(BUTTON_VISIBILITY_MILLIS)
            ),
            exit = ExitTransition.None
        ) {
            PrimaryButton(
                text = stringResource(id = R.string.start_game_btn),
                onClick = onStartGame,
                enabled = true
            )
        }
    }
}

@Composable
private fun ForwardButtonColumn(
    canGoForward: Boolean,
    onGoForwardClicked: (PlayerNameDirection) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            enabled = canGoForward,
            onClick = {
                onGoForwardClicked(PlayerNameDirection.FORWARD)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = stringResource(id = R.string.forward_btn_content_desc)
            )
        }
    }
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
private fun NameInputContentPreview(
    @PreviewParameter(NameInputViewStateProvider::class)
    nameInputViewState: NameInputViewState
) {
    DicePokerTheme {
        NameInputContent(
            viewState = nameInputViewState,
            onCloseClicked = {},
            onPlayerNameChanged = {},
            onGoBackClicked = {},
            onGoForwardClicked = {},
            onStartGame = {}
        )
    }
}

class NameInputViewStateProvider : PreviewParameterProvider<NameInputViewState> {

    override val values: Sequence<NameInputViewState>
        get() {
            val gameOptions = GameOptions(
                numberOfColumns = 1,
                numberOfPlayers = 2
            )
            val players = listOf(
                Player(
                    playerId = 0,
                    playedGameId = UUID.randomUUID(),
                    name = "Player 1",
                    score = Score(
                        scoreColumnOne = 0,
                        hasWonColumnOne = false,
                        scoreColumnTwo = null,
                        hasWonColumnTwo = null,
                        scoreColumnThree = null,
                        hasWonColumnThree = null
                    )
                ),
                Player(
                    playerId = 1,
                    playedGameId = UUID.randomUUID(),
                    name = "Player 2",
                    score = Score(
                        scoreColumnOne = 0,
                        hasWonColumnOne = false,
                        scoreColumnTwo = null,
                        hasWonColumnTwo = null,
                        scoreColumnThree = null,
                        hasWonColumnThree = null
                    )
                )
            )

            return sequenceOf(
                NameInputViewState.Initial,
                NameInputViewState.LoadedNavArgument(
                    gameOptions = gameOptions,
                    players = players
                ),
                NameInputViewState.Active(
                    gameOptions = gameOptions,
                    players = players,
                    currentIndex = 0,
                    canGoBack = false,
                    canGoForward = true,
                    isStartGameButtonVisible = false
                ),
                NameInputViewState.Active(
                    gameOptions = gameOptions,
                    players = players,
                    currentIndex = 1,
                    canGoBack = true,
                    canGoForward = false,
                    isStartGameButtonVisible = true
                )
            )
        }
}
