package com.afiraz.suitgame

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.afiraz.suitgame.databinding.ActivityMainBinding
import com.afiraz.suitgame.case.GameInterface
import com.afiraz.suitgame.case.GetGameCase
import com.afiraz.ui.onboarding.OptionsActivity


class MainActivity : AppCompatActivity() {
    companion object {
        var playerOneName = ""
        var multiplePlayer = false
        var playerOneInput = 0
        var playerTwoInput = 0
        private val TAG = MainActivity::class.java.simpleName
        private const val EXTRAS_MULTIPLAYER_MODE = "EXTRAS_MULTIPLAYER_MODE"
        fun startActivity(context: Context, isUsingMultiPlayer: Boolean, namePlayer: String) {
            context.startActivity(Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRAS_MULTIPLAYER_MODE, isUsingMultiPlayer)
            })
            playerOneName = namePlayer
            multiplePlayer = isUsingMultiPlayer
        }
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val useCase: GameInterface by lazy {
        GetGameCase()
    }

    fun playerOneDisable() {
        binding.apply {
            ivStoneLeft.isClickable = false
            ivScissorLeft.isClickable = false
            ivPaperLeft.isClickable = false
        }
    }

    fun playerTwoDisable() {
        binding.apply {
            ivStoneRight.isClickable = false
            ivScissorRight.isClickable = false
            ivPaperRight.isClickable = false
        }
    }

    fun playerEnable() {
        binding.apply {
            ivStoneLeft.isClickable = true
            ivScissorLeft.isClickable = true
            ivPaperLeft.isClickable = true

            ivScissorLeft.setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.white
                )
            )
            ivPaperLeft.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.white))
            ivStoneLeft.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.white))

            ivScissorRight.setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.white
                )
            )
            ivPaperRight.setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.white
                )
            )
            ivStoneRight.setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.white
                )
            )

            ivStoneRight.visibility = View.VISIBLE
            ivPaperRight.visibility = View.VISIBLE
            ivScissorRight.visibility = View.VISIBLE

            ivStoneLeft.visibility = View.VISIBLE
            ivPaperLeft.visibility = View.VISIBLE
            ivScissorLeft.visibility = View.VISIBLE
            tvWinner.text = ""


        }
    }

    private fun initButton() {
        binding.apply {
            //LEFT
            ivStoneLeft.setOnClickListener {
                Log.d(TAG, "initButton: Batu Left Click")
                playerOneInput = 0
                Log.d(TAG, "Player One Input Stone")
                setLayoutPlayerTwo()
            }
            ivScissorLeft.setOnClickListener {
                Log.d(TAG, "initButton: Gunting Left Click")
                playerOneInput = 2
                Log.d(TAG, "Player One Input Scissor")
                setLayoutPlayerTwo()
            }

            ivPaperLeft.setOnClickListener {
                Log.d(TAG, "initButton: Kertas Left Click")
                playerOneInput = 1
                Log.d(TAG, "Player One Input Paper")
                setLayoutPlayerTwo()
            }
            //LEFT

            if (multiplePlayer) {
                //RIGHT
                ivStoneRight.setOnClickListener {
                    Log.d(TAG, "initButton: Batu Left Click")
                    useCase.gameOn(playerOneInput, multiplePlayer, 0)
                    Log.d(TAG, "Player Two Input Stone")
                    setLogic()
                }
                ivScissorRight.setOnClickListener {
                    Log.d(TAG, "initButton: Gunting Left Click")
                    useCase.gameOn(playerOneInput, multiplePlayer, 2)
                    Log.d(TAG, "Player Two Input Scissor")
                    setLogic()
                }
                ivPaperRight.setOnClickListener {
                    Log.d(TAG, "initButton: Kertas Left Click")
                    useCase.gameOn(playerOneInput, multiplePlayer, 1)
                    Log.d(TAG, "Player Two Input Paper")
                    setLogic()
                }
                //RIGHT
            }

            ivRefresh.setOnClickListener {
                playerEnable()
            }
        }
    }

    private fun setLogic() {
        var splitData = useCase.checkLogic(this).split(",").toTypedArray()
        var messageWinner = ""
        playerTwoDisable()
        binding.apply {
            ivStoneLeft.visibility = View.VISIBLE
            ivPaperLeft.visibility = View.VISIBLE
            ivScissorLeft.visibility = View.VISIBLE

            ivStoneRight.visibility = View.VISIBLE
            ivPaperRight.visibility = View.VISIBLE
            ivScissorRight.visibility = View.VISIBLE

            if (multiplePlayer) {
                when (splitData[0]) {
                    "COM Win" -> messageWinner = "Player Two WIN"
                    "Player Win" -> messageWinner = playerOneName.uppercase() + " WIN"
                    else -> messageWinner = splitData[0]
                }
            } else {
                when (splitData[0]) {
                    "Player Win" -> messageWinner = playerOneName.uppercase() + " WIN"
                    else -> messageWinner = splitData[0]
                }
            }

            tvWinner.text = messageWinner
            if (splitData[1] == "0") {
                ivStoneRight.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.teal_200
                    )
                )
            } else if (splitData[1] == "1") {
                ivPaperRight.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.teal_200
                    )
                )
            } else if (splitData[1] == "2") {
                ivScissorRight.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.teal_200
                    )
                )
            }

            if (splitData[2] == "0") {
                ivStoneLeft.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.teal_200
                    )
                )
            } else if (splitData[2] == "1") {
                ivPaperLeft.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.teal_200
                    )
                )
            } else if (splitData[2] == "2") {
                ivScissorLeft.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.teal_200
                    )
                )
            }
        }

        showDialog(messageWinner)
    }

    private fun showDialog(textWinner: String) {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setTitle("Hasil Permainan")
        dialog.setMessage(textWinner)
        dialog.setIcon(R.drawable.ic_permainan)
        dialog.setCancelable(false)
        dialog.setPositiveButton("MAIN LAGI") { dialogInterface, p1 ->
            playerEnable()
        }
        dialog.setNegativeButton("KEMBALI KE MENU") { dialogInterface, p1 ->
            Intent(this, OptionsActivity::class.java).also {
                it.putExtra("PLAYERNAME", playerOneName)
                startActivity(it)
            }
        }
        dialog.show()
    }

    private fun setLayout() {
        var playerTwoName = ""
        if (multiplePlayer) {
            playerTwoName = "Player Two"
        } else {
            playerTwoName = "CPU"
        }
        binding.apply {
            tvWinner.text = "$playerOneName Silahkan Memilih"
            ivStoneRight.visibility = View.INVISIBLE
            ivPaperRight.visibility = View.INVISIBLE
            ivScissorRight.visibility = View.INVISIBLE
            tvPlayerOne.text = playerOneName
            tvPlayerTwo.text = playerTwoName
        }

    }

    private fun setLayoutPlayerTwo() {
        binding.apply {
            ivStoneRight.isClickable = true
            ivScissorRight.isClickable = true
            ivPaperRight.isClickable = true
        }

        playerOneDisable()
        if (!multiplePlayer) {
            binding.apply {
                ivStoneRight.visibility = View.VISIBLE
                ivPaperRight.visibility = View.VISIBLE
                ivScissorRight.visibility = View.VISIBLE
            }
            useCase.gameOn(playerOneInput, false, 0)
            setLogic()
        } else {
            binding.apply {
                tvWinner.text = "Player Two Silahkan Memilih"
                ivStoneLeft.visibility = View.INVISIBLE
                ivPaperLeft.visibility = View.INVISIBLE
                ivScissorLeft.visibility = View.INVISIBLE

                ivStoneRight.visibility = View.VISIBLE
                ivPaperRight.visibility = View.VISIBLE
                ivScissorRight.visibility = View.VISIBLE
            }


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        setLayout()
        initButton()
    }

}