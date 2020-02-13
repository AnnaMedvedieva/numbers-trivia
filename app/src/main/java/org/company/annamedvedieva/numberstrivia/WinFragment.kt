package org.company.annamedvedieva.numberstrivia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import org.company.annamedvedieva.numberstrivia.databinding.FragmentGameOverBinding
import org.company.annamedvedieva.numberstrivia.databinding.FragmentWinBinding


class WinFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentWinBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_win, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = null

        binding.nextMatchButton.setOnClickListener { v: View ->
            v.findNavController().navigate(WinFragmentDirections.actionWinFragmentToGameFragment())
        }

        return binding.root
    }

}
