package com.abhinav.musically.common.navigation

/*
* Simple interface for keeping all routes in a easy visible place
*/
interface MusicallyDestinations {
    val route: String
}

object Home : MusicallyDestinations {
    override val route = "home"
}

object Details : MusicallyDestinations {
    const val ARG_ARTIST_ID = "taskId"
    override val route = "details"
}
