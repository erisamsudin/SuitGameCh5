package com.afiraz.suitgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.afiraz.suitgame.databinding.ActivityMainBinding
import com.afiraz.suitgame.case.GameInterface
import com.afiraz.suitgame.case.GetGameCase


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val useCase: GameInterface by lazy {
        GetGameCase()
    }

    fun playerDisable() {
        binding.apply {
            ivStoneLeft.isClickable = false
            ivScissorLeft.isClickable = false
            ivPaperLeft.isClickable = false
        }
    }

    fun playerEnable() {
        binding.apply {
            ivStoneLeft.isClickable = true
            ivScissorLeft.isClickable = true
            ivPaperLeft.isClickable = true
            ivScissorLeft.setBackgroundColor(Color.parseColor("#ffffff"))
            ivPaperLeft.setBackgroundColor(Color.parseColor("#ffffff"))
            ivStoneLeft.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tvWinner.text = ""
            ivScissorRight.setBackgroundColor(Color.parseColor("#ffffff"))
            ivPaperRight.setBackgroundColor(Color.parseColor("#ffffff"))
            ivStoneRight.setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }

    private fun initButton() {
        binding.apply {
            ivStoneLeft.setOnClickListener {
                Log.d(TAG, "initButton: Batu Left Click")
                useCase.gameOn(0)
                Log.d(TAG,"Player One Input Stone")
                playerDisable()
                ivStoneLeft.setBackgroundColor(Color.parseColor("#ff6666"))
                setMoodText()

            }
            ivScissorLeft.setOnClickListener {
                Log.d(TAG, "initButton: Gunting Left Click")
                useCase.gameOn(2)
                Log.d(TAG,"Player One Input Scissor")
                playerDisable()
                ivScissorLeft.setBackgroundColor(Color.parseColor("#ff6666"))
                setMoodText()

            }

            ivPaperLeft.setOnClickListener {
                Log.d(TAG, "initButton: Kertas Left Click")
                useCase.gameOn(1)
                Log.d(TAG,"Player One Input Paper")
                playerDisable()
                ivPaperLeft.setBackgroundColor(Color.parseColor("#ff6666"))
                setMoodText()

            }

            ivRefresh.setOnClickListener {
                playerEnable()
            }
        }
    }



    private fun setMoodText() {
        var splitData = useCase.checkLogic(this).split(",").toTypedArray()
        binding.apply {
            tvWinner.text = splitData[0]
            if (splitData[1] == "0") {
                ivStoneRight.setBackgroundColor(Color.parseColor("#ff6666"))
            } else if (splitData[1] == "1") {
                ivPaperRight.setBackgroundColor(Color.parseColor("#ff6666"))
            } else if (splitData[1] == "2") {
                ivScissorRight.setBackgroundColor(Color.parseColor("#ff6666"))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        initButton()
    }
}