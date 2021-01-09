package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class CentrosTest: DescribeSpec({
    val buenosAires = Provincia(3075646)
    val santaFe = Provincia(3369646)
    val rosario = Ciudad(santaFe)
    val marDelPlata = Ciudad(buenosAires)
    val certificacionOtroTipo1 = Certificacion(false,30)
    val certificacionOtroTipo2 = Certificacion(false,25)
    val vendedorfijo1 = VendedorFijo(marDelPlata)
    val vendedorfijo2 = VendedorFijo(marDelPlata)

    val centro1 = Centro(marDelPlata)

    centro1.agregarVendedor(vendedorfijo1)
    centro1.agregarVendedor(vendedorfijo2)
    vendedorfijo1.agregarCertificacion(certificacionOtroTipo1)
    vendedorfijo2.agregarCertificacion(certificacionOtroTipo2)

    describe("Se intenta agregar un vendedor que ya pertenece al centro  ") {
        shouldThrowAny{ centro1.agregarVendedor(vendedorfijo1)}
    }

    describe("Vendedor estrella") {
        centro1.vendedorEstrella().shouldBe(vendedorfijo1)
    }

    describe("Puede cubrir en") {
        it("En una ciudad dada en que al menos uno de los vendedores registrados pueda trabajar en esa ciudad") {
            centro1.puedeCubrir(marDelPlata).shouldBeTrue()
        }

        it("En una ciudad dada en que al menos uno de los vendedores registrados no pueda trabajar en esa ciudad") {
            centro1.puedeCubrir(rosario).shouldBeFalse()
        }
    }

    describe("Coleccion de vendedores genericos") {
        centro1.vendedoresGenericos().shouldContainAll(vendedorfijo1,vendedorfijo2)
    }

    describe("Es robusto") {
        centro1.esRobusto().shouldBeFalse()
    }
})
