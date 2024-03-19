package com.example.bubble.home.utils

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.floor

@Composable
fun CustomCircleAnimation(
    modifier: Modifier = Modifier,
    repeatableColorOne: Animatable<Color, AnimationVector4D>,
    repeatableColorTwo: Animatable<Color, AnimationVector4D>,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val randomValueOne = (floor(Math.random() * (200-350)+350))
    val randomValueTwo = (floor(Math.random() * (200-350)+350))
    val randomValueThree = (floor(Math.random() * (200-350)+350))
    val randomValueFour = (floor(Math.random() * (200-350)+350))

    val borderTopStart by getBorder(
        valueOf = 200,
        valueTo = 200 + randomValueOne.toInt(),
        infiniteTransition = infiniteTransition
    )
    val borderTopEnd by getBorder(
        valueOf = 200,
        valueTo = 200 + randomValueTwo.toInt(),
        infiniteTransition = infiniteTransition
    )
    val borderBottomStart by getBorder(
        valueOf = 200,
        valueTo = 200 + randomValueThree.toInt(),
        infiniteTransition = infiniteTransition
    )
    Log.d("checkMF", randomValueThree.toString())
    val borderBottomEnd by getBorder(
        valueOf = 200,
        valueTo = 200 + randomValueFour.toInt(),
        infiniteTransition = infiniteTransition
    )
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4_000, easing = LinearEasing)
        ), label = ""
    )
    val buttonShapeWidth by infiniteTransition.animateValue(
        initialValue = 2,
        targetValue = 4,
        typeConverter = Int.VectorConverter,
        animationSpec = InfiniteRepeatableSpec(
            animation = keyframes {
                durationMillis = 3_000
            },
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .size(150.dp)
            .rotate(angle)
            .border(
                width = buttonShapeWidth.dp,
                color = repeatableColorTwo.value,
                shape = RoundedCornerShape(
                    topStart = borderTopStart.toFloat(),
                    topEnd = borderTopEnd.toFloat(),
                    bottomStart = borderBottomStart.toFloat(),
                    bottomEnd = borderBottomEnd.toFloat()
                )
            )
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            repeatableColorOne.value,
                            repeatableColorTwo.value
                        ),
                        center = Offset(size.width / 2, size.height / 2),
                        tileMode = TileMode.Repeated,
                    )
                )
            },
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {}
}

@Composable
fun getBorder(valueOf: Int, valueTo: Int, infiniteTransition: InfiniteTransition): State<Int> {
    return infiniteTransition.animateValue(
        initialValue = valueOf,
        targetValue = (valueOf..valueTo).random(),
        typeConverter = Int.VectorConverter,
        animationSpec = InfiniteRepeatableSpec(
            animation = keyframes {
                durationMillis = 3_000
            },
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
}