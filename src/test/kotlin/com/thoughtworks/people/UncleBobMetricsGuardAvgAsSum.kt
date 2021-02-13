package com.thoughtworks.people

import jdepend.framework.JDepend
import jdepend.framework.PackageFilter
import org.junit.jupiter.api.Test

class UncleBobMetricsGuardAvgAsSum {

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

        val avgDistance = analyzed
            .map { it.distance() }
            .sum().div(analyzed.count())

        val avgAbstractness = analyzed
            .map { it.abstractness() }
            .sum().div(analyzed.count())

        val avgInstability = analyzed
            .map { it.instability() }
            .sum().div(analyzed.count())

        println("--------AVG---------")
        println("I: ${avgInstability}, A:$avgAbstractness, D:${avgDistance}")


    }
}