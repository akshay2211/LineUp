package com.fxn.models

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import java.io.Serializable

/**
 * Created by akshay on 12/05/18.
 */

@Table(name = "NotesTable")
class TextItem() : Serializable, Model() {

    @Column(name = "title")
    var title: String? = ""
    @Column(name = "date")
    var date: String = ""
    @Column(name = "color_type")
    var color: Int = 0

    constructor(title: String, date: String, color: Int) : this() {
        super.getId()
        this.title = title
        this.date = date
        this.color = color
    }
}