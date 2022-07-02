package dev.arogundade.composeapp.ui.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.arogundade.composeapp.ANIM_INTERVAL

@Composable
fun CircularProgress(value: Float, targetValue: Float, play: Boolean) {

    Card(
        modifier = Modifier
            .width(120.dp)
            .height(120.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        val animatedPercentage = animateFloatAsState(
            targetValue = if (play) value / targetValue else 0f,
            animationSpec = tween(
                durationMillis = ANIM_INTERVAL.toInt()
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(50.dp),
                contentAlignment = Alignment.Center
            ) {
                if (play) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize(),
                        onDraw = {
                            drawArc(
                                color = Color.Blue,
                                useCenter = false,
                                startAngle = -90f,
                                sweepAngle = 360 * animatedPercentage.value,
                                style = if (animatedPercentage.value >= 1) {
                                    Fill
                                } else {
                                    Stroke(
                                        width = 5f,
                                        cap = StrokeCap.Round,
                                    )
                                }
                            )
                        })

                    if (animatedPercentage.value >= 1) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "checked",
                            tint = Color.White
                        )
                    } else {
                        Text(
                            text = (animatedPercentage.value * 100).toInt()
                                .toString(), style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (play) {
                if (animatedPercentage.value >= 1) {
                    Text(text = "Uploaded")
                } else {
                    Text(text = "Uploading..")
                }
            }
        }
    }
}