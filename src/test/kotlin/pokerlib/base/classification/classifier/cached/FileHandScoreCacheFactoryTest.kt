package pokerlib.base.classification.classifier.cached

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pokerlib.base.classification.cached.DefaultHandScoreCacheFactory
import pokerlib.base.classification.cached.FileHandScoreCacheFactory

class FileHandScoreCacheFactoryTest {

    @Test
    fun validate() {
        val fileHandScoreCache = FileHandScoreCacheFactory.create()
        val defaultCache = DefaultHandScoreCacheFactory.create()
        assertEquals(defaultCache, fileHandScoreCache)
    }
}
