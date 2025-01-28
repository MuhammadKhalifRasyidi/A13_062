package com.example.a13finalucp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a13finalucp.R
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home"
}

@Composable
fun MainScreen(
    onPesertaClick: () -> Unit,
    onEventClick: () -> Unit,
    onTiketClick: () -> Unit,
    onTransaksiClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1A38))
    ) {
        // Background Image is placed in the back of other elements
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // Navbar Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 33.dp)
        ) {
            NavbarCard()

            // Content Section
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 33.dp),
                contentAlignment = Alignment.Center
            ) {
                ContentCard(
                    onPesertaClick = onPesertaClick,
                    onEventClick = onEventClick,
                    onTiketClick = onTiketClick,
                    onTransaksiClick = onTransaksiClick
                )
            }
        }
    }
}

@Composable
fun NavbarCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(12.dp) // Elevation still works fine
    ) {
        // Applying a Captain America themed gradient background (Red, Blue)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0055A4), // Captain America blue
                            Color(0xFFB22234)  // Captain America red
                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Menu Icon (Use a suitable Captain America shield icon)
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size), // Replace with a Captain America shield icon
                    contentDescription = "Menu Icon",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "AVENGERS STUDIO",
                        fontSize = 26.sp, // Bold, large title
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        "Join the Marvel Universe!",
                        fontSize = 18.sp, // Slightly larger welcome text
                        color = Color.White.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Medium
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.logo), // Use Captain America profile image
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        }
    }
}


@Composable
fun ContentCard(
    onPesertaClick: () -> Unit,
    onEventClick: () -> Unit,
    onTiketClick: () -> Unit,
    onTransaksiClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardItem(
            title = "Peserta",
            iconResId = R.drawable.peserta, // Use Captain America-themed icon
            onClick = onPesertaClick,
            backgroundColor = Color(0xFF003366) // Captain America Blue
        )
        CardItem(
            title = "Event",
            iconResId = R.drawable.event, // Use Captain America-themed icon
            onClick = onEventClick,
            backgroundColor = Color(0xFFB22234) // Captain America Red
        )
        CardItem(
            title = "Tiket",
            iconResId = R.drawable.tiket, // Use Captain America-themed icon
            onClick = onTiketClick,
            backgroundColor = Color(0xFF6A5ACD) // Captain America purple-ish hue for energy
        )
        CardItem(
            title = "Transaksi",
            iconResId = R.drawable.transaksi, // Use Captain America-themed icon
            onClick = onTransaksiClick,
            backgroundColor = Color(0xFFFFD700) // Gold, bright and dynamic
        )
    }
}

@Composable
fun CardItem(title: String, iconResId: Int, onClick: () -> Unit, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Center items vertically
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp)) // Adjust the space between the icon and text
            Text(
                title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp, // Larger text size
                textAlign = TextAlign.Center, // Center text horizontally
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    MainScreen(
        onPesertaClick = { println("Peserta clicked") },
        onEventClick = { println("Event clicked") },
        onTiketClick = { println("Tiket clicked") },
        onTransaksiClick = { println("Transaksi clicked") }
    )
}
