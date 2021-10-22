package com.aleksandrov.breakingbad.presentation.characterdetails

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.appComponent
import com.bumptech.glide.Glide
import javax.inject.Inject

const val CHARACTER_ID = "CHARACTER_ID"

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: DetailsViewModel

    private lateinit var img: ImageView
    private lateinit var name: TextView
    private lateinit var birthday: TextView
    private lateinit var occupation: TextView
    private lateinit var status: TextView
    private lateinit var nickname: TextView
    private lateinit var appearance: TextView
    private lateinit var portrayed: TextView
    private lateinit var betterCallSaulAppearancePrev: TextView
    private lateinit var betterCallSaulAppearance: TextView
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        appComponent.inject(this)

        initView()
    }

    private fun initView() {
        val id: Int = intent.getIntExtra(CHARACTER_ID, -1)
        viewModel.loadCharacterById(id)

        img = findViewById(R.id.img)
        name = findViewById(R.id.name)
        birthday = findViewById(R.id.birthday)
        occupation = findViewById(R.id.occupation)
        status = findViewById(R.id.status)
        nickname = findViewById(R.id.nickname)
        appearance = findViewById(R.id.appearance)
        portrayed = findViewById(R.id.portrayed)
        betterCallSaulAppearancePrev = findViewById(R.id.better_call_saul_appearance_prev)
        betterCallSaulAppearance = findViewById(R.id.better_call_saul_appearance)
        progress = findViewById(R.id.progress)

        viewModel.character.observe(this) { character ->
            Glide.with(this)
                .load(character.img)
                .into(img)
            name.text = character.name
            birthday.text = character.birthday
            occupation.text = character.occupation.joinToString(", ")
            status.text = character.status
            nickname.text = character.nickname
            appearance.text = character.appearance.joinToString(", ")
            portrayed.text = character.portrayed
            character.better_call_saul_appearance.also {
                if (it.isNotEmpty()) {
                    betterCallSaulAppearancePrev.visibility = View.VISIBLE
                    betterCallSaulAppearance.visibility = View.VISIBLE
                    betterCallSaulAppearance.text = it.joinToString(", ")
                }
            }
        }
        viewModel.progress.observe(this) {
            it.getContentIfNotHandled()?.also { visible ->
                progress.visibility = if (visible) View.VISIBLE else View.INVISIBLE
            }
        }
        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.also { message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

}