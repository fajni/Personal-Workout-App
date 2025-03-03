package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.example.myapplication.R

class CaloriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_calories, container, false)

        //TODO: calculations

        val addBtn = view?.findViewById<ImageButton>(R.id.addBtn)

        // Change CaloriesFragment to AddFragment
        addBtn?.setOnClickListener {

            val transaction = parentFragmentManager.beginTransaction()

            transaction.replace(R.id.mainFrameLayout, AddFragment())
            transaction.commit()

            Toast.makeText(context, "Add Fragment", Toast.LENGTH_SHORT).show()

        }

        return view
    }
}