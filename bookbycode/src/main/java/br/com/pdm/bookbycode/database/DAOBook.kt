package br.com.pdm.bookbycode.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.com.pdm.bookbycode.model.Book

/**
 * Created by raimundo on 25/02/18.
 */
class DAOBook {
    lateinit var bancoHelper: DatabaseManager
    val TABELA = "book"

    constructor(contexto: Context){
        this.bancoHelper = DatabaseManager(contexto)
    }

    fun insert(p: Book){
        val banco = this.bancoHelper.writableDatabase // SQLiteDabatase
        val cv = ContentValues()
        cv.put("title", p.title)
        cv.put("url_book", p.url_book)
        banco.insert(TABELA, null, cv)
    }

    fun select(): List<Book>{
        val lista = arrayListOf<Book>()
        val banco = this.bancoHelper.readableDatabase
        val colunas = arrayOf("id", "title", "url_book")
        val c = banco.query(TABELA, colunas, null, null, null, null, null)

        c.moveToFirst()

        do{
            // recuperar id, nome, idade
            val id = c.getInt(c.getColumnIndex("id"))
            val title = c.getString(c.getColumnIndex("title"))
            val url_book = c.getString(c.getColumnIndex("url_book"))

            // instanciar uma pessoa
            val p = Book(id, title, url_book)
            Log.i("APP", p.toString())

            // add book na lista
            lista.add(p)
        }while(c.moveToNext())

        return lista
    }

    fun delete(id: Int){

    }

    fun update(p: Book){

    }
}