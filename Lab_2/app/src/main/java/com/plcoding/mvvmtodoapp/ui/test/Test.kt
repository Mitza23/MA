package com.plcoding.mvvmtodoapp.ui.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel

@Composable
@Preview
fun Test(
    viewModel: TestViewModel = hiltViewModel()
) {
    Text(text = "aaaa")
}