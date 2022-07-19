package com.example.tbc_course_14v2

import android.app.usage.UsageEvents.Event.NONE
import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContentInfo
import android.widget.Toast
import com.example.tbc_course_14v2.adapter.MyAdapter
import com.example.tbc_course_14v2.databinding.ActivityAddBinding
import com.example.tbc_course_14v2.models.Content
import com.example.tbc_course_14v2.models.ContentInfo.logoContent

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private var edit: Boolean = false
    private val getPosition by lazy { intent.extras?.getInt("position") ?: NONE }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        edit = intent.getBooleanExtra("edit", false)
        val getName = intent.getStringExtra("name").toString()


        if(edit){
            binding.apply {
                itemActivityTextView.text = getString(R.string.edit_item)
                nameEditText.apply {
                    setText(getName)
                    isFocusable = false
                }
                binding.saveBtn.setOnClickListener {
                    editItem()
                }

            }

        }else{
            binding.saveBtn.setOnClickListener {
                addItem()
            }
        }



    }



    private fun addItem() {
        logoContent.add(
            Content(
                binding.nameEditText.text.toString(),
                binding.descriptionEditText.text.toString()
            )
        )
        finish()
    }

    private fun editItem() {

        logoContent[getPosition].apply {
            name = binding.nameEditText.text.toString()
            description = binding.descriptionEditText.text.toString()

        }
        finish()



    }

}