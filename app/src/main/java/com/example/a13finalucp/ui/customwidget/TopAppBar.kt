package com.example.a13finalucp.ui.customwidget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumeTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF0055A4), // Biru
            Color(0xFFB22234)  // Merah
        )
    )

    Box(
        modifier = Modifier
            .background(gradientBrush)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(title,
                color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 50.dp)
            ) },
            actions = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .clickable {
                            onRefresh()
                    }
                )
            },
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent // Transparan agar gradien terlihat
            ),
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp,
                        modifier = Modifier
                            .padding(bottom = 50.dp)) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = Color.White,
                            contentDescription = null)
                    }
                }
            }
        )
    }
}
