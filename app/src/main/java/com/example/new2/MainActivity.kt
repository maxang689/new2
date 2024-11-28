package com.example.new2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.new2.ui.theme.New2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            New2Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details/{input}") { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("input") ?: "No data")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var inputText = ""
    Column {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter something") }
        )
        Button(onClick = { navController.navigate("details/$inputText") }) {
            Text("Go to Details")
        }
    }
}

@Composable
fun DetailsScreen(input: String) {
    Text("You entered: $input")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    New2Theme {
        AppNavigation()
    }
}
