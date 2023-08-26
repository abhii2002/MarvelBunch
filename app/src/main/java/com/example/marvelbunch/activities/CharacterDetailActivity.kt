package com.example.marvelbunch.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.marvelbunch.models.Characters
import com.example.marvelbunch.constants.Constants
import com.example.marvelbunch.databinding.ActivityCharacterDetailBinding
import com.google.gson.Gson

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var characterModel: Characters? = null

        if(intent.hasExtra(Constants.CHARACTER_DETAILS)){
            val gson = Gson()
            characterModel = gson.fromJson(intent.getStringExtra(Constants.CHARACTER_DETAILS), Characters::class.java)


        }

        if (characterModel != null){
             val path = characterModel.thumbnail.path
             val extension = characterModel.thumbnail.extension
             val image = "${path}.${extension}"
             binding.tvTitle.text = characterModel.title
             binding.ivCharacter.load(image)
            binding.tvDescription.text = characterModel.description
            binding.tvComics.text = characterModel.comics.available.toString()
            binding.tvEvents.text = characterModel.events.available.toString()
            binding.tvStories.text = characterModel.stories.available.toString()
            binding.tvSeries.text = characterModel.series.available.toString()
        }

    }
}