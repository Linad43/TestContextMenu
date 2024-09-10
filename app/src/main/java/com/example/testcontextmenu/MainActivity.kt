package com.example.testcontextmenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var inputText: EditText
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var randomBTN: Button
    private var flag: Boolean = false

    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        randomBTN = findViewById(R.id.randomBTN)
        inputText = findViewById(R.id.inputScoreTeacher)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        registerForContextMenu(inputText)
        modify(flag)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.changeMode -> {
                flag = !flag
                modify(flag)
            }

            R.id.exit -> {
                finish()
            }
        }
        return true
    }

    private fun modify(flag: Boolean) {
        if (flag) {
            //inputText.isClickable = false
            //inputText.isEnabled = false
            randomBTN.isClickable = true
            randomBTN.isVisible = true
            toolbar.setSubtitle(R.string.modeTrue)
        } else {
            //inputText.isClickable = true
            //inputText.isEnabled = true
            randomBTN.isClickable = false
            randomBTN.isVisible = false
            toolbar.setSubtitle(R.string.modeFalse)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?,
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.changeColor -> {
                if (flag) {
                    when (inputText.text.toString().toInt()) {
                        in 1..10 -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.red
                            )
                        )

                        in 11..20 -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.orange
                            )
                        )

                        in 21..30 -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.yellow
                            )
                        )

                        in 31..40 -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.green
                            )
                        )

                        in 41..50 -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.blue
                            )
                        )
                    }
                } else {
                    when (inputText.text.toString()) {
                        "1" -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.orange
                            )
                        )

                        "2" -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.yellow
                            )
                        )

                        "3" -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.green
                            )
                        )

                        "4" -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.blue
                            )
                        )

                        "5" -> inputText.setBackgroundColor(
                            ContextCompat.getColor(
                                this,
                                R.color.red
                            )
                        )
                    }
                }
            }
            R.id.exit -> {
                finish()
            }
        }
        return true
    }

    fun randomClick(view: View) {
        inputText.setText((Math.random() * 50).toInt().toString())
    }
}