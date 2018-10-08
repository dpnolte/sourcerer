package com.laidpack.sourcerer.generator.lint

import com.android.tools.lint.LintCliClient
import com.android.tools.lint.LintCliFlags
import com.android.tools.lint.checks.ApiLookup
import com.android.tools.lint.detector.api.LintUtils
import com.android.tools.lint.detector.api.Project
import com.android.tools.lint.helpers.DefaultJavaEvaluator
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.search.GlobalSearchScope
import com.laidpack.sourcerer.generator.peeker.MethodInfo
import com.laidpack.sourcerer.generator.peeker.describeType
import com.laidpack.sourcerer.generator.target.Getter
import com.laidpack.sourcerer.generator.target.Setter
import com.squareup.kotlinpoet.ClassName
import java.io.File
import java.lang.IllegalArgumentException

class ApiDetector(private val projectDir: File) {
    private lateinit var project : Project
    private val client by lazy {
        val flags = LintCliFlags()
        flags.isShowEverything = true
        val lintClient = LintCliClient(flags, "sourcerer")
        project = lintClient.getProject(projectDir, projectDir)
        lintClient.registerProject(projectDir, project)
        lintClient.initializeProjects(lintClient.knownProjects)
        lintClient
    }
    private val lookup by lazy {
        ApiLookup.get(client)
    }
    private val evaluator by lazy {
        DefaultJavaEvaluator(client.ideaProject, project)
    }
    private val psiFacade by lazy {
        JavaPsiFacade.getInstance(client.ideaProject)
    }
    private fun findPsiClass(classNameAsString: String): PsiClass {
        return psiFacade.findClass(classNameAsString, GlobalSearchScope.allScope(client.ideaProject))
                ?: throw IllegalArgumentException("Class '$classNameAsString' not found as PsiClass")
    }

    fun getClassVersion(className: ClassName): Int {
        val psiClass = findPsiClass(className.canonicalName)
        return lookup.getClassVersion(evaluator.getInternalName(psiClass))
    }

    fun getMemberVersion(className: ClassName, method: MethodInfo): Int {
        return getMethodVersion(
                className,
                method.methodDeclaration.nameAsString,
                method.methodDeclaration.parameters.asSequence().map { it.describeType() }.toSet()
        )
    }

    fun getGetterVersion(className: ClassName, getter: Getter): Int {
        return if (getter.isField) {
            getFieldVersion(className, getter.name)
        } else {
            getMethodVersion(
                    className,
                    getter.name,
                    getter.parameters.asSequence().map { it.describedType }.toSet()
            )
        }
    }

    fun getSetterVersion(className: ClassName, setter: Setter): Int {
        return if (setter.isField) {
            getFieldVersion(className, setter.name)
        } else {
            getMethodVersion(
                    className,
                    setter.name,
                    setter.parameters.asSequence().map { it.describedType }.toSet()
            )
        }
    }

    private fun getMethodVersion(className: ClassName, methodName: String, parameterTypes: Set<String>): Int {
        val psiClass = findPsiClass(className.canonicalName)
        val psiMethods = psiClass.findMethodsByName(
                methodName, true
        )
        val psiMethod = psiMethods.find {
            it.parameterList.parametersCount == parameterTypes.size
                    && it.parameterList.parameters.all {psiParameter ->
                parameterTypes.contains(psiParameter.type.internalCanonicalText)
            }
        } ?: throw IllegalArgumentException("No PsiMethod found with signature: ${className.canonicalName}.$methodName(${parameterTypes.joinToString()})")
        val owner = evaluator.getInternalName(psiClass)
        val name = LintUtils.getInternalMethodName(psiMethod)
        val desc = evaluator.getInternalDescription(psiMethod, false, false)
        return lookup.getCallVersion(owner, name, desc)
    }

    fun getFieldVersion(className: ClassName, fieldName: String): Int {
        val psiClass = findPsiClass(className.canonicalName)
        val psiField = psiClass.findFieldByName(fieldName, true)
                ?: throw IllegalArgumentException("No PsiMethod found with signature: ${className.canonicalName}.$fieldName")

        val owner = evaluator.getInternalName(psiClass)
        val name = psiField.name
        return lookup.getFieldVersion(owner, name)
    }

    companion object {
        const val UNKNOWN_OR_VERSION_1 = -1
    }
}