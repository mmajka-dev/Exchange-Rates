package com.mmajka.waluty.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "allCurrenciesLocal")
class AllCurrenciesLocal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "update_date") val updateDate: String?,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "currency") val currency: String?,
    @ColumnInfo(name = "mid") val mid: Float?
)

@Entity(tableName = "favoritesLocal")
class FavoritesLocal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "update_date") val updateDate: String?,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "currency") val currency: String?,
    @ColumnInfo(name = "mid") val mid: Float?
)