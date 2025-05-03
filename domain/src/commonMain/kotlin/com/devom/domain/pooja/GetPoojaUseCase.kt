package com.devom.domain.pooja

import com.devom.data.repository.pooja.PoojaRepository
import com.devom.data.repository.pooja.PoojaRepositoryImpl

class GetPoojaUseCase {

    private val poojaRepository : PoojaRepository = PoojaRepositoryImpl()

    /**
     * get pooja
     * @return Flow<ResponseResult<List<PoojaItem>>>
     */
    operator fun invoke() = poojaRepository.getPooja()
}