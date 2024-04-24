package com.example.diario_personal

import android.app.Application
import com.parse.Parse

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("6oxwpZQDFieesqjABMlFpFWi9hBJBgZqsdi3IH4w")
                .clientKey("LIUUTgkXXL7MWAxS9RaR5t2hs1A93NX6ZIYpBcHt")
                .server("https://parseapi.back4app.com/")
                .build())
    }
}