package es.unizar.webeng.hello

import org.springframework.stereotype.Service
import java.time.Instant
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Represents an entry in the request history log.
 *
 * @property name The name provided by the user.
 * @property message The greeting message generated for the request.
 * @property timestamp The timestamp when the request entry was created.
 */
data class HistoryLog(
    val name: String,
    val message: String,
    val timestamp: Instant = Instant.now()
)

/**
 * Service responsible for managing in-memory request history logs.
 */
@Service
class HistoryService {

    private val logs = CopyOnWriteArrayList<HistoryLog>()

    /**
     * Adds a new entry to the history log.
     *
     * @param name The name of the user making the request.
     * @param message The associated greeting message
     */
    fun addLog(name: String, message: String) {
        logs.add(0, HistoryLog(name, message))
    }

    /**
     * Retrieves the most recent entries from the history log up to the specified limit.
     *
     * @param limit The maximum number of log entries to return (default is 10).
     * @return A list of recent [HistoryLog] instances.
     */
    fun getRecentLogs(limit: Int = 10): List<HistoryLog> = logs.take(limit)
}