package com.example.humblrvsv

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.humblrvsv.databinding.FragmentAuthBinding

class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)
//    private val viewModel by viewModels<AuthViewModel>()
    private val args by navArgs<AuthFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(CALL))
            startActivity(intent)
            if (args.code != null)
            binding.textView.text = args.code
        }
    }
}