package com.example.myapplication.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.models.WorkoutData
import com.example.myapplication.data.viewmodel.WorkoutViewModel
import com.example.myapplication.utils.CurrentDate

class WorkoutFragment : Fragment() {

    private lateinit var workoutViewModel: WorkoutViewModel

    private lateinit var monday: LinearLayout
    private lateinit var mondayWorkoutTitle: TextView
    private lateinit var tuesday: LinearLayout
    private lateinit var tuesdayWorkoutTitle: TextView
    private lateinit var wednesday: LinearLayout
    private lateinit var wednesdayWorkoutTitle: TextView
    private lateinit var thursday: LinearLayout
    private lateinit var thursdayWorkoutTitle: TextView
    private lateinit var friday: LinearLayout
    private lateinit var fridayWorkoutTitle: TextView
    private lateinit var saturday: LinearLayout
    private lateinit var saturdayWorkoutTitle: TextView
    private lateinit var sunday: LinearLayout
    private lateinit var sundayWorkoutTitle: TextView

    private lateinit var selectDayText: TextView
    private lateinit var scrollView: ScrollView
    private lateinit var day: TextView
    private lateinit var createdAt: TextView
    private lateinit var title: EditText
    private lateinit var muscle: EditText
    private lateinit var note: EditText

    private lateinit var workoutBtnDelete: Button
    private lateinit var workoutBtnUpdate: Button

    private var workoutsList: ArrayList<WorkoutData> = ArrayList()


    private fun initializeResources(view: View) {

        workoutViewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        monday = view.findViewById(R.id.monday)
        mondayWorkoutTitle = view.findViewById(R.id.mondayTraining)
        tuesday = view.findViewById(R.id.tuesday)
        tuesdayWorkoutTitle = view.findViewById(R.id.tuesdayTraining)
        wednesday = view.findViewById(R.id.wednesday)
        wednesdayWorkoutTitle = view.findViewById(R.id.wednesdayTraining)
        thursday = view.findViewById(R.id.thursday)
        thursdayWorkoutTitle = view.findViewById(R.id.thursdayTraining)
        friday = view.findViewById(R.id.friday)
        fridayWorkoutTitle = view.findViewById(R.id.fridayTraining)
        saturday = view.findViewById(R.id.saturday)
        saturdayWorkoutTitle = view.findViewById(R.id.saturdayTraining)
        sunday = view.findViewById(R.id.sunday)
        sundayWorkoutTitle = view.findViewById(R.id.sundayTraining)

        selectDayText = view.findViewById(R.id.selectDayForMoreDetailsText)
        scrollView = view.findViewById(R.id.workoutScrollView)
        day = view.findViewById(R.id.workoutDay)
        createdAt = view.findViewById(R.id.workoutCreatedAt)
        title = view.findViewById(R.id.workoutTitle)
        muscle = view.findViewById(R.id.workoutMuscle)
        note = view.findViewById(R.id.workoutNote)

        workoutBtnDelete = view.findViewById(R.id.btnWorkoutDelete)
        workoutBtnUpdate = view.findViewById(R.id.btnWorkoutUpdate)

        workoutBtnDelete.isVisible = false
        workoutBtnUpdate.isVisible = false
    }


    private fun clearFields(selectedDay: String) {

        when(selectedDay) {
            "monday" -> mondayWorkoutTitle.setText("/")
            "tuesday" -> tuesdayWorkoutTitle.setText("/")
            "wednesday" -> wednesdayWorkoutTitle.setText("/")
            "thursday" -> thursdayWorkoutTitle.setText("/")
            "friday" -> fridayWorkoutTitle.setText("/")
            "saturday" -> saturdayWorkoutTitle.setText("/")
            "sunday" -> sundayWorkoutTitle.setText("/")
        }

        createdAt.setText("/")
        title.setText("")
        muscle.setText("")
        note.setText("")

    }

    private fun toggleScrollViewAndSelectedDay(selectedDay: LinearLayout) {

        monday.setBackgroundResource(R.drawable.item_border)
        tuesday.setBackgroundResource(R.drawable.item_border)
        wednesday.setBackgroundResource(R.drawable.item_border)
        thursday.setBackgroundResource(R.drawable.item_border)
        friday.setBackgroundResource(R.drawable.item_border)
        saturday.setBackgroundResource(R.drawable.item_border)
        sunday.setBackgroundResource(R.drawable.item_border)

        selectedDay.isSelected = !selectedDay.isSelected

        if(selectedDay.isSelected) {

            selectedDay.setBackgroundResource(R.drawable.progress_table_green_border)

            selectDayText.isVisible = false
            scrollView.isVisible = true

            workoutBtnDelete.isVisible = true
            workoutBtnUpdate.isVisible = true

        } else {

            selectedDay.setBackgroundResource(R.drawable.item_border)

            selectDayText.isVisible = true
            scrollView.isVisible = false

            workoutBtnDelete.isVisible = false
            workoutBtnUpdate.isVisible = false
        }

    }

