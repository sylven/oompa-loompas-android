package app.sylven.oompaloompas.networking

import app.sylven.oompaloompas.AppConstants
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.Charset

class APIAvailabilityTest {
    @Test
    @Throws(Exception::class)
    fun testAvailability() {
        val connection: URLConnection =
            URL(AppConstants.OOMPA_LOOMPA_API_BASE_URL+"oompa-loompas").openConnection()
        val response: InputStream = connection.getInputStream()
        val buffer = StringBuffer()
        BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
        }
        assert(buffer.length > 0)
    }
}