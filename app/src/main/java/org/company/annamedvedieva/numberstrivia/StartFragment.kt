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
import org.company.annamedvedieva.numberstrivia.databinding.FragmentStartBinding


class StartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentStartBinding = DataBindingUtil.
            inflate(inflater, R.layout.fragment_start, container,false)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.game_title)

        binding.playButton.setOnClickListener { v: View ->
            v.findNavController().navigate(StartFragmentDirections.actionStartFragmentToGameFragment())
        }
        return binding.root
    }


}
