package com.example.animalfarm

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val model: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val spinner: Spinner = findViewById(R.id.spinner)
        model.getPartyData()

        ArrayAdapter.createFromResource(
            this,
            R.array.districts,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

        val nameObserver = Observer<List<AlpacaParty>> {newData ->
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.itemAnimator = (DefaultItemAnimator())
            recyclerView.adapter = PartyAdapter(newData)
        }
        model.getData().observe(this, nameObserver)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, district: Int, id: Long) {
        scope.launch {
            withContext(scope.coroutineContext) {
                withContext(Dispatchers.Main) {
                    model.getVotingData(district)
                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    companion object {
        var scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
}