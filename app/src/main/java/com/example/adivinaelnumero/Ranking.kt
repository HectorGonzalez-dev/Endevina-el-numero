package com.example.adivinaelnumero

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ranking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ranking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rankingTable = findViewById<TableLayout>(R.id.rankingTable)

        if (RankingStore.entries.isEmpty()) {
            val row = TableRow(this)
            val empty = TextView(this).apply {
                text = "Sin puntuaciones todavía."
                gravity = Gravity.CENTER
                setPadding(16, 16, 16, 16)
            }

            val params = TableRow.LayoutParams().apply { span = 3 }
            row.addView(empty, params)
            rankingTable.addView(row)
        } else {
            RankingStore.entries.forEachIndexed { index, entry ->
                val row = TableRow(this)

                val pos = TextView(this).apply {
                    text = "${index + 1}"
                    gravity = Gravity.CENTER
                    setPadding(8, 8, 8, 8)
                }
                val initials = TextView(this).apply {
                    text = entry.initials
                    gravity = Gravity.CENTER
                    setPadding(8, 8, 8, 8)
                }
                val attemptsView = TextView(this).apply {
                    text = entry.attempts.toString()
                    gravity = Gravity.CENTER
                    setPadding(8, 8, 8, 8)
                }

                row.addView(pos)
                row.addView(initials)
                row.addView(attemptsView)
                rankingTable.addView(row)
            }
        }



        // Interface
        val returnButton = findViewById<Button>(R.id.returnButton)

        returnButton.setOnClickListener {
            finish()
        }

    }
}