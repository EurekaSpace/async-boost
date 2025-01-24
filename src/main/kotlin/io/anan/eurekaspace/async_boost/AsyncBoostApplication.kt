package io.anan.eurekaspace.async_boost

import com.sun.management.OperatingSystemMXBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

@SpringBootApplication
class AsyncBoostApplication

fun main(args: Array<String>) {
	runApplication<AsyncBoostApplication>(*args)

	val osBean = ManagementFactory.getOperatingSystemMXBean() as? OperatingSystemMXBean
	val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()

	Thread {
		while (true) {
			if (osBean != null) {
				// CPU 및 메모리 사용량
				val cpuLoad = osBean.cpuLoad * 100
				val freeMemory = osBean.freeMemorySize / (1024 * 1024)
				val totalMemory = osBean.totalMemorySize / (1024 * 1024)

				println(
						"""
                    ==============================
                    System CPU Load: %.2f%%
                    System Memory: Free = ${freeMemory} MB, Total = ${totalMemory} MB
                    ==============================
                    """.trimIndent().format(cpuLoad)
				)
			}

			// 스레드 사용량 출력
			val threadCount = threadMXBean.threadCount
			val peakThreadCount = threadMXBean.peakThreadCount
			val daemonThreadCount = threadMXBean.daemonThreadCount

			println(
					"""
                Current Thread Count: $threadCount
                Peak Thread Count: $peakThreadCount
                Daemon Thread Count: $daemonThreadCount
                ==============================
                """.trimIndent()
			)

			Thread.sleep(2000) // 2초 간격으로 실행
		}
	}.start()
}
