package com.example.apiliveapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.example.apiliveapp.MainViewModel
import com.example.apiliveapp.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topNews.observe(viewLifecycleOwner) {
            binding.newsRV.adapter = NewsAdapter(it)
        }

        binding.searchET.doOnTextChanged { text, _, _, _ ->
            Log.d("OnTextChanged", "$text")

            viewModel.searchNews(text.toString())
        }

    }


}