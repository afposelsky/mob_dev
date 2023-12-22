package com.example.registration.database

import android.provider.BaseColumns

object DBNameClass: BaseColumns {
    const val TABLE_NAME = "ShoppingList"
    const val ELEMENT_NAME = "ElementName"
    const val COUNT = "Value"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "ShList.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$ELEMENT_NAME TEXT," +
            "$COUNT INTEGER)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}