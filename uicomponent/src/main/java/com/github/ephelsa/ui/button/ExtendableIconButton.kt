package com.github.ephelsa.ui.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.ephelsa.ui.R

@Composable
fun SimpleIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    fillBackground: Boolean,
    size: Dp = 50.dp,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.primary,
    iconColor: Color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = if (fillBackground) backgroundColor else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(12.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize(),
            tint = if (fillBackground) MaterialTheme.colors.surface else iconColor
        )
    }
}

@Preview
@Composable
internal fun SimpleIconButtonPreview() {
    SimpleIconButton(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_tune),
        contentDescription = "Settings",
        fillBackground = true,
    ) {

    }
}

@ExperimentalAnimationApi
@Composable
fun ExtendableIconButton(
    imageVector: ImageVector,
    text: String,
    isExtended: Boolean = false,
    iconColor: Color = if (isSystemInDarkTheme()) Color.White else Color.LightGray,
    onClick: () -> Unit
) {
    val (isExtend, setExtend) = remember { mutableStateOf(isExtended) }
    val width = text.length * MaterialTheme.typography.body1.fontSize.value

    Column(
        modifier = Modifier
            .width(width.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = text,
            modifier = Modifier
                .size(35.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, radius = 30.dp),
                    onClick = {
                        setExtend(!isExtend)
                        onClick()
                    }
                )
                .background(
                    color = if (isExtend) MaterialTheme.colors.primary else Color.Transparent,
                    shape = CircleShape
                )
                .padding(6.dp),
            tint = if (isExtend) MaterialTheme.colors.surface else iconColor
        )

        AnimatedVisibility(
            visible = isExtend,
            enter = slideInVertically() + expandVertically() + fadeIn(),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
internal fun ExtendableIconButtonPreview() {
    ExtendableIconButton(
        imageVector = Icons.Rounded.Home,
        text = "Home"
    ) {

    }
}