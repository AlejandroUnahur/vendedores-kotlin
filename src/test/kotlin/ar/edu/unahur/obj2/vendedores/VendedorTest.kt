package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("Es influyente") {
      vendedorFijo.esInfluyente().shouldBeFalse()
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val jujuy = Provincia(718971)
    val buenosAires = Provincia(10000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones,jujuy))
    val viajante2 = Viajante(listOf(buenosAires))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
    describe("Es influyente") {
      it("Cuando la poblacion total de todas la provincias es 10 millones o mas") {
        viajante2.esInfluyente().shouldBeTrue()
      }
      it("Cuando la poblacion total de todas las provincias es menor a 10 millones") {
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("Comercios corresponsal") {
    val jujuy = Provincia(718971)
    val tucuman = Provincia(1593000)
    val misiones = Provincia(1175000)
    val caimancito = Ciudad(jujuy)
    val tumbaya = Ciudad(jujuy)
    val humahuaca = Ciudad(jujuy)
    val posadas = Ciudad(misiones)
    val candelaria = Ciudad(misiones)
    val manantial = Ciudad(tucuman)
    val comercio = ComercioCorresponsal(listOf(caimancito,humahuaca,posadas,candelaria,manantial))
    val comercio2 = ComercioCorresponsal(listOf(caimancito,humahuaca,posadas,candelaria))

    describe("puedeTrabajarEn") {
      it("una ciudad que tiene sucursal") {
        comercio.puedeTrabajarEn(caimancito).shouldBeTrue()
      }
      it("una ciudad que no tiene sucursal") {
        comercio.puedeTrabajarEn(tumbaya).shouldBeFalse()
      }
    }

    describe("Es influyente") {
      it("Cuando tiene sucursales en al menos 5 ciudades") {
        comercio.esInfluyente().shouldBeTrue()
      }

      it("Cuando tiene sucursales en al menos 3 provincias ") {
        comercio.esInfluyente().shouldBeTrue()
      }

      it("Cuando no tiene sucursales en 4 ciudades y 2 provincias ") {
        comercio2.esInfluyente().shouldBeFalse()
      }
    }

  }
})
