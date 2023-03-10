package com.test.mvctest.Model

interface IUser {
    fun getEmail() : String?
    fun getPassword() : String?
    fun isValid() : Int
}