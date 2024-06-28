package com.example.maximbar.ui.screen.main

import android.os.Bundle
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.maximbar.data.model.dto.TableOccupation
import com.example.maximbar.data.model.entity.Occupation
import com.example.maximbar.data.model.entity.Table
import com.example.maximbar.data.repository.Repository
import com.example.maximbar.utils.getThisDay

class TableReservationVM(repository: Repository, stateHandle: SavedStateHandle): ViewModel()  {

    private val currentDate = getThisDay()


    private val _tables = MutableLiveData<List<Table>>(listOf(
        Table(1, 4),
        Table(2, 4),
        Table(3, 5),
        Table(4, 6),
        Table(5, 3),
        Table(6, 3),
        Table(7, 8),
        Table(8, 8),
    ))
    private val _occupation = MutableLiveData<List<Occupation>>(listOf(
        Occupation(1,true),
        Occupation(2, true),
        Occupation(4, true),
    ))

    private val tableOccupationList = _tables.value?.map { table ->
        val occupation = _occupation.value?.find { it.tableId == table.id }
        TableOccupation(
            table,
            occupation?.isOccupied ?: false
        )
    }

    lateinit var filterData: String
    var filterSeats = mutableIntStateOf(0) // 0 - столы с любым количеством мест
    var filterOccupied = mutableStateOf(false)

    val filteredTables
        get() = tableOccupationList?.filter { item ->
            (filterSeats.intValue == 0 || item.table.seats == filterSeats.intValue) &&
                    (!filterOccupied.value || !item.isOccupied)
        }

    fun getTables(){

    }

    fun getOccupation(data: String){

    }

    companion object{
        fun provideFactory(
            myRepository: Repository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return TableReservationVM(myRepository, handle) as T
                }
            }
    }
}