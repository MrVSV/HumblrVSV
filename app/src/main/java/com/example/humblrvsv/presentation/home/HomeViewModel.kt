package com.example.humblrvsv.presentation.home

import com.example.humblrvsv.domain.UseCase
import com.example.humblrvsv.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCase,
) : BaseViewModel() {


    fun getSubList(source: String?) = useCase.getSubList(source).flow
}