    private fun updateAddData() {

        if(muscle.text.isBlank() || title.text.isBlank() || !scrollView.isVisible){

            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show()
            return
        }
        else {

            val workout = WorkoutData(0, day.text.toString(), muscle.text.toString(), title.text.toString(), CurrentDate().getCurrentData(), note.text.toString())

            if(workoutBtnUpdate.text.equals("ADD")){

                workoutViewModel.addWorkout(workout)

                Toast.makeText(context, "Added: " + workout.workoutTitle, Toast.LENGTH_SHORT).show()
            }

            if(workoutBtnUpdate.text.equals("UPDATE")){

                workoutViewModel.updateWorkout(workout)

                Toast.makeText(context, "Updated: " + workout.workoutTitle, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteData(workout: WorkoutData) {

        if(workout.equals(null) || title.text.isBlank() || muscle.text.isBlank()) {

            Toast.makeText(context, "workout null", Toast.LENGTH_SHORT).show()
            return
        }

        val builder = AlertDialog.Builder(context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        workoutBtnDelete.setOnClickListener {

            builder.setMessage("Are you sure you want to delete workout data?")
                .setCancelable(true)
                .setTitle("Delete Workout " + workout.workoutTitle.uppercase())

                .setPositiveButton(positiveSpan) { dialog, id ->

                    Toast.makeText(context, "Workout deleted!", Toast.LENGTH_SHORT).show()

                    try {

                        clearFields(workout.day)
                        workoutViewModel.deleteWorkout(workout)

                        workoutBtnUpdate.setText("ADD")
                        workoutBtnUpdate.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.green, null))

                    } catch (e: Exception) {

                        throw e
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss() }
                .show()
        }
    }

    private fun setData(selectedDay: String) {

        day.text = selectedDay

        workoutBtnUpdate.setText("ADD")
        workoutBtnUpdate.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.green, null))

        createdAt.text = "/"
        title.setText("")
        muscle.setText("")
        note.setText("")

        workoutsList.forEach{ workout ->

            if(workout.day == selectedDay){

                createdAt.text = workout.createdAt
                title.setText(workout.workoutTitle)
                muscle.setText(workout.muscle)
                note.setText(workout.note)

                workoutBtnUpdate.setText("UPDATE")
                workoutBtnUpdate.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.discord_blue, null))

                workoutBtnDelete.isClickable = true

                deleteData(workout)
            }
        }

    }

    private fun setOnClickListenerDays() {

        monday.setOnClickListener {

            setData("monday")
            toggleScrollViewAndSelectedDay(monday)
        }

        tuesday.setOnClickListener {

            setData("tuesday")
            toggleScrollViewAndSelectedDay(tuesday)
        }

        wednesday.setOnClickListener {

            setData("wednesday")
            toggleScrollViewAndSelectedDay(wednesday)
        }

        thursday.setOnClickListener {

            setData("thursday")
            toggleScrollViewAndSelectedDay(thursday)
        }

        friday.setOnClickListener {

            setData("friday")
            toggleScrollViewAndSelectedDay(friday)
        }

        saturday.setOnClickListener {

            setData("saturday")
            toggleScrollViewAndSelectedDay(saturday)
        }

        sunday.setOnClickListener {

            setData("sunday")
            toggleScrollViewAndSelectedDay(sunday)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        initializeResources(view)

        workoutViewModel.readWorkouts.observe(viewLifecycleOwner) { workouts ->

            workouts.forEach { workout ->
                workoutsList.add(workout)

                when(workout.day) {
                    "monday" -> mondayWorkoutTitle.setText(workout.workoutTitle)
                    "tuesday" -> tuesdayWorkoutTitle.setText(workout.workoutTitle)
                    "wednesday" -> wednesdayWorkoutTitle.setText(workout.workoutTitle)
                    "thursday" -> thursdayWorkoutTitle.setText(workout.workoutTitle)
                    "friday" -> fridayWorkoutTitle.setText(workout.workoutTitle)
                    "saturday" -> saturdayWorkoutTitle.setText(workout.workoutTitle)
                    "sunday" -> sundayWorkoutTitle.setText(workout.workoutTitle)
                }

            }

            setOnClickListenerDays()
        }

        workoutBtnUpdate.setOnClickListener {
            updateAddData()
        }

        return view
    }
}