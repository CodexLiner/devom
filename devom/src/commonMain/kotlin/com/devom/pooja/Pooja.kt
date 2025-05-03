package com.devom.pooja

import com.devom.domain.pooja.GetPoojaUseCase

class Pooja {
    val getPoojaUseCase by lazy { GetPoojaUseCase() }
}