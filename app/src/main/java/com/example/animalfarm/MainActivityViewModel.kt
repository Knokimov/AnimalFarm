package com.example.animalfarm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel: ViewModel() {
    private var liveData: MutableLiveData<List<AlpacaParty>> = MutableLiveData()
    private var listData: List<AlpacaParty> = ArrayList()

    fun getPartyData(): MutableLiveData<List<AlpacaParty>> {
        MainActivity.scope.launch {
            withContext(MainActivity.scope.coroutineContext) {
                val rest = DataSource()
                val alpacaList = rest.alpacaparties.listAlpacas()
                withContext(Dispatchers.Main) {
                    setData(alpacaList.parties)
                }
            }
        }
        return liveData
    }

    fun getVotingData(district: Int){
        when (district) {
            1 -> {
                getVotingDataJSON(1)
            }
            2 -> {
                getVotingDataJSON(2)
            }
            3 -> {
                getVotingDataXML()
            }
        }
    }

    private fun getVotingDataJSON(district: Int) {
        MainActivity.scope.launch {
            withContext(MainActivity.scope.coroutineContext) {
                val rest = DataSource()
                var votingList : List<ID> = ArrayList()
                if(district == 1){
                    votingList = rest.district1.listVotes()
                } else if (district == 2){
                    votingList = rest.district2.listVotes()
                }
                withContext(Dispatchers.Main) {
                    val votes = arrayOf(0, 0, 0, 0)
                    for(x in votingList){
                        votes[x.id!!.toInt()-1] ++
                    }
                    for(i in votes.indices){
                        listData[i].votes = votes[i].toString()
                    }
                    setData(listData)
                }
            }
        }
    }

    private fun getVotingDataXML(){
        MainActivity.scope.launch {
            withContext(MainActivity.scope.coroutineContext) {
                val rest = DataSource()
                withContext(Dispatchers.Main) {
                    val votingList = rest.district3.getFeed()
                    Log.i("LOG XML", votingList.toString())
                    for(i in 0 until votingList.parties.size){
                        listData[i].votes = votingList.parties[i].votes.toString()
                    }
                    setData(listData)
                }
            }
        }
    }

    fun getData() : MutableLiveData<List<AlpacaParty>>{
        return liveData
    }

    private fun setData(data : List<AlpacaParty>) {
        liveData.value = data
        listData = data
    }
}