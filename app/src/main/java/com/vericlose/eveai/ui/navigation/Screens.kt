package com.vericlose.eveai.ui.navigation


sealed class Screens(val route : String){
    data object Profile : Screens("profile_route")
    data object Match : Screens("match_route")
    data object Chat : Screens("chat_route")
}
