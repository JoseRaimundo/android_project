package br.com.pdm.bookbycode.model

/**
 * Created by raimundo on 25/02/18.
 */
class Book {
    var id: Int
    var title: String
    var url_book: String

    constructor (id: Int, title: String, url_book: String){
        this.id = id
        this.title = title
        this.url_book = url_book
    }

    constructor (nome: String, url_book: String){
        this.id = -1
        this.title = nome
        this.url_book = url_book
    }

    override fun toString(): String {
        return id.toString() + " - " + title + " \n " + url_book
    }
}