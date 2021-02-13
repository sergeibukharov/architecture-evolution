package com.thoughtworks.people

import jdepend.framework.JDepend
import jdepend.framework.PackageFilter
import org.junit.jupiter.api.Test

class UncleBobMetricsGuard {

    @Test
    fun `calculate Bob metrics`() {
        val onlyProductionPackages = PackageFilter.all().including("com.thoughtworks")

        val jdepend = JDepend(onlyProductionPackages)
//		jdepend.addPackage("com.thoughtworks")
//		jdepend.addPackage("com.thoughtworks.people")
//		jdepend.addPackage("com.thoughtworks.people.businessPeople")
//		jdepend.addPackage("com.thoughtworks.people.persistance")

        val dir = "build/classes/kotlin/main/com/thoughtworks/people/"
		jdepend.addDirectory("businessPeople/$dir")
		jdepend.addDirectory("persistence/$dir")
		jdepend.addDirectory("presentation/$dir")
		jdepend.addDirectory("avatarsDicebear/$dir")
		jdepend.addDirectory("quoteGarden/$dir")
		jdepend.addDirectory("useCasePeople/$dir")

        jdepend.filter.excluding(listOf("org.springframework"))
        jdepend.filter.excluding(listOf("java"))
        jdepend.filter.excluding(listOf("kotlin"))

        println("classes " + jdepend.countClasses())
        val analyzed = jdepend.analyze()
        analyzed.forEach {
            pack ->
            println(pack.name)
            println("I: ${pack.instability()}, A:${pack.abstractness()}, D:${pack.distance()}")
        }

        val abstractClassCount = analyzed.sumBy { it.abstractClassCount }
        val classCount = analyzed.sumBy { it.classCount }
        val abstractness = abstractness(classCount, abstractClassCount)

        val afferentCoupling = analyzed.sumBy { it.afferentCoupling() }
        val efferentCoupling = analyzed.sumBy { it.efferentCoupling() }
        val instability = instability(efferentCoupling, afferentCoupling)

        val distance = distance(abstractness, instability)


        println("--------AVG---------")
        println("I: ${instability}, A:$abstractness, D:${distance}")


    }

    fun instability(efferentCoupling: Int, afferentCoupling: Int): Float {
        val totalCoupling = efferentCoupling.toFloat() + afferentCoupling.toFloat()
        return if (totalCoupling > 0) {
            efferentCoupling / totalCoupling
        } else "0.00".toFloat()
    }

    fun abstractness(classCount: Int, abstractClassCount: Int): Float {
        return if (classCount > 0) {
            abstractClassCount.toFloat() / classCount.toFloat()
        } else "0.00".toFloat()
    }

    fun distance(abstractness: Float, instability: Float): Float {
        val d = Math.abs(abstractness + instability - 1)
        return d * 1 // volatality
    }
}