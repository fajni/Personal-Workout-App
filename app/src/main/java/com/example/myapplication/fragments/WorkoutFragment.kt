package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class WorkoutFragment : Fragment() {

    private lateinit var workoutBtnDelete: Button
    private lateinit var workoutBtnUpdate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        workoutBtnDelete = view.findViewById(R.id.btnWorkoutDelete)
        workoutBtnUpdate = view.findViewById(R.id.btnWorkoutUpdate)

        workoutBtnDelete.setBackgroundColor(resources.getColor(R.color.black))
        workoutBtnUpdate.setBackgroundColor(resources.getColor(R.color.black))

        workoutBtnDelete.setOnClickListener {
            Toast.makeText(context, "Not implemented!", Toast.LENGTH_SHORT).show()
        }

        workoutBtnUpdate.setOnClickListener {
            Toast.makeText(context, "Not implemented!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}