package com.example.wishify.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wishify.data.model.WishItem
import kotlinx.coroutines.flow.Flow


@Dao
interface WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWish(wish: WishItem)

    @Update
    suspend fun updateWish(wish: WishItem)

    @Delete
    suspend fun deleteWish(wish: WishItem)

    @Query("Select * from 'wishTable'")
    fun listenToWishes(): Flow<List<WishItem>>


    @Query("Select * from 'wishTable' where id=id")
    fun getWishByd(id: String): Flow<WishItem>
}