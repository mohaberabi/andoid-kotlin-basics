package com.example.sevenminworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminworkout.databinding.ExerciseItemBinding

class ExerciseAdapter(private val exercises: List<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHodler>() {


    inner class ExerciseViewHodler(binding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val txItem = binding.tvItem


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHodler {


        return ExerciseViewHodler(
            ExerciseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = exercises.size

    override fun onBindViewHolder(holder: ExerciseViewHodler, position: Int) {
        val ex = exercises[position]
        holder.txItem.text = (position + 1).toString()

        when {
            ex.getSelected() -> {
                holder.txItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circuler_green_bg
                )
            }

            ex.getCompleted() -> {}
            else -> {}

        }
    }
}