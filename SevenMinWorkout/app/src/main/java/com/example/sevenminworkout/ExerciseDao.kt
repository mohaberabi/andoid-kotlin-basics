package com.example.sevenminworkout

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface ExerciseDao {
    @Insert
    suspend fun addExercise(exercise: ExerciseModel)


    @Update
    suspend fun updateExercise(exercise: ExerciseModel)

    @Delete
    suspend fun deleteExercise(exercise: ExerciseModel)

    @Query("SELECT * FROM 'exercises'")
    fun getExercises(): Flow<List<ExerciseModel>>

    @Query("SELECT * FROM 'exercises' WHERE id=:id")

    suspend fun getExerciseById(id: Int): ExerciseModel

}