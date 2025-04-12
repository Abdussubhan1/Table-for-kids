package com.example.tablesforkids

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Main page") {
        composable("Main page") {
            App(navController)
        }
        composable(
            "Table Screen/{number}",
            arguments = listOf(navArgument("number") { type = IntType })


        ) {
            TableScreen(it.arguments?.getInt("number")!!, navController)
        }

    }

}


@Composable
fun App(navController: NavHostController) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "Table App for Kids",
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { exitApp(context) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Exit App",
                        tint = Color.Red,
                        modifier = Modifier.size(30.dp)
                    )
                }


            }
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Select A number from below list to view the table",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.wrapContentSize(Alignment.Center)
                )
                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier = Modifier.height(340.dp)) {
                    LazyColumn {
                        items(20) { item ->

                            Box(modifier = Modifier.fillMaxSize()) {
                                Card(
                                    onClick = { navController.navigate("Table Screen/${item + 1}") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = CardDefaults.elevatedShape,
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.DarkGray,
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Text(
                                        "Table of    ${item + 1}",
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                            }
                        }
                    }
                }

            }


        }


    }
}

@Composable
fun TableScreen(number: Int, navController: NavHostController) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { navController.navigate("Main page") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Exit App",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "Table App for Kids",
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { exitApp(context) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Exit App",
                        tint = Color.Red,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))

            for (i in 1..10) {
                Text(
                    "$number x $i = ${number * i}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

            }
        }
    }
}


fun exitApp(context: Context) {
    (context as? Activity)?.finish()
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App(rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun TablePreview() {
    TableScreen(3, rememberNavController())
}

