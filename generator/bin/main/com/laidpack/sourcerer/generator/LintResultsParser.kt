package com.laidpack.sourcerer.generator

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

data class LintIssue (
        val classSimpleName: String,
        val id: String,
        val methodName: String,
        val minimumApiLevel: Int,
        val message: String,
        val errorLine1: String,
        val errorLine2: String
)
data class LintResults(val issues : Map< String /* class simple name */, Map<String /* method name */, LintIssue>>)

class LintResultsParser(lintResultsFilePath: Path) {
    private val docFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder = docFactory.newDocumentBuilder()
    private val doc = docBuilder.parse(lintResultsFilePath.toString())
    private val xpFactory = XPathFactory.newInstance()

    fun parse(): LintResults {
        val xPath = xpFactory.newXPath()
        val selector = "/issues/issue"

        val issues = mutableMapOf<String, MutableMap<String, LintIssue>>()
        val nodeList = xPath.compile(selector).evaluate(doc, XPathConstants.NODESET) as NodeList
        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            val id = node.attributes.getNamedItem("id").nodeValue
            if (id != "NewApi") continue

            val message = node.attributes.getNamedItem("message").nodeValue
            val errorLine1 = node.attributes.getNamedItem("errorLine1").nodeValue
            val errorLine2 = node.attributes.getNamedItem("errorLine2").nodeValue

            var matchResult = callRegex.find(message) ?: callRegex2.find(message)
            if (matchResult != null) {
                val apiLevel = matchResult.groupValues[1].toInt()
                val methodName = matchResult.groupValues[2]
                val classSimpleName = getClassSimpleName(node)
                if (!issues.containsKey(classSimpleName)) {
                    issues[classSimpleName] = mutableMapOf()
                }
                val issuesForThisClass = issues[classSimpleName] as MutableMap<String, LintIssue>
                issuesForThisClass[methodName] = LintIssue(
                        classSimpleName,
                        id,
                        methodName,
                        apiLevel,
                        message,
                        errorLine1,
                        errorLine2
                )
            } else {
                matchResult = classRegex.find(message)
                if (matchResult != null) {
                    val apiLevel = matchResult.groupValues[1].toInt()
                    val methodName = ALL_METHODS
                    val classSimpleName = getClassSimpleName(node)
                    if (!issues.containsKey(classSimpleName)) {
                        issues[classSimpleName] = mutableMapOf()
                    }
                    val issuesForThisClass = issues[classSimpleName] as MutableMap<String, LintIssue>
                    issuesForThisClass[methodName] = LintIssue(
                            classSimpleName,
                            id,
                            methodName,
                            apiLevel,
                            message,
                            errorLine1,
                            errorLine2
                    )
                }
            }
            if (matchResult == null)
                throw IllegalStateException("issue is NewApi but message regex doesn't match. Has the message changed? Message: $message")
        }
        return LintResults(issues)
    }

    private fun getLocationNode(node: Node): Node {
        val xPath = xpFactory.newXPath()
        val locationSelector = "location"
        val locationNodes = xPath.compile(locationSelector).evaluate(node, XPathConstants.NODESET) as DTMNodeList
        return locationNodes.item(0)
    }

    private fun getClassSimpleName(node: Node): String {
        val locationNode = getLocationNode(node)
        val fileLocation = locationNode.attributes.getNamedItem("file").nodeValue
        val matchResult = fileLocationRegex.find(fileLocation) ?: throw IllegalStateException("File location regex doesn't match. File location formats changed? File location: '$fileLocation'")
        return matchResult.groupValues[1]
    }

    companion object {
        const val ALL_METHODS = "*"
        val callRegex = Regex("^Call requires API level (\\d+) \\(current min is \\d+\\): .*?#(\\w+)")
        val callRegex2 = Regex("^Call requires API level (\\d+) \\(current min is \\d+\\):.*\\.(\\w+)")
        val classRegex = Regex("^Class requires API level (\\d+) \\(current min is \\d+\\):.*\\.(\\w+)")
        val fileLocationRegex = Regex(".*/(\\w+)Factory\\.kt")
    }

}

/*
    <issue
        id="NewApi"
        severity="Error"
        message="Call requires API level 16 (current min is 15): android.view.View#setBackground"
        category="Correctness"
        priority="6"
        summary="Calling new methods on older versions"
        explanation="This check scans through all the Android API calls in the application and warns about any calls that are not available on **all** versions targeted by this application (according to its minimum SDK attribute in the manifest).&#xA;&#xA;If you really want to use this API and don&apos;t need to support older devices just set the `minSdkVersion` in your `build.gradle` or `AndroidManifest.xml` files.&#xA;&#xA;If your code is **deliberately** accessing newer APIs, and you have ensured (e.g. with conditional execution) that this code will only ever be called on a supported platform, then you can annotate your class or method with the `@TargetApi` annotation specifying the local minimum SDK to apply, such as `@TargetApi(11)`, such that this check considers 11 rather than your manifest file&apos;s minimum SDK as the required API level.&#xA;&#xA;If you are deliberately setting `android:` attributes in style definitions, make sure you place this in a `values-v`*NN* folder in order to avoid running into runtime conflicts on certain devices where manufacturers have added custom attributes whose ids conflict with the new ones on later platforms.&#xA;&#xA;Similarly, you can use tools:targetApi=&quot;11&quot; in an XML file to indicate that the element will only be inflated in an adequate context."
        errorLine1="                background = immutableBackground"
        errorLine2="                ~~~~~~~~~~"
        quickfix="studio">
        <location
            file="/Users/derk/jsProjects/MyApplication/app/src/main/kotlin/com/laidpack/sourcerer/generated/BaseFactory.kt"
            line="22"
            column="17"/>
    </issue>
 */
