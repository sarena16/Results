package com.example.reasultactivity1
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_results.*
//

class ResultsActivity : AppCompatActivity() {

    private lateinit var adapterGame1: ResultsAdapter
    private lateinit var adapterGame2: ResultsAdapter
    private lateinit var adapterGame3: ResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        // Simulated results for testing
        val resultsGame1 = listOf1(
            Result("1", 100, 12000),
            Result("2", 90, 15000),
            Result("3", 80, 18000)
        )

        val resultsGame2 = listOf1(
            Result("4", 200, 25000),
            Result("5", 180, 28000),
            Result("6", 150, 30000)
        )

        val resultsGame3 = listOf1(
            javax.xml.transform.Result("7", 300, 32000),
            Result("8", 280, 35000),
            Result("9", 270, 38000)
        )

        // Configure RecyclerViews
        recyclerViewGame1.layoutManager = LinearLayoutManager(this)
        recyclerViewGame2.layoutManager = LinearLayoutManager(this)
        recyclerViewGame3.layoutManager = LinearLayoutManager(this)

        // Initialize adapters with simulated data
        adapterGame1 = ResultsAdapter(resultsGame1)
        adapterGame2 = ResultsAdapter(resultsGame2)
        adapterGame3 = ResultsAdapter(resultsGame3)

        // Set adapters to RecyclerViews
        recyclerViewGame1.adapter = adapterGame1
        recyclerViewGame2.adapter = adapterGame2
        recyclerViewGame3.adapter = adapterGame3
    }
}
