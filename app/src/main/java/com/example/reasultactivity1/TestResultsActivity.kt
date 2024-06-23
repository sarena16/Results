package com.example.reasultactivity1
// TestResultsActivity.kt
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class TestResultsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var testResultsAdapter: TestResultsAdapter
    private lateinit var database: DatabaseReference

    private val testResultsList = mutableListOf<TestResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_results)

        recyclerView = findViewById(R.id.recyclerViewTestResults)
        recyclerView.layoutManager = LinearLayoutManager(this)
        testResultsAdapter = TestResultsAdapter(testResultsList) { testResult ->
            // Handle item click here, navigate to detail activity or show details
            // For now, let's just print the test name as an example
            println("Clicked on test: ${testResult.testName}")
        }
        recyclerView.adapter = testResultsAdapter

        database = FirebaseDatabase.getInstance().reference
        fetchTestResults()
    }

    private fun fetchTestResults() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val userTestsRef = database.child("users").child(userId ?: "").child("tests")

        userTestsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                testResultsList.clear()
                for (testSnapshot in snapshot.children) {
                    val testResult = testSnapshot.getValue(TestResult::class.java)
                    testResult?.let {
                        testResultsList.add(it)
                    }
                }
                testResultsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }
}
