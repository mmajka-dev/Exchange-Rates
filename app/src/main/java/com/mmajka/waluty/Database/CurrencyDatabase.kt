package com.mmajka.waluty.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(AllCurrenciesLocal::class, FavoritesLocal::class), version = 1, exportSchema = false)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun currencyDao(): Dao

    companion object{
        private var instance: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase?{
            if(instance == null){
                instance = Room.databaseBuilder(context, CurrencyDatabase::class.java, "note_table").fallbackToDestructiveMigration().build()
            }

            return instance
        }
    }
    fun deleteInstance(){
        instance == null
    }
}