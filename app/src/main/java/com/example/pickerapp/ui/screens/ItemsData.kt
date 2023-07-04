package com.example.pickerapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pickerapp.data.Item
import com.example.pickerapp.ui.PickerViewModel

@Composable
fun TransferScreen(viewModel: PickerViewModel = hiltViewModel()) {

    Column {
        Card(modifier = Modifier.border(width = 1.dp, color = Color.Gray)) {
            Text(
                "Warehouse Items",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
                    .background(color = Color.Blue),
                style = TextStyle(color = Color.White),
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 250.dp), modifier = Modifier.padding(8.dp)) {
                items(viewModel.sourceList) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${it.name}", fontSize = 13.sp)
                        Text(
                            text = "Pick Up",
                            fontSize = 12.sp,
                            modifier = Modifier.background(Color.White).clickable {
                                viewModel.addToDispatcher(it)
                                viewModel.removeItemFromWareHouse(it)
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(13.dp))
        Card(modifier = Modifier.border(width = 1.dp, color = Color.Cyan)) {
            Text(
                "Picked Items",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .background(color = Color.Black),
                style = TextStyle(color = Color.White),
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 250.dp), modifier = Modifier.padding(8.dp))  {

                    items(viewModel.destinationList) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "${it.name}")
                            Row() {
                                TextButton(
                                    onClick = {

                                    },
                                ) {
                                    Text(text = "Dispatch")
                                }
                                TextButton(
                                    onClick = {
                                        viewModel.removeItemFromDispatcher(it)
                                        viewModel.addToWarehouse(item = it)
                                    },
                                ) {
                                    Text(text = "To Warehouse")
                                }
                            }
                        }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTransferScreen( viewModel: PickerViewModel = hiltViewModel()) {
    viewModel.sourceList.addAll(viewModel.getWareHouseItems())
    viewModel.destinationList.addAll(viewModel.getPickedUpList())

    TransferScreen()
}

@Composable
fun EmptyList(){
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            "Picked Items",
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .background(color = Color.Black),
            style = TextStyle(color = Color.White),
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
    }
}