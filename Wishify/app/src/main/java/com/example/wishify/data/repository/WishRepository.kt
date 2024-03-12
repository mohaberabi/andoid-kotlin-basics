package com.example.wishify.data.repository

import com.example.wishify.data.db.WishDao
import com.example.wishify.data.model.WishItem
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {


    suspend fun addWish(wish: WishItem) = wishDao.addWish(wish)
    suspend fun deleteWish(wish: WishItem) = wishDao.deleteWish(wish)
    suspend fun updateWish(wish: WishItem) = wishDao.updateWish(wish)
    fun getWishById(id: String): Flow<WishItem> = wishDao.getWishByd(id)
    fun listenToWishes(): Flow<List<WishItem>> = wishDao.listenToWishes()

}