package br.com.pdm.bookbycode.database

import android.content.Context
import br.com.pdm.bookbycode.model.Book

/**
 * Created by raimundo on 25/02/18.
 */

class FacadeBook {
    private val BOOK_ATRIBUTTES = 2

    private lateinit var dao_book : DAOBook

    constructor(contexto: Context) {
        dao_book = DAOBook(contexto)
    }

    fun delete(book : Book){
        dao_book.delete(book)
    }

    fun updateList(){
    }

    private fun spliltString(book_date : String):List<String> {
        val reg = Regex("(?<=[|])|(?=[|])")
        val list = book_date.split(reg)
        val result = ArrayList<String>()

        for (item in list)
            if(!item.contains("|"))
                result.add(item.trim())

        return result;
    }

    public fun addBook(book_date : String):Boolean{
        var list = spliltString(book_date)
//        for (item in list)
//            Log.i("APP",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + item)

        if (list.size == BOOK_ATRIBUTTES){
            var book = Book(list[0], list[1])
            dao_book.insert(book)
            return true
        }
        return false
    }

    public fun bookList(): List<Book>{
        return dao_book.select();
    }
}