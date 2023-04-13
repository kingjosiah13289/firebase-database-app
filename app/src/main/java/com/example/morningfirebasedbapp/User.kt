package com.example.morningfirebasedbapp

class User {
    var name:String = ""
    var email:String = ""
    var idnumber:String = ""
    var id:String = ""

    constructor(name: String, email: String, idnumber: String, id: String) {
        this.name = name
        this.email = email
        this.idnumber = idnumber
        this.id = id
    }

    constructor()

}