package com.vishal.cycleapp.ui.insights

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.vishal.cycleapp.R

class InsightsFragment : Fragment() {

    private lateinit var btnMonthly: TextView
    private lateinit var btnWeekly: TextView
    private lateinit var tvStabilityScore: TextView
    private lateinit var tvMonthRange: TextView
    private lateinit var layoutMonthPicker: LinearLayout

    // Dummy data for Monthly vs Weekly
    private val monthlyScore = "78%"
    private val weeklyScore  = "65%"

    // Currently selected toggle: true = Monthly, false = Weekly
    private var isMonthlySelected = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_insights, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMonthly       = view.findViewById(R.id.btnMonthly)
        btnWeekly        = view.findViewById(R.id.btnWeekly)
        tvStabilityScore = view.findViewById(R.id.tvStabilityScore)
        tvMonthRange     = view.findViewById(R.id.tvMonthRange)
        layoutMonthPicker = view.findViewById(R.id.layoutMonthPicker)

        // Set initial selected state
        setToggleState(isMonthlySelected)

        // Monthly button click
        btnMonthly.setOnClickListener {
            if (!isMonthlySelected) {
                isMonthlySelected = true
                setToggleState(true)
            }
        }

        // Weekly button click
        btnWeekly.setOnClickListener {
            if (isMonthlySelected) {
                isMonthlySelected = false
                setToggleState(false)
            }
        }

        // "4 months" dropdown click
        layoutMonthPicker.setOnClickListener {
            showMonthRangeDialog()
        }
    }

    /**
     * Updates the Monthly/Weekly toggle UI and dummy score.
     */
    private fun setToggleState(isMonthly: Boolean) {
        if (isMonthly) {
            // Monthly = selected (white bg, dark text)
            btnMonthly.setBackgroundColor(Color.WHITE)
            btnMonthly.setTextColor(Color.parseColor("#1A1A2E"))

            // Weekly = unselected (gray bg, gray text)
            btnWeekly.setBackgroundColor(Color.parseColor("#EBEBEB"))
            btnWeekly.setTextColor(Color.parseColor("#9E9E9E"))

            tvStabilityScore.text = monthlyScore
        } else {
            // Weekly = selected
            btnWeekly.setBackgroundColor(Color.WHITE)
            btnWeekly.setTextColor(Color.parseColor("#1A1A2E"))

            // Monthly = unselected
            btnMonthly.setBackgroundColor(Color.parseColor("#EBEBEB"))
            btnMonthly.setTextColor(Color.parseColor("#9E9E9E"))

            tvStabilityScore.text = weeklyScore
        }
    }

    /**
     * Shows a simple AlertDialog with month range options.
     */
    private fun showMonthRangeDialog() {
        val options = arrayOf("1 month", "2 months", "3 months", "4 months", "6 months", "12 months")

        AlertDialog.Builder(requireContext())
            .setTitle("Select Range")
            .setItems(options) { dialog, which ->
                tvMonthRange.text = options[which]
                dialog.dismiss()
            }
            .show()
    }
}