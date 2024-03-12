package com.example.sevenminworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminworkout.databinding.ItemHistoryBinding

class HistoryAdapter(private val historyItems: List<ExerciseModel>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    inner class HistoryViewHolder(binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val name = binding.historyItemName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {


        return HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = historyItems.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {


        val history: ExerciseModel = historyItems[position]

        holder.name.text = history.getName()
    }
}