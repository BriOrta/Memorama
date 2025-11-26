package com.androidexercises.memorama.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

val icons = listOf(
    Icons.Filled.Lock,
    Icons.Filled.Build,
    Icons.Filled.Create,
    Icons.Filled.CheckCircle,
    Icons.Filled.MailOutline,
    Icons.Filled.Home,
    Icons.Filled.Face,
    Icons.Filled.Favorite,
    Icons.Filled.LocationOn,
    Icons.Filled.Email, //10
    Icons.Filled.Notifications,
    Icons.Filled.Person,
    Icons.Filled.Phone,
    Icons.Filled.Delete,
    Icons.Filled.PlayArrow,
    Icons.Filled.Star,
    Icons.Filled.Settings,
    Icons.Filled.ShoppingCart,
    Icons.Filled.Info,
    Icons.Filled.ThumbUp, //20
    Icons.Filled.Warning,
    Icons.Filled.Send,
    Icons.Filled.Search,
    Icons.Filled.Clear,
    Icons.Filled.FavoriteBorder,
    Icons.Filled.DateRange,
    Icons.Filled.Share,
    Icons.Filled.Refresh,
    Icons.Filled.ArrowForward,
    Icons.Filled.ArrowBack,
    Icons.Filled.ArrowDropDown,
    Icons.Filled.AccountCircle
)

data class GameCard(
    var hasFlipped: Boolean = false,
    val icon: ImageVector,
    var isChecked: Boolean = false
)
