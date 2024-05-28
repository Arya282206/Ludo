package com.ex.ludo

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }






        val (players2, players3, players4) = listOf<Button>(
            findViewById(R.id.plyaers2),
            findViewById(R.id.plyaers3),
            findViewById(R.id.plyaers4)
        )
        val (dashboard, gameboard) = listOf<LinearLayout>(
            findViewById(R.id.dashboard),
            findViewById(R.id.gameboard)
        )


        val rrr1 = findViewById<View>(R.id.rrr1)
        val rrr2 = findViewById<View>(R.id.rrr2)
        val rrr3 = findViewById<View>(R.id.rrr3)
        val rrr4 = findViewById<View>(R.id.rrr4)
        val ggg1 = findViewById<View>(R.id.ggg1)
        val ggg2 = findViewById<View>(R.id.ggg2)
        val ggg3 = findViewById<View>(R.id.ggg3)
        val ggg4 = findViewById<View>(R.id.ggg4)
        val yyy1 = findViewById<View>(R.id.yyy1)
        val yyy2 = findViewById<View>(R.id.yyy2)
        val yyy3 = findViewById<View>(R.id.yyy3)
        val yyy4 = findViewById<View>(R.id.yyy4)
        val bbb1 = findViewById<View>(R.id.bbb1)
        val bbb2 = findViewById<View>(R.id.bbb2)
        val bbb3 = findViewById<View>(R.id.bbb3)
        val bbb4 = findViewById<View>(R.id.bbb4)

        val tvdice = findViewById<TextView>(R.id.tvdice)

        val block_red_1 = findViewById<LinearLayout>(R.id.block_red_1)
        val block_red_2 = findViewById<LinearLayout>(R.id.block_red_2)
        val block_red_3 = findViewById<LinearLayout>(R.id.block_red_3)
        val block_red_4 = findViewById<LinearLayout>(R.id.block_red_4)
        val block_green_1 = findViewById<LinearLayout>(R.id.block_green_1)
        val block_green_2 = findViewById<LinearLayout>(R.id.block_green_2)
        val block_green_3 = findViewById<LinearLayout>(R.id.block_green_3)
        val block_green_4 = findViewById<LinearLayout>(R.id.block_green_4)
        val block_blue_1 = findViewById<LinearLayout>(R.id.block_blue_1)
        val block_blue_2 = findViewById<LinearLayout>(R.id.block_blue_2)
        val block_blue_3 = findViewById<LinearLayout>(R.id.block_blue_3)
        val block_blue_4 = findViewById<LinearLayout>(R.id.block_blue_4)
        val block_yellow_1 = findViewById<LinearLayout>(R.id.block_yellow_1)
        val block_yellow_2 = findViewById<LinearLayout>(R.id.block_yellow_2)
        val block_yellow_3 = findViewById<LinearLayout>(R.id.block_yellow_3)
        val block_yellow_4 = findViewById<LinearLayout>(R.id.block_yellow_4)
        val new_game_button = findViewById<Button>(R.id.new_game_button)


        val dice_green = findViewById<ImageView>(R.id.dice_green)
        val dice_red = findViewById<ImageView>(R.id.dice_red)
        val dice_blue = findViewById<ImageView>(R.id.dice_blue)
        val dice_yellow = findViewById<ImageView>(R.id.dice_yellow)

        val Green_Dashboard = findViewById<LinearLayout>(R.id.Green_Dashboard)
        val Blue_Dashboard = findViewById<LinearLayout>(R.id.Blue_Dashboard)
        val select_players_layout = findViewById<LinearLayout>(R.id.select_players_layout)
        val exit_game_button_layout = findViewById<LinearLayout>(R.id.exit_button_layout)

        var paari = 1
        var red_played = 1
        var green_played = 1
        var yellow_played = 1
        var blue_played = 1
        var NumberOfPlayers = 4
        var green1_pakli = 0
        var green2_pakli = 0
        var green3_pakli = 0
        var green4_pakli = 0
        var yellow1_pakli = 0
        var yellow2_pakli = 0
        var yellow3_pakli = 0
        var yellow4_pakli = 0
        var blue1_pakli = 0
        var blue2_pakli = 0
        var blue3_pakli = 0
        var blue4_pakli = 0


        val blocks = Array<LinearLayout?>(88) { null }

        for (i in 0 until 88) {
            blocks[i] = findViewById(resources.getIdentifier("block_$i", "id", packageName))
        }



        val title = findViewById<TextView>(R.id.title)

        new_game_button.setOnClickListener {
            select_players_layout.visibility = View.VISIBLE
            exit_game_button_layout.visibility = View.VISIBLE
            new_game_button.visibility = View.GONE
            title.visibility = View.GONE
        }




        players2.setOnClickListener {
            NumberOfPlayers = 2
            dashboard?.visibility = View.GONE
            gameboard?.visibility = View.VISIBLE
            val views = arrayOf(ggg1, ggg2, ggg3, ggg4, bbb1, bbb2, bbb3, bbb4)
            views.forEach { view ->
                view?.visibility = View.GONE
            }
            Green_Dashboard.visibility = View.GONE
            Blue_Dashboard.visibility = View.GONE
        }



        players3.setOnClickListener {
            NumberOfPlayers = 3
            dashboard?.visibility = View.GONE
            gameboard?.visibility = View.VISIBLE
            val views = arrayOf(bbb1, bbb2, bbb3, bbb4)
            views.forEach { view ->
                view?.visibility = View.GONE
            }
            Blue_Dashboard.visibility = View.GONE
        }


        players4.setOnClickListener {
            NumberOfPlayers = 4
            dashboard?.visibility = View.GONE
            gameboard?.visibility = View.VISIBLE
            val views = arrayOf(bbb1, bbb2, bbb3, bbb4)
            views.forEach { view ->
                view?.visibility = View.GONE
            }
        }



        fun rollDice(diceImageView: ImageView) {
            Glide.with(this)
                .load(R.drawable.dice_rolling)
                .into(diceImageView)

            val mediaPlayer = MediaPlayer.create(diceImageView.context, R.raw.sound_roll_dice)
            mediaPlayer.start()

            val randomNumber = (1..6).random()



            Handler(Looper.getMainLooper()).postDelayed({
                val drawableResource = when (randomNumber) {
                    1 -> R.drawable.dice_1
                    2 -> R.drawable.dice_2
                    3 -> R.drawable.dice_3
                    4 -> R.drawable.dice_4
                    5 -> R.drawable.dice_5
                    else -> R.drawable.dice_6
                }
                Glide.with(this)
                    .load(drawableResource)
                    .into(diceImageView)
                tvdice.text = randomNumber.toString()

                if (NumberOfPlayers == 4) {
                    if (randomNumber != 6) {
                        paari = (paari % 4) + 1
                    }
                }

                if (NumberOfPlayers == 3) {
                    if (randomNumber != 6) {
                        paari = (paari % 3) + 1
                    }
                }

                if (NumberOfPlayers == 2) {
                    if (randomNumber != 6) {
                        paari = if (paari == 1) 3 else 1
                    }
                }

                mediaPlayer.setOnCompletionListener {
                    mediaPlayer.release()
                }

            }, 500)

        }







        dice_red.setOnClickListener {
            if (paari == 1) {
                rollDice(findViewById(R.id.dice_red))
                red_played = 0
            }
        }

        dice_green.setOnClickListener {
            if (paari == 2) {
                rollDice(findViewById(R.id.dice_green))
                green_played = 0
            }
        }

        dice_yellow.setOnClickListener {
            if (paari == 3) {
                rollDice(findViewById(R.id.dice_yellow))
                yellow_played = 0
            }
        }

        dice_blue.setOnClickListener {
            if (paari == 4) {
                rollDice(findViewById(R.id.dice_blue))
                blue_played = 0
            }
        }




        rrr1.setOnClickListener {

            if (red_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)

                if (current_position == "block_red_1") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_red_1
                        val destinationBlock: LinearLayout? = blocks[0]
                        sourceBlock?.removeView(rrr1)
                        destinationBlock?.addView(rrr1)
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos == 51) {
                        next_pos++
                    }
                    if (next_pos == 57) {
                        next_pos = 75
                    }
                    if (next_pos == 58) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 59) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 60) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 61) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 62) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 63) {
                        next_pos -= diceValue
                    }


                    if (next_pos == 76) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 77) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 78) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 79) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 80) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 81) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 82) {
                        next_pos -= diceValue
                    }





                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(rrr1)
                    destinationBlock?.addView(rrr1)




                }


                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)

                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)

                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)

                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)
                        }
                    }
                }







                movePieceIfOverlap(pos_r1, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_r1, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_r1, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_r1, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_r1, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_r1, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_r1, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_r1, pos_y4, yyy4, R.id.block_yellow_4)
                movePieceIfOverlap(pos_r1, pos_b1, bbb1, R.id.block_blue_1)
                movePieceIfOverlap(pos_r1, pos_b2, bbb2, R.id.block_blue_2)
                movePieceIfOverlap(pos_r1, pos_b3, bbb3, R.id.block_blue_3)
                movePieceIfOverlap(pos_r1, pos_b4, bbb4, R.id.block_blue_4)






                movePieceIfOverlap(pos_r3, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_r3, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_r3, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_r3, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_r3, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_r3, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_r3, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_r3, pos_y4, yyy4, R.id.block_yellow_4)
                movePieceIfOverlap(pos_r3, pos_b1, bbb1, R.id.block_blue_1)
                movePieceIfOverlap(pos_r3, pos_b2, bbb2, R.id.block_blue_2)
                movePieceIfOverlap(pos_r3, pos_b3, bbb3, R.id.block_blue_3)
                movePieceIfOverlap(pos_r3, pos_b4, bbb4, R.id.block_blue_4)






                red_played = 1


            }


        }
        rrr2.setOnClickListener {
            if (red_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)

                if (current_position == "block_red_2") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_red_2
                        val destinationBlock: LinearLayout? = blocks[0]
                        sourceBlock?.removeView(rrr2)
                        destinationBlock?.addView(rrr2)
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!


                    if (next_pos == 51) {
                        next_pos++
                    }
                    if (next_pos == 57) {
                        next_pos = 73
                    }
                    if (next_pos == 58) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 59) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 60) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 61) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 62) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 63) {
                        next_pos -= diceValue
                    }

                    if (next_pos == 74) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 75) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 76) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 77) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 78) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 79) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 80) {
                        next_pos -= diceValue
                    }


                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(rrr2)
                    destinationBlock?.addView(rrr2)
                }

                red_played = 1


                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)
                        }
                    }
                }


                movePieceIfOverlap(pos_r2, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_r2, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_r2, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_r2, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_r2, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_r2, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_r2, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_r2, pos_y4, yyy4, R.id.block_yellow_4)
                movePieceIfOverlap(pos_r2, pos_b1, bbb1, R.id.block_blue_1)
                movePieceIfOverlap(pos_r2, pos_b2, bbb2, R.id.block_blue_2)
                movePieceIfOverlap(pos_r2, pos_b3, bbb3, R.id.block_blue_3)
                movePieceIfOverlap(pos_r2, pos_b4, bbb4, R.id.block_blue_4)
            }
        }
        rrr3.setOnClickListener {
            if (red_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)

                if (current_position == "block_red_3") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_red_3
                        val destinationBlock: LinearLayout? = blocks[0]
                        sourceBlock?.removeView(rrr3)
                        destinationBlock?.addView(rrr3)
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos == 51) {
                        next_pos++
                    }
                    if (next_pos == 57) {
                        next_pos = 74
                    }
                    if (next_pos == 58) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 59) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 60) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 61) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 62) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 63) {
                        next_pos -= diceValue
                    }


                    if (next_pos == 75) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 76) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 77) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 78) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 79) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 80) {
                        next_pos -= diceValue
                    }

                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(rrr3)
                    destinationBlock?.addView(rrr3)
                }

                red_played = 1


                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String? = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)
                        }
                    }
                }


                movePieceIfOverlap(pos_r3, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_r3, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_r3, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_r3, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_r3, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_r3, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_r3, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_r3, pos_y4, yyy4, R.id.block_yellow_4)
                movePieceIfOverlap(pos_r3, pos_b1, bbb1, R.id.block_blue_1)
                movePieceIfOverlap(pos_r3, pos_b2, bbb2, R.id.block_blue_2)
                movePieceIfOverlap(pos_r3, pos_b3, bbb3, R.id.block_blue_3)
                movePieceIfOverlap(pos_r3, pos_b4, bbb4, R.id.block_blue_4)





            }
        }


        rrr4.setOnClickListener {
            if (red_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)

                if (current_position == "block_red_4") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_red_4
                        val destinationBlock: LinearLayout? = blocks[0]
                        sourceBlock?.removeView(rrr4)
                        destinationBlock?.addView(rrr4)
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos == 51) {
                        next_pos++
                    }
                    if (next_pos == 57) {
                        next_pos = 72
                    }

                    if (next_pos == 58) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 59) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 60) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 61) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 62) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 63) {
                        next_pos -= diceValue
                    }

                    if (next_pos == 73) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 74) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 75) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 76) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 77) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 78) {
                        next_pos -= diceValue
                    }
                    if (next_pos == 79) {
                        next_pos -= diceValue
                    }


                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(rrr4)
                    destinationBlock?.addView(rrr4)
                }

                red_played = 1

                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)
                        }
                    }
                }

                movePieceIfOverlap(pos_r4, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_r4, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_r4, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_r4, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_r4, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_r4, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_r4, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_r4, pos_y4, yyy4, R.id.block_yellow_4)
                movePieceIfOverlap(pos_r4, pos_b1, bbb1, R.id.block_blue_1)
                movePieceIfOverlap(pos_r4, pos_b2, bbb2, R.id.block_blue_2)
                movePieceIfOverlap(pos_r4, pos_b3, bbb3, R.id.block_blue_3)
                movePieceIfOverlap(pos_r4, pos_b4, bbb4, R.id.block_blue_4)





            }
        }






        ggg1.setOnClickListener {
            if (green_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)

                if (current_position == "block_green_1") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_green_1
                        val destinationBlock: LinearLayout? = blocks[13]
                        sourceBlock?.removeView(ggg1)
                        destinationBlock?.addView(ggg1)
                        green_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (green1_pakli == 0) {
                        if (next_pos >= 52 && next_pos <= 58) {
                            next_pos %= 52
                            green1_pakli = 1
                        }
                    }

                    if (green1_pakli == 1) {
                        val mappings =
                            mapOf(12 to 57, 13 to 58, 14 to 59, 15 to 60, 16 to 61, 62 to 78)

                        mappings[next_pos]?.let { new_pos ->
                            next_pos = new_pos
                        }
                    }

                    if (green1_pakli == 1 && next_pos >= 79 && next_pos <= 85) {
                        next_pos -= diceValue
                    }

                    if (green1_pakli == 1 && next_pos >= 63 && next_pos <= 68) {
                        // Do nothing
                    } else if (next_pos != 13) {
                        val destinationBlock: LinearLayout? = blocks[next_pos]
                        current_block?.removeView(ggg1)
                        destinationBlock?.addView(ggg1)
                    }

                    green_played = 1



                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                green1_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_g1, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_g1, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_g1, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_g1, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_g1, pos_y1, yyy1, R.id.block_yellow_1)
                    movePieceIfOverlap(pos_g1, pos_y2, yyy2, R.id.block_yellow_2)
                    movePieceIfOverlap(pos_g1, pos_y3, yyy3, R.id.block_yellow_3)
                    movePieceIfOverlap(pos_g1, pos_y4, yyy4, R.id.block_yellow_4)
                    movePieceIfOverlap(pos_g1, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_g1, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_g1, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_g1, pos_b4, bbb4, R.id.block_blue_4)



                }
            }
        }


        ggg2.setOnClickListener {
            if (green_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)

                if (current_position == "block_green_2") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_green_2
                        val destinationBlock: LinearLayout? = blocks[13]
                        sourceBlock?.removeView(ggg2)
                        destinationBlock?.addView(ggg2)
                        green_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (green2_pakli == 0) {
                        if (next_pos >= 52 && next_pos <= 58) {
                            next_pos %= 52
                            green2_pakli = 1
                        }
                    }

                    if (green2_pakli == 1) {

                        val mappings =
                            mapOf(12 to 57, 13 to 58, 14 to 59, 15 to 60, 16 to 61, 62 to 79)

                        mappings[next_pos]?.let { new_pos ->
                            next_pos = new_pos
                        }

                    }

                    if (green2_pakli == 1 && next_pos >= 80 && next_pos <= 86) {
                        next_pos -= diceValue
                    }

                    if (green2_pakli == 1 && next_pos >= 63 && next_pos <= 68) {
                        // Do nothing
                    } else if (next_pos != 13) {
                        val destinationBlock: LinearLayout? = blocks[next_pos]
                        current_block?.removeView(ggg2)
                        destinationBlock?.addView(ggg2)
                    }

                    green_played = 1



                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String? = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                green2_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_g2, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_g2, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_g2, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_g2, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_g2, pos_y1, yyy1, R.id.block_yellow_1)
                    movePieceIfOverlap(pos_g2, pos_y2, yyy2, R.id.block_yellow_2)
                    movePieceIfOverlap(pos_g2, pos_y3, yyy3, R.id.block_yellow_3)
                    movePieceIfOverlap(pos_g2, pos_y4, yyy4, R.id.block_yellow_4)
                    movePieceIfOverlap(pos_g2, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_g2, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_g2, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_g2, pos_b4, bbb4, R.id.block_blue_4)

                }
            }
        }



        ggg3.setOnClickListener {
            if (green_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)

                if (current_position == "block_green_3") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_green_3
                        val destinationBlock: LinearLayout? = blocks[13]
                        sourceBlock?.removeView(ggg3)
                        destinationBlock?.addView(ggg3)
                        green_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (green3_pakli == 0) {
                        if (next_pos >= 52 && next_pos <= 58) {
                            next_pos %= 52
                            green3_pakli = 1
                        }
                    }

                    if (green3_pakli == 1) {
                        val mappings =
                            mapOf(12 to 57, 13 to 58, 14 to 59, 15 to 60, 16 to 61, 62 to 77)

                        mappings[next_pos]?.let { new_pos ->
                            next_pos = new_pos
                        }
                    }

                    if (green3_pakli == 1 && next_pos >= 78 && next_pos <= 85) {
                        next_pos -= diceValue
                    }

                    if (green3_pakli == 1 && next_pos >= 63 && next_pos <= 68) {
                        // Do nothing
                    } else if (next_pos != 13) {
                        val destinationBlock: LinearLayout? = blocks[next_pos]
                        current_block?.removeView(ggg3)
                        destinationBlock?.addView(ggg3)
                    }

                    green_played = 1

                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                green3_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_g3, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_g3, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_g3, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_g3, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_g3, pos_y1, yyy1, R.id.block_yellow_1)
                    movePieceIfOverlap(pos_g3, pos_y2, yyy2, R.id.block_yellow_2)
                    movePieceIfOverlap(pos_g3, pos_y3, yyy3, R.id.block_yellow_3)
                    movePieceIfOverlap(pos_g3, pos_y4, yyy4, R.id.block_yellow_4)
                    movePieceIfOverlap(pos_g3, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_g3, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_g3, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_g3, pos_b4, bbb4, R.id.block_blue_4)

                }
            }
        }
        ggg4.setOnClickListener {
            if (green_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)

                if (current_position == "block_green_4") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_green_4
                        val destinationBlock: LinearLayout? = blocks[13]
                        sourceBlock?.removeView(ggg4)
                        destinationBlock?.addView(ggg4)
                        green_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (green4_pakli == 0) {
                        if (next_pos >= 52 && next_pos <= 58) {
                            next_pos %= 52
                            green4_pakli = 1
                        }
                    }

                    if (green4_pakli == 1) {
                        val mappings =
                            mapOf(12 to 57, 13 to 58, 14 to 59, 15 to 60, 16 to 61, 62 to 76)

                        mappings[next_pos]?.let { new_pos ->
                            next_pos = new_pos
                        }
                    }

                    if (green4_pakli == 1 && next_pos >= 77 && next_pos <= 84) {
                        next_pos -= diceValue
                    }

                    if (green4_pakli == 1 && next_pos >= 63 && next_pos <= 68) {

                    } else if (next_pos != 13) {
                        val destinationBlock: LinearLayout? = blocks[next_pos]
                        current_block?.removeView(ggg4)
                        destinationBlock?.addView(ggg4)
                    }

                    green_played = 1

                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                green4_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_g4, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_g4, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_g4, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_g4, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_g4, pos_y1, yyy1, R.id.block_yellow_1)
                    movePieceIfOverlap(pos_g4, pos_y2, yyy2, R.id.block_yellow_2)
                    movePieceIfOverlap(pos_g4, pos_y3, yyy3, R.id.block_yellow_3)
                    movePieceIfOverlap(pos_g4, pos_y4, yyy4, R.id.block_yellow_4)
                    movePieceIfOverlap(pos_g4, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_g4, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_g4, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_g4, pos_b4, bbb4, R.id.block_blue_4)

                }
            }
        }




        yyy1.setOnClickListener {

            if (yellow_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)

                if (current_position == "block_yellow_1") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_yellow_1
                        val destinationBlock: LinearLayout? = blocks[26]
                        sourceBlock?.removeView(yyy1)
                        destinationBlock?.addView(yyy1)
                        yellow_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!


                    if (next_pos >= 52 && next_pos <= 57) {
                        next_pos %= 52
                        yellow1_pakli=1
                    }


                    if (yellow1_pakli==1) {

                        when (next_pos) {
                            25 -> next_pos = 62
                            26 -> next_pos = 63
                            27 -> next_pos = 64
                            28 -> next_pos = 65
                            29 -> next_pos = 66
                            30 -> next_pos = 83
                            67 -> next_pos = 83
                            in 31..37 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 68..74 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 84..90 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                        }
                    }





                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(yyy1)
                    destinationBlock?.addView(yyy1)
                    yellow_played = 1



                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                yellow1_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_y1, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_y1, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_y1, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_y1, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_y1, pos_g1, ggg1, R.id.block_green_1)
                    movePieceIfOverlap(pos_y1, pos_g2, ggg2, R.id.block_green_2)
                    movePieceIfOverlap(pos_y1, pos_g3, ggg3, R.id.block_green_3)
                    movePieceIfOverlap(pos_y1, pos_g4, ggg4, R.id.block_green_4)
                    movePieceIfOverlap(pos_y1, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_y1, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_y1, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_y1, pos_b4, bbb4, R.id.block_blue_4)



                }
            }
        }
        yyy2.setOnClickListener {
            if (yellow_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)

                if (current_position == "block_yellow_2") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_yellow_2
                        val destinationBlock: LinearLayout? = blocks[26]
                        sourceBlock?.removeView(yyy2)
                        destinationBlock?.addView(yyy2)
                        yellow_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos >= 52 && next_pos <= 57) {
                        next_pos %= 52
                        yellow2_pakli=1
                    }


                    if (yellow2_pakli==1) {

                        when (next_pos) {
                            25 -> next_pos = 62
                            26 -> next_pos = 63
                            27 -> next_pos = 64
                            28 -> next_pos = 65
                            29 -> next_pos = 66
                            30 -> next_pos = 81
                            67 -> next_pos = 81
                            in 31..37 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 68..74 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 82..86 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                        }
                    }

                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(yyy2)
                    destinationBlock?.addView(yyy2)
                    yellow_played = 1


                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                yellow2_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_y2, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_y2, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_y2, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_y2, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_y2, pos_g1, ggg1, R.id.block_green_1)
                    movePieceIfOverlap(pos_y2, pos_g2, ggg2, R.id.block_green_2)
                    movePieceIfOverlap(pos_y2, pos_g3, ggg3, R.id.block_green_3)
                    movePieceIfOverlap(pos_y2, pos_g4, ggg4, R.id.block_green_4)
                    movePieceIfOverlap(pos_y2, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_y2, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_y2, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_y2, pos_b4, bbb4, R.id.block_blue_4)



                }
            }
        }


        yyy3.setOnClickListener {
            if (yellow_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)

                if (current_position == "block_yellow_3") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_yellow_3
                        val destinationBlock: LinearLayout? = blocks[26]
                        sourceBlock?.removeView(yyy3)
                        destinationBlock?.addView(yyy3)
                        yellow_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos >= 52 && next_pos <= 57) {
                        next_pos %= 52
                        yellow3_pakli=1
                    }


                    if (yellow3_pakli==1) {

                        when (next_pos) {
                            25 -> next_pos = 62
                            26 -> next_pos = 63
                            27 -> next_pos = 64
                            28 -> next_pos = 65
                            29 -> next_pos = 66
                            30 -> next_pos = 80
                            67 -> next_pos = 80
                            in 31..37 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 68..74 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 81..86 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                        }
                    }

                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(yyy3)
                    destinationBlock?.addView(yyy3)
                    yellow_played = 1


                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                yellow3_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_y3, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_y3, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_y3, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_y3, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_y3, pos_g1, ggg1, R.id.block_green_1)
                    movePieceIfOverlap(pos_y3, pos_g2, ggg2, R.id.block_green_2)
                    movePieceIfOverlap(pos_y3, pos_g3, ggg3, R.id.block_green_3)
                    movePieceIfOverlap(pos_y3, pos_g4, ggg4, R.id.block_green_4)
                    movePieceIfOverlap(pos_y3, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_y3, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_y3, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_y3, pos_b4, bbb4, R.id.block_blue_4)



                }
            }
        }
        yyy4.setOnClickListener {
            if (yellow_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)

                if (current_position == "block_yellow_4") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_yellow_4
                        val destinationBlock: LinearLayout? = blocks[26]
                        sourceBlock?.removeView(yyy4)
                        destinationBlock?.addView(yyy4)
                        yellow_played = 1
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    var currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos >= 52 && next_pos <= 57) {
                        next_pos %= 52
                        yellow4_pakli=1
                    }


                    if (yellow4_pakli==1) {

                        when (next_pos) {
                            25 -> next_pos = 62
                            26 -> next_pos = 63
                            27 -> next_pos = 64
                            28 -> next_pos = 65
                            29 -> next_pos = 66
                            30 -> next_pos = 82
                            67 -> next_pos = 82
                            in 31..37 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 68..74 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                            in 83..88 -> { /* Do nothing for positions 31 to 37 */
                                next_pos -= diceValue
                            }
                        }
                    }

                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(yyy4)
                    destinationBlock?.addView(yyy4)
                    yellow_played = 1


                    val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                    val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                    val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                    val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                    val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                    val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                    val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                    val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                    val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                    val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                    val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                    val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                    val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                    val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                    val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                    val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                    fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                        if (pos_r == pos_g) {

                            if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                            } else {

                                val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                                val destinationBlock: LinearLayout? =
                                    findViewById(destinationBlockId)
                                sourceBlock?.removeView(piece)
                                destinationBlock?.addView(piece)

                                yellow4_pakli = 0
                            }
                        }
                    }

                    movePieceIfOverlap(pos_y4, pos_r1, rrr1, R.id.block_red_1)
                    movePieceIfOverlap(pos_y4, pos_r2, rrr2, R.id.block_red_2)
                    movePieceIfOverlap(pos_y4, pos_r3, rrr3, R.id.block_red_3)
                    movePieceIfOverlap(pos_y4, pos_r4, rrr4, R.id.block_red_4)
                    movePieceIfOverlap(pos_y4, pos_g1, ggg1, R.id.block_green_1)
                    movePieceIfOverlap(pos_y4, pos_g2, ggg2, R.id.block_green_2)
                    movePieceIfOverlap(pos_y4, pos_g3, ggg3, R.id.block_green_3)
                    movePieceIfOverlap(pos_y4, pos_g4, ggg4, R.id.block_green_4)
                    movePieceIfOverlap(pos_y4, pos_b1, bbb1, R.id.block_blue_1)
                    movePieceIfOverlap(pos_y4, pos_b2, bbb2, R.id.block_blue_2)
                    movePieceIfOverlap(pos_y4, pos_b3, bbb3, R.id.block_blue_3)
                    movePieceIfOverlap(pos_y4, pos_b4, bbb4, R.id.block_blue_4)

                }
            }
        }





        bbb1.setOnClickListener {

            if (blue_played == 0) {

                        val diceValue = tvdice.text.toString().toIntOrNull()
                        val current_position: String? =
                            resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
    
                        if (current_position == "block_blue_1") {
                            if (diceValue == 6) {
                                val sourceBlock: LinearLayout? = block_blue_1
                                val destinationBlock: LinearLayout? = blocks[39]
                                sourceBlock?.removeView(bbb1)
                                destinationBlock?.addView(bbb1)
                            }
                        } else {
                            val currentId = resources.getIdentifier(current_position, "id", packageName)
                            val current_block: LinearLayout? = findViewById(currentId)
                            val matchResult = Regex("\\d+").find(current_position ?: "")
                            val currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                            var next_pos = currentPosInt + diceValue!!



                            if (next_pos >= 52 && next_pos <= 58) {
                                next_pos %= 52
                                blue1_pakli = 1
                            }



                            if (blue1_pakli == 1){

                                if (next_pos == 38) {
                                    next_pos = 67
                                }
                                if (next_pos == 39) {
                                    next_pos = 68
                                }
                                if (next_pos == 40) {
                                    next_pos = 69
                                }
                                if (next_pos == 41) {
                                    next_pos = 70
                                }
                                if (next_pos == 42) {
                                    next_pos = 71
                                }
                                if (next_pos == 43) {
                                    next_pos = 87
                                }

                                if (next_pos == 72) {
                                    next_pos = 87
                                }

                                if (next_pos == 73) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 74) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 75) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 76) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 77) {
                                    next_pos -= diceValue
                                }





                                if (next_pos == 88) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 89) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 90) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 91) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 92) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 93) {
                                    next_pos -= diceValue
                                }
                                if (next_pos == 94) {
                                    next_pos -= diceValue
                                }

                            }
    
    
    
    
    
    
                            val destinationBlock: LinearLayout? = blocks[next_pos]
                            current_block?.removeView(bbb1)
                            destinationBlock?.addView(bbb1)
                        }
                        blue_played = 1


                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)

                            blue1_pakli = 0
                        }
                    }
                }

                movePieceIfOverlap(pos_b1, pos_r1, rrr1, R.id.block_red_1)
                movePieceIfOverlap(pos_b1, pos_r2, rrr2, R.id.block_red_2)
                movePieceIfOverlap(pos_b1, pos_r3, rrr3, R.id.block_red_3)
                movePieceIfOverlap(pos_b1, pos_r4, rrr4, R.id.block_red_4)
                movePieceIfOverlap(pos_b1, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_b1, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_b1, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_b1, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_b1, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_b1, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_b1, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_b1, pos_y4, yyy4, R.id.block_yellow_4)




                    }
                }
        bbb2.setOnClickListener {
            if (blue_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)

                if (current_position == "block_blue_2") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_blue_2
                        val destinationBlock: LinearLayout? = blocks[39]
                        sourceBlock?.removeView(bbb2)
                        destinationBlock?.addView(bbb2)
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    val currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos >= 52 && next_pos <= 58) {
                        next_pos %= 52
                        blue2_pakli = 1
                    }



                    if (blue2_pakli == 1){

                        if (next_pos == 38) {
                            next_pos = 67
                        }
                        if (next_pos == 39) {
                            next_pos = 68
                        }
                        if (next_pos == 40) {
                            next_pos = 69
                        }
                        if (next_pos == 41) {
                            next_pos = 70
                        }
                        if (next_pos == 42) {
                            next_pos = 71
                        }
                        if (next_pos == 43) {
                            next_pos = 84
                        }

                        if (next_pos == 72) {
                            next_pos = 84
                        }

                        if (next_pos == 73) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 74) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 75) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 76) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 77) {
                            next_pos -= diceValue
                        }



                        if (next_pos == 85) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 86) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 87) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 88) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 89) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 90) {
                            next_pos -= diceValue
                        }




                    }

                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(bbb2)
                    destinationBlock?.addView(bbb2)
                }
                blue_played = 1

                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)

                            blue2_pakli = 0
                        }
                    }
                }

                movePieceIfOverlap(pos_b2, pos_r1, rrr1, R.id.block_red_1)
                movePieceIfOverlap(pos_b2, pos_r2, rrr2, R.id.block_red_2)
                movePieceIfOverlap(pos_b2, pos_r3, rrr3, R.id.block_red_3)
                movePieceIfOverlap(pos_b2, pos_r4, rrr4, R.id.block_red_4)
                movePieceIfOverlap(pos_b2, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_b2, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_b2, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_b2, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_b2, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_b2, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_b2, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_b2, pos_y4, yyy4, R.id.block_yellow_4)

            }
        }
        bbb3.setOnClickListener {
            if (blue_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)

                if (current_position == "block_blue_3") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_blue_3
                        val destinationBlock: LinearLayout? = blocks[39]
                        sourceBlock?.removeView(bbb3)
                        destinationBlock?.addView(bbb3)
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    val currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos >= 52 && next_pos <= 58) {
                        next_pos %= 52
                        blue3_pakli = 1
                    }

                    if (blue3_pakli == 1){

                        if (next_pos == 38) {
                            next_pos = 67
                        }
                        if (next_pos == 39) {
                            next_pos = 68
                        }
                        if (next_pos == 40) {
                            next_pos = 69
                        }
                        if (next_pos == 41) {
                            next_pos = 70
                        }
                        if (next_pos == 42) {
                            next_pos = 71
                        }
                        if (next_pos == 43) {
                            next_pos = 85
                        }

                        if (next_pos == 72) {
                            next_pos = 85
                        }

                        if (next_pos == 73) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 74) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 75) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 76) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 77) {
                            next_pos -= diceValue
                        }



                        if (next_pos == 86) {
                            next_pos -= diceValue
                        }

                        if (next_pos == 87) {
                            next_pos -= diceValue
                        }

                        if (next_pos == 88) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 89) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 90) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 91) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 92) {
                            next_pos -= diceValue
                        }



                    }

                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(bbb3)
                    destinationBlock?.addView(bbb3)
                }
                blue_played = 1

                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)

                            blue3_pakli = 0
                        }
                    }
                }

                movePieceIfOverlap(pos_b3, pos_r1, rrr1, R.id.block_red_1)
                movePieceIfOverlap(pos_b3, pos_r2, rrr2, R.id.block_red_2)
                movePieceIfOverlap(pos_b3, pos_r3, rrr3, R.id.block_red_3)
                movePieceIfOverlap(pos_b3, pos_r4, rrr4, R.id.block_red_4)
                movePieceIfOverlap(pos_b3, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_b3, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_b3, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_b3, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_b3, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_b3, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_b3, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_b3, pos_y4, yyy4, R.id.block_yellow_4)

            }
        }


        bbb4.setOnClickListener {
            if (blue_played == 0) {
                val diceValue = tvdice.text.toString().toIntOrNull()
                val current_position: String? =
                    resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)

                if (current_position == "block_blue_4") {
                    if (diceValue == 6) {
                        val sourceBlock: LinearLayout? = block_blue_4
                        val destinationBlock: LinearLayout? = blocks[39]
                        sourceBlock?.removeView(bbb4)
                        destinationBlock?.addView(bbb4)
                    }
                } else {
                    val currentId = resources.getIdentifier(current_position, "id", packageName)
                    val current_block: LinearLayout? = findViewById(currentId)
                    val matchResult = Regex("\\d+").find(current_position ?: "")
                    val currentPosInt = matchResult?.value?.toIntOrNull() ?: 0
                    var next_pos = currentPosInt + diceValue!!

                    if (next_pos >= 52 && next_pos <= 58) {
                        next_pos %= 52
                        blue4_pakli = 1
                    }

                    if (blue4_pakli == 1){

                        if (next_pos == 38) {
                            next_pos = 67
                        }
                        if (next_pos == 39) {
                            next_pos = 68
                        }
                        if (next_pos == 40) {
                            next_pos = 69
                        }
                        if (next_pos == 41) {
                            next_pos = 70
                        }
                        if (next_pos == 42) {
                            next_pos = 71
                        }
                        if (next_pos == 43) {
                            next_pos = 86
                        }

                        if (next_pos == 72) {
                            next_pos = 86
                        }

                        if (next_pos == 73) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 74) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 75) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 76) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 77) {
                            next_pos -= diceValue
                        }





                        if (next_pos == 87) {
                            next_pos -= diceValue
                        }

                        if (next_pos == 88) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 89) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 90) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 91) {
                            next_pos -= diceValue
                        }
                        if (next_pos == 92) {
                            next_pos -= diceValue
                        }



                    }

                    val destinationBlock: LinearLayout? = blocks[next_pos]
                    current_block?.removeView(bbb4)
                    destinationBlock?.addView(bbb4)
                }
                blue_played = 1


                val pos_r1: String? = resources.getResourceEntryName((rrr1.parent as? View)?.id ?: 0)
                val pos_r2: String? = resources.getResourceEntryName((rrr2.parent as? View)?.id ?: 0)
                val pos_r3: String? = resources.getResourceEntryName((rrr3.parent as? View)?.id ?: 0)
                val pos_r4: String? = resources.getResourceEntryName((rrr4.parent as? View)?.id ?: 0)
                val pos_b1: String? = resources.getResourceEntryName((bbb1.parent as? View)?.id ?: 0)
                val pos_b2: String? = resources.getResourceEntryName((bbb2.parent as? View)?.id ?: 0)
                val pos_b3: String? = resources.getResourceEntryName((bbb3.parent as? View)?.id ?: 0)
                val pos_b4: String? = resources.getResourceEntryName((bbb4.parent as? View)?.id ?: 0)
                val pos_y1: String? = resources.getResourceEntryName((yyy1.parent as? View)?.id ?: 0)
                val pos_y2: String? = resources.getResourceEntryName((yyy2.parent as? View)?.id ?: 0)
                val pos_y3: String? = resources.getResourceEntryName((yyy3.parent as? View)?.id ?: 0)
                val pos_y4: String? = resources.getResourceEntryName((yyy4.parent as? View)?.id ?: 0)
                val pos_g1: String? = resources.getResourceEntryName((ggg1.parent as? View)?.id ?: 0)
                val pos_g2: String? = resources.getResourceEntryName((ggg2.parent as? View)?.id ?: 0)
                val pos_g3: String? = resources.getResourceEntryName((ggg3.parent as? View)?.id ?: 0)
                val pos_g4: String?  = resources.getResourceEntryName((ggg4.parent as? View)?.id ?: 0)



                fun movePieceIfOverlap(pos_r: String?, pos_g: String?, piece: View, destinationBlockId: Int) {

                    if (pos_r == pos_g) {

                        if (pos_r == "block_0" || pos_r == "block_8" || pos_r == "block_13" || pos_r == "block_21" || pos_r == "block_26" || pos_r == "block_34" || pos_r == "block_39" || pos_r == "block_47") {

                        } else {

                            val sourceBlock: LinearLayout? = piece.parent as? LinearLayout
                            val destinationBlock: LinearLayout? =
                                findViewById(destinationBlockId)
                            sourceBlock?.removeView(piece)
                            destinationBlock?.addView(piece)

                            blue4_pakli = 0
                        }
                    }
                }

                movePieceIfOverlap(pos_b4, pos_r1, rrr1, R.id.block_red_1)
                movePieceIfOverlap(pos_b4, pos_r2, rrr2, R.id.block_red_2)
                movePieceIfOverlap(pos_b4, pos_r3, rrr3, R.id.block_red_3)
                movePieceIfOverlap(pos_b4, pos_r4, rrr4, R.id.block_red_4)
                movePieceIfOverlap(pos_b4, pos_g1, ggg1, R.id.block_green_1)
                movePieceIfOverlap(pos_b4, pos_g2, ggg2, R.id.block_green_2)
                movePieceIfOverlap(pos_b4, pos_g3, ggg3, R.id.block_green_3)
                movePieceIfOverlap(pos_b4, pos_g4, ggg4, R.id.block_green_4)
                movePieceIfOverlap(pos_b4, pos_y1, yyy1, R.id.block_yellow_1)
                movePieceIfOverlap(pos_b4, pos_y2, yyy2, R.id.block_yellow_2)
                movePieceIfOverlap(pos_b4, pos_y3, yyy3, R.id.block_yellow_3)
                movePieceIfOverlap(pos_b4, pos_y4, yyy4, R.id.block_yellow_4)

            }
        }





            val exit_game = findViewById<Button>(R.id.exit_game)

            exit_game.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Restart Game")
                builder.setMessage("Are you sure you want to exit the game?")

                builder.setPositiveButton("Yes") { _, _ ->
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                }

                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss() // Dismiss the dialog if "No" is clicked
                }

                val dialog = builder.create()
                dialog.show()
            }

            dashboard.visibility - View.VISIBLE


        }


    }

