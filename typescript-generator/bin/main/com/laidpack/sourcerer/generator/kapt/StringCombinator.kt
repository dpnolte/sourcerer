package com.laidpack.sourcerer.generator.kapt

object StringCombinator {
    fun getAllCombinations(parts: Collection<String>, separator: String = ""): Collection<String> {
        return getCombinations(parts, parts.size, separator)
    }
    /** recursive function to combine parts. E.g. if input = a, b, c, then a, aa, aaa, ab, ab, etc.
     *   -- from: https://stackoverflow.com/questions/5113707/getting-every-possible-permutation-of-a-string-or-combination-including-repeated?rq=1
     ***/
    fun getCombinations(parts: Collection<String>, lengthOfList: Int, separator: String = ""): Collection<String> {
        if (lengthOfList == 1)
            return parts
        else {
            val result = mutableListOf<String>()
            val subResults = getCombinations(parts, lengthOfList - 1)

            //append the sublists to each element
            var arrayIndex = 0
            parts.forEach{part ->
                subResults.forEach {subResult ->
                    result[arrayIndex] = part + separator + subResult
                    arrayIndex++
                }
            }
            return result
        }
    }
}