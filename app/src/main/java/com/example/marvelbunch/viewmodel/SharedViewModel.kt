package com.example.marvelbunch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelbunch.models.CharacterModel
import com.example.marvelbunch.constants.Constants
import com.example.marvelbunch.constants.ScreenState
import com.example.marvelbunch.models.EventsModel
import com.example.marvelbunch.network.ApiClient
import com.example.marvelbunch.models.MarvelComicData
import com.example.marvelbunch.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class SharedViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel(){

    private var _comicsLiveData = MutableLiveData<ScreenState<MarvelComicData>>()

    val comicsLiveData: LiveData<ScreenState<MarvelComicData>>
        get() = _comicsLiveData


    private var _charactersLiveData = MutableLiveData<ScreenState<CharacterModel>>()


    val charactersLiveData: LiveData<ScreenState<CharacterModel>>
        get() = _charactersLiveData

    private var _eventsLiveData = MutableLiveData<ScreenState<EventsModel>>()

    val eventsLiveData: LiveData<ScreenState<EventsModel>>
        get() = _eventsLiveData

    init {
       fetchCharacters()
        fetchComics()
        fetchEvents()
    }

    private fun fetchComics(){
        _comicsLiveData.postValue(ScreenState.Loading(null))
         viewModelScope.launch {
             try {
                 val client = repository.getComics(Constants.API_KEY, Constants.TIME_STAMP,  Constants.HASH)
                 _comicsLiveData.postValue(ScreenState.Success(client.results))
                 Log.d("comicvalues", "${client.results}")
             }catch (e: Exception){
                 _comicsLiveData.postValue(ScreenState.Error(e.message!!, null))
                  Log.d("exception", e.message!!)
             }
         }
    }

    private fun fetchCharacters(){
        _charactersLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch {
            try {
                val client = repository.getCharacters(Constants.API_KEY, Constants.TIME_STAMP,  Constants.HASH, 0, 100)
                _charactersLiveData.postValue(ScreenState.Success(client.results))
                Log.d("characterValues", "${client.results}")
            }catch (e: Exception){
                _charactersLiveData.postValue(ScreenState.Error(e.message!!, null))
                Log.d("exception", e.message!!)
            }
        }
    }

    private fun fetchEvents(){
        _eventsLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch {
            try {
                val client = repository.getEvents(Constants.API_KEY, Constants.TIME_STAMP,  Constants.HASH)
                _eventsLiveData.postValue(ScreenState.Success(client.results))
                Log.d("eventsValues", "${client.results}")
            }catch (e: Exception){
                _eventsLiveData.postValue(ScreenState.Error(e.message!!, null))
                Log.d("exception", e.message!!)
            }
        }
    }





}