package org.javafreedom.khol

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.verify.assertTrue
import kotlin.test.Test

internal class KonsistTest {

    @Test
    fun `test classes are declared internal`() {
        Konsist
            .scopeFromTest()
            .classes()
            .assertTrue {
                it.hasInternalModifier
            }
    }

    @Test
    fun `algorithm classes inherit BaseCalculationAlgorithm`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withPackage("$BASE_PACKAGE.algorithm..")
            .assertTrue {
                it.hasParentOf(BaseCalculationAlgorithm::class)
            }
    }

    @Test
    fun `declarations inherit Declaration`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withPackage("$BASE_PACKAGE.declarations..")
            .assertTrue {
                it.hasParentOf(HolidayDeclarations::class) or it.hasEnumModifier
            }
    }

    companion object {
        val BASE_PACKAGE = "org.javafreedom.khol"
    }
}