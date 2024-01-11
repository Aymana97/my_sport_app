package com.example.mysportapp.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun PlayerCard(id: Int, name: String, photo: String?) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.padding(10.dp)
        ) {
            if (photo != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = photo),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = id.toString())
                Text(text = name)
            }
        }
    }
}