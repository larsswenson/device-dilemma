package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {

    var currentStep by remember { mutableIntStateOf(1) }

    val images = listOf(
        R.drawable.sergio_1,
        R.drawable.sergio_2,
        R.drawable.sergio_3,
        R.drawable.sergio_4,
        R.drawable.sergio_5
    )

    val descriptions = listOf(
        R.string.lemon_tree_content_description,
        R.string.lemon_content_description,
        R.string.lemonade_content_description,
        R.string.empty_glass_content_description,
        R.string.empty_glass_content_description // repeat the last description for looping back
    )

    val instructions = listOf(
        "Tap to see the next image!",
        "Tap to see the next image!",
        "Tap to see the next image!",
        "Tap to see the next image!",
        "Tap to start the comic over!"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Sergio Aragones' Device Dilemma",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            LemonTextAndImage(
                drawableResourceId = images[currentStep - 1],
                contentDescriptionResourceId = descriptions[currentStep - 1],
                instructionText = instructions[currentStep - 1],
                onImageClick = {
                    currentStep = (currentStep % images.size) + 1
                }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    instructionText: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(drawableResourceId),
                        contentDescription = stringResource(contentDescriptionResourceId),
                        modifier = Modifier
                            .width(dimensionResource(R.dimen.button_image_width))
                            .height(dimensionResource(R.dimen.button_image_height))
                            .padding(dimensionResource(R.dimen.button_interior_padding))
                    )
                    Text(
                        text = instructionText,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 20.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_vertical))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonPreview() {
    AppTheme() {
        LemonadeApp()
    }
}

