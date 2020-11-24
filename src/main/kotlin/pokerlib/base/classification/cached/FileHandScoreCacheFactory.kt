package pokerlib.base.classification.cached

import com.google.common.base.Stopwatch
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import kotlin.text.Charsets.UTF_8

object FileHandScoreCacheFactory {

    private const val handScoreCacheFileName = "hand_score_cache.gzip"
    private const val handSeparator = "\n"
    private const val handScoreSeparator = ":"

    fun create(): Map<Int, Int> {
        val sw = Stopwatch.createStarted()
        println("initializing file cache from file")
        val cache = GZIPInputStream(javaClass.classLoader.getResourceAsStream(handScoreCacheFileName)!!)
            .bufferedReader(UTF_8)
            .use(BufferedReader::readText)
            .split(handSeparator)
            .map(this::parseLine).toMap()
        println("finished file cache from file in ${sw.elapsed(TimeUnit.MILLISECONDS)} ms")
        return cache
    }

    private fun parseLine(line: String): Pair<Int, Int> {
        return line.split(handScoreSeparator).let { Pair(it[0].toInt(), it[1].toInt()) }
    }

    fun generateCacheFile(outputFileName: String) {
        val content = DefaultHandScoreCacheFactory.create().entries
            .joinToString(handSeparator) { "${it.key}:${it.value}" }
            .let(this::compress)
        File(outputFileName).writeBytes(content)
    }

    private fun compress(content: String): ByteArray {
        val bos = ByteArrayOutputStream()
        GZIPOutputStream(bos).bufferedWriter(UTF_8).use { it.write(content) }
        return bos.toByteArray()
    }
}