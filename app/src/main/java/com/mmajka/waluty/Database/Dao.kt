package com.mmajka.waluty.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao


@Dao
interface Dao {

    @Query("SELECT * FROM allCurrenciesLocal")
    fun getAllLocal(): LiveData<MutableList<AllCurrenciesLocal>>

    @Query("SELECT * FROM favoritesLocal")
    fun getFavoritesLocal(): LiveData<List<FavoritesLocal>>

    @Insert
    fun insertItem(allCurrenciesLocal: AllCurrenciesLocal)

    @Insert
    fun setFavorite(allCurrenciesLocal: AllCurrenciesLocal)

    @Delete
    fun unsetFavorite(favoritesLocal: FavoritesLocal)


}