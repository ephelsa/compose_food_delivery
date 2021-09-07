package com.github.ephelsa.ui.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ephelsa.ui.theme.ExtraSmallSpacing
import com.github.ephelsa.ui.theme.MediumSpacing
import com.github.ephelsa.ui.theme.PinkSwan

@ExperimentalAnimationApi
@Composable
fun ExtendableIconButton(
    imageVector: ImageVector,
    text: String,
    number: Int = 0,
    isExtended: Boolean = false,
    iconColor: Color = PinkSwan,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width((ExpandableIcon.value * 1.5).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(imageVector, text, number, isExtended, iconColor, onClick)
        Spacer(Modifier.height(ExtraSmallSpacing))
        ExtendableText(isExtended, text)
    }
}

@ExperimentalAnimationApi
@Composable
private fun IconButton(
    imageVector: ImageVector,
    contentDescription: String,
    number: Int,
    isExtended: Boolean,
    iconColor: Color = PinkSwan,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isExtended,
            enter = expandIn(),
            exit = shrinkOut()
        ) {
            Spacer(
                modifier = Modifier
                    .size(ExpandableIcon)
                    .graphicsLayer(
                        scaleX = CoverScale,
                        scaleY = CoverScale
                    )
                    .clip(CircleShape)
                    .background(
                        color = MaterialTheme.colors.surface
                    )
            )
        }

        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(ExpandableIcon)
                    .clip(CircleShape)
                    .clickable(
                        onClick = onClick,
                        role = Role.Button
                    )
                    .background(
                        color = if (isExtended) MaterialTheme.colors.primary else Color.Transparent,
                    )
                    .padding(MediumSpacing),
                tint = if (isExtended) MaterialTheme.colors.surface else iconColor
            )

            if (number > 0) {
                Text(
                    text = number.toString(),
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .sizeIn(minHeight = 15.dp, minWidth = 15.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.error),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun ExtendableText(
    isExtended: Boolean,
    text: String
) {
    AnimatedVisibility(
        visible = isExtended,
        enter = slideInVertically() + expandVertically() + fadeIn(),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}

private val ExpandableIcon = 40.dp
private const val CoverScale = 1.4f

@ExperimentalAnimationApi
@Preview
@Composable
internal fun ExtendableIconButtonPreview() {
    ExtendableIconButton(
        imageVector = Icons.Rounded.Home,
        text = "Home",
        number = 3,
        isExtended = true
    ) {

    }
}