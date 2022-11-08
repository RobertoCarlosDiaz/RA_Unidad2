package com.example.ra.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ra.model.MainViewModel
import com.example.ra.ui.theme.RaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val objeto_view_model: MainViewModel by viewModels()

        setContent {
            RaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var texto = remember { mutableStateOf(value = "") }
                    var texto2 = remember { mutableStateOf(0.0) }
                    var texto3 = remember { mutableStateOf(value = 0.0)}

                    var desc = remember { mutableStateOf(value = "")}
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {

                        Text(text = "COTIZADOR DE AUTOS")
                        Row(
                        ) {
                            Text(text = "NOMBRE")
                            Spacer(modifier = Modifier.size(35.dp))
                            TextField(
                                value = desc.value ,
                                modifier = Modifier.size(240.dp,50.dp),
                                onValueChange ={
                                    desc.value = it

                                } )
                            objeto_view_model.asignarNombre(desc.value.toString())
                        }

                        Row {
                            Text(text = "MARCA DE AUTO")
                            Spacer(modifier = Modifier.padding(80.dp,0.dp))
                            generarSpinner(objeto_view_model)
                        }

                        Row {
                            Text(text = "ENGANCHE")
                            Spacer(modifier = Modifier.padding(60.dp,0.dp))
                            generarSpinner1(objeto_view_model)
                        }

                        Row {
                            Text(text = "FINANCIAMIENTO:")
                            Spacer(modifier = Modifier.padding(20.dp,0.dp))
                            generarSpiner2(objeto_view_model,texto3.value.toString() )
                        }

                        var textoSalida =remember { mutableStateOf(value = "")}
                        Button(onClick = {
                            textoSalida.value = objeto_view_model.generarFinanciamiento()

                        }) {
                            Text(text = "COTIZAR AUTO:")
                        }

                        Column() {
                            Text(text = textoSalida.value ) } } } }
        }
    }
}


@Composable
fun generarSpinner(objeto_auto: MainViewModel)
{
    var expanded by remember {
        mutableStateOf(false)
    }

    val suggestions = arrayOf(
        arrayOf(0,"Honda Acord $678,026.22","Honda Acord",678026.22,),
        arrayOf(0,"VW Touareg $879,266.15","VW Touareg",879266.15,),
        arrayOf(0,"BMW X5  $1,125,366.87","BMW X5",11253666.87,),
        arrayOf(0,"Mazda Cx7 $988,641.02","Mazda Cx7",988641.02,)
    )

    Box() {
        Button(onClick = { expanded = !expanded }){
            Text ("Opciones: ")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_auto.asignarMarca(label [2] as String,label [3] as Double)
                }) {
                    Text(text = label [1].toString()) } } }
    }
}


@Composable
fun generarSpinner1(objeto_Enganche:MainViewModel)
{
    var spiner2 by remember {
        mutableStateOf(false)
    }

    val opcion = arrayOf(
        arrayOf(0,"20%",20),
        arrayOf(0,"40%",40),
        arrayOf(0,"60%",60)
    )

    Box() {
        Button(onClick = {spiner2=!spiner2 }) {
            Text(text = "Seleccionar:")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription =null
            )
            DropdownMenu(
                expanded = spiner2,
                onDismissRequest = { spiner2 = false },
            )
            {
                opcion.forEach{label2 ->
                    DropdownMenuItem(onClick = {
                        spiner2 = false
                        objeto_Enganche.asignarEnganche(label2[2].toString().toDouble())
                    })
                    {
                        Text(text = label2[1].toString()) } } }
        }
    }
}

@Composable
fun generarSpiner2(objeto_finansa:MainViewModel,texto3: String)
{
    var Spiner3 by remember { mutableStateOf(false) }
    val elejir = arrayOf(
        arrayOf(0,"1 AñoS 7,5%",1,7.5,),
        arrayOf(0,"2 Años 9.5%",2,9.5,),
        arrayOf(0,"3 Años 12.6%",3,12.6,),
        arrayOf(0,"4 Años 12.6%",4,12.6,),
        arrayOf(0,"5 Años 13.5%",5,13.5,))

    Box() {
        Button(onClick = { Spiner3 =! Spiner3}) {
            Text(text = "Seleccione el plazo que decee:")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription =null
            )
            DropdownMenu(
                expanded = Spiner3,
                onDismissRequest = { Spiner3=false })
            {
                elejir.forEach { label3 ->
                    DropdownMenuItem(onClick = {
                        Spiner3 = false
                        objeto_finansa.asignarFinanciamiento(label3[2] as Int, label3[3] as Double)

                    })
                    {
                        Text(text = label3[1].toString())
                    }
                }
            }
        }
    }
}