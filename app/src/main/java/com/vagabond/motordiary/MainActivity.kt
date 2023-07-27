package com.vagabond.motordiary

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.vagabond.motordiary.ui.theme.MotorDiaryTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            MotorDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Login()


                }
            }
        }
    }
}

@Composable
fun Login() {
    val (loginVisibility, setLoginVisibility) = remember {
        mutableStateOf(false)
    }
    val (headerLogoVisibility, setHeaderLogoVisibility) = remember {
        mutableStateOf(false)
    }
    val (headerTextVisibility, setHeaderTextVisibility) = remember {
        mutableStateOf(false)
    }
    val (transparentBgVisibility, setTransparentBgVisibility) = remember {
        mutableStateOf(false)
    }
    val (loginFieldsVisibility, setLoginFieldsVisibility) = remember {
        mutableStateOf(false)
    }
    val (footerVisibility, setFooterVisibility) = remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(
        visible = loginVisibility,
    ) {
        LoginBackground(transparentBgVisibility) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                LoginHeader(headerLogoVisibility, headerTextVisibility)
                LoginFields(loginFieldsVisibility)
                LoginFooter(footerVisibility)
            }
        }
    }

    LaunchedEffect(Unit) {
        setLoginVisibility(true)
        delay(50)
        setTransparentBgVisibility(true)
        delay(100)
        setHeaderLogoVisibility(true)
        delay(150)
        setHeaderTextVisibility(true)
        delay(200)
        setLoginFieldsVisibility(true)
        delay(200)
        setFooterVisibility(true)
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LoginBackground(
    transparentBgVisibility: Boolean, content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.katao),
            contentDescription = "Login Background",
            modifier = Modifier
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop
        )
        if (transparentBgVisibility) Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                .alpha(.5f)
                .clip(CutCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.background)
                .animateEnterExit(enter = fadeIn() + slideInHorizontally(tween(150)))
        ) {
            content()
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LoginHeader(
    headerLogoVisibility: Boolean, headerTextVisibility: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row() {
            if (headerLogoVisibility) Image(
                painter = painterResource(id = R.drawable.motor_diaries),
                contentDescription = "Login Header",
                modifier = Modifier.padding(top = 16.dp).animateEnterExit(
                    enter = fadeIn(animationSpec = tween()) + expandIn(animationSpec = tween(150),
                    expandFrom = Alignment.Center)
                ),
                contentScale = ContentScale.Crop
            )
        }
        if (headerTextVisibility) {
            Text(
                "Welcome Back",
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .animateEnterExit(
                        enter = fadeIn(animationSpec = tween()) + expandIn(animationSpec = tween(150))
                    ),
                fontSize = 38.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                "Sign in to continue",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .animateEnterExit(
                        enter = fadeIn() + expandIn(
                            animationSpec = tween(delayMillis = 75, durationMillis = 150)
                        )
                    ),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall
            )
        }


    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LoginFields(loginFieldsVisibility: Boolean) {

    if (loginFieldsVisibility) Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        CreateLoginField(label = "Email", value = "", onValueChange = {})
        Spacer(modifier = Modifier.height(8.dp))
        CreateLoginField(
            label = "Password",
            value = "",
            animationDelay = 75,
            onValueChange = {},
            visualTransformation = PasswordVisualTransformation()
        )
        Text(
            text = "Forgot Password?",
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                .animateEnterExit(
                    enter = fadeIn() + expandVertically(
                        animationSpec = tween(delayMillis = 150, durationMillis = 50),
                        expandFrom = Alignment.Top
                    )
                )
                .wrapContentSize()
                .align(Alignment.End)
        )
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.CreateLoginField(
    label: String,
    value: String,
    animationDelay: Int = 0,
    placedHolder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .animateEnterExit(
                enter = fadeIn(animationSpec = tween(delayMillis = animationDelay)) + slideInHorizontally(
                    animationSpec = tween(delayMillis = animationDelay, durationMillis = 150),
                )
            ),
        placeholder = placedHolder,
        leadingIcon = leadingIcon,
        label = { Text(text = label) },
        visualTransformation = visualTransformation,
    )


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LoginFooter(footerVisibility: Boolean) {
    val isLoginInProgress = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .padding(start = 16.dp, end = 16.dp)
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AnimatedContent(targetState = isLoginInProgress.value, transitionSpec = {
            fadeIn(animationSpec = tween(300, 150)) + expandHorizontally(
                animationSpec = tween(300, 0), expandFrom = Alignment.CenterHorizontally
            ) with shrinkHorizontally(
                animationSpec = tween(300, 0), shrinkTowards = Alignment.CenterHorizontally
            ) + fadeOut(animationSpec = tween(150, 100)) using sizeTransform()
        }) {
            if (footerVisibility) {
                if (!it) {
                    Button(onClick = {
                        isLoginInProgress.value = true
                        Log.e("Login", "button clicked")

                        GlobalScope.launch {
                            delay(4000)
                            isLoginInProgress.value = false
                        }
                    }, modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Sign In",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                } else

                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.wrapContentSize()
                    )

            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        if (!isLoginInProgress.value)

            Text(
                "Don't have an account? Sign In",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 18.dp),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall
            )
        Spacer(modifier = Modifier.height(36.dp))
    }

}

//@Composable
@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<Boolean>.sizeTransform() =
    SizeTransform { initialSize, targetSize ->
        if (targetState) {
            keyframes {
                // Expand horizontally first.
                IntSize(targetSize.width, initialSize.height) at 150
                durationMillis = 300
            }
        } else {
            keyframes {
                // Shrink vertically first.
                IntSize(initialSize.width, targetSize.height) at 150
                durationMillis = 300
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MotorDiaryTheme {
        Login()
    }
}