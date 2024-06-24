package buildlabs.app.controloneandroid.connect.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import buildlabs.app.controloneandroid.connect.presentation.observer.wan.ParamsObserver
import buildlabs.app.controloneandroid.ui.theme.Blue01
import buildlabs.app.controloneandroid.ui.theme.ControlOneAndroidTheme
import buildlabs.app.controloneandroid.ui.theme.Gray01
import buildlabs.app.controloneandroid.ui.theme.White01
import buildlabs.app.controloneandroid.ui.theme.White02

@Composable
fun HomeScreen(networkStatus: String) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Sign in to continue",
            color = White01,
            fontSize = 22.sp,
        )
        Spacer(modifier = Modifier.height(36.dp))
        val txState = remember {
            mutableStateOf(networkStatus)
        }
//        val txState = rememberTextFieldState()

        OutlinedTextField(
            modifier = Modifier
                .width(357.dp)
                .height(52.dp)
                .border(BorderStroke(5.dp, Color.Transparent))
                .background(Color.Transparent),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = White01,
                fontSize = 16.sp
            ),
            colors = OutlinedTextFieldDefaults
                .colors(focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Cyan),
            shape = RoundedCornerShape(10.dp),
            value = txState.value,
            onValueChange = {
//                txState.value = networkStatus.toString()
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedButton(
            onClick = {
                      txState.value = networkStatus
            },
            modifier = Modifier
                .width(359.dp)
                .size(53.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(25),
                    clip = true
                )
                .background(Gray01),
            border = BorderStroke(2.dp, Color.Transparent),
            shape = RoundedCornerShape(25),
        ) {
            Text(
                text = "Update",
                color = White02,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row{
            Column(
                modifier = Modifier
                    .background(Color.Gray)
                    .align(Alignment.CenterVertically),
            ) {
                Divider(
                    color = Color.White,
                    thickness = 0.2.dp,
                    modifier = Modifier
                        .width(129.dp)
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = "or",
                color = Blue01,
                fontSize = 22.sp,
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column(
                modifier = Modifier
                    .background(Color.Gray)
                    .align(Alignment.CenterVertically)
            ) {
                Divider(
                    color = Color.Cyan,
                    thickness = 0.2.dp,
                    modifier = Modifier
                        .width(129.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEntryPoint() {
    ControlOneAndroidTheme {
        HomeScreen(
            networkStatus = ParamsObserver.NetworkStatus.UNAVAILABLE.toString()
        )
    }
}