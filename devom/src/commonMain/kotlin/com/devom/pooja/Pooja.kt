package com.devom.pooja

import com.devom.domain.pooja.CreatePoojaUseCase
import com.devom.domain.pooja.GetPoojaUseCase
import com.devom.domain.pooja.RemovePoojaUseCase
import com.devom.domain.pooja.UpdatePoojaUseCase

class Pooja {
    val getPoojaUseCase by lazy { GetPoojaUseCase() }
    val createPoojaUseCase by lazy { CreatePoojaUseCase() }
    val updatePoojaUseCase by lazy { UpdatePoojaUseCase() }
    val removePoojaUseCase by lazy { RemovePoojaUseCase() }
}