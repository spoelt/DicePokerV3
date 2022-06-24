package com.spoelt.dicepoker.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.spoelt.dicepoker.R
import com.spoelt.dicepoker.constants.SPLASH_SCREEN_ANIMATION_DURATION
import com.spoelt.dicepoker.constants.SPLASH_SCREEN_DELAY
import com.spoelt.dicepoker.constants.SPLASH_SCREEN_IMAGE_INITIAL_VALUE
import com.spoelt.dicepoker.constants.SPLASH_SCREEN_IMAGE_TARGET_VALUE
import com.spoelt.dicepoker.constants.SPLASH_SCREEN_INTERPOLATION_TENSION
import com.spoelt.dicepoker.destinations.CreateGameScreenDestination
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun SplashScreen(navigator: DestinationsNavigator) {
    val scale = remember {
        Animatable(SPLASH_SCREEN_IMAGE_INITIAL_VALUE)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = SPLASH_SCREEN_IMAGE_TARGET_VALUE,
            animationSpec = tween(
                durationMillis = SPLASH_SCREEN_ANIMATION_DURATION,
                easing = {
                    OvershootInterpolator(SPLASH_SCREEN_INTERPOLATION_TENSION).getInterpolation(it)
                }
            )
        )
        delay(SPLASH_SCREEN_DELAY)
        navigator.apply {
            popBackStack()
            navigate(CreateGameScreenDestination)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.scale(scale.value),
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop
        )
    }
}