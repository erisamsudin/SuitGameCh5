package com.afiraz.ui.onboarding

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.afiraz.suitgame.MainActivity
import com.afiraz.suitgame.databinding.ActivityOptionsBinding
import com.afiraz.ui.onboarding.OptionsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers


class OptionsActivity : AppCompatActivity() {
    private val binding: ActivityOptionsBinding by lazy {
        ActivityOptionsBinding.inflate(layoutInflater)
    }

    private fun setMenuClickListeners() {
        val playerName = intent.getStringExtra("PLAYERNAME")
        binding.ivVsPlayer.setOnClickListener {
            MainActivity.startActivity(this,true, playerName.toString())
        }
        binding.ivVsCom.setOnClickListener {
            MainActivity.startActivity(this,false,playerName.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        val playerName = intent.getStringExtra("PLAYERNAME")
        binding.tvVsPlayer.text = "$playerName Vs Pemain"
        binding.tvVsCom.text = "$playerName Vs CPU"
        setMenuClickListeners()
        val parentLayout: View = findViewById(R.id.content)
        Snackbar.make(parentLayout, "Selamat Datang $playerName", Snackbar.LENGTH_LONG)
            .setAction("tutup") {

            }
            .show()
    }

    companion object {
        private const val EXTRAS_NAME = "PLAYERNAME"
        fun startActivity(context: Context, name: String) {
            context.startActivity(Intent(context, OptionsActivity::class.java).apply {
                putExtra(EXTRAS_NAME, name)
            })
        }
    }

}