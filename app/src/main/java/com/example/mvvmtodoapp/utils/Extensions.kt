package com.example.mvvmtodoapp.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation


// transition for ; fragment to fragment
fun Navigation.transition(it: View, id: Int) {
    findNavController(it).navigate(id)
    //  androidx.navigation.Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_saveFragment)
}

fun Navigation.transition(it: View, id: NavDirections) {
    findNavController(it).navigate(id)
}