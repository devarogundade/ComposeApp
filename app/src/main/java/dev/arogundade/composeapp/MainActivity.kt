package dev.arogundade.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import dev.arogundade.composeapp.ui.progress.CircularProgress
import dev.arogundade.composeapp.ui.theme.ComposeAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val targetValue = 100f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var progress by remember {
                mutableStateOf(0f)
            }

            var play by remember {
                mutableStateOf(false)
            }

            ComposeAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (play) {
                        CircularProgress(value = progress, targetValue = targetValue, play)
                    } else {
                        Button(onClick = {
                            play = true
                            lifecycleScope.launchWhenCreated {
                                while (progress < targetValue) {
                                    delay(ANIM_INTERVAL)
                                    progress += 10
                                }
                            }
                        }) {
                            Text(text = "Upload Video")
                        }
                    }
                }
            }
        }
    }

}
