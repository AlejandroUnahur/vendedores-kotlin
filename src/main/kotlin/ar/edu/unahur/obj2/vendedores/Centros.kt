package ar.edu.unahur.obj2.vendedores

import java.lang.Exception

class Centro(val ciudad: Ciudad) {
    val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedor(vendedor: Vendedor) {
        if (this.vendedores.contains(vendedor)) {
            throw Exception("El vendedor ya trabaja en el centro de distribucion")
        }
        vendedores.add(vendedor)
    }

    fun vendedorEstrella() = vendedores.maxBy { it.puntajeCertificaciones() }

    fun puedeCubrir(ciudad: Ciudad) = vendedores.any { it.puedeTrabajarEn(ciudad) }

    fun vendedoresGenericos() = vendedores.filter { it.otrasCertificaciones() >= 1 }

    fun esRobusto() = this.vendedoresFirmes() >= 3

    fun vendedoresFirmes() = vendedores.count { it.esFirme() }

}