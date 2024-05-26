package com.glucode.about_you.about.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewQuestionCardBinding
import com.glucode.about_you.engineers.models.Question

class QuestionCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding: ViewQuestionCardBinding =
        ViewQuestionCardBinding.inflate(LayoutInflater.from(context), this)

    var question: Question? = null
        set(value) {
            field = value
            value?.let {
                questionText = it.questionText
                answers = it.answerOptions
                selection = it.answer.index
            }
        }

    var questionText: String? = null
        set(value) {
            field = value
            binding.title.text = value
        }

    var answers: List<String> = listOf()
        set(value) {
            field = value
            binding.answers.removeAllViews()
            value.forEachIndexed { index, answer ->
                addAnswer(answer, index)
            }
        }

    var selection: Int? = null
        set(value) {
            field = value
            value?.let {
                if (it >= 0 && it < binding.answers.childCount) {
                    binding.answers.children.elementAt(it).isSelected = true
                }
            }
        }

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
        binding.title.setTextColor(ContextCompat.getColor(context, R.color.white))
    }

    private fun addAnswer(answer: String, index: Int) {
        val answerView = AnswerCardView(context).apply {
            title = answer
            setOnClickListener { onAnswerClick(this, index) }
        }
        binding.answers.addView(answerView)
    }

    private fun onAnswerClick(view: View, index: Int) {
        if (!view.isSelected) {
            binding.answers.children.forEach { it.isSelected = false }
            view.isSelected = true
            selection = index
        }
    }

    private fun setSelection() {

    }
}