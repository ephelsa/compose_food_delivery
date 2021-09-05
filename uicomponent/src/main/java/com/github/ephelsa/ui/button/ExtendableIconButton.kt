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
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ephelsa.ui.theme.PinkSwan

@ExperimentalAnimationApi
@Composable
fun ExtendableIconButton(
    imageVector: ImageVector,
    text: String,
    isExtended: Boolean = false,
    iconColor: Color = PinkSwan,
    onClick: () -> Unit
) {
    val (extended, setExtend) = remember { mutableStateOf(isExtended) }
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
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, radius = 30.dp),
                    onClick = {
                        setExtend(!extended)
                        onClick()
                    },
                    role = Role.Button
                )
                .background(
                    color = if (extended) MaterialTheme.colors.primary else Color.Transparent,
                )
                .padding(6.dp),
            tint = if (extended) MaterialTheme.colors.surface else iconColor
        )

        AnimatedVisibility(
            visible = extended,
            enter = slideInVertically() + expandVertically() + fadeIn(),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.button,
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