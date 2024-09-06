package com.mysteriouscoder.mobikulassignment.navigation

sealed class Navigationitems(var route:String)
{
    data object Screen1: Navigationitems("Screen1")
    data object Screen2: Navigationitems("Screen2/{id}")
    data object Screen3: Navigationitems("Screen3/{id}")
}