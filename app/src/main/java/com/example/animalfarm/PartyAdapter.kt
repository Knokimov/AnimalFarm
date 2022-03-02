package com.example.animalfarm

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PartyAdapter(private val parties : List<AlpacaParty>) :
    RecyclerView.Adapter<PartyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val color: View = view.findViewById(R.id.relativeLayout)
        val name: TextView = view.findViewById(R.id.partyName)
        val image: ImageView = view.findViewById(R.id.imageView)
        val leader: TextView = view.findViewById(R.id.leader)
        var votes: TextView = view.findViewById(R.id.votes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(parties[position].img.toString() != ""){
            Glide.with(holder.itemView.context)
                .load(parties[position].img.toString())
                .into(holder.image)
        }
        holder.color.setBackgroundColor(Color.parseColor(parties[position].color.toString()))
        holder.name.text = parties[position].name.toString()
        "Leader : ${parties[position].leader.toString()}".also { holder.leader.text = it }
        "Votes: ${parties[position].votes}".also { holder.votes.text = it }
    }

    override fun getItemCount(): Int {
        return parties.size
    }
}