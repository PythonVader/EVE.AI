package com.vericlose.eveai.ui.navigation

import com.vericlose.eveai.R

data class BottomNavigationItem(
    val icon : Int = R.drawable.profile,
    val route : String = "",
    val clickedIndex : Int = 0
){
    fun bottomNavigationItems() : List<BottomNavigationItem>{
        return listOf(
            BottomNavigationItem(
                icon = R.drawable.profile,
                route = Screens.Profile.route
            ),
            BottomNavigationItem(
                icon = R.drawable.match_ai,
                route = Screens.Match.route
            ),
            BottomNavigationItem(
                icon = R.drawable.messages,
                route = Screens.Chat.route
            ),
        )
    }
}
