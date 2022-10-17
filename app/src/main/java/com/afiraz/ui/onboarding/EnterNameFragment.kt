package com.afiraz.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.afiraz.suitgame.databinding.FragmentEnterNameBinding
import com.afiraz.suitgame.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [EnterNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterNameFragment : Fragment(),OnFinishNavigateListner {
    // TODO: Rename and change types of parameters

    private  lateinit var binding: FragmentEnterNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterNameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onFinishNavigateListener() {
        val name = binding.etName.text.toString().trim()
        if(name.isEmpty()){
            Toast.makeText(requireContext(),"Harap Isikan Nama",Toast.LENGTH_SHORT).show()
        }else{
            navigateToMenu(name)
        }
    }

    private fun navigateToMenu(name: String) {
        Intent(requireContext(),OptionsActivity::class.java).also {
            it.putExtra("PLAYERNAME",name)
            startActivity(it)
        }
    }

}