package com.example.tbc_course_14v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tbc_course_14v2.adapter.MyAdapter
import com.example.tbc_course_14v2.databinding.ActivityMainBinding
import com.example.tbc_course_14v2.models.Content
import com.example.tbc_course_14v2.models.ContentInfo
import com.example.tbc_course_14v2.models.ContentInfo.logoContent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val contentAdapter by lazy {
        MyAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.mainRecycler.adapter = contentAdapter
        contentAdapter.submitList(logoContent.toList())




        binding.floatingAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        contentAdapter.removeClick = {
            removeItem(it)
        }

        contentAdapter.editClick = { name, position ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("edit", true)
            intent.putExtra("name", name.name)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        binding.refresher.setOnRefreshListener {
// ბევრი ვიწვალე მაგრავ ვერანაირად ვერ მოვახერხე რომ ui დაავაფდეიტო ედითის შემდეგ "sumbitList" ფუნქციით
// თვითონ ლისტი იცვლება edit-ის შემდეგ მაგრამ ეკრანზე მაინც ძველი ინფორმაცია რჩება
            contentAdapter.notifyDataSetChanged()
            binding.refresher.isRefreshing = false
        }
    }

    private fun removeItem(it: Content) {
        logoContent = logoContent.toMutableList()
        logoContent.remove(it)
        contentAdapter.submitList(logoContent)
    }


    override fun onRestart() {
        super.onRestart()
        contentAdapter.submitList(logoContent)

    }
}