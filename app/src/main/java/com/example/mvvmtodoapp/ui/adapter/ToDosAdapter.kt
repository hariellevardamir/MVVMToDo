package com.example.mvvmtodoapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtodoapp.data.entity.ToDos
import com.example.mvvmtodoapp.databinding.CardDesignBinding
import com.example.mvvmtodoapp.ui.fragment.MainFragmentDirections
import com.example.mvvmtodoapp.ui.viewModel.MainViewModel
import com.example.mvvmtodoapp.utils.transition
import com.google.android.material.snackbar.Snackbar

class ToDosAdapter(var mContext: Context, var toDoList: List<ToDos>, var viewModel: MainViewModel) :
    RecyclerView.Adapter<ToDosAdapter.CardDesignHolder>() {

    private var TAG = "ToDosAdapter"

    inner class CardDesignHolder(var binding: CardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val toDo = toDoList[position]
        val design = holder.binding
        design.textViewName.text = toDo.name

        design.singleItemView.setOnClickListener {
            val updateScreen = MainFragmentDirections.actionMainFragmentToUpdateFragment(toDo)
            Navigation.transition(it, updateScreen)
            Log.d(TAG, toDo.toString())
        }

        design.imageViewDelete.setOnClickListener {
            Snackbar.make(it, "Do you want to delete ${toDo.name} ?", Snackbar.LENGTH_SHORT)
                // .setActionTextColor(Color.WHITE)
                .setAction("YES")
                {
                    viewModel.delete(toDo.id)
                    Toast.makeText(mContext, "${toDo.name} deleted", Toast.LENGTH_SHORT).show()
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}