package com.example.sfsep.vaccins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.sfsep.R
import com.example.sfsep.generalModel.MenuManager
import com.example.sfsep.generalModel.ResourcesManager

class VaccinAssistantExpert : AppCompatActivity() {
    val menuManager = MenuManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccin_assistant_expert)

        supportActionBar!!.title = getString(R.string.vaccin_assistant)
        ResourcesManager.context = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuManager.manageMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun vhbSelectionChanged(sender: View) {

    }

    fun vhaSelectionChanged(sender:View) {

    }
    fun vzvSelectionChanged(sender:View) {

    }
    fun expert_nextButtonTouched(sender:View) {

    }
}