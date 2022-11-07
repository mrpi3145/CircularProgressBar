package com.windapp.animatedcircularprogressbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.windapp.animatedcircularprogressbar.ui.theme.AnimatedCircularProgressBarTheme
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedCircularProgressBarTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressBar(percentage = 0.8f, number = 100)
                }

            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage:Float,
    number:Int,
    fontSize:TextUnit=28.sp,
    radius: Dp =50.dp,
    color:Color= Color.Green,
    strokeWidth:Dp=8.dp,
    animDuration:Int=1000,
    aniDelay:Int=0
){

    var animationPlayed by remember{
        mutableStateOf(false)
    }
    val curPercentage=animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = aniDelay
        )
    )
    LaunchedEffect(key1 = true){
        animationPlayed=true
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius*2f)
    ){

Canvas(modifier =Modifier.size(radius*2f)  ){
    drawArc(
        color = color,
        -90f,
        360*curPercentage.value,
        useCenter = false,
        style = Stroke(strokeWidth.toPx(),cap= StrokeCap.Round)
    )

}
        Text(
            text=(curPercentage.value*number).toInt().toString(),
            color=Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )


    }

}


