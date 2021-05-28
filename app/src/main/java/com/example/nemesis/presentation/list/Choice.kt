package com.example.nemesis.presentation.list

data class Choice(
    val id: Int,
    val name: String,
    val description: String,
    val img_drawable: String

) {

    companion object {
        val choice1 = Choice(1, "NextSeason", "You'll see the list of future airing anime next season","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBdUeR62GBEMSRHxatJ7oY-MV3G-Yv-MK6cg&usqp=CAU")
        val choice2 = Choice(2, "Upcomming", "You'll see the list of announcing anime release", "https://wallpapercave.com/wp/wp5591884.png")

    }
}