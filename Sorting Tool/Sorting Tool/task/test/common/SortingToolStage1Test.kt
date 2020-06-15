package common

import org.hyperskill.hstest.stage.StageTest
import org.hyperskill.hstest.testcase.CheckResult
import org.hyperskill.hstest.testcase.TestCase
import sorting.Main
import java.util.*

abstract class SortingToolStage1Test : StageTest<SortingToolClue>(Main::class.java) {

    override fun generate(): List<TestCase<SortingToolClue>> {
        return stage1Tests()
    }

    override fun check(reply: String, clue: SortingToolClue): CheckResult {
        return checkForLong(clue, reply)
    }
}

fun stage1Tests(): List<TestCase<SortingToolClue>> {
    return listOf(
            createTest(
                    """
                |1 -2   33 4
                |42
                |1                 1
                """.trimMargin(),
                    true
            ),
            createTest("1 2 2 3 4 5 5", true),
            createTest("1 1 2 2 3 4 4 4", false)
    )
}


fun revealRawTest(clue: SortingToolClue, reply: String): String {
    return with(clue) { "Args:\n${args.joinToString(" ")}\nInput:\n$consoleInput\nYour output:\n$reply\n\n" }
}

class SortingToolClue(val consoleInput: String, val revealTest: Boolean, val args: List<String>)

fun createTest(
        consoleInput: String,
        revealTest: Boolean,
        vararg args: String = arrayOf("-dataType", "long")
): TestCase<SortingToolClue> {
    return TestCase<SortingToolClue>()
            .setAttach(SortingToolClue(consoleInput, revealTest, args.toList()))
            .setInput(consoleInput)
            .addArguments(*args)
}

fun checkForLong(clue: SortingToolClue, reply_: String): CheckResult {
    val reply = reply_.trim()
    val regex = """(\d+)\D+(\d+)\D+(\d+)""".toRegex()
    val matchResult = regex.find(reply)
    if (matchResult == null) {
        return if (clue.revealTest) {
            CheckResult(
                    false,
                    "Can't parse your output. Please check if your output contains three numbers\n" +
                            revealRawTest(clue, reply)
            )
        } else {
            CheckResult(false, "Can't parse your output.")
        }
    }

    val (totalNumbers, greatestNumber, greatestNumberCount) = matchResult.groupValues.drop(1).map { it.toInt() }

    val scanner = Scanner(clue.consoleInput)

    val actualNumbers = mutableListOf<Int>()

    while (scanner.hasNextInt()) {
        actualNumbers.add(scanner.nextInt())
    }

    val actualTotal = actualNumbers.size

    if (actualTotal != totalNumbers) {
        return if (clue.revealTest) {
            CheckResult(
                    false,
                    "Total numbers ($totalNumbers) are incorrect. Expected: $actualTotal.\n" +
                            revealRawTest(clue, reply)
            )
        } else {
            CheckResult(false, "Total numbers are incorrect.")
        }
    }

    val actualMax = actualNumbers.max()

    if (actualMax != greatestNumber) {
        return if (clue.revealTest) {
            CheckResult(
                    false,
                    "Greatest number ($greatestNumber) is incorrect. Expected: $actualMax.\n" +
                            revealRawTest(clue, reply)
            )
        } else {
            CheckResult(false, "Greatest number is incorrect.")
        }
    }

    val actualMaxCount = actualNumbers.count { it == actualMax }

    if (actualMaxCount != greatestNumberCount) {
        return if (clue.revealTest) {
            CheckResult(
                    false,
                    "Greatest number times ($greatestNumberCount) are incorrect. Expected: $actualMaxCount.\n" +
                            revealRawTest(clue, reply)
            )
        } else {
            CheckResult(false, "Greatest number times are incorrect.")
        }
    }

    return CheckResult(true)
}
