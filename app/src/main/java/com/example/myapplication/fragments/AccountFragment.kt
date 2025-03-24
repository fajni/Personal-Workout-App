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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.models.AccountData
import com.example.myapplication.data.viewmodel.AccountViewModel

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel

    private lateinit var accountName: EditText
    private lateinit var accountCalories: EditText
    private lateinit var accountProteins: EditText
    private lateinit var accountCarbs: EditText
    private lateinit var accountFats: EditText
    private lateinit var accountMaxBenchKg: EditText
    private lateinit var accountMaxBenchReps: EditText
    private lateinit var accountMaxDeadliftKg: EditText
    private lateinit var accountMaxDeadliftReps: EditText
    private lateinit var accountMaxSquadKg: EditText
    private lateinit var accountMaxSquadReps: EditText
    private lateinit var accountCurrentWeight: EditText
    private lateinit var accountUpdateDate: EditText

    private lateinit var accountBtnDelete: Button
    private lateinit var accountBtnUpdateAdd: Button

    private fun emptyFields(): Boolean {

        if(
            accountName.text.isBlank() ||
            accountCalories.text.isBlank() ||
            accountProteins.text.isBlank() ||
            accountCarbs.text.isBlank() ||
            accountFats.text.isBlank() ||
            accountMaxBenchKg.text.isBlank() ||
            accountMaxBenchReps.text.isBlank() ||
            accountMaxDeadliftKg.text.isBlank() ||
            accountMaxDeadliftReps.text.isBlank() ||
            accountMaxSquadKg.text.isBlank() ||
            accountMaxSquadReps.text.isBlank() ||
            accountCurrentWeight.text.isBlank() ||
            accountUpdateDate.text.isBlank()
        ){
            return false
        }
        return true
    }

    private fun clearFields() {

        accountName.setText("")
        accountCalories.setText("")
        accountProteins.setText("")
        accountProteins.setText("")
        accountCarbs.setText("")
        accountFats.setText("")
        accountMaxBenchKg.setText("")
        accountMaxBenchReps.setText("")
        accountMaxDeadliftKg.setText("")
        accountMaxDeadliftReps.setText("")
        accountMaxSquadKg.setText("")
        accountMaxSquadReps.setText("")
        accountCurrentWeight.setText("")
        accountUpdateDate.setText("")
    }

    private fun setFieldsUpdateAccount(account: AccountData) {

        accountName.setText(account.name)
        accountCalories.setText(account.calories.toString() + " kcal")
        accountProteins.setText(account.proteins.toString() + " g")
        accountCarbs.setText(account.carbs.toString() + " g")
        accountFats.setText(account.fats.toString() + " g")
        accountMaxBenchKg.setText(account.maxBenchKg.toString() + " kg")
        accountMaxBenchReps.setText(account.maxBenchReps.toString() + " reps")
        accountMaxDeadliftKg.setText(account.maxDeadliftKg.toString() + " kg")
        accountMaxDeadliftReps.setText(account.maxDeadliftReps.toString() + " reps")
        accountMaxSquadKg.setText(account.maxSquadKg.toString() + " kg")
        accountMaxSquadReps.setText(account.maxSquadReps.toString() + " reps")
        accountCurrentWeight.setText(account.currentWeight.toString() + " kg")
        accountUpdateDate.setText(account.accountDate)

        // UPDATE BTN
        accountBtnUpdateAdd.setText("Update")
        accountBtnUpdateAdd.setBackgroundColor(resources.getColor(R.color.discord_blue))

        accountBtnUpdateAdd.setOnClickListener {

            if(emptyFields()) {

                // id = 0 -> Room library will do the id (primary key) numbering
                var myAccount: AccountData = AccountData(
                    id = 0,
                    name = accountName.text.toString(),
                    calories = accountCalories.text.toString().replace(" kcal", "").toInt(),
                    proteins = accountProteins.text.toString().replace(" g", "").toInt(),
                    carbs = accountCarbs.text.toString().replace(" g", "").toInt(),
                    fats = accountFats.text.toString().replace(" g", "").toInt(),
                    maxBenchKg = accountMaxBenchKg.text.toString().replace(" kg", "").toInt(),
                    maxBenchReps = accountMaxBenchReps.text.toString().replace(" reps", "").toInt(),
                    maxDeadliftKg = accountMaxDeadliftKg.text.toString().replace(" kg", "").toInt(),
                    maxDeadliftReps = accountMaxDeadliftReps.text.toString().replace(" reps", "").toInt(),
                    maxSquadKg = accountMaxSquadKg.text.toString().replace(" kg", "").toInt(),
                    maxSquadReps = accountMaxSquadReps.text.toString().replace(" reps", "").toInt(),
                    currentWeight = accountCurrentWeight.text.toString().replace(" kg", "").toInt(),
                    accountDate = accountUpdateDate.text.toString()
                )

                try {

                    accountViewModel.updateAccount(myAccount)
                    Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    Toast.makeText(context, "Error occurred!", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun insertAccount() {

        // ADD BTN
        accountBtnUpdateAdd.setText("ADD")
        accountBtnUpdateAdd.setBackgroundColor(resources.getColor(R.color.green))

        accountBtnUpdateAdd.setOnClickListener {

            if(emptyFields()) {

                // id = 0 -> Room library will do the id (primary key) numbering
                var myAccount: AccountData = AccountData(
                    id = 0,
                    name = accountName.text.toString(),
                    calories = accountCalories.text.toString().toInt(),
                    proteins = accountProteins.text.toString().toInt(),
                    carbs = accountCarbs.text.toString().toInt(),
                    fats = accountFats.text.toString().toInt(),
                    maxBenchKg = accountMaxBenchKg.text.toString().toInt(),
                    maxBenchReps = accountMaxBenchReps.text.toString().toInt(),
                    maxDeadliftKg = accountMaxDeadliftKg.text.toString().toInt(),
                    maxDeadliftReps = accountMaxDeadliftReps.text.toString().toInt(),
                    maxSquadKg = accountMaxSquadKg.text.toString().toInt(),
                    maxSquadReps = accountMaxSquadReps.text.toString().toInt(),
                    currentWeight = accountCurrentWeight.text.toString().toInt(),
                    accountDate = accountUpdateDate.text.toString()
                )

                try {

                    accountViewModel.addAccount(myAccount)
                    Toast.makeText(context, "Successfully added!", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    Toast.makeText(context, "Error occurred!", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteAccount(account: AccountData) {

        if(account == null) {
            Toast.makeText(context, "Cannot delete null account!", Toast.LENGTH_SHORT).show()
            return
        }

        val builder = AlertDialog.Builder(context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        accountBtnDelete.setOnClickListener {

            builder.setMessage("Are you sure you want to delete account data?")
                .setCancelable(true)
                .setTitle("Delete Account " + account.name!!.uppercase())

                .setPositiveButton(positiveSpan) { dialog, id ->

                    Toast.makeText(context, "Account deleted!", Toast.LENGTH_SHORT).show()

                    accountViewModel.deleteAccount(account)

                    clearFields()
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_account, container, false)

        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        accountName = view.findViewById(R.id.accountName)
        accountCalories = view.findViewById(R.id.accountCalories)
        accountProteins = view.findViewById(R.id.accountProteins)
        accountCarbs = view.findViewById(R.id.accountCarbs)
        accountFats = view.findViewById(R.id.accountFats)
        accountMaxBenchKg = view.findViewById(R.id.accountBenchKg)
        accountMaxBenchReps = view.findViewById(R.id.accountBenchReps)
        accountMaxDeadliftKg = view.findViewById(R.id.accountDeadliftKg)
        accountMaxDeadliftReps = view.findViewById(R.id.accountDeadliftReps)
        accountMaxSquadKg = view.findViewById(R.id.accountSquadKg)
        accountMaxSquadReps = view.findViewById(R.id.accountSquadReps)
        accountCurrentWeight = view.findViewById(R.id.accountKg)
        accountUpdateDate = view.findViewById(R.id.accountDate)

        accountBtnDelete = view.findViewById(R.id.btnAccountDelete)
        accountBtnUpdateAdd = view.findViewById(R.id.btnAccountUpdateAdd)

        accountViewModel.readAccount.observe(viewLifecycleOwner, Observer { account ->

            if (account == null) {
                Toast.makeText(context, "Please enter account data!", Toast.LENGTH_SHORT).show()
                insertAccount()

            } else {
                //Toast.makeText(context, "You can update data!", Toast.LENGTH_SHORT).show()
                setFieldsUpdateAccount(account!!)
                deleteAccount(account)
            }

        })

        return view
    }
}