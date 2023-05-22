package id.ac.unpas.composelogin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.composelogin.ui.theme.ComposeLoginTheme
import id.ac.unpas.composelogin.ui.theme.Purple700
import id.ac.unpas.composelogin.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FormLogin()
                }
            }
        }
    }
}

@Composable
fun FormLogin() {
    val context = LocalContext.current
    val username = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Text(text = "Username", modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth())
        TextField(value = username.value , onValueChange = {
            username.value = it
        }, modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth())

        Text(text = "Password", modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth())
        TextField(value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                password.value = it
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth())

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        Divider(modifier = Modifier
            .weight(1f)
            .width(0.dp))

        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (username.value.text == "admin" && password.value.text == "admin") {
                    Toast.makeText(context, "Login Sukses", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Login Gagal", Toast.LENGTH_LONG).show()
                }
            }, colors = loginButtonColors) {
                Text(
                    text = "Login",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
        }
            Button(modifier = Modifier.weight(5f), onClick = {
                username.value = TextFieldValue("")
                password.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLoginTheme {
        FormLogin()
    }
}