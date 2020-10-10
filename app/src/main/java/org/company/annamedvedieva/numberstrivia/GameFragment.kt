package org.company.annamedvedieva.numberstrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import org.company.annamedvedieva.numberstrivia.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    data class Question(
        val image: Int,
        val text: String,
        val answers: List<String>)

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
        Question(image = R.drawable.number2, text = "How many lions do you see?",
            answers = listOf("2", "3", "4", "5")),
        Question(image = R.drawable.number3, text = "How many camels do you see?",
            answers = listOf("3", "2", "4", "5")),
        Question(image = R.drawable.number4, text = "How many elephants are on the picture?",
            answers = listOf("4", "3", "5", "6")),
        Question(image = R.drawable.number5, text = "Count the leopards!",
            answers = listOf("5", "4", "6", "7")),
        Question(image = R.drawable.number6, text = "What is the number of zebras?",
            answers = listOf("6", "5", "7", "8")),
        Question(image = R.drawable.number9, text = "How many hippos are on the picture?",
        answers = listOf("9", "10", "7", "8"))
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    var image: Int = R.drawable.ic_panorama_black_24dp
    private var questionIndex = 0
    private val numQuestions = questions.size

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding: FragmentGameBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToWinFragment())
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
                }
            }
        }
        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {

        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        image = currentQuestion.image
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_trivia_question, questionIndex + 1, numQuestions)

    }
}