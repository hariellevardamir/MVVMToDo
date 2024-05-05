package com.example.mvvmtodoapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtodoapp.data.entity.ToDos
import com.example.mvvmtodoapp.data.repo.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var toDosRepo: ToDosRepository) : ViewModel() {
    var toDosListLiveData = MutableLiveData<List<ToDos>>()

    // Açılır açılmaz classdan nesne türetmek istediğimiz için ilgili fonksiyonu init bloğu içinde çağırmalıyız.
    init {
        loadAllToDos()
    }

    fun delete(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            toDosRepo.delete(id)
            loadAllToDos()  // Silme işlmeinden sonra listemizi güncellemek için burada tekrar loadAllToDos() metodunu çağırmalıyız.
        }
    }

    fun loadAllToDos() {
        CoroutineScope(Dispatchers.Main).launch {
            toDosListLiveData.value = toDosRepo.loadAllToDos()
        }
    }

    fun searchToDos(searchText: String) {
        CoroutineScope(Dispatchers.Main).launch {
            toDosListLiveData.value = toDosRepo.searchToDos(searchText)
        }
    }
}