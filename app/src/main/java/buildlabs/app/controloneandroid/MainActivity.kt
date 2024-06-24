package buildlabs.app.controloneandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import buildlabs.app.controloneandroid.connect.presentation.HomeScreen
import buildlabs.app.controloneandroid.connect.presentation.observer.wan.ParamsObserver
import buildlabs.app.controloneandroid.connect.presentation.observer.wan.RANParamObserver
import buildlabs.app.controloneandroid.ui.theme.ControlOneAndroidTheme
import kotlin.math.acos

class MainActivity : ComponentActivity() {
    private val activity: MainActivity = this
    private lateinit var paramsObserver: ParamsObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paramsObserver = RANParamObserver(applicationContext)

        enableEdgeToEdge()
        setContent {
            ControlOneAndroidTheme {
                val networkStatus by paramsObserver.observeNetworkStatus().collectAsState(
                    initial = ParamsObserver.NetworkStatus.UNAVAILABLE
                )
                /** takes flow (network-status)from 'ParamsObserver' and  that to
                 * HomeScreen to be used as Composable-State
                 * */
                PresentHomeScreen(activity, networkStatus)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentHomeScreen(activity: MainActivity, networkStatus: ParamsObserver.NetworkStatus) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.secondary
                ),
                title = {
                    Text(
                        "Network Status: ${
                            networkStatus.toString().let { str ->
                                str.substring(0, 1).uppercase() +
                                        str.substring(1).lowercase()
                            }
                        }",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary,
            ) {
                Row {
                    ElevatedButton(
                        onClick = {
                            Toast.makeText(activity, "Connecting", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5A69BE),
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White
                        ),
                        content = {
                            Text(
                                text = "Connect",
                                textAlign = TextAlign.Start
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ElevatedButton(
                        onClick = {/**/ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF545A7A),
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White
                        ),
                        content = {
                            Text(
                                text = "Disconnect",
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                /** */
                Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(5.dp),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart),
                color = Color.Blue,
                text = "Network Status: $networkStatus"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant
                ),
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                HomeScreen("Network status: $networkStatus")
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEntryPoint() {
    ControlOneAndroidTheme {
        Text(
            modifier = Modifier
                .background(Color.White),
            color = Color.Blue,
            text = ParamsObserver.NetworkStatus.UNAVAILABLE.toString()
        )
        PresentHomeScreen(MainActivity(), ParamsObserver.NetworkStatus.UNAVAILABLE)
    }
}


