package com.glucode.about_you.about

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.views.ProfileCardView

class AboutFragment: Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var profileCardView: ProfileCardView
    private var engineer: Engineer? = null
    private var engineersList: List<Engineer> = MockData.engineers

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            profileCardView.updateProfileImage(it)
            engineer?.profilePictureUri = it

            engineer?.let { updatedEngineer ->
                val index = engineersList.indexOfFirst { it.name == updatedEngineer.name }
                if (index != -1) {
                    engineersList = engineersList.toMutableList().apply { set(index, updatedEngineer) }

                    binding.container.post{
                        binding.container.requestLayout()
                    }

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpProfileCard()
        setUpQuestions()
    }

    private fun setUpProfileCard() {
        profileCardView = ProfileCardView(requireContext()).apply {
            setImagePickerLauncher(imagePickerLauncher)
        }
        binding.container.addView(profileCardView, 0)


        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.firstOrNull { it.name == engineerName }
        engineer?.let {
            profileCardView.bind(it)
        }
    }


    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.first { it.name == engineerName }

        engineer?.questions?.forEach { question ->
            val questionView = QuestionCardView(requireContext()).apply {
                questionText = question.questionText
                answers = question.answerOptions
                selection = question.answer.index
            }
            binding.container.addView(questionView)
        }
    }
}