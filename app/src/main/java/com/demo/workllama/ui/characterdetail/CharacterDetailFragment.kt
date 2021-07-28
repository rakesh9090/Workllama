package com.demo.workllama.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.demo.workllama.R
import com.demo.workllama.data.entities.Characters
import com.demo.workllama.databinding.CharacterDetailFragmentBinding
import com.demo.workllama.utils.Constant
import com.demo.workllama.utils.Resource
import com.demo.workllama.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var binding: CharacterDetailFragmentBinding by autoCleared()
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
        setUpView()
    }

    private fun setUpView() {
        binding.heart.setOnClickListener { viewModel.starClick() }
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            it?.let {
                bindCharacter(it)
                binding.progressBar.visibility = View.GONE
                binding.characterCl.visibility = View.VISIBLE
            }
           /* when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }*/
        })
    }

    private fun bindCharacter(character: Characters) {
        binding.name.text = character.name
        binding.phNo.text = character.phone
        binding.email.text = character.email
        if (character.isStarred > 0)
            binding.heart.setBackgroundResource(R.drawable.ic_heart_red)
        else binding.heart.setBackgroundResource(R.drawable.ic_heart)
        Glide.with(binding.root)
            .load(Constant.BASE_URL_IMAGE + character.thumbnail)
            .transform(CircleCrop())
            .into(binding.image)
    }
}
